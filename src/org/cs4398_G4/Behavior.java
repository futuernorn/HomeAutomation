package org.cs4398_G4;


import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Timer;
import java.util.TimerTask;

import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.trigger.GpioSetStateTrigger;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class Behavior implements GpioPinListenerDigital{
	List<Condition> conditions;
	List<Action> actions;
	String name;
	
	public Behavior(String name, List<Condition> conditions, List<Action> actions) {
		this.name = name;
		this.actions = actions;
		this.conditions = conditions;
		for (Condition condition: conditions) {
			condition.getSensor().getInputPins().addListener(this);
		}
	}
	
	public List<Condition> getConditions() {
		return conditions;
	}
	
	public List<Action> getActions() {
		return actions;
	}
	
	private void Run() {
		for (final Action action : actions) {
			action.getActuator().getOutputPins().setState(action.getPinState());
			System.out.println("running action:" + action);
			new Timer().schedule(new TimerTask() {
				  @Override
				  public void run() {
					  action.getActuator().getOutputPins().toggle();
					  System.out.println("stopping action:" + action);
				  }
				}, (long) action.getDuration() * 1000);
		}
	}

	public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
		System.out.println(" --> GPIO PIN STATE CHANGE: " + event.getPin() + " = " + event.getState());
		for (Condition condition: conditions) {
			for (Entry<String, Pin> entry : condition.getSensor().getInputPinNumbers().entrySet()) {
				System.out.println("comaparing condition's pin number (" + entry.getValue() +") to event's pin number (" + event.getPin().getPin() + ") => " + (entry.getValue() == event.getPin().getPin()));
				
				if(entry.getValue() == event.getPin().getPin()) {
					System.out.println("comaparing condition's pin state (" + condition.getPinState() +") to event's pin number (" + event.getState() + ") => " + (condition.getPinState() == event.getState()));
					if (condition.getPinState() == event.getState())
						if (condition.getElapsedTime() == null)
							condition.startTimer(this);
					} else {
						if (condition.getElapsedTime() != null)
							condition.stopTimer();
							condition.setConditionMet(false);
				}
			}
		}
	}
		
		
	
	
	public String toString() {
		return name;
	}

	public void conditionMet() {
		boolean runActions = true;
		for (Condition condition: conditions) {
			if (!condition.isConditionMet())
				runActions = false;
		}
		System.out.println(" --> conditionMet / runActions => " + runActions);
		if (runActions)
			Run();
		
	}

	public void disconnect() {
		for (Condition condition: conditions) {
			condition.getSensor().getInputPins().removeListener(this);
		}
		
	}


}
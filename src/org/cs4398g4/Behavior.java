package org.cs4398g4;

import java.util.List;
import java.util.Map.Entry;
import java.util.Timer;
import java.util.TimerTask;

import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class Behavior implements GpioPinListenerDigital {
	List<Condition> conditions;
	List<Action> actions;
	String name;
	static int currentCount = 1;
	final int id;
	BaseStation baseStation;

	public int getId() {
		return id;
	}

	/**
	 * @param name
	 *            : the name of the behavior, from user input
	 * @param conditions
	 *            : the list of conditions that must be met for the behavior to
	 *            occur
	 * @param actions
	 *            : List of actions to be perfomed when the list of conditions
	 *            is met
	 */
	public Behavior(String name, List<Condition> conditions, List<Action> actions, BaseStation baseStation) {
		this.baseStation = baseStation;
		this.id = currentCount;
		currentCount++;
		// isRunning = 0;
		this.name = name;
		this.actions = actions;
		this.conditions = conditions;
		for (Condition condition : conditions) {
			if (condition.getSensor().getInputPin() != null)
				condition.getSensor().getInputPin().addListener(this);
		}
	}

	public List<Condition> getConditions() {
		return conditions;
	}

	public List<Action> getActions() {
		return actions;
	}

	@Override
	public int hashCode() {
		return id;
	}

	/**
	 * Runs the list of actions
	 */

	public void Run() {
		// if (isRunning > 0)
		// return;

		for (final Action action : actions) {
			// isRunning++;
			if (action.getPinState() == PinState.HIGH)
				try {
					action.getActuator().turnOn();
				} catch (IncorrectPinStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			else
				try {
					action.getActuator().turnOff();
				} catch (IncorrectPinStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			baseStation.getLog().addLog("running action:" + action);
			new Timer().schedule(new TimerTask() {
				@Override
				public void run() {
					action.getActuator().getOutputPin().toggle();
					baseStation.getLog().addLog("stopping action:" + action);
					// isRunning--;
				}
			}, (long) action.getDuration() * 1000);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.pi4j.io.gpio.event.GpioPinListenerDigital#
	 * handleGpioPinDigitalStateChangeEvent
	 * (com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent)
	 */
	public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
		for (Condition condition : conditions) {
			for (Entry<String, Pin> entry : condition.getSensor().getInputPinNumbers().entrySet()) {

				if (entry.getValue() == event.getPin().getPin()) {

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

	/**
	 * Checks if conditions are met and calls the Run function if they are
	 */
	public void conditionMet() {
		boolean runActions = true;
		for (Condition condition : conditions) {
			if (!condition.isConditionMet())
				runActions = false;
		}
		baseStation.getLog().addLog(this + " --> conditionMet / runActions => " + runActions);
		if (runActions) {
			Run();
			baseStation.runConnectedBehaviors(id);
		}

	}

	public void disconnect() {
		for (Condition condition : conditions) {
			condition.getSensor().getInputPin().removeListener(this);
		}

	}

}
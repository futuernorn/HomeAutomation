package org.cs4398_G4;


import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Timer;
import java.util.TimerTask;

import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.trigger.GpioSetStateTrigger;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class Behavior implements GpioPinListenerDigital{
	List<Condition> conditions;
	
	HashMap<Actuator, Integer> actions;
	public Behavior(List<Condition> conditions) {
//		this.actions = actions;
		this.conditions = conditions;
	}
	
	private void Run() {
//		for (final Entry<Actuator, Integer> entry : actions.entrySet()) {
//			entry.getKey().run();
//			new Timer().schedule(new TimerTask() {
//				  @Override
//				  public void run() {
//				   entry.getKey().run();
//				  }
//				}, (long) entry.getValue());
//		}
	}

	public void handleGpioPinDigitalStateChangeEvent(
			GpioPinDigitalStateChangeEvent arg0) {
		// TODO Auto-generated method stub
		
	}


}
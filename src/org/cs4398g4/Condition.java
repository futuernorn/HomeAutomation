package org.cs4398g4;

import java.util.Timer;
import java.util.TimerTask;

import com.pi4j.io.gpio.PinState;

public class Condition {
	Sensor sensor;
	PinState pinState;
	public PinState getPinState() {
		return pinState;
	}

	public void setPinState(PinState pinState) {
		this.pinState = pinState;
	}

	Integer duration;
	private Timer elapsedTime;
	private boolean conditionMet;
	
	public boolean isConditionMet() {
		return conditionMet;
	}

	public void setConditionMet(boolean conditionMet) {
		this.conditionMet = conditionMet;
	}

	public Condition (Sensor sensor, PinState pinState, Integer duration) {
		this.sensor = sensor;
		this.pinState = pinState;
		this.duration = duration;
		conditionMet=false;
		setElapsedTime(null);
	}
	
	public String toString() {
		return sensor.toString() + " to " + pinState + " for " + duration + "s";
	}

	public Sensor getSensor() {
		// TODO Auto-generated method stub
		return sensor;
	}

	public Timer getElapsedTime() {
		return elapsedTime;
	}

	public void setElapsedTime(Timer elapsedTime) {
		this.elapsedTime = elapsedTime;
	}
	
	public void startTimer(final Behavior behavior) {
		if (elapsedTime == null) {
			elapsedTime = new Timer();
			elapsedTime.schedule(new TimerTask() {
			  @Override
			  public void run() {
			   setConditionMet(true);
			   elapsedTime = null;
			   behavior.conditionMet();
			  }
			}, (long) duration*1000);
		}
	}

	public void stopTimer() {
		elapsedTime.cancel();
		elapsedTime.purge();
		
		
	}
}

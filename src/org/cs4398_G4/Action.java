package org.cs4398_G4;

import com.pi4j.io.gpio.PinState;

public class Action {
	Actuator actuator;
	PinState pinState;
	Integer duration;
	
	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public PinState getPinState() {
		return pinState;
	}

	public void setPinState(PinState pinState) {
		this.pinState = pinState;
	}

	
	
	public Action (Actuator actuator, PinState pinState, Integer duration) {
		this.actuator = actuator;
		this.pinState = pinState;
		this.duration = duration;
	}
	
	public String toString() {
		return actuator.toString() + " to " + pinState + " for " + duration + "s";
	}

	public Actuator getActuator() {
		return actuator;
	}
}

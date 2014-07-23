package org.cs4398_G4;

public class Action {
	Actuator actuator;
	ComponentState pinState;
	Integer duration;
	
	public Action (Actuator actuator, ComponentState pinState, Integer duration) {
		this.actuator = actuator;
		this.pinState = pinState;
		this.duration = duration;
	}
	
	public String toString() {
		return actuator.toString() + " to " + pinState + " for " + duration + "s";
	}

	public Actuator getActuator() {
		// TODO Auto-generated method stub
		return actuator;
	}
}

package org.cs4398_G4;

public class Condition {
	Sensor sensor;
	ComponentState pinState;
	Integer duration;
	
	public Condition (Sensor sensor, ComponentState pinState, Integer duration) {
		this.sensor = sensor;
		this.pinState = pinState;
		this.duration = duration;
	}
	
	public String toString() {
		return sensor.toString() + " to " + pinState + " for " + duration + "s";
	}
}

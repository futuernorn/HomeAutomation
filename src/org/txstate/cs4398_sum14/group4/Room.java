package org.txstate.cs4398_sum14.group4;

import java.util.ArrayList;

public class Room {
	ArrayList<Component> components;
	ArrayList<RFID> cardReaders;
	
	public Room() {
		components = new ArrayList<Component>();
		cardReaders  = new ArrayList<RFID>();
	}
	
	public void AddComponent(Component newComp) {
		components.add(newComp);
	}
	
	public void ToggleAllComponents() {
		
	}
	
	public ArrayList<Sensor> GetSensors() {
		return new ArrayList<Sensor>();
		
	}
	
	public ArrayList<Actuator> GetActuators() {
		return new ArrayList<Actuator>();
		
	}
	
	public void TurnOn() {
		
	}
	
	public void TurnOff() {
		
	}
}

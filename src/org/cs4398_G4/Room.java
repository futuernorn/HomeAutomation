package org.cs4398_G4;

import java.util.ArrayList;

public class Room {
	ArrayList<Component> components;
	ArrayList<RFID> cardReaders;
	String name;
	
	public Room(String name) {
		this.name = name;
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

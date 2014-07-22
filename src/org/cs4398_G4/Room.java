package org.cs4398_G4;

import java.util.ArrayList;
import java.util.List;

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
	
	public List<Sensor> GetSensors() {
		List<Sensor> sensors = new ArrayList<Sensor>();
		for (Component component : components) {
			if (component instanceof Sensor) 
				sensors.add((Sensor) component);
		}
		return sensors;
		
	}
	
	public ArrayList<Actuator> GetActuators() {
		return new ArrayList<Actuator>();
		
	}
	
	public void TurnOn() {
		
	}
	
	public void TurnOff() {
		
	}
}

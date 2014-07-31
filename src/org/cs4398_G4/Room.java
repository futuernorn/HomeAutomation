package org.cs4398_G4;

import java.util.ArrayList;
import java.util.Collection;
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
	
//	public List<? extends Component> GetComponents(Class<?> cls) {
//		List<? extends Object> componentList = new ArrayList<Component>();
//		for (Component component : components) {
//			if (cls.isInstance(component)) 
//				componentList.addAll((Collection<?>) component);
//		}
//		return (List<? extends Component>) componentList;
//		
//	}
	
	public List<Sensor> GetSensors() {
		List<Sensor> sensors = new ArrayList<Sensor>();
		for (Component component : components) {
			if (component instanceof Sensor) 
				sensors.add((Sensor) component);
		}
		return sensors;
		
		
	}
	public List<Actuator> GetActuators() {
		List<Actuator> actuators = new ArrayList<Actuator>();
		for (Component component : components) {
			if (component instanceof Actuator) 
				actuators.add((Actuator) component);
		}
		return actuators;
		
		
	}
	
	public void TurnOn() {
		
	}
	
	public void TurnOff() {
		
	}

//	public List<? extends Component> GetComponentsByType(Class<?> type) {
//		List<? extends Component> componentList = new ArrayList<Sensor>();
//		for (Component component : components) {
//			if (component.type == type) 
//				componentList.add( component);
//		}
//		return componentList;
//	}
	
	public String toString() {
		return name;
	}
}

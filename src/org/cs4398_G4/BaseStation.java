package org.cs4398_G4;

import java.util.ArrayList;
import java.util.List;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
 

/**
 * @author Jeffrey Hogan
 *
 */
public class BaseStation {
	ArrayList<Room> house;
	ArrayList<User> users;
	private Security securitySystem;
	
	public BaseStation() {
		house = new ArrayList<Room>();
		users = new ArrayList<User>();
	}
	
	String GetStatus() {
		
		return "Home lookin' p good bro!";
	}
	


	public void AddRoom(Room newRoom) {
		// TODO Auto-generated method stub
		house.add(newRoom);
		
	}
	
	public List<Sensor> GetSensors() {
		List<Sensor> sensors = new ArrayList<Sensor>();
		for (Room room : house) {
			sensors.addAll(room.GetSensors());
		}
		return sensors;
	}
	
	public List<Actuator> GetActuators() {
	List<Actuator> actuator = new ArrayList<Actuator>();
		for (Room room : house) {
			actuator.addAll(room.GetActuators());
		}
		return actuator;
	}

//	public List<? extends Component> GetComponents(Class<?> cls) {
//		List<? extends Component> components = new ArrayList<Component>();
//		for (Room room : house) {
//			components.addAll(room.GetComponents(cls));
//		}
//		return components;
//	}


//	public List<? extends Component> GetSensors() {
//		return GetComponentsByType(Sensor.class);
//	}
//
//	public List<Component> GetComponentsByType(Class<?> type) {
//		List<Component> components = new ArrayList<Component>();
//		for (Room room : house) {
//			components.addAll(room.GetComponentsByType(type));
//			
//		}
//		return components;
//	}
}
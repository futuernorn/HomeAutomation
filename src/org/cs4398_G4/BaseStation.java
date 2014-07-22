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

	public ArrayList<Actuator> GetActuators() {
		ArrayList<Actuator> actuators = new ArrayList<Actuator>();
		for (Room room : house) {
			actuators.addAll(room.GetActuators());
			
		}
		return null;
	}

	public List<Sensor> GetSensors() {
		List<Sensor> sensors = new ArrayList<Sensor>();
		for (Room room : house) {
			sensors.addAll(room.GetSensors());
			
		}
		return sensors;
	}
}

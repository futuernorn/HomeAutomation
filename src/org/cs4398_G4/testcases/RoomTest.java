package org.cs4398_G4.testcases;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.cs4398_G4.Actuator;
import org.cs4398_G4.Room;
import org.cs4398_G4.Sensor;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;

public class RoomTest {

	@Test
	public void AddComponentsTest() {
		
		//Room tester object
		Room RoomTester = new Room("testRoom");
		
		assertEquals("Room should have no actuators when created", 0, RoomTester.GetActuators().size());
		assertEquals("Room should have no sensors when created", 0, RoomTester.GetSensors().size());
		
		//Create Hashmap for pins
		HashMap<String, Pin> inputPinNumbers = new HashMap<String, Pin>();
		HashMap<String, Pin> outputPinNumbers = new HashMap<String, Pin>();
		
		//put pins for sensor and light
		inputPinNumbers.put("SensorInput", RaspiPin.GPIO_00);
		outputPinNumbers.put("SensorOutput", RaspiPin.GPIO_01);
		
		inputPinNumbers.put("LightInput", RaspiPin.GPIO_03);
		outputPinNumbers.put("LightOutput", RaspiPin.GPIO_04);
		
		//create a sensor
		Sensor testSensor = new Sensor("test Sensor", inputPinNumbers, outputPinNumbers);
		
		//create light
		Actuator testLight = new Actuator("test light", inputPinNumbers, outputPinNumbers);
		
		//Add sensor and light to room
		RoomTester.AddComponent(testLight);
		RoomTester.AddComponent(testSensor);
		
		//check if room contains one light and one sensor
		assertEquals("Room should contain one sensor", 1, RoomTester.GetSensors().size());
		assertEquals("Room should contain one light", 1, RoomTester.GetActuators().size());
		
		
	}

}

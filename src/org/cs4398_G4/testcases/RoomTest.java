package org.cs4398_G4.testcases;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

import org.cs4398_G4.Actuator;
import org.cs4398_G4.Room;
import org.cs4398_G4.Sensor;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;

@RunWith(Parameterized.class)
public class RoomTest {
	
	//Create Hashmap for tests
	private HashMap<String, Pin> inputPinNumbers = new HashMap<String, Pin>();
	private HashMap<String, Pin> outputPinNumbers = new HashMap<String, Pin>();
	
	public RoomTest(HashMap<String, Pin> input, HashMap<String, Pin> output) {
		this.inputPinNumbers = input;
		this.outputPinNumbers = output;
	}
	
	// creates the test data
	@Parameters
	public static Collection<Object[]> generateTestData() {
		
		HashMap<String, Pin> inputEmpty = new HashMap<String, Pin>();
		HashMap<String, Pin> outputEmpty = new HashMap<String, Pin>();
		
		HashMap<String, Pin> input = new HashMap<String, Pin>();
		HashMap<String, Pin> output = new HashMap<String, Pin>();
		
		input.put("SensorInput", RaspiPin.GPIO_00);
		output.put("SensorOutput", RaspiPin.GPIO_01);
		
		input.put("LightInput", RaspiPin.GPIO_03);
		output.put("LightOutput", RaspiPin.GPIO_04);
		
		Object[][] data = new Object[][] { { input, output },
		{ inputEmpty, outputEmpty }
		};
		
		return Arrays.asList(data);
	}

	@Test
	public void AddComponentsTest() {
		
		//Room tester object
		Room RoomTester = new Room("testRoom");
		
		assertEquals("Room should have no actuators when created", 0, RoomTester.GetActuators().size());
		assertEquals("Room should have no sensors when created", 0, RoomTester.GetSensors().size());
		
		//create a sensor
		Sensor testSensor = new Sensor("Test Sensor", inputPinNumbers, outputPinNumbers);
		
		//create light
		Actuator testLight = new Actuator("Test Light", inputPinNumbers, outputPinNumbers);
		
		//Add sensor and light to room
		RoomTester.AddComponent(testLight);
		RoomTester.AddComponent(testSensor);
		
		//check if room contains one light and one sensor
		assertEquals("Room should contain one sensor", 1, RoomTester.GetSensors().size());
		assertEquals("Room should contain one light", 1, RoomTester.GetActuators().size());
		
		
	}

}

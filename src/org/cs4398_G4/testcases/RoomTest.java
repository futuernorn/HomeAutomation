package org.cs4398_G4.testcases;

import static org.junit.Assert.*;


import java.util.HashMap;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;

public class RoomTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("@BeforeClass - oneTimeSetUp");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("@AfterClass - oneTimeTearDown");
	}
	
	//Test Case 1 - AddComponent()
	//size() components array should be one more
	//after component is added. Component should be off when added.
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void AddComponentTest() {
		
		//Room tester object
//		Room RoomTester = new Room();
		
		//Test Pins
		HashMap<String, Pin> inputPinNumbers = new HashMap<String, Pin>();
		inputPinNumbers.put("SensorInput", RaspiPin.GPIO_00);
		
		HashMap<String, Pin> outputPinNumbers = new HashMap<String, Pin>();
		outputPinNumbers.put("SensorOutput", RaspiPin.GPIO_01);
		
		inputPinNumbers.put("LightInput", RaspiPin.GPIO_03);
		
		outputPinNumbers.put("LightOutput", RaspiPin.GPIO_04);
		
		//Test Sensor Component
//		Sensor testSensor = new Sensor(inputPinNumbers, outputPinNumbers, "testSensor");
//		RoomTester.AddComponent(testSensor);
//		
//		//Test Light Component
//		Actuator testLight = new Actuator(inputPinNumbers, outputPinNumbers);
//		RoomTester.AddComponent(testLight);
		
		
	}

}

package org.cs4398_G4.testcases;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.cs4398_G4.Actuator;
import org.cs4398_G4.BaseStation;
import org.cs4398_G4.Controller;
import org.cs4398_G4.Room;
import org.cs4398_G4.Sensor;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;

public class ControllerTest {

	@Test
	public void test() {
		BaseStation BaseTest = new BaseStation();
		Controller tester = new Controller(BaseTest);
		
		Room testRoom = new Room("Test Room");
		BaseTest.AddRoom(testRoom);
		
		//Create Hashmap for pins
		HashMap<String, Pin> inputPinNumbers = new HashMap<String, Pin>();
		HashMap<String, Pin> outputPinNumbers = new HashMap<String, Pin>();
								
		//put pins for sensor and light
		inputPinNumbers.put("SensorInput", RaspiPin.GPIO_08);
		outputPinNumbers.put("SensorOutput", RaspiPin.GPIO_01);
								
		inputPinNumbers.put("LightInput", RaspiPin.GPIO_02);
		outputPinNumbers.put("LightOutput", RaspiPin.GPIO_03);
								
		//create sensor and light
		Actuator testLight = new Actuator("test light", inputPinNumbers, outputPinNumbers);
		Sensor testSensor = new Sensor("testSensor", inputPinNumbers, outputPinNumbers);
		
		tester.AddComponent(testSensor, testRoom);
		tester.AddComponent(testLight, testRoom);
		
	}
	
	
	
}

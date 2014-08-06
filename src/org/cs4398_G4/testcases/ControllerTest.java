package org.cs4398_G4.testcases;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;

//Bug has to do with outputPins not being initialized in Components
//This results in an error in the actuator file when calling Initialize()

@RunWith(Parameterized.class)
public class ControllerTest {
	
	BaseStation testBase;
	Controller testController;
	
	private HashMap<String, Pin> inputPinNumbers = new HashMap<String, Pin>();
	private HashMap<String, Pin> outputPinNumbers = new HashMap<String, Pin>();
	
	public ControllerTest(HashMap<String, Pin> input, HashMap<String, Pin> output) {
		testController = new Controller(testBase);
		
		this.inputPinNumbers = input;
		this.outputPinNumbers = output;
	}
	
	@Parameters
	public static Collection<Object[]> generateTestData() {
		
		HashMap<String, Pin> inputEmpty = new HashMap<String, Pin>();
		HashMap<String, Pin> outputEmpty = new HashMap<String, Pin>();
		
		HashMap<String, Pin> input1 = new HashMap<String, Pin>();
		HashMap<String, Pin> output1 = new HashMap<String, Pin>();
		
		input1.put("SensorInput", RaspiPin.GPIO_00);
		output1.put("SensorOutput", RaspiPin.GPIO_01);
		
		input1.put("LightInput", RaspiPin.GPIO_03);
		output1.put("LightOutput", RaspiPin.GPIO_04);
		
		Object[][] data = new Object[][] { { inputEmpty, outputEmpty },
				{ input1, output1 }
		};

		return Arrays.asList(data);
		
	}
	
	@Test
	public void TestSensorControl() {
		
		Room testRoom = new Room("Test Room");
								
		Sensor testSensor = new Sensor("testSensor", inputPinNumbers, outputPinNumbers);
		
		testController.AddComponent(testSensor, testRoom);
		
		assertEquals("Incorrect Number of Sensors (GetComponents function)", 1, testController.getComponents().size());
		assertEquals("Incorrect Number of Sensors (GetSensors function)", 1, testController.GetSensors().size());
		
	}
	
	@Test
	public void TestLightControl() {
		
		Room testRoom = new Room("Test Room");
								
		Actuator testLight = new Actuator("test light", inputPinNumbers, outputPinNumbers);
		
		testController.AddComponent(testLight, testRoom);
		
		assertEquals("Incorrect Number of Lights (GetComponents function)", 1, testController.getComponents().size());
		assertEquals("Incorrect Number of Lights (GetActuators function)", 1, testController.GetActuators().size());
		
	}
	
}

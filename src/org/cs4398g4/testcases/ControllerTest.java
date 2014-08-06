package org.cs4398g4.testcases;


import static org.junit.Assert.*;

import java.awt.Desktop.Action;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.locks.Condition;

import javax.media.j3d.Behavior;
import javax.media.j3d.Sensor;

import org.cs4398g4.Actuator;
import org.cs4398g4.BaseStation;
import org.cs4398g4.Component;
import org.cs4398g4.Controller;
import org.cs4398g4.Room;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;

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
								
		//Sensor testSensor = new Sensor("testSensor", inputPinNumbers, outputPinNumbers);
		
		//testController.addComponent(testSensor, testRoom);
		
		//assertEquals("Incorrect Number of Sensors (GetComponents function)", 1, testController.getComponents().size());
		//assertEquals("Incorrect Number of Sensors (GetSensors function)", 1, testController.GetSensors().size());
		
	}
	
	@Test
	public void TestLightControl() {
		
		Room testRoom = new Room("Test Room");
								
		Actuator testLight = new Actuator("test light", inputPinNumbers, outputPinNumbers);
		
		testController.addComponent(testLight, testRoom);
		
		//assertEquals("Incorrect Number of Lights (GetComponents function)", 1, testController.getComponents().size());
		//assertEquals("Incorrect Number of Lights (GetActuators function)", 1, testController.GetActuators().size());
		
	}

}

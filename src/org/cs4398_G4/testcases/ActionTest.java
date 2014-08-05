package org.cs4398_G4.testcases;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.cs4398_G4.Action;
import org.cs4398_G4.Actuator;
import org.cs4398_G4.Sensor;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

@RunWith(Parameterized.class)
public class ActionTest {
	
	//Create Hashmap for tests
	private HashMap<String, Pin> inputPinNumbers = new HashMap<String, Pin>();
	private HashMap<String, Pin> outputPinNumbers = new HashMap<String, Pin>();
	
	public ActionTest(HashMap<String, Pin> input, HashMap<String, Pin> output) {
		this.inputPinNumbers = input;
		this.outputPinNumbers = output;
	}
	
	// creates the test data
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
	public void CreateActionTest() {
		
		//create light
		Actuator testLight = new Actuator("test light", inputPinNumbers, outputPinNumbers);
		
		//create test actions and conditions for basestation behavior test
		Action testAction = new Action(testLight, PinState.HIGH, 5);
		
		//assert tests
		assertEquals("Duration incorrect", 5, testAction.getDuration().intValue());
		assertEquals("PinState incorrect", PinState.HIGH, testAction.getPinState());
		
		//call set duration and pin state functions
		testAction.setDuration(10);
		testAction.setPinState(PinState.LOW);
		
		//assert test new duration
		assertEquals("Duration incorrect", 10, testAction.getDuration().intValue());
		assertEquals("PinState incorrect", PinState.LOW, testAction.getPinState());
	}

}

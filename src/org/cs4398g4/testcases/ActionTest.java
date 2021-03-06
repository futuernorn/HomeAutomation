package org.cs4398g4.testcases;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

import org.cs4398g4.Action;
import org.cs4398g4.Actuator;
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
	private int duration;
	
	public ActionTest(HashMap<String, Pin> input, HashMap<String, Pin> output, int duration) {
		this.inputPinNumbers = input;
		this.outputPinNumbers = output;
		this.duration = duration;
	}
	
	// creates the test data
	@Parameters
	public static Collection<Object[]> generateTestData() {
		
		HashMap<String, Pin> inputEmpty = new HashMap<String, Pin>();
		HashMap<String, Pin> outputEmpty = new HashMap<String, Pin>();
		
		HashMap<String, Pin> input1 = new HashMap<String, Pin>();
		HashMap<String, Pin> output1 = new HashMap<String, Pin>();
		
		//First Test (empty)
		
		//Second Test
		
		input1.put("SensorInput", RaspiPin.GPIO_00);
		output1.put("SensorOutput", RaspiPin.GPIO_01);
		
		input1.put("LightInput", RaspiPin.GPIO_03);
		output1.put("LightOutput", RaspiPin.GPIO_04);
		
		Object[][] data = new Object[][] { { inputEmpty, outputEmpty, 0 },
											{ input1, output1, 0 },
		{ inputEmpty, outputEmpty, 1 },
		{ input1, output1, 1 },
		{ inputEmpty, outputEmpty, 2 },
		{ input1, output1, 2 },
		{ inputEmpty, outputEmpty, 10 },
		{ input1, output1, 10 }
										 };
		
		return Arrays.asList(data);
	}
	
	@Test
	public void ActionSetPinStateHighTest() {
		
		//create light
		Actuator testLight = new Actuator("test light", inputPinNumbers, outputPinNumbers);
		
		//create test actions and conditions for basestation behavior test
		Action testAction = new Action(testLight, PinState.HIGH, duration);
		
		//assert tests
		assertEquals("Duration incorrect", duration, testAction.getDuration().intValue());
		assertEquals("PinState incorrect", PinState.HIGH, testAction.getPinState());
		
		//call set duration and pin state functions
		testAction.setDuration(10);
		testAction.setPinState(PinState.LOW);
		
		//assert test new duration
		assertEquals("Duration incorrect", 10, testAction.getDuration().intValue());
		assertEquals("PinState incorrect", PinState.LOW, testAction.getPinState());
	}
	
	@Test
	public void ActionSetPinStateLowTest() {
		
		//create light
		Actuator testLight = new Actuator("test light", inputPinNumbers, outputPinNumbers);
		
		//create test actions and conditions for basestation behavior test
		Action testAction = new Action(testLight, PinState.LOW, duration);
		
		//assert tests
		assertEquals("Duration incorrect", duration, testAction.getDuration().intValue());
		assertEquals("PinState incorrect", PinState.LOW, testAction.getPinState());
		
		//call set duration and pin state functions
		testAction.setDuration(10);
		testAction.setPinState(PinState.HIGH);
		
		//assert test new duration
		assertEquals("Duration incorrect", 10, testAction.getDuration().intValue());
		assertEquals("PinState incorrect", PinState.HIGH, testAction.getPinState());
	}

}

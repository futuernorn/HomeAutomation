package org.cs4398_G4.testcases;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.cs4398_G4.Action;
import org.cs4398_G4.Actuator;
import org.cs4398_G4.Sensor;
import org.junit.Ignore;
import org.junit.Test;

import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class ActionTest {

	@Test
	public void test() {
		
		//Create Hashmap for pins
		HashMap<String, Pin> inputPinNumbers = new HashMap<String, Pin>();
		HashMap<String, Pin> outputPinNumbers = new HashMap<String, Pin>();
		
		//put pins for sensor and light
		inputPinNumbers.put("LightInput", RaspiPin.GPIO_03);
		outputPinNumbers.put("LightOutput", RaspiPin.GPIO_04);
		
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

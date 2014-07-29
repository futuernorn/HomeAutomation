package org.cs4398_G4.testcases;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.cs4398_G4.Action;
import org.cs4398_G4.Actuator;
import org.cs4398_G4.Behavior;
import org.cs4398_G4.Condition;
import org.cs4398_G4.Sensor;
import org.junit.Ignore;
import org.junit.Test;

import com.pi4j.io.gpio.GpioPin;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;

public class BehaviorTest {

	@Test
	public void EmptyBehaviorTest() {
		
		//-----Setup Behavior------
		
		List<Condition> testConditions = new ArrayList<Condition>();
		List<Action> testActions = new ArrayList<Action>();
				
		//Create Hashmap for pins
		HashMap<String, Pin> inputPinNumbers = new HashMap<String, Pin>();
		HashMap<String, Pin> outputPinNumbers = new HashMap<String, Pin>();
				
		//put pins for sensor and light
		inputPinNumbers.put("SensorInput", RaspiPin.GPIO_00);
		outputPinNumbers.put("SensorOutput", RaspiPin.GPIO_01);
				
		inputPinNumbers.put("LightInput", RaspiPin.GPIO_03);
		outputPinNumbers.put("LightOutput", RaspiPin.GPIO_04);
				
		//create sensor and light
		Actuator testLight = new Actuator("test light", inputPinNumbers, outputPinNumbers);
		Sensor testSensor = new Sensor("testSensor", inputPinNumbers, outputPinNumbers);
				
		//create test actions and conditions for basestation behavior test
		Action testAction = new Action(testLight, PinState.HIGH, 5);
		Condition testCondition = new Condition(testSensor, PinState.HIGH, 10);
		
		Behavior EmptyBehaviorTester = new Behavior("TestBehavior", testConditions, testActions);
		
//		testConditions.add(testCondition);
//		testActions.add(testAction);
//				
//		Behavior tester = new Behavior("TestBehavior", testConditions, testActions);
		
		//----Havent set up any tests yet----
		
		assertEquals("Conditions should be empty", 0, EmptyBehaviorTester.getConditions().size());
		assertEquals("Actions should be empty", 0, EmptyBehaviorTester.getActions().size());
//		
//		assertEquals("Conditions should be size 1", 1, tester.getConditions().size());
//		assertEquals("Actions should be size 1", 1, tester.getActions().size());
	}
	
	@Test
	public void BehaviorTest() {
		
		//-----Setup Behavior------
		
		List<Condition> testConditions = new ArrayList<Condition>();
		List<Action> testActions = new ArrayList<Action>();
				
		//Create Hashmap for pins
		HashMap<String, Pin> inputPinNumbers = new HashMap<String, Pin>();
		HashMap<String, Pin> outputPinNumbers = new HashMap<String, Pin>();
				
		//put pins for sensor and light
		inputPinNumbers.put("SensorInput", RaspiPin.GPIO_00);
		outputPinNumbers.put("SensorOutput", RaspiPin.GPIO_01);
				
		inputPinNumbers.put("LightInput", RaspiPin.GPIO_03);
		outputPinNumbers.put("LightOutput", RaspiPin.GPIO_04);
				
		//create sensor and light
		Actuator testLight = new Actuator("test light", inputPinNumbers, outputPinNumbers);
		Sensor testSensor = new Sensor("testSensor", inputPinNumbers, outputPinNumbers);
				
		//create test actions and conditions for basestation behavior test
		Action testAction = new Action(testLight, PinState.HIGH, 5);
		Condition testCondition = new Condition(testSensor, PinState.HIGH, 10);
		
		//testConditions.add(testCondition);
		testActions.add(testAction);
				
		Behavior tester = new Behavior("TestBehavior", testConditions, testActions);
		
		assertEquals("Conditions should be size 1", 0, tester.getConditions().size());
		assertEquals("Actions should be size 1", 1, tester.getActions().size());
	}

}

package org.cs4398g4.testcases;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.cs4398g4.Action;
import org.cs4398g4.Actuator;
import org.cs4398g4.BaseStation;
import org.cs4398g4.Behavior;
import org.cs4398g4.Condition;
import org.cs4398g4.Sensor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

@RunWith(Parameterized.class)
public class BehaviorTest {
	
	private HashMap<String, Pin> inputPinNumbers = new HashMap<String, Pin>();
	private HashMap<String, Pin> outputPinNumbers = new HashMap<String, Pin>();
	
	public BehaviorTest(HashMap<String, Pin> input, HashMap<String, Pin> output) {
		this.inputPinNumbers = input;
		this.outputPinNumbers = output;
	}
	
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
		
		Object[][] data = new Object[][] { { inputEmpty, outputEmpty },
				{ input1, output1, }
			 };

return Arrays.asList(data);
		
	}
	
	@Test
	public void EmptyBehaviorTest() {
		
		BaseStation testBase = new BaseStation();
		
		//-----Setup Behavior------
		
		List<Condition> testConditions = new ArrayList<Condition>();
		List<Action> testActions = new ArrayList<Action>();
		
		Behavior EmptyBehaviorTester = new Behavior("TestBehavior", testConditions, testActions, testBase);
		
		assertEquals("Conditions should be empty", 0, EmptyBehaviorTester.getConditions().size());
		assertEquals("Actions should be empty", 0, EmptyBehaviorTester.getActions().size());
		
	}
	
	@Test
	public void BehaviorTestFunction() {
		
		BaseStation testBase = new BaseStation();
		
		//-----Setup Behavior------
		
		List<Condition> testConditions = new ArrayList<Condition>();
		List<Action> testActions = new ArrayList<Action>();
				
		//create sensor and light
		Actuator testLight = new Actuator("test light", inputPinNumbers, outputPinNumbers);
		Sensor testSensor = new Sensor("testSensor", inputPinNumbers, outputPinNumbers);
				
		//create test actions and conditions for basestation behavior test
		Action testAction = new Action(testLight, PinState.HIGH, 5);
		Condition testCondition = new Condition(testSensor, PinState.HIGH, 10);
		
		testActions.add(testAction);
		testConditions.add(testCondition);
		
		Behavior BehaviorTester = new Behavior("TestBehavior", testConditions, testActions, testBase);
		
		assertEquals("Conditions should be empty", 1, BehaviorTester.getConditions().size());
		assertEquals("Actions should be empty", 1, BehaviorTester.getActions().size());
		
	}

}

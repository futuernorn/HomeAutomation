package org.cs4398g4.testcases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.cs4398g4.Action;
import org.cs4398g4.Actuator;
import org.cs4398g4.BaseStation;
import org.cs4398g4.Behavior;
import org.cs4398g4.Condition;
import org.cs4398g4.Sensor;
import org.junit.Ignore;

import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class ConditionTest {

	@Ignore
	public void Test() {
		
		//***********************
		//Something thats throwing me off is how the Condition function
		//StartTimer takes a Behavior as a parameter when behaviors
		//contain an array of Conditions as variables.
		//
		//I think it would make sense for the condition to not rely on a behavior
		//in order to determine if a condition has been met, as of now
		//I have to create a behavior in order to test a condition, which
		//seems strange. I might not be understanding your implementation fully though.
		//Going to ignore this test for now.
		//***********************
		BaseStation baseStation = new BaseStation();
		//------setup test condition------
		//Create Hashmap for pins
		HashMap<String, Pin> inputPinNumbers = new HashMap<String, Pin>();
		HashMap<String, Pin> outputPinNumbers = new HashMap<String, Pin>();
				
		//put pins for sensor and light
		inputPinNumbers.put("SensorInput", RaspiPin.GPIO_00);
		outputPinNumbers.put("SensorOutput", RaspiPin.GPIO_01);
				
		//create sensor
		Sensor testSensor = new Sensor("testSensor", inputPinNumbers, outputPinNumbers);
				
		//create test condition
		Condition testCondition = new Condition(testSensor, PinState.HIGH, 10);
		
		
		
		//-----Setup Test Behavior------
		
		List<Condition> testConditions = new ArrayList<Condition>();
		List<Action> testActions = new ArrayList<Action>();
				
		//put pins for sensor and light
		inputPinNumbers.put("BehaviorSensorInput", RaspiPin.GPIO_02);
		outputPinNumbers.put("BehaviorSensorOutput", RaspiPin.GPIO_03);
				
		inputPinNumbers.put("LightInput", RaspiPin.GPIO_04);
		outputPinNumbers.put("LightOutput", RaspiPin.GPIO_05);
				
		//create sensor and light
		Actuator testLight = new Actuator("testLight", inputPinNumbers, outputPinNumbers);
		Sensor behaviorSensor = new Sensor("testBehaviorSensor", inputPinNumbers, outputPinNumbers);
				
		//create test actions and conditions for basestation behavior test
		Action testAction = new Action(testLight, PinState.HIGH, 5);
		Condition behaviorCondition = new Condition(behaviorSensor, PinState.HIGH, 10);
				
		testConditions.add(behaviorCondition);
		testActions.add(testAction);
				
		Behavior testBehavior = new Behavior("TestBehavior", testConditions, testActions, baseStation);
		
		
		//---startTimer Test
		
		testCondition.startTimer(testBehavior);
		
	}

}

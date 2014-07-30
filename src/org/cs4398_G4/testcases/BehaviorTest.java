package org.cs4398_G4.testcases;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.cs4398_G4.Action;
import org.cs4398_G4.Actuator;
import org.cs4398_G4.BaseStation;
import org.cs4398_G4.Behavior;
import org.cs4398_G4.Condition;
import org.cs4398_G4.Controller;
import org.cs4398_G4.Room;
import org.cs4398_G4.Sensor;
import org.junit.Ignore;
import org.junit.Test;

import com.pi4j.io.gpio.GpioPin;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;

public class BehaviorTest {
	
//	------------Read this------------
//	I think part of the issue with the behavior
//	test right now has to do with behavior containing
//	multiple actions and conditions. For simplicity,
//	I think a behavior should work like this:
//		
//		One behavior has one condition and one action.
//		The action should contain a TimerTask.
//		The behavior, once given a condition and action,
//		starts the timer that is contained in the condition object
//		and schedules the TimerTask using the Task contained in the action object.
//		I dont think there be an array or list of conditions
//		and actions because it will get really complicated...
//		Which condition is assigned to which action? Multiple conditions for
//		an action possible? Dont think theres time for figuring that out unless
//		you have a good idea of how to do it (I dont :( )
	
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
		
		assertEquals("Conditions should be empty", 0, EmptyBehaviorTester.getConditions().size());
		assertEquals("Actions should be empty", 0, EmptyBehaviorTester.getActions().size());
		
	}
	
	@Test
	public void BehaviorTest() {
		
		//-----Setup Behavior------
		
		BaseStation testBase = new BaseStation();
		Controller testController = new Controller(testBase);
		
		Room testRoom = new Room("Test Room");
		testBase.AddRoom(testRoom);
		
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
		
		testController.AddComponent(testSensor, testRoom);
		//testController.AddComponent(testLight, testRoom);
		
		//create test actions and conditions for basestation behavior test
		Action testAction = new Action(testLight, PinState.HIGH, 5);
		Condition testCondition = new Condition(testSensor, PinState.HIGH, 10);
		
		testConditions.add(testCondition);
		testActions.add(testAction);
				
		Behavior tester = new Behavior("TestBehavior", testConditions, testActions);
		
		assertEquals("Conditions should be size 1", 1, tester.getConditions().size());
		assertEquals("Actions should be size 1", 1, tester.getActions().size());
		
	}

}

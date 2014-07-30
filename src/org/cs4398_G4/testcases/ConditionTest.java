package org.cs4398_G4.testcases;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;

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

import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class ConditionTest {

	@Test
	public void Test() {
		
		//-----Setup Behavior and Condition for testing------
		
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
		testController.AddComponent(testLight, testRoom);
				
		//create test actions and conditions for baseStation behavior test
		Action testAction = new Action(testLight, PinState.HIGH, 5);
		Condition testCondition = new Condition(testSensor, PinState.HIGH, 10);
				
		testConditions.add(testCondition);
		testActions.add(testAction);
						
		Behavior testBehavior = new Behavior("TestBehavior", testConditions, testActions);
		
		
		//---Tests-----
		
		if(testAction.getActuator().GetIsOn()) {
			fail("Light should be off");
		}
		
		testCondition.setConditionMet(true);
		testBehavior.conditionMet();
		
		if(testAction.getPinState() == PinState.LOW) {
			fail("PinState for light is LOW");
		}
		
		if(!testAction.getActuator().GetIsOn()) {
			fail("Light should be on");
		}
		
	}

}

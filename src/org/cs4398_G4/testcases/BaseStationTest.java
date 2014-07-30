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
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class BaseStationTest {

	@Test
	public void test() {
		
		//----create objects for tests----
		BaseStation BaseTester = new BaseStation();
		Controller testController = new Controller(BaseTester);
		
		assertEquals("Number of rooms should be zero", 0 ,BaseTester.getRooms().size());
		
		Room testRoom = new Room("Test Room");
		BaseTester.AddRoom(testRoom);
		
		List<Condition> testConditions = new ArrayList<Condition>();
		List<Action> testActions = new ArrayList<Action>();
				
		//Create Hashmap for pins
		HashMap<String, Pin> inputPinNumbers = new HashMap<String, Pin>();
		HashMap<String, Pin> outputPinNumbers = new HashMap<String, Pin>();
				
		//put pins for sensor and light
		inputPinNumbers.put("SensorInput", RaspiPin.GPIO_05);
		outputPinNumbers.put("SensorOutput", RaspiPin.GPIO_06);
				
		inputPinNumbers.put("LightInput", RaspiPin.GPIO_07);
		outputPinNumbers.put("LightOutput", RaspiPin.GPIO_08);
				
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
				
		Behavior testBehavior = new Behavior("TestBehavior", testConditions, testActions);
		
		assertEquals("Conditions should be size 1", 1, testBehavior.getConditions().size());
		assertEquals("Actions should be size 1", 1, testBehavior.getActions().size());
		
		//----end object creation----
		
		
		//-------Tests-------
		
		assertEquals("Number of rooms should be one", 1 ,BaseTester.getRooms().size());
		
		//add room
		BaseTester.AddRoom(testRoom);
		
		//test to determine if one room has been added
		assertEquals("Number of rooms should be one", 2, BaseTester.getRooms().size());
		
		//test behavior list is zero
		assertEquals("Number of behaviors should be zero", 0, BaseTester.getBehvaiors().size());
		
		//add behavior
		BaseTester.addBehavior(testBehavior);
		
		//test behavior has been added
		assertEquals("Number of Behaviors should be one", 1, BaseTester.getBehvaiors().size());
		
	}

}

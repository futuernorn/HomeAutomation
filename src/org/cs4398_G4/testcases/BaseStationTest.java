package org.cs4398_G4.testcases;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

@RunWith(Parameterized.class)
public class BaseStationTest {
	
	//Create variables for tests
	private BaseStation BaseTester;
	private Controller testController;
	private List<Room> testHouse;
	
	private List<Sensor> testSensors;
	private List<Actuator> testLights;
	
	public BaseStationTest(BaseStation base, Controller control, List<Room> house, List<Sensor> sensors, List<Actuator> lights) {
		this.BaseTester = base;
		this.testController = control;
		this.testHouse = house;
		this.testSensors = sensors;
		this.testLights = lights;
	}
	
	// ------Generates following test data------
	// One Basestation
	// One Controller
	// Empty array of rooms (house)
	// sensor array (With one sensor inside)
	// Light Array (with one light inside)
	
	@Parameters
	public static Collection<Object[]> generateTestData() {
		
		BaseStation base = new BaseStation();
		Controller control = new Controller(base);
		List<Room> house = new ArrayList<Room>();
		List<Sensor> sensors = new ArrayList<Sensor>();
		List<Actuator> lights = new ArrayList<Actuator>();
		
		HashMap<String, Pin> inputPinNumbers = new HashMap<String, Pin>();
		HashMap<String, Pin> outputPinNumbers = new HashMap<String, Pin>();
		
		inputPinNumbers.put("SensorInput", RaspiPin.GPIO_00);
		outputPinNumbers.put("SensorOutput", RaspiPin.GPIO_01);
		
		inputPinNumbers.put("LightInput", RaspiPin.GPIO_03);
		outputPinNumbers.put("LightOutput", RaspiPin.GPIO_04);
		
		Actuator testLight = new Actuator("test light", inputPinNumbers, outputPinNumbers);
		Sensor testSensor = new Sensor("testSensor", inputPinNumbers, outputPinNumbers);
		
		sensors.add(testSensor);
		lights.add(testLight);
		
		
		
		Object[][] data = new Object[][] { 
				{ base, control, house, sensors, lights } 
				};
		
		return Arrays.asList(data);
	}
	
	
	@Test
	public void test() {
		
		assertEquals("Number of rooms should be zero", 0 ,BaseTester.getRooms().size());
		
		Room testRoom = new Room("Test Room");
		BaseTester.AddRoom(testRoom);
		
		List<Condition> testConditions = new ArrayList<Condition>();
		List<Action> testActions = new ArrayList<Action>();
		
		Sensor testSensor = testSensors.get(0);
		Actuator testLight = testLights.get(0);
		
		testController.AddComponent(testSensor, BaseTester.getRooms().get(0));
		//testController.AddComponent(testLight, BaseTester.getRooms().get(0));
		
		//create test actions and conditions for basestation behavior test
		Action testAction = new Action(testLight, PinState.HIGH, 5);
		Condition testCondition = new Condition(testSensor, PinState.HIGH, 10);
		
		testConditions.add(testCondition);
		testActions.add(testAction);
				
		Behavior testBehavior = new Behavior("TestBehavior", testConditions, testActions);
		
		assertEquals("Conditions should be size 1", 1, testBehavior.getConditions().size());
		assertEquals("Actions should be size 1", 1, testBehavior.getActions().size());
		
		//-------Tests-------
		
		assertEquals("Number of rooms should be one", 1 ,BaseTester.getRooms().size());
		
		//test behavior list is zero
		assertEquals("Number of behaviors should be zero", 0, BaseTester.getBehvaiors().size());
		
		//add behavior
		BaseTester.addBehavior(testBehavior);
		
		//test behavior has been added
		assertEquals("Number of Behaviors should be one", 1, BaseTester.getBehvaiors().size());
		
	}

}

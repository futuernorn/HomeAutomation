package org.cs4398_G4.testcases;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.cs4398_G4.Action;
import org.cs4398_G4.Behavior;
import org.cs4398_G4.Condition;
import org.cs4398_G4.Sensor;
import org.junit.Ignore;
import org.junit.Test;

import com.pi4j.io.gpio.GpioPin;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;

public class BehaviorTest {

	@Ignore
	public void testHandleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
		
		GpioPin testEventPin = event.getPin();
		PinState testEventState = event.getState();
		
		List<Condition> testConditions = new ArrayList<Condition>();
		List<Action> testActions = new ArrayList<Action>();
		
		Sensor testSensor = new Sensor("testSensorString", null, null);
		
		Condition testCondition = new Condition(testSensor, testEventState, 1);
		
		Behavior tester = new Behavior("TestBehavior", testConditions, testActions);
		
		//assertEquals("", null, testConditions.getElapsedTime());
	}
	
	@Ignore
	private void Run() {
		fail("Not yet implemented");
	}

}

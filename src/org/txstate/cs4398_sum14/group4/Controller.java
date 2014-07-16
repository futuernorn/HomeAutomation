package org.txstate.cs4398_sum14.group4;

import java.util.Map;
import java.util.Map.Entry;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;

public class Controller {
	BaseStation baseStation;
	// create gpio controller instance
	final GpioController gpio = GpioFactory.getInstance();
	
	public Controller(BaseStation baseStation) {
		this.baseStation = baseStation;
		
	}
	
	public void Shutdown() {
		for (Actuator a : baseStation.actuators) {
			a.TurnOff();
		}
		
	}
	
	public boolean AddComponent(Component newComp) {
		
		for (Entry<String, Pin> entry : newComp.inputPinNumbers.entrySet()) {
		    String key = entry.getKey();
		    Pin pinNumber = entry.getValue();
		    GpioPinDigitalInput inputPin = gpio.provisionDigitalInputPin(pinNumber, key);
		    newComp.inputPins.add(inputPin);
		    
		}
		for (Entry<String, Pin> entry : newComp.outputPinNumbers.entrySet()) {
		    String key = entry.getKey();
		    Pin pinNumber = entry.getValue();
		    GpioPinDigitalOutput outputPin = gpio.provisionDigitalOutputPin(pinNumber, key);
		    newComp.outputPins.add(outputPin);
		    
		}
		
		if (newComp.getClass().equals(Sensor.class)) {
			baseStation.AddSensor((Sensor) newComp);
		} else if (newComp.getClass().equals(Actuator.class)) {
			((Actuator) newComp).Initalize();
			baseStation.AddActuator((Actuator) newComp);
		}

		
		return false;
		
	}
}

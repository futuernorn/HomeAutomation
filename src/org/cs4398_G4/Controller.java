package org.cs4398_G4;

import java.util.Map;
import java.util.Map.Entry;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class Controller {
	BaseStation baseStation;
	// create gpio controller instance
//	final HardwareAbstractionLayer hal = new HardwareAbstractionLayer();
	final GpioController gpio = GpioFactory.getInstance();
	
	public Controller(BaseStation baseStation) {
		this.baseStation = baseStation;
		
	}
	
	public void Shutdown() {
		for (Actuator a : baseStation.GetActuators()) {
			a.TurnOff();
		}
		
	}
	
	public boolean AddComponent(Component newComp, Room newRoom) {
		
		for (Entry<String, Pin> entry : newComp.inputPinNumbers.entrySet()) {
		    String key = entry.getKey();
		    Pin pinNumber = entry.getValue();
		    GpioPinDigitalInput inputPin = gpio.provisionDigitalInputPin(pinNumber, key);
		    inputPin.addListener((GpioPinListenerDigital) newComp);
		    newComp.inputPins.add(inputPin);
		    
		}
		for (Entry<String, Pin> entry : newComp.outputPinNumbers.entrySet()) {
		    String key = entry.getKey();
		    Pin pinNumber = entry.getValue();
		    GpioPinDigitalOutput outputPin = gpio.provisionDigitalOutputPin(pinNumber, key);
		    newComp.outputPins.add(outputPin);
		    
		}
		
		if (newComp.getClass().equals(Sensor.class)) {
			newRoom.AddComponent((Sensor) newComp);
		} else if (newComp.getClass().equals(Actuator.class)) {
			((Actuator) newComp).Initalize();
			newRoom.AddComponent((Actuator) newComp);
		}

		
		return false;
		
	}
}

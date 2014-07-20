package org.cs4398_G4;


import java.lang.reflect.Method;
import java.util.HashMap;

import javax.swing.JTextField;

import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class Sensor extends Component implements GpioPinListenerDigital  {
	int pinNum;
	Method action;
	String display;
	
	public Sensor(HashMap<String, Pin> inputPinNumbers, HashMap<String, Pin> outputPinNumbers, String display) {
		super(inputPinNumbers, outputPinNumbers);
		this.display = display;
		
	}
	
	public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
		System.out.println(" --> GPIO PIN STATE CHANGE: " + event.getPin() + " = " + event.getState());
//		display.setText(" --> GPIO PIN STATE CHANGE: " + event.getPin() + " = " + event.getState());
	}

}

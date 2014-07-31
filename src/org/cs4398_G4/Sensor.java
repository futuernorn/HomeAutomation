package org.cs4398_G4;


import java.lang.reflect.Method;
import java.util.HashMap;

import javax.swing.JTextField;

import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

/**
 * This class is for storing components that require only sensors. Read only components. 
 *
 */
public class Sensor extends Component  {
	int pinNum;
	Method action;
//	String display;
	
	public Sensor(String name, HashMap<String, Pin> inputPinNumbers, HashMap<String, Pin> outputPinNumbers) {
		super(name, inputPinNumbers, outputPinNumbers);
//		this.display = display;
		
	}
	
//	public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
//		System.out.println(" --> GPIO PIN STATE CHANGE: " + event.getPin() + " = " + event.getState());
////		display.setText(" --> GPIO PIN STATE CHANGE: " + event.getPin() + " = " + event.getState());
//	}
	
	

}

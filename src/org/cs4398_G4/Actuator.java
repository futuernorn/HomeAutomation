package org.cs4398_G4;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;
import java.util.HashMap;

import javax.swing.JTextField;

import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;

public class Actuator extends Component implements ActionListener   {
	int pinNum;
	Method action;
	JTextField display;
	boolean isOn;
	private int numClicks;
	
	public Actuator(String name, HashMap<String, Pin> inputPinNumbers, HashMap<String, Pin> outputPinNumbers) {
		super(name, inputPinNumbers, outputPinNumbers);

		
	}
	
	public void Initalize() {
		isOn = false;
//		for (GpioPinDigitalOutput outputPin : getOutputPins()) {
//			outputPin.low();
//		}
		getOutputPins().low();
	}
	
	public void actionPerformed(ActionEvent e) {
        numClicks++;
        
        if (isOn) {
        	getOutputPins().setState(PinState.LOW);
        	getOutputPins().low();
        	System.out.println("Setting " + getOutputPins().getName() + " to " + PinState.LOW);
        	isOn = false;
        } else {
        	getOutputPins().setState(PinState.HIGH);
        	System.out.println("Setting " + getOutputPins().getName() + " to " + PinState.HIGH);
        	isOn = true;
        }
        
	}

	public void run() {
		
		
	}
	public void TurnOff() {
		// TODO Auto-generated method stub
//		for (GpioPinDigitalOutput outputPin : getOutputPins()) {
//			outputPin.low();
//		}
		getOutputPins().low();
	}
	
	public void TurnOn() {
//		for (GpioPinDigitalOutput outputPin : getOutputPins()) {
//			outputPin.high();
//		}
		getOutputPins().high();
		
	}

}

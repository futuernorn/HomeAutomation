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
        	
        	try {
            	TurnOff();
        	} catch(IncorrectPinStateException e1) {
        		System.out.println("Pin should be " + e1.CorrectPinState());
        	}
        	
        	System.out.println("Setting " + getOutputPins().getName() + " to " + PinState.LOW);
        	isOn = false;
        } else {
        	
        	try {
            	TurnOn();
        	} catch(IncorrectPinStateException e2) {
        		System.out.println("Pin should be " + e2.CorrectPinState());
        	}
        	
        	System.out.println("Setting " + getOutputPins().getName() + " to " + PinState.HIGH);
        	isOn = true;
        }
        
	}
	
	public boolean GetIsOn() {
		return isOn;
	}

	public void run() {
		
		
	}
	
	public void TurnOff() throws IncorrectPinStateException {
		// TODO Auto-generated method stub
//		for (GpioPinDigitalOutput outputPin : getOutputPins()) {
//			outputPin.low();
//		}
		
		getOutputPins().low();
		
		//Exception if state isnt being set to low
    	if(getOutputPins().getState() == PinState.HIGH)
    		throw new IncorrectPinStateException(PinState.LOW);
	}
	
	public void TurnOn() throws IncorrectPinStateException {
//		for (GpioPinDigitalOutput outputPin : getOutputPins()) {
//			outputPin.high();
//		}
		
		getOutputPins().high();
		
		//Exception if state isn't being set to high
    	if(getOutputPins().getState() == PinState.LOW)
    		throw new IncorrectPinStateException(PinState.HIGH);
		
	}

}

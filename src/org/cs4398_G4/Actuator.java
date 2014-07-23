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
		for (GpioPinDigitalOutput outputPin : outputPins) {
			outputPin.low();
		}
	}
	
	public void actionPerformed(ActionEvent e) {
        numClicks++;
        
        if (isOn) {
        	outputPins.get(0).setState(PinState.LOW);
        	outputPins.get(0).low();
        	System.out.println("Setting " + outputPins.get(0).getName() + " to " + PinState.LOW);
        	isOn = false;
        } else {
        	outputPins.get(0).setState(PinState.HIGH);
        	System.out.println("Setting " + outputPins.get(0).getName() + " to " + PinState.HIGH);
        	isOn = true;
        }
        
	}

	public void run() {
		
		
	}
	public void TurnOff() {
		// TODO Auto-generated method stub
		for (GpioPinDigitalOutput outputPin : outputPins) {
			outputPin.low();
		}
	}
	
	public void TurnOn() {
		for (GpioPinDigitalOutput outputPin : outputPins) {
			outputPin.high();
		}
	}

}

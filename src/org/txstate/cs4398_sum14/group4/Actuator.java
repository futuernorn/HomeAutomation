package org.txstate.cs4398_sum14.group4;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;
import java.util.HashMap;

import javax.swing.JTextField;

import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;

public class Actuator extends Component implements ActionListener   {
	int pinNum;
	Method action;
	JTextField display;
	boolean isOn;
	private int numClicks;
	
	public Actuator(HashMap<String, Pin> inputPinNumbers, HashMap<String, Pin> outputPinNumbers) {
		super(inputPinNumbers, outputPinNumbers);

		
	}
	
	public void Initalize() {
		isOn = false;
		for (GpioPinDigitalOutput outputPin : outputPins) {
			outputPin.low();
		}
	}
	
	public void actionPerformed(ActionEvent e) {
        numClicks++;
        System.out.println("Button Clicked " + numClicks + " times");
        if (isOn) {
        	outputPins.get(0).low();
        	isOn = false;
        } else {
        	outputPins.get(0).high();
        	isOn = true;
        }
        
	}

	public void TurnOff() {
		// TODO Auto-generated method stub
		for (GpioPinDigitalOutput outputPin : outputPins) {
			outputPin.low();
		}
	}

}

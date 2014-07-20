package org.txstate.cs4398_sum14.group4;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;
import java.util.HashMap;

import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;

import javax.swing.JTextField;

public class Actuator extends Component implements ActionListener   {
	int pinNum;
	Method action;
	JTextField display;
	private int numClicks;
	
	public Actuator(HashMap<String, Pin> inputPinNumbers, HashMap<String, Pin> outputPinNumbers) {
		super(inputPinNumbers, outputPinNumbers);

		
	}
	
	public void actionPerformed(ActionEvent e) {
        numClicks++;
        System.out.println("Button Clicked " + numClicks + " times");
	}

}

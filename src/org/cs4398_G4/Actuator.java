package org.cs4398_G4;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;
import java.util.HashMap;

import javax.swing.JTextField;

import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;

/**
 * @author Kevin
 *
 */
public class Actuator extends Component implements ActionListener   {
	int pinNum;
	Method action;
	JTextField display;
	boolean isOn;
	private int numClicks;
	
	/**
	 * @param name: Name of component
	 * @param inputPinNumbers: Raspberry Pi pins used for input of component.
	 * @param outputPinNumbers: Raspberry Pi pins used for output of component. 
	 */
	public Actuator(String name, HashMap<String, Pin> inputPinNumbers, HashMap<String, Pin> outputPinNumbers) {
		super(name, inputPinNumbers, outputPinNumbers);

		
	}
	
	/**
	 * Initializes a component to the off state, sets the output pins to low or "off"
	 */
	public void Initalize() {
		isOn = false;
//		for (GpioPinDigitalOutput outputPin : getOutputPins()) {
//			outputPin.low();
//		}
		getOutputPins().low();
	}
	
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 * Toggles the component from on to off or vice versa depending on current state of component.
	 */
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

	/**
	 * 
	 */
	public void run() {
		
		
	}
	/**
	 * Turns off component by setting output pins to low or "off"
	 */
	public void TurnOff() {
		// TODO Auto-generated method stub
//		for (GpioPinDigitalOutput outputPin : getOutputPins()) {
//			outputPin.low();
//		}
		getOutputPins().low();
	}
	
	/**
	 * Turns on component by setting output pins to high or "on"
	 */
	public void TurnOn() {
//		for (GpioPinDigitalOutput outputPin : getOutputPins()) {
//			outputPin.high();
//		}
		getOutputPins().high();
		
	}

}

package org.cs4398g4;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;
import java.util.HashMap;

import javax.swing.JTextField;

import org.cs4398g4.User.AccessLevel;

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
	BaseStation baseStation;

	private int numClicks;
	
	/**
	 * @param name: Name of component
	 * @param inputPinNumbers: Raspberry Pi pins used for input of component.
	 * @param outputPinNumbers: Raspberry Pi pins used for output of component. 
	 */
	public Actuator(String name, HashMap<String, Pin> inputPinNumbers, HashMap<String, Pin> outputPinNumbers) {
		super(name, inputPinNumbers, outputPinNumbers);	
		baseStation = null;
	}
	
	/**
	 * Initializes a component to the off state, sets the output pins to low or "off"
	 */
	public void initalize(BaseStation baseStation) {
		this.baseStation = baseStation;
		getOutputPin().low();
		
	}
	
	



	/**
	 * Turns off component by setting output pins to low or "off"
	 */
	public boolean turnOff() throws IncorrectPinStateException {
		if (baseStation == null)
			return false;
		if (baseStation.checkUserAccess(AccessLevel.USER)) {
			baseStation.getLog().addLog(this + " turned off.");
			
			getOutputPin().low();
			
			if(getOutputPin().getState() == PinState.HIGH)
	    		throw new IncorrectPinStateException(PinState.LOW);
			
			return true;
		}
		baseStation.getLog().addLog(this + " unable to be turned off.");
		return false;
	}
	
	/**
	 * Turns on component by setting output pins to high or "on"
	 */
	public boolean turnOn() throws IncorrectPinStateException {
		if (baseStation == null)
			return false;
		if (baseStation.checkUserAccess(AccessLevel.USER)) {
			baseStation.getLog().addLog(this + " turned on.");
			
			getOutputPin().high();		
			
			if(getOutputPin().getState() == PinState.LOW)
	    		throw new IncorrectPinStateException(PinState.HIGH);
			
			return true;
		}
		baseStation.getLog().addLog(this + " unable to be turned on.");
		return false;
		
	}

	public boolean toggle() {   
		if (baseStation == null)
			return false;
		if (baseStation.checkUserAccess(AccessLevel.USER)) {
			
			getOutputPin().toggle();	
			String currentState = getOutputPinState() ? "off" : "on";
			baseStation.getLog().addLog(this + " toggled to: " + currentState);
			return true;
		}
		String currentState = getOutputPinState() ? "off" : "on";
		baseStation.getLog().addLog(this + " unable to be toggled, still set to: " + currentState);
		return false;
		
		
//        if (isOn) {
//        	getOutputPin().setState(PinState.LOW);
//        	isOn = false;
//        } else {
//        	getOutputPin().setState(PinState.HIGH);
//        	isOn = true;
//        }
		
	}



	public int getNumClicks() {
		return numClicks;
	}

	
	public void setBaseStation(BaseStation baseStation) {
		this.baseStation = baseStation;
	}


	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 * Toggles the component from on to off or vice versa depending on current state of component.
	 */
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}

package org.cs4398_G4;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;

import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;

public abstract class Component {
	HashMap<String, Pin> inputPinNumbers;
	/**
	 * @return: Raspberry Pi pins used for input
	 */
	public HashMap<String, Pin> getInputPinNumbers() {
		return inputPinNumbers;
	}

	/**
	 * Sets the Raspberry Pi input pins numbers for a component.
	 * @param inputPinNumbers: Raspberry Pi pins used for component input
	 */
	public void setInputPinNumbers(HashMap<String, Pin> inputPinNumbers) {
		this.inputPinNumbers = inputPinNumbers;
	}

	HashMap<String, Pin> outputPinNumbers;
	private GpioPinDigitalInput inputPins;
	private GpioPinDigitalOutput outputPins;
	String name;
	Boolean enabled;
	Room room;
	
	/**
	 * Component constructor
	 * @param name: Name of component
	 * @param inputPinNumbers: Raspberry Pi pins used for input
	 * @param outputPinNumbers: Raspberry Pi pins used for output
	 */
	public Component (String name, HashMap<String, Pin> inputPinNumbers, HashMap<String, Pin> outputPinNumbers) {
		this.name = name;
		this.inputPinNumbers = inputPinNumbers;
		this.outputPinNumbers = outputPinNumbers;
//		inputPins = new ArrayList<GpioPinDigitalInput>();
//		outputPins = new ArrayList<GpioPinDigitalOutput>();
		
	}
	
	public boolean GetStatus() {
		return false;
		
	}
	
	public String toString() {
		return name;
	}

	public GpioPinDigitalInput getInputPins() {
		return inputPins;
	}

	public void setInputPins(GpioPinDigitalInput inputPins) {
		this.inputPins = inputPins;
	}

	public GpioPinDigitalOutput getOutputPins() {
		return outputPins;
	}

	public void setOutputPins(GpioPinDigitalOutput outputPins) {
		this.outputPins = outputPins;
	}
	
//	public ArrayList
	
}

package org.cs4398_G4;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JPanel;

import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

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
	ComponentPanel componentUI;
	
	public ComponentPanel getComponentUI() {
		return componentUI;
	}

	public void setComponentUI(ComponentPanel componentUI) {
		this.componentUI = componentUI;
//		if (inputPins != null)
//			componentUI.setStateText(inputPins.getState().toString());
		
	}

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
		inputPins = null;
//		inputPins = new ArrayList<GpioPinDigitalInput>();
//		outputPins = new ArrayList<GpioPinDigitalOutput>();
		
	}
	
	public boolean GetStatus() {
		return false;
		
	}
	
	public String toString() {
		String pinNames = "";
		for (Entry<String, Pin> entry : outputPinNumbers.entrySet()) {
			if (pinNames != "")
				pinNames += " - ";
					
			pinNames += entry.getKey();
		}
		
		for (Entry<String, Pin> entry : inputPinNumbers.entrySet()) {
			if (pinNames != "")
				pinNames += " - ";
					
			pinNames += entry.getKey();
		}
		return name + " :: " + pinNames;
//		return pinNames;
	}

	public GpioPinDigitalInput getInputPins() {
		return inputPins;
	}

	public void setInputPins(GpioPinDigitalInput inputPins) {
		this.inputPins = inputPins;
		inputPins.addListener(this.componentUI);
	}

	public GpioPinDigitalOutput getOutputPins() {
		return outputPins;
	}

	public void setOutputPins(GpioPinDigitalOutput outputPins) {
		this.outputPins = outputPins;
	}
	
//	public ArrayList
	
}

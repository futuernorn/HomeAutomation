package org.cs4398g4;

import java.util.HashMap;
import java.util.Map.Entry;

import org.cs4398g4.ui.ComponentPanel;

import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;

public abstract class Component {
	HashMap<String, Pin> inputPinNumbers;



	HashMap<String, Pin> outputPinNumbers;
	private GpioPinDigitalInput inputPin;
	private GpioPinDigitalOutput outputPin;
	String name;
	Boolean enabled;
	Room room;
	ComponentPanel componentUI;

	public ComponentPanel getComponentUI() {
		return componentUI;
	}

	public void setComponentUI(ComponentPanel componentUI) {
		this.componentUI = componentUI;
	}

	/**
	 * Component constructor
	 * 
	 * @param name
	 *            : Name of component
	 * @param inputPinNumbers
	 *            : Raspberry Pi pins used for input
	 * @param outputPinNumbers
	 *            : Raspberry Pi pins used for output
	 */
	public Component(String name, HashMap<String, Pin> inputPinNumbers,
			HashMap<String, Pin> outputPinNumbers) {
		this.name = name;
		this.inputPinNumbers = inputPinNumbers;
		this.outputPinNumbers = outputPinNumbers;
		inputPin = null;
		outputPin = null;

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
		// return name + " :: " + pinNames;
		return pinNames;
	}

	public GpioPinDigitalInput getInputPin() {
		return inputPin;
	}

	public void setInputPins(GpioPinDigitalInput inputPins) {
		this.inputPin = inputPins;
		inputPins.addListener(this.componentUI);
	}

	public GpioPinDigitalOutput getOutputPin() {
		return outputPin;
	}

	public void setOutputPin(GpioPinDigitalOutput outputPins) {
		this.outputPin = outputPins;
	}

	public boolean getOutputPinState() {
		return (outputPin.getState() == PinState.HIGH) ? true : false;
	}

	/**
	 * @return: Raspberry Pi pins used for input
	 */
	public HashMap<String, Pin> getInputPinNumbers() {
		return inputPinNumbers;
	}

	/**
	 * Sets the Raspberry Pi input pins numbers for a component.
	 * 
	 * @param inputPinNumbers
	 *            : Raspberry Pi pins used for component input
	 */
	public void setInputPinNumbers(HashMap<String, Pin> inputPinNumbers) {
		this.inputPinNumbers = inputPinNumbers;
	}

}

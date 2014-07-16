package org.txstate.cs4398_sum14.group4;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;

import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;

public abstract class Component {
	HashMap<String, Pin> inputPinNumbers;
	HashMap<String, Pin> outputPinNumbers;
	ArrayList<GpioPinDigitalInput> inputPins;
	ArrayList<GpioPinDigitalOutput> outputPins;
	String name;
	Boolean enabled;
	Room room;
	
	public Component (HashMap<String, Pin> inputPinNumbers, HashMap<String, Pin> outputPinNumbers) {
		this.inputPinNumbers = inputPinNumbers;
		this.outputPinNumbers = outputPinNumbers;
		inputPins = new ArrayList<GpioPinDigitalInput>();
		outputPins = new ArrayList<GpioPinDigitalOutput>();
		
	}
	
}

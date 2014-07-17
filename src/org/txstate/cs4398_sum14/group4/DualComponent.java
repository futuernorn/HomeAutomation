package org.txstate.cs4398_sum14.group4;

import java.util.HashMap;

import com.pi4j.io.gpio.Pin;

public class DualComponent extends Actuator {

	Sensor sensor;
	public DualComponent(HashMap<String, Pin> inputPinNumbers,
			HashMap<String, Pin> outputPinNumbers) {
		super(new HashMap<String,Pin>(), outputPinNumbers);
		// TODO Auto-generated constructor stub
		sensor = new Sensor(inputPinNumbers, new HashMap<String,Pin>(), "Temp Sensor Text Field");
	}


}

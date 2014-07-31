package org.cs4398_G4;

import java.util.HashMap;

import com.pi4j.io.gpio.Pin;

/**
 * @author Jeffrey Hogan
 *This class handles components the require both input and output pins for functionality
 */
public class DualComponent extends Actuator {

	Sensor sensor;
	public DualComponent(String name, HashMap<String, Pin> inputPinNumbers,
			HashMap<String, Pin> outputPinNumbers) {
		super(name, new HashMap<String,Pin>(), outputPinNumbers);
		// TODO Auto-generated constructor stub
		sensor = new Sensor(name, inputPinNumbers, new HashMap<String,Pin>());
	}


}

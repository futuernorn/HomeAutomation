package org.cs4398g4;

import java.lang.reflect.Method;
import java.util.HashMap;

import com.pi4j.io.gpio.Pin;

public class Sensor extends Component {
	int pinNum;
	Method action;

	// String display;

	public Sensor(String name, HashMap<String, Pin> inputPinNumbers,
			HashMap<String, Pin> outputPinNumbers) {
		super(name, inputPinNumbers, outputPinNumbers);
		// this.setComponentUI(new SensorPanel(this));

		// this.display = display;

	}

	// public void
	// handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent
	// event) {
	// System.out.println(" --> GPIO PIN STATE CHANGE: " + event.getPin() +
	// " = " + event.getState());
	// // display.setText(" --> GPIO PIN STATE CHANGE: " + event.getPin() +
	// " = " + event.getState());
	// }

}

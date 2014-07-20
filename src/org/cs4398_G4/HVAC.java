package org.cs4398_G4;

import java.util.Collections;
import java.util.HashMap;

import com.pi4j.io.gpio.Pin;

public class HVAC extends DualComponent {
	public HVAC(HashMap<String, Pin> inputPinNumbers,
			HashMap<String, Pin> outputPinNumbers) {
		super(inputPinNumbers, outputPinNumbers);
		// TODO Auto-generated constructor stub
	}


	

	
	public void RunUntilTemperatureReached (Float goalTemp) {
		
	}
	
	public void RunHeaterOnTimer (Integer minutesToRun) {
		
	}

}

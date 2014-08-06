package org.cs4398g4;

import com.pi4j.io.gpio.PinState;

public class IncorrectPinStateException extends Exception{
	private PinState state;
	
	public IncorrectPinStateException(PinState P_state) {
		state = P_state;
	}
	public String CorrectPinState() {
		if(state == PinState.HIGH)
			return "high";
		else
			return "low";
	}
}

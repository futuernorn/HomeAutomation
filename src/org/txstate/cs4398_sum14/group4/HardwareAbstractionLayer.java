package org.txstate.cs4398_sum14.group4;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;

public class HardwareAbstractionLayer {
//	final String HARDWARE_TYPE = "pi";
	final GpioController gipo = GpioFactory.getInstance();
	public HardwareAbstractionLayer() {
//		if (HARDWARE_TYPE == "pi") {
			
//		}
		
	}
}

package org.cs4398g4;

import java.util.HashMap;

import com.pi4j.io.gpio.Pin;

public class Lock extends DualComponent{

	
	
	public Lock(HashMap<String, Pin> inputPinNumbers,
			HashMap<String, Pin> outputPinNumbers) {
		super("LOCK", inputPinNumbers, outputPinNumbers);
		// TODO Auto-generated constructor stub
	}

	public boolean LockDoor() throws DoorNotClosedException {
		boolean doorLockSensor = true;
		
		if (!doorLockSensor)
			throw new DoorNotClosedException("not closed");
		return doorLockSensor;
		
	}
}

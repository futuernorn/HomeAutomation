package org.txstate.cs4398_sum14.group4;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public abstract class Sensor implements GpioPinListenerDigital  {
	int pinNum;
	Method action;
	final BaseStation baseStation;
	public Sensor(int pinNum, Method action, BaseStation baseStation) {
		this.pinNum = pinNum;
		this.action = action;
		this.baseStation = baseStation;
		
	}
	
	public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
		System.out.println(" --> GPIO PIN STATE CHANGE: " + event.getPin() + " = " + event.getState());
		try {
			action.invoke(baseStation, event);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

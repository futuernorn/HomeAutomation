
 package org.txstate.cs4398_sum14.group4;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;


/**
 * This class is the main entry point for the Home Automation System (HAS).
 * @author Jeffrey Hogan
 * 
 */
public class Driver {

	public static void main(String[] args) {
		BaseStation baseStation = new BaseStation();
		final Controller controller = new Controller(baseStation);
		
		
		// This is all simulation-type stuff
		// TODO: replace with actual interfaces
		

//		System.out.println(baseStation.GetStatus());

		//UserInterface ui = new UserInterface();

			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						LocalInterface window = new LocalInterface();
						// for instance, this assumes someone has added a compomnent and supplied the needed information
						HashMap<String, Pin> inputPinNumbers = new HashMap<String, Pin>();
						inputPinNumbers.put("Motion Detector (PIR)", RaspiPin.GPIO_00);
						HashMap<String, Pin> outputPinNumbers = new HashMap<String, Pin>();
						Sensor newMotionSensor = new Sensor(inputPinNumbers, outputPinNumbers, window.motionTxt);
						controller.AddComponent(newMotionSensor);
						
						HashMap<String, Pin> inputPinNumbers1 = new HashMap<String, Pin>();
						
						HashMap<String, Pin> outputPinNumbers1 = new HashMap<String, Pin>();
						outputPinNumbers1.put("LED TestLight", RaspiPin.GPIO_01);
						Actuator newLedActuator = new Actuator(inputPinNumbers1, outputPinNumbers1);
						controller.AddComponent(newLedActuator);
						newLedActuator.outputPins.get(0).low();
//						newLedActuator.outputPins.get(0).pulse(1000);
						window.btnToggleLed.addActionListener(newLedActuator);
						
						window.ShowInterface();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});

	}

}

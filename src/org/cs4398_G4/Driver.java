
 package org.cs4398_G4;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;


/**
 * This class is the main entry point for the Home Automation System (HAS).
 * @author Jeffrey Hogan
 * 
 */
public class Driver {

	public static void main(String[] args) {
		final BaseStation baseStation = new BaseStation();
		
		final Controller controller = new Controller(baseStation );
		
		
//		baseStation.getUser().
		// This is all simulation-type stuff
		// TODO: replace with actual interfaces
		


		//UserInterface ui = new UserInterface();

			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						
//						controller.setView(window);
						// for instance, this assumes someone has added a compomnent and supplied the needed information
						Room defaultRoom = new Room("Living Room");
						Room bedroom = new Room("Bedroom");
						Room bathroom = new Room("Bathroom");
						Room diningRoom = new Room("DiningRoom");
						baseStation.AddRoom(defaultRoom);
						baseStation.AddRoom(bedroom);
						baseStation.AddRoom(bathroom);
						baseStation.AddRoom(diningRoom);
						
						HashMap<String, Pin> inputPinNumbers = new HashMap<String, Pin>();
						inputPinNumbers.put("Reed Switch Demo", RaspiPin.GPIO_06);
						HashMap<String, Pin> outputPinNumbers = new HashMap<String, Pin>();
						Sensor reedSwitchSensor = new Sensor("Reed Switch", inputPinNumbers, outputPinNumbers);
						controller.AddComponent(reedSwitchSensor, defaultRoom);
						
						inputPinNumbers = new HashMap<String, Pin>();
						inputPinNumbers.put("Motion Sensor Demo", RaspiPin.GPIO_00);
						outputPinNumbers = new HashMap<String, Pin>();
						Sensor newMotionSensor = new Sensor("Motion Sensor", inputPinNumbers, outputPinNumbers);
						controller.AddComponent(newMotionSensor, defaultRoom);
//						
						HashMap<String, Pin> inputPinNumbers1 = new HashMap<String, Pin>();						
						HashMap<String, Pin> outputPinNumbers1 = new HashMap<String, Pin>();
						outputPinNumbers1.put("LED TestLight", RaspiPin.GPIO_04);
						Actuator newLedActuator = new Actuator("LED", inputPinNumbers1, outputPinNumbers1);						
						controller.AddComponent(newLedActuator, defaultRoom);
						
						inputPinNumbers1 = new HashMap<String, Pin>();						
						outputPinNumbers1 = new HashMap<String, Pin>();
						outputPinNumbers1.put("Buzzer", RaspiPin.GPIO_07);
						Actuator buzzer = new Actuator("Buzzer", inputPinNumbers1, outputPinNumbers1);						
						controller.AddComponent(buzzer, defaultRoom);
						
						
						
						List<Action> actions = new ArrayList<Action>();
						List<Condition> conditions = new ArrayList<Condition>();
						
						Condition condition = new Condition(reedSwitchSensor, PinState.LOW, 1);
						Action action = new Action(buzzer, PinState.HIGH, 5);
						actions.add(action);
						conditions.add(condition);
						Behavior behavior = new Behavior("Fridge Alarm",conditions, actions,baseStation);
						controller.addBehavior(behavior);
						
						
						actions = new ArrayList<Action>();
						conditions = new ArrayList<Condition>();
						
						condition = new Condition(newMotionSensor, PinState.HIGH, 1);
						action = new Action(newLedActuator, PinState.HIGH, 1);
						actions.add(action);
						conditions.add(condition);
						behavior = new Behavior("Motion Light",conditions, actions,baseStation);
						controller.addBehavior(behavior);
						
						
						
						LocalInterface window = new LocalInterface(controller, baseStation);
						baseStation.setView(window);
//						window.btnToggleLed.addActionListener(newLedActuator);
//						window.refresh();
//						window.addActionListener(baseStation);
//						baseStation.addActionListener(window);
						window.ShowInterface();
//						window.refreshComponents(controller.getComponents());
						
						
						
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			
	}
	
	public void LoadDemoData() {
		
	}

}

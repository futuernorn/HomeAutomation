package org.cs4398g4;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.cs4398g4.ui.LocalInterface;

import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

/**
 * This class is the main entry point for the Home Automation System (HAS).
 * 
 * @author Jeffrey Hogan
 * 
 */
public class Driver {

	public static void main(String[] args) {
		final DriverDemoData demoData = new DriverDemoData();

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					demoData.LoadDemoData();

					LocalInterface window = new LocalInterface(demoData
							.getController(), demoData.getBaseStation());
					demoData.getBaseStation().setView(window);
					window.ShowInterface();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}

class DriverDemoData {

	private BaseStation baseStation;
	private Controller controller;

	public DriverDemoData() {
		baseStation = new BaseStation();
		controller = new Controller(baseStation);
	}

	public void LoadDemoData() {
		loadDemoComponents();
		loadDemoUsers();
	}

	private void loadDemoComponents() {
		// controller.setView(window);
		// for instance, this assumes someone has added a compomnent and
		// supplied the needed information

		// temporarily log in to add in demo settings automatically
		String demoUsername = "DEMO";
		String demoPassword = "demo123";
		User currentUser = new User(demoUsername, demoPassword,
				User.AccessLevel.ADMIN);
		baseStation.addUser(currentUser);
		baseStation.doLogin(demoUsername, demoPassword);

		Room defaultRoom = new Room("Kitchen");
		Room bedroom = new Room("Bedroom");
		Room bathroom = new Room("Bathroom");
		Room diningRoom = new Room("DiningRoom");
		baseStation.addRoom(defaultRoom);
		baseStation.addRoom(bedroom);
		baseStation.addRoom(bathroom);
		baseStation.addRoom(diningRoom);

		HashMap<String, Pin> inputPinNumbers = new HashMap<String, Pin>();
		inputPinNumbers.put("Reed Switch Demo", RaspiPin.GPIO_06);
		HashMap<String, Pin> outputPinNumbers = new HashMap<String, Pin>();
		Sensor reedSwitchSensor = new Sensor("Reed Switch", inputPinNumbers,
				outputPinNumbers);
		controller.addComponent(reedSwitchSensor, defaultRoom);

		inputPinNumbers = new HashMap<String, Pin>();
		inputPinNumbers.put("Motion Sensor Demo", RaspiPin.GPIO_00);
		outputPinNumbers = new HashMap<String, Pin>();
		Sensor newMotionSensor = new Sensor("Motion Sensor", inputPinNumbers,
				outputPinNumbers);
		controller.addComponent(newMotionSensor, diningRoom);
		//
		HashMap<String, Pin> inputPinNumbers1 = new HashMap<String, Pin>();
		HashMap<String, Pin> outputPinNumbers1 = new HashMap<String, Pin>();
		outputPinNumbers1.put("LED TestLight", RaspiPin.GPIO_04);
		Actuator newLedActuator = new Actuator("LED", inputPinNumbers1,
				outputPinNumbers1);
		controller.addComponent(newLedActuator, bedroom);

		inputPinNumbers1 = new HashMap<String, Pin>();
		outputPinNumbers1 = new HashMap<String, Pin>();
		outputPinNumbers1.put("Buzzer", RaspiPin.GPIO_07);
		Actuator buzzer = new Actuator("Buzzer", inputPinNumbers1,
				outputPinNumbers1);
		controller.addComponent(buzzer, defaultRoom);

		List<Action> actions = new ArrayList<Action>();
		List<Condition> conditions = new ArrayList<Condition>();

		Condition condition = new Condition(reedSwitchSensor, PinState.LOW, 1);
		Action action = new Action(buzzer, PinState.HIGH, 5);
		actions.add(action);
		conditions.add(condition);
		Behavior behavior = new Behavior("Fridge Alarm", conditions, actions,
				baseStation);
		controller.addBehavior(behavior);

		actions = new ArrayList<Action>();
		conditions = new ArrayList<Condition>();

		condition = new Condition(newMotionSensor, PinState.HIGH, 1);
		action = new Action(newLedActuator, PinState.HIGH, 1);
		actions.add(action);
		conditions.add(condition);
		behavior = new Behavior("Motion Light", conditions, actions,
				baseStation);
		controller.addBehavior(behavior);

		baseStation.removeUser(currentUser);
		baseStation.doLogout();

	}

	private void loadDemoUsers() {
		User currentUser = new User("Jeffrey", "626755", User.AccessLevel.ADMIN);
		User user1 = new User("Ryan", "default", User.AccessLevel.ADMIN);
		User user2 = new User("Test", "1234", User.AccessLevel.USER);

		baseStation.addUser(currentUser);
		baseStation.addUser(user1);
		baseStation.addUser(user2);

	}

	public BaseStation getBaseStation() {
		return baseStation;
	}

	public Controller getController() {
		return controller;
	}
}

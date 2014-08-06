package org.cs4398g4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;

import org.cs4398g4.User.AccessLevel;
import org.cs4398g4.ui.ActuatorPanel;
import org.cs4398g4.ui.ComponentPanel;
import org.cs4398g4.ui.LocalInterface;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPin;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.RaspiPin;

public class Controller {
	BaseStation baseStation;
	LocalInterface view;

	public LocalInterface getView() {
		return view;
	}

	public void setView(LocalInterface view) {
		this.view = view;
	}

	// create gpio controller instance
	// final HardwareAbstractionLayer hal = new HardwareAbstractionLayer();
	final GpioController gpio = GpioFactory.getInstance();

	public Controller(BaseStation baseStation) {
		this.baseStation = baseStation;

	}

	public Collection<Pin> getAllPossiblePins() {
		Collection<Pin> possiblePins = new ArrayList<Pin>();
		possiblePins.add(RaspiPin.GPIO_00);
		possiblePins.add(RaspiPin.GPIO_02);
		possiblePins.add(RaspiPin.GPIO_01);
		possiblePins.add(RaspiPin.GPIO_03);
		possiblePins.add(RaspiPin.GPIO_04);
		possiblePins.add(RaspiPin.GPIO_05);
		possiblePins.add(RaspiPin.GPIO_06);
		possiblePins.add(RaspiPin.GPIO_07);
		return possiblePins;

	}

	public Collection<Pin> getAvailablePins() {
		Collection<GpioPin> provisionedPins = gpio.getProvisionedPins();
		Collection<Pin> possiblePins = getAllPossiblePins();
		for (GpioPin provisionedPin : provisionedPins) {
			provisionedPin.getPin().getAddress();
			possiblePins.remove(provisionedPin.getPin());
		}
		return possiblePins;
	}

	public void Shutdown() {
		for (Actuator a : baseStation.getComponents(Actuator.class)) {
			a.turnOff();
		}

	}

	public boolean addComponent(Component newComp, Room newRoom) {
		if (baseStation.checkUserAccess(AccessLevel.ADMIN)) {
			baseStation.getLog().addLog("Component added to " + newRoom + ": " + newComp);

			for (Entry<String, Pin> entry : newComp.inputPinNumbers.entrySet()) {
				String key = entry.getKey();
				Pin pinNumber = entry.getValue();
				GpioPinDigitalInput inputPin = gpio.provisionDigitalInputPin(pinNumber, key,
						PinPullResistance.PULL_DOWN);

				newComp.setInputPins(inputPin);

			}
			for (Entry<String, Pin> entry : newComp.outputPinNumbers.entrySet()) {
				String key = entry.getKey();
				Pin pinNumber = entry.getValue();

				GpioPinDigitalOutput outputPin = gpio.provisionDigitalOutputPin(pinNumber, key);
				// outputPin.addListener((GpioPinListenerDigital) newComp);
				newComp.setOutputPin(outputPin);

			}

			if (newComp.getClass().equals(Sensor.class)) {
				newComp.setComponentUI(new ComponentPanel(newComp, baseStation));
				newRoom.AddComponent((Sensor) newComp);
			} else if (newComp.getClass().equals(Actuator.class)) {

				newComp.setComponentUI(new ActuatorPanel(newComp, baseStation));
				((Actuator) newComp).initalize(baseStation);
				newRoom.AddComponent((Actuator) newComp);
			}
			return true;
		}
		baseStation.getLog().addLog(
				"Unable to add the following component to " + newRoom + " due to insufficent permissions: " + newComp);

		return false;

	}

	public boolean removeComponent(Component removedComponent) {
		if (baseStation.checkUserAccess(AccessLevel.ADMIN)) {
			baseStation.getLog().addLog("Component removed: " + removedComponent);
			if (removedComponent.getInputPin() != null)
				gpio.unprovisionPin(removedComponent.getInputPin());

			if (removedComponent.getOutputPin() != null)
				gpio.unprovisionPin(removedComponent.getOutputPin());
			baseStation.removeComponent(removedComponent);
			return true;
		}
		baseStation.getLog().addLog(
				"Unable to remove the following component due to insufficent permissions: " + removedComponent);
		return false;
	}

	static public String pinState(Integer state) {
		String stateName = "High";
		if (state == 0)
			stateName = "Low";
		return stateName;
	}

	public boolean addBehavior(Behavior behavior) {
		if (baseStation.checkUserAccess(AccessLevel.ADMIN)) {
			baseStation.getLog().addLog("New behavior added: " + behavior);
			baseStation.addBehavior(behavior);
			return true;
		}
		baseStation.getLog().addLog("Unable to add the following behavior due to insufficent permissions: " + behavior);
		return false;

	}

	public boolean removeBehavior(Behavior removedBehavior) {
		if (baseStation.checkUserAccess(AccessLevel.ADMIN)) {
			baseStation.getLog().addLog("Behavior removed: " + removedBehavior);
			baseStation.removeBehavior(removedBehavior);
			return true;
		}
		baseStation.getLog().addLog(
				"Unable to remove the following behavior due to insufficent permissions: " + removedBehavior);
		return false;

	}

	public List<Room> getRooms() {
		return baseStation.getRooms();
	}

	public <T> List<T> getComponents(Class<T> cls) {
		return baseStation.getComponents(cls);
	}

	public List<Behavior> getBehaviors() {
		return baseStation.getBehvaiors();
	}

	public boolean removeRoom(Room room) {
		if (baseStation.checkUserAccess(AccessLevel.ADMIN)) {
			baseStation.getLog().addLog("Room removed: " + room);
			baseStation.removeRoom(room);
			return true;
		}
		baseStation.getLog().addLog("Unable to remove the following room due to insufficent permissions: " + room);
		return false;

	}

	public boolean addRoom(String newRoomName) {
		if (baseStation.checkUserAccess(AccessLevel.ADMIN)) {
			Room newRoom = new Room(newRoomName);
			baseStation.getLog().addLog("Room added: " + newRoom);
			baseStation.addRoom(newRoom);
			return true;
		}
		baseStation.getLog().addLog("Unable to add the following room due to insufficent permissions: " + newRoomName);
		return false;

	}

}

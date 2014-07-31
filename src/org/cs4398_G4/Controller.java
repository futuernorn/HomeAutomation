package org.cs4398_G4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.DefaultListModel;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPin;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

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
//	final HardwareAbstractionLayer hal = new HardwareAbstractionLayer();
	final GpioController gpio = GpioFactory.getInstance();
	
	public Controller(BaseStation baseStation) {
		this.baseStation = baseStation;

		
	}
	
	public Collection<Pin> getAllPossiblePins() {
		Collection<Pin> possiblePins = new ArrayList<Pin>();
		possiblePins.add( RaspiPin.GPIO_00);
		possiblePins.add( RaspiPin.GPIO_02);
		possiblePins.add( RaspiPin.GPIO_01);
		possiblePins.add( RaspiPin.GPIO_03);
		possiblePins.add( RaspiPin.GPIO_04);
		possiblePins.add( RaspiPin.GPIO_05);
		possiblePins.add(RaspiPin.GPIO_06);
		possiblePins.add( RaspiPin.GPIO_07);
		return possiblePins;
		
	}
	
	public Collection<Pin> getAvailablePins() {
		Collection<GpioPin> provisionedPins = gpio.getProvisionedPins();
		Collection<Pin> possiblePins =  getAllPossiblePins();
		for (GpioPin provisionedPin : provisionedPins) {
			provisionedPin.getPin().getAddress();
//			System.out.println("comaparing provisioned pin number (" + provisionedPin.getPin() +") to possiblePins (" + possiblePins + ") => " + (possiblePins.contains(provisionedPin.getPin())));
//			if (possiblePins.contains(provisionedPin.getPin())) {
				possiblePins.remove(provisionedPin.getPin());
//			}
		}
		return possiblePins;
	}
	
	public void Shutdown() {
		for (Actuator a : baseStation.GetActuators()) {
			a.TurnOff();
		}
		
	}
	
	public boolean AddComponent(Component newComp, Room newRoom) {
		
		for (Entry<String, Pin> entry : newComp.inputPinNumbers.entrySet()) {
		    String key = entry.getKey();
		    Pin pinNumber = entry.getValue();
		    GpioPinDigitalInput inputPin = gpio.provisionDigitalInputPin(pinNumber, key);
		    
		    newComp.setInputPins(inputPin);
		    
		}
		for (Entry<String, Pin> entry : newComp.outputPinNumbers.entrySet()) {
		    String key = entry.getKey();
		    Pin pinNumber = entry.getValue();
		    
		    GpioPinDigitalOutput outputPin = gpio.provisionDigitalOutputPin(pinNumber, key);
//		    outputPin.addListener((GpioPinListenerDigital) newComp);
		    newComp.setOutputPins(outputPin);
		    
		}
		
		if (newComp.getClass().equals(Sensor.class)) {
			
			newRoom.AddComponent((Sensor) newComp);
		} else if (newComp.getClass().equals(Actuator.class)) {
			((Actuator) newComp).Initalize();
			newRoom.AddComponent((Actuator) newComp);
		}

		view.refreshComponents(getComponents());
		return false;
		
	}
	
	public void RemoveComponent(Component removedComponent) {
//		for (GpioPinDigitalInput inputPin : ) {
//		    
//			inputPin.
//		    
//		}
		if (removedComponent.getInputPins() != null)
			gpio.unprovisionPin(removedComponent.getInputPins());
		
		if (removedComponent.getOutputPins() != null)
			gpio.unprovisionPin(removedComponent.getOutputPins());
		baseStation.removeComponent(removedComponent);
//		removedComponent.getInputPins().unexport();
		
		
//		for (Entry<String, Pin> entry : newComp.outputPinNumbers.entrySet()) {
		
	}
	
//	public List<Component> GetComponentsByType(Class<?> type) {
//		return baseStation.GetComponentsByType(type);
//	}
	public List<Sensor> GetSensors() {
		return baseStation.GetSensors();
	}
	
	static public String PinState(Integer state) {
		String stateName = "High";
		if (state == 0)
			stateName = "Low";
		return stateName;
	}

	public List<Actuator> GetActuators() {
		return baseStation.GetActuators();
	}

	public void addBehavior(Behavior behavior) {
//		for (Sensor sensor : behavior.getSensors()) {
//			actuator.addAll(room.GetActuators());
//		} 
		baseStation.addBehavior(behavior);
		
	}

	public void removeBehavior(Behavior removedBehavior) {
		// TODO Auto-generated method stub
		baseStation.removeBehavior(removedBehavior);
		
	}

	public List<Room> getRooms() {
		// TODO Auto-generated method stub
		return baseStation.getRooms();
	}

	public List<Component> getComponents() {
		// TODO Auto-generated method stub
		return baseStation.getComponents();
	}


}

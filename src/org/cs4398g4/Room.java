package org.cs4398g4;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jh1921 Basic data structured used to separate groups of components
 *         logically by location
 */
public class Room {
	ArrayList<Component> components;
	String name;

	public Room(String name) {
		this.name = name;
		components = new ArrayList<Component>();
	}

	public void AddComponent(Component newComp) {
		components.add(newComp);
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> getComponents(Class<T> cls) {
		List<T> components = new ArrayList<T>();

		for (Component component : this.components) {
			if (cls.isInstance(component))
				components.add((T) component);
		}
		return components;
	}

	public void TurnOn() {
		List<Actuator> actuators = getComponents(Actuator.class);
		for (Actuator actuator : actuators) {
			try {
				actuator.turnOn();
			} catch (IncorrectPinStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void TurnOff() {
		List<Actuator> actuators = getComponents(Actuator.class);
		for (Actuator actuator : actuators) {
			try {
				actuator.turnOff();
			} catch (IncorrectPinStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public String toString() {
		return name;
	}

	public void removeComponent(Component removedComponent) {

		components.remove(removedComponent);

	}
}

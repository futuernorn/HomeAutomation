package org.cs4398g4;

import java.util.ArrayList;
import java.util.List;

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

	public void ToggleAllComponents() {

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

	}

	public void TurnOff() {

	}

	public String toString() {
		return name;
	}

	public void removeComponent(Component removedComponent) {

		components.remove(removedComponent);

	}
}

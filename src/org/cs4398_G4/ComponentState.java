package org.cs4398_G4;

public class ComponentState {

	private String name;
	private Integer state;
	
	public ComponentState(String name, Integer state) {
		this.name = name;
		this.state = state;
	}
	
	public String toString() {
		return name;
	}
	
	public Integer toNumber() {
		return state;
	}
}

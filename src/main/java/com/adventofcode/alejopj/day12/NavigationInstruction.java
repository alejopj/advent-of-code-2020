package com.adventofcode.alejopj.day12;

public class NavigationInstruction {

	private final Action action;
	private final Integer value;
	
	public NavigationInstruction(Action action, Integer value) {
	
		this.action = action;
		this.value = value;
	}

	public Action getAction() {
		return action;
	}

	public Integer getValue() {
		return value;
	}
	
}

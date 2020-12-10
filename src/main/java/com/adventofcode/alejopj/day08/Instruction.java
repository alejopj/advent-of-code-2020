package com.adventofcode.alejopj.day08;

public class Instruction {

	private final Operation operation;
	private final Integer value;
	
	public Instruction(Operation operation, Integer value) {
		
		this.operation = operation;
		this.value = value;
	}

	public Operation getOperation() {
		return operation;
	}

	public Integer getValue() {
		return value;
	}
	
}

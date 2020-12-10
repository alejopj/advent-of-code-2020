package com.adventofcode.alejopj.day08;

import com.google.common.collect.ImmutableList;

public enum Operation {
	ACC("acc"),
	JMP("jmp"),
	NOP("nop");
	
	private final String code;
	
	private Operation(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
	
	public static Operation of(String code) {
		
		return ImmutableList.copyOf(Operation.values()).stream()
				.filter(value -> value.getCode().equals(code))
				.findFirst().orElse(null);
	}
}

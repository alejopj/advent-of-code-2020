package com.adventofcode.alejopj.day02;

public class PasswordPolicy {

	private Integer min;
	private Integer max;
	private String character;
	
	public PasswordPolicy(Integer min, Integer max, String character) {
		
		this.min = min;
		this.max = max;
		this.character = character;
	}

	public Integer getMin() {
		return min;
	}

	public Integer getMax() {
		return max;
	}

	public String getCharacter() {
		return character;
	}
	
}

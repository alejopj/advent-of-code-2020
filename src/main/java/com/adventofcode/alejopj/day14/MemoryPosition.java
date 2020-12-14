package com.adventofcode.alejopj.day14;

public class MemoryPosition {

	private final Long index;
	private final Long value;
	
	public MemoryPosition(Long index, Long value) {
		
		this.index = index;
		this.value = value;
	}

	public Long getIndex() {
		return index;
	}

	public Long getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "MemoryPosition [index=" + index + ", value=" + value + "]";
	}
	
}

package com.adventofcode.alejopj.day14;

import java.util.List;

public class MemoryOperation {

	private final String mask;
	private final List<MemoryPosition> positions;
	
	public MemoryOperation(String mask, List<MemoryPosition> positions) {
		
		this.mask = mask;
		this.positions = positions;
	}

	public String getMask() {
		return mask;
	}

	public List<MemoryPosition> getPositions() {
		return positions;
	}

	@Override
	public String toString() {
		return "MemoryOperation [mask=" + mask + ", positions=" + positions + "]";
	}
	
}

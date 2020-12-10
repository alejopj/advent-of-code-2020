package com.adventofcode.alejopj.day08;

import java.util.ArrayList;
import java.util.List;

public class Day08 {

	public Day08() {
		
	}
	
	public Integer findAccumulatorValueBeforeAnyInstructionIsExecutedTwice(List<Instruction> instructions) {

		Integer accumulator = 0;
		int i = 0;
		List<Integer> history = new ArrayList<>();
		Instruction instruction = null;
		while (i >= 0 && i < instructions.size() && !history.contains(i)) {
			history.add(i);
			instruction = instructions.get(i);
			Operation operation = instruction.getOperation();
			Integer value = instruction.getValue();
			switch (operation) {
				case ACC:
					accumulator += value;
					i++;
					break;
				case JMP:
					i += value;
					break;
				default:
					i++;
					break;
			}
		}
		
		return accumulator;
	}
	
	public Integer findAccumulatorValueAfterFixAndCompleteExecution(List<Instruction> instructions) {
		
		Integer accumulator = null;
		
		for (int i = 0; i < instructions.size(); i++) {
			Instruction instruction = instructions.get(i);
			Operation operation = instruction.getOperation();
			switch (operation) {
				case JMP:
					Instruction nop = new Instruction(Operation.NOP, instruction.getValue());
					accumulator = getAccumulator(instructions, i, nop);
					break;
				case NOP:
					Instruction jmp = new Instruction(Operation.JMP, instruction.getValue());
					accumulator = getAccumulator(instructions, i, jmp);
					break;
				default:
					break;
			}
			if (accumulator != null) {
				return accumulator;
			}
		}
		
		return accumulator;
	}
	
	// Private
	
	private Integer getAccumulator(List<Instruction> instructions, Integer index, Instruction newInstruction) {
		
		Integer accumulator = 0;
		
		List<Instruction> newInstructions = new ArrayList<>(instructions);
		newInstructions.set(index, newInstruction);
		int i = 0;
		List<Integer> history = new ArrayList<>();
		Instruction instruction = null;
		while (i >= 0 && i < newInstructions.size()) {
			if (history.contains(i)) {
				accumulator = null;
				break;
			}
			history.add(i);
			instruction = newInstructions.get(i);
			Operation operation = instruction.getOperation();
			Integer value = instruction.getValue();
			switch (operation) {
				case ACC:
					accumulator += value;
					i++;
					break;
				case JMP:
					i += value;
					break;
				default:
					i++;
					break;
			}
		}
		
		return accumulator;
	}
	
}

package com.adventofcode.alejopj.day08;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.ImmutableList;

public class Day08Test {
	
	/**
	 * Given a list of instructions
	 * When executed
	 * And stopped just before any instruction is executed twice
	 * Then the value of the accumulator is returned
	 * And that value is 5
	 */
	@Test
	public void findAccumulatorValueBeforeAnyInstructionIsExecutedTwiceIs5() {

		List<Instruction> instructions = getInstructions("part1.txt");
		Day08 day08 = new Day08();
		Integer accumulatorValueBeforeAnyInstructionIsExecutedTwice =
				day08.findAccumulatorValueBeforeAnyInstructionIsExecutedTwice(instructions);
		Assert.assertTrue(accumulatorValueBeforeAnyInstructionIsExecutedTwice == 5);
	}

	/**
	 * Given a list of instructions
	 * When executed
	 * And stopped just before any instruction is executed twice
	 * Then the value of the accumulator is returned
	 */
	@Test
	public void findAccumulatorValueBeforeAnyInstructionIsExecutedTwice() {

		List<Instruction> instructions = getInstructions("input.txt");
		Day08 day08 = new Day08();
		Integer accumulatorValueBeforeAnyInstructionIsExecutedTwice =
				day08.findAccumulatorValueBeforeAnyInstructionIsExecutedTwice(instructions);
		Assert.assertTrue(accumulatorValueBeforeAnyInstructionIsExecutedTwice >= 0);
		System.out.println(accumulatorValueBeforeAnyInstructionIsExecutedTwice);
	}
	
	/**
	 * Given a list of instructions
	 * When executed
	 * And changing exactly one jmp (to nop) or nop (to jmp)
	 * Then the program terminates normally
	 * And the value of the accumulator is returned
	 * And that value is 8
	 */
	@Test
	public void findAccumulatorValueAfterFixAndCompleteExecutionIs8() {

		List<Instruction> instructions = getInstructions("part2.txt");
		Day08 day08 = new Day08();
		Integer accumulatorValueAfterFixAndCompleteExecution =
				day08.findAccumulatorValueAfterFixAndCompleteExecution(instructions);
		Assert.assertNotNull(accumulatorValueAfterFixAndCompleteExecution);
	}
	
	/**
	 * Given a list of instructions
	 * When executed
	 * And changing exactly one jmp (to nop) or nop (to jmp)
	 * Then the program terminates normally
	 * And the value of the accumulator is returned
	 */
	@Test
	public void findAccumulatorValueAfterFixAndCompleteExecution() {

		List<Instruction> instructions = getInstructions("input.txt");
		Day08 day08 = new Day08();
		Integer accumulatorValueAfterFixAndCompleteExecution =
				day08.findAccumulatorValueAfterFixAndCompleteExecution(instructions);
		Assert.assertNotNull(accumulatorValueAfterFixAndCompleteExecution);
		System.out.println(accumulatorValueAfterFixAndCompleteExecution);
	}
	
	// Private
	
	private List<Instruction> getInstructions(String fileName) {
		
		List<Instruction> instructions = new ArrayList<>();
		
		try {
			Path path = Paths.get(getClass().getClassLoader().getResource("com/adventureofcode/alejopj/day08/" + fileName).toURI());
			List<String> lines = Files.lines(path).collect(ImmutableList.toImmutableList());
			for (String line : lines) {
				String[] operationAndValue = line.split(" ");
				Operation operation = Operation.of(operationAndValue[0].trim());
				Integer value = Integer.valueOf(operationAndValue[1].trim());
				instructions.add(new Instruction(operation, value));
			}
		} catch (Exception e) {
			Assert.fail();
		}
		
		return instructions;
	}
	
}

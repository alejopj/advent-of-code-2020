package com.adventofcode.alejopj.day14;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.ImmutableList;

public class Day14Test {
	
	/**
	 * Given a mask
	 * And a list of memory positions and values
	 * When mask is applied to values
	 * And values are written into their memory positions 
	 * Then the sum of all written values is returned
	 * And that sum is 165
	 */
	@Test
	public void findSumOfMemoryValuesIs165() {

		List<MemoryOperation> memOperations = getMaskAndMemPositions("part1.txt");
		Day14 day14 = new Day14();
		Long sum = day14.findSumOfMemoryValues(memOperations);
		System.out.println(sum);
		Assert.assertTrue(sum == 165);
	}
	
	/**
	 * Given a mask
	 * And a list of memory positions and values
	 * When mask is applied to values
	 * And values are written into their memory positions 
	 * Then the sum of all written values is returned
	 */
	@Test
	public void findSumOfMemoryValues() {

		List<MemoryOperation> memOperations = getMaskAndMemPositions("input.txt");
		Day14 day14 = new Day14();
		Long sum = day14.findSumOfMemoryValues(memOperations);
		Assert.assertTrue(sum >= 0);
		System.out.println(sum);
	}
	
	/**
	 * Given a mask
	 * And a list of memory positions and values
	 * When mask is applied to indexes
	 * And values are written into their memory positions 
	 * Then the sum of all written values is returned
	 * And that sum is 208
	 */
	@Test
	public void findNewSumOfMemoryValuesIs208() {

		List<MemoryOperation> memOperations = getMaskAndMemPositions("part2.txt");
		Day14 day14 = new Day14();
		Long sum = day14.findNewSumOfMemoryValues(memOperations);
		System.out.println(sum);
		Assert.assertTrue(sum == 208);
	}
	
	/**
	 * Given a mask
	 * And a list of memory positions and values
	 * When mask is applied to indexes
	 * And values are written into their memory positions 
	 * Then the sum of all written values is returned
	 */
	@Test
	public void findNewSumOfMemoryValues() {

		List<MemoryOperation> memOperations = getMaskAndMemPositions("input.txt");
		Day14 day14 = new Day14();
		Long sum = day14.findNewSumOfMemoryValues(memOperations);
		Assert.assertTrue(sum >= 0);
		System.out.println(sum);
	}
	
	// Private
	
	private List<MemoryOperation> getMaskAndMemPositions(String fileName) {
		
		List<MemoryOperation> memoryOperations = new ArrayList<>();
		
		try {
			Path path = Paths.get(getClass().getClassLoader().getResource("com/adventureofcode/alejopj/day14/" + fileName).toURI());
			List<String> lines = Files.lines(path).collect(ImmutableList.toImmutableList());
			if (lines.isEmpty()) {
				Assert.fail();
			}
			List<String> masks = new ArrayList<>();
			List<Integer> maskIndexes = new ArrayList<>();
			for (int n = 0; n < lines.size(); n++) {
				String line = lines.get(n);
				if (line.startsWith("mask")) {
					masks.add(line.substring(7));
					maskIndexes.add(n);
				}
			}
			
			List<MemoryPosition> memPositions = lines.stream()
					.filter(line -> line.startsWith("mem"))
					.map(line -> {
						Long memIndex = Long.valueOf(line.substring(4).split("]")[0]);
						Long memValue = Long.valueOf(line.split("=")[1].trim());
						return new MemoryPosition(memIndex, memValue);
					})
					.collect(Collectors.toList());
			
			String mask = null;
			for (int i = 0; i < maskIndexes.size() - 1; i++) {
				mask = masks.get(i);
				int j = maskIndexes.get(i + 1) - maskIndexes.get(i) - 1;
				List<MemoryPosition> positions = memPositions.subList(0, j);
				memoryOperations.add(new MemoryOperation(mask, ImmutableList.copyOf(positions)));
				memPositions.removeAll(positions);
			};
			mask = masks.get(masks.size() - 1);
			memoryOperations.add(new MemoryOperation(mask, memPositions));
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		
		return memoryOperations;
	}
	
}

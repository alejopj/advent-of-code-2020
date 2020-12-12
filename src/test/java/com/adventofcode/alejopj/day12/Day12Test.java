package com.adventofcode.alejopj.day12;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.ImmutableList;

public class Day12Test {
	
	/**
	 * Given a list of navigation instructions
	 * And East as initial direction
	 * When interpreted
	 * Then the Manhattan distance of the final coordinates is returned
	 * And that number is 25
	 */
	@Test
	public void findManhattanDistanceIs25() {

		List<NavigationInstruction> instructions = getNavigationInstructions("part1.txt");
		Day12 day12 = new Day12();
		Integer manhattanDistance = day12.findManhattanDistance(Action.E, instructions);
		Assert.assertTrue(manhattanDistance == 25);
	}
	
	/**
	 * Given a list of navigation instructions
	 * And East as initial direction
	 * When interpreted
	 * Then the Manhattan distance of the final coordinates is returned
	 */
	@Test
	public void findManhattanDistance() {

		List<NavigationInstruction> instructions = getNavigationInstructions("input.txt");
		Day12 day12 = new Day12();
		Integer manhattanDistance = day12.findManhattanDistance(Action.E, instructions);
		Assert.assertTrue(manhattanDistance >= 0);
		System.out.println(manhattanDistance);
	}
	
	/**
	 * Given a list of navigation instructions
	 * And a initial waypoint position
	 * When interpreted
	 * Then the Manhattan distance of the final coordinates is returned
	 * And that number is 25
	 */
	@Test
	public void findNewManhattanDistanceIs286() {

		List<NavigationInstruction> instructions = getNavigationInstructions("part2.txt");
		Coordinate wayPoint = new Coordinate(10, 1);
		Day12 day12 = new Day12();
		Integer manhattanDistance = day12.findManhattanDistance(wayPoint, instructions);
		System.out.println(manhattanDistance);
		Assert.assertTrue(manhattanDistance == 286);
	}
	
	/**
	 * Given a list of navigation instructions
	 * And a initial waypoint position
	 * When interpreted
	 * Then the Manhattan distance of the final coordinates is returned
	 */
	@Test
	public void findNewManhattanDistance() {

		List<NavigationInstruction> instructions = getNavigationInstructions("input.txt");
		Coordinate wayPoint = new Coordinate(10, 1);
		Day12 day12 = new Day12();
		Integer manhattanDistance = day12.findManhattanDistance(wayPoint, instructions);
		Assert.assertTrue(manhattanDistance >= 0);
		System.out.println(manhattanDistance);
	}
	
	// Private
	
	private List<NavigationInstruction> getNavigationInstructions(String fileName) {
		
		List<NavigationInstruction> instructions = new ArrayList<>();
		
		try {
			Path path = Paths.get(getClass().getClassLoader().getResource("com/adventureofcode/alejopj/day12/" + fileName).toURI());
			List<String> lines = Files.lines(path).collect(ImmutableList.toImmutableList());
			for (String line : lines) {
				Action action = Action.valueOf(line.substring(0, 1));
				Integer value = Integer.valueOf(line.substring(1));
				NavigationInstruction instruction = new NavigationInstruction(action, value);
				instructions.add(instruction);
			}
		} catch (Exception e) {
			Assert.fail();
		}
		
		return instructions;
	}
	
}

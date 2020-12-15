package com.adventofcode.alejopj.day15;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.ImmutableList;

public class Day15Test {
	
	/**
	 * Given a list of starting numbers
	 * When played to the most recently spoken number game
	 * Then the 2020th number is returned
	 * And that number is 436
	 */
	@Test
	public void find2020thSpokenNumberIs436() {

		List<Integer> startingNumbers = getStartingNumbers("part1.txt");
		Day15 day15 = new Day15();
		Integer number = day15.findNthSpokenNumber(startingNumbers, 2020);
		Assert.assertTrue(number == 436);
	}
	
	/**
	 * Given the starting numbers 1,3,2 
	 * And the starting numbers 2,1,3 
	 * And the starting numbers 1,2,3 
	 * And the starting numbers 2,3,1 
	 * And the starting numbers 3,2,1 
	 * And the starting numbers 3,1,2 
	 * When played to the most recently spoken number game
	 * Then the 2020th number is returned
	 * And that number is 1
	 * And that number is 10
	 * And that number is 27
	 * And that number is 78
	 * And that number is 438
	 * And that number is 1836 
	 */
	@Test
	public void find2020thSpokenNumberIs() {

		Day15 day15 = new Day15();
		Integer number = day15.findNthSpokenNumber(ImmutableList.of(1,3,2), 2020);
		Assert.assertTrue(number == 1);
		number = day15.findNthSpokenNumber(ImmutableList.of(2,1,3), 2020);
		Assert.assertTrue(number == 10);
		number = day15.findNthSpokenNumber(ImmutableList.of(1,2,3), 2020);
		Assert.assertTrue(number == 27);
		number = day15.findNthSpokenNumber(ImmutableList.of(2,3,1), 2020);
		Assert.assertTrue(number == 78);
		number = day15.findNthSpokenNumber(ImmutableList.of(3,2,1), 2020);
		Assert.assertTrue(number == 438);
		number = day15.findNthSpokenNumber(ImmutableList.of(3,1,2), 2020);
		Assert.assertTrue(number == 1836);
	}
	
	/**
	 * Given a list of starting numbers
	 * When played to the most recently spoken number game
	 * Then the 2020th number is returned
	 */
	@Test
	public void find2020thSpokenNumber() {

		List<Integer> startingNumbers = getStartingNumbers("input.txt");
		Day15 day15 = new Day15();
		Integer number = day15.findNthSpokenNumber(startingNumbers, 2020);
		Assert.assertTrue(number >= 0);
		System.out.println(number);
	}
	
	/**
	 * Given the starting numbers 0,3,6
	 * And the starting numbers 1,3,2 
	 * And the starting numbers 2,1,3 
	 * And the starting numbers 1,2,3 
	 * And the starting numbers 2,3,1 
	 * And the starting numbers 3,2,1 
	 * And the starting numbers 3,1,2 
	 * When played to the most recently spoken number game
	 * Then the 30000000th number is returned
	 * And that number is 175594
	 * And that number is 2578
	 * And that number is 3544142
	 * And that number is 261214
	 * And that number is 6895259
	 * And that number is 18
	 * And that number is 362
	 */
	@Test
	public void find30000000thSpokenNumberIs() {

		Day15 day15 = new Day15();
		Integer number = day15.findNthSpokenNumber(ImmutableList.of(0,3,6), 30000000);
		Assert.assertTrue(number == 175594);
//		number = day15.findNthSpokenNumber(ImmutableList.of(1,3,2), 30000000);
//		Assert.assertTrue(number == 2578);
//		number = day15.findNthSpokenNumber(ImmutableList.of(2,1,3), 30000000);
//		Assert.assertTrue(number == 3544142);
//		number = day15.findNthSpokenNumber(ImmutableList.of(1,2,3), 30000000);
//		Assert.assertTrue(number == 261214);
//		number = day15.findNthSpokenNumber(ImmutableList.of(2,3,1), 30000000);
//		Assert.assertTrue(number == 6895259);
//		number = day15.findNthSpokenNumber(ImmutableList.of(3,2,1), 30000000);
//		Assert.assertTrue(number == 18);
//		number = day15.findNthSpokenNumber(ImmutableList.of(3,1,2), 30000000);
//		Assert.assertTrue(number == 362);
	}
	
	/**
	 * Given a list of starting numbers
	 * When played to the most recently spoken number game
	 * Then the 2020th number is returned
	 */
	@Test
	public void find30000000thSpokenNumber() {

		List<Integer> startingNumbers = getStartingNumbers("input.txt");
		Day15 day15 = new Day15();
		Integer number = day15.findNthSpokenNumber(startingNumbers, 30000000);
		Assert.assertTrue(number >= 0);
		System.out.println(number);
	}
	
	// Private
	
	private List<Integer> getStartingNumbers(String fileName) {
		
		List<Integer> startingNumbers = ImmutableList.of();
		
		try {
			Path path = Paths.get(getClass().getClassLoader().getResource("com/adventureofcode/alejopj/day15/" + fileName).toURI());
			List<String> lines = Files.lines(path).collect(ImmutableList.toImmutableList());
			if (lines.isEmpty()) {
				Assert.fail();
			}
			startingNumbers = Arrays.stream(lines.get(0).split(","))
					.map(value -> Integer.valueOf(value))
					.collect(ImmutableList.toImmutableList());
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		
		return startingNumbers;
	}
	
}

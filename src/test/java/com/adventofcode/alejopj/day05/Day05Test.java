package com.adventofcode.alejopj.day05;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.ImmutableList;

public class Day05Test {
	
	/**
	 * Given a list of boarding passes
	 * When they are checked
	 * Then the highest seat id is returned
	 * And that id is 357
	 */
	@Test
	public void findHighestSeatIdIs357() {

		Day05 day05 = new Day05();
		List<String> boardingPasses = getBoardingPasses("part1.txt");
		Integer highestSeatId = day05.findHighestSeatId(boardingPasses);
		Assert.assertNotNull(highestSeatId);
		Assert.assertTrue(highestSeatId == 357);
	}

	/**
	 * Given a list of boarding passes
	 * When they are checked
	 * Then the highest seat id is returned
	 */
	@Test
	public void findHighestSeatId() {

		Day05 day05 = new Day05();
		List<String> boardingPasses = getBoardingPasses("input.txt");
		Integer highestSeatId = day05.findHighestSeatId(boardingPasses);
		Assert.assertNotNull(highestSeatId);
		System.out.println(highestSeatId);
	}
	
	/**
	 * Given a list of boarding passes
	 * When they are checked
	 * Then my seat id is returned
	 */
	@Test
	public void findMySeatId() {

		Day05 day05 = new Day05();
		List<String> boardingPasses = getBoardingPasses("input.txt");
		Integer mySeatId = day05.findMySeatId(boardingPasses);
		Assert.assertNotNull(mySeatId);
		System.out.println(mySeatId);
	}
	
	// Private
	
	private List<String> getBoardingPasses(String fileName) {
		
		List<String> boardingPasses = ImmutableList.of();
		
		try {
			Path path = Paths.get(getClass().getClassLoader().getResource("com/adventureofcode/alejopj/day05/" + fileName).toURI());
			boardingPasses = Files.lines(path).collect(ImmutableList.toImmutableList());
		} catch (Exception e) {
			Assert.fail();
		}
		
		return boardingPasses;
	}
	
}

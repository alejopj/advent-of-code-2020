package com.adventofcode.alejopj.day10;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.ImmutableList;

public class Day10Test {
	
	/**
	 * Given a list of joltage ratings
	 * And an initial joltage rating of 0
	 * And a list of allowed input joltage diffs = [1, 2, 3]
	 * And a final joltage diff of 3
	 * When checked for the number of 1-jolt differences multiplied by the number of 3-jolt differences
	 * Then that number is returned
	 * And that number is 35
	 */
	@Test
	public void findProductOf1JoltDiffBy3JoltDiffIs35() {

		List<Integer> joltageRatings = getJoltageRatings("part1-1.txt");
		Day10 day10 = new Day10();
		Integer productOf1JoltDiffBy3JoltDiff =
				day10.findProductOf1JoltDiffBy3JoltDiff(joltageRatings, 0, ImmutableList.of(1, 2, 3), 3);
		Assert.assertTrue(productOf1JoltDiffBy3JoltDiff == 35);
	}
	
	/**
	 * Given a list of joltage ratings
	 * And an initial joltage rating of 0
	 * And a list of allowed input joltage diffs = [1, 2, 3]
	 * And a final joltage diff of 3
	 * When checked for the number of 1-jolt differences multiplied by the number of 3-jolt differences
	 * Then that number is returned
	 * And that number is 220
	 */
	@Test
	public void findProductOf1JoltDiffBy3JoltDiffIs220() {

		List<Integer> joltageRatings = getJoltageRatings("part1-2.txt");
		Day10 day10 = new Day10();
		Integer productOf1JoltDiffBy3JoltDiff =
				day10.findProductOf1JoltDiffBy3JoltDiff(joltageRatings, 0, ImmutableList.of(1, 2, 3), 3);
		Assert.assertTrue(productOf1JoltDiffBy3JoltDiff == 220);
	}
	
	/**
	 * Given a list of joltage ratings
	 * And an initial joltage rating of 0
	 * And a list of allowed input joltage diffs = [1, 2, 3]
	 * And a final joltage diff of 3
	 * When checked for the number of 1-jolt differences multiplied by the number of 3-jolt differences
	 * Then that number is returned
	 */
	@Test
	public void findProductOf1JoltDiffBy3JoltDiff() {

		List<Integer> joltageRatings = getJoltageRatings("input.txt");
		Day10 day10 = new Day10();
		Integer productOf1JoltDiffBy3JoltDiff =
				day10.findProductOf1JoltDiffBy3JoltDiff(joltageRatings, 0, ImmutableList.of(1, 2, 3), 3);
		Assert.assertTrue(productOf1JoltDiffBy3JoltDiff >= 0);
		System.out.println(productOf1JoltDiffBy3JoltDiff);
	}
	
	/**
	 * Given a list of joltage ratings
	 * And an initial joltage rating of 0
	 * And a list of allowed input joltage diffs = [1, 2, 3]
	 * And a final joltage diff of 3
	 * When checked for the total number of distinct ways of arranging the adapters to connect the charging outlet to the device
	 * Then that number is returned
	 * And that number is 8
	 */
	@Test
	public void findWaysToConnectChargingOutletToDeviceIs8() {

		List<Integer> joltageRatings = getJoltageRatings("part2-1.txt");
		Day10 day10 = new Day10();
		Long waysToConnectChargingOutletToDevice =
				day10.findWaysToConnectChargingOutletToDevice(joltageRatings, 0, ImmutableList.of(1, 2, 3), 3);
		Assert.assertTrue(waysToConnectChargingOutletToDevice == 8);
	}
	
	/**
	 * Given a list of joltage ratings
	 * And an initial joltage rating of 0
	 * And a list of allowed input joltage diffs = [1, 2, 3]
	 * And a final joltage diff of 3
	 * When checked for the total number of distinct ways of arranging the adapters to connect the charging outlet to the device
	 * Then that number is returned
	 * And that number is 19208
	 */
	@Test
	public void findWaysToConnectChargingOutletToDeviceIs19208() {

		List<Integer> joltageRatings = getJoltageRatings("part2-2.txt");
		Day10 day10 = new Day10();
		Long waysToConnectChargingOutletToDevice =
				day10.findWaysToConnectChargingOutletToDevice(joltageRatings, 0, ImmutableList.of(1, 2, 3), 3);
		Assert.assertTrue(waysToConnectChargingOutletToDevice == 19208);
	}
	
	/**
	 * Given a list of joltage ratings
	 * And an initial joltage rating of 0
	 * And a list of allowed input joltage diffs = [1, 2, 3]
	 * And a final joltage diff of 3
	 * When checked for the total number of distinct ways of arranging the adapters to connect the charging outlet to the device
	 * Then that number is returned
	 * And that number is 
	 */
	@Test
	public void findWaysToConnectChargingOutletToDevice() {

		List<Integer> joltageRatings = getJoltageRatings("input.txt");
		Day10 day10 = new Day10();
		Long waysToConnectChargingOutletToDevice =
				day10.findWaysToConnectChargingOutletToDevice(joltageRatings, 0, ImmutableList.of(1, 2, 3), 3);
		Assert.assertTrue(waysToConnectChargingOutletToDevice >= 0);
		System.out.println(waysToConnectChargingOutletToDevice);
	}

	// Private
	
	private List<Integer> getJoltageRatings(String fileName) {
		
		List<Integer> joltageRatings = ImmutableList.of();
		
		try {
			Path path = Paths.get(getClass().getClassLoader().getResource("com/adventureofcode/alejopj/day10/" + fileName).toURI());
			List<String> lines = Files.lines(path).collect(ImmutableList.toImmutableList());
			joltageRatings = lines.parallelStream().map(line -> Integer.valueOf(line)).collect(ImmutableList.toImmutableList());
		} catch (Exception e) {
			Assert.fail();
		}
		
		return joltageRatings;
	}
	
}

package com.adventofcode.alejopj.day13;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.ImmutableList;

public class Day13Test {
	
	/**
	 * Given a departure time and a list of buses
	 * When searched for the earliest bus
	 * Then the product of the earliest bus id and waiting time from departure is returned
	 * And that number is 295
	 */
	@Test
	public void findProductOfEarliestBusAndWaitingTimeIs295() {

		Map<Integer, List<Integer>> departureAndBuses = getDepartureAndBuses("part1.txt");
		Day13 day13 = new Day13();
		Integer product = day13.findProductOfEarliestBusAndWaitingTime(departureAndBuses);
		Assert.assertTrue(product == 295);
	}
	
	/**
	 * Given a departure time and a list of buses
	 * When searched for the earliest bus
	 * Then the product of the earliest bus id and waiting time from departure is returned
	 */
	@Test
	public void findProductOfEarliestBusAndWaitingTime() {

		Map<Integer, List<Integer>> departureAndBuses = getDepartureAndBuses("input.txt");
		Day13 day13 = new Day13();
		Integer product = day13.findProductOfEarliestBusAndWaitingTime(departureAndBuses);
		Assert.assertTrue(product >= 0);
		System.out.println(product);
	}
	
	/**
	 * Given a list of buses and positions
	 * When searched for the earliest time matching buses and positions
	 * Then the earliest time matching buses and positions is returned
	 * And that number is 1068781
	 */
	@Test
	public void findEarliestTimeMatchingBusesAndPositionsIs1068781() {

		Map<Integer, List<Integer>> departureAndBuses = getDepartureAndBuses("part2.txt");
		Long initialLeastCommonMultCandidate = 0L;
		Day13 day13 = new Day13();
		Long product = day13.findEarliestTimeMatchingBusesAndPositions(initialLeastCommonMultCandidate, departureAndBuses);
		Assert.assertTrue(product == 1068781);
	}
	
	/**
	 * Given a list of buses and positions
	 * When searched for the earliest time matching buses and positions
	 * Then the earliest time matching buses and positions is returned
	 */
	@Test
	public void findEarliestTimeMatchingBusesAndPositions() {

		Map<Integer, List<Integer>> departureAndBuses = getDepartureAndBuses("input.txt");
		Long initialLeastCommonMultCandidate = 100000000000000L;
		Day13 day13 = new Day13();
		Long product = day13.findEarliestTimeMatchingBusesAndPositions(initialLeastCommonMultCandidate, departureAndBuses);
		Assert.assertTrue(product >= 0);
		System.out.println(product);
	}
	
	// Private
	
	private Map<Integer, List<Integer>> getDepartureAndBuses(String fileName) {
		
		Map<Integer, List<Integer>> departureAndBuses = new LinkedHashMap<>();
		
		try {
			Path path = Paths.get(getClass().getClassLoader().getResource("com/adventureofcode/alejopj/day13/" + fileName).toURI());
			List<String> lines = Files.lines(path).collect(ImmutableList.toImmutableList());
			if (lines.isEmpty()) {
				Assert.fail();
			}
			Integer departure = Integer.valueOf(lines.get(0));
			List<Integer> buses = Arrays.stream(lines.get(1).split(","))
					.map(bus -> bus.equals("x") ? Integer.MIN_VALUE : Integer.valueOf(bus))
					.collect(ImmutableList.toImmutableList());
			departureAndBuses.put(departure, buses);
		} catch (Exception e) {
			Assert.fail();
		}
		
		return departureAndBuses;
	}
	
}

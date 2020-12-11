package com.adventofcode.alejopj.day11;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.ImmutableList;

public class Day11Test {
	
	/**
	 * Given a list of seat statuses
	 * And iteration N and N-1 are equals
	 * When checked for the number of occupied seats
	 * Then that number is returned
	 * And that number is 37
	 */
	@Test
	public void findNumberOfOccupiedSeatsIs37() {

		SeatStatus[][] seatStatuses = getSeatStatuses("part1.txt");
		Day11 day11 = new Day11();
		Long numberOfOccupiedSeats = day11.findNumberOfOccupiedSeats(seatStatuses);
		Assert.assertTrue(numberOfOccupiedSeats == 37);
	}
	
	/**
	 * Given a list of seat statuses
	 * And iteration N and N-1 are equals
	 * When checked for the number of occupied seats
	 * Then that number is returned
	 */
	@Test
	public void findNumberOfOccupiedSeats() {

		SeatStatus[][] seatStatuses = getSeatStatuses("input.txt");
		Day11 day11 = new Day11();
		Long numberOfOccupiedSeats = day11.findNumberOfOccupiedSeats(seatStatuses);
		Assert.assertTrue(numberOfOccupiedSeats >= 0);
		System.out.println(numberOfOccupiedSeats);
	}
	
	/**
	 * Given a list of seat statuses
	 * And iteration N and N-1 are equals
	 * When checked for the number of occupied seats
	 * Then that number is returned
	 * And that number is 37
	 */
	@Test
	public void findNewNumberOfOccupiedSeatsIs26() {

		SeatStatus[][] seatStatuses = getSeatStatuses("part2.txt");
		Day11 day11 = new Day11();
		Long numberOfOccupiedSeats = day11.findNewNumberOfOccupiedSeats(seatStatuses);
		Assert.assertTrue(numberOfOccupiedSeats == 26);
	}
	
	/**
	 * Given a list of seat statuses
	 * And iteration N and N-1 are equals
	 * When checked for the number of occupied seats
	 * Then that number is returned
	 */
	@Test
	public void findNewNumberOfOccupiedSeats() {

		SeatStatus[][] seatStatuses = getSeatStatuses("input.txt");
		Day11 day11 = new Day11();
		Long numberOfOccupiedSeats = day11.findNewNumberOfOccupiedSeats(seatStatuses);
		Assert.assertTrue(numberOfOccupiedSeats >= 0);
		System.out.println(numberOfOccupiedSeats);
	}
	
	// Private
	
	private SeatStatus[][] getSeatStatuses(String fileName) {
		
		SeatStatus[][] seatStatuses = {};
		
		try {
			Path path = Paths.get(getClass().getClassLoader().getResource("com/adventureofcode/alejopj/day11/" + fileName).toURI());
			List<String> lines = Files.lines(path).collect(ImmutableList.toImmutableList());
			int rows = lines.size();
			int columns = lines.isEmpty() ? 0 : lines.get(0).length();
			seatStatuses = new SeatStatus[rows][columns];
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < columns; j++) {
					seatStatuses[i][j] = SeatStatus.of(String.valueOf(lines.get(i).charAt(j)));
				}
			}
		} catch (Exception e) {
			Assert.fail();
		}
		
		return seatStatuses;
	}
	
}

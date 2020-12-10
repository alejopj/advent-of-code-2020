package com.adventofcode.alejopj.day03;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

public class Day03Test {
	
	/**
	 * Given the start is at the top-left corner
	 * And a slop slope of right 3, down 1
	 * When the map is traversed
	 * And the bottom is reached
	 * Then the number of encountered trees is returned
	 * And that number is 7
	 */
	@Test
	public void findNumberOfTreesIs7() {

		Day03 day03 = new Day03();
		SquareContent[][] map = getMap("part1.txt");
		Map<SlopeDirection, Integer> slope = ImmutableMap.of(SlopeDirection.RIGHT, 3, SlopeDirection.BOTTOM, 1);
		Integer numberOfTreesEncountered = day03.findNumberOfTreesEncountered(map, 0, 0, slope);
		Assert.assertTrue(numberOfTreesEncountered == 7);
	}

	/**
	 * Given the start is at the top-left corner
	 * And a slop slope of right 3, down 1
	 * When the map is traversed
	 * And the bottom is reached
	 * Then the number of encountered trees is returned
	 */
	@Test
	public void findNumberOfTrees() {

		Day03 day03 = new Day03();
		SquareContent[][] map = getMap("input.txt");
		Map<SlopeDirection, Integer> slope = ImmutableMap.of(SlopeDirection.RIGHT, 3, SlopeDirection.BOTTOM, 1);
		Integer numberOfTreesEncountered = day03.findNumberOfTreesEncountered(map, 0, 0, slope);
		Assert.assertTrue(numberOfTreesEncountered >= 0);
		System.out.println(numberOfTreesEncountered);
	}
	
	/**
	 * Given the start is at the top-left corner
	 * And a list of slopes:
	 *   right 1, down 1
	 *   right 3, down 1
	 *   right 5, down 1
	 *   right 7, down 1
	 *   right 1, down 2
	 * When the map is traversed
	 * And the bottom is reached
	 * Then the number of encountered trees is returned
	 * And that number is 336
	 */
	@Test
	public void findMultipliedNumberOfTreesIs336() {

		Day03 day03 = new Day03();
		SquareContent[][] map = getMap("part2.txt");
		List<Map<SlopeDirection, Integer>> slopes = ImmutableList.of(
				ImmutableMap.of(SlopeDirection.RIGHT, 1, SlopeDirection.BOTTOM, 1),
				ImmutableMap.of(SlopeDirection.RIGHT, 3, SlopeDirection.BOTTOM, 1),
				ImmutableMap.of(SlopeDirection.RIGHT, 5, SlopeDirection.BOTTOM, 1),
				ImmutableMap.of(SlopeDirection.RIGHT, 7, SlopeDirection.BOTTOM, 1),
				ImmutableMap.of(SlopeDirection.RIGHT, 1, SlopeDirection.BOTTOM, 2));
		Long multipliedEncounteredTrees = slopes.stream()
				.mapToLong(slope -> day03.findNumberOfTreesEncountered(map, 0, 0, slope))
				.reduce((a, b) -> a * b).orElse(0);
		Assert.assertTrue(multipliedEncounteredTrees == 336);
	}
	
	/**
	/**
	 * Given the start is at the top-left corner
	 * And a list of slopes:
	 *   right 1, down 1
	 *   right 3, down 1
	 *   right 5, down 1
	 *   right 7, down 1
	 *   right 1, down 2
	 * When the map is traversed
	 * And the bottom is reached
	 * Then the number of encountered trees is returned
	 */
	@Test
	public void findMultipliedNumberOfTrees() {

		Day03 day03 = new Day03();
		SquareContent[][] map = getMap("input.txt");
		List<Map<SlopeDirection, Integer>> slopes = ImmutableList.of(
				ImmutableMap.of(SlopeDirection.RIGHT, 1, SlopeDirection.BOTTOM, 1),
				ImmutableMap.of(SlopeDirection.RIGHT, 3, SlopeDirection.BOTTOM, 1),
				ImmutableMap.of(SlopeDirection.RIGHT, 5, SlopeDirection.BOTTOM, 1),
				ImmutableMap.of(SlopeDirection.RIGHT, 7, SlopeDirection.BOTTOM, 1),
				ImmutableMap.of(SlopeDirection.RIGHT, 1, SlopeDirection.BOTTOM, 2));
		Long multipliedEncounteredTrees = slopes.stream()
				.mapToLong(slope -> day03.findNumberOfTreesEncountered(map, 0, 0, slope))
				.reduce((a, b) -> a * b).orElse(0);
		Assert.assertTrue(multipliedEncounteredTrees >= 0);
		System.out.println(multipliedEncounteredTrees);
	}
	
	// Private
	
	private SquareContent[][] getMap(String fileName) {

		SquareContent[][] map = {};
		
		try {
			Path path = Paths.get(getClass().getClassLoader().getResource("com/adventureofcode/alejopj/day03/" + fileName).toURI());
			List<String> lines = Files.lines(path).collect(ImmutableList.toImmutableList());
			int rows = lines.size();
			int columns = lines.isEmpty() ? 0 : lines.get(0).length();
			map = new SquareContent[rows][columns];
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < columns; j++) {
					map[i][j] = SquareContent.of(String.valueOf(lines.get(i).charAt(j)));
				}
			}
		} catch (Exception e) {
			Assert.fail();
		}
		
		return map;
	}
	
}

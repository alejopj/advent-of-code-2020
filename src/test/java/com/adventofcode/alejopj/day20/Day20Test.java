package com.adventofcode.alejopj.day20;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.ImmutableList;

public class Day20Test {
	
	/**
	 * Given a list of rotated random-orientation flipped tiles
	 * When rearranging them
	 * Then there is a square arrangement that causes all adjacent borders to line up
	 * And the product of the tile ids of the four corners is returned
	 * And that number is 20899048083289
	 */
	@Test
	public void findProductOfFourCorningTileIdsIs20899048083289() {

		List<Tile> tiles = getTiles("part1.txt");
		Day20 day20 = new Day20();
		Long product = day20.findProductOfFourCorningTileIds(tiles);
		Assert.assertTrue(product == 20899048083289L);
	}
	
	/**
	 * Given a list of rules and messages
	 * And an initial rule
	 * When applying rules to messages
	 * Then the number of messages matching the initial rule is returned
	 */
	@Test
	public void findProductOfFourCorningTileIds() {

		List<Tile> tiles = getTiles("input.txt");
		Day20 day20 = new Day20();
		Long product = day20.findProductOfFourCorningTileIds(tiles);
		Assert.assertTrue(product >= 0);
		System.out.println(product);
	}
	
	// Private
	
	private List<Tile> getTiles(String fileName) {
		
		List<Tile> tiles = ImmutableList.of();
		
		try {
			Path path = Paths.get(getClass().getClassLoader().getResource("com/adventureofcode/alejopj/day20/" + fileName).toURI());
			List<String> lines = Files.lines(path).collect(ImmutableList.toImmutableList());
			if (lines.isEmpty()) {
				Assert.fail();
			}
			List<Integer> tileIndexes = IntStream.range(0, lines.size()).boxed()
					.filter(i -> lines.get(i).contains("Tile"))
					.collect(ImmutableList.toImmutableList());
			tiles = IntStream.range(0, tileIndexes.size() - 1).boxed()
					.map(i -> getTile(lines.subList(tileIndexes.get(i), tileIndexes.get(i + 1) - 1)))
					.collect(ImmutableList.toImmutableList());
			tiles = ImmutableList.<Tile>builder()
					.addAll(tiles)
					.add(getTile(lines.subList(tileIndexes.get(tileIndexes.size() - 1), lines.size())))
					.build();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		
		return tiles;
	}

	private Tile getTile(List<String> lines) {
		
		Integer id = Integer.valueOf(lines.get(0).replace("Tile ", "").replace(":", ""));
		List<String> data = lines.subList(1, lines.size());
		
		return new Tile(id, data);
	}
	
}

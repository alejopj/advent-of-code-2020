package com.adventofcode.alejopj.day17;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.ImmutableList;

public class Day17Test {
	
	/**
	 * Given a cube initial configuration
	 * When cycling it for N times
	 * And N = 6
	 * Then the number of active cubes is returned
	 * And that number is 112
	 */
	@Test
	public void findNumberOfActiveCubesAfterCyclesIs112() {

		Map<Point3D, CubeStatus> cubes = getCubes("part1.txt");
		Day17 day17 = new Day17();
		Long activeCubes = day17.findNumberOfActiveCubesAfterCycles(cubes, 6);
		Assert.assertTrue(activeCubes == 112);
	}
	
	/**
	 * Given a cube initial configuration
	 * When cycling it for N times
	 * And N = 6
	 * Then the number of active cubes is returned
	 */
	@Test
	public void findNumberOfActiveCubesAfterCycles() {

		Map<Point3D, CubeStatus> cubes = getCubes("input.txt");
		Day17 day17 = new Day17();
		Long activeCubes = day17.findNumberOfActiveCubesAfterCycles(cubes, 6);
		Assert.assertTrue(activeCubes >= 0);
		System.out.println(activeCubes);
	}
	
	// Private
	
	private Map<Point3D, CubeStatus> getCubes(String fileName) {
		
		Map<Point3D, CubeStatus> cubes = new LinkedHashMap<>();
		
		try {
			Path path = Paths.get(getClass().getClassLoader().getResource("com/adventureofcode/alejopj/day17/" + fileName).toURI());
			List<String> lines = Files.lines(path).collect(ImmutableList.toImmutableList());
			if (lines.isEmpty()) {
				Assert.fail();
			}
			for (int x = 0; x < lines.size(); x++) {
				String line = lines.get(x);
				for (int y = 0; y < line.length(); y++) {
					CubeStatus status = CubeStatus.of(String.valueOf(line.charAt(y)));
					cubes.put(new Point3D(x, y, 0), status);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		
		return cubes;
	}
	
}

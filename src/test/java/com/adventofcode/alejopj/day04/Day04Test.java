package com.adventofcode.alejopj.day04;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.ImmutableList;

public class Day04Test {
	
	/**
	 * Given a list of passports
	 * When they are validated
	 * Then the number of valid passports is returned
	 * And that number is 2
	 */
	@Test
	public void findNumberOfValidPassportsIs2() {

		Day04 day04 = new Day04();
		List<Map<String, String>> passports = getPassports("part1.txt");
		Long numberOfValidPassports = day04.findNumberOfValidPassports(passports);
		Assert.assertTrue(numberOfValidPassports == 2);
	}

	/**
	 * Given a list of passports
	 * When they are validated
	 * Then the number of valid passports is returned
	 */
	@Test
	public void findNumberOfValidPassports() {

		Day04 day04 = new Day04();
		List<Map<String, String>> passports = getPassports("input.txt");
		Long numberOfValidPassports = day04.findNumberOfValidPassports(passports);
		Assert.assertTrue(numberOfValidPassports >= 0);
		System.out.println(numberOfValidPassports);
	}
	
	/**
	 * Given a list of passports
	 * When they are validated
	 * Then the number of valid passports is returned
	 * And that number is 2
	 */
	@Test
	public void findNumberOfPresentAndValidPassportsIs2() {

		Day04 day04 = new Day04();
		List<Map<String, String>> passports = getPassports("part2.txt");
		Long numberOfValidPassports = day04.findNumberOfPresentAndValidPassports(passports);
		Assert.assertTrue(numberOfValidPassports == 2);
	}
	
	/**
	 * Given a list of passports
	 * When they are validated
	 * Then the number of valid passports is returned
	 */
	@Test
	public void findNumberOfPresentAndValidPassports() {

		Day04 day04 = new Day04();
		List<Map<String, String>> passports = getPassports("input.txt");
		Long numberOfValidPassports = day04.findNumberOfPresentAndValidPassports(passports);
		Assert.assertTrue(numberOfValidPassports >= 0);
		System.out.println(numberOfValidPassports);
	}
	
	// Private
	
	private List<Map<String, String>> getPassports(String fileName) {
		
		List<Map<String, String>> passports = new ArrayList<>();
		
		try {
			Path path = Paths.get(getClass().getClassLoader().getResource("com/adventureofcode/alejopj/day04/" + fileName).toURI());
			List<String> lines = Files.lines(path).collect(ImmutableList.toImmutableList());
			if (lines.isEmpty()) {
				return passports;
			}
			List<Integer> emptyLineIndexes = new ArrayList<>();
			for (int i = 0; i < lines.size(); i++) {
				if (lines.get(i).trim().isEmpty()) {
					emptyLineIndexes.add(i);
				}
			}
			if (emptyLineIndexes.isEmpty()) {
				passports.add(getPassport(lines));
				return passports;
			}
			int fromIndex = 0;
			int toIndex = 0;
			for (int i = 0; i < emptyLineIndexes.size(); i++) {
				toIndex = emptyLineIndexes.get(i);
				passports.add(getPassport(lines.subList(fromIndex, toIndex)));
				fromIndex = toIndex + 1;
			}
			passports.add(getPassport(lines.subList(fromIndex, lines.size())));
		} catch (Exception e) {
			Assert.fail();
		}
		
		return passports;
	}
	
	private Map<String, String> getPassport(List<String> lines) {
		
		Map<String, String> passport = new LinkedHashMap<>();
		
		lines.stream().map(line -> ImmutableList.copyOf(line.split(" "))).flatMap(List::stream).forEach(field -> {
			String key = field.split(":")[0].trim();
			String value = field.split(":")[1].trim();
			passport.put(key, value);
		});
		
		return passport;
	}
	
}

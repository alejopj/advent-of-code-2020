package com.adventofcode.alejopj.day07;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.ImmutableList;

public class Day07Test {
	
	/**
	 * Given a list bag content rules
	 * When searching for how many of them can contain at least one shiny gold bag
	 * Then that number is returned
	 * And that number is 4
	 */
	@Test
	public void findNumberOfBagsCanContainAtLeastOneShinyGoldBagIs4() {

		Map<String, Map<String, Integer>> bagRules = getBagRules("part1.txt");
		Day07 day07 = new Day07(bagRules);
		Long numberOfBagsCanContainAtLeastOneShinyGoldBag = day07.findNumberOfBagsCanContainAtLeast("shiny gold", 1);
		Assert.assertTrue(numberOfBagsCanContainAtLeastOneShinyGoldBag == 4);
	}

	/**
	 * Given a list bag content rules
	 * When searching for how many of them can contain at least one shiny gold bag
	 * Then that number is returned
	 */
	@Test
	public void findNumberOfBagsCanContainAtLeastOneShinyGoldBag() {

		Map<String, Map<String, Integer>> bagRules = getBagRules("input.txt");
		Day07 day07 = new Day07(bagRules);
		Long numberOfBagsCanContainAtLeastOneShinyGoldBag = day07.findNumberOfBagsCanContainAtLeast("shiny gold", 1);
		Assert.assertTrue(numberOfBagsCanContainAtLeastOneShinyGoldBag >= 0);
		System.out.println(numberOfBagsCanContainAtLeastOneShinyGoldBag);
	}
	
	/**
	 * Given a list bag content rules
	 * When searching for how many individual bags are required inside a single shiny gold bag
	 * Then that number is returned
	 * And that number is 126
	 */
	@Test
	public void findNumberOfRequiredIndividualBagsInsideAShinyGoldBagIs126() {

		Map<String, Map<String, Integer>> bagRules = getBagRules("part2.txt");
		Day07 day07 = new Day07(bagRules);
		Integer numberOfBagsCanContainAtLeastOneShinyGoldBag = day07.findNumberOfRequiredIndividualBagsInside("shiny gold");
		Assert.assertTrue(numberOfBagsCanContainAtLeastOneShinyGoldBag == 126);
	}
	
	/**
	 * Given a list bag content rules
	 * When searching for how many individual bags are required inside a single shiny gold bag
	 * Then that number is returned
	 */
	@Test
	public void findNumberOfRequiredIndividualBagsInsideAShinyGoldBag() {

		Map<String, Map<String, Integer>> bagRules = getBagRules("input.txt");
		Day07 day07 = new Day07(bagRules);
		Integer numberOfBagsCanContainAtLeastOneShinyGoldBag = day07.findNumberOfRequiredIndividualBagsInside("shiny gold");
		Assert.assertTrue(numberOfBagsCanContainAtLeastOneShinyGoldBag >= 0);
		System.out.println(numberOfBagsCanContainAtLeastOneShinyGoldBag);
	}
	
	// Private
	
	private Map<String, Map<String, Integer>> getBagRules(String fileName) {
		
		Map<String, Map<String, Integer>> bagRules = new LinkedHashMap<>();
		
		try {
			Path path = Paths.get(getClass().getClassLoader().getResource("com/adventureofcode/alejopj/day07/" + fileName).toURI());
			List<String> lines = Files.lines(path).collect(ImmutableList.toImmutableList());
			for (String line : lines) {
				String[] bagAndContents = line.split("bags contain");
				String bag = bagAndContents[0].trim();
				String[] contents = bagAndContents[1].replaceAll("bags", "").replaceAll("bag", "").replaceAll("\\.", "").split(",");
				Map<String, Integer> bagContents = new LinkedHashMap<>();
				for (String content : contents) {
					if (content.contains("no other")) {
						break;
					}
					Integer units = Integer.valueOf(content.trim().split("\\D+")[0]);
					String color = content.trim().split("\\d+")[1].trim();
					bagContents.put(color, units);
				}
				bagRules.put(bag, bagContents);
			}
		} catch (Exception e) {
			Assert.fail();
		}

		return bagRules;
	}
	
}

package com.adventofcode.alejopj.day19;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

public class Day19Test {
	
	/**
	 * Given a list of rules and messages
	 * And an initial rule
	 * When applying rules to messages
	 * Then the number of messages matching the initial rule is returned
	 * And that number is 2
	 */
	@Test
	public void findNumberOfMessagesMatchingRule0Is2() {

		Map<List<Rule>, List<String>> rulesAndMessages = getRulesAndMessages("part1-1.txt");
		Day19 day19 = new Day19();
		Long number = day19.findNumberOfMessagesMatchingRule(rulesAndMessages, 0);
		Assert.assertTrue(number == 2);
	}
	
	/**
	 * Given a list of rules and messages
	 * And an initial rule
	 * When applying rules to messages
	 * Then the number of messages matching the initial rule is returned
	 * And that number is 2
	 */
	@Test
	public void findNumberOfMessagesMatchingRule0Is2Too() {

		Map<List<Rule>, List<String>> rulesAndMessages = getRulesAndMessages("part1-2.txt");
		Day19 day19 = new Day19();
		Long number = day19.findNumberOfMessagesMatchingRule(rulesAndMessages, 0);
		Assert.assertTrue(number == 2);
	}
	
	/**
	 * Given a list of rules and messages
	 * And an initial rule
	 * When applying rules to messages
	 * Then the number of messages matching the initial rule is returned
	 */
	@Test
	public void findNumberOfMessagesMatchingRule0() {

		Map<List<Rule>, List<String>> rulesAndMessages = getRulesAndMessages("input1.txt");
		Day19 day19 = new Day19();
		Long number = day19.findNumberOfMessagesMatchingRule(rulesAndMessages, 0);
		Assert.assertTrue(number >= 0);
		System.out.println(number);
	}
	
	/**
	 * Given a list of rules and messages
	 * And an initial rule
	 * And having no looping rules
	 * When applying rules to messages
	 * Then the number of messages matching the initial rule is returned
	 * And that number is 3
	 */
	@Test
	public void findNumberOfMessagesMatchingRule0Is3() {

		Map<List<Rule>, List<String>> rulesAndMessages = getRulesAndMessages("part2-1.txt");
		Day19 day19 = new Day19();
		Long number = day19.findNumberOfMessagesMatchingRule(rulesAndMessages, 0);
		Assert.assertTrue(number == 3);
	}
	
	/**
	 * Given a list of rules and messages
	 * And an initial rule
	 * And having looping rules
	 * When applying rules to messages
	 * Then the number of messages matching the initial rule is returned
	 * And that number is 12
	 */
	@Test
	public void findNumberOfMessagesMatchingRule0Is12() {

		Map<List<Rule>, List<String>> rulesAndMessages = getRulesAndMessages("part2-2.txt");
		Day19 day19 = new Day19();
		Long number = day19.findNumberOfMessagesMatchingRule(rulesAndMessages, 0);
		Assert.assertTrue(number == 12);
	}
	
	/**
	 * Given a list of rules and messages
	 * And an initial rule
	 * And having looping rules
	 * When applying rules to messages
	 * Then the number of messages matching the initial rule is returned
	 */
	@Test
	public void findNewNumberOfMessagesMatchingRule0() {

		Map<List<Rule>, List<String>> rulesAndMessages = getRulesAndMessages("input1.txt");
		Day19 day19 = new Day19();
		Long number = day19.findNumberOfMessagesMatchingRule(rulesAndMessages, 0);
		Assert.assertTrue(number >= 0);
		System.out.println(number);
	}
	
	// Private
	
	private Map<List<Rule>, List<String>> getRulesAndMessages(String fileName) {
		
		Map<List<Rule>, List<String>> rulesAndMessages = ImmutableMap.of();
		
		try {
			Path path = Paths.get(getClass().getClassLoader().getResource("com/adventureofcode/alejopj/day19/" + fileName).toURI());
			List<String> lines = Files.lines(path).collect(ImmutableList.toImmutableList());
			if (lines.isEmpty()) {
				Assert.fail();
			}
			Integer index = IntStream.range(0, lines.size() - 1).boxed()
					.filter(i -> lines.get(i).isBlank()).findFirst().orElse(0);
			List<Rule> rules = lines.subList(0, index).stream()
					.map(line -> getRule(line))
					.sorted((a, b) -> a.getId() - b.getId())
					.collect(ImmutableList.toImmutableList());
			List<String> messages = lines.subList(index + 1, lines.size());
			rulesAndMessages = ImmutableMap.of(rules, messages);
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		
		return rulesAndMessages;
	}
	
	private Rule getRule(String line) {
		
		Integer id = Integer.valueOf(line.split(":")[0]);
		String value = null;
		List<Integer> left = ImmutableList.of();
		List<Integer> right = ImmutableList.of();
		if (line.contains("\"")) {
			value = line.split(":")[1].split("\"")[1];
		} else {
			left = Arrays.stream(line.split(":")[1].trim().split("\\|")[0].trim().split(" "))
					.map(val -> Integer.valueOf(val))
					.collect(ImmutableList.toImmutableList());
			if (line.contains("|")) {
				right = Arrays.stream(line.split(":")[1].split("\\|")[1].trim().split(" "))
						.map(val -> Integer.valueOf(val))
						.collect(ImmutableList.toImmutableList());
			}
		}
		
		return new Rule(id, value, left, right);
	}
	
}

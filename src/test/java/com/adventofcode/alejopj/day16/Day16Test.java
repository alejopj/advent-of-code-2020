package com.adventofcode.alejopj.day16;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

public class Day16Test {
	
	/**
	 * Given a list of starting numbers
	 * When played to the most recently spoken number game
	 * Then the 2020th number is returned
	 * And that rate is 71
	 */
	@Test
	public void findTicketScanningErrorRateIs71() {

		Note note = getNote("part1.txt");
		Day16 day16 = new Day16();
		Long rate = day16.findTicketScanningErrorRate(note);
		Assert.assertTrue(rate == 71);
	}
	
	/**
	 * Given a list of starting numbers
	 * When played to the most recently spoken number game
	 * Then the 2020th number is returned
	 */
	@Test
	public void findTicketScanningErrorRate() {

		Note note = getNote("input.txt");
		Day16 day16 = new Day16();
		Long rate = day16.findTicketScanningErrorRate(note);
		Assert.assertTrue(rate >= 0);
		System.out.println(rate);
	}
	
	// Private
	
	private Note getNote(String fileName) {
		
		Map<String, String> rules = ImmutableMap.of();
		List<Integer> ticket = ImmutableList.of();
		List<List<Integer>> nearbyTickets = ImmutableList.of();
		
		try {
			Path path = Paths.get(getClass().getClassLoader().getResource("com/adventureofcode/alejopj/day16/" + fileName).toURI());
			List<String> lines = Files.lines(path).collect(ImmutableList.toImmutableList());
			if (lines.isEmpty()) {
				Assert.fail();
			}
			Integer ticketIndex = 0;
			Integer nearbyTicketsIndex = 0;
			for (int i = 0; i < lines.size(); i++) {
				if (lines.get(i).contains("your ticket")) {
					ticketIndex = i;
				} else if (lines.get(i).contains("nearby tickets")) {
					nearbyTicketsIndex = i;
					break;
				}
			}
			rules = getRules(lines, 0, ticketIndex - 1);
			ticket = getTicket(lines, ticketIndex + 1);
			nearbyTickets = getTickets(lines, nearbyTicketsIndex + 1, lines.size());
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		
		return new Note(rules, ticket, nearbyTickets);
	}

	private Map<String, String> getRules(List<String> lines, int fromIndex, int toIndex) {
		
		return lines.subList(fromIndex, toIndex).parallelStream()
				.collect(ImmutableMap.toImmutableMap(line -> getRuleName(line), line -> getRuleRegex(line)));
	}
	
	private String getRuleName(String line) {
		
		return line.split(":")[0].trim();
	}
	
	private String getRuleRegex(String line) {
		
		List<String> ranges = Arrays.stream(line.split(":")[1].split("or"))
				.map(range -> range.trim())
				.collect(ImmutableList.toImmutableList());
		
		return "[" + String.join("|", ranges) + "]";
	}
	
	private List<List<Integer>> getTickets(List<String> lines, int fromIndex, int toIndex) {
		
		return lines.subList(fromIndex, toIndex).parallelStream()
				.map(line -> getTicket(ImmutableList.of(line), 0))
				.collect(ImmutableList.toImmutableList());
	}
	
	private List<Integer> getTicket(List<String> lines, int fromIndex) {
		
		return Arrays.stream(lines.get(fromIndex).split(","))
				.map(value -> Integer.valueOf(value))
				.collect(ImmutableList.toImmutableList());
	}
	
}

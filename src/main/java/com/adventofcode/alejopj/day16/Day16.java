package com.adventofcode.alejopj.day16;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

public class Day16 {

	public Day16() {
		
	}

	public Integer findTicketScanningErrorRate(Note note) {
		
		List<Integer> invalidValues = new ArrayList<>();
		
		List<Range> ranges = note.getRules().values().parallelStream()
				.flatMap(List::stream)
				.collect(ImmutableList.toImmutableList());
		
		for (List<Integer> ticket : note.getNearbyTickets()) {
			for (Integer value : ticket) {
				if (ranges.parallelStream().noneMatch(range -> value >= range.getMin() && value <= range.getMax())) {
					invalidValues.add(value);
				}
			}
		}
		
		return invalidValues.parallelStream().mapToInt(value -> value).sum();
	}

	public Long findFieldsProduct(Note note, String ruleName) {
		
		List<Range> ranges = note.getRules().values().parallelStream()
				.flatMap(List::stream)
				.collect(ImmutableList.toImmutableList());
		
		List<List<Integer>> validTickets = note.getNearbyTickets().parallelStream()
				.filter(ticket -> ticket.parallelStream().allMatch(value ->
						ranges.parallelStream().anyMatch(range -> value >= range.getMin() && value <= range.getMax())))
				.collect(ImmutableList.toImmutableList());
		
		List<List<String>> rules = IntStream.range(0, validTickets.get(0).size()).boxed()
				.map(i -> getTicketFieldValues(validTickets, i))
				.map(values -> getRules(values, note.getRules()))
				.collect(ImmutableList.toImmutableList());
		
		Map<Integer, List<String>> rulesByField = new LinkedHashMap<>();
		for (int i = 0; i < rules.size(); i++) {
			rulesByField.put(i, rules.get(i));
		}
		
		while (rulesByField.values().stream().anyMatch(r -> r.size() > 1)) {
			
			List<String> knownFields = rulesByField.values().parallelStream()
					.filter(value -> value.size() == 1)
					.flatMap(List::stream)
					.collect(ImmutableList.toImmutableList());
			
			rulesByField.values().forEach(value -> {
				if (value.size() > 1) {
					value.removeAll(knownFields);
				}
			});
		}
		
		Map<Integer, List<String>> matchingRulesByField = rulesByField.entrySet().parallelStream()
				.filter(entry -> entry.getValue().get(0).startsWith(ruleName))
				.collect(ImmutableMap.toImmutableMap(entry -> entry.getKey(), entry -> entry.getValue()));
		
		return matchingRulesByField.keySet().stream()
				.mapToLong(field -> note.getTicket().get(field))
				.reduce((a, b) -> a * b).orElse(0);
	}
	
	// Private
	
	private List<Integer> getTicketFieldValues(List<List<Integer>> tickets, Integer field) {
		
		return tickets.parallelStream().map(ticket -> ticket.get(field)).collect(ImmutableList.toImmutableList());
	}
	
	private List<String> getRules(List<Integer> fieldValues, Map<String, List<Range>> rules) {
		
		return fieldValues.parallelStream()
				.map(value -> getRules(value, rules))
				.reduce((a, b) -> getIntersection(a, b)).orElse(new ArrayList<>());
	}
	
	private List<String> getRules(Integer fieldValue, Map<String, List<Range>> rules) {
		
		return rules.entrySet().parallelStream()
				.filter(entry -> entry.getValue().parallelStream()
						.anyMatch(range -> fieldValue >= range.getMin() && fieldValue <= range.getMax()))
				.map(entry -> entry.getKey())
				.collect(ImmutableList.toImmutableList());
	}
	
	private <T> List<T> getIntersection(List<T> a, List<T> b) {
		
		return a.stream()
                .filter(b::contains)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
	}
	
}

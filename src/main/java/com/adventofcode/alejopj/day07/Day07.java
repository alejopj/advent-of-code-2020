package com.adventofcode.alejopj.day07;

import java.util.Map;

public class Day07 {

	private final Map<String, Map<String, Integer>> bagRules;
	
	public Day07(Map<String, Map<String, Integer>> bagRules) {
		this.bagRules = bagRules;
	}
	
	public Long findNumberOfBagsCanContainAtLeast(String bagContent, Integer units) {

		return bagRules.entrySet().parallelStream()
				.filter(bagRule -> canContainAtLeast(bagRules.get(bagRule.getKey()), bagContent, units)).count();
	}
	
	public Integer findNumberOfRequiredIndividualBagsInside(String bag) {
		if (!bagRules.containsKey(bag)) {
			return 0;
		}
		return bagRules.get(bag).entrySet().stream()
				.mapToInt(content -> content.getValue() + content.getValue() * findNumberOfRequiredIndividualBagsInside(content.getKey()))
				.sum();
	}
	
	// Private
	
	private boolean canContainAtLeast(Map<String, Integer> bagContents, String bagContent, Integer units) {

		return bagContents != null
				&& ((bagContents.containsKey(bagContent) && bagContents.get(bagContent) >= units)
						|| (bagContents != null && bagContents.keySet().parallelStream()
								.anyMatch(content -> canContainAtLeast(bagRules.get(content), bagContent, units))));
	}

}

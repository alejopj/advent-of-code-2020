package com.adventofcode.alejopj.day10;

import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableList;

public class Day10 {

	public Day10() {
		
	}
	
	public Integer findProductOf1JoltDiffBy3JoltDiff(List<Integer> joltageRatings, Integer initialJoltageRating,
			List<Integer> allowedInputJoltageDiffs, Integer finalJoltageDiff) {
		
		List<Integer> sortedJoltageRatings = joltageRatings.parallelStream().sorted().collect(Collectors.toList());
		sortedJoltageRatings.add(0, initialJoltageRating);
		sortedJoltageRatings.add(sortedJoltageRatings.get(sortedJoltageRatings.size() - 1) + finalJoltageDiff);
		Integer oneJoltDiffs = 0;
		Integer threeJoltDiffs = 0;
		for (int i = 1; i < sortedJoltageRatings.size(); i++) {
			Integer joltageDiff = sortedJoltageRatings.get(i) - sortedJoltageRatings.get(i - 1);
			if (!allowedInputJoltageDiffs.contains(joltageDiff)) {
				break;
			}
			switch (joltageDiff) {
				case 1:
					oneJoltDiffs++;
					break;
				case 3:
					threeJoltDiffs++;
					break;
				default:
					break;
			}
		}
		
		return oneJoltDiffs * threeJoltDiffs;
	}

	public Long findWaysToConnectChargingOutletToDevice(List<Integer> joltageRatings, Integer initialJoltageRating,
			List<Integer> allowedInputJoltageDiffs, Integer finalJoltageDiff) {
		
		List<Integer> sortedJoltageRatings = joltageRatings.parallelStream().sorted().collect(Collectors.toList());
		sortedJoltageRatings.add(0, initialJoltageRating);
		sortedJoltageRatings.add(sortedJoltageRatings.get(sortedJoltageRatings.size() - 1) + finalJoltageDiff);
		
		return findWaysToConnectChargingOutletToDevice(sortedJoltageRatings, allowedInputJoltageDiffs);
	}
	
	private Long findWaysToConnectChargingOutletToDevice(List<Integer> joltageRatings, List<Integer> allowedInputJoltageDiffs) {
		
		if (joltageRatings.size() == 1) {
			return 1L;
		}
		List<Integer> nextJotalgeRatings = joltageRatings.parallelStream()
				.filter(rating -> allowedInputJoltageDiffs.contains(rating - joltageRatings.get(0)))
				.collect(ImmutableList.toImmutableList());
		return nextJotalgeRatings.parallelStream().mapToLong(joltageRating ->
				findWaysToConnectChargingOutletToDevice(joltageRatings.subList(joltageRatings.indexOf(joltageRating),
						joltageRatings.size()), allowedInputJoltageDiffs)).sum();
	}
	
}

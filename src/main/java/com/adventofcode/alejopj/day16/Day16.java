package com.adventofcode.alejopj.day16;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableList;

public class Day16 {

	public Day16() {
		
	}

	public Long findTicketScanningErrorRate(Note note) {
		
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
		
		return (long) invalidValues.parallelStream().mapToInt(value -> value).sum();
	}
	
}

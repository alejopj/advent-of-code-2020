package com.adventofcode.alejopj.day13;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Day13 {

	public Day13() {
		
	}

	public Integer findProductOfEarliestBusAndWaitingTime(Map<Integer, List<Integer>> departureAndBuses) {
		
		Integer departure = departureAndBuses.keySet().iterator().next();
		List<Integer> buses = departureAndBuses.values().iterator().next();
		Integer bus = buses.parallelStream()
				.filter(b -> Integer.MIN_VALUE != b)
				.reduce((a, b) ->
					   (a * (departure / a + (departure % a == 0 ? 0 : 1)) - departure)
					<= (b * (departure / b + (departure % b == 0 ? 0 : 1)) - departure) ? a : b)
				.orElse(null);
		Integer waitingTime = bus * (departure / bus + (departure % bus == 0 ? 0 : 1)) - departure;
		return bus * waitingTime;
	}

	public Long findEarliestTimeMatchingBusesAndPositions(Long initialCandidate,
			Map<Integer, List<Integer>> departureAndBuses) {
		
		Long time = 0L;
		
		List<Integer> buses = departureAndBuses.values().iterator().next();
		Map<Integer, Integer> busByPosition = new LinkedHashMap<>();
		for (int i = 0; i < buses.size(); i++) {
			if (buses.get(i) != Integer.MIN_VALUE) {
				busByPosition.put(i, buses.get(i));
			}
		}
		Long candidate = initialCandidate > 0 ? initialCandidate : 1;
		boolean isLeastCommonMultiple = true;
		Integer position = null;
		Integer bus = null;
		for (long i = candidate; i <= Long.MAX_VALUE; i++) {
			isLeastCommonMultiple = true;
			position = null;
			bus = null;
			for (Entry<Integer, Integer> entry : busByPosition.entrySet()) {
				position = entry.getKey();
				bus = entry.getValue();
				isLeastCommonMultiple &= (((i + position) % bus) == 0);
				if (!isLeastCommonMultiple) {
					break;
				}
			}
			if (isLeastCommonMultiple) {
				time = i;
				break;
			}
		}
		
		return time;
	}
	
}

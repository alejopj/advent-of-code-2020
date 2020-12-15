package com.adventofcode.alejopj.day15;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.ImmutableList;

public class Day15 {

	public Day15() {
		
	}

	public Integer findNthSpokenNumber(List<Integer> startingNumbers, Integer n) {
		
		Map<Integer, Integer> spokenNumberByTurn = new LinkedHashMap<>();
		
		for (int i = 0; i < startingNumbers.size(); i++) {
			
			spokenNumberByTurn.put(i, startingNumbers.get(i));
		}
		
		for (int i = startingNumbers.size(); i < n; i++) {
			
			Integer lastSpokenTurn = null;
			Integer lastSpokenNumber = null;
			Iterator<Entry<Integer,Integer>> iterator = spokenNumberByTurn.entrySet().iterator();
			while (iterator.hasNext()) {
				Entry<Integer, Integer> entry = iterator.next();
				lastSpokenTurn = entry.getKey();
				lastSpokenNumber = entry.getValue();
			};
			Map<Integer, Integer> previouslySpokenNumberByTurn = new LinkedHashMap<>(spokenNumberByTurn);
			previouslySpokenNumberByTurn.remove(lastSpokenTurn);
			Integer previouslySpokenNumberTurn = null;
			
			List<Entry<Integer, Integer>> entries = ImmutableList.copyOf(previouslySpokenNumberByTurn.entrySet()).reverse();
			for (Entry<Integer, Integer> entry : entries) {
				
				Integer spokenNumber = entry.getValue();
				if (spokenNumber.equals(lastSpokenNumber)) {
					previouslySpokenNumberTurn = entry.getKey();
					break;
				}
			}
			
			if (previouslySpokenNumberTurn == null) {
				lastSpokenNumber = 0;
			} else {
				lastSpokenNumber = lastSpokenTurn - previouslySpokenNumberTurn;
			}
			
			spokenNumberByTurn.put(i, lastSpokenNumber);
			
			if (previouslySpokenNumberTurn != null) {
				spokenNumberByTurn.remove(previouslySpokenNumberTurn);
			}
		}
		
		Integer lastSpokenNumber = null;
		Iterator<Integer> iterator = spokenNumberByTurn.values().iterator();
		while (iterator.hasNext()) { lastSpokenNumber = iterator.next(); };
		return lastSpokenNumber;
	}
	
}

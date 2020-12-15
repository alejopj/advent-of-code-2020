package com.adventofcode.alejopj.day15;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Day15 {

	public Day15() {
		
	}

	public Integer findNthSpokenNumber(List<Integer> startingNumbers, Integer n) {
		
		Map<Integer, Integer> spokenNumberByTurn = new LinkedHashMap<>();
		
		for (int i = 0; i < startingNumbers.size(); i++) {
			
			spokenNumberByTurn.put(i, startingNumbers.get(i));
		}
		
		while (spokenNumberByTurn.size() < n) {
			
			Map<Integer, Integer> previouslySpokenNumberByTurn = new LinkedHashMap<>(spokenNumberByTurn);
			previouslySpokenNumberByTurn.remove(spokenNumberByTurn.size() - 1);
			Integer previouslySpokenNumberTurn = null;
			Integer lastSpokenNumber = spokenNumberByTurn.get(spokenNumberByTurn.size() - 1);
			
			for (int i = previouslySpokenNumberByTurn.size() - 1; i >= 0; i--) {
				
				Integer spokenNumber = previouslySpokenNumberByTurn.get(i);
				if (spokenNumber.equals(lastSpokenNumber)) {
					previouslySpokenNumberTurn = i;
					break;
				}
			}
			if (previouslySpokenNumberTurn == null) {
				lastSpokenNumber = 0;
			} else {
				lastSpokenNumber = spokenNumberByTurn.size() - 1 - previouslySpokenNumberTurn;
			}
			spokenNumberByTurn.put(spokenNumberByTurn.size(), lastSpokenNumber);
		}
		return spokenNumberByTurn.get(n - 1);
	}
	
}

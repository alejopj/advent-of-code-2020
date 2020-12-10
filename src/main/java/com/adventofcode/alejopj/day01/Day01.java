package com.adventofcode.alejopj.day01;

import java.util.List;

import com.google.common.collect.ImmutableList;

public class Day01 {

	public Day01() {
		
	}
	
	public List<Integer> findTwoNumbersSum2020(List<Integer> numbers) {
		
		for (int i = 0; i < numbers.size(); i++) {
			for (int j = 0; j < numbers.size(); j++) {
				Integer a = numbers.get(i);
				Integer b = numbers.get(j);
				if (a + b == 2020) {
					return ImmutableList.of(a, b);
				}
			}
		}
		
		return ImmutableList.of();
	}
	
	public List<Integer> findThreeNumbersSum2020(List<Integer> numbers) {
		
		for (int i = 0; i < numbers.size(); i++) {
			for (int j = 0; j < numbers.size(); j++) {
				for (int k = 0; k < numbers.size(); k++) {
					Integer a = numbers.get(i);
					Integer b = numbers.get(j);
					Integer c = numbers.get(k);
					if (a + b + c == 2020) {
						return ImmutableList.of(a, b, c);
					}
				}
			}
		}
		
		return ImmutableList.of();
	}
}

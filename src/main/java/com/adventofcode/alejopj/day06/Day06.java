package com.adventofcode.alejopj.day06;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.common.collect.ImmutableList;

public class Day06 {

	public Day06() {
		
	}
	
	public Integer findSumOfAffirmativeGroupalAnswers(List<List<String>> answers) {
		
		List<Integer> numberOfAffirmativeGroupalAnswers = getNumberOfAffirmativeGroupalAnswers(answers);
		return numberOfAffirmativeGroupalAnswers.parallelStream().mapToInt(number -> number).sum();
	}
	
	public Integer findSumOfMatchingAffirmativeGroupalAnswers(List<List<String>> answers) {
		
		List<Integer> numberOfMatchingAffirmativeGroupalAnswers = getNumberOfMatchingAffirmativeGroupalAnswers(answers);
		return numberOfMatchingAffirmativeGroupalAnswers.parallelStream().mapToInt(number -> number).sum();
	}
	
	// Private
	
	private List<Integer> getNumberOfAffirmativeGroupalAnswers(List<List<String>> answers) {

		return answers.parallelStream()
				.map(groupAnswers -> getNumberOfAffirmativeGroupAnswers(groupAnswers))
				.collect(ImmutableList.toImmutableList());
	}
	
	private Integer getNumberOfAffirmativeGroupAnswers(List<String> groupAnswers) {
		
		Set<String> distinctAnwers = new HashSet<>();
		for (String answer : groupAnswers) {
			char[] questions = answer.toLowerCase().toCharArray();
			for (char q : questions) {
				distinctAnwers.add(String.valueOf(q));
			}
		}
		return distinctAnwers.size();
	}
	
	private List<Integer> getNumberOfMatchingAffirmativeGroupalAnswers(List<List<String>> answers) {

		return answers.parallelStream()
				.map(groupAnswers -> getNumberOfMatchingAffirmativeGroupAnswers(groupAnswers))
				.collect(ImmutableList.toImmutableList());
	}
	
	private Integer getNumberOfMatchingAffirmativeGroupAnswers(List<String> groupAnswers) {
		
		List<String> intersection = groupAnswers.parallelStream()
				.map(answer -> answer.toLowerCase().toCharArray())
				.map(answer -> {
					List<String> stringifiedAnwer = new ArrayList<>();
					for (int i = 0; i < answer.length; i++) {
						stringifiedAnwer.add(String.valueOf(answer[i]));
					}
					return stringifiedAnwer;
				})
				.reduce((a, b) -> intersection(a, b)).orElse(ImmutableList.of());
		
		return intersection.size();
	}
	
	public <T> List<T> intersection(List<T> a, List<T> b) {
		
	    return a.stream().filter(b::contains).distinct().collect(ImmutableList.toImmutableList());
	}
	
}

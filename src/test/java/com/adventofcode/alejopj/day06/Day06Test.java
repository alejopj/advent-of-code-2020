package com.adventofcode.alejopj.day06;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.ImmutableList;

public class Day06Test {
	
	/**
	 * Given a list of custom declaration from groupal anwsers
	 * When they are counted
	 * Then the sum of all of them is returned
	 * And that sum is 11
	 */
	@Test
	public void findNumberOfAffirmativeGroupalAnswersIs11() {

		Day06 day06 = new Day06();
		List<List<String>> answers = getCustomDeclarationFormGroupalAnswers("part1.txt");
		Integer sumOfAffirmativeGroupalAnswers = day06.findSumOfAffirmativeGroupalAnswers(answers);
		Assert.assertTrue(sumOfAffirmativeGroupalAnswers == 11);
	}

	/**
	 * Given a list of custom declaration from groupal anwsers
	 * When they are counted
	 * Then the sum of all of them is returned
	 */
	@Test
	public void findNumberOfAffirmativeGroupalAnswers() {

		Day06 day06 = new Day06();
		List<List<String>> answers = getCustomDeclarationFormGroupalAnswers("input.txt");
		Integer sumOfAffirmativeGroupalAnswers = day06.findSumOfAffirmativeGroupalAnswers(answers);
		Assert.assertTrue(sumOfAffirmativeGroupalAnswers >= 0);
		System.out.println(sumOfAffirmativeGroupalAnswers);
	}
	
	/**
	 * Given a list of custom declaration from groupal anwsers
	 * When they are counted
	 * Then the sum of all of them is returned
	 * And that sum is 6
	 */
	@Test
	public void findNumberOfMatchingAffirmativeGroupalAnswersIs6() {

		Day06 day06 = new Day06();
		List<List<String>> answers = getCustomDeclarationFormGroupalAnswers("part2.txt");
		Integer sumOfAffirmativeGroupalAnswers = day06.findSumOfMatchingAffirmativeGroupalAnswers(answers);
		Assert.assertTrue(sumOfAffirmativeGroupalAnswers == 6);
	}
	
	/**
	 * Given a list of custom declaration from groupal anwsers
	 * When they are counted
	 * Then the sum of all of them is returned
	 */
	@Test
	public void findNumberOfMatchingAffirmativeGroupalAnswers() {

		Day06 day06 = new Day06();
		List<List<String>> answers = getCustomDeclarationFormGroupalAnswers("input.txt");
		Integer sumOfAffirmativeGroupalAnswers = day06.findSumOfMatchingAffirmativeGroupalAnswers(answers);
		Assert.assertTrue(sumOfAffirmativeGroupalAnswers >= 0);
		System.out.println(sumOfAffirmativeGroupalAnswers);
	}
	
	// Private
	
	private List<List<String>> getCustomDeclarationFormGroupalAnswers(String fileName) {
		
		List<List<String>> answers = new ArrayList<>();
		
		try {
			Path path = Paths.get(getClass().getClassLoader().getResource("com/adventureofcode/alejopj/day06/" + fileName).toURI());
			List<String> lines = Files.lines(path).collect(ImmutableList.toImmutableList());
			if (lines.isEmpty()) {
				return answers;
			}
			List<String> groupAnwsers = new ArrayList<>();
			for (String line : lines) {
				if (line == null || line.trim().isEmpty()) {
					answers.add(groupAnwsers);
					groupAnwsers = new ArrayList<>();
				} else {
					groupAnwsers.add(line);
				}
			}
			if (!answers.contains(groupAnwsers)) {
				answers.add(groupAnwsers);
			}
		} catch (Exception e) {
			Assert.fail();
		}
		
		return answers;
	}
	
}

package com.adventofcode.alejopj.day18;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.ImmutableList;

public class Day18Test {
	
	/**
	 * Given a list of expressions
	 * When evaluated
	 * Then the sum of their results is returned
	 */
	@Test
	public void findSumOfExpressionsIs() {

		List<String> expressions = getTrimmedExpressions("1 + 2 * 3 + 4 * 5 + 6");
		Day18 day18 = new Day18();
		Long sum = day18.findSumOfExpressions(expressions);
		Assert.assertTrue(sum == 71);
		expressions = getTrimmedExpressions("1 + (2 * 3) + (4 * (5 + 6))");
		sum = day18.findSumOfExpressions(expressions);
		Assert.assertTrue(sum == 51);
		expressions = getTrimmedExpressions("2 * 3 + (4 * 5)");
		sum = day18.findSumOfExpressions(expressions);
		Assert.assertTrue(sum == 26);
		expressions = getTrimmedExpressions("5 + (8 * 3 + 9 + 3 * 4 * 3)");
		sum = day18.findSumOfExpressions(expressions);
		Assert.assertTrue(sum == 437);
		expressions = getTrimmedExpressions("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))");
		sum = day18.findSumOfExpressions(expressions);
		Assert.assertTrue(sum == 12240);
		expressions = getTrimmedExpressions("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2");
		sum = day18.findSumOfExpressions(expressions);
		Assert.assertTrue(sum == 13632);
	}
	
	/**
	 * Given a list of expressions
	 * When evaluated
	 * Then the sum of their results is returned
	 */
	@Test
	public void findSumOfExpressions() {

		List<String> expressions = getExpressions("input.txt");
		Day18 day18 = new Day18();
		Long sum = day18.findSumOfExpressions(expressions);
		Assert.assertTrue(sum >= 0);
		System.out.println(sum);
	}
	
	// Private
	
	private List<String> getTrimmedExpressions(String expression) {
		
		return ImmutableList.of(expression);
	}
	
	private List<String> getExpressions(String fileName) {
		
		List<String> expressions = ImmutableList.of();
		
		try {
			Path path = Paths.get(getClass().getClassLoader().getResource("com/adventureofcode/alejopj/day18/" + fileName).toURI());
			List<String> lines = Files.lines(path).collect(ImmutableList.toImmutableList());
			if (lines.isEmpty()) {
				Assert.fail();
			}
			expressions = lines.parallelStream().map(line -> line.replace(" ", "")).collect(ImmutableList.toImmutableList());
			
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		
		return expressions;
	}
	
}

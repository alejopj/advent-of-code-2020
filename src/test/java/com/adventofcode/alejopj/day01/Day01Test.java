package com.adventofcode.alejopj.day01;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.adventofcode.alejopj.day01.Day01;
import com.google.common.collect.ImmutableList;

public class Day01Test {

	/**
	 * Given a list of numbers
	 * When searching for two of them summing 2020
	 * Then those numbers are returned
	 * And first of them is 1721
	 * And second one is 299
	 * And they sum 2020
	 * And their product is 514579
	 */
	@Test
	public void findTwoNumbersSum2020AndProduct514579() {
		try {
			Day01 day01 = new Day01();
			Path path = Paths.get(getClass().getClassLoader().getResource("com/adventureofcode/alejopj/day01/part1.txt").toURI());
			List<Integer> numbers = Files.lines(path).map(line -> Integer.valueOf(line)).collect(ImmutableList.toImmutableList());
			List<Integer> numbersSum2020 = day01.findTwoNumbersSum2020(numbers);
			Assert.assertEquals(numbersSum2020.size(), 2);
			Integer a = numbersSum2020.get(0);
			Integer b = numbersSum2020.get(1);
			Assert.assertTrue(a == 1721);
			Assert.assertTrue(b == 299);
			Assert.assertEquals(a + b, 2020);
			Assert.assertEquals(a * b, 514579);
		} catch (Exception e) {
			Assert.fail();
		}
	}
	
	/**
	 * Given a list of numbers
	 * When two of them sum 2020
	 * Then those numbers are returned
	 * And they sum 2020
	 */
	@Test
	public void findTwoNumbersSum2020AndProduct241861950() {
		try {
			Day01 day01 = new Day01();
			Path path = Paths.get(getClass().getClassLoader().getResource("com/adventureofcode/alejopj/day01/input.txt").toURI());
			List<Integer> numbers = Files.lines(path).map(line -> Integer.valueOf(line)).collect(ImmutableList.toImmutableList());
			List<Integer> numbersSum2020 = day01.findTwoNumbersSum2020(numbers);
			Assert.assertEquals(numbersSum2020.size(), 2);
			Integer a = numbersSum2020.get(0);
			Integer b = numbersSum2020.get(1);
			Assert.assertNotNull(a);
			Assert.assertNotNull(b);
			Assert.assertEquals(a + b, 2020);
			System.out.println(a * b);
		} catch (Exception e) {
			Assert.fail();
		}
	}
	
	/**
	 * Given a list of numbers
	 * When searching for three of them summing 2020
	 * Then those numbers are returned
	 * And first of them is 1721
	 * And second one is 299
	 * And they sum 2020
	 * And their product is 241861950
	 */
	@Test
	public void findThreeNumbersSum2020AndProduct241861950() {
		try {
			Path path = Paths.get(getClass().getClassLoader().getResource("com/adventureofcode/alejopj/day01/part2.txt").toURI());
			List<Integer> numbers = Files.lines(path).map(line -> Integer.valueOf(line)).collect(ImmutableList.toImmutableList());
			Day01 day01 = new Day01();
			List<Integer> numbersSum2020 = day01.findThreeNumbersSum2020(numbers);
			Assert.assertEquals(numbersSum2020.size(), 3);
			Integer a = numbersSum2020.get(0);
			Integer b = numbersSum2020.get(1);
			Integer c = numbersSum2020.get(2);
			Assert.assertTrue(a == 979);
			Assert.assertTrue(b == 366);
			Assert.assertTrue(c == 675);
			Assert.assertEquals(a + b + c, 2020);
			Assert.assertEquals(a * b * c, 241861950);
		} catch (Exception e) {
			Assert.fail();
		}
	}
	
	/**
	 * Given a list of numbers
	 * When three of them sum 2020
	 * Then those numbers are returned
	 * And they sum 2020
	 */
	@Test
	public void findThreeNumbersSum2020() {
		try {
			Path path = Paths.get(getClass().getClassLoader().getResource("com/adventureofcode/alejopj/day01/input.txt").toURI());
			List<Integer> numbers = Files.lines(path).map(line -> Integer.valueOf(line)).collect(ImmutableList.toImmutableList());
			Day01 day01 = new Day01();
			List<Integer> numbersSum2020 = day01.findThreeNumbersSum2020(numbers);
			Assert.assertEquals(numbersSum2020.size(), 3);
			Integer a = numbersSum2020.get(0);
			Integer b = numbersSum2020.get(1);
			Integer c = numbersSum2020.get(2);
			Assert.assertEquals(a + b + c, 2020);
			System.out.println(a * b * c);
		} catch (Exception e) {
			Assert.fail();
		}
	}
}

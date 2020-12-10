package com.adventofcode.alejopj.day09;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.ImmutableList;

public class Day09Test {
	
	/**
	 * Given a list of eXchange-Masking Addition System (XMAS) data
	 * And N = 5
	 * When checked for the first number that is not the sum of two of the N previous
	 * Then that number is returned
	 * And that number is 127
	 */
	@Test
	public void findFirstNumberIsNotSumOfAPairOfPreambleNumbersIs127() {

		List<Long> data = getData("part1.txt");
		Day09 day09 = new Day09();
		Long firstNumberIsNotSumOfAPairOfPreambleNumbers =
				day09.findFirstNumberIsNotSumOfAPairOfPreambleNumbers(data, 5);
		Assert.assertNotNull(firstNumberIsNotSumOfAPairOfPreambleNumbers);
		Assert.assertTrue(firstNumberIsNotSumOfAPairOfPreambleNumbers == 127);
	}

	/**
	 * Given a list of eXchange-Masking Addition System (XMAS) data
	 * And N = 25
	 * When checked for the first number that is not the sum of two of the N previous
	 * Then that number is returned
	 */
	@Test
	public void findFirstNumberIsNotSumOfAPairOfPreambleNumbers() {

		List<Long> data = getData("input.txt");
		Day09 day09 = new Day09();
		Long firstNumberIsNotSumOfAPairOfPreambleNumbers =
				day09.findFirstNumberIsNotSumOfAPairOfPreambleNumbers(data, 25);
		Assert.assertNotNull(firstNumberIsNotSumOfAPairOfPreambleNumbers);
		System.out.println(firstNumberIsNotSumOfAPairOfPreambleNumbers);
	}
	
	/**
	 * Given a list of eXchange-Masking Addition System (XMAS) data
	 * And N = 5
	 * When searching for the weakness
	 * Then the weakness is returned
	 * And that weakness is 62
	 */
	@Test
	public void findEncryptionWeaknessIs62() {

		List<Long> data = getData("part2.txt");
		Day09 day09 = new Day09();
		Long invalidNumber =
				day09.findFirstNumberIsNotSumOfAPairOfPreambleNumbers(data, 5);
		Assert.assertNotNull(invalidNumber);
		Long encryptionWeakness = day09.findEncryptionWeakness(data, invalidNumber);
		Assert.assertNotNull(encryptionWeakness);
		Assert.assertTrue(encryptionWeakness == 62);
	}
	
	/**
	 * Given a list of eXchange-Masking Addition System (XMAS) data
	 * And N = 25
	 * When searching for the weakness
	 * Then the weakness is returned
	 */
	@Test
	public void findEncryptionWeakness() {

		List<Long> data = getData("input.txt");
		Day09 day09 = new Day09();
		Long invalidNumber =
				day09.findFirstNumberIsNotSumOfAPairOfPreambleNumbers(data, 25);
		Assert.assertNotNull(invalidNumber);
		Long encryptionWeakness = day09.findEncryptionWeakness(data, invalidNumber);
		Assert.assertNotNull(encryptionWeakness);
		System.out.println(encryptionWeakness);
	}
	
	// Private
	
	private List<Long> getData(String fileName) {
		
		List<Long> data = ImmutableList.of();
		
		try {
			Path path = Paths.get(getClass().getClassLoader().getResource("com/adventureofcode/alejopj/day09/" + fileName).toURI());
			List<String> lines = Files.lines(path).collect(ImmutableList.toImmutableList());
			data = lines.parallelStream().map(line -> Long.valueOf(line)).collect(ImmutableList.toImmutableList());
		} catch (Exception e) {
			Assert.fail();
		}
		
		return data;
	}
	
}

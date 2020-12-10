package com.adventofcode.alejopj.day02;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;

public class Day02Test {

	/**
	 * Given a list of policy/password pairs
	 * When each pair is matched
	 * Then total number of matching pairs is returned
	 * And that number is 2
	 */
	@Test
	public void findNumberOfValidPasswordsIs2() {

		Day02 day02 = new Day02();
		Map<PasswordPolicy, Collection<String>> passwordsByPolicy = getPasswordsByPolicy("part1.txt");
		Integer numberOfValidPasswords = day02.findNumberOfValidPasswords(passwordsByPolicy);
		Assert.assertTrue(numberOfValidPasswords == 2);
	}
	
	/**
	 * Given a list of policy/password pairs
	 * When each pair is matched
	 * Then total number of matching pairs is returned
	 */
	@Test
	public void findNumberOfValidPasswords() {

		Day02 day02 = new Day02();
		Map<PasswordPolicy, Collection<String>> passwordsByPolicy = getPasswordsByPolicy("input.txt");
		Integer numberOfValidPasswords = day02.findNumberOfValidPasswords(passwordsByPolicy);
		Assert.assertTrue(numberOfValidPasswords >= 0);
		System.out.println(numberOfValidPasswords);
	}
	
	/**
	 * Given a list of policy/password pairs
	 * When each pair is matched
	 * Then total number of matching pairs is returned
	 * And that number is 1
	 */
	@Test
	public void findNewNumberOfValidPasswordsIs2() {

		Day02 day02 = new Day02();
		Map<PasswordPolicy, Collection<String>> passwordsByPolicy = getPasswordsByPolicy("part2.txt");
		Integer numberOfValidPasswords = day02.findNewNumberOfValidPasswords(passwordsByPolicy);
		Assert.assertTrue(numberOfValidPasswords == 1);
	}
	
	/**
	 * Given a list of policy/password pairs
	 * When each pair is matched
	 * Then total number of matching pairs is returned
	 */
	@Test
	public void findNewNumberOfValidPasswords() {

		Day02 day02 = new Day02();
		Map<PasswordPolicy, Collection<String>> passwordsByPolicy = getPasswordsByPolicy("input.txt");
		Integer numberOfValidPasswords = day02.findNewNumberOfValidPasswords(passwordsByPolicy);
		Assert.assertTrue(numberOfValidPasswords >= 0);
		System.out.println(numberOfValidPasswords);
	}
	
	// Private
	
	private Map<PasswordPolicy, Collection<String>> getPasswordsByPolicy(String fileName) {
		
		Multimap<PasswordPolicy, String> passwordsByPolicy = LinkedListMultimap.create();
		try {
			Path path = Paths.get(getClass().getClassLoader().getResource("com/adventureofcode/alejopj/day02/" + fileName).toURI());
			Files.lines(path).forEach(line -> {
				PasswordPolicy policy = getPolicy(line);
				String password = getPassword(line);
				passwordsByPolicy.put(policy, password);
			});
		} catch (Exception e) {
			Assert.fail();
		}
		
		return passwordsByPolicy.asMap();
	}
	
	private PasswordPolicy getPolicy(String policyAndPassword) {
		
		String policy = policyAndPassword.split(":")[0];
		Integer min = Integer.valueOf(policy.split("-")[0]);
		Integer max = Integer.valueOf(policy.split("-")[1].split(" ")[0]);
		String character = policy.split("-")[1].split(" ")[1];
		return new PasswordPolicy(min, max, character);
	}
	
	private String getPassword(String policyAndPassword) {
		
		return policyAndPassword.split(":")[1].trim();
	}
}

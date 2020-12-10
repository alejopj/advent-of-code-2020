package com.adventofcode.alejopj.day02;

import java.util.Collection;
import java.util.Map;

public class Day02 {

	public Day02() {
		
	}
	
	public Integer findNumberOfValidPasswords(Map<PasswordPolicy, Collection<String>> passwordsByPolicy) {
		
		return passwordsByPolicy.entrySet().parallelStream().mapToInt(entry ->
				findNumberOfValidPasswords(entry.getKey(), entry.getValue())).sum();
	}
	
	public Integer findNewNumberOfValidPasswords(Map<PasswordPolicy, Collection<String>> passwordsByPolicy) {
		
		return passwordsByPolicy.entrySet().parallelStream().mapToInt(entry ->
				findNewNumberOfValidPasswords(entry.getKey(), entry.getValue())).sum();
	}
	
	// Private

	private Integer findNumberOfValidPasswords(PasswordPolicy policy, Collection<String> collection) {
		
		Integer min = policy.getMin();
		Integer max = policy.getMax();
		String character = policy.getCharacter();
		return (int) collection.parallelStream().filter(password -> {
				String p = password;
				int appearances = 0;
				while (p.contains(character)) {
					p = p.replaceFirst(character, "");
					appearances++;
				}
				return appearances >= min && appearances <= max;
			}).count();
	}
	
	private Integer findNewNumberOfValidPasswords(PasswordPolicy policy, Collection<String> collection) {
		
		Integer min = policy.getMin() - 1;
		Integer max = policy.getMax() - 1;
		String character = policy.getCharacter();
		return (int) collection.parallelStream().filter(password ->
				String.valueOf(password.charAt(min)).equals(character) ^ String.valueOf(password.charAt(max)).equals(character)).count();
	}
}

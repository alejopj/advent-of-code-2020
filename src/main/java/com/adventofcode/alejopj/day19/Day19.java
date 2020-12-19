package com.adventofcode.alejopj.day19;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

public class Day19 {
	
	private Map<Integer, Rule> rulesById;
	private Map<Rule, Integer> ruleLoops;

	public Day19() {
		
	}

	public Long findNumberOfMessagesMatchingRule(Map<List<Rule>, List<String>> rulesAndMessages, Integer ruleId) {
		
		List<Rule> rules = rulesAndMessages.keySet().iterator().next();
		rulesById = rules.stream().collect(ImmutableMap.toImmutableMap(rule -> rule.getId(), rule -> rule));
		ruleLoops = getRuleLoops(rules);
		Rule initialRule = rules.stream()
				.filter(rule -> rule.getId().equals(ruleId))
				.findFirst().orElse(null);
		List<String> messages = rulesAndMessages.values().iterator().next();
		Set<String> validMessages = getValidMessages(ImmutableSet.of(""), initialRule, rules);
		return messages.stream().filter(message -> validMessages.contains(message)).count();
	}
	
	private Map<Rule, Integer> getRuleLoops(List<Rule> rules) {
		
		Map<Rule, Integer> ruleLoops = new HashMap<>();
		
		rules.stream().forEach(rule -> {
			if (rule.hasLoop()) {
				ruleLoops.put(rule, 0);
			}
		});
		
		return ruleLoops;
	}
	
	private Set<String> getValidMessages(Set<String> messages, Rule rule, List<Rule> rules) {
		
		if (rule.getValue() != null) {
			return messages.stream().map(message -> message + rule.getValue()).collect(ImmutableSet.toImmutableSet());
		} else {
			
			Set<String> leftMessages = ImmutableSet.of("");
			for (Integer ruleIndex : rule.getLeft()) {
				Rule leftRule = rulesById.get(ruleIndex);
				leftMessages = getValidMessages(leftMessages, leftRule, rules);
			}
			Set<String> leftValidMessages = ImmutableSet.copyOf(messages);
			Set<String> lMessages = ImmutableSet.copyOf(leftMessages);
			leftValidMessages = leftValidMessages.stream()
					.map(message -> lMessages.stream()
							.map(leftMessage -> message + leftMessage)
							.collect(ImmutableSet.toImmutableSet()))
					.flatMap(Set::stream)
					.collect(ImmutableSet.toImmutableSet());

			Set<String> rightValidMessages = ImmutableSet.of();
			if (!rule.getRight().isEmpty()) {
				Set<String> rightMessages = ImmutableSet.of("");
				for (Integer ruleIndex : rule.getRight()) {
					Rule rightRule = rulesById.get(ruleIndex);
					rightMessages = getValidMessages(rightMessages, rightRule, rules);
				}
				rightValidMessages = ImmutableSet.copyOf(messages);
				Set<String> rMessages = ImmutableSet.copyOf(rightMessages);
				rightValidMessages = rightValidMessages.stream()
						.map(message -> rMessages.stream()
								.map(rightMessage -> message + rightMessage)
								.collect(ImmutableSet.toImmutableSet()))
						.flatMap(Set::stream)
						.collect(ImmutableSet.toImmutableSet());
			}
			
			return ImmutableSet.<String>builder()
					.addAll(leftValidMessages)
					.addAll(rightValidMessages)
					.build();
		}
	}
	
}

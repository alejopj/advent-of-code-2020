package com.adventofcode.alejopj.day19;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

public class Day19 {
	
	private Map<Integer, Rule> rulesById;
	private Integer maxLoops;
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
		maxLoops = getMaxCharRepetitions(messages);
		List<String> validMessages = getValidMessages(ImmutableList.of(""), initialRule, rules);
		
		return messages.stream().filter(message -> validMessages.contains(message)).count();
	}
	
	private Integer getMaxCharRepetitions(List<String> messages) {
		
		return messages.stream().mapToInt(message -> getMaxCharRepetitions(message)).max().orElse(0);
	}
	
	private Integer getMaxCharRepetitions(String message) {
		
		Map<String, Integer> charRepetitions = new HashMap<>();
		
		message.chars().forEach(character -> charRepetitions.put(String.valueOf(character),
				charRepetitions.containsKey(String.valueOf(character)) ?
						charRepetitions.get(String.valueOf(character)) + 1 : 0));
		
		return charRepetitions.values().stream().mapToInt(value -> value).max().orElse(0);
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
	
	private List<String> getValidMessages(List<String> messages, Rule rule, List<Rule> rules) {
		
//		if (rule.hasLoop()) {
//			Integer loops = ruleLoops.get(rule);
//			loops++;
//			if (loops == maxLoops) {
//				return ImmutableList.of();
//			}
//		}
		
		if (rule.getValue() != null) {
			return messages.stream().map(message -> message + rule.getValue()).collect(ImmutableList.toImmutableList());
		} else {
			
			List<String> leftMessages = ImmutableList.of("");
			for (Integer ruleIndex : rule.getLeft()) {
				Rule leftRule = rulesById.get(ruleIndex);
				leftMessages = getValidMessages(leftMessages, leftRule, rules);
			}
			List<String> leftValidMessages = ImmutableList.copyOf(messages);
			List<String> lMessages = ImmutableList.copyOf(leftMessages);
			leftValidMessages = leftValidMessages.stream()
					.map(message -> lMessages.stream()
							.map(leftMessage -> message + leftMessage)
							.collect(ImmutableList.toImmutableList()))
					.flatMap(List::stream)
					.distinct()
					.collect(ImmutableList.toImmutableList());

			List<String> rightValidMessages = ImmutableList.of();
			if (!rule.getRight().isEmpty()) {
				List<String> rightMessages = ImmutableList.of("");
				for (Integer ruleIndex : rule.getRight()) {
					Rule rightRule = rulesById.get(ruleIndex);
					rightMessages = getValidMessages(rightMessages, rightRule, rules);
				}
				rightValidMessages = ImmutableList.copyOf(messages);
				List<String> rMessages = ImmutableList.copyOf(rightMessages);
				rightValidMessages = rightValidMessages.stream()
						.map(message -> rMessages.stream()
								.map(rightMessage -> message + rightMessage)
								.collect(ImmutableList.toImmutableList()))
						.flatMap(List::stream)
						.distinct()
						.collect(ImmutableList.toImmutableList());
			}
			
			return ImmutableList.<String>builder()
					.addAll(leftValidMessages)
					.addAll(rightValidMessages)
					.build()
					.stream()
					.distinct()
					.collect(ImmutableList.toImmutableList());
		}
	}
	
}

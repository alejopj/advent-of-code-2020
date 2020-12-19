package com.adventofcode.alejopj.day19;

import java.util.List;

public class Rule {

	private final Integer id;
	private final String value;
	private final List<Integer> left;
	private final List<Integer> right;
	
	public Rule(Integer id, String value, List<Integer> left, List<Integer> right) {

		this.id = id;
		this.value = value;
		this.left = left;
		this.right = right;
	}

	public Integer getId() {
		return id;
	}

	public String getValue() {
		return value;
	}

	public List<Integer> getLeft() {
		return left;
	}

	public List<Integer> getRight() {
		return right;
	}
	
	public boolean hasLoop() {
		return left.contains(id) || right.contains(id);
	}

	@Override
	public String toString() {
		return "Rule [id=" + id + ", value=" + value + ", left=" + left + ", right=" + right + "]";
	}
	
}

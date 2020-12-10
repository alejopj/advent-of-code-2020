package com.adventofcode.alejopj.day04;

public enum PassportField {
	BIRTH_YEAR("byr"),
	ISSUE_YEAR("iyr"),
	EXPIRATION_YEAR("eyr"),
	HEIGHT("hgt"),
	HAIR_COLOR("hcl"),
	EYE_COLOR("ecl"),
	PASSPORT_ID("pid"),
	COUNTRY_ID("cid");
	
	private final String id;
	
	private PassportField(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
}

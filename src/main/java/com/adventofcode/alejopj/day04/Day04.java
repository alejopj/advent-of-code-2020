package com.adventofcode.alejopj.day04;

import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

public class Day04 {

	public Day04() {
		
	}
	
	public Long findNumberOfValidPassports(List<Map<String, String>> passports) {
		
		return passports.stream().filter(passport -> hasMandatoryFields(passport)).count();
	}
	
	public Long findNumberOfPresentAndValidPassports(List<Map<String, String>> passports) {
		
		return passports.stream().filter(passport -> isValidPassport(passport)).count();
	}
	
	// Private
	
	private Boolean hasMandatoryFields(Map<String, String> passport) {
		
		Map<PassportField, Boolean> allFields = ImmutableMap.<PassportField, Boolean>builder()
				.put(PassportField.BIRTH_YEAR, true)
				.put(PassportField.ISSUE_YEAR, true)
				.put(PassportField.EXPIRATION_YEAR, true)
				.put(PassportField.HEIGHT, true)
				.put(PassportField.HAIR_COLOR, true)
				.put(PassportField.EYE_COLOR, true)
				.put(PassportField.PASSPORT_ID, true)
				.put(PassportField.COUNTRY_ID, false)
				.build();
		
		List<String> mandatoryFields = allFields.entrySet().stream()
				.filter(entry -> entry.getValue()).map(entry -> entry.getKey())
				.map(field -> field.getId())
				.collect(ImmutableList.toImmutableList());
		
		return passport.keySet().containsAll(mandatoryFields);
	}
	
	private Boolean isValidPassport(Map<String, String> passport) {
		
		return hasMandatoryFields(passport)
				&& isValidBirthYear(passport.get(PassportField.BIRTH_YEAR.getId()))
				&& isValidIssueYear(passport.get(PassportField.ISSUE_YEAR.getId()))
				&& isValidExpirationYear(passport.get(PassportField.EXPIRATION_YEAR.getId()))
				&& isValidHeight(passport.get(PassportField.HEIGHT.getId()))
				&& isValidHairColor(passport.get(PassportField.HAIR_COLOR.getId()))
				&& isValidEyeColor(passport.get(PassportField.EYE_COLOR.getId()))
				&& isValidPassportId(passport.get(PassportField.PASSPORT_ID.getId()))
				&& isValidCountryId(passport.get(PassportField.COUNTRY_ID.getId()));
	}

	private boolean isValidBirthYear(String birthYear) {
		try {
			Integer year = Integer.valueOf(birthYear);
			return birthYear.length() == 4 && year >= 1920 && year <= 2002;
		} catch (Exception e) {
			return false;
		}
	}
	
	private boolean isValidIssueYear(String issueYear) {
		try {
			Integer year = Integer.valueOf(issueYear);
			return issueYear.length() == 4 && year >= 2010 && year <= 2020;
		} catch (Exception e) {
			return false;
		}
	}
	
	private boolean isValidExpirationYear(String expirationYear) {
		try {
			Integer year = Integer.valueOf(expirationYear);
			return expirationYear.length() == 4 && year >= 2020 && year <= 2030;
		} catch (Exception e) {
			return false;
		}
	}
	
	private boolean isValidHeight(String height) {
		try {
			boolean isHeight = height.matches("\\d+(cm|in){1}");
			boolean isCm = height.endsWith("cm");
			Integer number = Integer.valueOf(height.substring(0, height.indexOf(isCm ? "cm" : "in")));
			boolean isValidNumber = false;
			if (isCm) {
				isValidNumber = number >= 150 && number <= 193;
			} else {
				isValidNumber = number >= 59 && number <= 76;
			}
			return isHeight && isValidNumber;
		} catch (Exception e) {
			return false;
		}
	}
	
	private boolean isValidHairColor(String hairColor) {
		
		return hairColor.matches("#[0-9a-f]{6}");
	}
	
	private boolean isValidEyeColor(String eyeColor) {
		
		return eyeColor.matches("amb|blu|brn|gry|grn|hzl|oth");
	}
	
	private boolean isValidPassportId(String passportId) {
		try {
			Integer.valueOf(passportId);
			return passportId.length() == 9;
		} catch (Exception e) {
			return false;
		}
	}
	
	private boolean isValidCountryId(String countryId) {
		return true;
	}
	
}

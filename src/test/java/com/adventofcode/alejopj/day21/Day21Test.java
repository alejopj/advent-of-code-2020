package com.adventofcode.alejopj.day21;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.ImmutableList;

public class Day21Test {
	
	/**
	 * Given a list of foods, containing ingredients and possibly allergens too
	 * When Determining which ingredients cannot possibly contain any of the allergens
	 * Then the number of appearances of allergens free ingredients is returned
	 * And that number is 5
	 */
	@Test
	public void findNumberOfAppearancesOfAllergensFreeIngredientsIs5() {

		List<Food> foods = getFoods("part1.txt");
		Day21 day21 = new Day21();
		Integer number = day21.findNumberOfAppearancesOfAllergenFreeIngredients(foods);
		Assert.assertTrue(number == 5);
	}
	
	/**
	 * Given a list of foods, containing ingredients and possibly allergens too
	 * When Determining which ingredients cannot possibly contain any of the allergens
	 * Then the number of appearances of allergens free ingredients is returned
	 */
	@Test
	public void findNumberOfAppearancesOfAllergensFreeIngredients() {

		List<Food> foods = getFoods("input.txt");
		Day21 day21 = new Day21();
		Integer number = day21.findNumberOfAppearancesOfAllergenFreeIngredients(foods);
		Assert.assertTrue(number >= 0);
		System.out.println(number);
	}
	
	// Private
	
	private List<Food> getFoods(String fileName) {
		
		List<Food> foods = ImmutableList.of();
		
		try {
			Path path = Paths.get(getClass().getClassLoader().getResource("com/adventureofcode/alejopj/day21/" + fileName).toURI());
			List<String> lines = Files.lines(path).collect(ImmutableList.toImmutableList());
			if (lines.isEmpty()) {
				Assert.fail();
			}
			foods = lines.parallelStream().map(line -> getFood(line)).collect(ImmutableList.toImmutableList());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		
		return foods;
	}

	private Food getFood(String line) {
		
		List<String> ingredients = Arrays.stream(line.split("(")[0].trim().split(" "))
				.map(ingredient -> ingredient.trim())
				.collect(ImmutableList.toImmutableList());
		
		List<String> allergens = ImmutableList.of();
			if (line.contains("contains")) {
				allergens = Arrays.stream(line.split("(contains")[1].replace(")", "").trim().split(", "))
				.map(ingredient -> ingredient.trim())
				.collect(ImmutableList.toImmutableList());
			}
			
		return new Food(ingredients, allergens);
	}
	
}

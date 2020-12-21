package com.adventofcode.alejopj.day20;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

public class Day20 {
	
	public Day20() {
		
	}

	public Long findProductOfFourCorningTileIds(List<Tile> tiles) {
		
		Integer dimension = (int) Math.sqrt(tiles.size());
		Integer neededDimensionMatches = dimension * (dimension - 1);
		
		Set<Set<Integer>> horizontalMatches = new HashSet<>();
		Set<Set<Integer>> verticalMatches = new HashSet<>();
		
		while (horizontalMatches.size() + verticalMatches.size() < 2 * neededDimensionMatches) {
			
			List<Tile> randomTiles = getRandomTiles(tiles);
			// FIXME Getting less matches than expected.
			horizontalMatches.addAll(getHorizontalMatches(randomTiles));
			verticalMatches.addAll(getVerticalMatches(randomTiles));
		}
		
		while (horizontalMatches.size() != dimension) {
			horizontalMatches = joinMatches(horizontalMatches);
		}
		
		while (verticalMatches.size() != dimension) {
			verticalMatches = joinMatches(verticalMatches);
		}
		
		Set<Integer> corners = getCorners(horizontalMatches, verticalMatches);
		
		return Long.valueOf(corners.parallelStream().reduce(1, (a, b) -> a * b));
	}
	
	private List<Tile> getRandomTiles(List<Tile> tiles) {
		
		Random r = new Random();
		return tiles.parallelStream()
				.map(tile -> r.nextBoolean() ? rotate(tile) : tile)
				.map(tile -> r.nextBoolean() ? flip(tile) : tile)
				.collect(ImmutableList.toImmutableList());
	}
	
	private Tile rotate(Tile tile) {
		
		List<String> rotatedData = new ArrayList<>(tile.getData());
		Collections.reverse(rotatedData);
		rotatedData = ImmutableList.copyOf(rotatedData);
		
		return new Tile(tile.getId(), rotatedData);
	}

	private Tile flip(Tile tile) {
		
		List<String> flippedData = tile.getData().parallelStream()
				.map(line -> new StringBuilder(line).reverse().toString())
				.collect(ImmutableList.toImmutableList());
		
		return new Tile(tile.getId(), flippedData);
	}

	private Set<Set<Integer>> getHorizontalMatches(List<Tile> tiles) {
		
		return tiles.parallelStream()
				.map(tileA -> tiles.parallelStream()
						.map(tileB -> getHorizontalMatch(tileA, tileB))
						.filter(match -> !match.isEmpty())
						.collect(ImmutableSet.toImmutableSet()))
				.flatMap(Set::stream)
				.collect(ImmutableSet.toImmutableSet());
	}
	
	private Set<Integer> getHorizontalMatch(Tile a, Tile b) {
		
		if (a.getId().equals(b.getId())) {
			return ImmutableSet.of();
		}

		String columnA = a.getData().parallelStream().map(line ->
				String.valueOf(line.charAt(line.length() - 1))).reduce("", (r1, r2) -> r1.concat(r2));
		String columnB = b.getData().parallelStream().map(line ->
				String.valueOf(line.charAt(0))).reduce("", (r1, r2) -> r1.concat(r2));
		
		if (columnA.equals(columnB)) {
			return ImmutableSet.of(a.getId(), b.getId());
		}
		
		columnA = a.getData().parallelStream().map(line ->
				String.valueOf(line.charAt(0))).reduce("", (r1, r2) -> r1.concat(r2));
		columnB = b.getData().parallelStream().map(line ->
				String.valueOf(line.charAt(line.length() - 1))).reduce("", (r1, r2) -> r1.concat(r2));
		
		if (columnB.equals(columnA)) {
			return ImmutableSet.of(b.getId(), a.getId());
		}
		
		return ImmutableSet.of();
	}
	
	private Set<Set<Integer>> getVerticalMatches(List<Tile> tiles) {
		
		return tiles.parallelStream()
				.map(tileA -> tiles.parallelStream()
						.map(tileB -> getVerticalMatch(tileA, tileB))
						.filter(match -> !match.isEmpty())
						.collect(ImmutableSet.toImmutableSet()))
				.flatMap(Set::stream)
				.collect(ImmutableSet.toImmutableSet());
	}
	
	private Set<Integer> getVerticalMatch(Tile a, Tile b) {
		
		if (a.getId().equals(b.getId())) {
			return ImmutableSet.of();
		}
		
		String rowA = a.getData().get(a.getData().size() - 1);
		String rowB = b.getData().get(0);
		
		if (rowA.equals(rowB)) {
			return ImmutableSet.of(a.getId(), b.getId());
		}
		
		rowA = a.getData().get(0);
		rowB = b.getData().get(b.getData().size() - 1);
		
		if (rowB.equals(rowA)) {
			return ImmutableSet.of(b.getId(), a.getId());
		}
		
		return ImmutableSet.of();
	}
	
	private Set<Set<Integer>> joinMatches(Set<Set<Integer>> matches) {
		
		Set<Set<Integer>> reduction = new HashSet<>(matches);
		
		for (Set<Integer> matchA : matches) {
			for (Set<Integer> matchB : matches) {
				if (!matchA.equals(matchB)) {
					if (!getIntersection(matchA, matchB).isEmpty()) {
						reduction.add(getUnion(matchA, matchB));
						reduction.remove(matchA);
						reduction.remove(matchB);
					}
				}
			}
		}
		
		return ImmutableSet.copyOf(reduction);
	}
	
	private <T> Set<T> getIntersection(Set<T> a, Set<T> b) {
		
		return a.stream()
                .filter(b::contains)
                .distinct()
                .sorted()
                .collect(ImmutableSet.toImmutableSet());
	}
	
	private <T> Set<T> getUnion(Set<T> a, Set<T> b) {
		
		return ImmutableSet.<T>builder().addAll(a).addAll(b).build();
	}
	
	private Set<Integer> getCorners(Set<Set<Integer>> horizontalMatches, Set<Set<Integer>> verticalMatches) {
		
		Set<Integer> horizontalPerimeter = getPerimeter(horizontalMatches);
		Set<Integer> verticalPerimeter = getPerimeter(verticalMatches);
		return getIntersection(horizontalPerimeter, verticalPerimeter);
	}

	private Set<Integer> getPerimeter(Set<Set<Integer>> matches) {
		
		return matches.parallelStream()
				.map(match -> {
					List<Integer> list = ImmutableList.copyOf(match);
					return ImmutableSet.of(list.get(0), list.get(list.size() - 1));
				})
				.flatMap(Set::stream)
				.collect(ImmutableSet.toImmutableSet());
	}
	
}

package com.adventofcode.alejopj.day11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import com.google.common.collect.ImmutableSet;

public class Day11 {

	public Day11() {
		
	}
	
	public Long findNumberOfOccupiedSeats(SeatStatus[][] seatStatuses) {
		
		SeatStatus[][] oldSeatStatuses = {};
		SeatStatus[][] newSeatStatuses = Arrays.stream(seatStatuses).map(SeatStatus[]::clone).toArray(SeatStatus[][]::new);

		do {
			oldSeatStatuses =Arrays.stream(newSeatStatuses).map(SeatStatus[]::clone).toArray(SeatStatus[][]::new);
			for (int i = 0; i < oldSeatStatuses.length; i++) {
				
				for (int j = 0; j < oldSeatStatuses[0].length; j++) {
					
					newSeatStatuses[i][j] = getNewStatusFromAdjacents(oldSeatStatuses, i, j);
				}
			}
		} while (!Arrays.deepEquals(newSeatStatuses, oldSeatStatuses));
		
		return Arrays.stream(oldSeatStatuses).mapToLong(row ->
				Arrays.stream(row).filter(seatStatus -> SeatStatus.OCCUPIED.equals(seatStatus)).count()).sum();
	}
	
	public Long findNewNumberOfOccupiedSeats(SeatStatus[][] seatStatuses) {
		
		SeatStatus[][] oldSeatStatuses = {};
		SeatStatus[][] newSeatStatuses = Arrays.stream(seatStatuses).map(SeatStatus[]::clone).toArray(SeatStatus[][]::new);

		do {
			oldSeatStatuses =Arrays.stream(newSeatStatuses).map(SeatStatus[]::clone).toArray(SeatStatus[][]::new);
			for (int i = 0; i < oldSeatStatuses.length; i++) {
				
				for (int j = 0; j < oldSeatStatuses[0].length; j++) {
					
					newSeatStatuses[i][j] = getNewStatusFromFirstSeat(oldSeatStatuses, i, j);
				}
			}
		} while (!Arrays.deepEquals(newSeatStatuses, oldSeatStatuses));
		
		return Arrays.stream(oldSeatStatuses).mapToLong(row ->
				Arrays.stream(row).filter(seatStatus -> SeatStatus.OCCUPIED.equals(seatStatus)).count()).sum();
	}

	// Private

	private SeatStatus getNewStatusFromAdjacents(SeatStatus[][] seatStatuses, int i, int j) {
		
		switch (seatStatuses[i][j]) {
			case EMPTY:
				return getNewStatusFromAdjacentsAndEmpty(seatStatuses, i, j);
			case OCCUPIED:
				return getNewStatusFromAdjacentsAndOccupied(seatStatuses, i, j);
			default:
				return seatStatuses[i][j];
		}
	}
	
	private SeatStatus getNewStatusFromAdjacentsAndEmpty(SeatStatus[][] seatStatuses, int i, int j) {
		
		List<SeatStatus> adjacentSeatStatuses = getAdjacentSeatStatuses(seatStatuses, i, j);
		boolean allAdjacentSeatStatusesAreNotOccupied = adjacentSeatStatuses.parallelStream()
				.allMatch(seatStatus -> !SeatStatus.OCCUPIED.equals(seatStatus));
		return allAdjacentSeatStatusesAreNotOccupied ? SeatStatus.OCCUPIED : seatStatuses[i][j];
	}

	private SeatStatus getNewStatusFromAdjacentsAndOccupied(SeatStatus[][] seatStatuses, int i, int j) {
		
		List<SeatStatus> adjacentSeatStatuses = getAdjacentSeatStatuses(seatStatuses, i, j);
		boolean atLeast4AdjacentSeatStatusesAreOccupied = adjacentSeatStatuses.parallelStream()
				.filter(seatStatus -> SeatStatus.OCCUPIED.equals(seatStatus)).count() >= 4;
		return atLeast4AdjacentSeatStatusesAreOccupied ? SeatStatus.EMPTY : seatStatuses[i][j];
	}

	private List<SeatStatus> getAdjacentSeatStatuses(SeatStatus[][] seatStatuses, int i, int j) {
		
		List<SeatStatus> adjacentSeatStatuses = new ArrayList<>();
		
		for (int x = i - 1; x <= i + 1; x++) {
			
			for (int y = j - 1; y <= j + 1; y++) {
				try {
					if (x == i && y == j) {
						continue;
					}
					adjacentSeatStatuses.add(seatStatuses[x][y]);
				} catch (Exception e) {
				}
			}
		}
		
		return adjacentSeatStatuses;
	}
	
	private SeatStatus getNewStatusFromFirstSeat(SeatStatus[][] seatStatuses, int i, int j) {
		
		switch (seatStatuses[i][j]) {
			case EMPTY:
				return getNewStatusFromFirstSeatAndEmpty(seatStatuses, i, j);
			case OCCUPIED:
				return getNewStatusFromFirstSeatAndOccupied(seatStatuses, i, j);
			default:
				return seatStatuses[i][j];
		}
	}
	
	private SeatStatus getNewStatusFromFirstSeatAndEmpty(SeatStatus[][] seatStatuses, int i, int j) {
		
		List<SeatStatus> adjacentSeatStatuses = getFirstSeatStatuses(seatStatuses, i, j);
		boolean allAdjacentSeatStatusesAreNotOccupied = adjacentSeatStatuses.parallelStream()
				.allMatch(seatStatus -> !SeatStatus.OCCUPIED.equals(seatStatus));
		return allAdjacentSeatStatusesAreNotOccupied ? SeatStatus.OCCUPIED : seatStatuses[i][j];
	}

	private SeatStatus getNewStatusFromFirstSeatAndOccupied(SeatStatus[][] seatStatuses, int i, int j) {
		
		List<SeatStatus> adjacentSeatStatuses = getFirstSeatStatuses(seatStatuses, i, j);
		boolean atLeast4AdjacentSeatStatusesAreOccupied = adjacentSeatStatuses.parallelStream()
				.filter(seatStatus -> SeatStatus.OCCUPIED.equals(seatStatus)).count() >= 5;
		return atLeast4AdjacentSeatStatusesAreOccupied ? SeatStatus.EMPTY : seatStatuses[i][j];
	}
	
	private List<SeatStatus> getFirstSeatStatuses(SeatStatus[][] seatStatuses, int i, int j) {
		
		List<SeatStatus> firstSeatStatuses = new ArrayList<>();

		Set<SeatStatus> statuses = ImmutableSet.of(SeatStatus.EMPTY, SeatStatus.OCCUPIED);
		
		// Top
		SeatStatus seatStatus = SeatStatus.FLOOR;
		for (int x = i - 1; x >= 0; x--) {
			if (statuses.contains(seatStatuses[x][j])) {
				seatStatus = seatStatuses[x][j];
				break;
			}
		}
		firstSeatStatuses.add(seatStatus);
		
		// Right
		seatStatus = SeatStatus.FLOOR;
		for (int y = j + 1; y < seatStatuses[0].length; y++) {
			if (statuses.contains(seatStatuses[i][y])) {
				seatStatus = seatStatuses[i][y];
				break;
			}
		}
		firstSeatStatuses.add(seatStatus);
		
		// Bottom
		seatStatus = SeatStatus.FLOOR;
		for (int x = i + 1; x < seatStatuses.length; x++) {
			if (statuses.contains(seatStatuses[x][j])) {
				seatStatus = seatStatuses[x][j];
				break;
			}
		}
		firstSeatStatuses.add(seatStatus);
		
		// Left
		seatStatus = SeatStatus.FLOOR;
		for (int y = j - 1; y >= 0; y--) {
			if (statuses.contains(seatStatuses[i][y])) {
				seatStatus = seatStatuses[i][y];
				break;
			}
		}
		firstSeatStatuses.add(seatStatus);
		
		// Top left
		seatStatus = SeatStatus.FLOOR;
		int x = i - 1;
		int y = j - 1;
		while (x >= 0 && y >= 0) {
			if (statuses.contains(seatStatuses[x][y])) {
				seatStatus = seatStatuses[x][y];
				break;
			}
			x--;
			y--;
		}
		firstSeatStatuses.add(seatStatus);
		
		// Top right
		seatStatus = SeatStatus.FLOOR;
		x = i - 1;
		y = j + 1;
		while (x >= 0 && y < seatStatuses[0].length) {
			if (statuses.contains(seatStatuses[x][y])) {
				seatStatus = seatStatuses[x][y];
				break;
			}
			x--;
			y++;
		}
		firstSeatStatuses.add(seatStatus);
		
		// Bottom right
		seatStatus = SeatStatus.FLOOR;
		x = i + 1;
		y = j + 1;
		while (x < seatStatuses.length && y < seatStatuses[0].length) {
			if (statuses.contains(seatStatuses[x][y])) {
				seatStatus = seatStatuses[x][y];
				break;
			}
			x++;
			y++;
		}
		firstSeatStatuses.add(seatStatus);
		
		// Bottom left
		seatStatus = SeatStatus.FLOOR;
		x = i + 1;
		y = j - 1;
		while (x < seatStatuses.length && y >= 0) {
			if (statuses.contains(seatStatuses[x][y])) {
				seatStatus = seatStatuses[x][y];
				break;
			}
			x++;
			y--;
		}
		firstSeatStatuses.add(seatStatus);
		
		return firstSeatStatuses;
	}
	
}

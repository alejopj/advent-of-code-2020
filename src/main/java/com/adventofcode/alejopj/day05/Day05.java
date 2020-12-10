package com.adventofcode.alejopj.day05;

import java.util.List;

import com.google.common.collect.ImmutableList;

public class Day05 {

	public Day05() {
		
	}
	
	public Integer findHighestSeatId(List<String> boardingPasses) {
		
		List<Integer> seatIds = getSortedSeatIds(boardingPasses);
		return seatIds.isEmpty() ? null : seatIds.get(seatIds.size() - 1);
	}
	
	public Integer findMySeatId(List<String> boardingPasses) {
		
		Integer mySeatId = null;
		
		List<Integer> seatIds = getSortedSeatIds(boardingPasses);
		for (int i = 0; i < seatIds.size() - 1; i++) {
			if (seatIds.get(i) + 1 != seatIds.get(i + 1)) {
				mySeatId = seatIds.get(i) + 1;
				break;
			}
		}
		
		return mySeatId;
	}
	
	// Private
	
	private List<Integer> getSortedSeatIds(List<String> boardingPasses) {

		return boardingPasses.parallelStream()
				.map(boardingPass -> getRow(boardingPass) * 8 + getColumn(boardingPass))
				.sorted()
				.collect(ImmutableList.toImmutableList());
	}
	
	private Integer getRow(String boardingPass) {
		
		Integer fromRow = 0;
		Integer toRow = 127;
		Integer half = 0;
		
		for (int i = 0; i < boardingPass.length(); i++) {
			switch (boardingPass.charAt(i)) {
				case 'F':
					half = -1 * ((toRow - fromRow + 1) / 2);
					toRow += half;
					break;
				case 'B':
					half = (toRow - fromRow + 1) / 2;
					fromRow += half;
					break;
				default:
					break;
			}
		}
		
		if (half < 0) {
			return toRow;
		} else {
			return fromRow;
		}
	}
	
	private Integer getColumn(String boardingPass) {
	
		Integer fromColumn = 0;
		Integer toColumn = 7;
		Integer half = 0;
		
		for (int i = 0; i < boardingPass.length(); i++) {
			switch (boardingPass.charAt(i)) {
				case 'L':
					half = -1 * ((toColumn - fromColumn + 1) / 2);
					toColumn += half;
					break;
				case 'R':
					half = (toColumn - fromColumn + 1) / 2;
					fromColumn += half;
					break;
				default:
					break;
			}
		}
		
		if (half < 0) {
			return toColumn;
		} else {
			return fromColumn;
		}
	}
	
}

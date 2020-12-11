package com.adventofcode.alejopj.day11;

import com.google.common.collect.ImmutableSet;

public enum SeatStatus {
	FLOOR("."),
	EMPTY("L"),
	OCCUPIED("#");
	
	private final String status;
	
	private SeatStatus(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return status;
	}

	public static SeatStatus of(String status) {
		
		return ImmutableSet.copyOf(SeatStatus.values()).stream()
				.filter(squareContent -> squareContent.getStatus().equals(status)).findFirst().orElse(null);
	}
}

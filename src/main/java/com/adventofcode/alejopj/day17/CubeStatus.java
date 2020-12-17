package com.adventofcode.alejopj.day17;

import com.google.common.collect.ImmutableSet;

public enum CubeStatus {
	ACTIVE("#"),
	INACTIVE(".");
	
	private final String status;
	
	private CubeStatus(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return status;
	}
	
	public static CubeStatus of(String status) {
		
		return ImmutableSet.copyOf(CubeStatus.values()).stream()
				.filter(cubeStatus -> cubeStatus.getStatus().equals(status)).findFirst().orElse(null);
	}
}

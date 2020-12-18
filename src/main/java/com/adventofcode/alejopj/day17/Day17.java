package com.adventofcode.alejopj.day17;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Day17 {

	public Day17() {
		
	}

	public Long findNumberOfActiveCubesAfterCycles(Map<Point3D, CubeStatus> cubes, Integer cycles) {

		Map<Point3D, CubeStatus> updatedCubes = new LinkedHashMap<>(cubes);
		
		for (int i = 0; i < cycles; i++) {
			
			Map<Point3D, CubeStatus> newCubes = updatedCubes.entrySet().stream()
					.map(entry -> getNewStatus(updatedCubes, entry.getKey(), entry.getValue()))
					.reduce((a, b) -> {
						a.putAll(b);
						return a;
					}).orElse(updatedCubes);
			
			updatedCubes.putAll(newCubes);
		}
		
		return updatedCubes.values().parallelStream().filter(cubeStatus -> CubeStatus.ACTIVE.equals(cubeStatus)).count();
	}
	
	private Map<Point3D, CubeStatus> getNewStatus(Map<Point3D, CubeStatus> cubes, Point3D point, CubeStatus cubeStatus) {

		Map<Point3D, CubeStatus> newCubes = new LinkedHashMap<>();
		
		List<Point3D> neighbors = new ArrayList<>();
		for (int x = point.getX() -1; x <= point.getX() + 1; x++) {
			for (int y = point.getY() -1; y <= point.getY() + 1; y++) {
				for (int z = point.getZ() -1; z <= point.getZ() + 1; z++) {
					Point3D neighbor = new Point3D(x, y, z);
					if (neighbor != point) {
						neighbors.add(neighbor);
					}
				}
			}
		}
		
		neighbors.forEach(neighbor -> {
			if (!newCubes.containsKey(neighbor)) {
				newCubes.put(neighbor, CubeStatus.INACTIVE);
			}
		});

		long activeNeighbors = neighbors.stream().filter(neighbor -> CubeStatus.ACTIVE.equals(cubes.get(neighbor))).count();
		if (CubeStatus.ACTIVE.equals(cubeStatus)) {
			if (activeNeighbors != 2 && activeNeighbors != 3) {
				newCubes.put(point, CubeStatus.INACTIVE);
			}
		} else {
			if (activeNeighbors == 3) {
				newCubes.put(point, CubeStatus.ACTIVE);
			}
		}
		
		return newCubes;
	}
}

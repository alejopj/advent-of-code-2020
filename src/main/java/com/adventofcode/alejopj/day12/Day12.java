package com.adventofcode.alejopj.day12;

import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

public class Day12 {

	public Day12() {
		
	}

	public Integer findManhattanDistance(Action initialDirection, List<NavigationInstruction> instructions) {
		
		Action currentDirection = initialDirection;
		Coordinate currentCoordinate = new Coordinate(0, 0);
		
		for (NavigationInstruction instruction : instructions) {
			
			switch (instruction.getAction()) {
				case N:
				case S:
				case E:
				case W:
					currentCoordinate = getCoordinate(currentCoordinate, instruction);
					break;
				case L:
					currentDirection = getCurrentDirection(currentDirection, instruction);
					break;
				case R:
					currentDirection = getCurrentDirection(currentDirection, instruction);
					break;
				case F:
					NavigationInstruction forwardInstruction = new NavigationInstruction(currentDirection, instruction.getValue());
					currentCoordinate = getCoordinate(currentCoordinate, forwardInstruction);
					break;
				default:
					break;
			}
		}
		
		return Math.abs(currentCoordinate.getX()) + Math.abs(currentCoordinate.getY());
	}
	
	public Integer findManhattanDistance(Coordinate initialWayPoint, List<NavigationInstruction> instructions) {
		
		Coordinate ship = new Coordinate(0, 0);
		Coordinate waypoint = initialWayPoint;
		
		for (NavigationInstruction instruction : instructions) {
			
			switch (instruction.getAction()) {
				case N:
				case S:
				case E:
				case W:
					waypoint = getCoordinate(waypoint, instruction);
					break;
				case L:
					waypoint = rotate(waypoint, instruction);
					break;
				case R:
					waypoint = rotate(waypoint, instruction);
					break;
				case F:
					ship = move(ship, waypoint, instruction);
					break;
				default:
					break;
			}
		}
		
		return Math.abs(ship.getX()) + Math.abs(ship.getY());
	}
	
	// Private
	
	private Coordinate getCoordinate(Coordinate currentCoordinate, NavigationInstruction instruction) {
		
		switch (instruction.getAction()) {
			case N:
				return new Coordinate(currentCoordinate.getX(), currentCoordinate.getY() + instruction.getValue());
			case S:
				return new Coordinate(currentCoordinate.getX(), currentCoordinate.getY() - instruction.getValue());
			case E:
				return new Coordinate(currentCoordinate.getX() + instruction.getValue(), currentCoordinate.getY());
			case W:
				return new Coordinate(currentCoordinate.getX() - instruction.getValue(), currentCoordinate.getY());
			default:
				return currentCoordinate;
		}
	}
	
	private Action getCurrentDirection(Action currentDirection, NavigationInstruction instruction) {
		
		Map<Action, Integer> degreesByDirection = getDegreesByDirection();
		Integer currentDegrees = degreesByDirection.get(currentDirection);
		Integer signedValue = (Action.L.equals(instruction.getAction()) ? -1 : 1) * instruction.getValue();
		Integer newDegrees = ((currentDegrees + signedValue) + 360) % 360;
		return degreesByDirection.entrySet().stream()
				.filter(entry -> entry.getValue().equals(newDegrees))
				.map(entry -> entry.getKey())
				.findFirst().orElse(null);
	}
	
	private Map<Action, Integer> getDegreesByDirection() {
		
		return ImmutableMap.of(
				Action.N, 0,
				Action.E, 90,
				Action.S, 180,
				Action.W, 270);
	}
	
	private Coordinate rotate(Coordinate coordinate, NavigationInstruction instruction) {
		
		Integer direction = Action.L.equals(instruction.getAction()) ? -1 : 1;
		Integer signedValue = direction * instruction.getValue();
		Integer degrees = (signedValue + 360) % 360;
		switch (degrees) {
			case 90:
				return new Coordinate(coordinate.getY(), -1 * coordinate.getX());
			case 180:
				return new Coordinate(-1 * coordinate.getX(), -1 * coordinate.getY());
			case 270:
				return new Coordinate(-1 * coordinate.getY(), coordinate.getX());
			default:
				return coordinate;
		}
	}
	
	private Coordinate move(Coordinate ship, Coordinate waypoint, NavigationInstruction instruction) {
		
		return new Coordinate(ship.getX() + instruction.getValue() * waypoint.getX(),
				ship.getY() + instruction.getValue() * waypoint.getY());
	}
	
}

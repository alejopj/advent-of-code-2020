package com.adventofcode.alejopj.day03;

import java.util.Map;
import java.util.Map.Entry;

public class Day03 {

	public Day03() {
		
	}
	
	public Integer findNumberOfTreesEncountered(SquareContent[][] map, Integer startingX, Integer startingY,
			Map<SlopeDirection, Integer> slope) {
		
		Integer numberOfTreesEncountered = 0;
		
		Integer currentX = startingX;
		Integer currentY = startingY;

		while (currentY < map.length - 1) {
			for (Entry<SlopeDirection, Integer> s : slope.entrySet()) {
				SlopeDirection slopeDirection = s.getKey();
				Integer squares = s.getValue() * slopeDirection.getDirection();
				switch(slopeDirection) {
					case TOP:
					case BOTTOM:
						currentY = (currentY + squares) % map.length;
						break;
					case RIGHT:
					case LEFT:
						currentX = (currentX + squares) % map[0].length;
						break;
					default:
						break;
				}
			}
			if (SquareContent.TREE.equals(map[currentY][currentX])) {
				numberOfTreesEncountered++;
			}
		}
		
		return numberOfTreesEncountered;
	}
}

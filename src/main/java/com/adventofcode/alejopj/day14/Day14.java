package com.adventofcode.alejopj.day14;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableList;

public class Day14 {

	public Day14() {
		
	}

	public Long findSumOfMemoryValues(List<MemoryOperation> memOperations) {
		
		Map<Long, Long> memory = new LinkedHashMap<>();
		
		for (MemoryOperation memOperation : memOperations) {
			String mask = memOperation.getMask();
			String fullMask = String.format("%1$" + 64 + "s", mask).replace(" ", "X");
			List<MemoryPosition> memoryPositions = memOperation.getPositions();
			for (MemoryPosition position : memoryPositions) {
				memory.put(position.getIndex(), getMaskedValue(fullMask, position.getValue()));
			}
		}
		
		return memory.values().stream().mapToLong(value -> value).sum();
	}
	
	public Long findNewSumOfMemoryValues(List<MemoryOperation> memOperations) {
		
		Map<Long, Long> memory = new LinkedHashMap<>();
		
		for (MemoryOperation memOperation : memOperations) {
			String mask = memOperation.getMask();
			String fullMask = String.format("%1$" + 64 + "s", mask).replace(" ", "0");
			List<MemoryPosition> memoryPositions = memOperation.getPositions();
			for (MemoryPosition position : memoryPositions) {
				
				List<Long> indexes = getMaskedIndexes(fullMask, position.getIndex());
				for (Long index : indexes) {
					memory.put(index, position.getValue());
				}
			}
		}
		
		return memory.values().stream().mapToLong(value -> value).sum();
	}
	
	// Private

	private Long getMaskedValue(String mask, Long value) {
		
		char[] newValue = String.format("%1$" + 64 + "s", Long.toBinaryString(value)).replace(" ", "0").toCharArray();
		
		for (int i = 0; i < mask.length(); i++) {
			switch (mask.charAt(i)) {
				case '0':
				case '1':
					newValue[i] = mask.charAt(i);
					break;
				default:
					break;
			}
		}
		
		return Long.parseLong(String.valueOf(newValue), 2);
	}
	
	private List<Long> getMaskedIndexes(String mask, Long index) {
		
		char[] newIndex = String.format("%1$" + 64 + "s", Long.toBinaryString(index)).replace(" ", "0").toCharArray();
		
		for (int i = 0; i < mask.length(); i++) {
			switch (mask.charAt(i)) {
				case '0':
					break;
				case '1':
					newIndex[i] = '1';
					break;
				case 'X':
					char[] newIndex0 = Arrays.copyOf(newIndex, newIndex.length);
					newIndex0[i] = '0';
					char[] newIndex1 = Arrays.copyOf(newIndex, newIndex.length);
					newIndex1[i] = '1';
					String newMask = getSubmask(mask, i);
					List<Long> newIndexes0 = getMaskedIndexes(newMask, Long.parseLong(String.valueOf(newIndex0), 2));
					List<Long> newIndexes1 = getMaskedIndexes(newMask, Long.parseLong(String.valueOf(newIndex1), 2));
					return ImmutableList.<Long>builder().addAll(newIndexes0).addAll(newIndexes1).build();
				default:
					break;
			}
		}
		
		return ImmutableList.of(Long.parseLong(String.valueOf(newIndex), 2));
	}
	
	private String getSubmask(String mask, Integer index) {
		
		char[] submask = mask.toCharArray();
		for (int i = 0; i <= index; i++) {
			submask[i] = '0';
		}
		return String.valueOf(submask);
	}

}

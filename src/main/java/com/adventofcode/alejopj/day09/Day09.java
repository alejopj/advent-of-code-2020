package com.adventofcode.alejopj.day09;

import java.util.List;

public class Day09 {

	public Day09() {
		
	}
	
	public Long findFirstNumberIsNotSumOfAPairOfPreambleNumbers(List<Long> data, Integer preambleSize) {

		Long number = null;
		
		if (preambleSize >= data.size()) {
			return number;
		}
		
		for (int i = preambleSize; i < data.size(); i++) {
			List<Long> preamble = data.subList(i - preambleSize, i);
			if (!isSumOf(preamble, data.get(i))) {
				number = data.get(i);
			}
		}
		
		return number;
	}
	
	public Long findEncryptionWeakness(List<Long> data, Long invalidNumber) {
		
		Long encryptionWeakness = null;
		
		int i = data.indexOf(invalidNumber);
		if (i > 1) {
			List<Long> leftData = data.subList(0, i);
			encryptionWeakness = getEncryptionWeakness(leftData, invalidNumber);
		}
		if (i < data.size() - 1 && encryptionWeakness == null) {
			List<Long> rightData = data.subList(i + 1, data.size());
			encryptionWeakness = getEncryptionWeakness(rightData, invalidNumber);
		}
		
		return encryptionWeakness;
	}
	
	// Private

	private boolean isSumOf(List<Long> preamble, Long number) {

		for (Long a : preamble.subList(0, preamble.size() - 1)) {
			
			for (Long b : preamble.subList(1, preamble.size())) {
				
				if (number == (a + b)) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	private Long getEncryptionWeakness(List<Long> data, Long invalidNumber) {

		Long encryptionWeakness = null;
		
		for (int size = 2; size <= data.size(); size++) {
			
			for (int i = 0; i < data.size() - size; i++) {
				
				List<Long> subList = data.subList(i, i + size);
				Long sum = subList.parallelStream().mapToLong(number -> number).sum();
				if (invalidNumber.equals(sum)) {
					Long min = subList.parallelStream().reduce((a, b) -> a <= b ? a : b).orElse(0L);
					Long max = subList.parallelStream().reduce((a, b) -> a >= b ? a : b).orElse(0L);
					encryptionWeakness = min + max;
					return encryptionWeakness;
				}
			}
		}
		
		return encryptionWeakness;
	}
	
}

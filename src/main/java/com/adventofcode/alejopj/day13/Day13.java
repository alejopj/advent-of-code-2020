package com.adventofcode.alejopj.day13;

import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

public class Day13 {

	public Day13() {
		
	}

	public Integer findProductOfEarliestBusAndWaitingTime(Map<Integer, List<Integer>> departureAndBuses) {
		
		Integer departure = departureAndBuses.keySet().iterator().next();
		List<Integer> buses = departureAndBuses.values().iterator().next();
		Integer bus = buses.parallelStream()
				.filter(b -> Integer.MIN_VALUE != b)
				.reduce((a, b) ->
					   (a * (departure / a + (departure % a == 0 ? 0 : 1)) - departure)
					<= (b * (departure / b + (departure % b == 0 ? 0 : 1)) - departure) ? a : b)
				.orElse(null);
		Integer waitingTime = bus * (departure / bus + (departure % bus == 0 ? 0 : 1)) - departure;
		return bus * waitingTime;
	}

	public Long findEarliestTimeMatchingBusesAndPositions(Map<Integer, List<Integer>> departureAndBuses) {
		
		List<Integer> buses = departureAndBuses.values().iterator().next();
		Map<Integer, Integer> numbersAndReminders = buses.parallelStream()
				.filter(bus -> bus != Integer.MIN_VALUE)
				.collect(ImmutableMap.toImmutableMap(bus -> bus, bus -> bus - buses.indexOf(bus)));
		List<Integer> numbers = numbersAndReminders.keySet().parallelStream().collect(ImmutableList.toImmutableList());
		List<Integer> reminders = numbersAndReminders.values().parallelStream().collect(ImmutableList.toImmutableList());
		
		Long product = numbers.parallelStream().mapToLong(number -> number).reduce(1, (a, b) -> a * b);
		Long sum = 0L;
		
		for (int i = 0; i < numbers.size(); i++) {
			Long number = Long.valueOf(numbers.get(i));
			Long partialProduct = product / number;
			Long inverse = mulInv(partialProduct, number);
			Integer reminder = reminders.get(i);
			sum += partialProduct * inverse * reminder;
		}
		
		return sum % product;
	}
	
	// Private
	
	private Long mulInv(Long a, Long b) {
		
        long b0 = b;
        long x0 = 0;
        long x1 = 1;
 
        if (b == 1) {
        	return 1L;
        }
 
        while (a > 1) {
        	long q = a / b;
        	long amb = a % b;
            a = b;
            b = amb;
            long xqx = x1 - q * x0;
            x1 = x0;
            x0 = xqx;
        }
 
        if (x1 < 0) {
        	x1 += b0;
        }
 
        return x1;
    }
	
}

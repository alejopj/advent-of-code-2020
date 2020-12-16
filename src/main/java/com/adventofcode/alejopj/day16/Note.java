package com.adventofcode.alejopj.day16;

import java.util.List;
import java.util.Map;

public class Note {

	private final Map<String, List<Range>> rules;
	private final List<Integer> ticket;
	private final List<List<Integer>> nearbyTickets;
	
	public Note(Map<String, List<Range>> rules, List<Integer> ticket, List<List<Integer>> nearbyTickets) {
		
		this.rules = rules;
		this.ticket = ticket;
		this.nearbyTickets = nearbyTickets;
	}

	public Map<String, List<Range>> getRules() {
		return rules;
	}

	public List<Integer> getTicket() {
		return ticket;
	}

	public List<List<Integer>> getNearbyTickets() {
		return nearbyTickets;
	}

	@Override
	public String toString() {
		return "Note [rules=" + rules + ", ticket=" + ticket + ", nearbyTickets=" + nearbyTickets + "]";
	}
	
}

package com.adventofcode.alejopj.day20;

import java.util.List;

public class Tile {

	private final Integer id;
	private final List<String> data;
	
	public Tile(Integer id, List<String> data) {

		this.id = id;
		this.data = data;
	}
	
	public Integer getId() {
		return id;
	}
	
	public List<String> getData() {
		return data;
	}
	
	@Override
	public String toString() {
		return "Tile [id=" + id + ", data=" + data + "]";
	}
	
}

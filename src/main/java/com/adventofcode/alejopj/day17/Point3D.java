package com.adventofcode.alejopj.day17;

public class Point3D {

	private final Integer x;
	private final Integer y;
	private final Integer z;
	
	public Point3D(Integer x, Integer y, Integer z) {
		
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Integer getX() {
		return x;
	}
	
	public Integer getY() {
		return y;
	}
	
	public Integer getZ() {
		return z;
	}
	
	@Override
	public String toString() {
		return "Point3D [x=" + x + ", y=" + y + ", z=" + z + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		
		Point3D other = (Point3D) obj;
		return x.equals(other.getX()) && y.equals(other.getY()) && z.equals(other.getZ());
	}
		
}

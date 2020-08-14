package it.polito.tdp.crimes.model;

public class DistrictDistance implements Comparable<DistrictDistance> {
	private int id;
	private Double distance;
	public DistrictDistance(int id, Double distance) {
		super();
		this.id = id;
		this.distance = distance;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Double getDistance() {
		return distance;
	}
	public void setDistance(Double distance) {
		this.distance = distance;
	}
	@Override
	public int compareTo(DistrictDistance o) {
		return this.distance.compareTo(o.distance);
	}
	public String toString() {
		return this.id+": "+this.distance;
	}
	
}

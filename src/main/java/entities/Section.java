package entities;

import java.util.List;

public class Section {

	private static int cmp = 0;
	private int id;
	private String name;
	public List<Place> places;
	
	public Section(String name) {
		this.id = ++cmp;
		this.name = name;
	}
	
	public Section(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Section(int id, String name, List<Place> place) {
		this.id = id;
		this.name = name;
		this.places = place;
	}

	public Section(String name, List<Place> place) {
		this.id = ++cmp;
		this.name = name;
		this.places = place;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Place> getPlaces() {
		return places;
	}

	public void setPlaces(List<Place> places) {
		this.places = places;
	}

	@Override
	public String toString() {
		return "Section [id=" + id + ", name=" + name + ", place=" + places + "]";
	}
	
	
}

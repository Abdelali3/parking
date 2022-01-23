package entities;

import java.time.LocalDateTime;
import java.util.Date;

public class Occupation {

	private static int cmp = 0;
	private int id;
	private LocalDateTime start;
	private LocalDateTime end;
	private Boolean batteryCharging;
	private Boolean washing;
	public Ticket ticket = null;
	public Vehicle vehicle;
	public Place place;
	
	
	public Occupation(LocalDateTime start, LocalDateTime end, Boolean batteryCharging, Boolean washing, Ticket ticket, Vehicle vehicle, Place place) {
		this.start = start;
		this.end = end;
		this.batteryCharging = batteryCharging;
		this.washing = washing;
		this.ticket = ticket;
		this.vehicle = vehicle;
		this.place = place;
		this.id = ++cmp;
	}


	public Occupation(int id, LocalDateTime start, LocalDateTime end, Boolean batteryCharging, Boolean washing, Ticket ticket, Vehicle vehicle, Place place) {
		super();
		this.id = id;
		this.start = start;
		this.end = end;
		this.batteryCharging = batteryCharging;
		this.washing = washing;
		this.ticket = ticket;
		this.vehicle = vehicle;
		this.place = place;
	}

	

	public Place getPlace() {
		return place;
	}


	public void setPlace(Place place) {
		this.place = place;
	}


	public Vehicle getVehicle() {
		return vehicle;
	}


	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public LocalDateTime getStart() {
		return start;
	}


	public void setStart(LocalDateTime start) {
		this.start = start;
	}


	public LocalDateTime getEnd() {
		return end;
	}


	public void setEnd(LocalDateTime end) {
		this.end = end;
	}


	public Boolean getBatteryCharging() {
		return batteryCharging;
	}


	public void setBatteryCharging(Boolean batteryCharging) {
		this.batteryCharging = batteryCharging;
	}


	public Boolean getWashing() {
		return washing;
	}


	public void setWashing(Boolean washing) {
		this.washing = washing;
	}


	public Ticket getTicket() {
		return ticket;
	}


	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}


	@Override
	public String toString() {
		return "Occupation [id=" + id + ", start=" + start + ", end=" + end + ", batteryCharging=" + batteryCharging
				+ ", washing=" + washing + ", ticket=" + ticket + "]";
	}

	
	
	
	
}

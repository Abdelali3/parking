package models;

public class TicketModel {
	public String brand;
	public String matriculation;
	public String details;
	public float price;
	public float washing;
	public float battrie;
	public float total;
	public float ticketId;
	public boolean paid;
	@Override
	public String toString() {
		return "TicketModel [brand=" + brand + ", matriculation=" + matriculation + ", details=" + details + ", price="
				+ price + ", washing=" + washing + ", battrie=" + battrie + ", total=" + total + ", ticketId="
				+ ticketId + ", paid=" + paid + "]";
	}

	
	
	
}

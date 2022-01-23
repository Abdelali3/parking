package entities;

public class Vehicle {
	
	private static int cmp; 
	private int id;
	private String type;
	private String matriculation;
	private String brand;
	
	public Vehicle(int id, String type, String matriculation, String brand) {
		this.id = id;
		this.type = type;
		this.matriculation = matriculation;
		this.brand = brand;
	}

	public Vehicle(String type, String matriculation, String brand) {
		this.id = ++cmp;
		this.type = type;
		this.matriculation = matriculation;
		this.brand = brand;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMatriculation() {
		return matriculation;
	}

	public void setMatriculation(String matriculation) {
		this.matriculation = matriculation;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", type=" + type + ", matriculation=" + matriculation + ", brand=" + brand + "]";
	}
	
	
	
}

package entities;

public class Place {
	
	private static int cmp = 0;
	private int id;
	private boolean free;
	private String type;
	private Float priceH1;
	private Float priceH2;
	private Float priceHn;
	
	   	   
	public Place(int id, boolean free, String type, Float priceH1, Float priceH2, Float priceHn) {
		super();
		this.id = id;
		this.free = free;
		this.type = type;
		this.priceH1 = priceH1;
		this.priceH2 = priceH2;
		this.priceHn = priceHn;
	}
	   
	public Place(boolean free, String type, Float priceH1, Float priceH2, Float priceHn) {
		this.id = ++cmp;
		this.free = free;
		this.type = type;
		this.priceH1 = priceH1;
		this.priceH2 = priceH2;
		this.priceHn = priceHn;
	}

	public static int getCmp() {
		return cmp;
	}

	public static void setCmp(int cmp) {
		Place.cmp = cmp;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isFree() {
		return free;
	}

	public void setFree(boolean free) {
		this.free = free;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Float getPriceH1() {
		return priceH1;
	}

	public void setPriceH1(Float priceH1) {
		this.priceH1 = priceH1;
	}

	public Float getPriceH2() {
		return priceH2;
	}

	public void setPriceH2(Float priceH2) {
		this.priceH2 = priceH2;
	}

	public Float getPriceHn() {
		return priceHn;
	}

	public void setPriceHn(Float priceHn) {
		this.priceHn = priceHn;
	}

	@Override
	public String toString() {
		return "Place [id=" + id + ", free=" + free + ", type=" + type + ", priceH1=" + priceH1 + ", priceH2=" + priceH2
				+ ", priceHn=" + priceHn + "]";
	}

	
}

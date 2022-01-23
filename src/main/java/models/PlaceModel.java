package models;

public class PlaceModel {
	@Override
	public String toString() {
		return "PlaceModel [idPlace=" + idPlace + ", type=" + type + ", idSection=" + idSection + ", sectionName="
				+ sectionName + ", p1=" + p1 + ", p2=" + p2 + ", pn=" + pn + "]";
	}
	public int idPlace = 0;
	public String type;
	public int idSection = 0;
	public String sectionName;
	public float p1 = 0;
	public float p2 = 0;
	public float pn = 0;
}

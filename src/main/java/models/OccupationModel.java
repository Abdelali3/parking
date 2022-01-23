package models;

import java.time.LocalDateTime;

public class OccupationModel {
	public int id = 0;
	public String sectionName = "";
	public int sectionId = 0;
	public int placeId = 0;
	public String type = "";
	public LocalDateTime occupation;
	public String vehicleBrand = "";
	public String services = "";
	public String matriculation = "";
	
	
	@Override
	public String toString() {
		return "OccupationModel [id=" + id + ", sectionName=" + sectionName + ", sectionId=" + sectionId + ", placeId="
				+ placeId + ", type=" + type + ", occupation=" + occupation + ", vehicleBrand=" + vehicleBrand
				+ ", services=" + services + ", matriculation=" + matriculation + "]";
	}
	
	
}

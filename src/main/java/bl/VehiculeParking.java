package bl;

import dal.OccupationDal;
import entities.Occupation;
import entities.Place;
import entities.Vehicle;

public class VehiculeParking {
	
	
	public static boolean in(Vehicle vehicle) {
		if(!parked(vehicle)) {
			Place p = PlacesManager.findPlace(vehicle);
			if(p != null) {
				//return OccupationManager.startOccupation(p, vehicle);
			}
		}

		return false;
	}
	
	public static boolean out(Vehicle vehicle) {
		if(parked(vehicle)) {
			return OccupationManager.endOccupation(vehicle);
		}
		return false;
	}
	
	public static boolean parked(Vehicle vehicle) {
		OccupationDal od = OccupationDal.getInstance();
		for(Occupation o: od.findAll()) {
			if(o.getVehicle().getId() == vehicle.getId()) {
				if(o.getEnd() == null) {
					return true;
				}
			}
		}
		return false;
	}
}

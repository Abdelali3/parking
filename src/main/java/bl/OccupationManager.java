package bl;

import java.time.LocalDateTime;

import dal.OccupationDal;
import dal.PlaceDal;
import dal.TicketDal;
import entities.Occupation;
import entities.Place;
import entities.Ticket;
import entities.Vehicle;

public class OccupationManager {

	public static boolean startOccupation(Place place, Vehicle vehicle, String [] services) {
		OccupationDal od = OccupationDal.getInstance();
		if(services == null) {
			return od.create(new Occupation(LocalDateTime.now(), null, false, false, null, vehicle, place));
		}
		if(services.length == 2) {
			return od.create(new Occupation(LocalDateTime.now(), null, true, true, null, vehicle, place));
		} else if (services.length == 0) {
			return od.create(new Occupation(LocalDateTime.now(), null, false, false, null, vehicle, place));
		} else if(services[0] == "battrieCharging") {
			return od.create(new Occupation(LocalDateTime.now(), null, true, false, null, vehicle, place));
		} else {
			return od.create(new Occupation(LocalDateTime.now(), null, false, true, null, vehicle, place));
		}
		
	}
	
	public static boolean addservices(boolean washing, boolean batteryCharging, Occupation occupation) {
		OccupationDal od = OccupationDal.getInstance();
		occupation.setBatteryCharging(batteryCharging);
		occupation.setWashing(washing);
		return od.update(occupation);
	}
	
	public static boolean endOccupation(Occupation occupation) {
		OccupationDal od = OccupationDal.getInstance();
		PlaceDal pd = PlaceDal.getInstance(null);
		occupation.setEnd(LocalDateTime.now());
		boolean o1 = TicketManager.generateTicket(occupation);
		occupation.getPlace().setFree(true);
		boolean o2 = pd.update(occupation.getPlace());
		
		TicketManager.generateTicket(occupation);

		
		
		return o1 && o2;
	}
	
	public static boolean endOccupation(Place place) {
		OccupationDal od = OccupationDal.getInstance();
		for(Occupation o: od.findAll()) {
			if(o.getPlace().getId() != place.getId()) {
				return endOccupation(o);
			}
		}
		return false;
	}
	
	public static boolean endOccupation(Vehicle vehicle) {
		OccupationDal od = OccupationDal.getInstance();
		for(Occupation o: od.findAll()) {
			if(o.getVehicle().getId() != vehicle.getId()) {
				return endOccupation(o);
			}
		}
		return false;
	}
	
	
	public static boolean cancelOccupation(Occupation occupation) {
		OccupationDal od = OccupationDal.getInstance();
		return od.delete(occupation);
	}

	public static boolean endOccupation(int id) {
		OccupationDal od = OccupationDal.getInstance();
		
		return endOccupation(od.findById(id));
	}
}

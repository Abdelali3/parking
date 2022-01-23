package bl;

import java.util.ArrayList;
import java.util.List;

import dal.OccupationDal;
import dal.SectionDal;
import dal.TicketDal;
import dal.VehicleDal;
import entities.Occupation;
import entities.Section;
import entities.Vehicle;
import models.OccupationModel;
import models.TicketModel;

public class DataSender {

	public static List<Section> getSections() {
		SectionDal sd = SectionDal.getInstance();
		return sd.findAll();
	}

	public static List<Vehicle> getVehicles() {
		VehicleDal vd = VehicleDal.getInstance();
		return vd.findAll();
	}

	public static List<OccupationModel> getOccupations() {
		List<OccupationModel> occupations = new ArrayList<OccupationModel>();
		OccupationDal od = OccupationDal.getInstance();

		
		for (Occupation o : od.findAll()) {
			if (o.getEnd() == null) {
				OccupationModel om = new OccupationModel();
				om.id = o.getId();
				om.occupation = o.getStart();
				om.placeId = o.getPlace().getId();
				om.sectionId = PlacesManager.getSection(o.getPlace()).getId();
				om.sectionName = PlacesManager.getSection(o.getPlace()).getName();
				if(o.getBatteryCharging() && o.getWashing()) {
					om.services = "Chargement de battrie et Lavage";
				} else if (o.getBatteryCharging()) {
					om.services = " Chargement de battrie";
				} else if (o.getWashing()) {
					om.services = "Lavage";
				} else {
					om.services = "aucun";
				}
				om.type = o.getVehicle().getType();
				om.vehicleBrand = o.getVehicle().getBrand();
				om.matriculation = o.getVehicle().getMatriculation();
				occupations.add(om);
			}
		}

		return occupations;
	}

	public static List<Vehicle> getUnparkedVehicles(){
		VehicleDal vd = VehicleDal.getInstance();
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		for(Vehicle v: vd.findAll()) {
			if(!VehiculeParking.parked(v)) {
				vehicles.add(v);
			}
		}
		return vehicles;
	}
	
	public static List<TicketModel> getTickets(){
		OccupationDal od = OccupationDal.getInstance();
		List<TicketModel> tickets = new ArrayList<TicketModel>();
		for(Occupation o: od.findAll()) {
			if(o.getTicket() != null) {
				tickets.add(TicketManager.generateTicketModel(o));
			}
		}
		return tickets;
	}
	
	
}

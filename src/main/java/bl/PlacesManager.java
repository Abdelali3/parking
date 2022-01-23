package bl;

import java.util.ArrayList;
import java.util.List;

import dal.PlaceDal;
import dal.SectionDal;
import entities.Place;
import entities.Section;
import entities.Vehicle;
import models.PlaceModel;

public class PlacesManager {

	
	public static Place findPlace(Vehicle vehicle) {
		PlaceDal pd = PlaceDal.getInstance(null);
		for(Place p:  pd.findAll()) {
			if(p.isFree() && p.getType() == vehicle.getType()) {
				return p;
			}
		}
		return null;
	}

	public static Place findPlace(Vehicle vehicle, Section section) {
		PlaceDal pd = PlaceDal.getInstance(section);
		for(Place p:  pd.findAll()) {
			if(p.isFree() && p.getType() == vehicle.getType()) {
				return p;
			}
		}
		return null;
	}

	public static Section getSection(Place place) {
		SectionDal sd = SectionDal.getInstance();
		
		for(Section s: sd.findAll()) {
			for(Place p: s.getPlaces()) {
				if(p.getId() == place.getId()) {
					return s;
				}
			}
		}
		return null;
	}
	
	public static List<PlaceModel> getFreePlaces() {
		PlaceDal pd = PlaceDal.getInstance(null);
		List<PlaceModel> places = new ArrayList<PlaceModel>();
		for(Place p:  pd.findAll()) {
			if(p.isFree()) {
				PlaceModel pm = new PlaceModel();
				pm.idPlace = p.getId();
				pm.idSection = getSection(p).getId();
				pm.p1 = p.getPriceH1();
				pm.p2 = p.getPriceH2();
				pm.pn = p.getPriceHn();
				pm.sectionName = getSection(p).getName();
				pm.type = p.getType();
				places.add(pm);
			}
		}
		return places;
	}
	
}

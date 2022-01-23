package bl;

import dal.PlaceDal;
import dal.SectionDal;
import dal.VehicleDal;
import entities.Place;
import entities.Section;
import entities.Vehicle;

public class DataSaver {

	public static boolean addPlace(String sectionName, String type, float ph1, float ph2, float phn) {
		if(Verificator.checkPlace(sectionName, type, ph1, ph2, phn)) {
			SectionDal sd = SectionDal.getInstance();
			Section newSection =  sd.createThenGet(new Section(sectionName));
			PlaceDal pd = PlaceDal.getInstance(newSection);
			return pd.create(new Place(true, type, ph1,ph2, phn));
			
		} else {
			return false;
		}
	}
	
	public static boolean addPlace(int sectionId,  String type, float ph1, float ph2, float phn) {
		if(Verificator.checkPlace(sectionId, type, ph1, ph2, phn)) {
			SectionDal sd = SectionDal.getInstance();
			PlaceDal pd = PlaceDal.getInstance(sd.findById(sectionId));
			
			return pd.create(new Place(true, type, ph1, ph2, phn));
			
		} else {
			return false;
		}
		
	}
	
	public static boolean addVehicle(String type, String matricule, String brand) {
		if(Verificator.checkType(type)) {
			VehicleDal vd = VehicleDal.getInstance();
			
			return vd.create(new Vehicle(type, matricule, brand));
		} else {
			return false;
		}
	}
}

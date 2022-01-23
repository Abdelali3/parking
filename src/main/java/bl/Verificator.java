package bl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dal.OccupationDal;
import dal.SectionDal;
import dal.TicketDal;
import dal.UserDal;
import entities.Occupation;
import entities.Place;
import entities.Ticket;
import entities.User;
import entities.Vehicle;

public class Verificator {

	private static UserDal ud = UserDal.getInstance();

	public static boolean userBlocked(String email) {
		for (User u : ud.findAll()) {
			if (u.getEmail() == email) {
				return u.isBlocked();
			}
		}
		return false;
	}

	public static boolean emailUsed(String email) {
		for (User u : ud.findAll()) {
			if (u.getEmail() == email) {
				return true;
			}
		}
		return false;
	}

	public static boolean passwordValid() {
		return true;
	}

	public static boolean isAuth() {
		return false;
	}

	public static boolean isAdmin(User user) {
		return user.getRole() == "Admin";
	}

	public static boolean vehicleParked(Vehicle vehicle) { // tested
		OccupationDal od = OccupationDal.getInstance();

		for (Occupation o : od.findAll()) {
			if (o.getVehicle().getId() == vehicle.getId()) {
				if (o.getEnd() == null) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean vehicleTicketsPaid(Vehicle vehicle) { // tested
		OccupationDal od = OccupationDal.getInstance();

		for (Occupation o : od.findAll()) {
			if (o.getVehicle().getId() == vehicle.getId()) {
				if (o.getTicket() != null) {
					if (o.getTicket().getPaymentDate() == null) {
						return false;
					}
				}
			}
		}
		return true;
	}

	public static boolean placeAppropriate(Place place, Vehicle vehicle) { // tested
		if (place.getType().equals("andicape")) {
			return true;
		}

		return place.getType().equals(vehicle.getType());
	}

	public static boolean hasTicket(Occupation occupation) { // tested


		return occupation.getTicket() != null;
	}

	public static boolean checkPlace(String sectionName, String type, float ph1, float ph2, float phn) {
		System.out.println("2) sectionName = " + sectionName);
		String[] types = new String[] { "car", "moto", "electric", "handicapped", "truck" };

		for (String t : types) {
			if (t.equals(type)) {
				return true;
			}
		}

		return false;
	}

	public static boolean checkPlace(int sectionId, String type, float ph1, float ph2, float phn) {

		String[] types = new String[] { "car", "moto", "electric", "handicapped", "truck" };
		boolean checkType = false;
		for (String t : types) {
			if (t.equals(type)) {
				checkType = true;
			}
		}
		if (!checkType) {
			return false;
		}

		SectionDal sd = SectionDal.getInstance();
		return sd.findById(sectionId) != null;
	}

	public static boolean checkType(String type) {
		String[] types = new String[] { "car", "moto", "electric", "handicapped", "truck" };
		for (String t : types) {
			if (t.equals(type)) {
				return true;
			}
		}
		return false;
	}
}

package test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import bl.DataSender;
import bl.TicketManager;
import bl.VehiculeParking;
import bl.Verificator;
import dal.OccupationDal;
import dal.PlaceDal;
import dal.SectionDal;
import dal.TicketDal;
import dal.UserDal;
import dal.VehicleDal;
import entities.Occupation;
import entities.Place;
import entities.Section;
import entities.Ticket;
import entities.User;
import entities.Vehicle;

public class Test {



	public static void main(String[] args) {

		OccupationDal od = OccupationDal.getInstance();
		PlaceDal pd = PlaceDal.getInstance(null);
		Occupation occupation = od.findById(1);
		
		VehicleDal vd = VehicleDal.getInstance();
		
		System.out.println(DataSender.getTickets());

		
	}

}

package bl;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import dal.OccupationDal;
import dal.TicketDal;
import entities.Occupation;
import entities.Place;
import entities.Ticket;
import models.TicketModel;

public class TicketManager {

	public static boolean generateTicket(Occupation occupation) {

		if (Verificator.hasTicket(occupation)) {
			return false;
		}
		TicketDal td = TicketDal.getInstance();
		OccupationDal od = OccupationDal.getInstance();

		Place p = occupation.getPlace();

		long hours = ChronoUnit.HOURS.between(occupation.getStart(), occupation.getEnd());

		if (ChronoUnit.MINUTES.between(occupation.getStart(), occupation.getEnd()) > hours * 60) {
			hours++;
		}
		float amount = 0;
		if (hours == 1) {
			amount = p.getPriceH1();
		} else if (hours == 2) {
			amount = p.getPriceH1() + p.getPriceH2();
		} else {
			amount = p.getPriceH1() + p.getPriceH2();
			hours = hours - 2;
			while (hours > 0) {
				amount += p.getPriceHn();
				hours--;
			}
		}
		if (occupation.getWashing()) {
			amount += 30;
		}

		if (occupation.getBatteryCharging()) {
			amount += 100;
		}

		occupation.setTicket(td.createThenGet(new Ticket(amount, new Date(), null)));
		od.update(occupation);

		return occupation.getTicket() != null;
	}

	public static TicketModel generateTicketModel(Occupation occupation) {

		TicketModel tm = new TicketModel();
		tm.paid = occupation.getTicket().getPaymentDate() != null;
		tm.brand = occupation.getVehicle().getBrand();
		tm.matriculation = occupation.getVehicle().getMatriculation();
		tm.ticketId = occupation.getTicket().getId();
		tm.details = "Occupation de ";
		
		
		OccupationDal od = OccupationDal.getInstance();

		Place p = occupation.getPlace();

		long hours = ChronoUnit.HOURS.between(occupation.getStart(), occupation.getEnd());

		if (ChronoUnit.MINUTES.between(occupation.getStart(), occupation.getEnd()) > hours * 60) {
			hours++;
		}
		tm.details += hours + "H<br>";
		tm.details += "Du "+occupation.getStart()+" jusqu'au "+occupation.getEnd()+"<br>";
		
		
		
		
		float amount = 0;
		if (hours == 1) {
			amount = p.getPriceH1();
			tm.details += " 1ère H = "+occupation.getPlace().getPriceH1()+" DH ";
		} else if (hours == 2) {
			amount = p.getPriceH1() + p.getPriceH2();
			tm.details += " 1ère H = "+occupation.getPlace().getPriceH1()+" DH ";
			tm.details += " 2ème H = "+occupation.getPlace().getPriceH2()+" DH ";
		} else {
			amount = p.getPriceH1() + p.getPriceH2();
			hours = hours - 2;
			tm.details += " 1ère H = "+occupation.getPlace().getPriceH1()+" DH ";
			tm.details += " 2ème H = "+occupation.getPlace().getPriceH2()+" DH ";
			tm.details += hours + "H (  "+occupation.getPlace().getPriceHn()+"DH chaqu'une )";
			while (hours > 0) {
				amount += p.getPriceHn();
				hours--;
			}
		}
		tm.price = amount;
		
		if (occupation.getWashing()) {
			tm.washing = 30;
			amount += 30;
		} else {
			tm.washing = 0;
		}

		if (occupation.getBatteryCharging()) {
			tm.battrie = 100;
			amount += 100;
		} else {
			tm.battrie = 30;
		}
		tm.total = amount;
		return tm;
	}

	public static boolean pay(Ticket ticket) {
		ticket.setPaymentDate(new Date());
		TicketDal td = TicketDal.getInstance();

		return td.update(ticket);
	}

	public static boolean cancelTicket(Ticket ticket) {
		TicketDal td = TicketDal.getInstance();
		return td.delete(ticket);
	}

}
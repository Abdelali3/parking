package bl;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import dal.OccupationDal;
import dal.PlaceDal;
import dal.TicketDal;
import dal.VehicleDal;
import entities.Occupation;
import entities.Place;
import entities.Ticket;

public class Statistics {

	
	
	public static  models.Statistics getStatistics() {
		models.Statistics s = new models.Statistics();
		TicketDal td = TicketDal.getInstance();
		
		for(Ticket t: td.findAll()) {
			if(t.getPaymentDate() != null) {
				s.payed++;
				if(getDay(t.getPaymentDate()) == 1){
					s.dimanche += t.getAmount();
				} else if(getDay(t.getPaymentDate()) == 2){
					s.lundi += t.getAmount();
				} else if(getDay(t.getPaymentDate()) == 3){
					s.mardi += t.getAmount();
				}  else if(getDay(t.getPaymentDate()) == 4){
					s.mercredi += t.getAmount();
				}  else if(getDay(t.getPaymentDate()) == 5){
					s.jeudi += t.getAmount();
				}  else if(getDay(t.getPaymentDate()) == 6){
					s.vendredi += t.getAmount();
				}  else if(getDay(t.getPaymentDate()) == 7){
					s.samedi += t.getAmount();
				} 
				
			} else {
				s.unpayed++;
			}
			

		}
		PlaceDal pd = PlaceDal.getInstance(null);
		for(Place p: pd.findAll()){
			if(p.getType().equals("moto")) {
				s.motos++;
			} else if(p.getType().equals("electric")) {
				s.electrics++;
			} else if(p.getType().equals("car")) {
				s.cars++;
			} else if(p.getType().equals("truck")) {
				s.trucks++;
			} else {
				s.handicappeds++;
			}
		}
		
		return s;
	}
	
	private static int getDay(Date paymentDate) {
		Calendar cal = dateToCalendar(paymentDate);
        int day = cal.get(Calendar.DAY_OF_WEEK);
        return day;
	}

	
	private static Calendar dateToCalendar(Date date) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;

	}
	

}

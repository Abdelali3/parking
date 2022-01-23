package dal;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import entities.Occupation;

public class OccupationDal implements IDao <Occupation> {
	
	
	private static OccupationDal vd;
	
	
	private OccupationDal() {
		
	}
	
	public static OccupationDal getInstance() {
		if(vd == null) {
			vd = new OccupationDal();
		}
		return vd;
	}
	

	@Override
	public boolean create(Occupation o) {
		try {
			String req = "insert into occupations values (null, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pr = Connexion.getConnection().prepareStatement(req);
			pr.setTimestamp(1, Timestamp.valueOf(o.getStart()));
			if(o.getEnd() != null) {
				pr.setTimestamp(2, Timestamp.valueOf(o.getEnd()));
			} else {
				pr.setDate(2,  null);
			}
			pr.setBoolean(3, o.getBatteryCharging());
			pr.setBoolean(4, o.getWashing());
			pr.setString(5, null);
			pr.setInt(6, o.getVehicle().getId());
			pr.setInt(7, o.getPlace().getId());
			
			if (pr.executeUpdate() == 1)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Occupation o) {
		TicketDal td = TicketDal.getInstance();
		if(o.getTicket() != null) {
			td.delete(o.getTicket());
		}
		try {
			String req = "delete from occupations where id = ?";
			PreparedStatement pr = Connexion.getConnection().prepareStatement(req);
			pr.setInt(1, o.getId());

			if (pr.executeUpdate() == 1)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(Occupation o) {
		try {
			String req = "update occupations set start = ?,  end = ?,  batteryCharging = ?,  washing = ?,  vehicle_id = ?, place_id = ? where id = ?";
			PreparedStatement pr = Connexion.getConnection().prepareStatement(req);
			pr.setTimestamp(1, Timestamp.valueOf(o.getStart()));
			if(o.getEnd() != null) {
				pr.setTimestamp(2, Timestamp.valueOf(o.getEnd()));
			} else {
				pr.setDate(2,  null);
			}
			pr.setBoolean(3, o.getBatteryCharging());
			pr.setBoolean(4, o.getWashing());
			if(o.getTicket() == null) {
				pr.setString(5, null);
			} else {
				pr.setInt(5, o.getTicket().getId());
			}
			
			pr.setInt(5, o.getVehicle().getId());
			pr.setInt(6, o.getPlace().getId());
			pr.setInt(7, o.getId());
			
			if (pr.executeUpdate() == 1)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Occupation findById(int id) {
		try {
			String req = "select * from occupations where id = ?";
			PreparedStatement pr = Connexion.getConnection().prepareStatement(req);
			pr.setInt(1, id);
			ResultSet rs = pr.executeQuery();
			VehicleDal vd = VehicleDal.getInstance(); 
			TicketDal td = TicketDal.getInstance();
			PlaceDal pd = PlaceDal.getInstance(null);
			
			if (rs.next()) {
				return new Occupation(rs.getInt("id"), rs.getObject("start", LocalDateTime.class), rs.getObject("end", LocalDateTime.class),  rs.getBoolean("batterycharging"), rs.getBoolean("Washing"),
						td.findById(rs.getInt("ticket_id")), vd.findById(rs.getInt("vehicle_id")), pd.findById(rs.getInt("place_id")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Occupation> findAll() {
		List<Occupation> occupations = new ArrayList<Occupation>();
		try {
			String req = "select * from occupations";
			PreparedStatement pr = Connexion.getConnection().prepareStatement(req);
			ResultSet rs = pr.executeQuery();
			VehicleDal vd = VehicleDal.getInstance(); 
			TicketDal td = TicketDal.getInstance();
			PlaceDal pd = PlaceDal.getInstance(null);
			while (rs.next()) {
				occupations.add(new Occupation(rs.getInt("id"), rs.getObject("start", LocalDateTime.class), rs.getObject("end", LocalDateTime.class),  rs.getBoolean("batterycharging"), rs.getBoolean("Washing"),
						td.findById(rs.getInt("ticket_id")), vd.findById(rs.getInt("vehicle_id")), pd.findById(rs.getInt("place_id"))));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return occupations;
	}
}
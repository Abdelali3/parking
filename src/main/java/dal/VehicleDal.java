package dal;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Vehicle;

public class VehicleDal implements IDao <Vehicle> {
	
	
	private static VehicleDal vd;
	
	
	private VehicleDal() {
		
	}
	
	public static VehicleDal getInstance() {
		if(vd == null) {
			vd = new VehicleDal();
		}
		return vd;
	}
	

	@Override
	public boolean create(Vehicle o) {
		try {
			String req = "insert into vehicles values (null, ?, ?, ?)";
			PreparedStatement pr = Connexion.getConnection().prepareStatement(req);
			pr.setString(1, o.getType());
			pr.setString(2, o.getMatriculation());
			pr.setString(3, o.getBrand());

			if (pr.executeUpdate() == 1)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Vehicle o) {
		try {
			String req = "delete from vehicles where id = ?";
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
	public boolean update(Vehicle o) {
		try {
			String req = "update vehicles set type = ?,  matriculation = ?,  brand = ? where id = ?";
			PreparedStatement pr = Connexion.getConnection().prepareStatement(req);
			pr.setString(1, o.getType());
			pr.setString(2, o.getMatriculation());
			pr.setString(3, o.getBrand());
			pr.setInt(4, o.getId());
			if (pr.executeUpdate() == 1)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Vehicle findById(int id) {
		try {
			String req = "select * from vehicles where id = ?";
			PreparedStatement pr = Connexion.getConnection().prepareStatement(req);
			pr.setInt(1, id);
			ResultSet rs = pr.executeQuery();
			if (rs.next()) {
				return new Vehicle(rs.getInt("id"), rs.getString("type"), rs.getString("matriculation"), rs.getString("brand"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Vehicle> findAll() {
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		try {
			String req = "select * from vehicles";
			PreparedStatement pr = Connexion.getConnection().prepareStatement(req);
			ResultSet rs = pr.executeQuery();
			while (rs.next()) {
				vehicles.add(new Vehicle(rs.getInt("id"), rs.getString("type"), rs.getString("matriculation"), rs.getString("brand")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vehicles;
	}
}
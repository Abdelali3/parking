package dal;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Place;
import entities.Section;

public class PlaceDal  implements IDao <Place> {
	
	
	private static PlaceDal ud;
	private Section section;
	
	private PlaceDal(Section s) {
		section = s;
	}

	
	public static PlaceDal getInstance(Section s) {
		
		ud = new PlaceDal(s);

		return ud;
	}
	

	
	
	@Override
	public boolean create(Place o) {
		try {
			String req = "insert into places values (null, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pr = Connexion.getConnection().prepareStatement(req);
			pr.setBoolean(1, o.isFree());
			pr.setString(2, o.getType());
			pr.setFloat(3, o.getPriceH1());
			pr.setFloat(4, o.getPriceH2());
			pr.setFloat(5, o.getPriceHn());
			pr.setInt(6, section.getId());
			
			if (pr.executeUpdate() == 1)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Place o) {
		try {
			String req = "delete from places where id = ?";
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
	public boolean update(Place o) {
		try {
			String req = "update places set free = ?,  type = ?,  priceH1 = ?,  priceH2 = ?,  priceHn = ? where id = ?";
			PreparedStatement pr = Connexion.getConnection().prepareStatement(req);
			pr.setBoolean(1, o.isFree());
			pr.setString(2, o.getType());
			pr.setFloat(3, o.getPriceH1());
			pr.setFloat(4, o.getPriceH2());
			pr.setFloat(5, o.getPriceHn());
			pr.setInt(6, o.getId());
			if (pr.executeUpdate() == 1)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Place findById(int id) {
		try {
			PreparedStatement pr;
			if(section != null) {
				String req = "select * from places where id = ? and section_id = ?";
				pr = Connexion.getConnection().prepareStatement(req);
				pr.setInt(1, id);
				pr.setInt(2, section.getId());
			} else {
				String req = "select * from places where id = ?";
				pr = Connexion.getConnection().prepareStatement(req);
				pr.setInt(1, id);
				
			}
			
			ResultSet rs = pr.executeQuery();
			if (rs.next()) {
				return new Place(rs.getInt("id"), rs.getBoolean("free"), rs.getString("type"), rs.getFloat("priceH1"), rs.getFloat("priceH2"), rs.getFloat("priceHn"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Place> findAll() {
		List<Place> tickets = new ArrayList<Place>();
		try {
			PreparedStatement pr;
			if(section != null) {
				String req = "select * from places where section_id = ?";
				pr = Connexion.getConnection().prepareStatement(req);
				pr.setInt(1, section.getId());
			} else {
				String req = "select * from places";
				pr = Connexion.getConnection().prepareStatement(req);
			}
			
			ResultSet rs = pr.executeQuery();
			while (rs.next()) {
				tickets.add(new Place(rs.getInt("id"), rs.getBoolean("free"), rs.getString("type"), rs.getFloat("priceH1"), rs.getFloat("priceH2"), rs.getFloat("priceHn")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tickets;
	}
}
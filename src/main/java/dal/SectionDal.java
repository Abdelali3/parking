package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.Place;
import entities.Section;
import entities.Ticket;

public class SectionDal  implements IDao <Section> {
	
	
	private static SectionDal sd;
	private SectionDal() {
	}
	
	public static SectionDal getInstance() {

		if(sd == null) {
			sd = new SectionDal();
		}
		
		return sd;
	}
	

	@Override
	public boolean create(Section o) {
		try {
			String req = "insert into sections values (null, ?)";
			PreparedStatement pr = Connexion.getConnection().prepareStatement(req);
			pr.setString(1, o.getName());

			
			if (pr.executeUpdate() == 1)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Section o) {
		try {
			String req = "delete from sections where id = ?";
			PreparedStatement pr = Connexion.getConnection().prepareStatement(req);
			pr.setInt(1, o.getId());
			

			PlaceDal pd = PlaceDal.getInstance(o);

			for(Place p: pd.findAll()) {
				pd.delete(p);
			}

			if (pr.executeUpdate() == 1)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(Section o) {
		try {
			String req = "update sections set name = ? where id = ?";
			PreparedStatement pr = Connexion.getConnection().prepareStatement(req);
			pr.setString(1, o.getName());
			pr.setInt(2, o.getId());
			
			if (pr.executeUpdate() == 1)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Section findById(int id) {
		try {
			String req = "select * from sections where id = ?";
			PreparedStatement pr = Connexion.getConnection().prepareStatement(req);
			pr.setInt(1, id);
			ResultSet rs = pr.executeQuery();
			if (rs.next()) {
				PlaceDal pd = PlaceDal.getInstance(new Section(rs.getInt("id"), rs.getString("name")));
				
				return new Section(rs.getInt("id"), rs.getString("name"), pd.findAll());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Section> findAll() {
		List<Section> sections = new ArrayList<Section>();
		try {
			String req = "select * from sections";
			PreparedStatement pr = Connexion.getConnection().prepareStatement(req);
			ResultSet rs = pr.executeQuery();
			while (rs.next()) {
				PlaceDal pd = PlaceDal.getInstance(new Section(rs.getInt("id"), rs.getString("name")));
				
				sections.add(new Section(rs.getInt("id"), rs.getString("name"), pd.findAll()));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sections;
	}
	
	
	public Section createThenGet(Section o) {
		try {
			String req = "insert into sections values (null, ?)";
			PreparedStatement pr = Connexion.getConnection().prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
			pr.setString(1, o.getName());


			pr.execute();
			ResultSet rs = pr.getGeneratedKeys();
			int generatedKey = 0;
			if (rs.next()) {
			    generatedKey = rs.getInt(1);
			    return findById(generatedKey);
			}

				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
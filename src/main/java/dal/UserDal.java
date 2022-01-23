package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.User;


public class UserDal implements IDao <User> {
	
	
	private static UserDal ud;
	
	
	private UserDal() {
		
	}
	
	public static UserDal getInstance() {
		if(ud == null) {
			ud = new UserDal();
		}
		return ud;
	}
	

	@Override
	public boolean create(User o) {
		try {
			String req = "insert into users values (null, ?, ?, ?, ?, ?)";
			PreparedStatement pr = Connexion.getConnection().prepareStatement(req);
			pr.setString(1, o.getLastname());
			pr.setString(2, o.getFirstname());
			pr.setString(3, o.getPassword());
			pr.setString(4, o.getEmail());
			pr.setString(5, o.getRole());
			if (pr.executeUpdate() == 1)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(User o) {
		try {
			String req = "delete from users where id = ?";
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
	public boolean update(User o) {
		try {
			String req = "update users set lastname = ?,  firstname = ?,  password = ?,  email = ?,  role = ? where id = ?";
			PreparedStatement pr = Connexion.getConnection().prepareStatement(req);
			pr.setString(1, o.getLastname());
			pr.setString(2, o.getFirstname());
			pr.setString(3, o.getPassword());
			pr.setString(4, o.getEmail());
			pr.setString(5, o.getRole());
			pr.setInt(6, o.getId());
			if (pr.executeUpdate() == 1)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public User findById(int id) {
		try {
			String req = "select * from users where id = ?";
			PreparedStatement pr = Connexion.getConnection().prepareStatement(req);
			pr.setInt(1, id);
			ResultSet rs = pr.executeQuery();
			if (rs.next()) {
				System.out.println("get In");
				return new User(rs.getInt("id"), rs.getString("lastname"), rs.getString("firstname"), rs.getString("password"), rs.getString("email"), rs.getString("role"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<User> findAll() {
		List<User> users = new ArrayList<User>();
		try {
			String req = "select * from users";
			PreparedStatement pr = Connexion.getConnection().prepareStatement(req);
			ResultSet rs = pr.executeQuery();
			while (rs.next()) {
				users.add(new User(rs.getInt("id"), rs.getString("lastname"), rs.getString("firstname"), rs.getString("password"), rs.getString("email"), rs.getString("role")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	public User findByEmail(String email) {
		try {
			String req = "select * from users where email = ?";
			PreparedStatement pr = Connexion.getConnection().prepareStatement(req);
			pr.setString(1, email);
			ResultSet rs = pr.executeQuery();
			if (rs.next()) {
				return new User(rs.getInt("id"), rs.getString("lastname"), rs.getString("firstname"), rs.getString("password"), rs.getString("email"), rs.getString("role"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
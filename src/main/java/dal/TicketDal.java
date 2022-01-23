package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entities.Ticket;

public class TicketDal implements IDao <Ticket> {
	
	
	private static TicketDal ud;
	
	
	private TicketDal() {
		
	}
	
	public static TicketDal getInstance() {
		if(ud == null) {
			ud = new TicketDal();
		}
		return ud;
	}
	

	@Override
	public boolean create(Ticket o) {
		try {
			String req = "insert into tickets values (null, ?, ?, ?)";
			PreparedStatement pr = Connexion.getConnection().prepareStatement(req);
			pr.setFloat(1, o.getAmount());
			pr.setDate(2, new Date(o.getCreationDate().getTime()));

			if(o.getPaymentDate() != null) {
				pr.setDate(3,  new Date(o.getPaymentDate().getTime()));
			} else {
				pr.setDate(3,  null);
			}

			if (pr.executeUpdate() == 1)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Ticket o) {
		try {
			String req = "delete from tickets where id = ?";
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
	public boolean update(Ticket o) {
		try {
			String req = "update tickets set amount = ?,  creationDate = ?,  paymentDate = ? where id = ?";
			PreparedStatement pr = Connexion.getConnection().prepareStatement(req);
			pr.setFloat(1, o.getAmount());
			pr.setDate(2, new Date(o.getCreationDate().getTime()));
			pr.setDate(3, new Date(o.getPaymentDate().getTime()));
			pr.setInt(4, o.getId());
			if (pr.executeUpdate() == 1)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Ticket findById(int id) {
		try {
			String req = "select * from tickets where id = ?";
			PreparedStatement pr = Connexion.getConnection().prepareStatement(req);
			pr.setInt(1, id);
			ResultSet rs = pr.executeQuery();
			if (rs.next()) {
				return new Ticket(rs.getInt("id"), rs.getFloat("amount"), rs.getDate("creationDate"), rs.getDate("paymentDate"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Ticket createThenGet(Ticket o) {
		try {
			String req = "insert into tickets values (null, ?, ?, ?)";
			PreparedStatement pr = Connexion.getConnection().prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
			pr.setFloat(1, o.getAmount());
			pr.setDate(2, new Date(o.getCreationDate().getTime()));

			if(o.getPaymentDate() != null) {
				pr.setDate(3,  new Date(o.getPaymentDate().getTime()));
			} else {
				pr.setDate(3,  null);
			}

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
	

	@Override
	public List<Ticket> findAll() {
		List<Ticket> tickets = new ArrayList<Ticket>();
		try {
			String req = "select * from tickets";
			PreparedStatement pr = Connexion.getConnection().prepareStatement(req);
			ResultSet rs = pr.executeQuery();
			while (rs.next()) {
				tickets.add(new Ticket(rs.getInt("id"), rs.getFloat("amount"), rs.getDate("creationDate"), rs.getDate("paymentDate")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tickets;
	}
}
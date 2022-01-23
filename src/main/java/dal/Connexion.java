package dal;


import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
	private static Connection connection;

	static {
		try {
			String dbServer = "mysql-66947-0.cloudclusters.net"; 
	        int dbPort = 19054; 
	        String dbName = "parking";
	        String userName = "admin";
	        String password = "GtWNbjgE";
	        String url = String.format("jdbc:mysql://%s:%d?user=%s&password=%s", 
	                                    dbServer, dbPort, userName, password);
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url);
            Statement stmt = connection.createStatement();
            // change database
            String sqlusedb = "use " + dbName;
            int result = stmt.executeUpdate(sqlusedb);
            		} catch (ClassNotFoundException e) {
			System.out.println("Impossible de charger le driver");
		} catch (SQLException e) {
			System.out.println("Impossible d'étabir la connexion");
		}

	}

	public static Connection getConnection() {
		return connection;
	}

}

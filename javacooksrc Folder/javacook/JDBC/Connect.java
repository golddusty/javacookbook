import java.awt.*;
import java.sql.*;

/** Load a driver and connect to a database.
 */
public class Connect {

	public static void main(String[] av) {
		String dbURL = "jdbc:odbc:Companies";
	    try {
			// Load the jdbc-odbc bridge driver
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

			// Enable logging
			DriverManager.setLogStream(System.err);

			System.out.println("Getting Connection");
			Connection conn = 
				DriverManager.getConnection(dbURL, "ian", "");	// user, passwd

			// If a SQLWarning object is available, print its
			// warning(s).  There may be multiple warnings chained.

			SQLWarning warn = conn.getWarnings();
			while (warn != null) {
				System.out.println("SQLState: " + warn.getSQLState());
				System.out.println("Message:  " + warn.getMessage());
				System.out.println("Vendor:   " + warn.getErrorCode());
				System.out.println("");
				warn = warn.getNextWarning();
		    }

			// Process the connection here...

			conn.close();	// All done with that DB connection

	    } catch (ClassNotFoundException e) {
			System.out.println("Can't load driver " + e);
	    } catch (SQLException e) {
			System.out.println("Database access failed " + e);
	    }
	}
}

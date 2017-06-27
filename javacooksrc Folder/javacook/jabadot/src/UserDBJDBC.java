package jabadot;

import java.sql.*;
import java.io.*;
import java.util.*;

/** A UserDB using JDBC and a relational DBMS..
 * We use the inherited getUser ("Find the User object for a given nickname")
 * since we keep everything in memory in this version.
 * <br/>
 * XXX SHOULD BE AN ENTITY EJB
 */
public class UserDBJDBC extends UserDB {

	protected PreparedStatement setPasswordStatement;
	protected PreparedStatement addUserStmt;
	protected PreparedStatement setLastLoginStmt;
	protected PreparedStatement deleteUserStmt;

	/** insert the dozen or so fields into the user database */
	final static String SQL_INSERT_USER =
		"insert into users values (?,?,?,?,?,?,?,?,?,?,?,?)";

	/** Default constructor */
	protected UserDBJDBC() 
	throws ClassNotFoundException, SQLException, IOException {
		super();


		// Load the database driver
		String className = JDConstants.getProperty("jabadot.jabadb.driver");
		Class.forName(className);

		Connection conn = DriverManager.getConnection(
			JDConstants.getProperty("jabadot.jabadb.url"),
			JDConstants.getProperty("jabadot.jabadb.user"),
			JDConstants.getProperty("jabadot.jabadb.password"));

		Statement stmt = conn.createStatement();

		ResultSet rs = stmt.executeQuery("select * from users");

		while (rs.next()) {
			//name:password:fullname:City:Prov:Country:privs

			// Get the fields from the query.
			// Should be an Entity EJB with CMP: this is unnecessarily 
			// chummy with the SQL. See CreateUserDatabase.java for field#'s!
			int i = 1;
			String nick = rs.getString(i++).trim();
			String pass = rs.getString(i++).trim();
System.err.println(nick + " (" + pass + ")");
			String first = rs.getString(i++).trim();
			String last = rs.getString(i++).trim();
			String email = rs.getString(i++).trim();
			String city = rs.getString(i++).trim();
			String prov = rs.getString(i++).trim();
			String ctry = rs.getString(i++).trim();
			java.sql.Date credt = rs.getDate(i++);
			java.sql.Date lastlog = rs.getDate(i++);
			// String skin = rs.getString(i++).trim();
			boolean editPrivs = rs.getBoolean(i++);
			boolean adminPrivs = rs.getBoolean(i++);

			// Construct a user object from the fields
			User u = new User(nick, pass, first, last, email,
				prov, ctry, credt, lastlog,
				"", editPrivs, adminPrivs);

			// Add it to the in-memory copy.
			users.add(u);
System.err.println("User " + nick + "; pass " + pass.charAt(0));
		}
		stmt.close();
		rs.close();		// All done with that resultset

		// Set up the PreparedStatements now so we don't have to
		// re-create them each time needed.
		addUserStmt = conn.prepareStatement(SQL_INSERT_USER);
		setPasswordStatement = conn.prepareStatement(
			"update users SET password = ? where name = ?");
		setLastLoginStmt = conn.prepareStatement(
			"update users SET lastLogin = ? where name = ?");
		deleteUserStmt = conn.prepareStatement(
			"delete from users where name = ?");
	}

	/** Add one user to the list, both in-memory and on disk. */
	public synchronized void addUser(User nu)
	throws IOException, SQLException {
		// Add it to the in-memory list
		super.addUser(nu);

		// Copy fields from user to DB
		// XXX WAY INCOMPLETE NOW
		int i = 1;
		addUserStmt.setString(i++, nu.name);
		addUserStmt.setString(i++, nu.password);
		addUserStmt.setString(i++, nu.firstName); 
		addUserStmt.setString(i++, nu.lastName);
		addUserStmt.setString(i++, nu.email);
		addUserStmt.setString(i++, nu.city);
		addUserStmt.setString(i++, nu.prov);
		addUserStmt.setString(i++, nu.country);
		// java.sql.Date now = new java.sql.Date(System.currentTimeMillis());
		// addUserStmt.setDate(i++, now);
		// addUserStmt.setDate(i++, now);
		// addUserStmt.setString(i++, nu.skin);
		// addUserStmt.setInt   (i++, nu.getPrivs());

		// Store in persistent DB
		addUserStmt.executeUpdate();
	}

	public void deleteUser(String nick) throws SQLException {
		// Find the user object
		User u = getUser(nick);
		if (u == null) {
			throw new SQLException("User " + nick + " not in in-memory DB");
		}
		deleteUserStmt.setString(1, nick);
		int n = deleteUserStmt.executeUpdate();
		if (n != 1) {	// not just one row??
			/*CANTHAPPEN */
			throw new SQLException("ERROR: deleted " + n + " rows!!");
		}

		// IFF we deleted it from the DB, also remove from the in-memory list
		users.remove(u);
	}

	public synchronized void setPassword(String nick, String newPass) 
	throws SQLException {

		// Find the user object
		User u = getUser(nick);

		// Change it in DB first; if this fails, the info in
		// the in-memory copy won't be changed either.
		setPasswordStatement.setString(1, newPass);
		setPasswordStatement.setString(2, nick);
		setPasswordStatement.executeUpdate();

		// Change it in-memory
		u.setPassword(newPass);
	}

	/** Update the Last Login Date field. */
	public synchronized void setLoginDate(String nick, java.util.Date date) 
	throws SQLException {
	
		// Find the user object
		User u = getUser(nick);

		// Change it in DB first; if this fails, the date in
		// the in-memory copy won't be changed either.
		// Have to convert from java.util.Date to java.sql.Date here.
		// Would be more efficient to use java.sql.Date everywhere.
		setLastLoginStmt.setDate(1, new java.sql.Date(date.getTime()));
		setLastLoginStmt.setString(2, nick);
		setLastLoginStmt.executeUpdate();

		// Change it in-memory
		u.setLastLoginDate(date);
	}
}

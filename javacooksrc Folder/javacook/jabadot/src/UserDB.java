package jabadot;

import java.io.*;
import java.util.*;
import java.sql.SQLException;	// Only used by JDBC version
import java.lang.reflect.*;		// For loading our subclass class.

/** A base for several "database" accessors for User objects.
 * We use a Singleton access method for efficiency and to enforce
 * single access on the database, which means we can keep an in-memory
 * copy (in an ArrayList) perfectly in synch with the database.
 *
 * Of course this really should be an EJB(!). But then JabaDot
 * would need a full EJB server to run, not just Tomcat and idb.
 */
public abstract class UserDB {

	/** The in-memory copy of the data */
	protected ArrayList users;

	/** The only instance of this class. */
	protected static UserDB singleton;

	/** Static code block to intialize the Singleton.
	 * It is created as the non-abstract subclass formerly named
	 * in the context parameter "jabadot.jabadb.class"
	 * but now hardcoded as "jabadot.UserDBJDBC"
	 */
	static {
		String dbClass = null;
		try {
			dbClass = "jabadot.UserDBJDBC";
			singleton = (UserDB)Class.forName(dbClass).newInstance();
		} catch (ClassNotFoundException ex) {
			System.err.println("Unable to instantiate UserDB singleton " + 
				dbClass + " (" + ex.toString() + ")");
			throw new IllegalArgumentException(ex.toString());
		} catch (Exception ex) {
			System.err.println(
			"Unexpected exception: Unable to initialize UserDB singleton");
			ex.printStackTrace(System.err);
			throw new IllegalArgumentException(ex.toString());
		}
	}

	/** In some subclasses the constructor will probably load the database,
	 *  while in others it may defer this until getUserList().
	 */
	protected UserDB() throws IOException, SQLException {
		users = new ArrayList();
	}

	/** "factory" method to get an instance, which will always be
	 * the Singleton.
	 */
	public static UserDB getInstance() {
		if (singleton == null)
			throw new IllegalStateException(
				"UserDB initialization failed (singleton was null)");
		return singleton;
	}

	/** Get the list of users. */
	public ArrayList getUserList() {
		return users;
	}

	/** Get the User object for a given nickname */
	public User getUser(String nick) {
		Iterator it = users.iterator();
		while (it.hasNext()) {
			User u = (User)it.next();
			if (u.getName().equals(nick))
				return u;
		}
		return null;
	}

	public synchronized void addUser(User nu) throws IOException, SQLException {
		// Add it to the in-memory list
		users.add(nu);

		// Add it to the on-disk version
		// N.B. - must be done in subclass.
	}

	public abstract void setPassword(String nick, String newPass) 
	throws SQLException;

	public abstract void deleteUser(String nick)
	throws SQLException;
}

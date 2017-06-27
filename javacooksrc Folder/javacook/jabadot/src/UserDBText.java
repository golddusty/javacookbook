package jabadot;

import java.io.*;
import java.util.*;
import java.sql.SQLException;

/** A trivial "database" for User objects, stored in a flat file.
 * <P>
 * Since this is exected to be used heavily, and to avoid the overhead
 * of re-reading the file, the "Singleton" Design Pattern is used
 * to ensure that there is only ever one instance of this class.
 */
public class UserDBText extends UserDB {
	protected final static String DEF_NAME = 
		"/home/ian/src/jabadot/jabadb.txt";

	protected String fileName;

	protected UserDBText() throws IOException,SQLException {
		this(DEF_NAME);
	}

	/** Constructor */
	protected UserDBText(String fn) throws IOException,SQLException {
		super();
		fileName = fn;
		BufferedReader is = new BufferedReader(new FileReader(fn));
		String line;
		while ((line = is.readLine()) != null) {
			//name:password:fullname:City:Prov:Country:privs

			if (line.startsWith("#")) {		// comment
				continue;
			}

			StringTokenizer st =
				new StringTokenizer(line, ":");
			String nick = st.nextToken();
			String pass = st.nextToken();
			String full = st.nextToken();
			String email = st.nextToken();
			String city = st.nextToken();
			String prov = st.nextToken();
			String ctry = st.nextToken();
			User u = new User(nick, pass, full, email,
				city, prov, ctry);
			String privs = st.nextToken();
			if (privs.indexOf("A") != -1) {
				u.setAdminPrivileged(true);
			}
			users.add(u);
		}
	}

	protected PrintWriter pw;

	public synchronized void addUser(User nu) throws IOException,SQLException {
		// Add it to the in-memory list
		super.addUser(nu);

		// Add it to the on-disk version
		if (pw == null) {
			pw = new PrintWriter(new FileWriter(fileName, true));
		}
		pw.println(toDB(nu));
		// toDB returns: name:password:fullname:City:Prov:Country:privs
		pw.flush();
	}

	protected String toDB(User u) {
		// #name:password:fullName:email:City:Prov:Country:privs
		char privs = '-';
		if (adminPrivs)
			privs = 'A';
		else if (editPrivs) 
			privs = 'E';
		
		return new StringBuffer()
			.append(u.name).append(':')
			.append(u.password).append(':')
			.append(u.fullName).append(':')
			.append(u.email).append(':')
			.append(u.city).append(':')
			.append(u.prov).append(':')
			.append(u.country).append(':')
			.append(u.privs)
			.toString();
	}
}

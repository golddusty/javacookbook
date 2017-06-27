import java.io.*;
import java.net.*;
import java.util.*;

/**
 * DaytimeObject - connect to the Daytime (ascii) service.
 * @author Ian F. Darwin
 * @version $Id: DaytimeObject.java,v 1.3 2001/04/30 15:17:44 ian Exp $
 */
public class DaytimeObject {
	/** The TCP port for the object time service. */
	public static final short TIME_PORT = 1951;

	public static void main(String[] argv) {
		String hostName;
		if (argv.length == 0)
			hostName = "localhost";
		else
			hostName = argv[0];

		try {
			Socket sock = new Socket(hostName, TIME_PORT);
			ObjectInputStream is = new ObjectInputStream(new 
				BufferedInputStream(sock.getInputStream()));

			// Read and validate the Object
			Object o = is.readObject();
			if (o == null) {
				System.err.println("Read null from server!");
			} else if ((o instanceof Date)) {

				// Valid, so cast to Date, and print
				Date d = (Date) o;
				System.out.println("Server host is " + hostName);
				System.out.println("Time there is " + d.toString());

			} else {
				throw new IllegalArgumentException("Wanted Date, got " + o);
			}
		} catch (ClassNotFoundException e) {
			System.err.println("Wanted date, got INVALID CLASS (" + e + ")");
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}

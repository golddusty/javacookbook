import java.io.*;
import java.net.*;
import java.util.*;

/**
 * DaytimeBinary - connect to the Daytime (ascii) service.
 * @author Ian F. Darwin
 * @version $Id: DaytimeBinary.java,v 1.5 2001/03/30 14:58:55 ian Exp $
 */
public class DaytimeBinary {
	/** The TCP port for the binary time service. */
	public static final short TIME_PORT = 37;
	/** Seconds between 1970, the time base for Date(long) and Time.
	 * Factors in leap years (up to 2100), hours, minutes, and seconds.
	 * Subtract 1 day for 1900, add in 1/2 day for 1969/1970.
	 */
	protected static final long BASE_DAYS = 
		(long)(((1970 - 1900) * 365.25) - 1 + .5);
	/* Seconds since 1970 */
	public static final long BASE_DIFF = (BASE_DAYS * 24 * 60 * 60);
	/** Convert from seconds to milliseconds */
	public static final int MSEC = 1000;

	public static void main(String[] argv) {
		String hostName;
		if (argv.length == 0)
			hostName = "localhost";
		else
			hostName = argv[0];

		try {
			Socket sock = new Socket(hostName, TIME_PORT);
			DataInputStream is = new DataInputStream(new 
				BufferedInputStream(sock.getInputStream()));
			// Need to read 4 bytes from the network, unsigned.
			// Do it yourself; there is no readUnsignedInt().
			// Long is 8 bytes on Java, but we are using the
			// existing daytime protocol, which uses 4-byte ints.
			long remoteTime = (
				((long)(is.readUnsignedByte() & 0xff) << 24) |
				((long)(is.readUnsignedByte() & 0xff) << 16) |
				((long)(is.readUnsignedByte() & 0xff) <<  8) |
				((long)(is.readUnsignedByte() & 0xff) <<  0));
			System.out.println("Remote time is " + remoteTime);
			System.out.println("BASE_DIFF is " + BASE_DIFF);
			System.out.println("Time diff == " + (remoteTime - BASE_DIFF));
			Date d = new Date((remoteTime - BASE_DIFF) * MSEC);
			System.out.println("Time on " + hostName + " is " + d.toString());
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}

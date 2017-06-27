import java.io.*;
import java.net.*;

/**
 * DaytimeText - connect to the Daytime (ascii) service.
 * @author Ian F. Darwin
 * @version $Id: DaytimeText.java,v 1.3 2000/03/13 17:17:01 ian Exp $
 */
public class DaytimeText {
	public static final short TIME_PORT = 13;

	public static void main(String[] argv) {
		String hostName;
		if (argv.length == 0)
			hostName = "localhost";
		else
			hostName = argv[0];

		try {
			Socket sock = new Socket(hostName, TIME_PORT);
			BufferedReader is = new BufferedReader(new 
				InputStreamReader(sock.getInputStream()));
			String remoteTime = is.readLine();
			System.out.println("Time on " + hostName + " is " + remoteTime);
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}

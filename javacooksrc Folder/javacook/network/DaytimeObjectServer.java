import java.io.*;
import java.net.*;
import java.util.*;

/**
 * DaytimeObject - connect to the Daytime (ascii) service.
 * @author Ian F. Darwin
 * @version $Id: DaytimeObjectServer.java,v 1.2 2000/03/13 17:17:00 ian Exp $
 */
public class DaytimeObjectServer {
	/** The TCP port for the object time service. */
	public static final short TIME_PORT = 1951;

	public static void main(String[] argv) {
		ServerSocket sock;
		Socket  clientSock;
		try {
			sock = new ServerSocket(TIME_PORT);
			while ((clientSock = sock.accept()) != null) {
				System.out.println("Accept from " + 
					clientSock.getInetAddress());
				ObjectOutputStream os = new ObjectOutputStream(
					clientSock.getOutputStream());

				// Construct and write the Object
				os.writeObject(new Date());

				os.close();
			}

		} catch (IOException e) {
			System.err.println(e);
		}
	}
}

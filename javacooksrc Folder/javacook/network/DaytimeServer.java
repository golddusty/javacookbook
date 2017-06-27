import java.io.*;
import java.net.*;

/**
 * DaytimeServer - send the binary time.
 * @author	Ian Darwin, ian@darwinsys.com
 * @version $Id: DaytimeServer.java,v 1.2 2001/03/30 14:58:55 ian Exp $
 */
public class DaytimeServer {
	/** Our server-side rendezvous socket */
	ServerSocket sock;
	/** The port number to use by default */
	public final static int PORT = 37;

	/** main: construct and run */
	public static void main(String[] argv) {
		new DaytimeServer(PORT).runService();
	}

	/** Construct an EchoServer on the given port number */
	public DaytimeServer(int port) {
		try {
			sock = new ServerSocket(port);
		} catch (IOException e) {
			System.err.println("I/O error in setup\n" + e);
			System.exit(1);
		}
	}

	/** This handles the connections */
	protected void runService() {
		Socket ios = null;
		DataOutputStream os = null;
		while (true) {
			try {
				System.out.println("Waiting for connection on port " + PORT);
				ios = sock.accept();
				System.err.println("Accepted from " +
					ios.getInetAddress().getHostName());
				os = new DataOutputStream(ios.getOutputStream());
				long time = System.currentTimeMillis();

				time /= DaytimeBinary.MSEC;	// Daytime Protocol is in seconds

				// Convert to Java time base.
				time += DaytimeBinary.BASE_DIFF;

				// Write it, truncating cast to int since it is using
				// the Internet Daytime protocol which uses 4 bytes.
				// This will fail in the year 2038, along with all
				// 32-bit timekeeping systems based from 1970.
				// Remember, you read about the Y2038 crisis here first!
				os.writeInt((int)time);
				os.close();
			} catch (IOException e) {
				System.err.println(e);
			}
		}
	}
}

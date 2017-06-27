import java.io.*;
import java.net.*;

/** Simple console-mode (command-line) chat client.
 * @author Ian Darwin, ian@darwinsys.com
 * @version $Id: ConsChat.java,v 1.4 2001/12/26 01:04:09 ian Exp $
 */
public class ConsChat {
	public static void main(String[] args) throws IOException {
		new ConsChat().chat();
	}

	protected Socket sock;
	protected BufferedReader is;
	protected PrintWriter pw;
	protected BufferedReader cons;

	protected ConsChat() throws IOException {
		sock = new Socket("localhost", Chat.PORTNUM);
		is   = new BufferedReader(new InputStreamReader(sock.getInputStream()));
		pw   = new PrintWriter(sock.getOutputStream(), true);
		cons = new BufferedReader(new InputStreamReader(System.in));

		// Construct and start the reader: from server to stdout.
		// Make a Thread to avoid lockups.
		new Thread() {
			public void run() {
				setName("socket reader thread");
				System.out.println("Starting " + getName());
				System.out.flush();
				String line;
				try {
					// reader thread blocks here
					while ((line = is.readLine()) != null) {
						System.out.println(line);
						System.out.flush();
					}
				} catch (IOException ex) {
					System.err.println("Read error on socket: " + ex);
					return;
				}
			}
		}.start();
	}

	protected void chat() throws IOException {
		String text;

		System.out.print("Login name: "); System.out.flush();
		text = cons.readLine();
		send(Chat.CMD_LOGIN + text);

		// Main thread blocks here
		while ((text = cons.readLine()) != null) {
			if (text.length() == 0 || text.charAt(0) == '#')
				continue;			// ignore null lines and comments
			if (text.charAt(0) == '/')
				send(text.substring(1));
			else send("B"+text);
		}
	}

	protected void send(String s) {
		pw.println(s);
		pw.flush();
	}

}

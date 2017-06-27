import javax.swing.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;

/**
 * Threaded NetLog Server, pre-allocation schema.
 * @author Ian F. Darwin.
 */
public class NetLogServer {

	public static final int PORT = 65432;

	public static final int NUM_THREADS = 8;

	JFrame theFrame;
	JTextArea theTextArea;

	/** Main method, to start the servers. */
	public static void main(String[] av)
	{
		new NetLogServer(PORT, NUM_THREADS);
	}

	/** Constructor */
	public NetLogServer(int port, int numThreads)
	{
		ServerSocket servSock;
		Socket clientSocket;

		try {
			servSock = new ServerSocket(PORT);

		} catch(IOException e) {
			/* Crash the server if IO fails. Something bad has happened */
			System.err.println("Could not create ServerSocket " + e);
			System.exit(1);
			return;	/*NOTREACHED*/
		}

		// Build the GUI - must be before Handler constructors!
		theFrame  = new JFrame("NetLog Server");
		theTextArea = new JTextArea(24, 80);
		theTextArea.setEditable(false);
		theTextArea.setBorder(BorderFactory.createTitledBorder("NetLogServer"));
		theFrame.getContentPane().add(new JScrollPane(theTextArea));

		// Now start the Threads
		for (int i=0; i<numThreads; i++) {
			new Thread(new Handler(servSock, i)).start();
		}

		theFrame.pack();
		theFrame.setVisible(true);
		theFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});

	}

	public synchronized void log(int tid, String s) {
		StringBuffer sb = new StringBuffer();
		sb.append(tid);
		sb.append(": ");

		if (s == null) {
			sb.append("(null)");
		}
		else if (s.length() == 0) {
			sb.append("(null string)");
		}
		else
			sb.append(s);

		sb.append('\n');
		theTextArea.append(sb.toString());
		theTextArea.setCaretPosition(theTextArea.getText().length());
		theFrame.toFront();
	}

	/** A Thread subclass to handle one client conversation. */
	class Handler extends Thread {
		ServerSocket servSock;
		int tid;

		/** Construct a Handler. */
		Handler(ServerSocket s, int i) {
			super();
			servSock = s;
			tid = i;
			setName("Thread " + tid);
		}

		public void run()
		{
			/* Wait for a connection */
			while (true){
				try {
					// log(tid, getName() + " waiting");
					Socket clientSocket = servSock.accept();
					log(tid,getName() + " START, IP=" +
						clientSocket.getInetAddress());
					BufferedReader is = new BufferedReader(
						new InputStreamReader(clientSocket.getInputStream()));
					String line;
					while ((line = is.readLine()) != null) {
						// System.out.println(">> " + line);
						log(tid,line);
					}
					log(tid,getName() + " ENDED ");
					clientSocket.close();
				} catch (IOException ex) {
					log(tid, getName() + ": IO Error on socket " + ex);
					return;
				}
			}
		}
	}
}

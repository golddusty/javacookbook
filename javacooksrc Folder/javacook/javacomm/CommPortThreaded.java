import java.io.*;
import javax.comm.*;
import java.util.*;

/**
 * This program tries to do I/O in each direction using a separate Thread.
 * @author	Ian F. Darwin, ian@darwinsys.com
 */
public class CommPortThreaded extends CommPortOpen {

	public static void main(String[] ap)
		throws IOException, NoSuchPortException,PortInUseException,
			UnsupportedCommOperationException {
		CommPortThreaded cp;
		try {
			cp = new CommPortThreaded();
			cp.converse();
		} catch(Exception e) {
			System.err.println("You lose!");
			System.err.println(e);
		}
	}

	public CommPortThreaded()
		throws IOException, NoSuchPortException, PortInUseException,
			UnsupportedCommOperationException {
		super(null);
	}

	/** This version of converse() just starts a Thread in each direction.
	 */
	protected void converse() throws IOException {

		String resp;		// the modem response.

		new DataThread(is, System.out).start();
		new DataThread(new DataInputStream(System.in), os).start();

	}

	/** This inner class handles one side of a conversation. */
	class DataThread extends Thread {
		DataInputStream inStream;
		PrintStream pStream;

		/** Construct this object */
		DataThread(DataInputStream is, PrintStream os) {
			inStream = is;
			pStream = os;
		}

		/** A Thread's run method does the work. */
		public void run() {
			byte ch = 0;
			try {
				while ((ch = (byte)inStream.read()) != -1)
					pStream.print((char)ch);
			} catch (IOException e) {
				System.err.println("Input or output error: " + e);
				return;
			}
		}
	}
}

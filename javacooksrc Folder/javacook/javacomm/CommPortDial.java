import java.io.*;
import javax.comm.*;
import java.util.*;

/**
 * Dial a phone using the Java Communications Package.
 *
 * @author	Ian F. Darwin, ian@darwinsys.com
 */
public class CommPortDial extends CommPortModem {

	protected static String number = "000-0000";

	public static void main(String[] ap)
		throws IOException, NoSuchPortException,PortInUseException,
			UnsupportedCommOperationException {
		if (ap.length == 1)
			number = ap[0];
		new CommPortDial().converse();
		System.exit(0);
	}

	public CommPortDial() 
		throws IOException, NoSuchPortException, PortInUseException,
			UnsupportedCommOperationException {
		super(null);
	}

	protected void converse() throws IOException {

		String resp;		// the modem response.

		// Send the reset command
		send("ATZ");

		expect("OK");

		send("ATDT" + number);

		expect("OK");

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// nothing to do
		}
		is.close();
		os.close();
	}
}

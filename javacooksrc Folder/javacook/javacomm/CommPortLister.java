import javax.comm.*;
import java.util.*;

/**
 * List the ports.
 * @author	Ian F. Darwin, ian@darwinsys.com
 * @version	$Id: CommPortLister.java,v 1.3 2000/11/25 17:55:35 ian Exp $
 */
public class CommPortLister {

	/** Simple test program. */
	public static void main(String[] ap) {
		new CommPortLister().list();
	}

	/** Ask the Java Communications API * what ports it thinks it has. */
	protected void list() {
		// get list of ports available on this particular computer,
		// by calling static method in CommPortIdentifier.
		Enumeration pList = CommPortIdentifier.getPortIdentifiers();

		// Process the list.
		while (pList.hasMoreElements()) {
			CommPortIdentifier cpi = (CommPortIdentifier)pList.nextElement();
			System.out.print("Port " + cpi.getName() + " ");
			if (cpi.getPortType() == CommPortIdentifier.PORT_SERIAL) {
				System.out.println("is a Serial Port: " + cpi);
			} else if (cpi.getPortType() == CommPortIdentifier.PORT_PARALLEL) {
				System.out.println("is a Parallel Port: " + cpi);
			} else {
				System.out.println("is an Unknown Port: " + cpi);
			}
		}
	}
}

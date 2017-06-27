import java.awt.*;
import java.io.*;
import javax.comm.*;

/**
 * Print to a serial port using Java Communications.
 *
 * @author	Ian F. Darwin, ian@darwinsys.com
  */
public class ParallelPrint extends CommPortOpen {

	protected static String inputFileName;

	public static void main(String[] argv)
		throws IOException, NoSuchPortException, PortInUseException,
			UnsupportedCommOperationException {

		if (argv.length != 1) {
			System.err.println("Usage: ParallelPrint filename");
			System.exit(1);
		}
		inputFileName = argv[0];

		new ParallelPrint(null).converse();

		System.exit(0);
	}

	/* Constructor */
	public ParallelPrint(Frame f)
		throws IOException, NoSuchPortException, PortInUseException,
			UnsupportedCommOperationException {
		
		super(f);
	}

	/** 
	 * Hold the (one-way) conversation. 
	 */
	protected void converse() throws IOException {

		// Make a reader for the input file.
		BufferedReader file = new BufferedReader(
			new FileReader(inputFileName));

		String line;
		while ((line = file.readLine()) != null)
			os.println(line);

		// Finally, clean up.
		file.close();
		os.close();
	}
}

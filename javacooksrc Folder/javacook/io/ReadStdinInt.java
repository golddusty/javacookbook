import java.io.*;
/**
 * Read an int from Standard Input
 * @author	Ian F. Darwin, ian@darwinsys.com
 * @version 	$Id: ReadStdinInt.java,v 1.1 2001/03/15 17:53:20 ian Exp $
 */
public class ReadStdinInt {
	public static void main(String[] ap) {
		String line = null;
		int val = 0;
		try {
			BufferedReader is = new BufferedReader(
				new InputStreamReader(System.in));
			line = is.readLine();
			val = Integer.parseInt(line);
		} catch (NumberFormatException ex) {
			System.err.println("Not a valid number: " + line);
		} catch (IOException e) {
			System.err.println("Unexpected IO ERROR: " + e);
		}
		System.out.println("I read this number: " + val);
	}
}

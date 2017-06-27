import java.io.*;

/**
 * "Redirect" or reassign some standard descriptors.
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id: Redirect.java,v 1.2 2000/11/25 17:55:31 ian Exp $
 */
public class Redirect {
	public static void main(String[] argv) throws IOException {
		//+
		String LOGFILENAME = "error.log";
		System.setErr(new PrintStream(new FileOutputStream(LOGFILENAME)));
		System.out.println("Please look for errors in " + LOGFILENAME);
		// Now to see somebody else's code writing to stderr...
		int a[] = new int[5];
		a[10] = 0;	// here comes an ArrayIndexOutOfBoundsException
		//-
	}
}

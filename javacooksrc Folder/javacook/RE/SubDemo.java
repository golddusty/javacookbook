import org.apache.regexp.*;

/**
 * Quick demo of RE substitution: correct "demon" and other 
 * spelling variants to the correct, non-satanic "daemon".
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id: SubDemo.java,v 1.4 2001/04/15 17:53:20 ian Exp $
 */
public class SubDemo {
	public static void main(String[] argv) throws RESyntaxException {

		// Make an RE pattern to match almost any form (deamon, demon, etc.).
		String patt = "d[ae]{1,2}mon";	// i.e., 1 or 2 'a' or 'e' any combo

		// A test input.
		String input = "Unix hath demons and deamons in it!";

		// Run it from a RE instance and see that it works
		RE r = new RE(patt);
		System.out.println(input + " --> " + r.subst(input, "daemon"));
	}
}

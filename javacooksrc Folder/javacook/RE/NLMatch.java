import org.apache.regexp.*;

/**
 * Show line ending matching using RE class.
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id: NLMatch.java,v 1.2 2001/04/07 01:35:49 ian Exp $
 */
public class NLMatch {
	public static void main(String[] argv) throws RESyntaxException {

		String input = "I dream of engines\nmore engines, all day long";
		System.out.println("INPUT: " + input);
		System.out.println();

		String[] patt = {
			"engines\nmore engines",
			"engines$"
		};

		for (int i = 0; i < patt.length; i++) {
			System.out.println("PATTERN " + patt[i]);

			boolean found;
			RE r = new RE(patt[i]);

			found = r.match(input);
			System.out.println("DEFAULT match " + found);

			r.setMatchFlags(RE.MATCH_MULTILINE);
			found = r.match(input);
			System.out.println("MATCH_MULTILINE match was " + found);
			System.out.println();
		}
	}
}

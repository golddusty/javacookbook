import java.util.*;

/** Show using a StringTokenizer including getting the delimiters back */
public class StrTokDemo4b {
	/** The delimiter used between fields on input. */
	public final static String DELIM = "|";

	/** Processes one String, returns it as an array of fields */
	public static String[] process(String line) {
		// A Vector is an array-like container that grows dynamically;
		// see the chapter on Java Collections.
		Vector results = new Vector();

		// Unless you ask StringTokenizer to give you the tokens,
		// it silently discards multiple null tokens.
		StringTokenizer st = new StringTokenizer(line, DELIM, true);

		int i = 0;
		// Stuff each token into the Vector
		while (st.hasMoreTokens()) {
			String s = st.nextToken();
			if (s.equals(DELIM)) {
				// Just ignore the "tokens" that consist of DELIM
				// But do insert something for consecutive delimiters
				if (results.size() < ++i)
					results.add(null);
				continue;
			}
			results.addElement(s);
		}
		// Make an Array just the right size, ask the
		// Vector to copy its data into it, and return it.
		String[] sResults = new String[results.size()];
		results.toArray(sResults);
		return sResults;
	}

	public static void printResults(String input, Object[] outputs) {
		System.out.println("Input: " + input);
		for (int i=0; i<outputs.length; i++)
			System.out.println("Output " + i + " was: " + outputs[i]);
	}

	public static void main(String[] a) {
		printResults("A|B|C|D", process("A|B|C|D"));
		printResults("A||C|D", process("A||C|D"));
		printResults("A|||D|E", process("A|||D|E"));
	}
}

import java.util.*;

/** Demonstrate use of Arrays.sort on Booleans(!).
 * Try it, you'll get a ClassCastException, as there is no ordering of Booleans
 * @version $Id: SortBooleans.java,v 1.1 1999/08/07 23:48:04 ian Exp $
 */
public class SortBooleans {
	public static void main(String[] unused) {
		Boolean[] data = {
			new Boolean(true),
			new Boolean(false),
			new Boolean(false),
			new Boolean(true),
		};
		Arrays.sort(data);
		for (int i=0; i<data.length; i++)
			System.out.println(data[i]);
	}
}

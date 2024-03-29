/** Comparator for comparing strings ignoring first character.
 * @version $id$
 */
public class SubstringComparator implements java.util.Comparator {
	public int compare(Object o1, Object o2) {
		String s1 = o1.toString().substring(1);
		String s2 = o2.toString().substring(1);
		return s1.compareTo(s2);
		// or, more concisely:
		// return o1.substring(1).equals(o2.substring(1));
	}
}

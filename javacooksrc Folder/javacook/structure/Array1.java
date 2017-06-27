import java.util.*;

/** Review examples of arrays: shows array allocation, processing,
 * storing objects in Arrays,, two-dimensional arrays, and lengths.
 *
 * @author Ian Darwin
 * @version $Id: Array1.java,v 1.3 2000/11/25 17:56:19 ian Exp $
 */
public class Array1  {
	public static void main(String[] argv) {
		int monthLen1[];			// declare a reference
		monthLen1 = new int[12];		// construct it
		int monthlen2[] = new int[12];	// short form
		// even shorter is this initializer form:
		int monthLen3[] = {
				31, 28, 31, 30,
				31, 30, 31, 31,
				30, 31, 30, 31,
		};
		
		final int MAX = 10;
		Calendar days[] = new Calendar[MAX];
		for (int i=0; i<MAX; i++) {
			// Note that this actually stores GregorianCalendar
			// etc. instances into a Calendar Array
			days[i] = Calendar.getInstance();
		}
	 
		// Two-Dimensional Arrays
		// Want a 10-by-24 array
		int me[][] = new int[10][];
		for (int i=0; i<10; i++)
			me[i] = new int[24];

		// Remember that an array has a ".length" attribute
		System.out.println(me.length);
		System.out.println(me[0].length);

	}
}

import java.util.*;

/** Re-allocate an array, bigger...
 * @author Ian Darwin
 * @version $Id: Array2.java,v 1.5 2000/11/25 17:56:20 ian Exp $
 */
public class Array2  {
	public static void main(String[] argv) {
		int nDates = 0;
		final int MAX = 10;
		Calendar dates[] = new Calendar[MAX];
		Calendar c;
		StructureDemo source = new StructureDemo(21);
		while ((c=(Calendar)source.getDate()) != null) {

			// if (nDates >= dates.length) {
			// 	System.err.println("Too Many Dates! Simplify your life!!");
			// 	System.exit(1);  // wimp out
			// }

			// better: reallocate, making data structure dynamic
			if (nDates >= dates.length) {
				Calendar tmp[] = new Calendar[dates.length + 10];
				System.arraycopy(dates, 0, tmp, 0, dates.length);
				dates = tmp;    // copies the array reference
				// old array will be garbage collected soon...
			}
			dates[nDates++] = c;
		}
		System.out.println("Array size = " + dates.length);
	}
}

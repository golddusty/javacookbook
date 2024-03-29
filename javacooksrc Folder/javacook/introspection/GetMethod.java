import java.lang.reflect.*;

/** This class is just here to give us something to work on,
 * with a println() call that will prove we got here. */
class X {
	public void work(String s) {
		System.out.println("Working on \"" + s + "\"");
	}
}

/**
 * Get a given method, and invoke it.
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id: GetMethod.java,v 1.2 2000/11/25 17:55:25 ian Exp $
 */
public class GetMethod {

	public static void main(String[] argv) {
		try {
			Class clX = X.class; // or Class.forName("X");
			// To find a method we need the array of matching Class types.
			Class[] argTypes = {
				String.class
			};

			// Now find a Method object for the given method.
			Method worker = clX.getMethod("work", argTypes);

			// To INVOKE the method, we need its actual arguments, as an array.
			Object[] theData = {
				"Chocolate Chips"
			};

			// The obvious last step: invoke the method.
			worker.invoke(new X(), theData);
		} catch (Exception e) {
			System.err.println("Invoke() failed: " + e);
		}
	}
}

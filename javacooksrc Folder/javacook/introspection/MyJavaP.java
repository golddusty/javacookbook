import java.io.*;
import java.util.*;
import java.lang.reflect.*;

/**
 * JavaP prints structural information about classes.
 * For each class, all public fields and methods are listed.
 * "Reflectance" is used to look up the information.
 *
 * @author	Ian Darwin, Ian@DarwinSys.com
 * @version	$Id: MyJavaP.java,v 1.4 2002/03/29 15:00:48 ian Exp $
 */
public class MyJavaP {

	/** Simple main program, construct self, process each class name
	 * found in argv.
	 */
	public static void main(String[] argv) {
		MyJavaP pp = new MyJavaP();

		if (argv.length == 0) {
			System.err.println("Usage: javap className [...]");
			System.exit(1);
		} else for (int i=0; i<argv.length; i++)
			pp.doClass(argv[i]);
	}

	/** Format the fields and methods of one class, given its name.
	 */
	protected void doClass(String className) {

		try {
			Class c = Class.forName(className);
			System.out.println(Modifier.toString(c.getModifiers()) + ' ' + c + " {");
			int i, mods;
			Field fields[] = c.getDeclaredFields();
			for (i = 0; i < fields.length; i++) {
				if (!Modifier.isPrivate(fields[i].getModifiers())
				 && !Modifier.isProtected(fields[i].getModifiers()))
					System.out.println("\t" + fields[i]);
			}

			Method methods[] = c.getDeclaredMethods();
			for (i = 0; i < methods.length; i++) {
				if (!Modifier.isPrivate(methods[i].getModifiers())
				 && !Modifier.isProtected(methods[i].getModifiers()))
					System.out.println("\t" + methods[i]);
			}
		} catch (ClassNotFoundException e) {
			System.err.println("Error: Class " + 
				className + " not found!");
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			System.out.println("}");
		}
	}
}

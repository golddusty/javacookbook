import java.io.*;
import java.util.*;
import java.util.zip.*;
import java.lang.reflect.*;

/**
 * APIRef prints a cross-reference about all classes named in argv.
 * For each class, all public fields and methods are listed.
 * "Reflectance" is used to look up the information.
 *
 * It is expected that the output will be post-processed e.g.,
 * with sort and awk/perl. Try: 
	java APIRef | 
		uniq | # squeeze out polymorphic forms early
		grep ' method ' |
		sort | awk '{ ... }' > crossref-methods.txt
 * The part in "{ ... }" is left as an exercise for the reader. :-(
 *
 * @author	Ian Darwin, Ian@DarwinSys.com
 *
 */
public class APIRef {
	/** Counter of fields/methods printed. */
	static int n = 0;

	/** A "Modifier" object, to decode modifiers of fields/methods */
	Modifier m = new Modifier();

	/** Simple main program, construct self, process each .ZIP file
	 * found in CLASSPATH.
	 */
	public static void main(String[] argv) {
		APIRef xref = new APIRef();

		for (int i=0; i<argv.length; i++) {
			String cand = argv[i];
			// System.err.println("Trying path " + cand);
			if (cand.endsWith(".zip") || cand.endsWith(".jar"))
				xref.processOneZip(cand);
		}
		System.exit(0);
	}

	/** For each Zip file, for each entry, xref it */
	public void processOneZip(String classes) {
			try {
				ZipFile zippy = 
				new ZipFile(new File(classes));
				Enumeration all = zippy.entries();
				while (all.hasMoreElements()) {
					doClass(((ZipEntry)(all.nextElement())).getName());
				}
			} catch (IOException err) {
				System.err.println("IO Error: " + err);
				return;
			}
	}

	/** Format the fields and methods of one class, given its name.
	 */
	protected void doClass(String zipName) {
		// Ignore package/directory, other odd-ball stuff.
		if (zipName.endsWith("/")) {
			return;
		}
		if (zipName.startsWith("META-INF/")) {
			return;
		}
		if (!zipName.endsWith(".class")) {
			System.err.println("Not a class: " + zipName);
			return;
		}
	
		// Convert the zip file entry, like
		//	java/lang/Math.class
		// to a class name like
		//	java.lang.Math
		String className = zipName.replace('/', '.').
			substring(0, zipName.length() - 6);	// 6 for ".class"
		// System.err.println("ZipName " + zipName + 
		//	"; className " + className);
		try {
			Class c = Class.forName(className);
			printClass(c);
		} catch (ClassNotFoundException e) {
			System.err.println("Error: Class " + 
				className + " not found!");
		} catch (Exception e) {
			System.err.println(e);
		}
		// System.err.println("in gc...");
		System.gc();
		// System.err.println("done gc");
	}

	/**
	 * Print the fields and methods of one class.
	 */
	protected void printClass(Class c) {
		int i, mods;
		String className = c.getName();
		System.out.println(className + " " + c);
		try {
			Field fields[] = c.getFields();
			for (i = 0; i < fields.length; i++) {
				System.out.println(className + " " + fields[i]);
			}

			Method methods[] = c.getMethods();
			for (i = 0; i < methods.length; i++) {
				System.out.println(className + " " + methods[i]);
			}
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	/** put a Field's information to the standard output.
	 * Marked protected so you can override it (hint, hint).
	 */
	protected void putField(Field fld, Class c) {
		println(fld.getName() + " field " + c.getName() + " ");
		++n;
	}
	/** put a Method's information to the standard output.
	 * Ignores ubiquitous methods listed in the "if" statement.
	 * Marked protected so you can override it (hint, hint).
	 */
	protected void putMethod(Method meth, Class c) {
		String methName = meth.getName();
		if (methName.equals("wait") ||
			methName.equals("notify") ||
			methName.equals("toString"))
			return;
		println(methName + " method " + c.getName() + " ");
		++n;
	}

	/** Convenience routine, short for System.out.println */
	private final void println(String s) {
		System.out.println(s);
	}
}

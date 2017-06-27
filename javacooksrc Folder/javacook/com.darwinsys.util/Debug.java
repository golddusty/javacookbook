package com.darwinsys.util;

/** Utilities for debugging
 * @author	Ian Darwin, ian@darwinsys.com
 * @version	$Id: Debug.java,v 1.7 2001/03/29 18:05:38 ian Exp $
 */
public class Debug {
	/** Static method to see if a given category of debugging is enabled.
	 * Enable by setting e.g., -Ddebug.fileio to debug file I/O operations.
	 * Use like this:<BR>
	 * if (Debug.isEnabled("fileio"))<BR>
	 * 	System.out.println("Starting to read file " + fileName);
	 */
	public static boolean isEnabled(String category) {
		return System.getProperty("debug." + category) != null;
	}

	/** Static method to println a given message if the
	 * given category is enabled for debugging.
	 */
	public static void println(String category, String msg) {
		if (isEnabled(category))
			System.out.println(msg);
	}
	/** Same thing but for non-String objects (think of the other
	 * form as an optimization of this).
	 */
	public static void println(String category, Object stuff) {
		println(category, stuff.toString());
	}
}

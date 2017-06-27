import java.io.*;
import java.util.*;
import com.darwinsys.util.*;

/** MkIndex -- make a static index.html for a Java Source directory
 *
 * REQUIRES JDK1.2 OR LATER FOR SORT!!
 *
 * Started life as an awk script that used "ls" to get
 * the list of files, grep out .class and javadoc output files, |sort.
 * Now it's all in Java (including the ls-ing and the sorting).
 *
 * @author	Ian F. Darwin, ian@darwinsys.com
 * @Version $Id: MkIndex.java,v 1.32 2002/05/21 02:11:52 ian Exp $
 */
public class MkIndex {
	/** The output file that we create */
	public static final String OUTPUTFILE = "index-byname.html";
	/** The string for TITLE and H1 */
	public static final String TITLE =
		"Ian Darwin's Java Cookbook: Source Code: By Name";
	/** The main output stream */
	PrintWriter out;
	/** The background color for the page */
	public static final String BGCOLOR="#33ee33";
	/** The File object, for directory listing. */
	File dirFile;

	/** Make an index */
	public static void main(String[] args) throws IOException {
		MkIndex mi = new MkIndex();
		String inDir = args.length > 0 ? args[0] : ".";
		mi.open(inDir, OUTPUTFILE);		// open files
		mi.BEGIN();		// print HTML header
		mi.process();		// do bulk of work
		mi.END();		// print trailer.
		mi.close();		// close files
	}

	void open(String dir, String outFile) {
		dirFile = new File(dir);
		try {
			out = new PrintWriter(new FileWriter(outFile));
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	/** Write the HTML headers */
	void BEGIN() throws IOException {
		println("<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN'");
		println("	'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'");
		println(">");
		println();
		println("<html>");

		println("<head>");
		println("	<meta http-equiv='Content-Type' content='text/html; charset=iso-8859-1'/>");
		println("	<meta name='Generator' content='Java MkIndex'/>");
		println("	<link rel='stylesheet' type='text/css' href='/stylesheet.css' title='style'/>");
		println("	<title>" + TITLE + "</title>");
		println("</head>");
		println();
		println("<body bgcolor=\"" + BGCOLOR + "\">");
		println("<h1>" + TITLE + "</h1>");
		if (new File("about.html").exists()) {
			FileIO.copyFile("about.html", out, false);
		} else {
			println("<p>The following files are online.");
			println("Some of these files are still experimental!</p>");
			println("<p>Most of these files are Java source code.");
			println("If you load an HTML file from here, the applets will not run!");
			println("The HTML files must be saved to disk and the applets compiled,");
			println("before you can run them!</p>");
		}
		println("<p>All files are Copyright (c): All rights reserved.");
		println("See the accompanying <a href=\"legal-notice.txt\">Legal Notice</a> for conditions of use.");
		println("May be used by readers of my Java Cookbook for educational purposes, and for commercial use if certain conditions are met.");
		println("</p>");
		println("<hr />");
	}

	/** Array of letters that exist. Should
	 * fold case here so don't get f and F as distinct entries!
	 * This only works for ASCII characters (8-bit chars).
	 */
	boolean[] exists = new boolean[255];

	/** Vector for temporary storage, and sorting */
	ArrayList vec = new ArrayList();

	/** Do the bulk of the work */
	void process() throws IOException {

		System.out.println("Start PASS ONE -- from directory to Vector...");
		String[] fl = dirFile.list();
		for (int i=0; i<fl.length; i++) {
			String fn = fl[i];
			if (fn.startsWith("index")) { // we'll have no self-reference here!
				System.err.println("Ignoring " + fn);
				continue;
			} else if (fn.endsWith(".bak")) {		// delete .bak files
				System.err.println("DELETING " + fn);
				new File(fn).delete();
				continue;
			} else if (fn.equals("CVS")) {		// Ignore CVS subdirectories
				continue;						// don't mention it
			} else if (fn.charAt(0) == '.') {	// UNIX dot-file
				continue;
			} else if (fn.endsWith(".class")) {	// nag about .class files
				System.err.println("Ignoring " + fn);
				continue;
			} else if (new File(fn).isDirectory()) {
				vec.add(fn + "/");
			} else
				vec.add(fn);
			exists[fn.charAt(0)] = true;	// only after chances to continue
		}

		System.out.println("Writing the Alphabet Navigator...");
		for (char c = 'A'; c<='Z'; c++)
			if (exists[c])
				print("<a href=\"#" + c + "\">" + c + "</a> ");

		// ... (and the beginning of the HTML Unordered List...)
		println("<ul>");

		System.out.println("Sorting the Vector...");
		Collections.sort(vec, String.CASE_INSENSITIVE_ORDER);

		System.out.println("Start PASS TWO -- from Vector to " +
			OUTPUTFILE + "...");
		String fn;
		Iterator it = vec.iterator();
		while (it.hasNext()) {
			fn = (String)it.next();
			// Need to make a link into this directory.
			// IF there is a descr.txt file, use it for the text
			// of the link, otherwise, use the directory name.
			// But, if there is an index.html or index.html file,
			// make the link to that file, else to the directory itself.
			if (fn.endsWith("/")) {	// directory
				String descr = null;
				if (new File(fn + "descr.txt").exists()) {
					descr = com.darwinsys.util.FileIO.readLine(fn + 
						"descr.txt");
				};
				if (new File(fn + "index.html").exists())
					mkDirLink(fn+"index.html", descr!=null?descr:fn);
				else if (new File(fn + "index.htm").exists())
						mkDirLink(fn+"index.htm", descr!=null?descr:fn);
				else
					mkLink(fn, descr!=null?descr:fn + " -- Directory");
			} else // file
				mkLink(fn, fn);
		}
		System.out.println("*** process - ALL DONE***");
	}

	/** Keep track of each letter for #links */
	boolean done[] = new boolean[255];

	void mkLink(String href, String descrip) {
		print("<li>");
		char c = href.charAt(0);
		if (!done[c]) {
			print("<a name=\"" + c + "\"/>");
			done[c] = true;
		}
		println("<a href=\"" + href + "\">" + descrip + "</a>");
	}

	void mkDirLink(String index, String dir) {
		// XXX Open the index and look for TITLE lines!
		mkLink(index, dir + " -- Directory");
	}

	/** Write the trailers and a signature */
	void END() {
		System.out.println("Finishing the HTML");
		println("</ul>");
		flush();
		println("<p>This file generated by ");
		print("<a href=\"MkIndex.java\">MkIndex</a>, a Java program, at ");
		println(Calendar.getInstance().getTime().toString());
		println("</p>");
		println("</body>");
		println("</html>");
	}

	/** Close open files */
	void close() {
		System.out.println("Closing output files...");
		if (out != null)
			out.close();
	}

	/** Convenience routine for out.print */
	void print(String s) {
		out.print(s);
	}

	/** Convenience routine for out.println */
	void println(String s) {
		out.println(s);
	}

	/** Convenience routine for out.println */
	void println() {
		out.println();
	}

	/** Convenience for out.flush(); */
	void flush() {
		out.flush();
	}
}

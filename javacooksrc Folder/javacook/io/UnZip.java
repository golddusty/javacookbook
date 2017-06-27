import java.io.*;
import java.util.*;
import java.util.zip.*;

/**
 * UnZip -- print or unzip a JAR or PKZIP file using JDK1.1 java.util.zip.
 * Final command-line version: extracts files.
 * @author	Ian Darwin, Ian@DarwinSys.com
 * $Id: UnZip.java,v 1.4 2000/11/25 17:55:34 ian Exp $
 */
public class UnZip {
	/** Constants for mode listing or mode extracting. */
	public static final int LIST = 0, EXTRACT = 1;
	/** Whether we are extracting or just printing TOC */
	protected int mode = LIST;

	/** The ZipFile that is used to read an archive */
	protected ZipFile zippy;

	/** The buffer for reading/writing the ZipFile data */
	protected byte[] b;

	/** Simple main program, construct an UnZipper, process each
	 * .ZIP file from argv[] through that object.
	 */
	public static void main(String[] argv) {
		UnZip u = new UnZip();

		for (int i=0; i<argv.length; i++) {
			if ("-x".equals(argv[i])) {
				u.setMode(EXTRACT);
				continue;
			}
			String candidate = argv[i];
			// System.err.println("Trying path " + candidate);
			if (candidate.endsWith(".zip") ||
				candidate.endsWith(".jar"))
					u.unZip(candidate);
			else System.err.println("Not a zip file? " + candidate);
		}
		System.err.println("All done!");
	}

	/** Construct an UnZip object. Just allocate the buffer */
	UnZip() {
		b = new byte[8092];
	}

	/** Set the Mode (list, extract). */
	protected void setMode(int m) {
		if (m == LIST ||
		    m == EXTRACT)
			mode = m;
	}

	/** For a given Zip file, process each entry. */
	public void unZip(String fileName) {
		try {
			zippy = new ZipFile(fileName);
			Enumeration all = zippy.entries();
			while (all.hasMoreElements()) {
				getFile(((ZipEntry)(all.nextElement())));
			}
		} catch (IOException err) {
			System.err.println("IO Error: " + err);
			return;
		}
	}

	/** Process one file from the zip, given its name.
	 * Either print the name, or create the file on disk.
	 */
	protected void getFile(ZipEntry e) throws IOException {
		String zipName = e.getName();
		if (mode == EXTRACT) {
			// double-check that the file is in the zip
			// if a directory, mkdir it (remember to
			// create intervening subdirectories if needed!)
			if (zipName.endsWith("/")) {
				new File(zipName).mkdirs();
				return;
			}
			// Else must be a file; open the file for output
			System.err.println("Creating " + zipName);
			FileOutputStream os = new FileOutputStream(zipName);
			InputStream  is = zippy.getInputStream(e);
			int n = 0;
			while ((n = is.read(b)) >0)
				os.write(b, 0, n);
			is.close();
			os.close();
		} else
			// Not extracting, just list
			if (e.isDirectory()) {
				System.out.println("Directory " + zipName);
			} else {
				System.out.println("File " + zipName);
			}
	}
}

import java.io.File;
import java.util.*;

/** A simple FileFilter class that works by filename extension,
 * like the one in the JDK demo called ExtentionFilter, which
 * has been announced to be supported in a future Swing release.
 */
class JFileFilter extends javax.swing.filechooser.FileFilter {
	protected String description;
	protected ArrayList exts = new ArrayList();

	public void addType(String s) {
		exts.add(s);
	}

	/** Return true if the given file is accepted by this filter. */
	public boolean accept(File f) {
		// Little trick: if you don't do this, only directory names
		// ending in one of the extentions appear in the window.
		if (f.isDirectory()) {
			return true;

		} else if (f.isFile()) {
			Iterator it = exts.iterator();
			while (it.hasNext()) {
				if (f.getName().endsWith((String)it.next()))
					return true;
			}
		}

		// A file that didn't match, or a weirdo (e.g. UNIX device file?).
		return false;
	}

	/** Set the printable description of this filter. */
	public void setDescription(String s) {
		description = s;
	}
	/** Return the printable description of this filter. */
	public String getDescription() {
		return description;
	}
}

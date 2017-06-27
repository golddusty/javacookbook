import java.io.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;

/** Subclass XML Tree Walker to use the provided TreeWalker
 * STATUS -- INCOMPLETE.
 * @author Ian Darwin, ian@darwinsys.com
 * @version $Id: XTWalker.java,v 1.4 2002/02/11 19:20:36 ian Exp $
 */
public class XTWalker extends XTW {

	public static void main(String[] av) {
		if (av.length == 0) {
			System.err.println("Usage: XTWalker file [...]");
			return;
		}
		for (int i=0; i<av.length; i++) {
			String name = av[i];
			new XTWalker().convert(name, true);
		}
	}

	/* Process all the nodes, recursively. */
	protected void doRecursive(Node p) {

		// NOTE -- YOU HAVE TO WRITE YOUR OWN TREEWALKER CLASS.
		// OTHERWISE JUST USE XTW AS IT IS, NOT THIS SUBCLASS.

		TreeWalker tw = new TreeWalker(p);
		Node n;
		while ((n = (Node)tw.getNext()) != null) {

			doNode(n);

		}
	}
}

import java.io.*;
import org.w3c.dom.*;
import com.sun.xml.tree.*;

/**
 * Class with code to walk a tree and convert it to MML (not MIF).
 * WAY OUT OF DATE W.R.T. THE "DTD" -- DO NOT USE!!
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id: GenMML.java,v 1.9 2000/04/18 17:56:32 ian Exp $
 */
public class GenMML implements XmlFormWalker {
	/** The normal output writer */
	PrintWriter msg;
	/** Specialized PrintWriter for use by GetMark. */
	StyledWriter smsg;
	/** A tree walker object for walking the tree */
	TreeWalker tw;
	/** A GetMark converter for source code. */
	GetMark gm = new GetMark();

	/** Construct a converter object */
	GenMML(Document doc, PrintWriter pw) {
		tw = new TreeWalker(doc);
		msg = new PrintWriter(pw);
		smsg = new StyledPrintStream(msg);
	}

	/** Convert all the nodes in the current document. */
	public void convertAll() {

		msg.println("<MML 1.00 -- MML produced by XmlForm>");
		msg.println("<Include \"xmlformat.mml\">");

		for (Node p = tw.getCurrent(); p != null; p = tw.getNext())
			doNode(p);
	}

	public void doNode(Node p) {
		if (p instanceof com.sun.xml.tree.XmlDocument)
			return;	// nothing to do - structural object.
		// else if (p instanceof com.sun.xml.tree.Doctype)
		//	return;	// ditto
		else if (p instanceof Element)
			doElement((Element)p);
		else if (p instanceof org.w3c.dom.CharacterData)
			doCData((org.w3c.dom.CharacterData)p);
		else
			System.err.println("IGNORING non-Element: " +
				p.getClass() + : + p.toString() + "\n" +
				p.getNodeValue());
	}

	protected void doElement(Element p) {
		String tag = p.getTagName().toLowerCase();
		if (tag.equals("ch")) {
			doChapter(p);
		} else if (tag.equals("sc")) {
			doSection(p);
		} else if (tag.equals("ss")) {
			doSubSection(p);
		} else if (tag.equals("p")) {
			doParagraph(p);
		} else if (tag.equals("pr")) {
			msg.println("<HeadB>Problem");
		} else if (tag.equals("so")) {
			msg.println("<HeadB>Solution");
		} else if (tag.equals("di")) {
			msg.println("<HeadB>Discussion");
		} else if (tag.equals("code")) {
			doCode(p);
		} else if (tag.equals("b")) {
			doBold(p);
		} else if (tag.equals("i")) {
			doItalic(p);
		} else if (tag.equals("example")) {
			doExample(p);
		} else
			System.err.println("IGNORING UNHANDLED TAG " + tag + ( +
				p.getClass() + @ + p.hashCode() + ));
	}

	protected void doChapter(Element p) {
		msg.println("<ChapterTitle>");
	}

	protected void doSection(Element p) {
		msg.println("<HeadA>");
	}

	protected void doSubSection(Element p) {
		msg.println("<HeadB>");
	}

	protected void doParagraph(Element p) {
		msg.println("<Body>");
	}

	protected void doExample(Element p) {
		NamedNodeMap attrs = p.getAttributes();
		Node href;
		if ((href = attrs.getNamedItem("HREF")) == null)
			throw new IllegalArgumentException(
				"node " + p + "lacks required HREF Attribute");
		String fname = href.getNodeValue();
		msg.println("<Example>");
		try {
			fname = "/javasrc/" + fname;
			LineNumberReader is = new LineNumberReader(new FileReader(fname));
			gm.process(fname, is, smsg);
		} catch(IOException e) {
			throw new IllegalArgumentException(e.toString());
		}
	}

	protected void doCData(org.w3c.dom.CharacterData p) {
		String s = p.getData().trim();
		if (s.length() == 0)	// Suns parser returns extra 1-space "Text"s
			return;
		msg.println(s);
	}

	protected void doBold(Element p) {
		msg.print("<Bold>");
		doNodes(p);
		msg.print("<Plain>");
	}
	protected void doItalic(Element p) {
		msg.print("<Italic>");
		doNodes(p);
		msg.print("<Plain>");
	}
	protected void doNodes(Element p) {
		NodeList nodes = p.getChildNodes();
		for (int i=0; i<nodes.getLength(); i++) {
			Node n = nodes.item(i);
			if (n instanceof CharacterData) {
				doCData((CharacterData)n);
				p.removeChild(n);
			}
		}
	}
	/** Simply subclass PrintWriter so we dont have to modify
	 * GetMark to change the format of lines that it writes, or
	 * resort to other kluges like passing it a prefix and/or suffix.
	 */
	public class StyledWriter extends PrintWriter {
		public StyledWriter(PrintWriter p) {
			super(p, true);
		}
		public void println(String s) {
			super.println("<CellBody>" + s);
		}
	}
}

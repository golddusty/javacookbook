import java.io.*;
import java.net.*;
import java.util.*;

/**
 * ScanStreamTok - show scanning a file with StringTokenizer.
 *
 * @author	Ian Darwin, ian@darwinsys.com
 * @version	$Id: ScanStreamTok.java,v 1.4 2001/09/01 00:12:07 ian Exp $
 */
public class ScanStreamTok {
	protected  StreamTokenizer tf;

	public static void main(String[] av) throws IOException {
		if (av.length == 0)
			new ScanStreamTok(
				new InputStreamReader(System.in)).process();
		else 
			for (int i=0; i<av.length; i++)
				new ScanStreamTok(av[i]).process();
	}

	/** Construct a file scanner by name */
	public ScanStreamTok(String fileName) throws IOException {
		tf = new StreamTokenizer(new FileReader(fileName));
	}

	/** Construct a file scanner by existing Reader */
	public ScanStreamTok(Reader rdr) throws IOException {
		tf = new StreamTokenizer(rdr);
	}

	protected void process() throws IOException {
		String s = null;
		int i;

		while ((i = tf.nextToken()) != tf.TT_EOF) {
			switch(i) {
			case StreamTokenizer.TT_EOF:
				System.out.println("End of file");
				break;
			case StreamTokenizer.TT_EOL:
				System.out.println("End of line");
				break;
			case StreamTokenizer.TT_NUMBER:
				System.out.println("Number " + tf.nval);
				break;
			case StreamTokenizer.TT_WORD:
				System.out.println("Word, length " + tf.sval.length() + "->" + tf.sval);
				break;
			default:
				System.out.println("What is it? i = " + i);
			}
		}
	}
}

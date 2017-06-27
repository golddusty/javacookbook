import org.apache.regexp.*;
import com.darwinsys.util.*;
import java.io.*;
import java.util.*;

/** A command-line grep-like program. Some options, and takes a pattern
 * and an arbitrary list of text files.
 */
public class Grep2 {
	/** The pattern we're looking for */
	protected RE pattern;
	/** The Reader for the current file */
    protected BufferedReader d;
	/** Are we to only count lines, instead of printing? */
	protected boolean countOnly = false;
	/** Are we to ignore case? */
	protected boolean ignoreCase = false;
	/** Are we to suppress print of filenames? */
	protected boolean dontPrintFileName = false;
	/** Are we to only list names of files that match? */
	protected boolean listOnly = false;
	/** are we to print line numbers? */
	protected boolean numbered = false;
	/** Are we to be silent bout errors? */
	protected boolean silent = false;
	/** are we to print only lines that DONT match? */
	protected boolean inVert = false;

	/** Construct a Grep2 object for each pattern, and run it
	 * on all input files listed in argv.
	 * Be aware that a few of the command-line options are not
	 * acted upon in this version - this is an exercise for the reader!
	 */
	public static void main(String[] argv) throws RESyntaxException {

		if (argv.length < 1) {
		    System.err.println(
			"Usage: Grep2 pattern [-chilsnv][-f pattfile][filename...]");
		    System.exit(1);
		}
		String pattern = null;

		GetOpt go = new GetOpt("cf:hilnsv");
		BitSet args = new BitSet();

		char c;
		while ((c = go.getopt(argv)) != 0) {
			switch(c) {
				case 'c':
					args.set('C');
					break;
				case 'f':
					try {
						BufferedReader b = new BufferedReader(new FileReader(go.optarg()));
						pattern = b.readLine();
						b.close();
					} catch (IOException e) {
						System.err.println("Can't read pattern file " + go.optarg());
						System.exit(1);
					}
					break;
				case 'h':
					args.set('H');
					break;
				case 'i':
					args.set('I');
					break;
				case 'l':
					args.set('L');
					break;
				case 'n':
					args.set('N');
					break;
				case 's':
					args.set('S');
					break;
				case 'v':
					args.set('V');
					break;
			}
		}

		int ix = go.getOptInd();

		if (pattern == null)
			pattern = argv[ix-1];

		Grep2 pg = new Grep2(pattern, args);

		if (argv.length == ix)
			pg.process(new InputStreamReader(System.in), "(standard input)");
		else
			for (int i=ix; i<argv.length; i++) {
				try {
					pg.process(new FileReader(argv[i]), argv[i]);
				} catch(Exception e) {
					System.err.println(e);
				}
			}
	}

	public Grep2(String arg, BitSet args) throws RESyntaxException {
		// compile the regular expression
		if (args.get('C'))
			countOnly = true;
		if (args.get('H'))
			dontPrintFileName = true;
		if (args.get('I'))
			ignoreCase = true;
		if (args.get('L'))
			listOnly = true;
		if (args.get('N'))
			numbered = true;
		if (args.get('S'))
			silent = true;
		if (args.get('V'))
			inVert = true;
		int caseMode = ignoreCase?RE.MATCH_CASEINDEPENDENT:RE.MATCH_NORMAL;
		pattern = new RE(arg, caseMode);
	}
        
	/** Do the work of scanning one file
	 * @param	ifile	Reader	Reader object already open
	 * @param	fileName String	Name of the input file
	 */
	public void process(Reader ifile, String fileName) {

		String line;
		int matches = 0;

		try {
			d = new BufferedReader(ifile);
		    
			while ((line = d.readLine()) != null) {
				if (pattern.match(line)) {
					if (countOnly)
						matches++;
					else {
					if (!dontPrintFileName)
						System.out.print(fileName + ": ");
					System.out.println(line);
					}
				} else if (inVert) {
					System.out.println(line);
				}
			}
			if (countOnly)
				System.out.println(matches + " matches in " + fileName);
			d.close();
		} catch (IOException e) { System.err.println(e); }
	}
}

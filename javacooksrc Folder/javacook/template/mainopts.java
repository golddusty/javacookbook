import com.darwinsys.util.GetOpt;

/** Template main program using GetOpt.
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id: mainopts.java,v 1.3 2001/03/14 03:36:13 ian Exp $
 */
public class mainopts {
	public static void main(String[] argv) {
		String argChars = "o:hv";
		GetOpt go = new GetOpt("ho:v");
		boolean verbose = false;

		char c;
		while ((c =go.getopt(argv)) != 0) {
			switch(c) {
				case 'h':
					System.out.println("HELP!!");
					break;
				case 'o':
					System.out.print("-o Option " + go.optarg());
					break;
				case 'v':
					verbose = true;
					break;
				default:
					System.err.println("Unknown option char " + ((char)c));
			}
		}
		for (int i=go.getOptInd(); i<argv.length; i++)
			System.out.println("Filename-like arg " + argv[i]);
	}
}

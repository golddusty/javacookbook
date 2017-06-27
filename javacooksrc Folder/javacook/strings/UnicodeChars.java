/**
 * Conversion between Unicode characters and bytes
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id: UnicodeChars.java,v 1.3 2001/02/26 17:21:00 ian Exp $
 */
public class UnicodeChars {
	public static void main(String[] argv) {
		//+
		StringBuffer b = new StringBuffer();
		for (char c = 'a'; c<'d'; c++) {
			b.append(c);
		}
		b.append('\u00a5');	// Japanese Yen symbol
		b.append('\u01FC');	// Roman AE with acute accent
		b.append('\u0391');	// GREEK Capital Alpha
		b.append('\u03A9');	// GREEK Capital Omega

		for (int i=0; i<b.length(); i++) {
			System.out.println("Character #" + i + " is " + b.charAt(i));
		}
		System.out.println("Accumulated characters are " + b);
		//-
	}
}

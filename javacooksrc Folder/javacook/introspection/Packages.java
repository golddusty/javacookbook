/**
 * Show the Packages. Requires JDK1.2.
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id: Packages.java,v 1.3 2000/11/25 17:55:26 ian Exp $
 */
public class Packages {
	public static void main(String[] argv) {
		//+
		java.lang.Package[] all = java.lang.Package.getPackages();
		for (int i=0; i<all.length; i++)
			System.out.println(all[i]);
		//-
	}
}

package jabadot;

import java.util.*;

/**
 * Standalone test program for UserDB.
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id: UserDBTest.java,v 1.4 2000/12/03 00:43:57 ian Exp $
 */
public class UserDBTest {
	public static void main(String argv[]) throws java.io.IOException {

		System.out.println("Testing Getting One...");
		User iadmin = UserDB.getInstance().getUser("iadmin");
		System.out.println(iadmin);

		System.out.println("Testing Getting All...");
		ArrayList al = UserDB.getInstance().getUserList();
		Iterator it = al.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}

		System.out.println("Testing privs");
		iadmin.setPassword("guten abend");
		if (iadmin.checkPassword("foo"))
			System.out.println("setPassword failed");
		if (!iadmin.checkPassword("guten abend"))
			System.out.println("checkPassword failed");
		System.out.println("All done");
		System.exit(0);
	}
}

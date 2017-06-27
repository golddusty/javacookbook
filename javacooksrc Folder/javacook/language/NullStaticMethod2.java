/**
 * Test if you can use a null reference to find a static method.
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id: NullStaticMethod2.java,v 1.1 2002/06/18 20:29:45 ian Exp $
 */
public class NullStaticMethod2 {
	public static void invoke() {
		System.out.println("Invoked even though null");
	}
}

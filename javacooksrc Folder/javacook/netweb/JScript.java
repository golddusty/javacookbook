import java.applet.*;
import java.awt.*;
import netscape.javascript.*; 

/* An Applet to perform JavaScript directly.
 * The import of netscape.javascript.* requires a JAR file.
 * EXPECT COMPILE ERROR unless you have the Netscape JAR file.
 * This may be e.g., $NETSCAPEHOME/java/classes/java40.jar.
 * The use of JavaScript requires <APPLET ... MAYSCRIPT>
 * @author Ian F. Darwin, ian@darwinsys.com
 * @author Roger Goudarzi -- provided background information.
 * @version $Id: JScript.java,v 1.3 2002/01/03 01:51:17 ian Exp $
 */
public class JScript extends java.applet.Applet {
	public void init() {
		JSObject o = JSObject.getWindow(this);
		o.eval("arbitrary javascript here");
	}

	public void paint(Graphics g) {
		g.drawString("Welcome to Java", 50, 50);
	}
}

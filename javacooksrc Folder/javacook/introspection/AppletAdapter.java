import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.net.*;
import java.util.*;

/*
 * AppletAdaptor: partial implementation of AppletStub and AppletContext.
 *
 * This code is far from finished, as you will see.
 *
 * @author	Ian Darwin, ian@darwinsys.com, for Learning Tree Course 478
 */
public class AppletAdapter extends Panel implements AppletStub, AppletContext {
	/** The status window at the bottom */
	Label status = null;

	/** Construct the GUI for an Applet Status window */
	AppletAdapter() {
		super();

		// Must do this very early on, since the Applet's
		// Constructor or its init() may use showStatus()
		add(status = new Label());

		// Give "status" the full width
		status.setSize(getSize().width, status.getSize().height);

		showStatus("AppletAdapter constructed");	// now it can be said
	}

	/****************** AppletStub ***********************/
	/** Called when the applet wants to be resized.  */
	public void appletResize(int w, int h) {
		// applet.setSize(w, h);
	}

	/** Gets a reference to the applet's context.  */
	public AppletContext getAppletContext() {
		return this;
	}

	/** Gets the base URL.  */
	public URL getCodeBase() {
		return getClass().getResource(".");
	}

	/** Gets the document URL.  */
	public URL getDocumentBase() {
		return getClass().getResource(".");
	}

	/** Returns the value of the named parameter in the HTML tag.  */
	public String getParameter(String name) {
		String value = null;
		return value;
	}
	/** Determines if the applet is active.  */
	public boolean isActive() {
		return true;
	}

	/************************ AppletContext ************************/

	/** Finds and returns the applet with the given name. */
	public Applet getApplet(String an) {
		return null;
	}

	/** Finds all the applets in the document */
	public Enumeration getApplets()  {
		class AppletLister implements Enumeration {
			public boolean hasMoreElements() {
				return false;
			}
			public Object nextElement() {
				return null;
			}
		}
		return new AppletLister();
	}

	/** Create an audio clip for the given URL of a .au file */
	public AudioClip getAudioClip(URL u) {
		return null;
	}

	/** Look up and create an Image object that can be paint()ed */
	public Image getImage(URL u)  {
		return null;
	}

	/** Request to overlay the current page with a new one - ignored */
	public void showDocument(URL u) {
	}

	/** as above but with a Frame target */
	public void showDocument(URL u, String frame)  {
	}

	/** Called by the Applet to display a message in the bottom line */
	public void showStatus(String msg) {
		if (msg == null)
			msg = "";
		status.setText(msg);
	}
}

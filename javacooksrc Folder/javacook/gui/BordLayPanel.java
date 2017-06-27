import java.awt.*;
import java.net.*;
import java.io.*;

/**
 * BordLayPanel.java - BorderLayout with a Panel
 * @author	Ian Darwin, ian@darwinsys.com, for Learning Tree Course 478
 */
public class BordLayPanel extends Frame {
	TextField fileName;
	TextArea main;
	Label status;

	public static void main(String[] av) {
		new BordLayPanel().setVisible(true);
	}

	BordLayPanel() {
		super("BordLayPanel");
		Panel p;
		Button b;
		setLayout(new BorderLayout());
		add(BorderLayout.NORTH,  p = new Panel());
			p.setLayout(new FlowLayout());
			p.add(new Label("FileName:"));
			p.add(fileName  = new TextField(40));
			p.add(b = new Button("Load"));
			// now you need to add an action listener to the button
		add(BorderLayout.CENTER, main = new TextArea(24,80));
		add(BorderLayout.SOUTH,  status = new Label(""));
		pack();
		
		// Don't forget to add a window listener so the quit control works
	}

	/** Simulate applet.showStatus() for Frame-based applications */
	public void showStatus(String msg) {
		if (msg == null)
			msg = "";
		status.setText(msg);
	}
}

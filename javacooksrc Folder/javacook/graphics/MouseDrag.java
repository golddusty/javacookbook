import com.darwinsys.util.WindowCloser;

import java.awt.*;
import java.awt.event.*;

/** MouseDrag -- implement simple mouse drag in a window.
 */
public class MouseDrag extends Component 
		implements MouseListener, MouseMotionListener {
	/** The Image we are to paint */
	Image curImage;
	/** Kludge for showStatus */
	static Label status;
	/** true if we are in drag */
	boolean inDrag = false;
	/** starting location of a drag */
	int startX = -1, startY = -1;
	/** current location of a drag */
	int curX = -1, curY = -1;

	// "main" method
	public static void main(String[] av) {
		Frame f = new Frame("Mouse Dragger");

		if (av.length < 1) {
			System.err.println("Usage: MouseDrag imagefile");
			System.exit(1);
		}
		Image im = Toolkit.getDefaultToolkit().getImage(av[0]);

		// create a MouseDrag object
		MouseDrag j = new MouseDrag(im);

		f.setLayout(new BorderLayout());
		f.add(BorderLayout.NORTH,
			new Label("Hello, and welcome to the world of Java"));
		f.add(BorderLayout.CENTER, j);
		f.add(BorderLayout.SOUTH, status = new Label());
		f.addWindowListener(new WindowCloser(f));
		f.pack();
		status.setSize(f.getSize().width, status.getSize().height);
		f.setVisible(true);
	}

	// "Constructor" - creates the object
	public MouseDrag(Image i) {
		super();
		curImage = i;
		setSize(300,200);
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	public void showStatus(String s) {
		status.setText(s);
	}

	// Five methods from MouseListener:
	/** Called when the mouse has been clicked on a component. */
	public void mouseClicked(MouseEvent e)  {
	}
	/** Called when the mouse enters a component. */
	public void mouseEntered(MouseEvent e)  {
	}
	/** Called when the mouse exits a component. */
	public void mouseExited(MouseEvent e)  {
	}
	/** Called when the mouse has been pressed. */
	public void mousePressed(MouseEvent e)  {
		Point p = e.getPoint();
		System.err.println("mousePressed at " + p);
		startX = p.x; startY = p.y;
		inDrag = true;
	}

	/** Called when the mouse has been released. */
	public void mouseReleased(MouseEvent e)  {
		inDrag = false;
		System.err.println("SELECTION IS " + startX + "," +
			startY + " to " + curX + "," + curY);
	}

	// And two methods from MouseMotionListener:
	public void mouseDragged(MouseEvent e) {
		Point p = e.getPoint();
		// System.err.println("mouse drag to " + p);
		showStatus("mouse Dragged to " + p);
		curX = p.x; curY = p.y;
		if (inDrag) {
			repaint();
		}
	}

	public void paint(Graphics g) {
		int w = curX - startX, h = curY - startY;
		Dimension d = getSize();
		g.drawImage(curImage, 0, 0, d.width, d.height, this);
		if (startX < 0 || startY < 0)
			return;
		System.err.println("paint:drawRect @[" + startX +"," + startY +
			"] size " + w + "x" + h);
		g.setColor(Color.red);
		g.fillRect(startX, startY, w, h);
	}

	public void mouseMoved(MouseEvent e) {
		showStatus("mouse Moved to " + e.getPoint());
	}
	
}

import com.darwinsys.util.WindowCloser;

import java.awt.*;
import java.awt.event.*;
import java.net.*;

/**
 * Demo of Tiled Image
 * @version $Id: TiledImageComponent.java,v 1.3 2001/10/27 18:57:17 ian Exp $
 * @author	Ian F. Darwin, ian@darwinsys.com
 */
public class TiledImageComponent extends Container {
	TextField nameTF, passTF, domainTF;
	Image im;
	String IMAGE_NAME = "background.gif";

	/** Set things up nicely. */
	public TiledImageComponent() {
		Label l;

		setLayout(new FlowLayout());
		add(l = new Label("Name:", Label.CENTER));
		add(nameTF=new TextField(10));

		add(l = new Label("Password:", Label.CENTER));
		add(passTF=new TextField(10));
		passTF.setEchoChar('*');

		add(l = new Label("Domain:", Label.CENTER));
		add(domainTF=new TextField(10));

		im = getToolkit().getImage(IMAGE_NAME);
	}

	/** paint()  - just tile the background.  */
	public void paint(Graphics g) {
		// System.out.println("In paint()");
		if (im == null)
			return;
		int iw = im.getWidth(this), ih=im.getHeight(this);
		if (iw < 0 || ih < 0)	// image not ready
			return;				// live to try again later.
		int w = getSize().width, h = getSize().height;
		// System.out.println(iw + "," + ih + "; " + w + ", " + h);
		for (int i = 0; i<=w; i+=iw) {
			for (int j = 0; j<=h; j+=ih) {
				// System.out.println("drawImage(im,"+i+","+j+")");
				g.drawImage(im, i, j, this);
			}
		}
	}


	public static void main(String[] av) {
		Frame f = new Frame("TiledImageComponent Demo");
		f.add(new TiledImageComponent());
		f.setSize(200, 200);
		f.setVisible(true);
		f.addWindowListener(new WindowCloser(f, true));
	}
}


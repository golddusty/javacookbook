import java.awt.*;
import javax.swing.*;

/**
 * Layers - Try to use "alpha values (transparency) to draw in layers.
 * @author	Ian Darwin, ian@darwinsys.com
 * @version	$Id: Layers.java,v 1.4 2001/12/25 21:00:08 ian Exp $
 */
public class Layers extends JFrame {
	int width, height;

	Layers(String s) {
		this(s, 400, 300);
	}
	Layers(String s, int w, int h) {
		setTitle(s);
		setSize(width=w, height=h);	// a short form
		setBackground(Color.black);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void paint(Graphics g) {
		Color c1 = new Color(200,100,100, 50);	// r, g, b, a
		Color c2 = new Color(100,200,000,128);
		Color c3 = new Color(100,200,000,255);
		g.setColor(c1);
		g.fillRect(0, 0, width/2, height/2);
		g.setColor(c2);
		g.fillRect(width/4, height/4, width/2, height/2);
		g.setColor(c3);
		g.fillRect(width/3, height/3, width/4, height/4);
	}

	public static void main(String[] a) {
		new Layers("Default Size").setVisible(true);
		new Layers("Smaller", 100,100).setVisible(true);
	}
}


import java.util.*;
import java.awt.*;

/** BorderLayout demo. Also shows off BitSet class from java.util.
 * <P>Do <B>not</B> assume from this demo that BorderLayout forces
 * e.g., East and West to be same width, or North and South to be
 * same height. See BorderLayDemo2 for counter examples.
 */
public class BordLayDemo extends Frame {
	String cp[] = { "", "North", "East", "South", "West", "Center" };
	static final int   NORTH=1, EAST=2, SOUTH=3, WEST=4, CENTER=5;

	BordLayDemo(String s, BitSet b) {
		super(s);
		setLayout(new BorderLayout());
		for (int i = 1; i<=5; i++)
			if (b.get(i))
				add(cp[i], new Button(cp[i]));
		pack();
		setVisible(true);
	}

	public static void main(String[] av) {
		BitSet b = new BitSet();
		b.set(NORTH);
		b.set(EAST);
		b.set(SOUTH);
		b.set(WEST);
		b.set(CENTER);
		new BordLayDemo("All", b);

		b.clear(CENTER);
		new BordLayDemo("Compass", b);

		b.set(CENTER);
		b.clear(NORTH);
		b.clear(SOUTH);
		new BordLayDemo("Horizontal", b);

	}
}


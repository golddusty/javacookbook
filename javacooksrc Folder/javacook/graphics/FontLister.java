import java.awt.*;

/** Print the list of available fonts. */
public class FontLister {

	public static void main(String[] av) {

		// A list of fonts, the 1.0 way.
		Toolkit t = Toolkit.getDefaultToolkit();
		printList("the 1.0 way", t.getFontList());

		// The 1.2 way, using getAvailableFontFamilyNames().
		printList("the 1.2 way", 
				GraphicsEnvironment.getLocalGraphicsEnvironment().
				getAvailableFontFamilyNames());

		// Calling getFontList methods activates the AWT Event thread, so:
		System.exit(0);
	}

	protected static void printList(String title, String[] list) {

		System.out.println("Number of Fonts " + title + " = " + list.length);

		for (int i = 0; i<list.length; i++)
			System.out.println("Font " + i + " = " + list[i]);
	}
}

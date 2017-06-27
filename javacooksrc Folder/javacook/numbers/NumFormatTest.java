import java.text.*;
import java.util.*;

/*
 * Format a number our way and the default way.
 */
public class NumFormatTest {
	//+
	/** A number to format */
	public static final double intlNumber = 1024.25;
	/** Another number to format */
	public static final double ourNumber = 100.2345678;
	//-

	/** The main (and only) method in this class. */
	public static void main(String[] av) { 

		//+
		NumberFormat defForm = NumberFormat.getInstance();
		NumberFormat ourForm = new DecimalFormat("##0.##");
		// toPattern() will reveal the combination of #0., etc
		// that this particular local uses to format with!
		System.out.println("defForm's pattern is " +
			((DecimalFormat)defForm).toPattern());
		System.out.println(intlNumber + " formats as " +
			defForm.format(intlNumber));
		System.out.println(ourNumber + " formats as " +
			ourForm.format(ourNumber));
		System.out.println(ourNumber + " formats as " +
			defForm.format(ourNumber) + " using the default format");
		//-
	}
}

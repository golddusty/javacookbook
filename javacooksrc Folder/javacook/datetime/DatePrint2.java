import java.util.*;
/**
 * Show dates before 1970, in JDK1.0/1.1 when Date was broken
 * DO NOT USE ANYMORE -- use DateFormat instead!!.
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id: DatePrint2.java,v 1.3 2000/11/25 17:54:50 ian Exp $
 */
public class DatePrint2 {
	//+
	static String months[] = {
		"January", "February", "March",
		"April",   "May",      "June",
		"July",    "August",   "September",
		"October", "November", "December" };
	static String[] eras = { "B.C.", "A.D." };
	public static void main(String[] argv) {
		Calendar c = new GregorianCalendar(1918, 10, 11);
		System.out.println(
			months[c.get(Calendar.MONTH)] + " " +
			c.get(Calendar.DAY_OF_MONTH) + ", " +
			c.get(Calendar.YEAR) + " " +
			eras[c.get(Calendar.ERA)]);
	//-
	}
}

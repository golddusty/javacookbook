/** Casting Demo. */
public class CastNeeded {
	//+
	public static void main(String[] argv) {
		int i, k;
		double j = 2.75;
		i = j;			// EXPECT COMPILE ERROR
		i = (int)j;		// with cast; i gets 2
		System.out.println("i =" + i);
		byte b;
		b = i;			// EXPECT COMPILE ERROR
		b = (byte)i;	// with cast, i gets 2
		System.out.println("b =" + b);
	}
	//-
}

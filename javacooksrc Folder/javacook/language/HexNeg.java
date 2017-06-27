/**
 * Are all hex integers negative?
 * @author Ian F. Darwin, ian@darwinsys.com
 * @version $Id: HexNeg.java,v 1.3 2000/11/25 17:55:41 ian Exp $
 */
public class HexNeg {
	public static void main(String[] argv) {
		//+
		long data[] = { 0, 0x01, 0xff, 0x100, 0xffff, 0xffffff, 
			0x7fffffff, 0xffffffff };
		for (int i=0; i<data.length; i++)
			System.out.println("data["+i+"] = " + data[i]);
		//-
	}
}

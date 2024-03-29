/** What happens when two constructors call each other?
 * Can you get an infinite loop out of the process?
 */
public class ConsRecurs {
	public ConsRecurs(int i) {
		this(i*1.0);
	}
	public ConsRecurs(double d) {
		this((int)d);
	}
	public static void main(String[] a) {
		System.out.println("About to construct...");
		new ConsRecurs(Math.PI);
		System.out.println("Hey, I'm still alive!");
	}
}

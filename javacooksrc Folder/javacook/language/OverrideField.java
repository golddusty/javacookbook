/** Demonstration of overriding fields. */
public class OverrideField {
	public static void main(String[] av) {
		System.out.println("A's version of getAttr returns: " +
			new A().getAttr());
		System.out.println("B's version of getAttr returns: " +
			new B().getAttr());
		// Declared as A, instantiated as B, so gets B's version of things.
		A c = new B();
		System.out.println("C's version of getAttr returns: " +
			c.getAttr());
	}
}

class A {
	final int attr = 1;
	int getAttr() {
		System.out.println("In A.getAttr");
		return attr;
	}
}

class B extends A {
	final int attr = 2;
	int getAttr() {
		System.out.println("In B.getAttr");
		super.getAttr(); // Just to show flow of control
		return attr;
	}
}

/** A class to represent Complex Numbers. A Complex object is
 * immutable once created; the add, subtract and multiply routines
 * return newly-created Complex objects containing the results.
 *
 * @author Ian F. Darwin, inspired by David Flanagan.
 * @version $Id: Complex.java,v 1.1 1999/05/12 02:40:31 ian Exp $
 */
public class Complex {
	/** The real part */
	private double r;
	/** The imaginary part */
	private double i;
	/** Construct a Complex */
	Complex(double rr, double ii) {
		r = rr;
		i = ii;
	}
	/** Display the current Complex as a String, for use in
	 * println() and elsewhere.
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer().append(r);
		if (i>0)
			sb.append('+');	// else append(i) appends - sign
		return sb.append(i).append('i').toString();
	}
	/** Return just the Real part */
	public double getReal() {
		return r;
	}
	/** Return just the Real part */
	public double getImaginary() {
		return i;
	}
	/** Return the magnitude of a complex number */
	public double magnitude() {
		return Math.sqrt(r*r + i*i);
	}

	/** Add another Complex to this one */
	public Complex add(Complex other) {
		return add(this, other);
	}
	/** Add two Complexs */
	public static Complex add(Complex c1, Complex c2) {
		return new Complex(c1.r+c2.r, c1.i+c2.i);
	}

	/** Subtract another Complex from this one */
	public Complex subtract(Complex other) {
		return subtract(this, other);
	}
	/** Subtract two Complexs */
	public static Complex subtract(Complex c1, Complex c2) {
		return new Complex(c1.r-c2.r, c1.i-c2.i);
	}

	/** Multiply this Complex times another one */
	public Complex multiply(Complex other) {
		return multiply(this, other);
	}
	/** Multiply two Complexs */
	public static Complex multiply(Complex c1, Complex c2) {
		return new Complex(c1.r*c2.r - c1.i*c2.i, c1.r*c2.i + c1.i*c2.r);
	}
}

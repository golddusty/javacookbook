package com.darwinsys.util;

/** A MutableInteger is like an Integer but mutable, to avoid the
 * excess object creation involved in 
 * c = new Integer(c.getInt()+1)
 * which can get expensive if done a lot.
 * Not subclassed from Integer, since Integer is final (for performance :-))
 * @version $Id: MutableInteger.java,v 1.3 2001/10/12 22:47:06 ian Exp $
 */
public class MutableInteger {
	private int value = 0;

	public MutableInteger() {
	}

	public MutableInteger(int i) {
		value = i;
	}

	public void incr() {
		value++;
	}

	public void incr(int amt) {
		value += amt;
	}

	public void decr() {
		value--;
	}

	public void setValue(int i) {
		value = i;
	}

	public int getValue() {
		return value;
	}

	public String toString() {
		return Integer.toString(value);
	}

	public static String toString(int val) {
		return Integer.toString(val);
	}

	public static int parseInt(String str) {
		return Integer.parseInt(str);
	}
}

package jabadot;

import java.util.Date;

/** Represents one logged in user 
 */
public class User implements java.io.Serializable {

	// #name:password:name:email:City:Prov:Country:privs
	/** The login name */
	protected String name;
	protected String password;
	protected String firstName;
	protected String lastName;
	protected String email;			// 5
	protected String address;
	protected String address2;
	protected String company;
	protected String city;
	protected String prov;			// 10
	protected String country;
	protected Date creationDate;
	protected Date lastLoginDate;
	protected String jobDescr;
	protected String os;			// 15
	protected String unixGUI;
	protected String proglang;
	/** user preference */
	protected String skin;

	//								// privs is 19

	protected boolean editPrivs = false;
	protected boolean adminPrivs = false;

	public static final int P_ADMIN = 01000;
	public static final int P_EDIT = 0100;

	/** Construct a user with no data -- must be a no-argument
	 * constructor for use in jsp:useBean.
	 */
	public User() {
		creationDate = new Date();
	}

	/** Construct a user with just the name */
	public User(String n) {
		this();			// set credt
		name = n;
	}

	/** Construct a user with all text fields. */
	public User(String nick, String pw, String fname, String lName,
		String emaddr,
		String comp,
		String addr1, String addr2,
		String cty, String pr, String cntry,
		String jd, String os, String gui, String lang,
		String skin) {
		this();			// set credt
		name = nick;
		password = pw;
		firstName = fname;
		lastName = lName;
		email = emaddr;
		address = addr1;
		address2 = addr2;
		company = comp;
		city = cty;
		prov = pr;
		country = cntry;
		this.skin = skin;
		jobDescr = jd;
		os = os;
		unixGUI = gui;
		proglang = lang;
	}

	/** Construct a user with common text fields. */
	public User(String nick, String pw, String fname, String lName,
		String emaddr, String prov, String cntry,
		Date credt, Date lastlog, String skin, boolean e, boolean a) {
		this();			// set credt
		name = nick;
		password = pw;
		firstName = fname;
		lastName = lName;
		email = emaddr;
		this.prov = prov;
		this.country = cntry;
		this.skin = skin;
		creationDate = credt;
		lastLoginDate = lastlog;
		adminPrivs = a;
		editPrivs = e;
	}

	/** Return the nickname. */
	public String getName() {
		return name;
	}

	/** The name should not be changeable, but we
	 * want to be able to say <jsp:setProperty property="*"/>
	 * and get it all...
	 */
	public void setName(String nick) {
		name = nick;
	}

	public String getPassword() {
		return password;
	}

	/** Validate a given password against the user's. */
	public boolean checkPassword(String userInput) {
		return password.equals(userInput);
	}

	/** Set password */
	public void setPassword(String password) {
		this.password = password;
	}

	/** Get email */
	public String getEmail() {
		return email;
	}

	/** Set email */
	public void setEmail(String email) {
		this.email = email;
	}

	/** Get fullName */
	public String getFullName() {
		return firstName + ' ' + lastName;
	}

	/** Set firstName */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/** Set lastName */
	public void setlastName(String lastName) {
		this.lastName = lastName;
	}

	/** Get city */
	public String getCity() {
		return city;
	}

	/** Set city */
	public void setCity(String city) {
		this.city = city;
	}

	/** Get prov */
	public String getProv() {
		return prov;
	}

	/** Set prov */
	public void setProv(String prov) {
		this.prov = prov;
	}

	/** Get country */
	public String getCountry() {
		return country;
	}

	/** Set country */
	public void setCountry(String country) {
		this.country = country;
	}

	/** Get adminPrivs */
	public boolean isAdminPrivileged() {
		return adminPrivs;
	}

	/** Set adminPrivs */
	public void setAdminPrivileged(boolean adminPrivs) {
		this.adminPrivs = adminPrivs;
	}

	/** Get EditPrivs */
	public boolean isEditPrivileged() {
		return editPrivs;
	}

	/** Set EditPrivs */
	public void setEditPrivileged(boolean editPrivs) {
		this.editPrivs = editPrivs;
	}

	/** Get all privs, as an int, for use in the database */
	public int getPrivs() {
		int i = 0;
		if (adminPrivs)
			i |= P_ADMIN;
		if (editPrivs)
			i |= P_EDIT;
		return i;
	}

	/** Get the Creation Date (read only field) */
	public Date getCreationDate() {
		return creationDate;
	}
	/** Set the Creation Date (read only field) */
	public void setCreationDate(Date date) {
		creationDate = date;
	}

	/** Get the LastLog Date (read only field) */
	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	/** Get the LastLog Date (read only field) */
	public void setLastLoginDate(Date d) {
		lastLoginDate = d;
	}

	/** Return a String representation. */
	public String toString() {
		return new StringBuffer("User[").append(name).append(',').
			append(firstName).append(' ').append(lastName).
			append(']').toString();
	}

	/** Check if all required fields have been set */
	public boolean isComplete() {
		if (name == null || name.length()==0 ||
		    firstName == null || firstName.length()==0 ||
		    lastName == null || lastName.length()==0 ||
		    email == null || email.length()==0)
			return false;
		return true;
	}
}

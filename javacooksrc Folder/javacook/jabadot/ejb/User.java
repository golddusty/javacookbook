package jabadot;

import javax.ejb.*;
import java.util.*;
import java.rmi.*;

/** The "User" EJB "remote interface".
 * Most of this bean's methods are the CMP set/get methods.
 */
public interface User extends EJBObject {

	// CMP Database fields.
	public abstract String getName() throws RemoteException;
	public abstract void setName(String nick) throws RemoteException;

	public abstract String getPassword() throws RemoteException;
	public abstract void setPassword(String arg) throws RemoteException;

	public abstract String getFullName() throws RemoteException;
	public abstract void setFullName(String arg) throws RemoteException;

	public abstract String getEmail() throws RemoteException;
	public abstract void setEmail(String arg) throws RemoteException;

	public abstract String getCity() throws RemoteException;
	public abstract void setCity(String arg) throws RemoteException;

	public abstract String getProvince() throws RemoteException;
	public abstract void setProvince(String arg) throws RemoteException;

	public abstract String getCountry() throws RemoteException;
	public abstract void setCountry(String arg) throws RemoteException;

	public abstract Date getCreationDate() throws RemoteException;
	public abstract void setCreationDate(Date arg) throws RemoteException;

	public abstract Date getLastLoginDate() throws RemoteException;
	public abstract void setLastLoginDate(Date arg) throws RemoteException;

	public abstract boolean getEditPrivs() throws RemoteException;
	public abstract void setEditPrivs(boolean p) throws RemoteException;

	public abstract boolean getAdminPrivs() throws RemoteException;
	public abstract void setAdminPrivs(boolean p) throws RemoteException;

	// Business Methods
	public abstract boolean checkPassword(String userInput)
		throws RemoteException;
}

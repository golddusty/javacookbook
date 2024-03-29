// import com.darwinsys.sql.*;

import java.sql.*;
import javax.sql.*;
import javax.naming.*;

public class DarwinsDataDelivery {
	public static void main(String[] argv)  throws Exception {
		String driver = "oracle.jdbc.driver.OracleDriver";
		String dbURL = "jdbc:oracle:thin:@server:1521:db570";
		DataSource ds = new DarwinsDataSource(driver, dbURL);
		// Test it out...
		// Connection conn = ds.getConnection("student", "student");
		// System.out.println("Connection = " + conn);
		// conn.close();

		// Bind it into JNDI
		System.getProperties().setProperty("java.naming.factory.initial",
			"com.sun.jndi.rmi.registry.RegistryContextFactory");
		System.getProperties().setProperty("java.naming.provider.url",
			"rmi://localhost/");
		new InitialContext().rebind("darwinsys/RainData", ds);
	}
}
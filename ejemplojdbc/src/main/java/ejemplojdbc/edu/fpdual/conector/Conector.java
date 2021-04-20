package ejemplojdbc.edu.fpdual.conector;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import lombok.Getter;
import lombok.Setter;

/**
 * Class responsible for creation DDBB connections.
 * @author jose.m.prieto.villar
 *
 */
public class Conector {
	
	@Setter
	@Getter
	Properties prop = new Properties();

	public Conector() {
		try {
			//Loads all the properties of file "config.properties".
			prop.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Creates the connection object for a MySQL DDBB
	 * @return a {@link Connection}
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public Connection getMySQLConnection() throws ClassNotFoundException, SQLException {
		try {

			//Indicates which driver is going to be used.
			Class.forName(prop.getProperty(MySQLConstants.DRIVER));

			try {
				//Creates the connection based on the obtained URL.
				return  DriverManager.getConnection(getURL());
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * Obtains the URL to connect to a MySQL DDBB.
	 * @return an URL
	 */
	private String getURL() {
		//jdbc:mysql://localhost:3306/world?user=sa&password=12345678&useSSL=false;
		return new StringBuilder().append(prop.getProperty(MySQLConstants.URL_PREFIX))
				.append(prop.getProperty(MySQLConstants.URL_HOST)).append(":")
				.append(prop.getProperty(MySQLConstants.URL_PORT)).append("/")
				.append(prop.getProperty(MySQLConstants.URL_SCHEMA)).append("?user=")
				.append(prop.getProperty(MySQLConstants.USER)).append("&password=")
				.append(prop.getProperty(MySQLConstants.PASSWD)).append("&useSSL=")
				.append(prop.getProperty(MySQLConstants.URL_SSL)).toString();
	}

}

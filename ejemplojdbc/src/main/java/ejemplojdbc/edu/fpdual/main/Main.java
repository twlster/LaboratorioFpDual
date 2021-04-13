package ejemplojdbc.edu.fpdual.main;

import java.sql.Connection;
import java.sql.SQLException;

import ejemplojdbc.edu.fpdual.conector.Conector;
import ejemplojdbc.edu.fpdual.manager.CityManager;

public class Main {

	public static void main(String[] args) {
		Conector conector = new Conector();
		Connection con = conector.getMySQLConnection();
		try {
			new CityManager().findAll(con).forEach(city ->System.out.println(city));
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}

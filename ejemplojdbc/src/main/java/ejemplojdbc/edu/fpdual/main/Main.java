package ejemplojdbc.edu.fpdual.main;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import ejemplojdbc.edu.fpdual.conector.Conector;
import ejemplojdbc.edu.fpdual.dao.Country;
import ejemplojdbc.edu.fpdual.manager.impl.CityManagerImpl;
import ejemplojdbc.edu.fpdual.manager.impl.CountryManagerImpl;
import ejemplojdbc.edu.fpdual.manager.impl.GeneralManagerImpl;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// Connects to the DB
		Connection con = new Conector().getMySQLConnection();
		try {
			// Looks for all the cities in the DB and prints them.
			 System.out.println(new CityManagerImpl().findById(con, 2));

//			List<Country> countries = new CountryManager().findBySurfaceAreaBetween(con, BigDecimal.valueOf(100),
//					BigDecimal.valueOf(1000));
//			System.out.println(countries.size());
//			countries.forEach(country -> System.out.println(country));
//			new GeneralManager().findLanguajeDataWithPercentageGreaterThan(con, 0)
//					.forEach(data -> System.out.printf(
//							"Datos de la ciudad %s: lenguaje -> %s - Porcentaje de habla: %f - Pais: (%s) %s ",
//							data.getCityName(), data.getCityLanguage(), data.getLanguagePercentage(),
//							data.getCountryCode(), data.getCountryName() + "\n"));
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}

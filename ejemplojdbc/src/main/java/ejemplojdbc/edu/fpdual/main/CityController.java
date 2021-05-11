package ejemplojdbc.edu.fpdual.main;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import ejemplojdbc.edu.fpdual.conector.Conector;
import ejemplojdbc.edu.fpdual.dao.City;
import ejemplojdbc.edu.fpdual.dao.Country;
import ejemplojdbc.edu.fpdual.manager.CityManager;
import ejemplojdbc.edu.fpdual.manager.impl.CityManagerImpl;

public class CityController {

	private CityManager cityManager;

	public CityController(CityManagerImpl cityManagerImpl) {
		this.cityManager = cityManagerImpl;
	}

	public List<City> getAllCities() throws ClassNotFoundException, SQLException {
		try (Connection con = new Conector().getMySQLConnection()) {
			List<Country> countries = new ArrayList<>();
			Country conun = new Country();
			Consumer<City> consu = data -> {
				countries.add(data.getCountry());
				System.out.println(conun);
			};
			cityManager.findAll(con).forEach(consu);

			return cityManager.findAll(con);
		}
	}

}

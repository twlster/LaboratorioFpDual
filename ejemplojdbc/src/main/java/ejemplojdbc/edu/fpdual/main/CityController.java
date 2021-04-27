package ejemplojdbc.edu.fpdual.main;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import ejemplojdbc.edu.fpdual.conector.Conector;
import ejemplojdbc.edu.fpdual.dao.City;
import ejemplojdbc.edu.fpdual.manager.CityManager;
import ejemplojdbc.edu.fpdual.manager.impl.CityManagerImpl;

public class CityController {

	private CityManager cityManager;

	public CityController(CityManagerImpl cityManagerImpl) {
		this.cityManager = cityManagerImpl;
	}

	public List<City> getAllCities() throws ClassNotFoundException, SQLException {
		try (Connection con = new Conector().getMySQLConnection()) {
			return cityManager.findAll(con);
		}
	}

}

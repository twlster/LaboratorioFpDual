package ejemplojdbc.edu.fpdual.manager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import ejemplojdbc.edu.fpdual.dao.City;
import ejemplojdbc.edu.fpdual.dao.Country;

public class CityManager {

	public List<City> findAll(Connection con) {
		try (Statement stmt = con.createStatement()) {
			ResultSet result = stmt.executeQuery("SELECT * FROM City");
			result.beforeFirst();

			List<City> cities = new ArrayList<>();
			Map<Integer, String> countries = new HashMap();

			while (result.next()) {
				cities.add(new City(result));
				countries.put(result.getInt("ID"), result.getString("CountryCode"));
			}

			fillCountries(con, countries, cities);

			return cities;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	private void fillCountries(Connection con, Map<Integer, String> countries, List<City> cities) {
		Set<String> countryCodes = new HashSet<>(countries.values());

		Map<String, Country> countriesMap = new CountryManager().findAllById(con, countryCodes).stream()
				.collect(Collectors.toMap(Country::getId, data -> data));

		cities.forEach(city -> {
			String countryCode = countries.get(city.getId());
			Country foundCountry = countriesMap.get(countryCode);
			city.setCountry(foundCountry);
		});

	}

}

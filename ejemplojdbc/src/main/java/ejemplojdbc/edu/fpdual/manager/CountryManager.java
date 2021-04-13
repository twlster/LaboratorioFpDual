package ejemplojdbc.edu.fpdual.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import ejemplojdbc.edu.fpdual.dao.Country;

public class CountryManager {

	public List<Country> findAllById(Connection con, Set<String> ids) {
		
		String sql = String.format("SELECT * FROM Country WHERE Code in (%s)",
									ids.stream()
									.map(data -> "\"" + data + "\"")
									.collect(Collectors.joining(", ")));
		
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			
			ResultSet result = stmt.executeQuery();
			result.beforeFirst();

			List<Country> countries = new ArrayList<>();

			while (result.next()) {
				countries.add(new Country(result));
			}

			return countries;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}


package ejemplojdbc.edu.fpdual.manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ejemplojdbc.edu.fpdual.dao.CityLanguageData;

public class GeneralManager {

	public List<CityLanguageData> findLanguajeDataWithPercentageGreaterThan(Connection con, int percentage)
			throws SQLException {

		String sql = "select a.name as cityName, c.language as cityLanguage, c.percentage as languagePercentage, b.code, b.name "
				+ "from city a, country b, countrylanguage c where a.CountryCode = b.Code "
				+ "	and b.Code = c.CountryCode and c.percentage > ?";

		try (PreparedStatement prepStmt = con.prepareStatement(sql)) {
			prepStmt.setInt(1, percentage);
			ResultSet result = prepStmt.executeQuery();
			result.beforeFirst();
			List<CityLanguageData> resultado = new ArrayList<>();
			while (result.next()) {
//				System.out.printf("Datos de la ciudad %s: lenguaje -> %s - Porcentaje de habla: %f - Pais: (%s) %s ",
//						result.getString(1), result.getString("cityLanguage"), result.getFloat(3),
//						result.getString("code"), result.getString("name") + "\n");
				resultado.add(new CityLanguageData(result));
			}
			return resultado;
		}

	}
}

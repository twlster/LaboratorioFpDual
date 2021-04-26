package ejemplojdbc.edu.fpdual.manager;

import static org.junit.Assume.assumeNotNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import ejemplojdbc.edu.fpdual.conector.Conector;
import ejemplojdbc.edu.fpdual.dao.City;

@RunWith(JUnitPlatform.class)
public class CityManagerTest {

	private Connection con;
	private int idMasAlto;
	private String name = "Prueba";

	@BeforeEach
	public void setUp() throws SQLException, ClassNotFoundException {
		con = new Conector().getMySQLConnection();
		con.createStatement().execute("use world");
		ResultSet result = con.createStatement().executeQuery("select max(ID) idMasAlto from city");
		result.beforeFirst();
		result.next();
		idMasAlto = result.getInt("idMasAlto");

		for (int i = 0; i < 5; i++) {
			try {
				con.createStatement()
						.executeUpdate("INSERT into City (ID, Name, CountryCode, District, Population ) "
								+ "values((select max(args.ID)+1 from city args),'" + name + "','ESP','"
								+ ((int) (Math.random() * 1000)) + "'," + ((int) (Math.random() * 100000)) + ")");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Test
	public void findAll_ValidarTamaño_ok() {
		// Validate if connection is not null
		assumeNotNull(con);

		// Search for data
		List<City> cities = new CityManager().findAll(con);

		// Validates city list size
		assertEquals(idMasAlto + 5, cities.size());
		//Same assert but using Hamcrest Framework
		assertThat(cities.size(), is(idMasAlto + 5));
	}

	@Test
	public void findAll_ValidarNombre_ok() {
		// Validate if connection is not null
		assumeNotNull(con);

		// Search for data
		List<City> cities = new CityManager().findAll(con);

		// Validates city data
		for (City city : cities) {
			if (city.getId() > idMasAlto) {
				assertAll("Validate content", () -> {
					assertEquals(name, city.getName());
					assertNotNull(city.getDistrict());
				});
			}
		}
	}

	@Test
	public void findAll_ko() {
		assertThrows(NullPointerException.class, () -> new CityManager().findAll(null));
	}

	@Test
	public void findAll_SQLException_ko() throws SQLException {
		con.createStatement().execute("use sys");
		assertNull(new CityManager().findAll(con));
	}

	@AfterEach
	public void tearDown() throws SQLException {
		con.createStatement().execute("use world");
		con.createStatement().executeUpdate("DELETE from city where ID >" + idMasAlto);
		con.close();
	}

}

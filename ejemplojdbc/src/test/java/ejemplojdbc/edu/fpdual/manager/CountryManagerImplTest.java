package ejemplojdbc.edu.fpdual.manager;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import ejemplojdbc.edu.fpdual.conector.Conector;
import ejemplojdbc.edu.fpdual.dao.Country;
import ejemplojdbc.edu.fpdual.manager.impl.CountryManagerImpl;

@RunWith(JUnitPlatform.class)
class CountryManagerImplTest {
	private Connection con;

	@BeforeEach
	public void setUp() throws SQLException, ClassNotFoundException {
		con = new Conector().getMySQLConnection();
		con.createStatement()
				.executeUpdate("INSERT into Country (Code, Code2, Name, Region) values('999','9','Prueba','Prueba')");
	}

	@Test
	public void findAll_CountyData_ok() {
		List<Country> countries = new CountryManagerImpl().findAllById(con, Collections.singleton("999"));
		assertNotNull(countries);
	}

	@Test
	public void findAll_CountyData_ko() {
		assertThrows(NullPointerException.class,
				() -> new CountryManagerImpl().findAllById(null, Collections.singleton("999")));
	}
	
	@Test
	public void findAll_ExceptionData_ko() {
		NullPointerException exception =  assertThrows(NullPointerException.class,
				() -> new CountryManagerImpl().findAllById(null, Collections.singleton("999")));
		assertEquals(null, exception.getMessage());
	}

	@AfterEach
	public void tearDown() throws SQLException {
		con.createStatement().executeUpdate("DELETE from Country  WHERE Code = '999'");
		con.close();
	}

}

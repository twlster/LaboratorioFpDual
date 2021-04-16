package ejemplojdbc.edu.fpdual.conector;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
class ConectorTest {

	private Conector conector;

	@BeforeEach
	public void init() {
		conector = new Conector();
	}

	@Test
	void conector_CrearConexion_ok() throws ClassNotFoundException, SQLException {
		Connection con = conector.getMySQLConnection();
		assertNotNull(con);
		con.close();
	}

	@Test
	void conector_CargarClase_ko() {
		conector.getProp().put(MySQLConstants.DRIVER, "gonzalo");
		assertThrows(ClassNotFoundException.class, () -> conector.getMySQLConnection());
	}

	@Test
	void conector_CrearConexion_ko() {
		conector.getProp().put(MySQLConstants.URL_SCHEMA, ".");
		assertThrows(SQLException.class, () -> conector.getMySQLConnection());
	}
}

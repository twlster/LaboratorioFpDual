package ejemplojdbc.edu.fpdual.conector;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Connection;

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
	void test() {
		Connection con = conector.getMySQLConnection();
		assertNotNull(con);
	}

}

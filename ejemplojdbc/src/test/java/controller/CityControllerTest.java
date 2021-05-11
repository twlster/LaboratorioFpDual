package controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import ejemplojdbc.edu.fpdual.dao.City;
import ejemplojdbc.edu.fpdual.main.CityController;
import ejemplojdbc.edu.fpdual.manager.CityManager;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class CityControllerTest {

	@InjectMocks
	CityController cityController;

	@Mock
	CityManager cityManager;

	@BeforeEach
	public void setUp() throws SQLException, ClassNotFoundException {
		MockitoAnnotations.initMocks(this);
		//Mockito.doReturn(Collections.emptyList()).when(cityManager.findAll(Mockito.any(Connection.class)));
	}

	@Test
	public void getAllCities_ok() throws ClassNotFoundException, SQLException {
		City city = new City();
		city.setName("Vicky");
		Mockito.when(cityManager.findAll(Mockito.any(Connection.class))).thenReturn(Collections.emptyList());
		Mockito.when(cityManager.findById(Mockito.any(Connection.class), Mockito.anyInt())).thenReturn(city);
		List<City> cities = cityController.getAllCities();
		assertNotNull(cities);
		MatcherAssert.assertThat(cities, Matchers.hasSize(0));
	}

}

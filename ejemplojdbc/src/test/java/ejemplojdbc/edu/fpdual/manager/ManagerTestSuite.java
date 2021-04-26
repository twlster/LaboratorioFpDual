package ejemplojdbc.edu.fpdual.manager;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

//Declare Suite runner
@RunWith(Suite.class)
//Declare Test Suite Classes
@SuiteClasses({CityManagerTest.class, CountryManagerTest.class})
public class ManagerTestSuite {

}

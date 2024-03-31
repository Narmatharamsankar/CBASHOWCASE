package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/feature/PetStoreApiRegressionSuite.feature",
                             glue = {"stepdefenitions"},
	                         monochrome = true,publish=true) 

public class PetStoreApiRegressionSuiteRunner extends AbstractTestNGCucumberTests {

	
	
}

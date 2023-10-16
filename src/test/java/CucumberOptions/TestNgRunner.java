package CucumberOptions;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/Features",

		glue = "StepDefination", 
		 monochrome = true, 
	//	 dryRun = true, 
		// tags = "not @Regression", 
		 plugin = { "pretty",
				"html:target/cucumber.html", 
				"json:target/cucumber.json" })

public class TestNgRunner extends AbstractTestNGCucumberTests {

}

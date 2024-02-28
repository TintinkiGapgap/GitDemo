package cucumber.Options;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/features",
        glue = {"stepDefinitions"},
        tags = "@UpdatePlace",
        plugin = "json:target/jsonReports/cucumber-report.json",
        monochrome = true, // display the console output in a proper readable format
        dryRun = false // to check the mapping is proper between feature file and step def file
)
public class TestRunner {

}

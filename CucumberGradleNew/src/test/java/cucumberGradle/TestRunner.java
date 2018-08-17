package cucumberGradle;
 
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
 
@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources"
		,tags={"@SoapUI"}
		,glue={"cucumberGradle"}
		,plugin = {"html:target/cucumber-html-report-original",
				   "json:target/cucumber-report/cucumber.json"}
		,dryRun=false
		)

public class TestRunner {
 
}
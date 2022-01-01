package ZadanieJedenSelenide;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {"src/Cucumber/Features/zadanie-jeden.feature"},
        plugin = {"pretty", "html:out"}
)
public class CucumberRunner extends AbstractTestNGCucumberTests {
}

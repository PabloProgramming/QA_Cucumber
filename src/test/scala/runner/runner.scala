package runner

import io.cucumber.junit.{Cucumber, CucumberOptions}
import org.junit.runner.RunWith

@RunWith(classOf[Cucumber])
@CucumberOptions(
  features   = Array("src/test/resources/features"),            // src/test/resources/features
  glue       = Array("stepdefs", "support"),
  plugin     = Array("pretty",
    "html:target/cucumber-report.html"),
  monochrome = true
)
class runner // an empty class body is OK
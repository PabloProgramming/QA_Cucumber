package runner

import io.cucumber.junit.{Cucumber, CucumberOptions}
import org.junit.runner.RunWith

@RunWith(classOf[Cucumber])
@CucumberOptions(
  features   = Array("classpath:features"),            // src/test/resources/features
  glue       = Array("stepdefs", "support"),
  // tags       = "@wip and not @smoke",
  plugin     = Array("pretty",
    "html:target/cucumber-report.html"),
  monochrome = true
)
class TestRunner // an empty class body is OK
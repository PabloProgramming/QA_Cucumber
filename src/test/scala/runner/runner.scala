package runner

import io.cucumber.junit.{Cucumber, CucumberOptions}
import org.junit.runner.RunWith

@RunWith(classOf[Cucumber])
@CucumberOptions(
  features   = Array("classpath:features"),            // src/test/resources/features
  glue       = Array("steps", "support"),
  plugin     = Array("pretty",
    "html:target/cucumber-report.html"),
  monochrome = true
)
class runner // an empty class body is OK
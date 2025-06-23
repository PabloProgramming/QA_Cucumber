# Integrating Cucumber Reports with Allure & ExtentReports (Scala + Cucumber + Selenium)

---

## 🔗 1. Allure Integration

### ✅ Step-by-Step

#### a. Add Dependencies in `build.sbt`

```scala
libraryDependencies ++= Seq(
  "io.qameta.allure" % "allure-cucumber7-jvm" % "2.24.0", // Use correct version for your Cucumber
  "org.scalatest" %% "scalatest" % "3.2.18" % Test
)
```

#### b. Add Allure Annotations (Optional)
You can annotate your step definitions or hooks for additional metadata:

```scala
import io.qameta.allure.{Allure, Description, Severity, SeverityLevel}

@Description("Login scenario with valid credentials")
@Severity(SeverityLevel.CRITICAL)
def loginTest(): Unit = {
  // test logic
}
```

#### c. Configure Allure Properties
Create a file named `allure.properties` in `src/test/resources/`:

```
allure.results.directory="target/allure-results"
```

## 📸 Screenshot Utility with Allure Integration


```scala
package utils

import io.qameta.allure.Attachment
import org.apache.commons.io.FileUtils
import org.openqa.selenium.{OutputType, TakesScreenshot, WebDriver}

import java.io.File
import java.text.SimpleDateFormat
import java.util.Date

object ScreenshotUtil {

  private val dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss")

  def captureScreenshot(driver: WebDriver, scenarioName: String, isPassed: Boolean): Unit = {
    val ts = driver.asInstanceOf[TakesScreenshot]
    val screenshot = ts.getScreenshotAs(OutputType.FILE)
    val timestamp = dateFormat.format(new Date())
    val folder = if (isPassed) "passed" else "failed"
    val fileName = s"screenshots/$folder/${scenarioName.replaceAll(" ", "_")}_$timestamp.png"
    FileUtils.copyFile(screenshot, new File(fileName))

    attachToAllure(new File(fileName))
  }

  @Attachment(value = "Screenshot", `type` = "image/png")
  def attachToAllure(file: File): Array[Byte] = {
    FileUtils.readFileToByteArray(file)
  }
}
```
### Update Hooks to Capture on Finish
```scala
import io.cucumber.scala.Scenario

    After { scenario: Scenario =>
    val isPassed = !scenario.isFailed
    ScreenshotUtil.captureScreenshot(driver, scenario.getName, isPassed)
}
```



## 📂 Folder Structure Summary
```bash
project-root/
└── src/
└── test/
├── resources/
│   └── allure.properties
└── scala/
└── steps/...
```

---

## 🌟 2. ExtentReports Integration

### ✅ Step-by-Step

#### a. Add Dependencies in `build.sbt`
There’s no native Scala version; use Java compatibility:

```scala
libraryDependencies += "com.aventstack" % "extentreports" % "5.1.1"
```

#### b. Create a Singleton Report Manager in utils folder

```scala
object ExtentManager {
  private val htmlReporter = new ExtentSparkReporter("target/extent-report.html")
  htmlReporter.config().setReportName("Test Report")

  val extent: ExtentReports = new ExtentReports()
  extent.attachReporter(htmlReporter)
}
```
#### c. Hooks for Report Lifecycle `Before` & `After`



```scala
import io.cucumber.scala.{EN, ScalaDsl, Scenario}
import com.aventstack.extentreports.{ExtentTest, Status}

object ReportHooks {
  var test: ExtentTest = _

  @Before
  def beforeScenario(scenario: Scenario): Unit = {
    test = ExtentManager.extent.createTest(scenario.getName)
  }

  @After
  def afterScenario(scenario: Scenario): Unit = {
    if (scenario.isFailed) {
      test.log(Status.FAIL, "Scenario Failed")
    } else {
      test.log(Status.PASS, "Scenario Passed")
    }
    ExtentManager.extent.flush()
  }
}
```

## 🆚 Allure vs ExtentReports – When to Use


| Tool               | Best For                             | Notes     |
|--------------------|--------------------------------------|------------|
| Allure             | CI/CD integration, history, analytics| Lightweight, popular with QA teams     |
| ExtentReports      | Detailed, visually rich manual reporting	   | Heavier but more customizable     |












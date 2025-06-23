# ⚡ Parallel Test Execution in Cucumber with Scala

---

## 🧠 What is Parallel Execution?

Parallel execution means running multiple test scenarios (or features) **simultaneously** rather than sequentially. This reduces total test suite runtime by utilizing multi-threading or parallel processes.

---

## 🔧 How Does It Work in Cucumber?

Cucumber doesn’t support parallelism directly, but you can use a **JUnit platform runner** or **custom threading** via libraries like:

- `cucumber-parallel` (Java plugin, adaptable)
- JUnit/TestNG parallel runners
- ScalaTest (indirect support with thread pools)
- SBT’s `Test / parallelExecution := true` (basic support)

---

## ⚠️ Common Challenges

| Challenge                          | Solution                                                                 |
|-----------------------------------|--------------------------------------------------------------------------|
| ❌ Shared WebDriver state          | Use a separate WebDriver instance per thread                             |
| ❌ Thread-safe reporting/logging   | Ensure logger & reporter (Allure, Extent) supports parallel execution    |
| ❌ Global variables                | Avoid mutable shared state — use `ThreadLocal` if necessary              |
| ❌ Order-dependent tests           | Make tests isolated and stateless                                        |
| ❌ Report overwrites               | Save separate reports per thread, merge later                            |

---

## ✅ Steps to Set Up Parallel Execution in Scala + Cucumber

---

### 1. Set Up JUnit + Cucumber Parallel Runner

Use **JUnit’s `@Suite` and `@Parameterized` runners** to break down tests into feature files or scenarios.

---

### 2. Enable Parallel Execution in SBT

In your `build.sbt`:

```scala
Test / parallelExecution := true
```

### 3. Use a Custom Runner per Feature
**Optional** autogenerate runner files per feature

```scala
val featureFiles = List("login.feature", "search.feature")

featureFiles.zipWithIndex.foreach { case (feature, i) =>
  val runner =
    s"""
    |@RunWith(classOf[Cucumber])
    |@CucumberOptions(
    |  features = Array("src/test/features/$feature"),
    |  glue = Array("steps", "hooks"),
    |  plugin = Array("pretty", "json:target/cucumber-$i.json")
    |)
    |class FeatureTestRunner$i
    """.stripMargin
  // Write to file
}
```

### 4. Ensure Thread-Safe WebDriver
Use a `ThreadLocal` util function to avoid browser session collisions:

```scala
package utils

import org.openqa.selenium.{WebDriver, ChromeDriver}

object WebDriverManager {
  private val driverThread = new ThreadLocal[WebDriver] {
    override def initialValue(): WebDriver = new ChromeDriver()
  }

  def getDriver(): WebDriver = driverThread.get()

  def quitDriver(): Unit = {
    driverThread.get().quit()
    driverThread.remove()
  }
}
```
### 5. Manage Parallel Reporting

##### ✅ Allure (Recommended)

```bash
sbt test
allure serve target/allure-results

```
### 🧠 Final Tips
 - Keep each test independent — no shared state, DB, or auth.

- Use CI runners with multiple executors for scaling this even further.

- Consider Docker grid or Selenium Grid if tests are very UI-heavy.


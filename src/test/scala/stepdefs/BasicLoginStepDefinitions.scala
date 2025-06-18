package stepdefs

import io.cucumber.scala.{EN, ScalaDsl}
import org.openqa.selenium.{By, WebDriver, WebElement}
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration


class BasicLoginStepDefinitions extends ScalaDsl with EN {

  val driver: WebDriver = new ChromeDriver()

  Given("""^the user is on the Login Page$""") { () =>

    driver.get("https://parabank.parasoft.com/parabank/index.html")
  }

  When("""^the user enters valid username and password$""") { () =>
    val usernameInput: WebElement = driver.findElement(By.name("username"))
    usernameInput.sendKeys("john")
    val passwordInput: WebElement = driver.findElement(By.name("password"))
    passwordInput.sendKeys("demo")
  }

  And("""^the user clicks on the login button$""") { () =>
    val logInButton: WebElement = driver.findElement(By.cssSelector("input[value='Log In']"))
    logInButton.click()
  }

  Then("""^the user should be logged in successfully$""") { () =>
    val waitForWelcomeMsg = new WebDriverWait(driver, Duration.ofSeconds(10))
    val welcomeMessage: WebElement = waitForWelcomeMsg.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p[class='smallText']")))
    if (welcomeMessage.getText.contains("Welcome")) println("Logged in successfully - ✅ ")
  }

}
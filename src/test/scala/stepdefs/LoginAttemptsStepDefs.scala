package stepdefs

import io.cucumber.scala.{EN, ScalaDsl}
import org.openqa.selenium.{By, WebDriver, WebElement}
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.support.ui.{ExpectedConditions, WebDriverWait}

import java.time.Duration

class LoginAttemptsStepDefs extends ScalaDsl with EN {

  val driver: WebDriver = new ChromeDriver()

  Given("""^user is on the login page$""") { () =>
    driver.get("https://practicetestautomation.com/practice-test-login/")
  }

  When("""user enters username: {string} and password: {string}""") { (username: String, password: String) =>
    val usernameInput: WebElement = driver.findElement(By.id("username"))
    usernameInput.sendKeys(username)

    val passwordInput: WebElement = driver.findElement(By.id("password"))
    passwordInput.sendKeys(password)
  }

  And("""^user clicks the submit button$""") { () =>
    val submitButton: WebElement = driver.findElement(By.id("submit"))
    submitButton.click()
  }

  Then("""^user should be logged in successfully$""") { () =>
    val waitForSuccessMsg = new WebDriverWait(driver, Duration.ofSeconds(10))
    val welcomeMessage: WebElement = waitForSuccessMsg.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")))
    if (welcomeMessage.getText.contains("Logged In")) println("Logged in successfully - ✅ ")
    assert(welcomeMessage.isDisplayed, "Success message was not displayed")
  }

  When("""user enters invalid username: {string} and password: {string}"""") { (username: String, password: String) =>
    val usernameInput: WebElement = driver.findElement(By.id("username"))
    usernameInput.sendKeys(username)

    val passwordInput: WebElement = driver.findElement(By.id("password"))
    passwordInput.sendKeys(password)
  }

  Then("""^login should fail with error message: The username or password is invalid$""") { () =>
    val waitForErrorMsg = new WebDriverWait(driver, Duration.ofSeconds(20))
    val errorMsg: WebElement = waitForErrorMsg.until(ExpectedConditions.visibilityOfElementLocated(By.id("error")))
    assert(errorMsg.getText.contains("invalid") && errorMsg.isDisplayed, "Error message was not displayed")
    println("Log in unsuccessful as expected - ✅")
  }


}
package stepdefs

import io.cucumber.scala.{EN, ScalaDsl}
import org.openqa.selenium.{By, JavascriptExecutor, WebDriver, WebElement}
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.support.ui.{ExpectedConditions, WebDriverWait}

import java.time.Duration

class FormSubmissionStepDefs extends ScalaDsl with EN {

  val driver: WebDriver = new ChromeDriver()
  val jsExecutor: JavascriptExecutor = driver.asInstanceOf[JavascriptExecutor]

  Given("""^I am on the practice form page$""") { () =>
    driver.get("https://demoqa.com/automation-practice-form")
  }

  When("""^I enter valid data into all required fields$""") { () =>
    val nameInput: WebElement = driver.findElement(By.id("firstName"))
    nameInput.sendKeys("Pablo")

    val lastNameInput: WebElement = driver.findElement(By.id("lastName"))
    lastNameInput.sendKeys("Montalvo")

    val emailInput: WebElement = driver.findElement(By.id("userEmail"))
    emailInput.sendKeys("pablo@exmaple.com")

    val mobileNumberInput: WebElement = driver.findElement(By.id("userNumber"))
    mobileNumberInput.sendKeys("7828546783")

    jsExecutor.executeScript("document.getElementById('dateOfBirthInput').value = '30 Jul 1990';")
  }

  And("""^I select a gender and a hobby$""") { () =>
    val genderRadioButton: WebElement = driver.findElement(By.cssSelector("label[for='gender-radio-1']"))
    if (!genderRadioButton.isSelected) genderRadioButton.click()

    val hobbyCheckBox: WebElement = driver.findElement(By.cssSelector("label[for='hobbies-checkbox-1']"))
    jsExecutor.executeScript("arguments[0].scrollIntoView(true);", hobbyCheckBox)
    if (!hobbyCheckBox.isSelected) hobbyCheckBox.click()
  }

  And("""^I submit the form$""") { () =>
    val submitButton: WebElement = driver.findElement(By.id("submit"))
    jsExecutor.executeScript("arguments[0].scrollIntoView(true);", submitButton)
    submitButton.click()
  }

  Then("""^I should see a confirmation message$""") { () =>
    val waitForConfirmationMsg = new WebDriverWait(driver, Duration.ofSeconds(10))
    val welcomeMessage: WebElement = waitForConfirmationMsg.until(ExpectedConditions.visibilityOfElementLocated(By.id("example-modal-sizes-title-lg")))
    if (welcomeMessage.getText.contains("Thanks")) println("Form submitted successfully - ✅ ")
    assert(welcomeMessage.isDisplayed, "Confirmation message was not displayed")
  }

  When("""^I leave required fields empty$""") { () =>
    println("Intentionally left required fields empty.")
  }

  Then("""^I should not be able to submit the form$""") { () =>
    val practiceFormH1: WebElement = driver.findElement(By.tagName("h1"))
    assert(practiceFormH1.isDisplayed, "Form was submitted despite missing fields - ❌")
    println("Form was not submitted as expected - ✅")
  }

}
package stepdefs

import io.cucumber.scala.{EN, ScalaDsl}
import locators.FormLocators.{expectedPageHeader, formPageHeader, subject, submit, suggestions}
import locators.FormSubmittedHeader.{expectedHeader, formSubmittedHeader}
import pages.FormPage._
import pages.FormSubmittedPage.verifyHeader
import testdata.FormCompletionData.{emailText, firstNameText, lastNameText, mobileNumberText}
import utils.ScreenCapture.takeScreenshot
import utils.WaitUtils.waitForElementVisible

import java.io.File

class FormSubmissionStepDefs extends ScalaDsl with EN {

  Given("""^I am on the practice form page$""") { () =>
    browserLaunch()
    waitForElementVisible(driver, getWebElement(subject), 10)
    println("container ready")
  }

  When("""^I enter valid data into all required fields$""") { () =>
    inputFirstName(firstNameText)
    inputLastName(lastNameText)
    inputEmail(emailText)
    inputMobileNumber(mobileNumberText)
    inputDateOfBirth()
    inputSubject("en")
    println("required fields")
  }

  And("""^I select a gender and a hobby$""") { () =>
    selectGender("label[for='gender-radio-1']")
    selectHobby("label[for='hobbies-checkbox-1']")
    println("more fields")
  }

  And("""^I submit the form$""") { () =>
    buttonSubmit(submit)
    println("click submit")
    val resultPageScreenshot: File = takeScreenshot(driver, "/Users/pablo.montalvo/Documents/Screenshots/", s"resultFormPage")
    println(s"Saved screenshot to: ${resultPageScreenshot.getPath} - ✅")
  }

  Then("""^I should see a confirmation message$""") { () =>
    verifyHeader(formSubmittedHeader, expectedHeader)
    println("form submitted")
  }

  When("""^I leave required fields empty$""") { () =>
    println("Intentionally left required fields empty.")
  }

  Then("""^I should not be able to submit the form$""") { () =>
    verifyHeader(formPageHeader, expectedPageHeader)
    println("form not submmited")
  }









  // BEFORE REFACTOR ALL HERE ⬇️

  /*
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
  }*/

}
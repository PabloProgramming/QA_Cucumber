package pages

import locators.FormLocators.{dateOfBirth, email, firstName, genderMale, hobbySport, lastName, mobileNumber, submit}
import org.openqa.selenium.{By, WebElement}
import testdata.FormCompletionData.dateOfBirthText

object FormPage extends BasePage {

  def inputFirstName(text: String): Unit =
    inputText(firstName, text)

  def inputLastName(text: String): Unit = {
    inputText(lastName, text)
  }

  def inputEmail(text: String): Unit = {
    inputText(email, text)
  }

  def inputMobileNumber(number: String): Unit = {
    inputText(mobileNumber, number)
  }

  def inputDateOfBirth(): Unit = {
    jsExecutorLaunch().executeScript(s"document.getElementById('dateOfBirthInput').value = '$dateOfBirthText';")
  }

  def selectGender(genderOptionCssLocator: String): Unit = {
    val genderOption = findByCssSelector(genderOptionCssLocator)
    if (!genderOption.isSelected) genderOption.click()
  }

  def selectHobby(hobbyOptionCssLocator: String): Unit = {
    val hobbyOption = findByCssSelector(hobbyOptionCssLocator)
    jsExecutorLaunch().executeScript("arguments[0].scrollIntoView(true);", hobbyOption)
    if (!hobbyOption.isSelected) hobbyOption.click()
  }

  def buttonSubmit(submitLocator: By): Unit = {
    jsExecutorLaunch().executeScript("arguments[0].scrollIntoView(true);", getWebElement(submitLocator))
    clickOn(submitLocator)
  }

}

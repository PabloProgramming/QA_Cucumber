package pages

import locators.FormLocators.{dateOfBirth, email, firstName, genderMale, hobbySport, lastName, mobileNumber, subject, submit, suggestions}
import org.openqa.selenium.{By, WebElement}
import testdata.FormCompletionData.dateOfBirthText
import utils.Autocomplete.selectSuggestionByText
import utils.WaitUtils.waitForElementVisible

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

  def inputSubject(partialText: String): Unit = {
    inputText(subject, partialText)
    selectSuggestionByText(driver, "English")
  }


  def buttonSubmit(submitLocator: By): Unit = {
    jsExecutorLaunch().executeScript("arguments[0].scrollIntoView(true);", getWebElement(submitLocator))
    clickOn(submitLocator)
  }

}

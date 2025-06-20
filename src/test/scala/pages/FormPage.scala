package pages

import locators.FormLocators.{chooseFile, currentAddress, dateOfBirth, email, firstName, genderMale, hobbySport, lastName, mobileNumber, stateDropDown, subject, submit, suggestions}
import org.openqa.selenium.{By, WebElement}
import testdata.FormCompletionData.dateOfBirthText
import utils.Autocomplete.selectSuggestionByText
import utils.Dropdown.{clickOption, openDropdown}
import utils.WaitUtils.{setImplicitWait, waitForElementClickable, waitForElementVisible}

import scala.jdk.CollectionConverters.CollectionHasAsScala

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

  def uploadFile(path: String): Unit = {
    inputText(chooseFile, path)
  }

  def inputCurrentAddress(text: String): Unit = {
    inputText(currentAddress, text)
  }

  def selectDropdownOption(dropDownLocator: By, optionText: String): Unit = {
    openDropdown(dropDownLocator)
    clickOption(optionText)
  }

  def buttonSubmit(submitLocator: By): Unit = {
    jsExecutorLaunch().executeScript("arguments[0].scrollIntoView(true);", getWebElement(submitLocator))
    clickOn(submitLocator)
  }

}

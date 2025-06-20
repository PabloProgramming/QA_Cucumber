package locators

import org.openqa.selenium.By

object FormLocators {

  val firstName: By = By.id("firstName")
  val lastName: By = By.id("lastName")
  val email: By = By.id("userEmail")
  val mobileNumber: By = By.id("userNumber")
  val dateOfBirth: By = By.id("dateOfBirthInput")
  val genderMale: By = By.cssSelector("label[for='gender-radio-1']")
  val hobbySport: By = By.cssSelector("label[for='hobbies-checkbox-1']")
  val submit: By = By.id("submit")
  val formPageHeader: By = By.tagName("h1")
  val expectedPageHeader: String = "Practice Form"
  val subject: By = By.id("subjectsInput")
  val suggestions: By = By.className("subjects-auto-complete__menu")
  val chooseFile: By = By.id("uploadPicture")
  val currentAddress: By = By.id("currentAddress")
  val stateDropDown: By = By.id("state")
  val cityDropDown: By = By.id("city")

}

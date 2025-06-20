package utils

import locators.FormLocators.suggestions
import org.openqa.selenium.{By, WebDriver, WebElement}
import pages.FormPage.{driver, getWebElement, jsExecutorLaunch}
import utils.WaitUtils.waitForElementVisible

import scala.jdk.CollectionConverters.CollectionHasAsScala

object Autocomplete {

  // 2. Get all suggestion options
  def getSuggestionOptions(container: WebElement): Seq[WebElement] = {
    container.findElements(By.className("subjects-auto-complete__option")).asScala.toSeq
  }

  // 3. Find the option that matches the given text
  def findSuggestionByText(options: Seq[WebElement], text: String): Option[WebElement] = {
    options.find(_.getText.equalsIgnoreCase(text))
  }

  // 4. Click the matched option
  def clickSuggestion(options: Option[WebElement]): Unit = {
    options.foreach { option =>
      jsExecutorLaunch().executeScript("arguments[0].scrollIntoView(true);", option)
      option.click()
    }
  }

  // Optional: Combine them in a top-level method if needed
  def selectSuggestionByText(driver: WebDriver, text: String): Unit = {
    val suggestionsContainer = waitForElementVisible(driver, getWebElement(suggestions), 10)
    val options = getSuggestionOptions(suggestionsContainer)
    val matchOpt = findSuggestionByText(options, text)
    clickSuggestion(matchOpt)
  }

}

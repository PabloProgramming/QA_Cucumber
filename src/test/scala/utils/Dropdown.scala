package utils

import locators.FormLocators.suggestions
import org.openqa.selenium.{By, WebDriver, WebElement}
import pages.FormPage.{driver, getWebElement, jsExecutorLaunch}
import utils.WaitUtils.{waitForElementClickable, waitForElementVisible}

import scala.jdk.CollectionConverters.CollectionHasAsScala

object Dropdown {

  def openDropdown(dropDownLocator: By): Unit = {
    val dropdown = waitForElementClickable(driver, getWebElement(dropDownLocator), 10)
    jsExecutorLaunch().executeScript("arguments[0].scrollIntoView(true);", dropdown)
    dropdown.click()
  }

  def clickOption(optionText: String): Unit = {
    val options = driver.findElements(By.xpath("//div[contains(@class,'option')]")).asScala
    println(s"Found ${options.size} options in dropdown:")
    options.foreach(opt => println(s"- ${opt.getText}"))

    val optionToClick = options.find(_.getText.trim.equalsIgnoreCase(optionText.trim))
      .getOrElse(throw new NoSuchElementException(s"Option '$optionText' not found."))
    optionToClick.click()
  }

}

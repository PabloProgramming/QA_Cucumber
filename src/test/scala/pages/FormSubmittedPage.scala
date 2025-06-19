package pages

import locators.FormSubmittedHeader.{expectedHeader, formSubmittedHeader}
import org.openqa.selenium.By
import pages.FormPage.{driver, getText, getWebElement}
import utils.Assertion
import utils.WaitUtils.waitForElementVisible

object FormSubmittedPage {
  def verifyHeader(header: By, expectedHeader: String): Unit = {
    waitForElementVisible(driver, getWebElement(header), 10)
    Assertion.assert(getText(header), expectedHeader)
  }
}

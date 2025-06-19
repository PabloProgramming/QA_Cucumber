package utils

import java.time.Duration
import org.openqa.selenium.{WebDriver, WebElement, NoSuchElementException}
import org.openqa.selenium.support.ui.{ExpectedConditions, FluentWait, WebDriverWait}
import java.util.function.Function

object WaitUtils {

  /**
   * Set implicit wait time for the WebDriver globally.
   * Selenium will wait this long when trying to find elements.
   */
  def setImplicitWait(driver: WebDriver, seconds: Long): Unit = {
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds))
  }

  /**
   * Wait explicitly for a specific element to be visible within given timeout.
   * Uses ExpectedConditions.visibilityOf
   */
  def waitForElementVisible(driver: WebDriver, element: WebElement, timeoutSeconds: Long): WebElement = {
    val wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
    wait.until(ExpectedConditions.visibilityOf(element))
  }

  /**
   * Fluent wait for a WebElement with custom polling interval and ignoring exceptions.
   * Example use: wait for element to be clickable or any custom condition.
   */
  def fluentWaitForElement(driver: WebDriver,
                           timeoutSeconds: Long,
                           pollingMillis: Long,
                           condition: Function[WebDriver, WebElement]): WebElement = {
    val wait = new FluentWait[WebDriver](driver)
      .withTimeout(Duration.ofSeconds(timeoutSeconds))
      .pollingEvery(Duration.ofMillis(pollingMillis))
      .ignoring(classOf[NoSuchElementException])

    wait.until(condition)
  }
}

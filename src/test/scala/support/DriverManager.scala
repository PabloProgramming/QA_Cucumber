package support

import org.openqa.selenium.{NoSuchSessionException, WebDriver}

object DriverManager {
    var driver: WebDriver = _

    def quitDriver(): Unit = {
        try {
            if (driver != null) {
                driver.quit()
                driver = null
            }
        } catch {
            case _: NoSuchSessionException =>
                println("Browser already closed.")
            case e: Exception =>
                println(s"Error closing browser: ${e.getMessage}")
        }
    }
}

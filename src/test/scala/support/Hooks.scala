package support

import io.cucumber.scala.{EN, ScalaDsl}
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver

class Hooks extends ScalaDsl with EN {

  var driver: WebDriver = _

  Before {
    println("Launching browser before scenario...")
    driver = new ChromeDriver()
    driver.manage().window().maximize()
  }

  After {
    println("Closing browser after scenario...")
    driver.quit()
  }

}
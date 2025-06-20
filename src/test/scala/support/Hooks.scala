package support

import io.cucumber.scala.{EN, ScalaDsl, Scenario}
import org.openqa.selenium.chrome.{ChromeDriver, ChromeOptions}
import utils.ScreenCapture.takeScreenshot

import java.io.File


class Hooks extends ScalaDsl with EN {

  val options = new ChromeOptions()
  options.addArguments("--headless=new")

  Before {
    println("Launching browser before scenario...")
    DriverManager.driver = new ChromeDriver(options)
    DriverManager.driver.manage().window().maximize()
  }

  After { scenario: Scenario =>
    if (scenario.isFailed) {
      println("Scenario failed! Taking screenshot...")
      val screenshotFile: File = takeScreenshot(DriverManager.driver, "/Users/pablo.montalvo/Documents/Screenshots/", s"${scenario.getName}_failure")
      println(s"Saved screenshot to: ${screenshotFile.getPath} - ✅")
    }
    println("Closing browser after scenario...")
    DriverManager.quitDriver()
  }

}

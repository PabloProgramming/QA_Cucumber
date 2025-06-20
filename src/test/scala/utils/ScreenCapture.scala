package utils

import org.openqa.selenium.io.FileHandler
import org.openqa.selenium.{OutputType, TakesScreenshot, WebDriver}

import java.io.File
import java.text.SimpleDateFormat
import java.util.Date

object ScreenCapture {

  def takeScreenshot(driver: WebDriver, folderPath: String, fileName: String): File = {
    val timeStamp = new SimpleDateFormat("YYYY-MM-DD_HH-MM-SS").format(new Date())
    val srcFile: File = driver.asInstanceOf[TakesScreenshot].getScreenshotAs(OutputType.FILE)
    val screenshot = new File(s"$folderPath/${fileName}_$timeStamp.png")
    FileHandler.copy(srcFile, screenshot)
    screenshot
  }

}
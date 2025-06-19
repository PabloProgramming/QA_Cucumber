package utils

object ConfigReader {

  // Create a new Scala Properties object to hold key-value pairs from the config file
  private val props = new java.util.Properties()

  // Load the 'config.properties' file as an input stream from the classpath
  private val stream = getClass.getClassLoader.getResourceAsStream("config.properties")

  // Check that the config file was found; if not, throw a runtime exception with a message
  require(stream != null, "config.properties file not found!")

  // Load properties from the input stream into the 'props' object
  props.load(stream)

  // Define a method to get the value of a property by its key
  def get(key: String): String = {
    // Get the value associated with the key from the properties
    val value = props.getProperty(key)

    // If the key does not exist, throw an exception indicating the missing key
    require(value != null, s"Property '$key' not found!")

    // Return the found value
    value
  }
}


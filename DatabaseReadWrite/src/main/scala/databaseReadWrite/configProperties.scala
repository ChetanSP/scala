package databaseReadWrite

import java.util.Properties
import org.apache.log4j.PropertyConfigurator

object configProperties {

  val connections = new java.util.Properties
  connections.load(getClass().getResourceAsStream("/config.properties"))
  PropertyConfigurator.configure(connections)

  val pass = connections.getProperty("pass")
}
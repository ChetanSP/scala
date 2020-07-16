package databaseReadWrite

import java.util.Properties
import org.apache.log4j.PropertyConfigurator

object configProperties {

  val connections = new java.util.Properties
  connections.load(getClass().getResourceAsStream("/config.properties"))
  PropertyConfigurator.configure(connections)

  val environment = connections.getProperty("environment")

  val OracleURL = connections.getProperty("OracleURL")
  val OracleUsername = connections.getProperty("OracleUsername")
  val OraclePassword = connections.getProperty("OraclePassword")
  val OracleDriver = connections.getProperty("OracleDriver")

  val PostGreSQLURL = connections.getProperty("PostGreSQLURL")
  val PostGreSQLUsername = connections.getProperty("PostGreSQLUsername")
  val PostGreSQLPassword = connections.getProperty("PostGreSQLPassword")
  val PostGreSQLDriver = connections.getProperty("PostGreSQLDriver")

  val MemSQLusername = connections.getProperty("MemSQLusername")
  val MemSQLpassword = connections.getProperty("MemSQLpassword")
  val MemSQLHost = connections.getProperty("MemSQLHost")
  val MemSQLPort = connections.getProperty("MemSQLPort")
  val MemSQLDatabase = connections.getProperty("MemSQLDatabase")
  val MemSQLVerification = connections.getProperty("MemSQLVerification")
}
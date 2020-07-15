package databaseReadWrite

import org.apache.spark.sql.{ DataFrame, SaveMode, SparkSession }
import org.apache.log4j.Logger
import org.apache.log4j.Level
import java.sql.SQLException
import oracle.jdbc.pool.OracleDataSource
import java.sql.Connection
import java.sql.ResultSet
import java.sql.DriverManager
import com.memsql.spark.connector._
import com.memsql.spark.connector.util._
import com.memsql.spark.connector.util.JDBCImplicits._

object DatabaseReadWrite {
  val log = Logger.getLogger("Application")
  log.setLevel(Level.ERROR)

  def ExtractDataFromOracle(query: String, spark: SparkSession): DataFrame = {
    var Oracle_DF = spark.emptyDataFrame
    Oracle_DF = spark.read.format("jdbc")
      .option("url", configProperties.OracleURL)
      .option("user", configProperties.OracleUsername)
      .option("password", configProperties.OraclePassword)
      .option("driver", configProperties.OracleDriver)
      .option("dbtable", query)
      .load()
    Oracle_DF
  }

  var ExtractDataFromPostGreSQLCallCounter = 0
  def ExtractDataFromPostGreSQL(query: String, spark: SparkSession): DataFrame = {
    //val query = "(SELECT * FROM ws_dsc_goit.plan_prod_vw LIMIT 5) as query"
    var PostGreSQL_DF = spark.emptyDataFrame
    try {
      PostGreSQL_DF = spark.read.format("jdbc")
        .option("url", configProperties.PostGreSQLURL)
        .option("user", configProperties.PostGreSQLUsername)
        .option("password", configProperties.PostGreSQLPassword)
        .option("driver", configProperties.PostGreSQLDriver)
        .option("dbtable", query)
        .option("fetchSize", "10000")
        .option("numPartitions", 10)
        .load()
      PostGreSQL_DF
    } catch {
      case x: SQLException =>
        log.error("Failed to Connect to Greeplum", x)
        log.info("Retrying Connection to Greeplum")
        ExtractDataFromPostGreSQLCallCounter += 1
        if (ExtractDataFromPostGreSQLCallCounter <= 3) {
          log.info("The value of callCounter is :" + ExtractDataFromPostGreSQLCallCounter)
          ExtractDataFromPostGreSQL(query, spark)
        } else { PostGreSQL_DF }
    }
  }

  def loadDataToOracle(finalDF: DataFrame, destinationTable: String) = {
    finalDF.write.format("jdbc")
      .options(Map("dbtable" -> destinationTable, "url" -> configProperties.OracleURL))
      .option("driver", configProperties.OracleDriver)
      .option("user", configProperties.OracleUsername)
      .option("password", configProperties.OraclePassword)
      .mode(SaveMode.Append)
      .save()
  }

  def OracleTableTruncate(OracleTableName: String) = {
    val ods = new OracleDataSource()
    ods.setUser(configProperties.OracleUsername)
    ods.setPassword(configProperties.OraclePassword)
    ods.setURL(configProperties.OracleURL)

    val connection = ods.getConnection()
    connection.setAutoCommit(false)
    print("Oracle Database connected...")

    val prepUpdateStat = connection.prepareStatement("TRUNCATE TABLE " + OracleTableName)
    prepUpdateStat.execute()
    connection.commit()
    prepUpdateStat.close()
  }

  def excecuteOnOracle(query: String) = {
    try {
      val OracleConnectionObject = DriverManager.getConnection(configProperties.OracleURL, configProperties.OracleUsername, configProperties.OraclePassword)
      val stmt = OracleConnectionObject.createStatement()
      stmt.execute(query)
      //conn.commit()
      OracleConnectionObject.close()
    } catch {
      case e: Throwable =>

        e.printStackTrace

        throw new Exception("Could not excecute statement in Oracle database.")
    }
  }

  var ExtractDataFromPostGreSQLparallellyCallCounter = 0
  def ExtractDataFromPostGreSQLparallelly(query: String, spark: SparkSession, lower: String, upper: String, column: String, numpart: Int): DataFrame = {
    var PostGreSQL_DF = spark.emptyDataFrame
    try {
      PostGreSQL_DF = spark.read.format("jdbc")
        .option("url", configProperties.PostGreSQLURL)
        .option("user", configProperties.PostGreSQLUsername)
        .option("password", configProperties.PostGreSQLPassword)
        .option("driver", configProperties.PostGreSQLDriver)
        .option("lowerBound", lower.toDouble.toLong)
        .option("upperBound", upper.toDouble.toLong)
        .option("partitionColumn", column)
        .option("fetchSize", "10000")
        .option("numPartitions", numpart)
        .option("dbtable", query)
        .load()
      PostGreSQL_DF
    } catch {
      case x: SQLException =>
        log.error("Failed to Connect to Greeplum", x)
        log.info("Retrying Connection to Greeplum")
        ExtractDataFromPostGreSQLparallellyCallCounter += 1
        if (ExtractDataFromPostGreSQLparallellyCallCounter <= 3) {
          log.info("The value of callCounter is :" + ExtractDataFromPostGreSQLparallellyCallCounter)
          ExtractDataFromPostGreSQLparallelly(query, spark, lower, upper, column, numpart)
        } else { PostGreSQL_DF }
    }
  }

  def ExtractDataFromMemSQL(query: String, spark: SparkSession): DataFrame = {

    var MemSQL_DF = spark.emptyDataFrame

    val MemSQLHost = configProperties.MemSQLHost
    val MemSQLPort = configProperties.MemSQLPort
    val MemSQLDatabase = configProperties.MemSQLDatabase
    val MemSQLVerification = configProperties.MemSQLVerification
    val MemSQLUrl = s"jdbc:mysql://$MemSQLHost:$MemSQLPort/$MemSQLDatabase?$MemSQLVerification"

    MemSQL_DF = spark.read.format("jdbc")
      .option("driver", "com.mysql.jdbc.Driver")
      .option("url", MemSQLUrl)
      .option("user", configProperties.MemSQLusername)
      .option("password", configProperties.MemSQLpassword)
      .option("dbtable", s"( $query ) t")
      .load()

    MemSQL_DF

  }

  def loadDataToMemSQL(final_DF: DataFrame, spark: SparkSession, TargetTableName: String): Unit = {
    println("Data will be written into : " + TargetTableName)

    val MemSQLHost = configProperties.MemSQLHost
    val MemSQLPort = configProperties.MemSQLPort
    val MemSQLDatabase = configProperties.MemSQLDatabase
    val MemSQLVerification = configProperties.MemSQLVerification
    val MemSQLUrl = s"jdbc:mysql://$MemSQLHost:$MemSQLPort/$MemSQLDatabase?$MemSQLVerification"

    try {
      final_DF.write
        .format("jdbc")
        .mode("append")
        .option("driver", "com.mysql.jdbc.Driver")
        .option("url", MemSQLUrl)
        .option("user", configProperties.MemSQLusername)
        .option("password", configProperties.MemSQLpassword)
        .option("numPartitions", 10)
        .option("dbtable", TargetTableName)
        .save(TargetTableName)
    } catch {
      case unknown: Exception => {
        println(s"Unknown exception in write Job: $unknown")
        None
      }
    }
  }

  def executeOnMemsql(query: String) = {
    val MemSQLHost = configProperties.MemSQLHost
    val MemSQLPort = configProperties.MemSQLPort
    val MemSQLDatabase = configProperties.MemSQLDatabase
    val MemSQLVerification = configProperties.MemSQLVerification
    val MemSQLUrl = s"jdbc:mysql://$MemSQLHost:$MemSQLPort/$MemSQLDatabase?$MemSQLVerification"

    try {
      val MemSQLconnection = DriverManager.getConnection(MemSQLUrl, configProperties.MemSQLusername, configProperties.MemSQLpassword)
      val statement = MemSQLconnection.createStatement()
      statement.execute(query)
      MemSQLconnection.close()
    } catch {
      case e: Throwable => e.printStackTrace
    }
  }

}



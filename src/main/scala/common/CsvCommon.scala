package common

import org.apache.spark.sql.{DataFrame, SparkSession}
import org.slf4j.LoggerFactory

import java.util.Properties

object CsvCommon {
  private val logger = LoggerFactory.getLogger(getClass.getName)

  def getCsvCommonProps(): Properties = {


    logger.info("getCsvCommonProps method started")
    //println("getCsvCommonProps method started")
    val csvConnectionProperties = new Properties()
    csvConnectionProperties.put("header", "true")
    csvConnectionProperties.put("inferSchema", "true")
    csvConnectionProperties.put("delimiter", ",")
    //println("getCsvCommonProps method ended")
    logger.info("getCsvCommonProps method ended")

    csvConnectionProperties
  }

  def csv_Reader(spark: SparkSession, inputPath: String): Option[DataFrame] = {
    //print("Csv data reader method started")

    try {
      logger.info("Csv data reader method started")
      //  val pgCourseDataframe = spark.read.jdbc("jdbc:postgresql://localhost:5432/futurex", pgTable, getPostgresCommonProps())
      val csvDataframe = spark.read.option("delimiter", getCsvCommonProps().getProperty("delimiter")).option("header", getCsvCommonProps().getProperty("header")).csv(inputPath)
      logger.info("Csv data reader method ended")
      Some(csvDataframe)
    } catch {
      case e: Exception => logger.error("An error has occured in csv_reader method" + e.printStackTrace())
        System.exit(1)
        None
    }
  }

  def csv_Writer(dataFrame: DataFrame, outputPath: String): Unit = {
    //print("Csv data reader method started")

    try {
      logger.info("Csv data writer method started")
      //  val pgCourseDataframe = spark.read.jdbc("jdbc:postgresql://localhost:5432/futurex", pgTable, getPostgresCommonProps())
      val csvDataframe = dataFrame.write.option("header", "True")
        .option("delimiter", ",").mode("append")
        .csv(outputPath)
      logger.info("Csv data writer method ended")

    } catch {
      case e: Exception => logger.error("An error has occurred in csv_Writer method" + e.printStackTrace())
        System.exit(1)

    }
  }

}

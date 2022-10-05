package common

import org.apache.spark.sql.SparkSession
import org.slf4j.LoggerFactory

object SparkCommon {
  private val logger = LoggerFactory.getLogger(getClass.getName)

  def createSparkSession(): Option[SparkSession] = {
    //println("createSparkSession method started")

    try {
      logger.warn("createSparkSession method started")
      val spark = SparkSession
        .builder
        .appName("Sample Application")
        //.config("spark.master", "local")
        .getOrCreate()
      //println("createSparkSession method ended")
      logger.warn("createSparkSession method ended")
      Some(spark)

    } catch {
      case e: Exception =>
        logger.error("An Exception occured in Spark Session Ceation" + e.printStackTrace())
        System.exit(1)
        None


    }


  }


}

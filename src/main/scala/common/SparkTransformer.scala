package common

import org.apache.spark.sql.DataFrame
import org.slf4j.LoggerFactory

object SparkTransformer {
  private val logger = LoggerFactory.getLogger(getClass.getName)

  def replaceNullValues(dataFrame: DataFrame): DataFrame = {
    logger.warn("replaceNullValues method started")
    logger.info(getClass.getName)

    val transformedDF = dataFrame.na.fill("Unknown", Seq("location"))
      .na.fill(value = "0", Seq("age"))
    logger.warn("replaceNullValues method ended")
    transformedDF
  }

}

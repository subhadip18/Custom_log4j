/* SimpleApp.scala */
package Sample

import common.CsvCommon.getClass
import common.{CsvCommon, FXJsonParser, SparkCommon, SparkTransformer}
import org.apache.spark.sql.SparkSession
import org.slf4j.LoggerFactory
import org.apache.log4j.Level
import java.nio.file.{Files, Paths, StandardCopyOption}


object Sample {
  //private val logger = LoggerFactory.getLogger(getClass.getName)
  private val logger = LoggerFactory.getLogger(getClass.getName)

  def toLogger(m: String): Unit = {
    logger.warn(m)
  }

  def main(args: Array[String]) {
    try {
     // println(args(0))
     // println(args(1))


      toLogger("test")
      logger.info("main method started")
      val spark: SparkSession = SparkCommon.createSparkSession().get
      // val csvDataframe = spark.read.option("delimiter", ",").option("header","true").csv("input/input_emp.csv")

      //val inputPath = FXJsonParser.fetchInputPath()
      //val inputPath = "input/input_emp.csv"
      val inputPath = "dbfs:/FileStore/shared_uploads/subhadip.chanda@databricks.com/input_emp.csv"
      val csvDataframe = CsvCommon.csv_Reader(spark, inputPath).get
      csvDataframe.show()

      val transformedDF1 = SparkTransformer.replaceNullValues(csvDataframe)
      transformedDF1.show()
      print(transformedDF1.count())

      //val outputPath = FXJsonParser.fetchOutputPath()
      //val outputPath = "output/output_emp.csv"
      val outputPath = "dbfs:/FileStore/shared_uploads/subhadip.chanda@databricks.com/output"
      CsvCommon.csv_Writer(transformedDF1,outputPath)

      logger.info("main method ended")




//      val path = Files.move(
//        Paths.get("/databricks/driver/logs/log4j-subh-sample.log"),
//        Paths.get("/dbfs/FileStore/shared_uploads/subhadip.chanda@databricks.com/log4j-subh-sample.log"),
//        StandardCopyOption.REPLACE_EXISTING
//      )

      val path = Files.move(
        Paths.get("/databricks/driver/logs/log4j-subh-sample.log"),
        Paths.get("/databricks/driver/logs/log4j-active.log"),
        StandardCopyOption.REPLACE_EXISTING
      )

      if (path != null) {
        println("moved the file  successfully")
      } else {
        println("could NOT move the file ")
      }

    } catch {
      case  e:Exception =>
        logger.error("An error has occured in the main method" + e.printStackTrace())
    }



  }
}
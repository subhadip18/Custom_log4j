package com.msorg.Sample1

import common.{CsvCommon, FXJsonParser, SparkCommon, SparkTransformer}
import org.apache.spark.sql.SparkSession
import org.slf4j.LoggerFactory

object Sample1 {
  //private val logger = LoggerFactory.getLogger(getClass.getName)

  def main(args: Array[String]) {


    val spark = SparkSession.builder.appName("Sample Application").getOrCreate()
    //logger.warn("main method started")
    //val spark: SparkSession = SparkCommon.createSparkSession().get


    val data = Seq(
      ("Alex", 45, "NYC"),
      ("Maria", 30, "Texas"),
      ("Bob", 34, "AB")
    )

    val df = spark.createDataFrame(data).toDF("name", "age", "location")
    df.show()
    println(s"Count: ${df.count}")
  }
}

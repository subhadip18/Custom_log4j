package common

import com.typesafe.config.{Config, ConfigFactory}
import org.slf4j.LoggerFactory

object FXJsonParser {
  private val logger = LoggerFactory.getLogger(getClass.getName)

  def readJsonFile(): Config = {
    ConfigFactory.load("Tx_Config.json")
  }

  def fetchInputPath(): String = {
    val inputPath = readJsonFile().getString("body.input_path")
    inputPath
  }

  def fetchOutputPath(): String = {
    val outputPath = readJsonFile().getString("body.output_path")
    outputPath
  }

  def returnConfigValue(key: String): String = {
    val value = readJsonFile().getString(key)
    value
  }


}

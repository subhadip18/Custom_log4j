logLevel := Level.Debug

name := "sample-project"

version := "0.1"

scalaVersion := "2.12.15"

val sparkVersion = "3.1.3"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion % "provided",
  "org.apache.spark" %% "spark-sql" % sparkVersion % "provided",
  "org.scala-lang" % "scala-compiler" % "2.12.15",
  "org.apache.logging.log4j" % "log4j-slf4j-impl" % "2.14.1" % Test,
  "org.apache.logging.log4j" % "log4j-api" % "2.14.1",
  "org.apache.logging.log4j" % "log4j-core" % "2.14.1",
  "org.apache.logging.log4j" % "log4j-api-scala_2.11" % "11.0",
  "com.typesafe" % "config" % "1.4.1",
  "junit" % "junit" % "4.13.1" % Test,
  "com.lihaoyi" %% "os-lib" % "0.7.1"
)

assembly / assemblyMergeStrategy := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}

// JAR file settings
assembly / assemblyOption := (assembly/assemblyOption).value.copy(includeScala = false)
assembly / assemblyJarName := s"${name.value}_${scalaBinaryVersion.value}-${sparkVersion}_${version.value}-assembly.jar"

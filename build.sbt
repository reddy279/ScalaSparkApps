//import AssemblyKeys._
// lazy loading the values
import sbt.Keys._


lazy val commonSettings = Seq(
  version in ThisBuild := "1.0-SNAPSHOT",
  organization := "com.scala.spark",
  scalaVersion := "2.10.4"

)

lazy val project = Project("ScalaSparkApps", file("."))
  .settings(commonSettings: _*)
  .settings(baseAssemblySettings: _*)
  .settings(

    // resolvers += Resolver.defaultLocal,
// publishLocal := true ,
    // parallelExecution :=false,
    offline := true,
    test in assembly := {},

    /**
     * The following statements imports all the required dependent jars that are required for the entire application
     */
    libraryDependencies += "org.apache.spark" %% "spark-core" % "1.5.0",
    libraryDependencies += "org.apache.spark" %% "spark-sql" % "1.5.0",
    libraryDependencies += "org.apache.spark" %% "spark-launcher" % "1.5.0",
    libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.4" % "test",
    libraryDependencies += "org.apache.hadoop" % "hadoop-aws" % "2.6.1" excludeAll ExclusionRule(organization = "javax.servlet"),
    libraryDependencies += "log4j" % "log4j" % "1.2.17",
    libraryDependencies += "org.json4s" %% "json4s-jackson" % "3.2.10",
    libraryDependencies += "org.json4s" %% "json4s-native" % "3.2.10",
    libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.3.11",
//    libraryDependencies += "org.apache.spark" %% "spark-streaming" % "1.5.0",
//    libraryDependencies += "org.apache.spark" %% "spark-streaming-kafka" % "1.5.0",



      //    mappings in Universal <+= (assembly in compile) map {
      //      jar => jar ->(jar.getName)}


      /**
       * The following statements avoids any conflicts when same jars are imported multiple times from different packages.
       */
      assemblyMergeStrategy in assembly := {
      case PathList("javax", "serlvet", xs@_*) => MergeStrategy.last
      case PathList("org", "apache", xs@_*) => MergeStrategy.last
      case PathList("org", "apache.spark", xs@_*) => MergeStrategy.last
      case PathList("com", "google", xs@_*) => MergeStrategy.last
      case PathList("com", " esotericsoftware", xs@_*) => MergeStrategy.last
      case PathList("org", "postgresql", xs@_*) => MergeStrategy.last
      case PathList("org", "typesafe", xs@_*) => MergeStrategy.last
      case "overview.html" => MergeStrategy.last
      case "parquet.thrift" => MergeStrategy.last
      case "plugin.xml" => MergeStrategy.last
    }
  )

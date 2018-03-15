name := "s3-kafka"

version := "0.1"

scalaVersion in ThisBuild := "2.11.8"

lazy val commonSettings = Seq(
  version := "0.1-SNAPSHOT",
  organization := "com.macwinux",
  scalaVersion := "2.11.8"
)


lazy val sparkS3LogsKafka = (project in file(".")).
  settings(commonSettings: _*)
  .settings(
    assemblyJarName in assembly := "s3-kafka.jar"
  ).
  settings(
    resolvers ++= Seq(
      "twttr" at "http://maven.twttr.com/",
      "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots",
      "Scalaz Bintray Repo" at "http://dl.bintray.com/scalaz/releases"
    )
  ).settings(libraryDependencies ++= Seq(

  "org.apache.spark" %% "spark-core" % "2.2.0",
  "org.apache.spark" %% "spark-sql" % "2.2.0",
  "org.apache.spark" %% "spark-sql-kafka-0-10" % "2.2.0",
  "org.apache.spark" %% "spark-streaming" % "2.2.0",
  "org.apache.hadoop" % "hadoop-aws" % "2.8.0"

))

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}
package com.macwinux

import org.apache.spark.sql.{Dataset, SparkSession}

object SparkS3LogsKafka extends App {


  val spark = SparkSession
      .builder
      .appName("SparkLogss3Kafka")
      .config("spark.master", "local[*]")
      .getOrCreate()
  spark.sparkContext.hadoopConfiguration.set("fs.s3n.impl", "org.apache.hadoop.fs.s3native.NativeS3FileSystem")
  spark.sparkContext.hadoopConfiguration.set("fs.s3n.awsAccessKeyId", "awsAccessKeyId")
  spark.sparkContext.hadoopConfiguration.set("fs.s3n.awsSecretAccessKey", "awsSecretAccessKey")
  val data: Dataset[String] = spark
    .read
    .option("checkpointLocation","s3n://name-bucket/checkpointLocation/")
    .textFile("s3n://name-bucket/path")

  //data.foreach(println(_))

  data.write
    .format("kafka")
    .option("kafka.bootstrap.servers", "server:9092")
    .option("topic","topic-name")
    .save()



}

package com.tp.spark.core

import com.tp.spark.core.Producer._
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd._
import com.tp.spark.utils.Message
import com.tp.spark.utils.Message._
import org.apache.kafka.clients.producer.ProducerRecord

import scala.io.Source


object Main {
  val pathToFile = "data/out2.csv"

  def loadData(): Unit = {

    val conf = new SparkConf()
      .setAppName("Drone")
      .setMaster("local[*]")

    val sc = SparkContext.getOrCreate(conf)


  }
}

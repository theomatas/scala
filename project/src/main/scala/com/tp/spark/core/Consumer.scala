package com.tp.spark.core

import java.io.FileWriter
import java.util

import org.apache.kafka.clients.consumer.KafkaConsumer
import java.util.Properties

import scala.collection.JavaConverters._

object Consumer extends App{
  val props = new Properties()

  props.put("bootstrap.servers", "localhost:9094")

  props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")

  props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")

  props.put("auto.offset.reset", "latest")

  props.put("group.id", "consumer-group")

  val consumer: KafkaConsumer[String, String] = new KafkaConsumer[String, String](props)

  val topic = "msgdrone"

  consumer.subscribe(util.Arrays.asList(topic))


  getMessage()


  def getMessage() : Unit = {
    var i : Int = 0
    println("fetching new drones data")
    val records=consumer.poll(3)
    for (record<-records.asScala){
      val fw = new FileWriter("drone_"+i+".JSON", true)
      try {
        fw.write(record.value()+"\n")
      }
      finally fw.close()
      i += 1
      println(record)
      }
  }
}

package com.tp.spark.core

import org.apache.kafka.clients.producer._
import java.util.Properties

import com.tp.spark.core.Main.pathToFile
import com.tp.spark.utils.Message
import com.tp.spark.utils.Message._

import scala.io.Source

object Producer extends App{
  val props = new Properties()
  props.put("bootstrap.servers", "localhost:9092")

  props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")

  props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")

  val producer = new KafkaProducer[String, String](props)

  val topic="msgdrone"

  writeToKafka()

  def writeToKafka(): Unit = {
    println("Start sending messages")
    Source.fromFile(pathToFile).getLines().drop(1).foreach{Line=>val key = Line.split(','){0}
      val Summons_Number = Line.split(','){1}
      val Plate_ID = Line.split(','){2}
      val Issue_Date = Line.split(','){3}
      val Violation_Code = Line.split(','){4}
      val Violation_Description = Line.split(','){5}
      val State = Line.split(','){6}
      val Vehicle_Make = Line.split(','){7}
      val Vehicle_Expiration_Date = Line.split(','){8}
      val Vehicle_Color = Line.split(','){9}
      val alert = Line.split(','){10}


      val message: msg = new msg(key, Summons_Number, Plate_ID, Issue_Date, Violation_Code, Violation_Description,
        State, Vehicle_Make, Vehicle_Expiration_Date, Vehicle_Color, alert)
      if (message.alert=="1")
      {
        val record = new ProducerRecord(topic, key, message.getJson());
        producer.send(record)
      }

    }
    println("Messages sent.")
    producer.close()
  }
}

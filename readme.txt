0. already done alert_maker.ipynb convert light_2.csv -> out2.csv 

1. Open 4 terminals in the kafka_2.12-2.4.0

2. Start zookeeper in the first terminal
bin/zookeeper-server-start.sh config/zookeeper.properties

3. In the second other terminal start kafka
bin/kafka-server-start.sh config/server.properties

4. Create a topic named msgdrone in the third terminal
bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic msgdrone

5. Create producer in the third terminal
bin/kafka-console-producer.sh --broker-list localhost:9092 --topic msgdrone

6. Create consumer in the fourth terminal
bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic msgdrone --from-beginning

7. Open another terminal in the Project_Scala file and "sbt run", choose to run Producer

8. ploter_DB_maker.ipynb use data.json to do database and data viz

Authors:

- Mickaël Cheng
- Theo Matas
- Nicolas Berat

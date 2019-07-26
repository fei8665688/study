package sparkStreaming

import java.util

import com.google.common.collect.Sets
import kafka.admin.{AdminClient, AdminUtils}
import kafka.server.ConfigType
import kafka.utils.ZkUtils
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.spark.sql.SparkSession
import org.apache.spark.streaming.dstream.InputDStream
import org.apache.spark.streaming.{Durations, Seconds, StreamingContext}
import org.apache.spark.streaming.kafka010.{CanCommitOffsets, ConsumerStrategies, KafkaUtils, LocationStrategies}

import scala.collection.mutable

/**
  * @author phil.zhang
  * @date 2019/7/15
  */
object SparkStreamingTest {

  def main(args: Array[String]): Unit = {
//    var session: SparkSession = SparkSession.builder().master("local[2]").appName("kafka-partition-test").getOrCreate()
//
//    val context = new StreamingContext(session.sparkContext, Durations.minutes(1))

    // 获取topic 分区数
    val zkUtils = ZkUtils.apply("192.168.1.147:2181", 1000, 1000, false)

    // 获取topic分区数
    val topic = zkUtils.getTopicPartitionCount("es-save")

    if (topic != None) {
      val size = Integer.valueOf(topic.get.toString)
      print(size)
    }



    val set: util.HashSet[String] = Sets.newHashSet()
    set.add("topic")

    // 构建流
/*    val dstream: InputDStream[ConsumerRecord[String, Array[Byte] ]] = KafkaUtils
      .createDirectStream(context, LocationStrategies.PreferConsistent
        , ConsumerStrategies.Subscribe(set, new util.HashMap[String, Object]()))


    // 提交offset到kafka
    dstream.asInstanceOf[CanCommitOffsets].commitAsync(null)*/

    // 提交offset到zk
//    zkUtils.updatePersistentPath("path","data",)

  }

}

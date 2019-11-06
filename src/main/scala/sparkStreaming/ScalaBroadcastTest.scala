package sparkStreaming

import org.apache.spark.SparkContext
import org.apache.spark.broadcast.Broadcast
import org.apache.spark.sql.SparkSession
import org.apache.spark.streaming.{Durations, StreamingContext}

/**
  * @author phil.zhang
  * @date 2019/8/12
  */
object ScalaBroadcastTest {


  def main(args: Array[String]): Unit = {
    val session = SparkSession.builder().master("local[2]").appName("scalaBroadcastTest").getOrCreate()

    val sparkContext = session.sparkContext

    val streamingContext = new StreamingContext(sparkContext, Durations.seconds(5))
    var broadcast = sparkContext.broadcast(System.currentTimeMillis())
/*    new Thread(new Runnable {
      override def run(): Unit = {
        while (true) {
          broadcast.unpersist()
          broadcast = sparkContext.broadcast(System.currentTimeMillis())
          Thread.sleep(1000)
        }
      }
    }).start()*/

    val dstream = streamingContext.socketTextStream("192.168.1.162",8888)
    dstream.map(x => x)
      .foreachRDD(rdd => {
        rdd.foreach(x => print(x+broadcast.hashCode()))
      })
    streamingContext.start()
    streamingContext.awaitTermination()
  }

}

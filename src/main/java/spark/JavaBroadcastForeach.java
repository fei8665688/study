package spark;

import java.util.concurrent.atomic.AtomicLong;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;

/**
 * @author phil.zhang
 * @date 2019/8/12
 */
public class JavaBroadcastForeach {

  private static Broadcast<Long> broadcastLong = null;

  private static AtomicLong num = new AtomicLong();

  public static void main(String[] args) {
    SparkSession spark = SparkSession.builder().appName("broadcast").getOrCreate();

    JavaSparkContext sparkContext = new JavaSparkContext(spark.sparkContext());

    JavaStreamingContext streamingContext = new JavaStreamingContext(sparkContext,
        Durations.seconds(5));
    JavaReceiverInputDStream<String> dStream = streamingContext
        .socketTextStream("192.168.1.162", 8888);
    dStream.foreachRDD(rdd -> {
      Broadcast<Long> broadcast = getInstance(sparkContext);
      rdd.foreach(x -> {
        System.out.println(x + broadcast.value());
      });
    });
  }

  private static Broadcast<Long> getInstance(JavaSparkContext sparkContext) {
    if (null == broadcastLong) {
      broadcastLong = sparkContext.broadcast(System.currentTimeMillis());
    }else {
      if ((num.getAndAdd(1) % 5) == 0) {
        broadcastLong = sparkContext.broadcast(System.currentTimeMillis());
      }
    }
    return broadcastLong;
  }

}

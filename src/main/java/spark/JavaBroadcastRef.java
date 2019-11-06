package spark;

import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import scala.runtime.ObjectRef;

/**
 * @author phil.zhang
 * @date 2019/8/12
 */
public class JavaBroadcastRef {

  public static void main(String[] args) throws InterruptedException {
    SparkSession sparkSession = SparkSession.builder().appName("broadRef").master("local[2]").getOrCreate();
    JavaSparkContext jssc = new JavaSparkContext(sparkSession.sparkContext());
    JavaStreamingContext streamingContext = new JavaStreamingContext(jssc,
        Durations.seconds(5));
    JavaReceiverInputDStream<String> inputDStream = streamingContext
        .socketTextStream("192.168.1.162", 8888);
    Broadcast<Long> broadcast = jssc.broadcast(System.currentTimeMillis());

//    ObjectRef<Broadcast<Long>> objectRef = new ObjectRef<>(broadcast);
//    refresh(objectRef, jssc);

    inputDStream.map(x->x)
        .foreachRDD(rdd -> {
          rdd.foreach(x -> System.out.println(x+broadcast.hashCode()));
        });

    streamingContext.start();
    streamingContext.awaitTermination();
  }

  private static void refresh(ObjectRef<Broadcast<Long>> objectRef, JavaSparkContext jssc) {
    new Thread(() -> {
      while (true) {
        Broadcast<Long> broadcast = objectRef.elem;
        broadcast.unpersist();
        Broadcast<Long> longBroadcast = jssc.broadcast(System.currentTimeMillis());
        objectRef.elem = longBroadcast;
        try {
          Thread.sleep(10000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }).start();
  }
}

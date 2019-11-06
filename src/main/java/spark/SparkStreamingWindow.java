package spark;

import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import scala.Tuple2;

/**
 * @author phil.zhang
 * @date 2019/8/13
 */
public class SparkStreamingWindow {

  public static void main(String[] args) throws InterruptedException {
    SparkSession sparkSession = SparkSession.builder().appName("windowTest").master("local[2]")
        .getOrCreate();

    JavaSparkContext javaSparkContext = new JavaSparkContext(sparkSession.sparkContext());

    JavaStreamingContext streamingContext = new JavaStreamingContext(javaSparkContext,
        Durations.seconds(5));

    JavaReceiverInputDStream<String> dStream = streamingContext
        .socketTextStream("192.168.1.162", 8888);

    JavaPairDStream<String, Integer> pairDStream = dStream
        .mapToPair(x -> new Tuple2<>(x, 1));

    pairDStream.cache();

    pairDStream.map(x -> x._1).foreachRDD(rdd -> rdd.foreach(x -> System.out.println("stream: "+x)));

    pairDStream.window(Durations.seconds(60), Durations.seconds(30))
        .reduceByKey((x, y) -> x + y)
        .foreachRDD(rdd -> {
          rdd.foreach(x -> System.out.println("window: "+ x._1+":"+x._2));
        });

    streamingContext.start();
    streamingContext.awaitTermination();
  }

}

package spark;


import java.io.IOException;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.SparkSession.Builder;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.StreamingContext;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;


/**
 * @author phil.zhang
 * @date 2019/7/24
 */
public class JavaBroadcastTest {


  private volatile static  Broadcast<Long> listBroadcast;

  public static void main(String[] args) throws IOException, InterruptedException {
    Builder builder = SparkSession.builder().appName("broadCastTest");
    if (null == args || args.length == 0) {
      builder.master("local[3]");
    }
    SparkSession spark = builder.getOrCreate();
    getBroadcast(spark);
    refresh(spark);

    JavaStreamingContext jssc = new JavaStreamingContext(
        new StreamingContext(spark.sparkContext(), Durations.seconds(5)));

    JavaDStream<String> dStream = jssc.socketTextStream ("192.168.1.162",9999);
    dStream.map(x -> x+listBroadcast.value())
        .foreachRDD(x-> {
          x.foreachPartition(str-> {
            while (str.hasNext()) {
              System.out.println(str.next());
            }
          });
        });

    jssc.start();
    jssc.awaitTermination();

  }
  private static Broadcast<Long> getBroadcast(SparkSession spark) {

    JavaSparkContext sparkContext = new JavaSparkContext(spark.sparkContext());

    listBroadcast = sparkContext.broadcast(System.currentTimeMillis());
    return listBroadcast;
  }

  public Broadcast<Long> getListBroadcast() {
    return listBroadcast;
  }
  private static void refresh(SparkSession spark) {
    new Thread(() -> {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      listBroadcast.unpersist();
      listBroadcast = new JavaSparkContext(spark.sparkContext())
          .broadcast(System.currentTimeMillis());
    }).start();
  }

}
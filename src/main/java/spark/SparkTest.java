package spark;


import com.google.common.collect.Lists;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.StreamingContext;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;

/**
 * @author phil.zhang
 * @date 2019/7/24
 */
public class SparkTest {

  private static Broadcast<List<String>> listBroadcast;

  private static ThreadPoolExecutor pool = new ThreadPoolExecutor(10,10,1000L, TimeUnit.SECONDS,new LinkedBlockingQueue<>(10));

  public static void main(String[] args) throws IOException, InterruptedException {
    SparkSession spark = SparkSession.builder().appName("broadCastTest").master("local[2]")
        .getOrCreate();
    listBroadcast = getBroadcast(spark);

    refresh(spark);


    JavaStreamingContext jssc = new JavaStreamingContext(
        new StreamingContext(spark.sparkContext(), Durations.seconds(5)));

    JavaDStream<String> dStream = jssc.socketTextStream ("192.168.1.162",9999);
    dStream.flatMap(x-> Lists.newArrayList(x.split(" ")).iterator())
        .foreachRDD(x-> {
          x.foreach(str-> {
            System.out.println(str + ":" + listBroadcast.value().size());
          });
        });

    jssc.start();
    jssc.awaitTermination();

  }

  private static Broadcast<List<String>> getBroadcast(SparkSession spark) throws IOException {
    JavaSparkContext sparkContext = new JavaSparkContext(spark.sparkContext());
    List<String> list = Lists.newArrayList();
    BufferedReader reader = new BufferedReader(new FileReader("c:/zf/custom/list.txt"));
    String[] s = reader.readLine().split(" ");
    for (String str : s) {
      list.add(str);
    }

    listBroadcast = sparkContext.broadcast(list);
    return listBroadcast;
  }

  private static void refresh(SparkSession spark) throws InterruptedException, IOException {

      pool.execute(() -> {
        while (true) {

          try {
            Thread.sleep(60*1000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }

          List<String> list = Lists.newArrayList();
          JavaSparkContext sparkContext = new JavaSparkContext(spark.sparkContext());
          try {
            BufferedReader reader = new BufferedReader(new FileReader("c:/zf/custom/list.txt"));

            String[] s = reader.readLine().split(" ");
            for (String str : s) {
              list.add(str);
            }
            System.out.println("------------");
            listBroadcast.unpersist();
            listBroadcast= sparkContext.broadcast(list);
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      });


  }

}

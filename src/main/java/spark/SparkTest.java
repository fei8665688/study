package spark;


import com.google.common.collect.Lists;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.SparkSession.Builder;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.StreamingContext;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author phil.zhang
 * @date 2019/7/24
 */
public class SparkTest {

  private static Logger logger = LoggerFactory.getLogger(SparkTest.class);

  private static Broadcast<List<String>> listBroadcast;

  private static ThreadPoolExecutor pool = new ThreadPoolExecutor(10,10,1000L, TimeUnit.SECONDS,new LinkedBlockingQueue<>(10));

  private static Configuration conf ;
  static  {
    conf = new Configuration();
    conf.set("fs.defaultFS", "hdfs://192.168.1.161:8020");
    conf.set("ipc.client.connect.timeout", "60000");
    System.setProperty("HADOOP_USER_NAME", "hdfs");
  }

  public static void main(String[] args) throws IOException, InterruptedException {
    Builder builder = SparkSession.builder().appName("broadCastTest");

    if (null == args || args.length == 0) {
      builder.master("local[3]");
    }
    SparkSession spark = builder.getOrCreate();
    getBroadcast(spark);
    refresh(spark);
//    List<String> list = listBroadcast.value();

    JavaStreamingContext jssc = new JavaStreamingContext(
        new StreamingContext(spark.sparkContext(), Durations.seconds(5)));

    JavaDStream<String> dStream = jssc.socketTextStream ("192.168.1.162",9999);
    dStream.map(x -> x+":"+listBroadcast.getValue())
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

  private static Broadcast<List<String>> getBroadcast(SparkSession spark) throws IOException {
    JavaSparkContext sparkContext = new JavaSparkContext(spark.sparkContext());
    List<String> list = Lists.newArrayList();
    FileSystem fileSystem = FileSystem.get(conf);
    FSDataInputStream inputStream = fileSystem
        .open(new Path("hdfs://192.168.1.161:8020/test/socket/1.txt"));

    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
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
            Thread.sleep(10*1000);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }

          List<String> list = Lists.newArrayList();
          JavaSparkContext sparkContext = new JavaSparkContext(spark.sparkContext());
          try {
            FileSystem fileSystem = FileSystem.get(conf);
            FSDataInputStream inputStream = fileSystem
                .open(new Path("hdfs://192.168.1.161:8020/test/socket/1.txt"));

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//            BufferedReader reader = new BufferedReader(new FileReader("c:/zf/custom/list.txt"));

            String[] s = reader.readLine().split(" ");
            for (String str : s) {
              list.add(str);
            }
            System.out.println("list size:" + list.size());
            listBroadcast.unpersist();
            listBroadcast= sparkContext.broadcast(list);
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      });


  }

}

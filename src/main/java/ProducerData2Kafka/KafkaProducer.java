package ProducerData2Kafka;

import ProducerData2Kafka.bean.Record;
import ProducerData2Kafka.bean.Solution;
import ProducerData2Kafka.util.SdkUtils;
import com.pkfare.collect.LogCollector;
import com.pkfare.collect.common.constant.SerializationType;
import com.pkfare.collect.common.constant.TransType;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author phil.zhang
 * @date 2019/7/2
 */
public class KafkaProducer {

  private static Solution solution;

  private static Record record;

  private static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5,5,1000L, TimeUnit.MILLISECONDS,
      new LinkedBlockingQueue<>(20));


  static {
    LogCollector.getInstance()
        .setAppName("testNifi")
        .setTransType(TransType.Kafka)
        .setSerializationType(SerializationType.BYTE_ARRAY)
        .setBrokers("192.168.1.147:9092,192.168.1.148:9092,192.168.1.149:9092")
        .init();

    solution = new Solution();
    solution.setRequestKey("12443124");
    solution.setRequestTime("2010-12-13 08:10:00.000");
    solution.setCabin("F");
    solution.setRequestTimeCompare("2010-12-12 08:10:00.000");
    solution.setFlightType("OW");
    solution.setOriginAirport("AAA");
    solution.setDestinationAirport("BBB");
    solution.setOriginCity("AAA");
    solution.setDestinationCity("BBB");
    solution.setOriginCountry("A");
    solution.setDestinationCountry("B");
    solution.setOutboundDate("9999-99-99");
    solution.setAdtCount(1);
    solution.setChdCount(0);
    solution.setBuyerCountry("C");
    solution.setBuyerId(1);
    solution.setGroupId(0);
    solution.setSegmentCount(5);
    solution.setAirline1("asd");
    solution.setFlightNumber1("YUI");
    solution.setOriginAirport1("AAA");
    solution.setDestinationAirport1("BBB");
    solution.setMinuteNum(1);
    solution.setHourNum(2);
    solution.setDayNum("2010-12-12");

    record = new Record();
    record.setOrigin("AAA");
    record.setDestination("BBB");
    record.setOriginCity("AAA");
    record.setDestinationCity("BBB");
    record.setFlightType("OW");
    record.setCabin("E");
    record.setOutboundDate("9999-99-99");
    record.setAdtCount(1);
    record.setChdCount(0);
    record.setResultCode(0);
    record.setDuration(10000);
    record.setRequestTime("2010-12-12 08:10:00.000");
    record.setRequestTimeCompare("2010-12-12 08:10:00.000");
    record.setRequestKey("12452452455");
    record.setBuyerId(1);
    record.setGroupId(2);
    record.setOriginCountry("A");
    record.setDestinationCountry("B");
    record.setDayNum("2010-12-12");
  }

  public static void main(String[] args) {
    threadPool.execute(() -> {
      while (true) {
        try {
          SdkUtils.send("dw_lowest_fare_request",record);
          try {
            Thread.sleep(10);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        } catch (IllegalAccessException e) {
          e.printStackTrace();
        }
      }

    });

    threadPool.execute(()->{
      while (true) {
        try {
          SdkUtils.send("dw_lowest_fare_response",solution);
          Thread.sleep(10);
        } catch (IllegalAccessException e) {
          e.printStackTrace();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }

    });
  }



}

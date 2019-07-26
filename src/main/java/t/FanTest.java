package t;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.pkfare.bigdata.common.spark.kafka.KafkaUtil;
import java.util.Iterator;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

/**
 * @author phil.zhang
 * @date 2019/7/25
 */
public class FanTest {



  public static void main(String[] args) {
    KafkaProducer<String, String> producer = KafkaUtil
        .initProducer("192.168.1.147:9092,192.168.1.148:9092,192.168.1.149:9092",
            StringSerializer.class, StringSerializer.class);

/*    Map<String, Object> params = Maps.newHashMap();
    params.put("bootstrap.servers", "192.168.1.147:9092,192.168.1.148:9092,192.168.1.149:9092");
    params.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    params.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

    KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(params);

    ProducerRecord<String, String> record = new ProducerRecord<>("shopping-dwd", "hahah");

    producer.send(record);
    kafkaProducer.send(record);*/


    Map<String, Object> params1 = Maps.newHashMap();
    params1.put("bootstrap.servers", "192.168.1.147:9092,192.168.1.148:9092,192.168.1.149:9092");
    params1.put("group.id", "test");
    // 如果已提交的offset时，从提交的offset开始消费；无提交的offset时，从头开始消费
    params1.put("auto.offset.reset", "earliest");
    params1.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
    params1.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");


    KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(params1);
    kafkaConsumer.subscribe(Lists.newArrayList("shopping-dwd"));
    ConsumerRecords<String, String> records = kafkaConsumer.poll(1000);
    Iterator<ConsumerRecord<String, String>> iterator = records.iterator();
    while (iterator.hasNext()) {
      System.out.println(iterator.next().value());
    }
    System.out.println(records.count());
  }


}

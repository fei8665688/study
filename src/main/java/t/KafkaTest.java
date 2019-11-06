package t;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.pkfare.bigdata.common.spark.kafka.KafkaUtil;
import com.pkfare.collect.common.serialization.ProtoSerializerUtil;
import com.pkfare.collect.entity.EsLogEntity;
import com.pkfare.collect.entity.TransData;
import java.util.Iterator;
import java.util.Map;
import javax.security.auth.login.Configuration;
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
public class KafkaTest {



  public static void main(String[] args) {

    Map<String, Object> params1 = Maps.newHashMap();
    params1.put("bootstrap.servers", "192.168.1.147:9092,192.168.1.148:9092,192.168.1.149:9092");
    params1.put("group.id", "test1");
    // 如果已提交的offset时，从提交的offset开始消费；无提交的offset时，从头开始消费
    params1.put("auto.offset.reset", "earliest");
    params1.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
//    params1.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
    params1.put("value.deserializer", "org.apache.kafka.common.serialization.ByteArrayDeserializer");


    KafkaConsumer<String, byte[]> kafkaConsumer = new KafkaConsumer<>(params1);
    kafkaConsumer.subscribe(Lists.newArrayList("api-solutions"));
/*    while (true) {
      ConsumerRecords<String, String> records = kafkaConsumer.poll(1000);
      Iterator<ConsumerRecord<String, String>> iterator = records.iterator();
      while (iterator.hasNext()) {
        System.out.println(iterator.next());
      }
    }*/

    while (true) {
      ConsumerRecords<String, byte[]> records = kafkaConsumer.poll(1000);
      for (ConsumerRecord<String, byte[]> record : records) {
        TransData transData = ProtoSerializerUtil.deserialize(record.value(), TransData.class);
        if (transData.getData() instanceof EsLogEntity) {
          System.out.println(((EsLogEntity) transData.getData()).getContent());
        }
      }
    }

  }


}

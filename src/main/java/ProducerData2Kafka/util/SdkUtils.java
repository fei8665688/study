package ProducerData2Kafka.util;

import ProducerData2Kafka.bean.ISaveEntity;
import com.alibaba.fastjson.annotation.JSONField;
import com.google.common.collect.Maps;
import com.pkfare.collect.LogCollector;
import com.pkfare.collect.entity.AbstractLogBuilder;
import com.pkfare.collect.entity.LogBuilderFactory;
import com.pkfare.collect.entity.builder.SimpleHashLogBuilder;
import com.pkfare.collect.entity.log.SimpleHashLog;
import com.pkfare.collect.utils.DateTimeUtil;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author phil.zhang
 * @date 2019/7/2
 */
public class SdkUtils {

  private static Map<Class<? extends ISaveEntity>, Field[] > fieldMap = Maps.newHashMap();


  /**
   * 对接collect-service的修改方法
   * @param entityCode entityCode
   * @param entity 待发送至kafka的实体
   */
  public static void send( String entityCode, ISaveEntity entity)
      throws IllegalAccessException {
    AbstractLogBuilder<SimpleHashLog> simpleHashLogAbstractLogBuilder = LogBuilderFactory
        .newSimpleHashBuilder(entityCode);

    SimpleHashLog simpleHashLog = simpleHashLogAbstractLogBuilder.build();
    String requestTime = entity.getTimeStamp();

    long timeStamp = DateTimeUtil.toEpochMilli(DateTimeUtil.parse(requestTime));
    simpleHashLogAbstractLogBuilder.timestamp(timeStamp);

    simpleHashLogAbstractLogBuilder.putAll(convert2Map(entity));

    LogCollector.getInstance().send("air-extract-event-track",simpleHashLogAbstractLogBuilder);
  }

  /**
   * 利用反射转换对象为map
   * @param entity 传入的实体
   * @return 转换后的Map对象
   * @throws IllegalAccessException exception
   */
  private static Map<String, Object> convert2Map(ISaveEntity entity) throws IllegalAccessException {

    Field[] fields = getFields(entity);

    HashMap<String, Object> map = Maps.newHashMap();
    for (Field field : fields) {

      Object value = field.get(entity);
      if (null != value) {
        JSONField[] annotations = field.getAnnotationsByType(JSONField.class);
        // 如果没有JSONField注解
        if (null == annotations || annotations.length == 0) {
          map.put(field.getName(), field.get(entity));
          break;
        }
        // 如果有JSONField注解
        map.put(annotations[0].name(),value);
      }
    }
    return map;
  }


  /**
   * 获取entity的所有成员变量
   * @param entity entity
   * @return field数组
   */
  private static Field[] getFields(ISaveEntity entity) {
    Field[] fields;
    fields = fieldMap.get(entity.getClass());
    if (null == fields) {
      Field[] declaredFields = entity.getClass().getDeclaredFields();
      for (Field declaredField : declaredFields) {
        declaredField.setAccessible(true);
      }
      fieldMap.put(entity.getClass(), declaredFields);
      fields = declaredFields;
    }

    return fields;

  }

}

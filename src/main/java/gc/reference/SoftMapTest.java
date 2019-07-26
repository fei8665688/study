package gc.reference;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

/**
 * @author phil.zhang
 * @date 2019/7/11
 */
public class SoftMapTest {

  private Map<String, SoftReference<String>> cache = new HashMap<>();


  private void putMap(String key, SoftReference<String> value) {
    cache.put(key,value);
  }

  private String getStrByKey(String key) {
    SoftReference<String> softReference = cache.get(key);

    if (null != softReference) {
      return softReference.get();
    }
    return null;
  }

  public static void main(String[] args) {

  }

}

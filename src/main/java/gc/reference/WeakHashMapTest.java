package gc.reference;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * @author phil.zhang
 * @date 2019/7/11
 */
public class WeakHashMapTest {

  public static void main(String[] args) throws Exception {

    // weakHashoMap key键是弱引用的键, 如果key被回收则get是会自动remove掉value
    String test = new String("test");
    String tmp = new String("tmp");
    Map weakmap = new WeakHashMap();
    Map map = new HashMap();
    map.put(test, "test");
    map.put(tmp, "tmp");


    weakmap.put(test, "test");
    weakmap.put(tmp, "tmp");

    map.remove(test);
    test=null;
    tmp=null;

    System.gc();
    Iterator itrTest = map.entrySet().iterator();
    while (itrTest.hasNext()) {
      Map.Entry en = (Map.Entry)itrTest.next();
      System.out.println("map:"+en.getKey()+":"+en.getValue());
    }

    Iterator itrTmp = weakmap.entrySet().iterator();
    while (itrTmp.hasNext()) {
      Map.Entry en = (Map.Entry)itrTmp.next();
      System.out.println("weakmap:"+en.getKey()+":"+en.getValue());
    }
  }

}

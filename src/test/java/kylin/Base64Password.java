package kylin;

import org.junit.Test;

/**
 * @author phil.zhang
 * @date 2019/9/11
 */
public class Base64Password {

  @Test
  public void generateBasePassword() {

    String x = "aaa limit \"100\" group by min(aaa)";
    String s = x.replaceAll("[\\(\\)]"," ").replaceAll("\""," "). replaceAll("\\s+", " ");
    System.out.println(s);
    System.out.println(s.contains(" group by "));
    System.out.println(!s.contains("from"));



  }

}

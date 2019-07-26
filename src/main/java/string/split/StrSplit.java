package string.split;

import java.util.Arrays;
import java.util.List;

/**
 * @author phil.zhang
 * @date 2019/5/6
 */
public class StrSplit {

  public static void main(String[] args) {
    String s = "^B";
    String[] strings = s.split("[/\\^]");
    List<String> strings1 = Arrays.asList(strings);
    System.out.println(strings1);
    System.out.println("数组长度为：" + strings.length);
    for (String string : strings) {
      System.out.println("数据："+string);
    }
  }

}

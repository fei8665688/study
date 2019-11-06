package hash.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.apache.commons.lang3.StringUtils;

/**
 * @author phil.zhang
 * @date 2019/9/4
 */
public class HandleRequestKey {

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader("C:/张飞/request_key1.csv"));
    int limit = 0;
    int allow = 0;
    String line = null;

/*    while (StringUtils.isNotBlank(line = reader.readLine())) {
//      System.out.println((Long.valueOf(line) >> 20)+1500000000000L);
      long time = (Long.valueOf(line) >> 20)+1500000000000L;
      if ((time % 100) < 50) {
        limit += 1;
      }else {
        allow += 1;
      }


    }*/
    System.out.println("限制:"+ limit);
    System.out.println("允许:"+ allow);

    long time = (Long.valueOf(70940374285209143L) >> 20)+1500000000000L;
    System.out.println(time);

  }

}

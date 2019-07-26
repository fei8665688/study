package datetime;

import com.pkfare.collect.utils.DateTimeUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author phil.zhang
 * @date 2019/7/16
 */
public class DateTimeTest {

  public static void main(String[] args) throws ParseException {

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    Date parse = dateFormat.parse("2019-03-25 05:23");

    SimpleDateFormat h = new SimpleDateFormat("HH");

    System.out.println(h.format(parse));

  }

}

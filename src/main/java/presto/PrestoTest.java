package presto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author phil.zhang
 * @date 2019/9/26
 */
public class PrestoTest {


  public static void main(String[] args) throws ClassNotFoundException {
    Class.forName("com.facebook.presto.jdbc.PrestoDriver");
    String url = "jdbc:presto://prestodb.pkfare.io:80/hive/dm";
    // sql2
    String sql = "SELECT \"DM_AIR_API_CONVERSION_RATE_SPB_V2_D\".\"OD_CITY\" AS \"OD_CITY\",     SUM(\"DM_AIR_API_CONVERSION_RATE_SPB_V2_D\".\"SHOPPING_REQUEST_OFFLINE_COUNT\") AS \"TEMP_Calculation_1229482715953393686__1213883151__0_\",     SUM(\"DM_AIR_API_CONVERSION_RATE_SPB_V2_D\".\"SHOPPING_REQUEST_COUNT\") AS \"TEMP_Calculation_1229482715953393686__2255800558__0_\",     SUM(\"DM_AIR_API_CONVERSION_RATE_SPB_V2_D\".\"SHOPPING_REQUEST_LIMIT_COUNT\") AS \"TEMP_Calculation_1229482715953393686__2646176150__0_\",     SUM(\"DM_AIR_API_CONVERSION_RATE_SPB_V2_D\".\"SHOPPING_REQUEST_COUNT\") AS \"sum_SHOPPING_REQUEST_COUNT_ok\"   FROM \"DM\".\"DM_AIR_API_CONVERSION_RATE_SPB_V2_D\" \"DM_AIR_API_CONVERSION_RATE_SPB_V2_D\"     INNER JOIN (   SELECT SUM(\"DM_AIR_API_CONVERSION_RATE_SPB_V2_D\".\"SHOPPING_REQUEST_COUNT\") AS \"X__alias__0\",       \"DM_AIR_API_CONVERSION_RATE_SPB_V2_D\".\"OD_COUNTRY\" AS \"none_OD_COUNTRY_nk\"   FROM \"DM\".\"DM_AIR_API_CONVERSION_RATE_SPB_V2_D\" \"DM_AIR_API_CONVERSION_RATE_SPB_V2_D\"     WHERE ((\"DM_AIR_API_CONVERSION_RATE_SPB_V2_D\".\"DAY_NUM\" >= date '2019-08-23') AND (\"DM_AIR_API_CONVERSION_RATE_SPB_V2_D\".\"DAY_NUM\" <= date '2019-09-23'))   GROUP BY \"DM_AIR_API_CONVERSION_RATE_SPB_V2_D\".\"OD_COUNTRY\"   ORDER BY 1 DESC,     2 ASC   LIMIT 50  ) \"t0\"   ON (\"DM_AIR_API_CONVERSION_RATE_SPB_V2_D\".\"OD_COUNTRY\" = \"t0\".\"none_OD_COUNTRY_nk\")   WHERE ((\"DM_AIR_API_CONVERSION_RATE_SPB_V2_D\".\"DAY_NUM\" >= date '2019-08-23') AND (\"DM_AIR_API_CONVERSION_RATE_SPB_V2_D\".\"DAY_NUM\" <= date '2019-09-23')) GROUP BY \"DM_AIR_API_CONVERSION_RATE_SPB_V2_D\".\"OD_CITY\" ORDER BY 3 DESC,   1 ASC LIMIT 50";

    // sql3
//    String sql = "SELECT \"DM_AIR_SERVICE_CONVERSION_RATE_SPB_SUPPLIER_D\".\"OD_CITY\" AS \"OD_CITY\",   SUM(\"DM_AIR_SERVICE_CONVERSION_RATE_SPB_SUPPLIER_D\".\"BOOKING_COUNT\") AS \"TEMP_Calculation_230809513359749120__203689048__0_\",   SUM(\"DM_AIR_SERVICE_CONVERSION_RATE_SPB_SUPPLIER_D\".\"SHOPPING_COUNT\") AS \"TEMP_Calculation_230809513359749120__2609876908__0_\" FROM \"DM\".\"DM_AIR_SERVICE_CONVERSION_RATE_SPB_SUPPLIER_D\" \"DM_AIR_SERVICE_CONVERSION_RATE_SPB_SUPPLIER_D\" WHERE (( (\"DM_AIR_SERVICE_CONVERSION_RATE_SPB_SUPPLIER_D\".\"GDS\" NOT IN ('', '1N', '1T', 'Group', 'LCC', 'TC'))) AND ( (\"DM_AIR_SERVICE_CONVERSION_RATE_SPB_SUPPLIER_D\".\"SUPPLIER_CODE\" NOT IN ('', 'BRQ-KIWI-LCC', 'f'))) AND (((\"DM_AIR_SERVICE_CONVERSION_RATE_SPB_SUPPLIER_D\".\"SOURCE_TYPE\")) = 'API') AND (\"DM_AIR_SERVICE_CONVERSION_RATE_SPB_SUPPLIER_D\".\"DAY_NUM\" >= date '2019-09-11') AND (\"DM_AIR_SERVICE_CONVERSION_RATE_SPB_SUPPLIER_D\".\"DAY_NUM\" <= date '2019-09-19')) GROUP BY \"DM_AIR_SERVICE_CONVERSION_RATE_SPB_SUPPLIER_D\".\"OD_CITY\"";

    // sql1
//    String sql = "SELECT \"DM_AIR_API_CONVERSION_RATE_SPB_V2_D\".\"OD_CITY\" AS \"OD_CITY\",     SUM(\"DM_AIR_API_CONVERSION_RATE_SPB_V2_D\".\"SHOPPING_REQUEST_OFFLINE_COUNT\") AS \"TEMP_Calculation_1229482715953393686__1213883151__0_\",     SUM(\"DM_AIR_API_CONVERSION_RATE_SPB_V2_D\".\"SHOPPING_REQUEST_COUNT\") AS \"TEMP_Calculation_1229482715953393686__2255800558__0_\",     SUM(\"DM_AIR_API_CONVERSION_RATE_SPB_V2_D\".\"SHOPPING_REQUEST_LIMIT_COUNT\") AS \"TEMP_Calculation_1229482715953393686__2646176150__0_\",     SUM(\"DM_AIR_API_CONVERSION_RATE_SPB_V2_D\".\"SHOPPING_REQUEST_COUNT\") AS \"sum_SHOPPING_REQUEST_COUNT_ok\"   FROM \"DM\".\"DM_AIR_API_CONVERSION_RATE_SPB_V2_D\" \"DM_AIR_API_CONVERSION_RATE_SPB_V2_D\"     INNER JOIN (   SELECT SUM(\"DM_AIR_API_CONVERSION_RATE_SPB_V2_D\".\"SHOPPING_REQUEST_COUNT\") AS \"X__alias__0\",       \"DM_AIR_API_CONVERSION_RATE_SPB_V2_D\".\"OD_COUNTRY\" AS \"none_OD_COUNTRY_nk\"   FROM \"DM\".\"DM_AIR_API_CONVERSION_RATE_SPB_V2_D\" \"DM_AIR_API_CONVERSION_RATE_SPB_V2_D\"      GROUP BY \"DM_AIR_API_CONVERSION_RATE_SPB_V2_D\".\"OD_COUNTRY\"   ORDER BY 1 DESC,     2 ASC   LIMIT 50  ) \"t0\"   ON (\"DM_AIR_API_CONVERSION_RATE_SPB_V2_D\".\"OD_COUNTRY\" = \"t0\".\"none_OD_COUNTRY_nk\")   WHERE ((\"DM_AIR_API_CONVERSION_RATE_SPB_V2_D\".\"DAY_NUM\" >= date '2019-09-01') AND (\"DM_AIR_API_CONVERSION_RATE_SPB_V2_D\".\"DAY_NUM\" <= date '2019-09-23')) GROUP BY \"DM_AIR_API_CONVERSION_RATE_SPB_V2_D\".\"OD_CITY\" ORDER BY 3 DESC,   1 ASC LIMIT 50";
    ThreadPoolExecutor executorService = new ThreadPoolExecutor(128, 256, 0L, TimeUnit.MILLISECONDS,
        new LinkedBlockingQueue<>(64));

    for(int i = 0; i < 30; i++) {
      executorService.execute(() ->{
        try {
          Connection connection = getConnection(url);
          Statement statement = connection.createStatement();
          statement.executeQuery(sql);
          connection.close();
          System.out.println("zhixingwancheng ");

        } catch (SQLException e) {
          e.printStackTrace();
        }
      });
//      try {
//        Thread.sleep(1000);
//      } catch (InterruptedException e) {
//        e.printStackTrace();
//      }
    }



  }

  public static Connection getConnection(String url) throws SQLException {
    Connection connection = DriverManager.getConnection(url, "admin", null);
//    Connection connection = DriverManager.getConnection(url, properties);
    return connection;
  }

}

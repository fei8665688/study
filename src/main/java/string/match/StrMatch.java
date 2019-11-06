package string.match;

/**
 * @author phil.zhang
 * @date 2019/9/26
 */
public class StrMatch {

  public static void main(String[] args) {
    String sql = "SELECT \"DM_AIR_API_CONVERSION_RATE_SPB_V2_D\".\"OD_COUNTRY\" AS \"OD_COUNTRY\" FROM \"DM\".\"DM_AIR_API_CONVERSION_RATE_SPB_V2_D\" \"DM_AIR_API_CONVERSION_RATE_SPB_V2_D\" WHERE ((\"DM_AIR_API_CONVERSION_RATE_SPB_V2_D\".\"DAY_NUM\" >= {d '2019-06-28'}) AND (\"DM_AIR_API_CONVERSION_RATE_SPB_V2_D\".\"DAY_NUM\" <= {d '2019-09-16'}) AND (\"DM_AIR_API_CONVERSION_RATE_SPB_V2_D\".\"OD_COUNTRY\" IS NULL)) LIMIT 1";

    String lowerSql = sql.replaceAll("\"","").replaceAll("[\\(\\)]"," ")
        .replaceAll("\\s+"," ")
        .toLowerCase()
        .trim();

    if (lowerSql.endsWith("limit 1")) {
      String[] split = lowerSql.split("select|from");

      if (split.length == 3) {
        String[] keywords = split[1].split(" as ");
        if (keywords.length == 2) {
          String str =  keywords[0]+" is null";
          if (lowerSql.contains(str)) {
            System.out.println("就是你");
          }
        }
      }
    }



  }

}

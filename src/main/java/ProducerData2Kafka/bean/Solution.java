package ProducerData2Kafka.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author phil.zhang
 * @date 2019/7/2
 */
public class Solution implements ISaveEntity {

  /**
   * 用于区别数据属于哪次查询，与PKFARE API的shopping solution请求对应，值相同
   */
  @JSONField(name = "request_key")
  private String requestKey;

  /**
   * 请求时间，精确至毫秒；与API侧shopping solution的request_time值相同
   */
  @JSONField(name = "request_time")
  private String requestTime;

  /**
   * PKFARE API侧的请求时间，年月日
   */
  @JSONField(name = "day_num")
  private String dayNum;

  /**
   * PKFARE API侧的请求时间的分钟，分
   */
  @JSONField(name = "minute_num")
  private Integer minuteNum;

  /**
   * PKFARE API侧的请求时间的小时，时
   */
  @JSONField(name = "hour_num")
  private Integer hourNum;

  /**
   * PKFARE API侧的请求舱位等级，字符串记录
   */
  @JSONField(name = "cabin")
  private String cabin;

  /**
   * 向比价接口发起的请求时间，精确至毫秒
   */
  @JSONField(name = "request_time_compare")
  private String requestTimeCompare;

  /**
   * 从请求中获得。行程类型，数字记录：1-OW，2-RT，对应单程/往返
   */
  @JSONField(name = "flight_type")
  private String flightType;

  /**
   * 取去程的第一段的出发机场三字码，大写字母记录
   */
  @JSONField(name = "origin_airport")
  private String originAirport;

  /**
   * 取去程的最后一段的到达机场三字码，大写字母记录
   */
  @JSONField(name = "destination_airport")
  private String destinationAirport;

  /**
   * 取origin_airport对应的城市三字码
   */
  @JSONField(name = "origin_city")
  private String originCity;

  /**
   * 取destination_airport 对应的城市三字码
   */
  @JSONField(name = "destination_city")
  private String destinationCity;

  /**
   * 需转换，取origin_airport对应的国家二字码
   */
  @JSONField(name = "origin_country")
  private String originCountry;

  /**
   * 需转换，取destination_airport 对应的国家二字码
   */
  @JSONField(name = "destination_country")
  private String destinationCountry;

  /**
   * 出发日期
   */
  @JSONField(name = "outbound_date")
  private String outboundDate;

  /**
   * 返程日期
   */
  @JSONField(name = "inbound_date")
  private String inboundDate;

  /**
   * 取请求中的成人人数
   */
  @JSONField(name = "adt_count")
  private Integer adtCount;

  /**
   * 取请求中的儿童人数
   */
  @JSONField(name = "chd_count")
  private Integer chdCount;

  /**
   * buyer_country
   */
  @JSONField(name = "buyer_country")
  private String buyerCountry;

  /**
   * 采购GTP id，同程 buyer_id = 1019
   */
  @JSONField(name = "buyer_id")
  private Integer buyerId;

  /**
   * 采购Group id，同程group_id = 770
   */
  @JSONField(name = "group_id")
  private Integer groupId;

  /**
   * 低价扫线API返回的币种 .航班管家：CNY
   */
  @JSONField(name = "low_price_currency")
  private String lowPriceCurrency;

  /**
   * 即segmentType=1时，segmentIndex最大值 + segmentType=2时，segmentIndex最大值，即航段数量(每人）
   */
  @JSONField(name = "segment_count")
  private Integer segmentCount;

  /**
   * 成人单人采购票价，本接口为CNY
   */
  @JSONField(name = "buyer_adt_fare")
  private BigDecimal buyerAdtFare;

  /**
   * 成人单人税费，本接口为CNY
   */
  @JSONField(name = "buyer_adt_taxes")
  private BigDecimal buyerAdtTaxes;

  /**
   * 儿童单人采购票价，本接口为CNY
   */
  @JSONField(name = "buyer_chd_fare")
  private BigDecimal buyerChdFare;

  /**
   * 儿童单人采购票价，本接口为CNY
   */
  @JSONField(name = "buyer_chd_taxes")
  private BigDecimal buyerChdTaxes;

  /**
   * 最低价的成人单人总价，本接口为CNY
   */
  @JSONField(name = "buyer_adt_price")
  private BigDecimal buyerAdtPrice;

  /**
   * 最低价的儿童单人总价，本接口为CNY
   */
  @JSONField(name = "buyer_chd_price")
  private BigDecimal buyerChdPrice;

  /**
   * 去程第1个航段的航司二字码
   */
  @JSONField(name = "airline1")
  private String airline1;

  /**
   * 去程journey第2个航段的航司二字码
   */
  @JSONField(name = "airline2")
  private String airline2;

  /**
   * 去程journey第3个航段的航司二字码
   */
  @JSONField(name = "airline3")
  private String airline3;

  /**
   * 返程journey第1个航段的航司二字码
   */
  @JSONField(name = "airline4")
  private String airline4;

  /**
   * 返程journey第2个航段的航司二字码
   */
  @JSONField(name = "airline5")
  private String airline5;

  /**
   * 返程journey第3个航段的航司二字码
   */
  @JSONField(name = "airline6")
  private String airline6;

  /**
   * 第1个journey第1个航司的航班号；包含开头的航司二字码：例:AA123
   */
  @JSONField(name = "flight_number1")
  private String flightNumber1;

  /**
   * 第1个journey第2个航段的航班号
   */
  @JSONField(name = "flight_number2")
  private String flightNumber2;

  /**
   * 第1个journey第3个航段的航班号
   */
  @JSONField(name = "flight_number3")
  private String flightNumber3;

  /**
   * 第2个journey第1个航段的航班号
   */
  @JSONField(name = "flight_number4")
  private String flightNumber4;

  /**
   * 第2个journey第2个航段的航班号
   */
  @JSONField(name = "flight_number5")
  private String flightNumber5;

  /**
   * 第2个journey第3个航段的航班号
   */
  @JSONField(name = "flight_number6")
  private String flightNumber6;

  /**
   * 第1个journey第1个航段的舱位代码
   */
  @JSONField(name = "booking_code1")
  private String bookingCode1;

  /**
   * 第1个journey第2个航段的舱位代码
   */
  @JSONField(name = "booking_code2")
  private String bookingCode2;

  /**
   * 第1个journey第3个航段的舱位代码
   */
  @JSONField(name = "booking_code3")
  private String bookingCode3;

  /**
   * 第2个journey第1个航段的舱位代码
   */
  @JSONField(name = "booking_code4")
  private String bookingCode4;

  /**
   * 第2个journey第2个航段的舱位代码
   */
  @JSONField(name = "booking_code5")
  private String bookingCode5;

  /**
   * 第2个journey第3个航段的舱位代码
   */
  @JSONField(name = "booking_code6")
  private String bookingCode6;

  /**
   * 第1个journey第1个航段的 舱位等级，以数字记录；1-头等舱，2-商务舱，3-超级经济舱，4-经济舱
   */
  @JSONField(name = "cabin_class1")
  private String cabinClass1;

  /**
   * 第1个journey第2个航段的舱位等级
   */
  @JSONField(name = "cabin_class2")
  private String cabinClass2;

  /**
   * 第1个journey第3个航段的舱位等级
   */
  @JSONField(name = "cabin_class3")
  private String cabinClass3;

  /**
   * 第2个journey第1个航段的舱位等级
   */
  @JSONField(name = "cabin_class4")
  private String cabinClass4;

  /**
   * 第2个journey第2个航段的舱位等级
   */
  @JSONField(name = "cabin_class5")
  private String cabinClass5;

  /**
   * 第2个journey第3个航段的舱位等级
   */
  @JSONField(name = "cabin_class6")
  private String cabinClass6;

  /**
   * 第1个journey第1个航段的出发时间
   */
  @JSONField(name = "departure_time1")
  private String departureTime1;

  /**
   * 第1个journey第2个航段的出发时间
   */
  @JSONField(name = "departure_time2")
  private String departureTime2;

  /**
   * 第1个journey第3个航段的出发时间
   */
  @JSONField(name = "departure_time3")
  private String departureTime3;

  /**
   * 第2个journey第1个航段的出发时间
   */
  @JSONField(name = "departure_time4")
  private String departureTime4;

  /**
   * 第2个journey第2个航段的出发时间
   */
  @JSONField(name = "departure_time5")
  private String departureTime5;

  /**
   * 第2个journey第3个航段的出发时间
   */
  @JSONField(name = "departure_time6")
  private String departureTime6;

  /**
   * 第1个journey第1个航段的到达时间
   */
  @JSONField(name = "arrive_time1")
  private String arriveTime1;

  /**
   * 第1个journey第2个航段的到达时间
   */
  @JSONField(name = "arrive_time2")
  private String arriveTime2;

  /**
   * 第1个journey第3个航段的到达时间
   */
  @JSONField(name = "arrive_time3")
  private String arriveTime3;

  /**
   * 第2个journey第1个航段的到达时间
   */
  @JSONField(name = "arrive_time4")
  private String arriveTime4;

  /**
   * 第2个journey第2个航段的到达时间
   */
  @JSONField(name = "arrive_time5")
  private String arriveTime5;

  /**
   * 第2个journey第3个航段的到达时间
   */
  @JSONField(name = "arrive_time6")
  private String arriveTime6;

  /**
   * 第1个journey第1个航段是否是代码共享航班：1-是，0-不是
   */
  @JSONField(name = "codeshare1")
  private Integer codeshare1;

  /**
   * 第1个journey第2个航段是否是代码共享航班：1-是，0-不是
   */
  @JSONField(name = "codeshare2")
  private Integer codeshare2;

  /**
   * 第1个journey第3个航段是否是代码共享航班：1-是，0-不是
   */
  @JSONField(name = "codeshare3")
  private Integer codeshare3;

  /**
   * 第2个journey第1个航段是否是代码共享航班：1-是，0-不是
   */
  @JSONField(name = "codeshare4")
  private Integer codeshare4;

  /**
   * 第2个journey第2个航段是否是代码共享航班：1-是，0-不是
   */
  @JSONField(name = "codeshare5")
  private Integer codeshare5;

  /**
   * 第2个journey第3个航段是否是代码共享航班：1-是，0-不是
   */
  @JSONField(name = "codeshare6")
  private Integer codeshare6;

  /**
   * 第1个journey第1个航段的出发地机场三字码
   */
  @JSONField(name = "origin_airport1")
  private String originAirport1;

  /**
   * 第1个journey第2个航段的出发地机场三字码
   */
  @JSONField(name = "origin_airport2")
  private String originAirport2;

  /**
   * 第1个journey第3个航段的出发地机场三字码
   */
  @JSONField(name = "origin_airport3")
  private String originAirport3;

  /**
   * 第2个journey第1个航段的出发地机场三字码
   */
  @JSONField(name = "origin_airport4")
  private String originAirport4;

  /**
   * 第2个journey第2个航段的出发地机场三字码
   */
  @JSONField(name = "origin_airport5")
  private String originAirport5;

  /**
   * 第2个journey第3个航段的出发地机场三字码
   */
  @JSONField(name = "origin_airport6")
  private String originAirport6;

  /**
   * 第1个journey第1个航段的目的地机场三字码
   */
  @JSONField(name = "destination_airport1")
  private String destinationAirport1;

  /**
   * 第1个journey第2个航段的目的地机场三字码
   */
  @JSONField(name = "destination_airport2")
  private String destinationAirport2;

  /**
   * 第1个journey第3个航段的目的地机场三字码
   */
  @JSONField(name = "destination_airport3")
  private String destinationAirport3;

  /**
   * 第2个journey第1个航段的目的地机场三字码
   */
  @JSONField(name = "destination_airport4")
  private String destinationAirport4;

  /**
   * 第2个journey第2个航段的目的地机场三字码
   */
  @JSONField(name = "destination_airport5")
  private String destinationAirport5;

  /**
   * 第2个journey第3个航段的目的地机场三字码
   */
  @JSONField(name = "destination_airport6")
  private String destinationAirport6;

  /**
   * PKFARE价格对应的第1个journey第1个航段的舱位代码
   */
  @JSONField(name = "pkfare_booking_code1")
  private String pkfareBookingCode1;

  /**
   * PKFARE价格对应的第1个journey第2个航段的舱位代码
   */
  @JSONField(name = "pkfare_booking_code2")
  private String pkfareBookingCode2;

  /**
   * PKFARE价格对应的 第1个journey第3个航段的舱位代码
   */
  @JSONField(name = "pkfare_booking_code3")
  private String pkfareBookingCode3;

  /**
   * PKFARE价格对应的第2个journey第1个航段的舱位代码
   */
  @JSONField(name = "pkfare_booking_code4")
  private String pkfareBookingCode4;

  /**
   * PKFARE价格对应的第2个journey第2个航段的舱位代码
   */
  @JSONField(name = "pkfare_booking_code5")
  private String pkfareBookingCode5;

  /**
   * PKFARE价格对应的第2个journey第3个航段的舱位代码
   */
  @JSONField(name = "pkfare_booking_code6")
  private String pkfareBookingCode6;

  /**
   * PKFARE的成人单人采购票价，本接口为CNY
   */
  @JSONField(name = "pkfare_adt_fare")
  private BigDecimal pkfareAdtFare;

  /**
   * PKFARE的成人单人税费，本接口为CNY
   */
  @JSONField(name = "pkfare_adt_taxes")
  private BigDecimal pkfareAdtTaxes;

  /**
   * PKFARE的儿童单人采购票价，本接口为CNY
   */
  @JSONField(name = "pkfare_chd_fare")
  private BigDecimal pkfareChdFare;

  /**
   * PKFARE的儿童单人采购票价，本接口为CNY
   */
  @JSONField(name = "pkfare_chd_taxes")
  private BigDecimal pkfareChdTaxes;

  /**
   * PKFARE的最低价的成人单人总价，本接口为CNY
   */
  @JSONField(name = "pkfare_adt_price")
  private BigDecimal pkfareAdtPrice;

  /**
   * PKFARE的最低价的儿童单人总价，本接口为CNY
   */
  @JSONField(name = "pkfare_chd_price")
  private BigDecimal pkfareChdPrice;

  /**
   * PKFARE是不是最低价。1- 是 , 0 - 否。  判断逻辑：接口返回结果里的“price”. “saleTotalPrice” = “merchantPrice”."saleTotalPrice"
   */
  @JSONField(name = "is_pkfare_cheapest")
  private Integer isPKFARECheapest;

  /**
   * PKFARE 是不是最低价。 1- 是 ， 0 - 否。 由Qunar接口返回
   */
  @JSONField(name = "qunar_ownerislowest")
  private Integer qunarOwnerIsLowest;

  public String getRequestKey() {
    return requestKey;
  }

  public void setRequestKey(String requestKey) {
    this.requestKey = requestKey;
  }

  public String getRequestTime() {
    return requestTime;
  }

  public void setRequestTime(String requestTime) {
    this.requestTime = requestTime;
  }

  public String getDayNum() {
    return dayNum;
  }

  public void setDayNum(String dayNum) {
    this.dayNum = dayNum;
  }

  public Integer getMinuteNum() {
    return minuteNum;
  }

  public void setMinuteNum(Integer minuteNum) {
    this.minuteNum = minuteNum;
  }

  public Integer getHourNum() {
    return hourNum;
  }

  public void setHourNum(Integer hourNum) {
    this.hourNum = hourNum;
  }

  public String getCabin() {
    return cabin;
  }

  public void setCabin(String cabin) {
    this.cabin = cabin;
  }

  public String getRequestTimeCompare() {
    return requestTimeCompare;
  }

  public void setRequestTimeCompare(String requestTimeCompare) {
    this.requestTimeCompare = requestTimeCompare;
  }

  public String getFlightType() {
    return flightType;
  }

  public void setFlightType(String flightType) {
    this.flightType = flightType;
  }

  public String getOriginAirport() {
    return originAirport;
  }

  public void setOriginAirport(String originAirport) {
    this.originAirport = originAirport;
  }

  public String getDestinationAirport() {
    return destinationAirport;
  }

  public void setDestinationAirport(String destinationAirport) {
    this.destinationAirport = destinationAirport;
  }

  public String getOriginCity() {
    return originCity;
  }

  public void setOriginCity(String originCity) {
    this.originCity = originCity;
  }

  public String getDestinationCity() {
    return destinationCity;
  }

  public void setDestinationCity(String destinationCity) {
    this.destinationCity = destinationCity;
  }

  public String getOriginCountry() {
    return originCountry;
  }

  public void setOriginCountry(String originCountry) {
    this.originCountry = originCountry;
  }

  public String getDestinationCountry() {
    return destinationCountry;
  }

  public void setDestinationCountry(String destinationCountry) {
    this.destinationCountry = destinationCountry;
  }

  public String getOutboundDate() {
    return outboundDate;
  }

  public void setOutboundDate(String outboundDate) {
    this.outboundDate = outboundDate;
  }

  public String getInboundDate() {
    return inboundDate;
  }

  public void setInboundDate(String inboundDate) {
    this.inboundDate = inboundDate;
  }

  public Integer getAdtCount() {
    return adtCount;
  }

  public void setAdtCount(Integer adtCount) {
    this.adtCount = adtCount;
  }

  public Integer getChdCount() {
    return chdCount;
  }

  public void setChdCount(Integer chdCount) {
    this.chdCount = chdCount;
  }

  public String getBuyerCountry() {
    return buyerCountry;
  }

  public void setBuyerCountry(String buyerCountry) {
    this.buyerCountry = buyerCountry;
  }

  public Integer getBuyerId() {
    return buyerId;
  }

  public void setBuyerId(Integer buyerId) {
    this.buyerId = buyerId;
  }

  public Integer getGroupId() {
    return groupId;
  }

  public void setGroupId(Integer groupId) {
    this.groupId = groupId;
  }

  public String getLowPriceCurrency() {
    return lowPriceCurrency;
  }

  public void setLowPriceCurrency(String lowPriceCurrency) {
    this.lowPriceCurrency = lowPriceCurrency;
  }

  public Integer getSegmentCount() {
    return segmentCount;
  }

  public void setSegmentCount(Integer segmentCount) {
    this.segmentCount = segmentCount;
  }

  public BigDecimal getBuyerAdtFare() {
    return buyerAdtFare;
  }

  public void setBuyerAdtFare(BigDecimal buyerAdtFare) {
    this.buyerAdtFare = buyerAdtFare;
  }

  public BigDecimal getBuyerAdtTaxes() {
    return buyerAdtTaxes;
  }

  public void setBuyerAdtTaxes(BigDecimal buyerAdtTaxes) {
    this.buyerAdtTaxes = buyerAdtTaxes;
  }

  public BigDecimal getBuyerChdFare() {
    return buyerChdFare;
  }

  public void setBuyerChdFare(BigDecimal buyerChdFare) {
    this.buyerChdFare = buyerChdFare;
  }

  public BigDecimal getBuyerChdTaxes() {
    return buyerChdTaxes;
  }

  public void setBuyerChdTaxes(BigDecimal buyerChdTaxes) {
    this.buyerChdTaxes = buyerChdTaxes;
  }

  public BigDecimal getBuyerAdtPrice() {
    return buyerAdtPrice;
  }

  public void setBuyerAdtPrice(BigDecimal buyerAdtPrice) {
    this.buyerAdtPrice = buyerAdtPrice;
  }

  public BigDecimal getBuyerChdPrice() {
    return buyerChdPrice;
  }

  public void setBuyerChdPrice(BigDecimal buyerChdPrice) {
    this.buyerChdPrice = buyerChdPrice;
  }

  public String getAirline1() {
    return airline1;
  }

  public void setAirline1(String airline1) {
    this.airline1 = airline1;
  }

  public String getAirline2() {
    return airline2;
  }

  public void setAirline2(String airline2) {
    this.airline2 = airline2;
  }

  public String getAirline3() {
    return airline3;
  }

  public void setAirline3(String airline3) {
    this.airline3 = airline3;
  }

  public String getAirline4() {
    return airline4;
  }

  public void setAirline4(String airline4) {
    this.airline4 = airline4;
  }

  public String getAirline5() {
    return airline5;
  }

  public void setAirline5(String airline5) {
    this.airline5 = airline5;
  }

  public String getAirline6() {
    return airline6;
  }

  public void setAirline6(String airline6) {
    this.airline6 = airline6;
  }

  public String getFlightNumber1() {
    return flightNumber1;
  }

  public void setFlightNumber1(String flightNumber1) {
    this.flightNumber1 = flightNumber1;
  }

  public String getFlightNumber2() {
    return flightNumber2;
  }

  public void setFlightNumber2(String flightNumber2) {
    this.flightNumber2 = flightNumber2;
  }

  public String getFlightNumber3() {
    return flightNumber3;
  }

  public void setFlightNumber3(String flightNumber3) {
    this.flightNumber3 = flightNumber3;
  }

  public String getFlightNumber4() {
    return flightNumber4;
  }

  public void setFlightNumber4(String flightNumber4) {
    this.flightNumber4 = flightNumber4;
  }

  public String getFlightNumber5() {
    return flightNumber5;
  }

  public void setFlightNumber5(String flightNumber5) {
    this.flightNumber5 = flightNumber5;
  }

  public String getFlightNumber6() {
    return flightNumber6;
  }

  public void setFlightNumber6(String flightNumber6) {
    this.flightNumber6 = flightNumber6;
  }

  public String getBookingCode1() {
    return bookingCode1;
  }

  public void setBookingCode1(String bookingCode1) {
    this.bookingCode1 = bookingCode1;
  }

  public String getBookingCode2() {
    return bookingCode2;
  }

  public void setBookingCode2(String bookingCode2) {
    this.bookingCode2 = bookingCode2;
  }

  public String getBookingCode3() {
    return bookingCode3;
  }

  public void setBookingCode3(String bookingCode3) {
    this.bookingCode3 = bookingCode3;
  }

  public String getBookingCode4() {
    return bookingCode4;
  }

  public void setBookingCode4(String bookingCode4) {
    this.bookingCode4 = bookingCode4;
  }

  public String getBookingCode5() {
    return bookingCode5;
  }

  public void setBookingCode5(String bookingCode5) {
    this.bookingCode5 = bookingCode5;
  }

  public String getBookingCode6() {
    return bookingCode6;
  }

  public void setBookingCode6(String bookingCode6) {
    this.bookingCode6 = bookingCode6;
  }

  public String getCabinClass1() {
    return cabinClass1;
  }

  public void setCabinClass1(String cabinClass1) {
    this.cabinClass1 = cabinClass1;
  }

  public String getCabinClass2() {
    return cabinClass2;
  }

  public void setCabinClass2(String cabinClass2) {
    this.cabinClass2 = cabinClass2;
  }

  public String getCabinClass3() {
    return cabinClass3;
  }

  public void setCabinClass3(String cabinClass3) {
    this.cabinClass3 = cabinClass3;
  }

  public String getCabinClass4() {
    return cabinClass4;
  }

  public void setCabinClass4(String cabinClass4) {
    this.cabinClass4 = cabinClass4;
  }

  public String getCabinClass5() {
    return cabinClass5;
  }

  public void setCabinClass5(String cabinClass5) {
    this.cabinClass5 = cabinClass5;
  }

  public String getCabinClass6() {
    return cabinClass6;
  }

  public void setCabinClass6(String cabinClass6) {
    this.cabinClass6 = cabinClass6;
  }

  public String getDepartureTime1() {
    return departureTime1;
  }

  public void setDepartureTime1(String departureTime1) {
    this.departureTime1 = departureTime1;
  }

  public String getDepartureTime2() {
    return departureTime2;
  }

  public void setDepartureTime2(String departureTime2) {
    this.departureTime2 = departureTime2;
  }

  public String getDepartureTime3() {
    return departureTime3;
  }

  public void setDepartureTime3(String departureTime3) {
    this.departureTime3 = departureTime3;
  }

  public String getDepartureTime4() {
    return departureTime4;
  }

  public void setDepartureTime4(String departureTime4) {
    this.departureTime4 = departureTime4;
  }

  public String getDepartureTime5() {
    return departureTime5;
  }

  public void setDepartureTime5(String departureTime5) {
    this.departureTime5 = departureTime5;
  }

  public String getDepartureTime6() {
    return departureTime6;
  }

  public void setDepartureTime6(String departureTime6) {
    this.departureTime6 = departureTime6;
  }

  public String getArriveTime1() {
    return arriveTime1;
  }

  public void setArriveTime1(String arriveTime1) {
    this.arriveTime1 = arriveTime1;
  }

  public String getArriveTime2() {
    return arriveTime2;
  }

  public void setArriveTime2(String arriveTime2) {
    this.arriveTime2 = arriveTime2;
  }

  public String getArriveTime3() {
    return arriveTime3;
  }

  public void setArriveTime3(String arriveTime3) {
    this.arriveTime3 = arriveTime3;
  }

  public String getArriveTime4() {
    return arriveTime4;
  }

  public void setArriveTime4(String arriveTime4) {
    this.arriveTime4 = arriveTime4;
  }

  public String getArriveTime5() {
    return arriveTime5;
  }

  public void setArriveTime5(String arriveTime5) {
    this.arriveTime5 = arriveTime5;
  }

  public String getArriveTime6() {
    return arriveTime6;
  }

  public void setArriveTime6(String arriveTime6) {
    this.arriveTime6 = arriveTime6;
  }

  public Integer getCodeshare1() {
    return codeshare1;
  }

  public void setCodeshare1(Integer codeshare1) {
    this.codeshare1 = codeshare1;
  }

  public Integer getCodeshare2() {
    return codeshare2;
  }

  public void setCodeshare2(Integer codeshare2) {
    this.codeshare2 = codeshare2;
  }

  public Integer getCodeshare3() {
    return codeshare3;
  }

  public void setCodeshare3(Integer codeshare3) {
    this.codeshare3 = codeshare3;
  }

  public Integer getCodeshare4() {
    return codeshare4;
  }

  public void setCodeshare4(Integer codeshare4) {
    this.codeshare4 = codeshare4;
  }

  public Integer getCodeshare5() {
    return codeshare5;
  }

  public void setCodeshare5(Integer codeshare5) {
    this.codeshare5 = codeshare5;
  }

  public Integer getCodeshare6() {
    return codeshare6;
  }

  public void setCodeshare6(Integer codeshare6) {
    this.codeshare6 = codeshare6;
  }

  public String getOriginAirport1() {
    return originAirport1;
  }

  public void setOriginAirport1(String originAirport1) {
    this.originAirport1 = originAirport1;
  }

  public String getOriginAirport2() {
    return originAirport2;
  }

  public void setOriginAirport2(String originAirport2) {
    this.originAirport2 = originAirport2;
  }

  public String getOriginAirport3() {
    return originAirport3;
  }

  public void setOriginAirport3(String originAirport3) {
    this.originAirport3 = originAirport3;
  }

  public String getOriginAirport4() {
    return originAirport4;
  }

  public void setOriginAirport4(String originAirport4) {
    this.originAirport4 = originAirport4;
  }

  public String getOriginAirport5() {
    return originAirport5;
  }

  public void setOriginAirport5(String originAirport5) {
    this.originAirport5 = originAirport5;
  }

  public String getOriginAirport6() {
    return originAirport6;
  }

  public void setOriginAirport6(String originAirport6) {
    this.originAirport6 = originAirport6;
  }

  public String getDestinationAirport1() {
    return destinationAirport1;
  }

  public void setDestinationAirport1(String destinationAirport1) {
    this.destinationAirport1 = destinationAirport1;
  }

  public String getDestinationAirport2() {
    return destinationAirport2;
  }

  public void setDestinationAirport2(String destinationAirport2) {
    this.destinationAirport2 = destinationAirport2;
  }

  public String getDestinationAirport3() {
    return destinationAirport3;
  }

  public void setDestinationAirport3(String destinationAirport3) {
    this.destinationAirport3 = destinationAirport3;
  }

  public String getDestinationAirport4() {
    return destinationAirport4;
  }

  public void setDestinationAirport4(String destinationAirport4) {
    this.destinationAirport4 = destinationAirport4;
  }

  public String getDestinationAirport5() {
    return destinationAirport5;
  }

  public void setDestinationAirport5(String destinationAirport5) {
    this.destinationAirport5 = destinationAirport5;
  }

  public String getDestinationAirport6() {
    return destinationAirport6;
  }

  public void setDestinationAirport6(String destinationAirport6) {
    this.destinationAirport6 = destinationAirport6;
  }

  public String getPkfareBookingCode1() {
    return pkfareBookingCode1;
  }

  public void setPkfareBookingCode1(String pkfareBookingCode1) {
    this.pkfareBookingCode1 = pkfareBookingCode1;
  }

  public String getPkfareBookingCode2() {
    return pkfareBookingCode2;
  }

  public void setPkfareBookingCode2(String pkfareBookingCode2) {
    this.pkfareBookingCode2 = pkfareBookingCode2;
  }

  public String getPkfareBookingCode3() {
    return pkfareBookingCode3;
  }

  public void setPkfareBookingCode3(String pkfareBookingCode3) {
    this.pkfareBookingCode3 = pkfareBookingCode3;
  }

  public String getPkfareBookingCode4() {
    return pkfareBookingCode4;
  }

  public void setPkfareBookingCode4(String pkfareBookingCode4) {
    this.pkfareBookingCode4 = pkfareBookingCode4;
  }

  public String getPkfareBookingCode5() {
    return pkfareBookingCode5;
  }

  public void setPkfareBookingCode5(String pkfareBookingCode5) {
    this.pkfareBookingCode5 = pkfareBookingCode5;
  }

  public String getPkfareBookingCode6() {
    return pkfareBookingCode6;
  }

  public void setPkfareBookingCode6(String pkfareBookingCode6) {
    this.pkfareBookingCode6 = pkfareBookingCode6;
  }

  public BigDecimal getPkfareAdtFare() {
    return pkfareAdtFare;
  }

  public void setPkfareAdtFare(BigDecimal pkfareAdtFare) {
    this.pkfareAdtFare = pkfareAdtFare;
  }

  public BigDecimal getPkfareAdtTaxes() {
    return pkfareAdtTaxes;
  }

  public void setPkfareAdtTaxes(BigDecimal pkfareAdtTaxes) {
    this.pkfareAdtTaxes = pkfareAdtTaxes;
  }

  public BigDecimal getPkfareChdFare() {
    return pkfareChdFare;
  }

  public void setPkfareChdFare(BigDecimal pkfareChdFare) {
    this.pkfareChdFare = pkfareChdFare;
  }

  public BigDecimal getPkfareChdTaxes() {
    return pkfareChdTaxes;
  }

  public void setPkfareChdTaxes(BigDecimal pkfareChdTaxes) {
    this.pkfareChdTaxes = pkfareChdTaxes;
  }

  public BigDecimal getPkfareAdtPrice() {
    return pkfareAdtPrice;
  }

  public void setPkfareAdtPrice(BigDecimal pkfareAdtPrice) {
    this.pkfareAdtPrice = pkfareAdtPrice;
  }

  public BigDecimal getPkfareChdPrice() {
    return pkfareChdPrice;
  }

  public void setPkfareChdPrice(BigDecimal pkfareChdPrice) {
    this.pkfareChdPrice = pkfareChdPrice;
  }

  public Integer getIsPKFARECheapest() {
    return isPKFARECheapest;
  }

  public void setIsPKFARECheapest(Integer isPKFARECheapest) {
    this.isPKFARECheapest = isPKFARECheapest;
  }

  public Integer getQunarOwnerIsLowest() {
    return qunarOwnerIsLowest;
  }

  public void setQunarOwnerIsLowest(Integer qunarOwnerIsLowest) {
    this.qunarOwnerIsLowest = qunarOwnerIsLowest;
  }

  @Override
  public String toString(){
    return JSON.toJSONString(this);
  }

  @Override
  public String getTimeStamp() {
    return requestTime;
  }
}

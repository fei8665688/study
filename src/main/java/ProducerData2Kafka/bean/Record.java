package ProducerData2Kafka.bean;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import java.io.Serializable;

/**
 * @author phil.zhang
 * @date 2019/7/2
 */
public class Record implements ISaveEntity {

  /**
   * 根据origin记录对应的城市三字码
   */
  @JSONField(name = "origin_city")
  private String originCity;

  /**
   * 根据destination记录对应的城市三字码
   */
  @JSONField(name = "destination_city")
  private String destinationCity;

  /**
   * 行程类型，OW、RT、OJ、MT，对应单程/往返/缺口/多程，超出OW、RT、OJ的均统计为MT
   */
  @JSONField(name = "flight_type")
  private String flightType;

  /**
   * 舱位等级
   */
  @JSONField(name = "cabin")
  private String cabin;

  /**
   * 指定航司查询的二字码
   */
  @JSONField(name = "airline")
  private String airline;

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
   * 成人人数
   */
  @JSONField(name = "adt_count")
  private Integer adtCount;

  /**
   * 儿童人数
   */
  @JSONField(name = "chd_count")
  private Integer chdCount;

  /**
   * 同程的产品类型。1-TC同程特惠；2-BUSINESS商务优选；3-ALL全部
   */
  @JSONField(name = "product_type")
  private Integer productType;

  /**
   * 返回代码 1-成功；0-失败
   */
  @JSONField(name = "result_code")
  private Integer resultCode;

  /**
   * 查询失败时返回定义的错误码。成功为空字符串
   */
  @JSONField(name = "status")
  private String status;

  /**
   * 第三方接口的请求耗时
   */
  @JSONField(name = "duration")
  private long duration;

  /**
   * 向比价接口发出查询的请求时间，年月日，时分秒毫秒。格式：YYYY-MM-DD hh-mm-ss-mss
   */
  @JSONField(name = "request_time_compare")
  private String requestTimeCompare;

  /**
   * 用于区别数据属于哪次查询，与查询结果对应
   */
  @JSONField(name = "request_key")
  private String requestKey;

  /**
   * 外部渠道请求我们API的时间（API记的请求时间），精确到毫秒
   */
  @JSONField(name = "request_time")
  private String requestTime;

  /**
   * 采购GTP
   */
  @JSONField(name = "buyer_id")
  private Integer buyerId;

  /**
   * 采购Group ID
   */
  @JSONField(name = "group_id")
  private Integer groupId;

  /**
   * 取原始的api shopping请求，出发地三字码，可能混有机场和城市
   */
  @JSONField(name = "origin")
  private String origin;

  /**
   * 取原始的api shopping请求，目的地三字码，可能混有机场和城市
   */
  @JSONField(name = "destination")
  private String destination;

  /**
   * 取原始的api shopping请求，国家二字码
   */
  @JSONField(name = "origin_country")
  private String originCountry;

  /**
   * 取原始的api shopping请求，国家二字码
   */
  @JSONField(name = "destination_country")
  private String destinationCountry;

  /**
   * 请求时间：分
   */
  @JSONField(name = "minute_num")
  private Integer minuteNum;

  /**
   * 请求时间：时
   */
  @JSONField(name = "hour_num")
  private Integer hourNum;

  /**
   * 请求时间：年月日
   */
  @JSONField(name = "day_num")
  private String dayNum;

  @JSONField(name = "origin_airport")
  private String originAirport;

  @JSONField(name = "destination_airport")
  private String destinationAirport;

  @JSONField(serialize = false, deserialize = false)
  private String errorMessage;

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
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

  public String getFlightType() {
    return flightType;
  }

  public void setFlightType(String flightType) {
    this.flightType = flightType;
  }

  public String getCabin() {
    return cabin;
  }

  public void setCabin(String cabin) {
    this.cabin = cabin;
  }

  public String getAirline() {
    return airline;
  }

  public void setAirline(String airline) {
    this.airline = airline;
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

  public Integer getProductType() {
    return productType;
  }

  public void setProductType(Integer productType) {
    this.productType = productType;
  }

  public Integer getResultCode() {
    return resultCode;
  }

  public void setResultCode(Integer resultCode) {
    this.resultCode = resultCode;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public long getDuration() {
    return duration;
  }

  public void setDuration(long duration) {
    this.duration = duration;
  }

  public String getRequestTimeCompare() {
    return requestTimeCompare;
  }

  public void setRequestTimeCompare(String requestTimeCompare) {
    this.requestTimeCompare = requestTimeCompare;
  }

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

  public String getOrigin() {
    return origin;
  }

  public void setOrigin(String origin) {
    this.origin = origin;
  }

  public String getDestination() {
    return destination;
  }

  public void setDestination(String destination) {
    this.destination = destination;
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

  public String getDayNum() {
    return dayNum;
  }

  public void setDayNum(String dayNum) {
    this.dayNum = dayNum;
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

  @Override
  public String toString(){
    return JSON.toJSONString(this);
  }

  @Override
  public String getTimeStamp() {
    return requestTime;
  }

}

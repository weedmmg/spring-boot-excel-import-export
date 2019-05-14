package com.home.base.util;


import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author chenxf
 * @date 3/16/2018
 * @description
 */
public class DateUtil {

  /**
   * 获取当前年月
   * @return 201803
   */
   public static String getYearMonth(){
     LocalDate today = LocalDate.now();
     int year = today.getYear();
     int month = today.getMonthValue();
     int day = today.getDayOfMonth();
     return String.format("%d-%02d", year, month);
   }

  /**
   * 获取 年月日期的第1天
   * @param yearMonth 年月
   * @param num 0当月 -1 上月 1下月
   * @return
   */
   public static Timestamp getFirstDay(String yearMonth,int num) throws Exception{
     int year=Integer.valueOf(yearMonth.substring(0,4));
     int month= Integer.valueOf(yearMonth.substring(4,6));
     LocalDateTime ldt1 = LocalDateTime.of(year,month, 1, 0, 0, 0);
     ldt1=ldt1.plusMonths(num);
     return Timestamp.valueOf(ldt1);
   }
    public static LocalDate getLocalDateByDate(Date date){
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        return localDateTime.toLocalDate();
    }

   public static Date getByLocalDate(LocalDate localDate){
     ZoneId zoneId = ZoneId.systemDefault();
     ZonedDateTime zdt = localDate.atStartOfDay(zoneId);
     return Date.from(zdt.toInstant());
   }

    public static Date getByLocalDateTime(LocalDateTime localDateTime){
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        return Date.from(zdt.toInstant());
    }
   public  static Date getDate(String dateStr){
     ZoneId zoneId = ZoneId.systemDefault();
     ZonedDateTime zdt = getLocalDate(dateStr).atStartOfDay(zoneId);
     return Date.from(zdt.toInstant());
   }

   public static LocalDate getLocalDate(String dateStr){
     String[] datas=dateStr.split("-");
     return  LocalDate.of(Integer.parseInt(datas[0]),Integer.parseInt(datas[1]),Integer.parseInt(datas[2]));
   }

  public static boolean localDateBeforeNow(String dateStr){
    return  LocalDate.now().isAfter(getLocalDate(dateStr));
  }

  public static Date getEDate(String dateStr){
    LocalDateTime localDateTime = LocalDateTime.parse(dateStr,DateTimeFormatter.ofPattern("MMM-dd-yyyy hh:mm:ss a", Locale.ENGLISH));
    ZoneId zoneId = ZoneId.of("UTC");
    return Date.from( localDateTime.atZone(zoneId).toInstant());
  }

  public static String getShortTime(Date date) {
    String shortString = null;
    long now = Calendar.getInstance().getTimeInMillis();
    if (date == null) {
      return shortString;
    }
    long delTime = (now - date.getTime()) / 1000;
    if (delTime > 365 * 24 * 60 * 60) {
      shortString = (int) (delTime / (365 * 24 * 60 * 60)) + "年前";
    } else if (delTime > 24 * 60 * 60) {
      shortString = (int) (delTime / (24 * 60 * 60)) + "天前";
    } else if (delTime > 60 * 60) {
      shortString = (int) (delTime / (60 * 60)) + "小时前";
    } else if (delTime > 60) {
      shortString = (int) (delTime / (60)) + "分前";
    } else if (delTime > 1) {
      shortString = delTime + "秒前";
    } else {
      shortString = "1秒前";
    }
    return shortString;
  }

  public static String formatDate(final Date date, final String format) {

    if (date == null){
        return "";
    }
    final SimpleDateFormat dateFormat = new SimpleDateFormat(format);
    return dateFormat.format(date);
  }

    public static void main(String[] args){



   String dateStr="Jul-26-2018 07:14:52 AM";
     LocalDateTime dateTime1 = LocalDateTime.now();
    DateTimeFormatter df=DateTimeFormatter.ofPattern("MMM-dd-yyyy hh:mm:ss a", Locale.ENGLISH);
     LocalDateTime dateTime2 = LocalDateTime.parse(dateStr,DateTimeFormatter.ofPattern("MMM-dd-yyyy hh:mm:ss a", Locale.ENGLISH));
     System.out.println(df.format(dateTime1));
     System.out.println(getShortTime(getEDate(dateStr)));
     int i=new BigDecimal(12).compareTo(  new BigDecimal(12));
     System.out.println(i==0);
     System.out.println(DateUtil.getDate(LocalDate.now().plusDays(-6).toString()));
     System.out.println(localDateBeforeNow("2018-09-18"));
   }

}

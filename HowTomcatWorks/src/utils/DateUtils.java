package com.atguigu.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author rd_kai_cao
 * @Description
 * @Date 2020/11/4 17:00
 */
public class DateUtils {

    /**
     * 获取两个时间点的月份差
     *
     * @param dt1 第一个时间点
     * @param dt2 第二个时间点
     * @return int，即需求的月数差
     */
    public static int monthDiff(LocalDateTime dt1, LocalDateTime dt2) {
        //获取第一个时间点的月份
        int month1 = dt1.getMonthValue();
        //获取第一个时间点的年份
        int year1 = dt1.getYear();
        //获取第一个时间点的月份
        int month2 = dt2.getMonthValue();
        //获取第一个时间点的年份
        int year2 = dt2.getYear();
        //返回两个时间点的月数差
        return (year2 - year1) * 12 + (month2 - month1);
    }


    /**
     * localdate格式化
     *
     * @param time
     * @return
     */
    public static String localDateToFomatterTime(LocalDate time) {

        DateTimeFormatter formatter = getDateFormatter();

        String format = formatter.format(time);

        return format;
    }

    /**
     * localdatetime格式化
     *
     * @param time
     * @return
     */
    public static String localDateTimeToFomatterTime(LocalDateTime time) {

        DateTimeFormatter formatter = getDateTimeFormatter();

        String format = formatter.format(time);

        return format;
    }

    /**
     * String转localDateTime
     *
     * @param time
     * @return
     */
    public static LocalDate stringToLocalDate(String time) {

        DateTimeFormatter df = getDateFormatter();

        LocalDate localDateTime = LocalDate.parse(time, df);

        return localDateTime;
    }

    /**
     * 获取时间格式化
     *
     * @return
     */
    public static DateTimeFormatter getDateFormatter() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd");
    }

    /**
     * String转localDateTime
     *
     * @param time
     * @return
     */
    public static LocalDateTime stringToLocalDateTime(String time) {

        DateTimeFormatter df = getDateTimeFormatter();

        LocalDateTime localDateTime = LocalDateTime.parse(time, df);

        return localDateTime;
    }

    /**
     * 获取时间格式化
     *
     * @return
     */
    public static DateTimeFormatter getDateTimeFormatter() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
    }

    /**
     * localDateTime转Date
     *
     * @param localDate
     * @return
     */
    public static Date localDateToDate(LocalDate localDate) {
        if (localDate == null) {
            return null;
        }
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    /**
     * localDateTime转Date
     *
     * @param localDateTime
     * @return
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * Date转localDateTime
     *
     * @param date
     * @return
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        if (date == null) {
            return null;
        }
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * Date转LocalDate
     *
     * @param date
     */
    public static LocalDate dateToLocalDate(Date date) {
        if (date == null) {
            return null;
        }
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}

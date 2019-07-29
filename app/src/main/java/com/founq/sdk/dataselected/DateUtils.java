package com.founq.sdk.dataselected;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期时间工具类
 */
public class DateUtils {

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    /**
     * 格式化时间戳
     *
     * @param time 秒时间戳
     * @return
     */
    public static String getDateFormat(long time) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time * 1000);

        return dateFormat.format(calendar.getTime());
    }

    /**
     * 格式化时间戳2
     *
     * @param time 秒时间戳
     * @return
     */
    public static String getDateFormat2(long time) {

        Calendar calendar = Calendar.getInstance();

        return dateFormat.format(calendar.getTime());
    }


    /**
     * 根据日期获得星期
     *
     * @param date
     * @return
     */
    public static int getWeekOfDate(Date date) {
        String[] weekDaysName = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        return intWeek;
    }

    /**
     * 获得某个月的天数
     * @param year
     * @param month
     * @return
     */
    public static int getThisMonthMaxDay(int year, int month) {

        //获取当前时间
        Calendar cal = Calendar.getInstance();
        //下面可以设置月份，注：月份设置要减1，所以设置1月就是1-1，设置2月就是2-1，如此类推
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DATE, 1);
        cal.roll(Calendar.DATE, -1);
        //得到一个月最最后一天日期(31/30/29/28)
//        int MaxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        int MaxDay = cal.get(Calendar.DATE);

        return MaxDay;
    }

    /**
     * 获得某个月第一天是星期几
     * @param year
     * @param month
     * @return 1为星期天
     */
    public static int getWeekOfMonthBegin(int year, int month) {

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        int weekOfMonthBegin = cal.get(Calendar.DAY_OF_WEEK);

        //若一周第一天为星期一，则+1
        if (cal.getFirstDayOfWeek() != Calendar.SUNDAY) {
            weekOfMonthBegin = weekOfMonthBegin + 1;
            if (weekOfMonthBegin == 8) {
                weekOfMonthBegin = 1;
            }
        }

        return weekOfMonthBegin;
    }

    /**
     * 获得月份 从1开始
     *
     * @return
     */
    public static int getMonth() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.MONTH) + 1;
    }

    /**
     * 获得星期
     *
     * @param date
     * @return
     */
    public static Date getWeek(Date date, int value) {

        if (getWeekOfDate(date) == 0) {
            date = DateAdd(date, -7);
        }

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);

        calendar.set(Calendar.DAY_OF_WEEK, value);

        return calendar.getTime();

    }

    /**
     * 给日期加上天数
     *
     * @param date
     * @param day
     * @return
     */
    public static Date DateAdd(Date date, int day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, day);
        return cal.getTime();
    }

    /**
     * 获得周一的日期
     *
     * @param date
     * @return
     */
    public static Date getMonday(Date date) {

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);

        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

        return calendar.getTime();

    }

    /**
     * 获得周三的日期
     *
     * @param date
     * @return
     */
    public static String getWednesday(Date date) {

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);

        calendar.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);

        return dateFormat.format(calendar.getTime());

    }

    /**
     * 获得周五的日期
     *
     * @param date
     * @return
     */
    public static String getFriday(Date date) {

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);

        calendar.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);

        return dateFormat.format(calendar.getTime());
    }

    /**
     * 当前日期前几天或者后几天的日期
     *
     * @param n
     * @return
     */
    public static String afterNDay(int n) {

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(new Date());

        calendar.add(Calendar.DATE, n);

        Date date = calendar.getTime();

        String s = dateFormat.format(date);

        return s;

    }

    /**
     * 判断两个日期是否在同一周
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameWeekDates(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
        if (0 == subYear) {
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2
                    .get(Calendar.WEEK_OF_YEAR))
                return true;
        } else if (1 == subYear && 11 == cal2.get(Calendar.MONTH)) {
            // 如果12月的最后一周横跨来年第一周的话则最后一周即算做来年的第一周
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2
                    .get(Calendar.WEEK_OF_YEAR))
                return true;
        } else if (-1 == subYear && 11 == cal1.get(Calendar.MONTH)) {
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2
                    .get(Calendar.WEEK_OF_YEAR))
                return true;
        }
        return false;
    }

    /**
     * 格式化时间
     *
     * @param time 秒时间戳
     * @return
     */
    public static String formatDate(long time) {
        long diff = Calendar.getInstance().getTimeInMillis() / 1000 - time / 1000;
        String formatTime;
        if (diff < 60) {
            formatTime = "刚刚";
        } else if (diff < 60 * 60) {
            formatTime = (diff / 60) + "分钟前";
        } else if (diff < 60 * 60 * 24) {
            formatTime = (diff / (60 * 60)) + "小时前";
        } else if (diff < 60 * 60 * 24 * 8) {
            int day = (int) (diff / (60 * 60 * 24));
            if (day == 1) {
                formatTime = "昨天";
            } else if (day == 2) {
                formatTime = "前天";
            } else {
                formatTime = day + "天前";
            }
        } else {
            formatTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date(time));
        }
        return formatTime;
    }
}


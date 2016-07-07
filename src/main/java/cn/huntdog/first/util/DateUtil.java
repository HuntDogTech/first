package cn.huntdog.first.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by JonDai on 2016/7/6.
 */
public class DateUtil {
    public static final String CHINESE_DATE_FORAMT_YMD = "yyyy年MM月dd日";
    public static final String CHINAESE_DATETIME_FORMAT_YMDHMS = "yyyy年MM月dd日 HH:mm:ss";
    public static final String CHINAESE_DATETIME_FORMAT_YMDHM = "yyyy年MM月dd日HH时mm分";
    public static final String CHINAESE_WEEK_FORMAT_YW = "xxxx年xx周";
    public static final String CHINAESE_WEEK_FORMAT = "xxxxxx";
    public static final String DEFAULT_DATE_FORMAT_YMD = "yyyy-MM-dd";
    public static final String DEFAULT_DATE_FORMAT_YM = "yyyy-MM";
    public static final String DEFAULT_DATE_FORMAT_MD = "MM-dd";
    public static final String DEFAULT_DATETIME_FORMAT_YMDH = "yyyy-MM-dd HH";
    public static final String DEFAULT_DATETIME_FORMAT_YMDHMS = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_DATETIME_FORMAT_YMDHM = "yyyy-MM-dd HH:mm";
    public static final String DEFAULT_DATETIME_FORMAT_MDH = "MM-dd HH";
    public static final String NOSYMBOL_DATETIME_FORMAT_YMDHMS = "yyyyMMddHHmmss";
    public static final String NOSYMBOL_DATETIME_FORMAT_YMDH = "yyyyMMddHH";
    public static final String NOSYMBOL_DATETIME_FORMAT_YMD = "yyyyMMdd";
    public static final String NOSYMBOL_TIME_FORMAT_HMS = "HH:mm:ss";
    public static final String SPRIT_DATETIME_FORMAT_YMDHMS = "yyyy/MM/dd HH:mm:ss";
    public static final String SPRIT_DATETIME_FORMAT_YMDHMSS = "yyyy/MM/dd HH:mm:ss.S";
    public static final String SPRIT_DATETIME_FORMAT_YMDHM = "yyyy/MM/dd HH:mm";
    public static final String SPRIT_DATETIME_FORMAT_YMD = "yyyy/MM/dd";
    public static final String SPRIT_DATETIME_FORMAT_YMDH = "YYYY/MM/dd HH";
    public static final String FLASH_DATETIME_FORMAT_YMDH = "yyyy,MM,dd,HH,mm,ss";
    public static final String DATE_FORMAT_HOUR = "HH:00";
    public static final int ONE_SECOND_MILLIOS = 1000;
    public static final int ONE_MINUTE_MILLIONS = 60000;
    public static final int ONE_HOUR_MILLIONS = 3600000;
    public static final int ONE_DAY_MILLIONS = 86400000;

    public DateUtil() {
    }

    public static String formatDateToString(Date date, String format) {
        if(date == null) {
            return "";
        } else {
            SimpleDateFormat formatter = new SimpleDateFormat(format);
            return formatter.format(date);
        }
    }

    public static String formateToString(String date, String format) throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        Date d = formatter.parse(date);
        return formatDateToString(d, format);
    }

    public static String formatDuration(long time, String format) {
        String result = new String(format);
        int hour = (int)(time / 3600000L);
        int minute = (int)((time - 3600000L) / 60000L);
        int second = (int)((time - 3600000L - (long)(minute * '\uea60')) / 1000L);
        int millisecond = (int)(time % 1000L);
        result = result.replace("hh", String.valueOf(hour));
        result = result.replace("mm", String.valueOf(minute));
        result = result.replace("ss", String.valueOf(second));
        result = result.replace("SSS", String.valueOf(millisecond));
        return result;
    }

    public static java.sql.Date toSqlDate(Date date) {
        return date != null?new java.sql.Date(date.getTime()):null;
    }

    public static Timestamp toTimestamp(Date date) {
        return date != null?new Timestamp(date.getTime()):null;
    }

    public static Date parseDate(String string, String format) {
        Date date = null;

        try {
            if(string != null) {
                SimpleDateFormat ex = new SimpleDateFormat(format);
                date = ex.parse(string);
            }
        } catch (Exception var4) {
            ;
        }

        return date;
    }

    private static Date parseDate(String string) {
        String format = "yyyy-MM-dd";
        if(string.matches("\\d{2,4}\\-\\d{1,2}\\-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}")) {
            format = "yyyy-MM-dd HH:mm:ss";
        }

        if(string.matches("\\d{2,4}\\-\\d{1,2}\\-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}.0")) {
            format = "yyyy-MM-dd HH:mm:ss";
        }

        if(string.matches("\\d{2,4}\\-\\d{1,2}\\-\\d{1,2} \\d{1,2}:\\d{1,2}")) {
            format = "yyyy-MM-dd HH:mm";
        }

        return parseDate(string, format);
    }

    public static Date parseStringToDate(String string) {
        Date date = null;
        date = parseDate(string, "yyyy-MM-dd");
        if(date == null) {
            date = parseDate(string, "yyyy-MM-dd HH:mm");
        }

        if(date == null) {
            date = parseDate(string, "yyyy-MM-dd HH:mm:ss");
        }

        if(date == null) {
            date = parseDate(string, "yyyyMMddHHmmss");
        }

        if(date == null) {
            date = parseDate(string, "yyyy/MM/dd HH:mm:ss");
        }

        if(date == null) {
            date = parseDate(string, "yyyy/MM/dd HH:mm");
        }

        if(date == null) {
            date = parseDate(string, "yyyy/MM/dd");
        }

        return date;
    }

    public static int compareDate(String s1, String s2) {
        try {
            Date e = parseDate(s1);
            Date d2 = parseDate(s2);
            return e.compareTo(d2);
        } catch (Exception var4) {
            var4.printStackTrace();
            return -1;
        }
    }

    public static int compareDate(Date d1, Date d2) {
        try {
            return d1.compareTo(d2);
        } catch (Exception var3) {
            return -1;
        }
    }

    private static Calendar getCalendar(long time) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(time);
        return cal;
    }

//    public static double getDiffInSecond(Date date1, Date date2) {
//        long diff = date2.getTime() - date1.getTime();
//        return DecimalUtil.round((double)diff * 1.0D / 1000.0D, 2);
//    }

    public static int daysBetween(Date begin, Date end) {
        Calendar beginCalendar = Calendar.getInstance();
        beginCalendar.setTime(begin);
        beginCalendar.set(11, 12);
        beginCalendar.set(12, 0);
        beginCalendar.set(13, 0);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(end);
        endCalendar.set(11, 12);
        endCalendar.set(12, 0);
        endCalendar.set(13, 0);
        return (int)((endCalendar.getTimeInMillis() - beginCalendar.getTimeInMillis()) / 86400000L);
    }

    public static int getYear(Date date) {
        Calendar cal = getCalendar(date.getTime());
        return cal.get(1);
    }

    public static int getMonth(Date date) {
        Calendar cal = getCalendar(date.getTime());
        return cal.get(2) + 1;
    }

    public static int getDate(Date date) {
        Calendar cal = getCalendar(date.getTime());
        return cal.get(5);
    }

    public static int getHour(Date date) {
        Calendar cal = getCalendar(date.getTime());
        return cal.get(11);
    }

    public static int getMinute(Date date) {
        Calendar cal = getCalendar(date.getTime());
        return cal.get(12);
    }

    public static int getSecond(Date date) {
        Calendar cal = getCalendar(date.getTime());
        return cal.get(13);
    }

    public static String getCurrDateTime(String pattern) {
        Date utilDate = new Date(System.currentTimeMillis());
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        return df.format(utilDate);
    }

    public static Date[] getAreaOfWeek(int year, int week) {
        Date dt1 = null;
        Date dt2 = null;
        Date[] dates = new Date[2];
        Calendar cal = Calendar.getInstance(Locale.CHINA);
        cal.set(year, 0, 1, 0, 0, 0);
        int maxDay = cal.getActualMaximum(6);

        for(int i = 0; i < maxDay; ++i) {
            if(cal.get(3) == week) {
                if(dt1 == null) {
                    dt1 = cal.getTime();
                }

                dt2 = cal.getTime();
            }

            if(cal.get(3) > week) {
                break;
            }

            cal.add(6, 1);
        }

        dates[0] = dt1;
        dates[1] = dt2;
        return dates;
    }

    public static boolean isLeapYear(int year) {
        return year % 4 == 0 && year % 100 != 0?true:year % 100 == 0 && year % 400 == 0;
    }

    public static String getNDayBeforeDateStr(int nday, String pattern) {
        long Current_Time_Millions = System.currentTimeMillis();
        SimpleDateFormat datetimeFormat = new SimpleDateFormat(pattern);
        String nDayBefore = datetimeFormat.format(new Date(Current_Time_Millions - (long)(86400000 * nday)));
        return nDayBefore;
    }

    public static String getNDayBeforeDateStr(int nday) {
        return getNDayBeforeDateStr(nday, "yyyy年MM月dd日 HH:mm:ss");
    }

    public static String getNDayBeforeOnlyDateStr(int nday) {
        return getNDayBeforeDateStr(nday, "yyyy年MM月dd日");
    }

    public static String getAfterDay(String date, int aday, String pattern) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        Date d = dateFormat.parse(date);
        return dateFormat.format(new Date(d.getTime() + (long)(86400000 * aday)));
    }

    public static String getDayOfWeek(String dateStr) {
        Date date = null;
        Calendar calendar = null;
        String dayOfWeek = "";
        if(dateStr.lastIndexOf(" ") == -1) {
            dateStr = dateStr + " 00:00:00";
        }

        try {
            SimpleDateFormat e = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
            date = e.parse(dateStr);
            calendar = Calendar.getInstance();
            calendar.setTime(date);
            switch(calendar.get(7) - 1) {
                case 0:
                    dayOfWeek = "星期日";
                    break;
                case 1:
                    dayOfWeek = "星期一";
                    break;
                case 2:
                    dayOfWeek = "星期二";
                    break;
                case 3:
                    dayOfWeek = "星期三";
                    break;
                case 4:
                    dayOfWeek = "星期四";
                    break;
                case 5:
                    dayOfWeek = "星期五";
                    break;
                case 6:
                    dayOfWeek = "星期六";
                    break;
                default:
                    dayOfWeek = "星期日";
            }
        } catch (ParseException var5) {
            var5.printStackTrace();
        }

        return dayOfWeek;
    }

    public static String getDistanceDate(String date, int monthCount, String pattern) throws Exception {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(parseDate(date, pattern));
        return formatDateToString(getAfterMonth(gc, monthCount), pattern);
    }

    public static String getDistanceDate(String date, int monthCount) throws Exception {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(parseDate(date));
        return formatDateToString(getAfterMonth(gc, monthCount), "yyyy年MM月dd日");
    }

    private static Date getAfterMonth(GregorianCalendar g, int monthNum) {
        GregorianCalendar gc = (GregorianCalendar)g.clone();
        gc.add(2, monthNum);
        return gc.getTime();
    }

    public static int getDateYearDiff(Date dates) {
        if(dates != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(dates);
            int yearBirth = cal.get(1);
            Calendar cal2 = Calendar.getInstance();
            int yearNow = cal2.get(1);
            cal2.set(1, yearBirth);
            return cal2.before(cal)?yearNow - yearBirth - 1:yearNow - yearBirth;
        } else {
            return 0;
        }
    }

    public static Date getBeforeYear(int nyear) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(1) - nyear;
        cal.set(1, year);
        return cal.getTime();
    }

    public static String getTimeInterval(long messageTime) {
        long interval = (new Date()).getTime() - messageTime;
        long day = interval / 86400000L;
        String time = null;
        if(day >= 0L && day < 1L) {
            int hour = (int)interval / 3600000;
            switch(hour) {
                case 0:
                    time = interval / 60000L + "分钟前";
                    break;
                case 1:
                    time = "一小时前";
                    break;
                case 2:
                    time = "两小时前";
                    break;
                case 3:
                    time = "三小时前";
                    break;
                case 4:
                    time = "四小时前";
                    break;
                case 5:
                    time = "五小时前";
                    break;
                case 6:
                    time = "六小时前";
                    break;
                case 7:
                    time = "七小时前";
                    break;
                case 8:
                    time = "八小时前";
                    break;
                case 9:
                    time = "九小时前";
                    break;
                case 10:
                    time = "十小时前";
                    break;
                case 11:
                    time = "十一小时前";
                    break;
                case 12:
                    time = "十二小时前";
                    break;
                case 13:
                    time = "十三小时前";
                    break;
                case 14:
                    time = "十四小时前";
                    break;
                case 15:
                    time = "十五小时前";
                    break;
                case 16:
                    time = "十六小时前";
                    break;
                case 17:
                    time = "十七小时前";
                    break;
                case 18:
                    time = "十八小时前";
                    break;
                case 19:
                    time = "十九小时前";
                    break;
                case 20:
                    time = "二十小时前";
                    break;
                case 21:
                    time = "二一小时前";
                    break;
                case 22:
                    time = "二十二小时前";
                    break;
                case 23:
                    time = "二十三小时前";
            }
        } else if(day >= 1L && day < 30L) {
            time = interval / 86400000L + "天前";
        } else if(day >= 30L && day < 60L) {
            time = "一个月前";
        } else if(day >= 60L && day < 90L) {
            time = "二个月前";
        } else if(day >= 90L && day < 120L) {
            time = "三个月前";
        } else if(day >= 120L) {
            time = "更早以前";
        }

        return time;
    }

    public static int getCurrentMonthDay() {
        Calendar a = Calendar.getInstance();
        a.set(5, 1);
        a.roll(5, -1);
        int maxDate = a.get(5);
        return maxDate;
    }

    public static int getDaysByYearMonth(int year, int month) {
        Calendar a = Calendar.getInstance();
        a.set(1, year);
        a.set(2, month - 1);
        a.set(5, 1);
        a.roll(5, -1);
        int maxDate = a.get(5);
        return maxDate;
    }

    public static String getDayOfWeekByDate(String date) {
        String dayOfweek = "-1";

        try {
            SimpleDateFormat e = new SimpleDateFormat("yyyy-MM-dd");
            Date myDate = e.parse(date);
            SimpleDateFormat formatter = new SimpleDateFormat("E");
            String str = formatter.format(myDate);
            dayOfweek = str;
        } catch (Exception var6) {
            System.out.println("错误!");
        }

        return dayOfweek;
    }

    public static List<Date> getDates(int workDayCount) {
        ArrayList dates = new ArrayList();
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        for(; dates.size() < workDayCount; cal.add(5, 1)) {
            int day = cal.get(7);
            if(day != 1 && day != 7) {
                dates.add((Date)cal.getTime().clone());
            }
        }

        return dates;
    }

    public static String formatByMillis(long millis, String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return formatter.format(calendar.getTime());
    }

    public static Date getDateFromMonthFirstDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(5, c.getActualMinimum(5));
        return c.getTime();
    }

    public static Date getDateFromMonthLastDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(5, c.getActualMaximum(5));
        return c.getTime();
    }

    public static boolean isTimeBetweenTime(Date date, Date beginDate, Date endDate) {
        boolean isBetween = false;
        if(date != null) {
            Calendar currentCalendar = Calendar.getInstance();
            currentCalendar.setTime(date);
            currentCalendar.set(2014, 1, 1);
            Calendar beginCalendar = Calendar.getInstance();
            beginCalendar.setTime(beginDate);
            beginCalendar.set(2014, 1, 1);
            Calendar endCalendar = Calendar.getInstance();
            endCalendar.setTime(endDate);
            endCalendar.set(2014, 1, 1);
            if(currentCalendar.getTime().after(beginCalendar.getTime()) && currentCalendar.getTime().before(endCalendar.getTime())) {
                isBetween = true;
            }
        }

        return isBetween;
    }
}

package com.lxy.frame.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Lcc on 2017/12/14.
 * Desc:
 */
public class DateUtil {
    private DateUtil() {
    }

    private static final String[] constellation = {"摩羯座", "水瓶座", "双鱼座", "白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座"};

    /**
     * @param date
     * @return yyyy-MM-dd HH:mm
     */
    public static String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        return format.format(date);
    }


    /**
     * @return yy.MM.dd HH:mm
     */
    public static String getNowTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yy.MM.dd HH:mm");
        return format.format(date);
    }

    /**
     * @return yy.MM.dd HH:mm
     */
    public static String getNowDay() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yy.MM.dd");
        return format.format(date);
    }

    public static String getNowMD() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("MM-dd");
        return format.format(date);
    }


    public static Date getDate(String time) {
        long l = Long.parseLong(time);
        Date date = new Date(l);
        return date;
    }

    public static String getMm(long tims) {
        long realTimestamp = getRealTimestamp(tims);
        Date date = new Date(realTimestamp);
        SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm");
        return format.format(date);
    }

    public static String getYM(long tims) {
        long realTimestamp = getRealTimestamp(tims);
        Date date = new Date(realTimestamp);
        SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
        return format.format(date);
    }

    public static String getYyM(long tims) {
        long realTimestamp = getRealTimestamp(tims);
        Date date = new Date(realTimestamp);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        return format.format(date);
    }
    public static String getYMD(long tims) {
        long realTimestamp = getRealTimestamp(tims);
        Date date = new Date(realTimestamp);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    public static String getTime(long tims) {
        long realTimestamp = getRealTimestamp(tims);
        Date date = new Date(realTimestamp);
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        return format.format(date);
    }

    public static String getTimeYMDHm(long tims) {
        long realTimestamp = getRealTimestamp(tims);
        Date date = new Date(realTimestamp);
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分");
        return format.format(date);
    }

    public static String getDetail(long tims) {
        long realTimestamp = getRealTimestamp(tims);
        Date date = new Date(realTimestamp);
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        return format.format(date);
    }

    public static String getNowYMD(long tims) {
        long realTimestamp = getRealTimestamp(tims);
        Date date = new Date(realTimestamp);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    public static String getDetailTime(long tims) {
        long realTimestamp = getRealTimestamp(tims);
        Date date = new Date(realTimestamp);
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm");
        return format.format(date);
    }

    public static int getYYYY(long tims) {
        long realTimestamp = getRealTimestamp(tims);
        Date date = new Date(realTimestamp);
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        return IntUtils.getInt(format.format(date));
    }

    public static String getYYYYMM(long tims) {
        Date date = new Date(tims);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        return format.format(date);
    }

    public static int getMM(long tims) {
        long realTimestamp = getRealTimestamp(tims);
        Date date = new Date(realTimestamp);
        SimpleDateFormat format = new SimpleDateFormat("MM");
        return IntUtils.getInt(format.format(date));
    }

    public static int getDD(long tims) {
        long realTimestamp = getRealTimestamp(tims);
        Date date = new Date(realTimestamp);
        SimpleDateFormat format = new SimpleDateFormat("dd");
        return IntUtils.getInt(format.format(date));
    }

    public static int getHH(long tims) {
        long realTimestamp = getRealTimestamp(tims);
        Date date = new Date(realTimestamp);
        SimpleDateFormat format = new SimpleDateFormat("HH");
        return IntUtils.getInt(format.format(date));
    }

    public static int getmm(long tims) {
        long realTimestamp = getRealTimestamp(tims);
        Date date = new Date(realTimestamp);
        SimpleDateFormat format = new SimpleDateFormat("mm");
        return IntUtils.getInt(format.format(date));
    }

    public static String getMdhmTime(long tims) {
        long realTimestamp = getRealTimestamp(tims);
        Date date = new Date(realTimestamp);
        SimpleDateFormat format = new SimpleDateFormat("MM.dd HH:mm");
        return format.format(date);
    }

    public static String getMonthDay(long tims) {
        long realTimestamp = getRealTimestamp(tims);
        Date date = new Date(realTimestamp);
        SimpleDateFormat format = new SimpleDateFormat("MM月dd日");
        return format.format(date);
    }

    public static String getStrNowTime(long tims) {
        long realTimestamp = getRealTimestamp(tims);
        Date date = new Date(realTimestamp);
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm");
        return format.format(date);
    }

    public static String getNowTime(long tims) {
        long realTimestamp = getRealTimestamp(tims);
        Date date = new Date(realTimestamp);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(date);
    }

    public static String getYMDhms(long tims) {
        long realTimestamp = getRealTimestamp(tims);
        Date date = new Date(realTimestamp);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    public static String getHM(long tims) {
        long realTimestamp = getRealTimestamp(tims);
        Date date = new Date(realTimestamp);
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        return format.format(date);
    }

    public static String getMS(long tims) {
        long realTimestamp = getRealTimestamp(tims);
        Date date = new Date(realTimestamp);
        SimpleDateFormat format = new SimpleDateFormat("mm:ss");
        return format.format(date);
    }

    public static String getHMS(long tims) {
        long realTimestamp = getRealTimestamp(tims);
        Date date = new Date(realTimestamp);
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        return format.format(date);
    }

    public static String getWeek(long time) {
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        long realTimestamp = getRealTimestamp(time);
        Date date = new Date(realTimestamp);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (week < 0) {
            week = 0;
        }
        return weekDays[week];
    }

    public static long getTime(String time, String formatStr) {//yyyy年MM月dd日
        long l = 0;
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        try {
            Date date = format.parse(time);
            l = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return l;
    }

    private static long getRealTimestamp(long tims) {
        if (tims + "".length() != 13) {
            tims = tims * 1000;
        }
        return tims;
    }

    public static long getCurrentTime() {
        return System.currentTimeMillis() / 1000;
    }

    public static String getApartTime(long tims) {
        String strTime = null;
        long currentTimeMillis = System.currentTimeMillis();
        long apartTime = currentTimeMillis - (tims * 1000);
        long oneMinute = 60L * 1000L;
        long oneHour = 60L * 1000L * 60L;
        long oneDay = 60L * 1000L * 60L * 24L;
        long oneMonth = 60L * 1000L * 60L * 24L * 30L;
        long oneYear = 60L * 1000L * 60L * 24L * 365L;
        if (apartTime < oneMinute) {
            //小于一分钟
            strTime = "刚刚";
        } else if (apartTime >= oneMinute && apartTime < oneHour) {
            long minute = apartTime / oneMinute;
            strTime = minute + "分钟前";
        } else if (apartTime >= oneHour && apartTime < oneDay) {
            //小于一天
            long hours = apartTime / oneHour;
            strTime = hours + "小时前";
        } else if (apartTime >= oneDay && apartTime < oneMonth) {
            long day = apartTime / oneDay;
            strTime = day + "天前";
        } else {
            SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            strTime = sDateFormat.format(tims);
        }

        /*else if (apartTime >= oneMonth && apartTime < oneYear) {
            long month = apartTime / oneMonth;
            strTime = month + "月前";
        } else if (apartTime >= oneYear) {
            long year = apartTime / oneYear;
            strTime = year + "年前";
        }
        */

        return strTime;
    }

    /**
     * @param date 出生日期
     * @return 年龄
     */
    public static int getAge(Date date) {
        long time = System.currentTimeMillis();
        long birthday = date.getTime();
        int age = (int) ((time - birthday) / 1000 / 60 / 60 / 24 / 365);
        return age;
    }

    /**
     * @param date 时间
     * @return 星座
     */
    public static String getConstellation(Date date) {
        String strConstellation;
        int month = date.getMonth();
        int dateDate = date.getDate();
        switch (month) {
            case 0:
                if (dateDate < 20) {
                    strConstellation = constellation[0];
                } else {
                    strConstellation = constellation[1];
                }
                break;
            case 1:
                if (dateDate < 19) {
                    strConstellation = constellation[1];
                } else {
                    strConstellation = constellation[2];
                }
                break;
            case 2:
                if (dateDate < 21) {
                    strConstellation = constellation[2];
                } else {
                    strConstellation = constellation[3];
                }
                break;
            case 3:
                if (dateDate < 20) {
                    strConstellation = constellation[3];
                } else {
                    strConstellation = constellation[4];
                }
                break;
            case 4:
                if (dateDate < 21) {
                    strConstellation = constellation[4];
                } else {
                    strConstellation = constellation[5];
                }
                break;
            case 5:
                if (dateDate < 22) {
                    strConstellation = constellation[5];
                } else {
                    strConstellation = constellation[6];
                }
                break;
            case 6:
                if (dateDate < 23) {
                    strConstellation = constellation[6];
                } else {
                    strConstellation = constellation[7];
                }
                break;
            case 7:
                if (dateDate < 23) {
                    strConstellation = constellation[7];
                } else {
                    strConstellation = constellation[8];
                }
                break;
            case 8:
                if (dateDate < 23) {
                    strConstellation = constellation[8];
                } else {
                    strConstellation = constellation[9];
                }
                break;
            case 9:
                if (dateDate < 24) {
                    strConstellation = constellation[9];
                } else {
                    strConstellation = constellation[10];
                }
                break;
            case 10:
                if (dateDate < 24) {
                    strConstellation = constellation[10];
                } else {
                    strConstellation = constellation[11];
                }
                break;
            case 11:
                if (dateDate < 22) {
                    strConstellation = constellation[11];
                } else {
                    strConstellation = constellation[0];
                }
                break;
            default:
                strConstellation = "未知星座";
                break;
        }
        return strConstellation;
    }

    /**
     * 毫秒转时分秒
     *
     * @param second
     * @return
     */
    public static String formatSecond(long second) {
        StringBuffer html = new StringBuffer();
        if (second != 0) {
            long s = second / 1000;
            Integer hours = (int) (s / (60 * 60));
            Integer minutes = (int) (s / 60 - hours * 60);
            Integer seconds = (int) (s - minutes * 60 - hours * 60 * 60);
            if (hours > 0) {
                if (hours < 10) {
                    html.append(0).append(hours);
                } else {
                    html.append(hours);
                }
                html.append(":");
                if (minutes < 10) {
                    html.append(0).append(minutes);
                } else {
                    html.append(minutes);
                }
                html.append(":");
                if (seconds < 10) {
                    html.append(0).append(seconds);
                } else {
                    html.append(seconds);
                }
            } else if (minutes > 0) {
                if (minutes < 10) {
                    html.append(0).append(minutes);
                } else {
                    html.append(minutes);
                }
                html.append(":");
                if (seconds < 10) {
                    html.append(0).append(seconds);
                } else {
                    html.append(seconds);
                }
            } else {
                if (seconds < 10) {
                    html.append("00:").append(0).append(seconds);
                } else {
                    html.append("00:").append(seconds);
                }
            }
        }
        return html.toString();

    }

    /**
     * 是否是今天
     *
     * @param date
     * @return
     */
    public static boolean isToday(final Date date) {
        return isTheDay(date, new Date());
    }

    /**
     * 是否是指定日期
     *
     * @param date
     * @param day
     * @return
     */
    public static boolean isTheDay(final Date date, final Date day) {
        return date.getTime() >= dayBegin(day).getTime()
                && date.getTime() <= dayEnd(day).getTime();
    }

    /**
     * 获取指定时间的那天 00:00:00.000 的时间
     *
     * @param date
     * @return
     */
    public static Date dayBegin(final Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    /**
     * 获取指定时间的那天 23:59:59.999 的时间
     *
     * @param date
     * @return
     */
    public static Date dayEnd(final Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.set(Calendar.MILLISECOND, 999);
        return c.getTime();
    }

    public static String getTime() {
        long time = System.currentTimeMillis() / 1000;//获取系统时间的10位的时间戳
        String str = String.valueOf(time);
        return str;
    }

    public static long getTimeLong() {
        long time = System.currentTimeMillis() / 1000;//获取系统时间的10位的时间戳
        return time;
    }

    public static boolean isTimeStamp(String time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日HH时mm分");
        Date date = null;
        try {
            date = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        LogUtil.e("isTimeStamp", date.getTime() + ">>>" + Long.valueOf(getTime()));
        return date.getTime() / 1000 > Long.valueOf(getTime()) ? true : false;
    }

    public static Long getTimeStamp(String time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日HH时mm分");
        Date date = null;
        try {
            date = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime() / 1000;
    }

    public static Long getTimeStampYYYYMMDD(String time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = null;
        try {
            date = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime() / 1000;
    }


    /**
     * MS turn every minute
     *
     * @param duration Millisecond
     * @return Every minute
     */
    public static String timeParse(long duration) {
        String time = "";
        if (duration > 1000) {
            time = timeParseMinute(duration);
        } else {
            long minute = duration / 60000;
            long seconds = duration % 60000;
            long second = Math.round((float) seconds / 1000);
            if (minute < 10) {
                time += "0";
            }
            time += minute + ":";
            if (second < 10) {
                time += "0";
            }
            time += second;
        }
        return time;
    }

    /**
     * MS turn every minute
     *
     * @param duration Millisecond
     * @return Every minute
     */
    public static String timeParseMinute(long duration) {
        SimpleDateFormat msFormat = new SimpleDateFormat("mm:ss");
        try {
            return msFormat.format(duration);
        } catch (Exception e) {
            e.printStackTrace();
            return "0:00";
        }
    }
}

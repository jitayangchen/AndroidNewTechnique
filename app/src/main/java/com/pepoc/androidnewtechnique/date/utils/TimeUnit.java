package com.pepoc.androidnewtechnique.date.utils;

/*******************************************************************************
 * Copyright (C) 2012-2015 Microfountain Technology, Inc. All Rights Reserved.
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential
 *
 * Last Modified: 2018-11-21 10:55:46
 ******************************************************************************/


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 时间格式化单元
 *
 * @author javiylee
 */
public class TimeUnit {

    public static final String timeBaseFormat = "yyyy-MM-dd-HH-mm-ss";

    public static final String normFormat = "yyyy年M月d日H时m分s秒";

    public static final String yyyy_FM = "yyyy年";

    public static final String M_FM = "M月";

    public static final String d_FM = "d日";

    public static final String H_FM = "H时";

    public static final String RTGROUP = "(?:(\\d{4})年)?(?:(\\d\\d?)月)?(?:(\\d\\d?)日)?(?:(\\d\\d?)时)?(?:(\\d\\d?)分)?(?:(\\d\\d?)秒)?";

    public static final Pattern RTGROUP_PAT = Pattern.compile(RTGROUP);

    public static final ThreadLocal<SimpleDateFormat> normFormater = new ThreadLocal<SimpleDateFormat>();

    // public SimpleDateFormat normFormater = new SimpleDateFormat(normFormat);

    public static SimpleDateFormat getNormFormater() {
        SimpleDateFormat sdf = normFormater.get();
        if (sdf == null) {
            sdf = new SimpleDateFormat(normFormat);
            normFormater.set(sdf);
        }
        return sdf;

    }

    public SimpleDateFormat Y_FMT = new SimpleDateFormat(yyyy_FM);

    public SimpleDateFormat M_FMT = new SimpleDateFormat(M_FM);

    public SimpleDateFormat D_FMT = new SimpleDateFormat(d_FM);

    public SimpleDateFormat H_FMT = new SimpleDateFormat(H_FM);

    public static final int AL_FULL = 5;

    public static final int AL_YEAR = 0;

    public static final int AL_MONTH = 1;

    public static final int AL_DATE = 2;

    public static final int AL_TIME = 3;

    private Calendar calendar;

    private String timeExpression = null;

    private String timeNorm = "";

    private Date time;

    // private TimePoint _tp = new TimePoint();

    private int[] tunit = {-1, -1, -1, -1, -1, -1};

    private String timeBaseText;

    private boolean autoFill;

    private boolean ignoreIfNoDate;

    private int[] originalTunit;

    private String[] timeRelate;

    private boolean halfday;

    private boolean toRelate;

    private int ignoreLevel;

    private SimpleDateFormat sdf = new SimpleDateFormat(timeBaseFormat);

    private static Pattern SPLIT_HG = Pattern.compile("-");

    private static Pattern SPLIT_TS = Pattern.compile("[/\\\\\\-. ]+");

    /**
     * 时间表达式单元构造方法 该方法作为时间表达式单元的入口，将时间表达式字符串传入
     *
     * @param expTime 时间表达式字符串
     * @param n
     */
    public TimeUnit(String exp_time, Date timeBase, boolean autoFill, boolean ignoreIfNoDate) {
        this(exp_time, timeBase, autoFill, ignoreIfNoDate, null);
    }

    /**
     * 时间表达式单元构造方法 该方法作为时间表达式单元的入口，将时间表达式字符串传入
     *
     * @param expTime 时间表达式字符串
     * @param n
     */
    public TimeUnit(String expTime, Date timeBase, boolean autoFill, boolean ignoreIfNoDate, String relateDate) {
        this(expTime, timeBase, autoFill, ignoreIfNoDate, relateDate, AL_FULL);
    }

    /**
     * 时间表达式单元构造方法 该方法作为时间表达式单元的入口，将时间表达式字符串传入
     *
     * @param expTime 时间表达式字符串
     * @param n
     */
    public TimeUnit(String expTime, Date timeBase, boolean autoFill, boolean ignoreIfNoDate, String relateDate, int ignoreLevel) {
        timeExpression = expTime;
        this.autoFill = autoFill;
        this.ignoreIfNoDate = ignoreIfNoDate;
        this.timeBaseText = sdf.format(timeBase);
        this.ignoreLevel = ignoreLevel;
        if (relateDate != null) {
            toRelate = true;
            this.timeRelate = new String[6];
            Matcher m = RTGROUP_PAT.matcher(relateDate);
            if (m.find()) {
                timeRelate[0] = m.group(1);
                timeRelate[1] = m.group(2);
                timeRelate[2] = m.group(3);
                timeRelate[3] = m.group(4);
                timeRelate[4] = m.group(5);
                timeRelate[5] = m.group(6);
            }

        }
        normalization();
    }

    public String getTimeExpression() {
        return timeExpression;
    }

    public Date getTime() {
        return time;
    }

    private int endidx = -1;

    private static Pattern pattern_year1 = Pattern.compile("([0-9]?[0-9]{3})(?:年|(?=[\\-/. ]?\\d\\d月)|(?=[\\-/. ]?\\d\\d[\\-/. ]?\\d\\d[日号]))");

    private static Pattern pattern_year2 = Pattern.compile("([0-9]{2}) ?(?:年)");

    /**
     * 年-规范化方法 该方法识别时间表达式单元的年字段
     */
    public void setYear() {

        Matcher match = pattern_year1.matcher(timeExpression);
        if (match.find()) {
            tunit[0] = Integer.parseInt(match.group(1));
            endidx = match.end();
            return;
        }

        match = pattern_year2.matcher(timeExpression);
        if (match.find()) {
            tunit[0] = Integer.parseInt(match.group(1));
            if (tunit[0] >= 0 && tunit[0] < 100) {
                if (tunit[0] < 60) {
                    tunit[0] += 2000;
                } else {
                    tunit[0] += 1900;
                }
            }
            endidx = match.end();
            return;
        }

    }

    private static Pattern pattern_mon1 = Pattern.compile("(1[012]|0?[1-9])(?:月|(?=[\\-/. ]?\\d\\d[日号]|[\\-/. ]\\d\\d?[日号]))");

    private static Pattern pattern_dg = Pattern.compile("[^\\d]{0,2}(\\d\\d)(?=(?:\\d\\d)*(?!\\d))");

    /**
     * 月-规范化方法 01月01号 该方法识别时间表达式单元的月字段
     */
    public void setMonth() {
        Matcher match = pattern_mon1.matcher(timeExpression);
        if (match.find()) {
            tunit[1] = Integer.parseInt(match.group(1));
            endidx = match.end();
        } else if (tunit[0] > -1 && endidx > 2) {
            match = pattern_dg.matcher(timeExpression);
            if (match.find(endidx)) {
                tunit[1] = Integer.parseInt(match.group(1));
                endidx = match.end();
            }
        }
    }

    private static Pattern pattern_day1 = Pattern
            .compile("(?:^|(?<=[^\\d]|(?:\\d\\d)))(?:[0-3][0-9]|[1-9])(?=[日号])|(?<=\\d\\d?月)(?:[0-3][0-9]|[1-9])(?![时分秒:])");

    /**
     * 日-规范化方法 3号 3月1时 该方法识别时间表达式单元的日字段
     */
    public void setDay() {
        Matcher match = pattern_day1.matcher(timeExpression);
        if (match.find()) {
            tunit[2] = Integer.parseInt(match.group());
            endidx = match.end();
        } else if (tunit[1] > -1 && endidx > 3) {
            match = pattern_dg.matcher(timeExpression);
            if (match.find(endidx)) {
                tunit[2] = Integer.parseInt(match.group(1));
                endidx = match.end();
            }
        }
    }

    private static Pattern pattern_hour1 = Pattern.compile("(?<!周|星期)([0-2]?[0-9])(?:[点时])");

    /**
     * 时-规范化方法 该方法识别时间表达式单元的时字段
     */
    public void setHour() {
        /*
         * 清除只能识别11-99时的bug
         */

        Matcher match = pattern_hour1.matcher(timeExpression);
        if (match.find()) {
            tunit[3] = Integer.parseInt(match.group(1));
            endidx = match.end();
        } else if (tunit[2] > -1 && endidx > 1) {
            match = pattern_dg.matcher(timeExpression);
            if (match.find(endidx)) {
                tunit[3] = Integer.parseInt(match.group(1));
                endidx = match.end();
            }
        }
        hourFix();
    }

    private static Pattern pattern_hour2 = Pattern.compile("中午|午间");

    private static Pattern pattern_hour3 = Pattern.compile("下午|午后|[pP][Mm]");

    private void hourFix() {
        /*
         * 对关键字：中午,午间,下午,午后,晚上,傍晚,晚间,晚,pm,PM的正确时间计算 规约： 1.中午/午间0-10点视为12-22点
         * 2.下午/午后0-11点视为12-23点 3.晚上/傍晚/晚间/晚1-11点视为13-23点，12点视为0点
         * 4.0-11点pm/PM视为12-23点
         */
        Matcher match = pattern_hour2.matcher(timeExpression);
        if (match.find()) {
            if (tunit[3] >= 0 && tunit[3] <= 10) {
                tunit[3] += 12;
                setHalfday(true);
            }
        }

        match = pattern_hour3.matcher(timeExpression);
        if (match.find()) {
            if (tunit[3] >= 0 && tunit[3] <= 11) {
                tunit[3] += 12;
                setHalfday(true);
            }
        }

        if (timeExpression.contains("晚")) {
            if (tunit[3] >= 1 && tunit[3] <= 11) {
                tunit[3] += 12;
                setHalfday(true);
            } else if (tunit[3] == 12) {
                tunit[3] = 0;
                setHalfday(true);
            }
        }
    }

    private static Pattern pattern_minu1 = Pattern.compile("(?:[0-5]?[0-9](?=分(?!钟)))|(?:(?<=((?<!小)[点时]))[0-5]?[0-9](?!刻))");

    private static Pattern pattern_minu2 = Pattern.compile("(?<=[点时])[1一]刻(?!钟)");

    private static Pattern pattern_minu3 = Pattern.compile("(?<=[点时])半");

    private static Pattern pattern_minu4 = Pattern.compile("(?<=[点时])[3三]刻(?!钟)");

    /**
     * 分-规范化方法 该方法识别时间表达式单元的分字段
     */
    public void setMinute() {
        /*
         * 添加了省略“分”说法的时间 如17点15
         */
        Matcher match = pattern_minu1.matcher(timeExpression);
        if (match.find()) {
            String mm = match.group();
            if (!mm.equals("")) {
                tunit[4] = Integer.parseInt(match.group());
                endidx = match.end();
            } else if (tunit[3] > -1 && endidx > 1) {
                match = pattern_dg.matcher(timeExpression);
                if (match.find(endidx)) {
                    tunit[4] = Integer.parseInt(match.group(1));
                    endidx = match.end();
                }
            }
        } else if (tunit[3] > -1 && endidx > 1) {
            match = pattern_dg.matcher(timeExpression);
            if (match.find(endidx)) {
                tunit[4] = Integer.parseInt(match.group(1));
                endidx = match.end();
            }
        }
        /*
         * 添加对一刻，半，3刻的正确识别（1刻为15分，半为30分，3刻为45分）
         */
        match = pattern_minu2.matcher(timeExpression);
        if (match.find()) {
            tunit[4] = 15;
            endidx = match.end();
        }

        match = pattern_minu3.matcher(timeExpression);
        if (match.find()) {
            tunit[4] = 30;
            endidx = match.end();
        }

        match = pattern_minu4.matcher(timeExpression);
        if (match.find()) {
            tunit[4] = 45;
            endidx = match.end();
        }

    }

    private static Pattern pattern_sec1 = Pattern.compile("(?:[0-5]?[0-9](?=秒))|(?:(?<=分)[0-5]?[0-9])");

    /**
     * 秒-规范化方法 该方法识别时间表达式单元的秒字段
     */
    public void setSecond() {
        /*
         * 添加了省略“分”说法的时间 如17点15分32
         */
        Matcher match = pattern_sec1.matcher(timeExpression);
        if (match.find()) {
            tunit[5] = Integer.parseInt(match.group());
            endidx = match.end();
        } else if (tunit[4] > -1 && endidx > 1) {
            match = pattern_dg.matcher(timeExpression);
            if (match.find(endidx)) {
                tunit[5] = Integer.parseInt(match.group(1));
                endidx = match.end();
            }
        }
    }

    private static Pattern pattern_s1 = Pattern.compile("(?<!周|星期)(?:[0-2]?[0-9]):[0-5]?[0-9]:[0-5]?[0-9]");

    private static Pattern pattern_s2 = Pattern.compile("(?<!周|星期)(?:[0-2]?[0-9]):[0-5]?[0-9]");

    private static Pattern pattern_s3 = pattern_hour2;

    private static Pattern pattern_s4 = pattern_hour3;

    private static Pattern pattern_s5 = Pattern.compile("(?<!\\d)[0-9]?[0-9]?[0-9]{2}-(?:1[012]|0?[1-9])-(?:[1-2][0-9]|3[0-1]|0?[1-9])");

    private static Pattern pattern_s6 = Pattern.compile("(?<!\\d)(?:1[012]|0?[1-9])/(?:[2-3][0-9]|1[3-9])/[0-9]?[0-9]?[0-9]{2}");

    private static Pattern pattern_s6b = Pattern.compile("(?<!\\d)(?:[2-3][0-9]|1[3-9])/(?:1[012]|0?[1-9])/[0-9]{4}");

    private static Pattern pattern_s6c = Pattern.compile("(?<!\\d)[0-9]{4}/(?:1[012]|0?[1-9])/(?:[2-3][0-9]|1[3-9])");

    private static Pattern pattern_s7 = Pattern.compile("(?<!\\d)[0-9]?[0-9]?[0-9]{2}\\.(?:1[012]|0?[1-9])\\.(?:[0-3][0-9]|[1-9])");

    private static Pattern pattern_s8 = Pattern.compile("(?<![\\d/\\-])([1][0-2]|0?[1-9])[/\\-]([1-2][0-9]|0?[1-9]|3[0-1])(?!/?\\d)");

    private static Pattern pattern_s9 = Pattern.compile("(?<!\\d)(1[012]|0?[1-9])\\.([0-3][0-9]|[1-9]) \\d\\d?[点时]");

    /**
     * 特殊形式的规范化方法 该方法识别特殊形式的时间表达式单元的各个字段
     */
    public void setTotal() {
        Matcher match;
        String[] tmp_parser;
        String tmp_target;

        match = pattern_s1.matcher(timeExpression);
        if (match.find()) {
            tmp_parser = new String[3];
            tmp_target = match.group();
            tmp_parser = tmp_target.split(":");
            tunit[3] = Integer.parseInt(tmp_parser[0]);
            tunit[4] = Integer.parseInt(tmp_parser[1]);
            tunit[5] = Integer.parseInt(tmp_parser[2]);
        } else {
            match = pattern_s2.matcher(timeExpression);
            if (match.find()) {
                tmp_parser = new String[2];
                tmp_target = match.group();
                tmp_parser = tmp_target.split(":");
                tunit[3] = Integer.parseInt(tmp_parser[0]);
                tunit[4] = Integer.parseInt(tmp_parser[1]);
            }
        }
        match = pattern_s3.matcher(timeExpression);
        if (match.find()) {
            if (tunit[3] >= 0 && tunit[3] <= 10) {
                tunit[3] += 12;
                setHalfday(true);
            }
        }

        match = pattern_s4.matcher(timeExpression);
        if (match.find()) {
            if (tunit[3] >= 0 && tunit[3] <= 11) {
                tunit[3] += 12;
                setHalfday(true);
            }
        }

        if (timeExpression.contains("晚")) {
            if (tunit[3] >= 1 && tunit[3] <= 11) {
                tunit[3] += 12;
                setHalfday(true);
            } else if (tunit[3] == 12) {
                tunit[3] = 0;
                setHalfday(true);
            }
        }

        match = pattern_s5.matcher(timeExpression);
        if (match.find()) {
            tmp_parser = new String[3];
            tmp_target = match.group();
            tmp_parser = SPLIT_HG.split(tmp_target);
            tunit[0] = Integer.parseInt(tmp_parser[0]);
            tunit[1] = Integer.parseInt(tmp_parser[1]);
            tunit[2] = Integer.parseInt(tmp_parser[2]);
        }

        match = pattern_s6.matcher(timeExpression);
        if (match.find()) {
            tmp_parser = new String[3];
            tmp_target = match.group();
            tmp_parser = tmp_target.split("/");
            tunit[1] = Integer.parseInt(tmp_parser[0]);
            tunit[2] = Integer.parseInt(tmp_parser[1]);
            tunit[0] = Integer.parseInt(tmp_parser[2]);
        }

        match = pattern_s6b.matcher(timeExpression);
        if (match.find()) {
            tmp_parser = new String[3];
            tmp_target = match.group();
            tmp_parser = tmp_target.split("/");
            tunit[2] = Integer.parseInt(tmp_parser[0]);
            tunit[1] = Integer.parseInt(tmp_parser[1]);
            tunit[0] = Integer.parseInt(tmp_parser[2]);
        }

        match = pattern_s6c.matcher(timeExpression);
        if (match.find()) {
            tmp_parser = new String[3];
            tmp_target = match.group();
            tmp_parser = tmp_target.split("/");
            tunit[0] = Integer.parseInt(tmp_parser[0]);
            tunit[1] = Integer.parseInt(tmp_parser[1]);
            tunit[2] = Integer.parseInt(tmp_parser[2]);
        }

        /*
         * 增加了:固定形式时间表达式 年.月.日 的正确识别
         */
        match = pattern_s7.matcher(timeExpression);
        if (match.find()) {
            tmp_parser = new String[3];
            tmp_target = match.group();
            tmp_parser = tmp_target.split("\\.");
            tunit[0] = Integer.parseInt(tmp_parser[0]);
            tunit[1] = Integer.parseInt(tmp_parser[1]);
            tunit[2] = Integer.parseInt(tmp_parser[2]);
        }
        // 2014/1.1
        match = pattern_s8.matcher(timeExpression);
        if (match.find()) {
            tunit[1] = Integer.parseInt(match.group(1));
            tunit[2] = Integer.parseInt(match.group(2));
        }
        match = pattern_s9.matcher(timeExpression);
        if (match.find()) {
            tunit[1] = Integer.parseInt(match.group(1));
            tunit[2] = Integer.parseInt(match.group(2));
        }
    }

    private static Pattern pattern_b1 = Pattern.compile("\\d+(?=天[以之]?前)");

    // private static Pattern pattern_b1_2 = Pattern.compile("(大)?前[天日]");

    private static Pattern pattern_b2 = Pattern.compile("\\d+(?=天[以之]?后)");

    // private static Pattern pattern_b2_2 = Pattern.compile("(大)?后[天日]");

    private static Pattern pattern_b3 = Pattern.compile("\\d+(?=个月[以之]?前)");

    private static Pattern pattern_b4 = Pattern.compile("\\d+(?=(个)月[以之]?后)");

    private static Pattern pattern_b5 = Pattern.compile("\\d+(?=年[以之]?前)");

    private static Pattern pattern_b6 = Pattern.compile("\\d+(?=年[以之]?后)");

    private static Pattern pattern_b7 = Pattern.compile("\\d+(?=小时[以之]?前)");

    private static Pattern pattern_b8 = Pattern.compile("\\d+(?=小时[以之]?后)");

    private static Pattern pattern_b9 = Pattern.compile("半个?小时[以之]?前");

    private static Pattern pattern_b10 = Pattern.compile("半个?小时[以之]?后");

    private static Pattern pattern_b11 = Pattern.compile("\\d+(?=刻钟[以之]?前)");

    private static Pattern pattern_b12 = Pattern.compile("\\d+(?=刻钟[以之]?后)");

    private static Pattern pattern_b13 = Pattern.compile("\\d+(?=分钟?[以之]?前)");

    private static Pattern pattern_b14 = Pattern.compile("\\d+(?=分钟?[以之]?后)");

    private static Pattern pattern_b15 = Pattern.compile("半分钟?[以之]?前");

    private static Pattern pattern_b16 = Pattern.compile("半分钟?[以之]?后");

    private static Pattern pattern_b17 = Pattern.compile("\\d+(?=秒钟?[以之]?前)");

    private static Pattern pattern_b18 = Pattern.compile("\\d+(?=秒钟?[以之]?后)");

    private static Pattern pattern_b19 = Pattern.compile("半秒钟?[以之]?前");

    private static Pattern pattern_b20 = Pattern.compile("半秒钟?[以之]?后");

    private static Pattern pattern_b21 = Pattern.compile("(\\d+)((周|星期)?[以之]?后)");

    private static Pattern pattern_b22 = Pattern.compile("(\\d+)((周|星期)[以之]?前)");

    /**
     * 设置以上文时间为基准的时间偏移计算
     */
    public void norm_setBaseRelated() {
        String[] time_grid = new String[6];
        time_grid = SPLIT_HG.split(timeBaseText);
        int[] ini = new int[6];
        for (int i = 0; i < 6; i++) {
            ini[i] = Integer.parseInt(time_grid[i]);
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(ini[0], ini[1] - 1, ini[2], ini[3], ini[4], ini[5]);
        calendar.getTime();

        // year, month, day, hour, min, second
        boolean[] flag = {false, false, false, false, false, false};// 观察时间表达式是否因当前相关时间表达式而改变时间

        Matcher match = pattern_b1.matcher(timeExpression);
        if (match.find()) {
            flag[2] = true;
            int day = Integer.parseInt(match.group());
            calendar.add(Calendar.DATE, -day);
        }

        match = pattern_b2.matcher(timeExpression);
        if (match.find()) {
            flag[2] = true;
            int day = Integer.parseInt(match.group());
            calendar.add(Calendar.DATE, day);
        }

        match = pattern_b3.matcher(timeExpression);
        if (match.find()) {
            flag[1] = true;
            int month = Integer.parseInt(match.group());
            calendar.add(Calendar.MONTH, -month);
        }

        match = pattern_b4.matcher(timeExpression);
        if (match.find()) {
            flag[1] = true;
            int month = Integer.parseInt(match.group());
            calendar.add(Calendar.MONTH, month);
        }
        if (tunit[0] == -1) {
            match = pattern_b5.matcher(timeExpression);
            if (match.find()) {
                flag[0] = true;
                int year = Integer.parseInt(match.group());
                calendar.add(Calendar.YEAR, -year);
            }
        }
        if (tunit[0] == -1) {
            match = pattern_b6.matcher(timeExpression);
            if (match.find()) {
                flag[0] = true;
                int year = Integer.parseInt(match.group());
                calendar.add(Calendar.YEAR, year);
            }
        }

        // ///////////////////新支持各类之前/之后 ////// //added by zhu
        match = pattern_b7.matcher(timeExpression);
        if (match.find()) {
            flag[4] = true;
            int hour = Integer.parseInt(match.group());
            long ms = calendar.getTime().getTime() - hour * 3600 * 1000;
            calendar.setTimeInMillis(ms);
        }

        match = pattern_b8.matcher(timeExpression);
        if (match.find()) {
            flag[4] = true;
            int hour = Integer.parseInt(match.group());
            long ms = calendar.getTime().getTime() + hour * 3600 * 1000;
            calendar.setTimeInMillis(ms);
        }

        match = pattern_b9.matcher(timeExpression);
        if (match.find()) {
            flag[4] = true;
            long ms = calendar.getTime().getTime() - 1800 * 1000;
            calendar.setTimeInMillis(ms);
        }

        match = pattern_b10.matcher(timeExpression);
        if (match.find()) {
            flag[4] = true;
            long ms = calendar.getTime().getTime() + 1800 * 1000;
            calendar.setTimeInMillis(ms);
        }

        match = pattern_b11.matcher(timeExpression);
        if (match.find()) {
            flag[4] = true;
            int q = Integer.parseInt(match.group());
            long ms = calendar.getTime().getTime() - q * 15 * 60 * 1000;
            calendar.setTimeInMillis(ms);
        }

        match = pattern_b12.matcher(timeExpression);
        if (match.find()) {
            flag[4] = true;
            int q = Integer.parseInt(match.group());
            long ms = calendar.getTime().getTime() + q * 15 * 60 * 1000;
            calendar.setTimeInMillis(ms);
        }

        if (tunit[3] == -1) {
            match = pattern_b13.matcher(timeExpression);
            if (match.find()) {
                flag[4] = true;
                int min = Integer.parseInt(match.group());
                long ms = calendar.getTime().getTime() - min * 60 * 1000;
                calendar.setTimeInMillis(ms);
            }

            match = pattern_b14.matcher(timeExpression);
            if (match.find()) {
                flag[4] = true;
                int min = Integer.parseInt(match.group());
                long ms = calendar.getTime().getTime() + min * 60 * 1000;
                calendar.setTimeInMillis(ms);
            }

        }
        match = pattern_b15.matcher(timeExpression);
        if (match.find()) {
            flag[4] = true;
            long ms = calendar.getTime().getTime() - 30 * 1000;
            calendar.setTimeInMillis(ms);
        }

        match = pattern_b16.matcher(timeExpression);
        if (match.find()) {
            flag[4] = true;
            long ms = calendar.getTime().getTime() + 30 * 1000;
            calendar.setTimeInMillis(ms);
        }

        if (tunit[4] == -1) {
            match = pattern_b17.matcher(timeExpression);
            if (match.find()) {
                flag[5] = true;
                int sec = Integer.parseInt(match.group());
                long ms = calendar.getTime().getTime() - sec * 1000;
                calendar.setTimeInMillis(ms);
            }

            match = pattern_b18.matcher(timeExpression);
            if (match.find()) {
                flag[5] = true;
                int sec = Integer.parseInt(match.group());
                long ms = calendar.getTime().getTime() + sec * 1000;
                calendar.setTimeInMillis(ms);
            }
        }

        match = pattern_b19.matcher(timeExpression);
        if (match.find()) {
            flag[5] = true;
            long ms = calendar.getTime().getTime() - 500;
            calendar.setTimeInMillis(ms);
        }

        match = pattern_b20.matcher(timeExpression);
        if (match.find()) {
            flag[5] = true;
            long ms = calendar.getTime().getTime() + 500;
            calendar.setTimeInMillis(ms);
        }

        match = pattern_b21.matcher(timeExpression);
        if (match.find()) {
            flag[2] = true;
            int week = Integer.parseInt(match.group(1));
            long ms = calendar.getTime().getTime() + week * 7 * 24 * 3600 * 1000;
            calendar.setTimeInMillis(ms);
        }

        match = pattern_b22.matcher(timeExpression);
        if (match.find()) {
            flag[2] = true;
            int week = Integer.parseInt(match.group(1));
            long ms = calendar.getTime().getTime() - week * 7 * 24 * 3600 * 1000;
            calendar.setTimeInMillis(ms);
        }
        // /////////////////////////////////////

        String s = sdf.format(calendar.getTime());
        String[] time_fin = SPLIT_HG.split(s);
        if (flag[0] || flag[1] || flag[2] || flag[3] || flag[4] || flag[5]) {
            tunit[0] = Integer.parseInt(time_fin[0]);
        }
        if (flag[1] || flag[2] || flag[3] || flag[4] || flag[5]) {
            tunit[1] = Integer.parseInt(time_fin[1]);
        }
        if (flag[2] || flag[3] || flag[4] || flag[5]) {
            tunit[2] = Integer.parseInt(time_fin[2]);
        }
        if (flag[3] || flag[4] || flag[5]) {
            tunit[3] = Integer.parseInt(time_fin[3]);
        }
        if (flag[4] || flag[5]) {
            tunit[4] = Integer.parseInt(time_fin[4]);
        }
        if (flag[5]) {
            tunit[5] = Integer.parseInt(time_fin[5]);
        }
    }

    private static Pattern pattern_c1 = Pattern.compile("大?前年");

    private static Pattern pattern_c2 = Pattern.compile("去年|上[一1]?年");

    private static Pattern pattern_c3 = Pattern.compile("[明次下]年");

    private static Pattern pattern_c4 = Pattern.compile("大?后年");

    private static Pattern pattern_c5 = Pattern.compile("(上上?)个?月");

    private static Pattern pattern_c6 = Pattern.compile("(?:[本当该]|这个)月");

    private static Pattern pattern_c7 = Pattern.compile("[下次]个?月");

    private static Pattern pattern_c8 = Pattern.compile("(?<!大)前[天日]");

    private static Pattern pattern_c9 = Pattern.compile("今(?![年月个])|[当本][日天]");

    private static Pattern pattern_c10 = Pattern.compile("明(?!年)|次日");

    private static Pattern pattern_c11 = Pattern.compile("(?<!大)后[天日]");

    private static Pattern pattern_c12 = Pattern.compile("大大?后[天日]");

    private static Pattern pattern_c13 = Pattern.compile("(?<=(?:上上个?(?:周|星期)))[1-7]");

    private static Pattern pattern_c14 = Pattern.compile("(?<=(?:(?<!上)上个?(?:周|星期)))[1-7]");

    private static Pattern pattern_c15 = Pattern.compile("(?<=(?:(?<!下)下个?(?:周|星期)))[1-7]");

    private static Pattern pattern_c16 = Pattern.compile("(?<=(?:下下个?(?:周|星期)))[1-7]");

    private static Pattern pattern_c17 = Pattern.compile("(?<=(?:(?<![上下])个?(?:周|星期)))[1-7]");

    private static Pattern pattern_c18 = Pattern.compile("月[底末]");

    private static Pattern pattern_c19 = Pattern.compile("年[底末]");

    /**
     * 设置当前时间相关的时间表达式
     */
    public void norm_setCurRelated() {
        String[] time_grid = new String[6];
        time_grid = SPLIT_HG.split(timeBaseText);
        int[] ini = new int[6];
        for (int i = 0; i < 6; i++) {
            ini[i] = tunit[i] != -1 ? tunit[i] : Integer.parseInt(time_grid[i]);
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(ini[0], ini[1] - 1, ini[2], ini[3], ini[4], ini[5]);
        calendar.getTime();

        boolean[] flag = {false, false, false};// 观察时间表达式是否因当前相关时间表达式而改变时间

        Matcher match = null;

        if (tunit[0] == -1) {
            match = pattern_c1.matcher(timeExpression);
            if (match.find()) {
                flag[0] = true;
                calendar.add(Calendar.YEAR, -match.group().length());
            }

            match = pattern_c2.matcher(timeExpression);
            if (match.find()) {
                flag[0] = true;
                calendar.add(Calendar.YEAR, -1);
            }

            if (timeExpression.contains("今年")) {
                flag[0] = true;
                calendar.add(Calendar.YEAR, 0);
            }

            match = pattern_c3.matcher(timeExpression);
            if (match.find()) {
                flag[0] = true;
                calendar.add(Calendar.YEAR, 1);
            }

            match = pattern_c4.matcher(timeExpression);
            if (match.find()) {
                flag[0] = true;
                calendar.add(Calendar.YEAR, match.group().length());
            }
        }

        if (tunit[1] == -1) {
            match = pattern_c5.matcher(timeExpression);
            if (match.find()) {
                flag[1] = true;
                calendar.add(Calendar.MONTH, -match.group(1).length());

            }

            match = pattern_c6.matcher(timeExpression);
            if (match.find()) {
                flag[1] = true;
                calendar.add(Calendar.MONTH, 0);
            }

            match = pattern_c7.matcher(timeExpression);
            if (match.find()) {
                flag[1] = true;
                calendar.add(Calendar.MONTH, 1);
            }
        }

        if (tunit[2] == -1) {
            if (timeExpression.contains("大前天") || timeExpression.contains("大前日")) {
                flag[2] = true;
                calendar.add(Calendar.DATE, -3);
            }

            match = pattern_c8.matcher(timeExpression);
            if (match.find()) {
                flag[2] = true;
                calendar.add(Calendar.DATE, -2);
            }

            if (timeExpression.contains("昨")) {
                flag[2] = true;
                calendar.add(Calendar.DATE, -1);
            }

            match = pattern_c9.matcher(timeExpression);
            if (match.find()) {
                flag[2] = true;
                calendar.add(Calendar.DATE, 0);
            }

            match = pattern_c10.matcher(timeExpression);
            if (match.find()) {
                flag[2] = true;
                calendar.add(Calendar.DATE, 1);
            }

            match = pattern_c11.matcher(timeExpression);
            if (match.find()) {
                flag[2] = true;
                calendar.add(Calendar.DATE, 2);
            }

            match = pattern_c12.matcher(timeExpression);
            if (match.find()) {
                flag[2] = true;
                calendar.add(Calendar.DATE, match.group().length());
            }

            match = pattern_c13.matcher(timeExpression);
            if (match.find()) {
                flag[2] = true;
                int week = Integer.parseInt(match.group());
                if (week == 7) {
                    week = 1;
                } else {
                    week++;
                }
                calendar.add(Calendar.WEEK_OF_MONTH, -2);
                calendar.set(Calendar.DAY_OF_WEEK, week);
            }

            match = pattern_c14.matcher(timeExpression);
            if (match.find()) {
                flag[2] = true;
                int week = Integer.parseInt(match.group());
                if (week == 7) {
                    week = 1;
                } else {
                    week++;
                }
                calendar.add(Calendar.WEEK_OF_MONTH, -1);
                calendar.set(Calendar.DAY_OF_WEEK, week);
            }

            match = pattern_c15.matcher(timeExpression);
            if (match.find()) {
                flag[2] = true;
                int week = Integer.parseInt(match.group());
                if (week == 7) {
                    week = 1;
                } else {
                    week++;
                }
                calendar.add(Calendar.WEEK_OF_MONTH, 1);
                calendar.set(Calendar.DAY_OF_WEEK, week);
            }

            match = pattern_c16.matcher(timeExpression);
            if (match.find()) {
                flag[2] = true;
                int week = Integer.parseInt(match.group());
                if (week == 7) {
                    week = 1;
                } else {
                    week++;
                }
                calendar.add(Calendar.WEEK_OF_MONTH, 2);
                calendar.set(Calendar.DAY_OF_WEEK, week);
            }

            match = pattern_c17.matcher(timeExpression);
            if (match.find()) {
                flag[2] = true;
                int week = Integer.parseInt(match.group());
                if (week == 7) {
                    week = 1;
                } else {
                    week++;
                }
                calendar.add(Calendar.WEEK_OF_MONTH, 0);
                calendar.set(Calendar.DAY_OF_WEEK, week);
            }

            match = pattern_c18.matcher(timeExpression);
            if (match.find()) {
                flag[2] = true;
                if (tunit[1] != -1 || flag[1]) {
                    calendar.set(Calendar.MONTH, flag[1] ? calendar.get(Calendar.MONTH) + 1 : tunit[1]);
                    calendar.set(Calendar.DAY_OF_MONTH, 1);
                    calendar.add(Calendar.DATE, -1);
                } else {
                    calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
                    calendar.set(Calendar.DAY_OF_MONTH, 1);
                    calendar.add(Calendar.DATE, -1);
                }
            }

            match = pattern_c19.matcher(timeExpression);
            if (match.find()) {
                flag[2] = true;
                if (tunit[0] != -1 || flag[0]) {
                    calendar.set(Calendar.YEAR, flag[0] ? calendar.get(Calendar.YEAR) + 1 : tunit[0] + 1);
                    calendar.set(Calendar.DAY_OF_YEAR, 1);
                    calendar.add(Calendar.DATE, -1);
                } else {
                    calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + 1);
                    calendar.set(Calendar.DAY_OF_YEAR, 1);
                    calendar.add(Calendar.DATE, -1);
                }
            }

        }

        String s = sdf.format(calendar.getTime());
        String[] time_fin = SPLIT_HG.split(s);
        if (flag[0] || ((flag[1] || flag[2]))) {
            tunit[0] = Integer.parseInt(time_fin[0]);
        }
        if (flag[1] || (flag[2])) {
            tunit[1] = Integer.parseInt(time_fin[1]);
        }
        if (flag[2]) {
            tunit[2] = Integer.parseInt(time_fin[2]);
        }

    }

    /**
     * 时分秒 hh:mm:ss
     */
    private static Pattern pattern_nn1 = Pattern.compile("([01]?[0-9]|2[0-4])?:([0-5]?[0-9])(?::([0-5]?[0-9]))?");

    /**
     * 年-月-日
     */
    private static Pattern pattern_nn2 = Pattern
            .compile("(?:(\\d{4})-((?:1[012]|0?[1-9]))-((?:[1-2][0-9]|3[0-1]|0?[1-9]))([01]?[0-9]|2[0-4])[:.]?([0-5]?[0-9])(?:[:.]?([0-5]?[0-9]))?)(?!\\d)");

    /**
     * 年.月.日
     */
    private static Pattern pattern_nn3 = Pattern
            .compile("(?:(\\d{4})\\.((?:1[012]|0?[1-9]))\\.((?:[1-2][0-9]|3[0-1]|0?[1-9]))([01]?[0-9]|2[0-4])[:.]?([0-5]?[0-9])(?:[:.]?([0-5]?[0-9]))?)(?!\\d)");

    /**
     * 年/月/日
     */
    private static Pattern pattern_nn4 = Pattern
            .compile("(?:(\\d{4})/((?:1[012]|0?[1-9]))/((?:[1-2][0-9]|3[0-1]|0?[1-9]))([01]?[0-9]|2[0-4])[:.]?([0-5]?[0-9])(?:[:.]?([0-5]?[0-9]))?)(?!\\d)");

    /**
     * 月/日/年
     */
    private static Pattern pattern_nn5 = Pattern.compile("(?<!\\d)(\\d\\d)/(\\d\\d)/(\\d\\d(?:\\d\\d)?)(?!\\d)");

    /**
     * 13.11 没有年月日等中文信息的时间信息解析
     *
     * @return
     */
    private boolean normal() {
        int len = timeExpression.length();
        boolean ct = false;
        StringBuffer sb = new StringBuffer();
        int x = -1;
        // 保留时间，其他字符过滤掉
        for (int i = 0; i < len; i++) {
            char c = timeExpression.charAt(i);
            // 如果不是时间格式
            if (!((c >= '0' && c <= '9') || c == '/' || c == '.' || c == '-' || c == '\\' || c == ' ' || c == ':')) {
                // 如果是时间格式之前(!ct)的字符或第一次遇到(x<0)的非时间字符
                if (!ct || x < 0) {
                    if (x < 0 && ('周' == c)) {
                        // 如果是“周”，则跳过一位
                        i++;
                        sb.append(" ");
                    } else if (x < 0 && ('上' == c || '中' == c || '下' == c || '晚' == c || '早' == c)) {
                        // 如果是上中下晚早这些分隔时间和日期的字符，则加分隔符
                        sb.append(" ");
                    } else if (sb.length() > 0 && ('年' == c || '月' == c || '日' == c || '号' == c || '时' == c || '点' == c || '分' == c)) {
                        return false;
                    }
                    // 不属于上面两种情况的，则过滤掉
                    x = -2;
                    continue;
                }
                // 如果数字中包含其他字符，则认为时间有较明显的文字信息
                return false;
            }
            if (x == -2)
                x = i;
            sb.append(c);
            ct = true;
        }
        String s = sb.toString();
        Matcher m = pattern_nn1.matcher(s);
        boolean f = m.find();
        // 时分秒
        if (f) {
            String hh = m.group(1);
            String mm = m.group(2);
            String ss = m.group(3);
            if (hh != null)
                tunit[3] = Integer.valueOf(hh);
            if (mm != null)
                tunit[4] = Integer.valueOf(mm);
            if (ss != null)
                tunit[5] = Integer.valueOf(ss);
            s = m.replaceAll("");
        }
        s.trim();

        m = pattern_nn2.matcher(s);
        f = m.find();
        // 年月日时分秒
        if (f) {
            String yy = m.group(1);
            String MM = m.group(2);
            String dd = m.group(3);
            String hh = m.group(4);
            String mm = m.group(5);
            String ss = m.group(6);
            if (yy != null)
                tunit[0] = Integer.valueOf(yy);
            if (MM != null)
                tunit[1] = Integer.valueOf(MM);
            if (dd != null)
                tunit[2] = Integer.valueOf(dd);
            if (hh != null)
                tunit[3] = Integer.valueOf(hh);
            if (mm != null)
                tunit[4] = Integer.valueOf(mm);
            if (ss != null)
                tunit[5] = Integer.valueOf(ss);
            s = m.replaceAll("");
            return true;
        }
        s.trim();

        m = pattern_nn3.matcher(s);
        f = m.find();
        // 年月日时分秒
        if (f) {
            String yy = m.group(1);
            String MM = m.group(2);
            String dd = m.group(3);
            String hh = m.group(4);
            String mm = m.group(5);
            String ss = m.group(6);
            if (yy != null)
                tunit[0] = Integer.valueOf(yy);
            if (MM != null)
                tunit[1] = Integer.valueOf(MM);
            if (dd != null)
                tunit[2] = Integer.valueOf(dd);
            if (hh != null)
                tunit[3] = Integer.valueOf(hh);
            if (mm != null)
                tunit[4] = Integer.valueOf(mm);
            if (ss != null)
                tunit[5] = Integer.valueOf(ss);
            s = m.replaceAll("");
            return true;
        }
        s.trim();

        m = pattern_nn4.matcher(s);
        f = m.find();
        // 年月日时分秒
        if (f) {
            String yy = m.group(1);
            String MM = m.group(2);
            String dd = m.group(3);
            String hh = m.group(4);
            String mm = m.group(5);
            String ss = m.group(6);
            if (yy != null)
                tunit[0] = Integer.valueOf(yy);
            if (MM != null)
                tunit[1] = Integer.valueOf(MM);
            if (dd != null)
                tunit[2] = Integer.valueOf(dd);
            if (hh != null)
                tunit[3] = Integer.valueOf(hh);
            if (mm != null)
                tunit[4] = Integer.valueOf(mm);
            if (ss != null)
                tunit[5] = Integer.valueOf(ss);
            s = m.replaceAll("");
            return true;
        }
        s.trim();

        m = pattern_nn5.matcher(s);
        f = m.find();
        // 月/日/年
        if (f) {
            int a = Integer.valueOf(m.group(1));
            int b = Integer.valueOf(m.group(2));
            int c = Integer.valueOf(m.group(3));
            f = false;
            if (a > 12 && b < 13 && c > 100) {
                tunit[0] = c;
                tunit[1] = b;
                tunit[2] = a;
                f = true;
            } else if (b > 12 && a < 13 && c > 100) {
                tunit[0] = c;
                tunit[1] = a;
                tunit[2] = b;
                f = true;
            }
            if (f) {
                s = m.replaceAll("");
                return true;
            } else {
                return false;
            }
        }
        s.trim();

        String[] xs = SPLIT_TS.split(s);
        if (xs == null || xs.length == 0)
            return false;
        String temp = null;
        if (xs.length == 1) {
            // 如果只是纯数字时间
            temp = xs[0];
            if (temp.length() == 4) {
                int t = Integer.valueOf(temp);
                int y = Integer.valueOf(temp.substring(0, 2));
                int py = Integer.valueOf(temp.substring(2, 4));
                if ((py > 31)) {
                    if (y >= 0 && y < 25) {
                        tunit[3] = y;
                        tunit[4] = py;
                    } else if (y > 24) {
                        tunit[4] = y;
                        tunit[5] = py;
                    }
                    return false;
                }
                // else if (y >= 19 && y <= 20) {
                // tunit[0] = t;
                // }
                // else if ((y > 12 && y < 19 && py < 13)) {
                // tunit[0] = y;
                // tunit[1] = py;
                // }
                // else if (y < 13) {
                // tunit[1] = y;
                // tunit[2] = py;
                // }
                else {
                    return false;
                }
            } else if (temp.length() >= 8) {
                int y = Integer.valueOf(temp.substring(0, 2));
                int oy = Integer.valueOf(temp.substring(2, 4));
                int py = Integer.valueOf(temp.substring(4, 6));
                int qy = Integer.valueOf(temp.substring(6, 8));
                if (temp.length() == 8) {
                    if (y > 12 && py < 13 && qy < 32) {
                        tunit[0] = Integer.valueOf(temp.substring(0, 4));
                        tunit[1] = py;
                        tunit[2] = qy;
                    } else if (y < 13 && oy < 32) {
                        tunit[0] = Integer.valueOf(temp.substring(4, 8));
                        tunit[1] = y;
                        tunit[2] = oy;
                    } else if (oy < 13 && py < 32) {
                        tunit[0] = y;
                        tunit[1] = oy;
                        tunit[2] = py;
                        tunit[3] = qy;
                    } else {
                        return false;
                    }
                } else {
                    tunit[0] = Integer.valueOf(temp.substring(0, 4));
                    if (py < 13 && qy < 32) {
                        tunit[1] = py;
                        tunit[2] = qy;
                        if (temp.length() >= 10)
                            tunit[3] = Integer.valueOf(temp.substring(8, 10));
                        if (temp.length() >= 12)
                            tunit[4] = Integer.valueOf(temp.substring(10, 12));
                        if (temp.length() >= 14)
                            tunit[5] = Integer.valueOf(temp.substring(12, 14));
                    } else {
                        return false;
                    }
                }
            } else if (temp.length() == 6) {
                int y = Integer.valueOf(temp.substring(0, 2));
                int py = Integer.valueOf(temp.substring(2, 4));
                int qy = Integer.valueOf(temp.substring(4, 6));
                if (y > 18 && qy < 13) {
                    tunit[0] = Integer.valueOf(temp.substring(0, 4));
                    tunit[1] = qy;
                } else if (y > 12 && py < 13 && qy < 32) {
                    tunit[0] = y;
                    tunit[1] = py;
                    tunit[2] = qy;
                } else if (qy < 25 && y < 13 && py < 32) {
                    tunit[1] = y;
                    tunit[2] = py;
                    tunit[3] = qy;
                } else if (y < 13 && py < 32) {
                    tunit[0] = qy;
                    tunit[1] = y;
                    tunit[2] = py;
                } else {
                    return false;
                }
            } else {
                return false;
            }
            return true;
        } else {
            temp = xs[0];
            if (temp.isEmpty())
                return false;
            int y = Integer.valueOf(temp);
            if (temp.length() == 4) {
                tunit[0] = y;
                for (int i = 1; i < xs.length; i++) {
                    temp = xs[i];
                    if (temp.length() > 2) {
                        xs[i] = temp.substring(2);
                        tunit[i] = Integer.valueOf(temp.substring(0, 2));
                        if (i < 5) {
                            tunit[i + 1] = Integer.valueOf(xs[i]);
                        }
                    } else {
                        tunit[i] = Integer.valueOf(temp);
                    }
                }
                return true;
            } else if (temp.length() == 2 || temp.length() == 1) {
                tunit[1] = y;
                tunit[2] = Integer.valueOf(xs[1]);
                if (xs.length > 2) {
                    tunit[0] = Integer.valueOf(xs[2]);
                    if (y > 12 || (tunit[0] > 12 && tunit[0] < 32)) {
                        tunit[1] = tunit[2];
                        tunit[2] = tunit[0];
                        tunit[0] = y;
                    }
                } else if (y > 12) {
                    tunit[1] = tunit[2];
                    tunit[2] = y;
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 该方法用于更新timeBase使之具有上下文关联性
     */
    public void modifyTimeBase() {
        String[] time_grid = new String[6];
        time_grid = SPLIT_HG.split(timeBaseText);

        String s = "";
        if (tunit[0] != -1) {
            s += Integer.toString(tunit[0]);
        } else {
            s += time_grid[0];
        }
        for (int i = 1; i < 6; i++) {
            s += "-";
            if (tunit[i] != -1) {
                s += Integer.toString(tunit[i]);
            } else {
                s += time_grid[i];
            }
        }
        timeBaseText = s;
    }

    /**
     * 时间表达式规范化的入口 时间表达式识别后，通过此入口进入规范化阶段， 具体识别每个字段的值
     */
    private void normalization() {
        boolean isNormal = normal();
        if (!isNormal) {
            setYear();
            setMonth();
            setDay();
            setHour();
            setMinute();
            setSecond();
            setTotal();
        } else {
            hourFix();
            modifyTimeBase();
        }

        timeNormallization();

    }

    private void timeNormallization() {
        norm_setBaseRelated();
        // if(!isNormal||tunit[0]==-1)
        if (autoFill) {
            norm_setCurRelated();
        }

        String[] time_grid = new String[6];
        time_grid = SPLIT_HG.split(timeBaseText);

        int tunitpointer = 5;
        while (tunitpointer >= 0 && tunit[tunitpointer] < 0) {
            tunitpointer--;
        }
        int nyr = 0;
        originalTunit = new int[]{-1, -1, -1, -1, -1, -1};
        for (int i = 0; i < 6; i++) {
            if (i < tunitpointer) {
                if (tunit[i] < 0) {
                    if (autoFill || toRelate) {
                        if (toRelate && timeRelate[i] != null && i < 3) {
                            tunit[i] = Integer.parseInt(timeRelate[i]);
                        } else if (autoFill) {
                            tunit[i] = Integer.parseInt(time_grid[i]);
                        }
                    }
                    originalTunit[i] = -1;
                } else if (i <= 2) {
                    nyr++;
                    originalTunit[i] = tunit[i];
                } else {
                    originalTunit[i] = tunit[i];
                }
            } else {
                originalTunit[i] = tunit[i];
            }
        }
        if (ignoreIfNoDate && (tunitpointer > 3 && nyr == 0)) {
            time = null;
            timeNorm = null;
            return;
        }
        if (ignoreIfNoDate && tunitpointer > 3 && (3 - ignoreLevel - nyr) > 0) {
            time = null;
            timeNorm = null;
            return;
        }
        String[] _result_tmp = new String[6];
        _result_tmp[0] = String.valueOf(tunit[0]);
        if (tunit[0] >= 50 && tunit[0] < 100) {
            _result_tmp[0] = "19" + String.valueOf(tunit[0]);
        } else if (tunit[0] > 0 && tunit[0] < 10) {
            _result_tmp[0] = "200" + String.valueOf(tunit[0]);
        } else if (tunit[0] >= 10 && tunit[0] < 50) {
            _result_tmp[0] = "20" + String.valueOf(tunit[0]);
        }

        for (int i = 1; i < 6; i++) {
            _result_tmp[i] = String.valueOf(tunit[i]);
        }

        Calendar cale = Calendar.getInstance();
        cale.clear();
        int i = 0;
        if (Integer.parseInt(_result_tmp[0]) != -1) {
            timeNorm += _result_tmp[0] + "年";
            cale.set(Calendar.YEAR, Integer.valueOf(_result_tmp[0]));
            i++;
        } else {
            if (!autoFill)
                cale.set(Calendar.YEAR, 1970);
        }
        if (Integer.parseInt(_result_tmp[1]) != -1) {
            timeNorm += _result_tmp[1] + "月";
            cale.set(Calendar.MONTH, Integer.valueOf(_result_tmp[1]) - 1);
            i++;
        }
        if (Integer.parseInt(_result_tmp[2]) != -1) {
            timeNorm += _result_tmp[2] + "日";
            cale.set(Calendar.DAY_OF_MONTH, Integer.valueOf(_result_tmp[2]));
            i++;
        }
        if (Integer.parseInt(_result_tmp[3]) != -1) {
            timeNorm += _result_tmp[3] + "时";
            cale.set(Calendar.HOUR_OF_DAY, Integer.valueOf(_result_tmp[3]));
            i++;
        }
        if (Integer.parseInt(_result_tmp[4]) != -1) {
            timeNorm += _result_tmp[4] + "分";
            cale.set(Calendar.MINUTE, Integer.valueOf(_result_tmp[4]));
            i++;
        }
        if (Integer.parseInt(_result_tmp[5]) != -1) {
            timeNorm += _result_tmp[5] + "秒";
            cale.set(Calendar.SECOND, Integer.valueOf(_result_tmp[5]));
            i++;
        }
        if (i == 0) {
            time = null;
            timeNorm = null;
            return;
        }
        time = cale.getTime();
        calendar = cale;
    }

    public int get(int i) {
        return tunit[i];
    }

    public void addHour(int hour) {
        calendar.add(Calendar.HOUR, 12);
        time = calendar.getTime();
        timeNorm = timeNorm.replaceFirst("\\d\\d?时", H_FMT.format(time));
    }

    public void addDate(int d) {
        calendar.add(Calendar.DATE, d);
        time = calendar.getTime();
        timeNorm = timeNorm.replaceFirst("\\d\\d?日", D_FMT.format(time));
    }

    public void addMonth(int m) {
        calendar.add(Calendar.MONTH, m);
        time = calendar.getTime();
        timeNorm = timeNorm.replaceFirst("\\d\\d?月", M_FMT.format(time));
    }

    public void addYear(int y) {
        calendar.add(Calendar.YEAR, y);
        time = calendar.getTime();
        timeNorm = timeNorm.replaceFirst("\\d\\d\\d\\d年", Y_FMT.format(time));
    }

    public boolean noTime() {
        return originalTunit[3] == -1;
    }

    public boolean noDate() {
        return originalTunit[2] == -1;
    }

    public boolean noMomth() {
        return originalTunit[1] == -1;
    }

    public boolean noYear() {
        return originalTunit[0] == -1;
    }

    public String getTimeNorm() {
        return timeNorm;
    }

    @Override
    public String toString() {
        return timeNorm;
    }

    private TimeUnit(String dateText, Date baseDate) {
        this.timeExpression = dateText;
        this.timeBaseText = sdf.format(baseDate);
        this.autoFill = true;
        this.ignoreIfNoDate = false;
        timeNormallization();
    }

    public static String formateDate(String dateText, Date baseDate) {
        TimeUnit tn = new TimeUnit(dateText, baseDate);
        // TimeUnit tn =new TimeUnit(dateText, baseDate, true, false);
        if (tn.timeNorm == null || tn.timeNorm.trim().length() == 0) {
            return dateText;
        }
        return tn.timeNorm;
    }

    public boolean isHalfday() {
        return halfday;
    }

    public void setHalfday(boolean halfday) {
        this.halfday = halfday;
    }

}
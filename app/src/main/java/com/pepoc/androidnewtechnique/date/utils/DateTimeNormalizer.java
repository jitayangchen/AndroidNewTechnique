/******************************************************************************* 
 * Copyright (C) 2012-2015 Microfountain Technology, Inc. All Rights Reserved. 
 * 
 * Unauthorized copying of this file, via any medium is strictly prohibited.   
 * Proprietary and confidential
 * 
 * Last Modified: 2018-9-30 14:30:35
 ******************************************************************************/

package com.pepoc.androidnewtechnique.date.utils;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 时间格式转换器
 * @author javiylee
 */
public class DateTimeNormalizer {

    public static final String patternStr = "([前昨今明后次][天|日]?[早晚][晨上间]?)"
            + "|((?<!\\d)(?:[1][0-2]|[0]?[1-9])-(?:[0-3][0-9])-\\d{4}(?![\\d:\\-]))"
            + "|((?:[明次下上去]年)?(?<![^周]\\d)(?:\\d{2,4} ?[\\.\\-/\\\\ 年]) ?[底末内前后]? ?(?:(?:[1][0-2]|[0]?[1-9])[ ]?[\\.\\-/\\\\ 月])?份? ?[底末内前后]? ?(?:\\d\\d? ?[\\./\\\\ 号日])?(?<!\\.\\d\\d?-\\d\\d?\\.)[ T]*(?:\\d\\d?(?: ?(?::|[点分秒刻][钟半]?|小?时|min|[hH])(?: ?\\d\\d?[分秒]?)?){0,2})?(?![年月日时分秒.:\\d]))"
            + "|((?:[次下上去]年)?(?<![^周]\\d)(?:(?:[1][0-2]|[0]?[1-9]) ?[\\.\\-/\\\\ 月])份? ?[底末内前后]? ?(?:\\d\\d? ?[\\./\\\\ 号日])?[ \\tT]*(?:\\d\\d?(?: ?(?::|[点分秒刻][钟半]?|小?时|min|[hH])(?: ?\\d\\d?[分秒]?)?){0,2})?(?![年月日时分秒.:\\d]))"
            + "|((?<![^周]\\d)(?:\\d\\d? ?[\\./\\ 号日])[ \\tT]*(?:(?:\\d\\d? ?[:点分]\\d{0,2}){0,3}(?:[秒分]|:\\d\\d?)?)?)"
            + "|(\\d\\d?(?: ?(?::|[点分秒刻][钟半]?|小?时|min|[hH])(?: ?\\d\\d?[分秒]?)?){1,2})" + "|((?<![^周]\\d)\\d\\d?(?:[点分秒刻][钟半]?|小?时|min|[hH]))"
            + "|([本今当该下上次]+[日月年][底末])" + "|((?:上+|[这今该本]|下+)个?(?:周|星期)[1-7]?)" + "|([这上下]1?个?[年月])" + "|(大+[前后][年月天日])" + "|(早晨|[早晚]上?|am|AM|pm|PM)"
            + "|([当该本去次][天日周月年])" + "|([今明昨前后次][天日])" + "|([上下中]午|晚上|晚间|白天|傍晚|凌[晨时点]|午后)" + "|((\\d{4,20})[年月日号时分秒]?份?[底末]?)"
            + "|((?<=[月号日分秒]份?|\\d\\d?)[之以]?[前后内底末])" + "|((?:周|星期)[1-7](?!\\d\\d?[月\\-./日号]))" + "|((?<=\\d)/\\d\\d(?:\\d\\d)?(?!\\d))";

    // "([前昨今明后][天|日]?[早晚][晨上间])"
    // + "|(\\d+个?月)"
    // + "|(\\d+年)"
    // + "|(\\d+个?半?(?:小时|钟头))"
    // + "|(\\d+个?半?(?:小时|钟头|[hH]))"
    // + "|(半个?(?:小时|钟头))"
    // + "|(\\d+(?:分|分钟|min))"
    // + "|([13]刻钟)"
    // + "|(\\d+秒钟?)"
    // + "|((?:上+|这|本|下+)(?:周|星期)[1-7]?)"
    // + "|(\\d+(周|星期))"
    // + "|((下下?个)?(?:周|星期)[1-7])"
    // + "|((?:早|晚)?(?:[0-2]?[0-9](点|时)半)(?:am|AM|pm|PM)?)"
    // + "|((?:早|晚)?(?:\\d+[:：]\\d+(?:[:：]\\d+)*)\\s*(?:am|AM|pm|PM)?)"
    // + "|((?:早|晚)?([0-2]?[0-9](点|时)[13一三]刻)(am|AM|pm|PM)?)"
    // + "|((?:早|晚)?(\\d+[时点](\\d+)?分?(\\d+秒?)?)\\s*(?:am|AM|pm|PM)?)"
    // + "|(大+(前|后)[天年])"
    // +
    // "|([0-9]?[0-9]?[0-9]{2}\\.((10)|(11)|(12)|([1-9]))\\.((?<!\\\\d))([0-3][0-9]|[1-9]))"
    // + "|([这上下]+个月)"
    // +
    // "|((\\d+)点)|(今年([零一二三四五六七八九十]+|\\d+))|(\\d+[:：]\\d+(分|))|((\\d+):(\\d+))|(\\d+/\\d+(/\\d+)?)"
    // + "|(([0-3][0-9]|[1-9])(日|号))|(([0-3][0-9]|[1-9])[日号])"
    // + "|((\\d+分)+(\\d+秒)?)"
    // + "|([次本后昨新后明今去前上下][一二三四五六七八九十1-9]?[年月日天周])|([当该][天日周月年])"
    // +
    // "|[上下]午|晚上|晚间|白天|傍晚|凌晨|午后|((\\d+点)+(\\d+分)?(\\d+秒)?)|(本月(\\d+))|(第(\\d+)天)|((\\d+)年(\\d+)月)"
    // + "|(今年(\\d+)月(\\d+)日)|((\\d+)月(\\d+)日[上下]午(\\d+)时)"
    // +
    // "|([T\\s]+\\d+:\\d+(:\\d+)?)|(\\d+/\\d+/\\d+:\\d+:\\d+.\\d+)|(\\?\\?\\?\\?-\\?\\?-\\?\\?\\d+:\\d+:\\d+)"
    // +
    // "|(\\d+-\\d+-\\d+[T\\s]+\\d+:\\d+:\\d+)|(\\d+/\\d+/\\d+ +\\d+:\\d+:\\d+.\\d+)|(\\d+-\\d+(-\\d+)?|[0-9]{8})"
    // + "|(\\d{4,20})"

    private static final Pattern pattern = Pattern.compile(patternStr, Pattern.CASE_INSENSITIVE);

    /**
     * 返回时间字符串（yyyy年M月d日H时m分s秒）（与dateString有关，如果原文没有明确的时间字段，则不会显示，如4-28
     * 号只会返回2015年4月28号）
     * @param dateString
     *            时间字符串
     * @return
     */
    public static String convertDateToString(String dateString) {
        return convertDateToString(dateString, new Date(), true, false);
    }

    /**
     * 返回时间字符串（yyyy年M月d日H时m分s秒）（与dateString有关，如果原文没有明确的时间字段，则不会显示，如4-28
     * 号只会返回2015年4月28号）
     * @param dateString
     *            时间字符串
     * @param timeBase
     *            基准时间
     * @param autoFill
     *            自动填充 如果为false，4-28号只会返回4月28号不会补充年份
     * @param ignoreIfNoDate
     *            若为true:当没有日期，则返回null(如不识别20:10分，直接返回null)
     * @return
     */
    public static String convertDateToString(String dateString, Date timeBase, boolean autoFill, boolean ignoreIfNoDate) {
        return convertDateToString(dateString, timeBase, autoFill, ignoreIfNoDate, false);
    }

    /**
     * 返回时间字符串（yyyy年M月d日H时m分s秒）（与dateString有关，如果原文没有明确的时间字段，则不会显示，如4-28
     * 号只会返回2015年4月28号）
     * @param dateString
     *            时间字符串
     * @param timeBase
     *            基准时间
     * @param autoFill
     *            自动填充 如果为false，4-28号只会返回4月28号不会补充年份
     * @param ignoreIfNoDate
     *            若为true:当没有日期，则返回null(如不识别20:10分，直接返回null)
     * @param maxThanBaseTime
     *            若为true，
     *            则提取的时间如果没有年份，则判断月份是否小于当前月，小于就加一年；没有年月，则判断日期是否小于当天，如果是就加一个月
     *            (如是当前月是12月则+1是1月份，年份也需+1）；如果没有年月日，则判断时间是否小于当前时间，如果是就加一天
     * @return
     */
    public static String convertDateToString(String dateString, Date timeBase, boolean autoFill, boolean ignoreIfNoDate, boolean maxThanBaseTime) {

        return convertDateToString(dateString, timeBase, autoFill, ignoreIfNoDate, maxThanBaseTime, TimeUnit.AL_FULL);
    }

    /**
     * 返回时间字符串（yyyy年M月d日H时m分s秒）（与dateString有关，如果原文没有明确的时间字段，则不会显示，如4-28
     * 号只会返回2015年4月28号）
     * @param dateString
     *            时间字符串
     * @param timeBase
     *            基准时间
     * @param autoFill
     *            自动填充 如果为false，4-28号只会返回4月28号不会补充年份
     * @param ignoreIfNoDate
     *            若为true:当没有日期，则返回null(如不识别20:10分，直接返回null)
     * @param maxThanBaseTime
     *            若为true， 则提取的时间如果没有年份，则判断月份是否小于当前月，小于就加一年；<br>
     *            没有年月，则判断日期是否小于当天 ，如果是就加一个月<br>
     *            (如是当前月是12月则+1是1月份，年份也需+1）；如果没有年月日，则判断时间是否小于当前时间，如果是就加一天<br>
     *            （上述规则还依赖下面的ignoreLevel参数）
     * @param ignoreLevel
     *            补全要求的等级<br>
     *            3:要有时间才补全，例如：15:00 -》 2016-11-11 15:00<br>
     *            2:要有日期才补全，例如：11号 15:00 -》 2016-11-11 15:00<br>
     *            1:要有月份才补全，例如：11-11 15:00 -》 2016-11-11 15:00<br>
     * @return
     */
    public static String convertDateToString(String dateString, Date timeBase, boolean autoFill, boolean ignoreIfNoDate, boolean maxThanBaseTime,
                                             int ignoreLevel) {
        TimeUnit[] tu = parse(dateString, timeBase, autoFill, ignoreIfNoDate, maxThanBaseTime, ignoreLevel);
        if (tu != null && tu.length > 0) {
            if (tu[0] == null)
                return null;
            return tu[0].toString();
        }
        return null;
    }

    /**
     * 返回时间字符串数组（yyyy年M月d日H时m分s秒）（与dateString有关，如果原文没有明确的时间字段，则不会显示，如4-28
     * 号只会返回2015年4月28号）
     * @param dateString
     *            时间字符串
     * @return
     */
    public static String[] convertDateToStrings(String dateString) {
        return convertDateToStrings(dateString, new Date(), true, false);
    }

    /**
     * 返回时间字符串数组（yyyy年M月d日H时m分s秒）（与dateString有关，如果原文没有明确的字段，则不会显示，如4-28
     * 号只会返回4月28号）
     * @param dateString
     *            时间字符串
     * @param timeBase
     *            基准时间
     * @param autoFill
     *            自动填充 如果为false，4-28号只会返回4月28号不会补充年份
     * @param ignoreIfNoDate
     *            若为true:当没有日期，则返回null(如不识别20:10分，直接返回null)
     * @return
     */
    public static String[] convertDateToStrings(String dateString, Date timeBase, boolean autoFill, boolean ignoreIfNoDate) {
        return convertDateToStrings(dateString, timeBase, autoFill, ignoreIfNoDate, false);
    }

    /**
     * 返回时间字符串数组（yyyy年M月d日H时m分s秒）（与dateString有关，如果原文没有明确的字段，则不会显示，如4-28
     * 号只会返回4月28号）
     * @param dateString
     *            时间字符串
     * @param timeBase
     *            基准时间
     * @param autoFill
     *            自动填充 如果为false，4-28号只会返回4月28号不会补充年份
     * @param ignoreIfNoDate
     *            若为true:当没有日期，则返回null(如不识别20:10分，直接返回null)
     * @param maxThanBaseTime
     *            若为true，
     *            则提取的时间如果没有年份，则判断月份是否小于当前月，小于就加一年；没有年月，则判断日期是否小于当天，如果是就加一个月
     *            (如是当前月是12月则+1是1月份，年份也需+1）；如果没有年月日，则判断时间是否小于当前时间，如果是就加一天
     * @return
     */
    public static String[] convertDateToStrings(String dateString, Date timeBase, boolean autoFill, boolean ignoreIfNoDate, boolean maxThanBaseTime) {
        return convertDateToStrings(dateString, timeBase, autoFill, ignoreIfNoDate, maxThanBaseTime, TimeUnit.AL_FULL);
    }

    /**
     * 返回时间字符串数组（yyyy年M月d日H时m分s秒）（与dateString有关，如果原文没有明确的字段，则不会显示，如4-28
     * 号只会返回4月28号）
     * @param dateString
     *            时间字符串
     * @param timeBase
     *            基准时间
     * @param autoFill
     *            自动填充 如果为false，4-28号只会返回4月28号不会补充年份
     * @param ignoreIfNoDate
     *            若为true:当没有日期，则返回null(如不识别20:10分，直接返回null)
     * @param maxThanBaseTime
     *            若为true， 则提取的时间如果没有年份，则判断月份是否小于当前月，小于就加一年；<br>
     *            没有年月，则判断日期是否小于当天 ，如果是就加一个月<br>
     *            (如是当前月是12月则+1是1月份，年份也需+1）；如果没有年月日，则判断时间是否小于当前时间，如果是就加一天<br>
     *            （上述规则还依赖下面的ignoreLevel参数）
     * @param ignoreLevel
     *            补全要求的等级<br>
     *            3:只要有时间就补全，例如：15:00 -》 2016-11-11 15:00<br>
     *            2:只要有日期就补全，例如：11号 15:00 -》 2016-11-11 15:00<br>
     *            1:只要有月份就补全，例如：11-11 15:00 -》 2016-11-11 15:00<br>
     * @return
     */
    public static String[] convertDateToStrings(String dateString, Date timeBase, boolean autoFill, boolean ignoreIfNoDate, boolean maxThanBaseTime,
                                                int ignoreLevel) {
        TimeUnit[] tu = parse(dateString, timeBase, autoFill, ignoreIfNoDate, maxThanBaseTime, ignoreLevel);
        if (tu != null && tu.length > 0) {
            String[] dates = new String[tu.length];
            for (int i = 0; i < tu.length; i++) {
                TimeUnit t = tu[i];
                if (t == null)
                    return null;
                dates[i] = t.toString();
            }
            return dates;
        }
        return null;
    }

    /**
     * 返回时间毫秒数（如果dateString没有年月日等，将基于当前时间）
     * @param dateString
     *            时间字符串
     * @return
     */
    public static long convertDateLong(String dateString) {
        return convertDateLong(dateString, new Date(), true, false);
    }

    /**
     * 返回时间毫秒数（如果dateString没有年月日等，将基于timeBase）
     * @param dateString
     *            时间字符串
     * @param timeBase
     *            基准时间
     * @param autoFill
     *            自动填充 如果为false，4-28号会用1970年补充年份，如果为true则根据timeBase填充年份
     * @param ignoreIfNoDate
     *            若为true:当没有日期，则返回null(如不识别20:10分，直接返回null)
     * @return
     */
    public static long convertDateLong(String dateString, Date timeBase, boolean autoFill, boolean ignoreIfNoDate) {
        return convertDateLong(dateString, timeBase, autoFill, ignoreIfNoDate, false);
    }

    /**
     * 返回时间毫秒数（如果dateString没有年月日等，将基于timeBase）
     * @param dateString
     *            时间字符串
     * @param timeBase
     *            基准时间
     * @param autoFill
     *            自动填充 如果为false，4-28号会用1970年补充年份，如果为true则根据timeBase填充年份
     * @param ignoreIfNoDate
     *            若为true:当没有日期，则返回null(如不识别20:10分，直接返回null)
     * @param maxThanBaseTime
     *            若为true，
     *            则提取的时间如果没有年份，则判断月份是否小于当前月，小于就加一年；没有年月，则判断日期是否小于当天，如果是就加一个月
     *            (如是当前月是12月则+1是1月份，年份也需+1）；如果没有年月日，则判断时间是否小于当前时间，如果是就加一天
     * @return
     */
    public static long convertDateLong(String dateString, Date timeBase, boolean autoFill, boolean ignoreIfNoDate, boolean maxThanBaseTime) {
        return convertDateLong(dateString, timeBase, autoFill, ignoreIfNoDate, maxThanBaseTime, TimeUnit.AL_FULL);
    }

    /**
     * 返回时间毫秒数（如果dateString没有年月日等，将基于timeBase）
     * @param dateString
     *            时间字符串
     * @param timeBase
     *            基准时间
     * @param autoFill
     *            自动填充 如果为false，4-28号会用1970年补充年份，如果为true则根据timeBase填充年份
     * @param ignoreIfNoDate
     *            若为true:当没有日期，则返回null(如不识别20:10分，直接返回null)
     * @param maxThanBaseTime
     *            若为true， 则提取的时间如果没有年份，则判断月份是否小于当前月，小于就加一年；<br>
     *            没有年月，则判断日期是否小于当天 ，如果是就加一个月<br>
     *            (如是当前月是12月则+1是1月份，年份也需+1）；如果没有年月日，则判断时间是否小于当前时间，如果是就加一天<br>
     *            （上述规则还依赖下面的ignoreLevel参数）
     * @param ignoreLevel
     *            补全要求的等级<br>
     *            3:只要有时间就补全，例如：15:00 -》 2016-11-11 15:00<br>
     *            2:只要有日期就补全，例如：11号 15:00 -》 2016-11-11 15:00<br>
     *            1:只要有月份就补全，例如：11-11 15:00 -》 2016-11-11 15:00<br>
     * @return
     */
    public static long convertDateLong(String dateString, Date timeBase, boolean autoFill, boolean ignoreIfNoDate, boolean maxThanBaseTime,
                                       int ignoreLevel) {
        TimeUnit[] tu = parse(dateString, timeBase, autoFill, ignoreIfNoDate, maxThanBaseTime, ignoreLevel);
        if (tu != null && tu.length > 0) {
            return tu[0] == null ? -1 : tu[0].getTime().getTime();
        }
        return -1;
    }

    /**
     * 返回时间毫秒数数组（如果dateString没有年月日等，将基于当前时间）
     * @param dateString
     *            时间字符串
     * @return
     */
    public static long[] convertDateLongs(String dateString) {
        return convertDateLongs(dateString, new Date(), true, false);
    }

    /**
     * 返回时间毫秒数数组（如果dateString没有年月日等，将基于timeBase）
     * @param dateString
     *            时间字符串
     * @param timeBase
     *            基准时间
     * @param autoFill
     *            自动填充 如果为false，4-28号会用1970年补充年份，如果为true则根据timeBase填充年份
     * @param ignoreIfNoDate
     *            若为true:当没有日期，则返回null(如不识别20:10分，直接返回null)
     * @return
     */
    public static long[] convertDateLongs(String dateString, Date timeBase, boolean autoFill, boolean ignoreIfNoDate) {
        return convertDateLongs(dateString, timeBase, autoFill, ignoreIfNoDate, false);
    }

    /**
     * 返回时间毫秒数数组（如果dateString没有年月日等，将基于timeBase）
     * @param dateString
     *            时间字符串
     * @param timeBase
     *            基准时间
     * @param autoFill
     *            自动填充 如果为false，4-28号会用1970年补充年份，如果为true则根据timeBase填充年份
     * @param ignoreIfNoDate
     *            若为true:当没有日期，则返回null(如不识别20:10分，直接返回null)
     * @param maxThanBaseTime
     *            若为true，
     *            则提取的时间如果没有年份，则判断月份是否小于当前月，小于就加一年；没有年月，则判断日期是否小于当天，如果是就加一个月
     *            (如是当前月是12月则+1是1月份，年份也需+1）；如果没有年月日，则判断时间是否小于当前时间，如果是就加一天
     * @return
     */
    public static long[] convertDateLongs(String dateString, Date timeBase, boolean autoFill, boolean ignoreIfNoDate, boolean maxThanBaseTime) {
        return convertDateLongs(dateString, timeBase, autoFill, ignoreIfNoDate, maxThanBaseTime, TimeUnit.AL_FULL);
    }

    /**
     * 返回时间毫秒数数组（如果dateString没有年月日等，将基于timeBase）
     * @param dateString
     *            时间字符串
     * @param timeBase
     *            基准时间
     * @param autoFill
     *            自动填充 如果为false，4-28号会用1970年补充年份，如果为true则根据timeBase填充年份
     * @param ignoreIfNoDate
     *            若为true:当没有日期，则返回null(如不识别20:10分，直接返回null)
     * @param maxThanBaseTime
     *            若为true， 则提取的时间如果没有年份，则判断月份是否小于当前月，小于就加一年；<br>
     *            没有年月，则判断日期是否小于当天 ，如果是就加一个月<br>
     *            (如是当前月是12月则+1是1月份，年份也需+1）；如果没有年月日，则判断时间是否小于当前时间，如果是就加一天<br>
     *            （上述规则还依赖下面的ignoreLevel参数）
     * @param ignoreLevel
     *            补全要求的等级<br>
     *            3:只要有时间就补全，例如：15:00 -》 2016-11-11 15:00<br>
     *            2:只要有日期就补全，例如：11号 15:00 -》 2016-11-11 15:00<br>
     *            1:只要有月份就补全，例如：11-11 15:00 -》 2016-11-11 15:00<br>
     * @return
     */
    public static long[] convertDateLongs(String dateString, Date timeBase, boolean autoFill, boolean ignoreIfNoDate, boolean maxThanBaseTime,
                                          int ignoreLevel) {
        TimeUnit[] tu = parse(dateString, timeBase, autoFill, ignoreIfNoDate, maxThanBaseTime, ignoreLevel);
        if (tu != null && tu.length > 0) {
            long[] dates = new long[tu.length];
            for (int i = 0; i < tu.length; i++) {
                TimeUnit t = tu[i];
                if (t == null)
                    return null;
                dates[i] = t.getTime().getTime();
            }
            return dates;
        }
        return null;
    }

    /**
     * 返回时间（如果dateString没有年月日等，将基于当前时间）
     * @param dateString
     *            时间字符串
     * @return
     */
    public static Date convertDate(String dateString) {
        return convertDate(dateString, new Date(), true, false);
    }

    /**
     * 返回时间（如果dateString没有年月日等，将基于timeBase）
     * @param dateString
     *            时间字符串
     * @param timeBase
     *            基准时间
     * @param autoFill
     *            自动填充 如果为false，4-28号会用1970年补充年份，如果为true则根据timeBase填充年份
     * @param ignoreIfNoDate
     *            若为true:当没有日期，则返回null(如不识别20:10分，直接返回null)
     * @return
     */
    public static Date convertDate(String dateString, Date timeBase, boolean autoFill, boolean ignoreIfNoDate) {
        return convertDate(dateString, timeBase, autoFill, ignoreIfNoDate, false);
    }

    /**
     * 返回时间（如果dateString没有年月日等，将基于timeBase）
     * @param dateString
     *            时间字符串
     * @param timeBase
     *            基准时间
     * @param autoFill
     *            自动填充 如果为false，4-28号会用1970年补充年份，如果为true则根据timeBase填充年份
     * @param ignoreIfNoDate
     *            若为true:当没有日期，则返回null(如不识别20:10分，直接返回null)
     * @param maxThanBaseTime
     *            若为true，
     *            则提取的时间如果没有年份，则判断月份是否小于当前月，小于就加一年；没有年月，则判断日期是否小于当天，如果是就加一个月
     *            (如是当前月是12月则+1是1月份，年份也需+1）；如果没有年月日，则判断时间是否小于当前时间，如果是就加一天
     * @return
     */
    public static Date convertDate(String dateString, Date timeBase, boolean autoFill, boolean ignoreIfNoDate, boolean maxThanBaseTime) {
        return convertDate(dateString, timeBase, autoFill, ignoreIfNoDate, maxThanBaseTime, TimeUnit.AL_FULL);
    }

    /**
     * 返回时间（如果dateString没有年月日等，将基于timeBase）
     * @param dateString
     *            时间字符串
     * @param timeBase
     *            基准时间
     * @param autoFill
     *            自动填充 如果为false，4-28号会用1970年补充年份，如果为true则根据timeBase填充年份
     * @param ignoreIfNoDate
     *            若为true:当没有日期，则返回null(如不识别20:10分，直接返回null)
     * @param maxThanBaseTime
     *            若为true， 则提取的时间如果没有年份，则判断月份是否小于当前月，小于就加一年；<br>
     *            没有年月，则判断日期是否小于当天 ，如果是就加一个月<br>
     *            (如是当前月是12月则+1是1月份，年份也需+1）；如果没有年月日，则判断时间是否小于当前时间，如果是就加一天<br>
     *            （上述规则还依赖下面的ignoreLevel参数）
     * @param ignoreLevel
     *            补全要求的等级<br>
     *            3:只要有时间就补全，例如：15:00 -》 2016-11-11 15:00<br>
     *            2:只要有日期就补全，例如：11号 15:00 -》 2016-11-11 15:00<br>
     *            1:只要有月份就补全，例如：11-11 15:00 -》 2016-11-11 15:00<br>
     * @return
     */
    public static Date convertDate(String dateString, Date timeBase, boolean autoFill, boolean ignoreIfNoDate, boolean maxThanBaseTime,
                                   int ignoreLevel) {
        TimeUnit[] tu = parse(dateString, timeBase, autoFill, ignoreIfNoDate, maxThanBaseTime, ignoreLevel);
        if (tu != null && tu.length > 0) {
            return tu[0] == null ? null : tu[0].getTime();
        }
        return null;
    }

    /**
     * 返回时间数组（如果dateString没有年月日等，将基于当前时间）
     * @param dateString
     *            时间字符串
     * @return
     */
    public static Date[] convertDates(String dateString) {
        return convertDates(dateString, new Date(), true, false);
    }

    /**
     * 返回时间数组（如果dateString没有年月日等，将基于timeBase）
     * @param dateString
     *            时间字符串
     * @param timeBase
     *            基准时间
     * @param autoFill
     *            自动填充 如果为false，4-28号会用1970年补充年份，如果为true则根据timeBase填充年份
     * @param ignoreIfNoDate
     *            若为true:当没有日期，则返回null(如不识别20:10分，直接返回null)
     * @return
     */
    public static Date[] convertDates(String dateString, Date timeBase, boolean autoFill, boolean ignoreIfNoDate) {
        return convertDates(dateString, timeBase, autoFill, ignoreIfNoDate, false);
    }

    /**
     * 返回时间数组（如果dateString没有年月日等，将基于timeBase）
     * @param dateString
     *            时间字符串
     * @param timeBase
     *            基准时间
     * @param autoFill
     *            自动填充 如果为false，4-28号会用1970年补充年份，如果为true则根据timeBase填充年份
     * @param ignoreIfNoDate
     *            若为true:当没有日期，则返回null(如不识别20:10分，直接返回null)
     * @param maxThanBaseTime
     *            若为true，
     *            则提取的时间如果没有年份，则判断月份是否小于当前月，小于就加一年；没有年月，则判断日期是否小于当天，如果是就加一个月
     *            (如是当前月是12月则+1是1月份，年份也需+1）；如果没有年月日，则判断时间是否小于当前时间，如果是就加一天
     * @return
     */
    public static Date[] convertDates(String dateString, Date timeBase, boolean autoFill, boolean ignoreIfNoDate, boolean maxThanBaseTime) {
        return convertDates(dateString, timeBase, autoFill, ignoreIfNoDate, maxThanBaseTime, TimeUnit.AL_FULL);
    }

    /**
     * 返回时间数组（如果dateString没有年月日等，将基于timeBase）
     * @param dateString
     *            时间字符串
     * @param timeBase
     *            基准时间
     * @param autoFill
     *            自动填充 如果为false，4-28号会用1970年补充年份，如果为true则根据timeBase填充年份
     * @param ignoreIfNoDate
     *            若为true:当没有日期，则返回null(如不识别20:10分，直接返回null)
     * @param maxThanBaseTime
     *            若为true， 则提取的时间如果没有年份，则判断月份是否小于当前月，小于就加一年；<br>
     *            没有年月，则判断日期是否小于当天 ，如果是就加一个月<br>
     *            (如是当前月是12月则+1是1月份，年份也需+1）；如果没有年月日，则判断时间是否小于当前时间，如果是就加一天<br>
     *            （上述规则还依赖下面的ignoreLevel参数）
     * @param ignoreLevel
     *            补全要求的等级<br>
     *            3:只要有时间就补全，例如：15:00 -》 2016-11-11 15:00<br>
     *            2:只要有日期就补全，例如：11号 15:00 -》 2016-11-11 15:00<br>
     *            1:只要有月份就补全，例如：11-11 15:00 -》 2016-11-11 15:00<br>
     * @return
     */
    public static Date[] convertDates(String dateString, Date timeBase, boolean autoFill, boolean ignoreIfNoDate, boolean maxThanBaseTime,
                                      int ignoreLevel) {
        TimeUnit[] tu = parse(dateString, timeBase, autoFill, ignoreIfNoDate, maxThanBaseTime, ignoreLevel);
        if (tu != null && tu.length > 0) {
            Date[] dates = new Date[tu.length];
            for (int i = 0; i < tu.length; i++) {
                TimeUnit t = tu[i];
                if (t == null)
                    return null;
                dates[i] = t.getTime();
            }
            return dates;
        }
        return null;
    }

    /**
     * @param text
     *            时间字符串
     * @return
     */
    public static TimeUnit[] parse(String text) {
        return parse(text, new Date(), true, false, false);
    }

    /**
     * @param text
     *            时间字符串
     * @param timeBase
     *            基准时间
     * @param autoFill
     *            自动填充年月日
     * @param ignoreIfNoDate
     *            若为true:当没有日期，则返回null(如不识别20:10分，直接返回null)
     * @return
     */
    public static TimeUnit[] parse(String text, Date timeBase, boolean autoFill, boolean ignoreIfNoDate) {
        return parse(text, timeBase, autoFill, ignoreIfNoDate, false);
    }

    /**
     * @param text
     *            时间字符串
     * @param timeBase
     *            基准时间
     * @param autoFill
     *            自动填充年月日
     * @param ignoreIfNoDate
     *            若为true:当没有日期，则返回null(如不识别20:10分，直接返回null)
     * @param maxThanBaseTime
     *            若为true，
     *            则提取的时间如果没有年份，则判断月份是否小于当前月，小于就加一年；没有年月，则判断日期是否小于当天，如果是就加一个月
     *            (如是当前月是12月则+1是1月份，年份也需+1）；如果没有年月日，则判断时间是否小于当前时间，如果是就加一天
     * @return
     */
    public static TimeUnit[] parse(String text, Date timeBase, boolean autoFill, boolean ignoreIfNoDate, boolean maxThanBaseTime) {
        return parse(text, timeBase, autoFill, ignoreIfNoDate, maxThanBaseTime, TimeUnit.AL_FULL);
    }

    /**
     * @param text
     *            时间字符串
     * @param timeBase
     *            基准时间
     * @param autoFill
     *            自动填充年月日
     * @param ignoreIfNoDate
     *            若为true:当没有日期，则返回null(如不识别20:10分，直接返回null)
     * @param maxThanBaseTime
     *            若为true，
     *            则提取的时间如果没有年份，则判断月份是否小于当前月，小于就加一年；没有年月，则判断日期是否小于当天，如果是就加一个月
     *            (如是当前月是12月则+1是1月份，年份也需+1）；如果没有年月日，则判断时间是否小于当前时间，如果是就加一天
     * @param ignoreLevel
     *            补全要求的等级<br>
     *            3:只要有时间就补全，例如：15:00 -》 2016-11-11 15:00<br>
     *            2:只要有日期就补全，例如：11号 15:00 -》 2016-11-11 15:00<br>
     *            1:只要有月份就补全，例如：11-11 15:00 -》 2016-11-11 15:00<br>
     * @return
     */
    public static TimeUnit[] parse(String text, Date timeBase, boolean autoFill, boolean ignoreIfNoDate, boolean maxThanBaseTime, int ignoreLevel) {
        text = preprocess(text);

        int startline = -1, endline = -1;

        String[] temp = new String[99];
        int rpointer = 0;
        TimeUnit[] units = null;

        Matcher match = pattern.matcher(text);
        while (match.find()) {
            startline = match.start();
            boolean split = false;
            if (endline == startline || (endline > 0 && startline > 0 && (split = text.charAt(startline - 1) == ' '))) {
                rpointer--;
                temp[rpointer] = temp[rpointer] + (split ? " " : "") + match.group();
            } else {
                temp[rpointer] = match.group();
            }
            endline = match.end();
            rpointer++;
        }
        units = new TimeUnit[rpointer];
        TimeUnit tn = null;
        boolean addedYear = false;
        timeBase = timeBase == null ? new Date() : timeBase;
        TimeUnit btu = null;
        boolean dateOnly = false;
        for (int j = 0, k = 0; j < rpointer; k++, j++) {
            tn = new TimeUnit(temp[j], timeBase, autoFill, j == 0 ? ignoreIfNoDate : (units[0] == null && ignoreIfNoDate),
                    (j > 0 && units[k - 1] != null) ? units[k - 1].toString() : null, ignoreLevel);
            String bt = tn.getTimeNorm();
            if (bt == null) {
                units[k] = null;
            } else {
                BT: if (j > 0 || maxThanBaseTime) {
                    TimeUnit tmp = maxThanBaseTime && (j == 0 || units[k - 1] == null) ? (btu == null ? (btu = parse(TimeUnit.getNormFormater()
                            .format(timeBase))[0]) : btu) : units[k - 1];
                    Date tnt = tmp == null ? (maxThanBaseTime ? timeBase : null) : tmp.getTime();
                    if (tnt == null)
                        break BT;
                    if ((ignoreLevel >= TimeUnit.AL_TIME && tn.noDate())) {
                        if (tnt != null && tn.getTime().before(tnt)) {
                            boolean isHalfday = tmp == null ? false : tmp.isHalfday();
                            if (isHalfday && !tn.isHalfday()) {
                                tn.addHour(12);
                            } else if (!tn.noTime()) {
                                tn.addDate(1);
                            }
                        }
                    } else if (ignoreLevel >= TimeUnit.AL_DATE && (!tn.noDate() && tn.noMomth())) {
                        if (tn.get(2) < tmp.get(2) || (tn.get(2) == tmp.get(2) && !tn.noTime() && tn.getTime().before(tnt))) {
                            tn.addMonth(1);
                        }
                    } else if (ignoreLevel >= TimeUnit.AL_MONTH && (!tn.noMomth() && tn.noYear())) {
                        boolean b1 = tn.get(1) < tmp.get(1);
                        boolean b2 = tn.get(1) == tmp.get(1);
                        boolean b3 = !tn.noDate();
                        boolean b4 = tn.get(2) < tmp.get(2);
                        boolean b5 = tn.get(2) == tmp.get(2);
                        boolean b6 = !tn.noTime();
                        boolean b7 = tn.getTime().before(tnt);
                        if (b1 || (b2 && b3 && (b4 || (b5 && b6 && b7)))) {
                            if (!addedYear) {
                                addedYear = true;
                                tn.addYear(1);
                            } else {
                                tmp.addYear(-1);
                            }
                        } else if (j > 0 && addedYear) {
                            tn.addYear(-1);
                            b1 = tn.get(1) < btu.get(1);
                            b2 = tn.get(1) == btu.get(1);
                            b3 = !tn.noDate();
                            b4 = tn.get(2) < btu.get(2);
                            b5 = tn.get(2) == btu.get(2);
                            b6 = !tn.noTime();
                            b7 = tn.getTime().before(tnt);
                            if (b1 || (b2 && b3 && (b4 || (b5 && b6 && b7)))) {
                                tn.addYear(1);
                            } else {
                                tmp.addYear(-1);
                            }
                        }
                    }
                }
                if (dateOnly && (!tn.noTime() && tn.noDate())) {
                    units[k - 1] = tn;
                    TimeUnit[] ntu = new TimeUnit[units.length - 1];
                    System.arraycopy(units, 0, ntu, 0, k);
                    units = ntu;
                    dateOnly = false;
                    k--;
                } else {
                    if (k < units.length) {
                        units[k] = tn;
                    }
                }
                dateOnly = !tn.noDate() && tn.noTime();
            }
        }
        return units;
    }

    /**
     * 如果结束时间在开始时间之前且月份相等不自动加一年
     * @param text
     *            时间字符串
     * @param timeBase
     *            基准时间
     * @param autoFill
     *            自动填充年月日
     * @param ignoreIfNoDate
     *            若为true:当没有日期，则返回null(如不识别20:10分，直接返回null)
     * @return
     */
    public static TimeUnit[] parseWithNoAutoYearIncrease(String text, Date timeBase, boolean autoFill, boolean ignoreIfNoDate) {
        text = preprocess(text);

        int startline = -1, endline = -1;

        String[] temp = new String[99];
        int rpointer = 0;
        TimeUnit[] units = null;

        Matcher match = pattern.matcher(text);
        while (match.find()) {
            startline = match.start();
            if (endline == startline) {
                rpointer--;
                temp[rpointer] = temp[rpointer] + match.group();
            } else {
                temp[rpointer] = match.group();
            }
            endline = match.end();
            rpointer++;
        }
        units = new TimeUnit[rpointer];
        TimeUnit tn = null;
        for (int j = 0; j < rpointer; j++) {
            tn = new TimeUnit(temp[j], timeBase, autoFill, j == 0 ? ignoreIfNoDate : (units[0] == null && ignoreIfNoDate));
            String bt = tn.getTimeNorm();
            if (bt == null) {
                units[j] = null;
            } else {
                if ((tn.noDate()) && j > 0) {
                    TimeUnit tnt = units[j - 1];
                    if (tn.getTime().before(tnt.getTime())) {
                        tn.addDate(1);
                    }
                } else if ((!tn.noDate() && tn.noMomth()) && j > 0) {
                    TimeUnit tnt = units[j - 1];
                    if (tn.getTime().before(tnt.getTime())) {
                        tn.addMonth(1);
                    }
                } else if ((!tn.noMomth() && tn.noYear()) && j > 0) {
                    TimeUnit tnt = units[j - 1];
                    if (tn.getTime().before(tnt.getTime()) && tn.getTime().getMonth() != tnt.getTime().getMonth()) {
                        tn.addYear(1);
                    }
                }
                units[j] = tn;
            }
        }
        return units;
    }

    private static final String ENG_MONTH = "(?:(?<!\\d)(\\d\\d?)(?:th|st|nd|rd)?(?:[,.\\- ]|\\s+of\\s+)?(jan(?:uary)?|feb(?:ruary)?|mar(?:ch)?|apr(?:il)?|may|june?|july?|aug(?:ust)?|sep(?:tember)?|oct(?:ober)?|nov(?:ember)?|dec(?:ember)?)(?:[,.\\- ](\\d{4}(?!\\d)))?)|(?:(?:(?<!\\d)(\\d{4})[,.\\- ])?(jan(?:uary)?|feb(?:ruary)?|mar(?:ch)?|apr(?:il)?|may|june?|july?|aug(?:ust)?|sep(?:tember)?|oct(?:ober)?|nov(?:ember)?|dec(?:ember)?)[,.\\- ](?:(\\d\\d?)(?:th|st|nd|rd)?(?:[,.\\-] ?(\\d{4}))?(?!\\d)))";

    private static final Pattern ENG_MONTH_PAT = Pattern.compile(ENG_MONTH, Pattern.CASE_INSENSITIVE);

    private static final Pattern DTD_PAT = Pattern.compile("(?<=\\d)。(?=\\d)");

    private static final Pattern SPACE_PAT = Pattern.compile("\\s+");

    private static String preprocess(String text) {
        text = text.replace("：", ":");
        text = PreProcessor.numberTranslator(text);// 大写数字转化
        text = SPACE_PAT.matcher(text).replaceAll(" ");
        text = DTD_PAT.matcher(text).replaceAll(".");
        text = engToCn(text);
        text = PreProcessor.delKeyword(text, "[的\\(\\)\\[\\]\\【】（）]+| +(?!\\d)| +(?=\\d+[年月日号/])|(?<![日号\\d]) +"); // 清理语气助词
        text = text.replace("星期", "周");
        text = text.replace("礼拜", "周");
        text = text.replace("全天", "");
        text = text.replace("周天", "周7");
        text = text.replace("0晨", "0时");

        return text;
    }

    public static String engToCn(String text) {
        Matcher m = ENG_MONTH_PAT.matcher(text);
        boolean fd = m.find();
        if (fd) {
            StringBuffer sb = new StringBuffer();
            do {
                String mon = m.group(5);
                if (mon != null) {
                    mon = getNumMonth(mon);
                    if (m.group(4) != null) {
                        m.appendReplacement(sb, "$4年" + mon + "$6日");
                    } else if (m.group(7) != null) {
                        m.appendReplacement(sb, "$7年" + mon + "$6日");
                    } else {
                        m.appendReplacement(sb, mon + "$6日");
                    }
                } else {
                    mon = m.group(2);
                    mon = getNumMonth(mon);
                    if (m.group(3) == null) {
                        m.appendReplacement(sb, mon + "$1日");
                    } else {
                        m.appendReplacement(sb, "$3年" + mon + "$1日");
                    }
                }
            } while (m.find());
            m.appendTail(sb);
            text = sb.toString();
        }
        return text;
    }

    public static final String[] MONS = new String[] { "jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov", "dec" };

    private static String getNumMonth(String mon) {
        mon = mon.toLowerCase();
        for (int i = 0; i < MONS.length; i++) {
            if (mon.startsWith(MONS[i])) {
                return (i + 1) + "月";
            }
        }
        return null;
    }

    /**
     * 用于会议提醒 15：30 9月12日（周五）15：30 2015年1月09日（今天）19:00 2015年3月18日 15:30
     * 2015年3月18日 (本周六) 15:30 2015年3月18日 星期三 15:30
     * @param dateString
     * @param timeBase
     * @param autoFill
     * @param ignoreIfNoDate
     * @return
     */
    public static String convertDateToString2(String dateString, Date timeBase, boolean autoFill, boolean ignoreIfNoDate) {
        return parse2(dateString, timeBase, autoFill, ignoreIfNoDate);
    }

    /**
     * @param text
     *            时间字符串
     * @param timeBase
     *            基准时间
     * @param autoFill
     *            自动填充年月日
     * @param ignoreIfNoDate
     *            若为true:当没有日期，则返回null(如不识别20:10分，直接返回null)
     * @return
     */
    public static String parse2(String text, Date timeBase, boolean autoFill, boolean ignoreIfNoDate) {
        text = preprocess(text);
        int startline = -1, endline = -1;
        Matcher match = pattern.matcher(text);
        StringBuffer sb = new StringBuffer();
        while (match.find()) {
            startline = match.start();
            if (endline == startline) {
                sb.append(match.group());
            } else {
                sb.append(match.group());
            }
            endline = match.end();
        }
        TimeUnit tn = new TimeUnit(sb.toString(), timeBase, autoFill, ignoreIfNoDate);
        return tn.toString();
    }
}
package com.pepoc.androidnewtechnique.date.utils;

/*******************************************************************************
 * Copyright (C) 2012-2015 Microfountain Technology, Inc. All Rights Reserved.
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential
 *
 * Last Modified: 2017-3-9 16:58:49
 ******************************************************************************/


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串预处理模块，为分析器DateTimeNormalizer提供相应的字符串预处理服务
 */
public class PreProcessor {

    /**
     * 该方法删除一字符串中所有匹配某一规则字串 可用于清理一个字符串中的空白符和语气助词
     *
     * @param target 待处理字符串
     * @param rules  删除规则
     * @return 清理工作完成后的字符串
     */
    public static String delKeyword(String target, String rules) {
        Pattern p = Pattern.compile(rules);
        Matcher m = p.matcher(target);
        StringBuffer sb = new StringBuffer();
        boolean result = m.find();
        while (result) {
            m.appendReplacement(sb, "");
            result = m.find();
        }
        m.appendTail(sb);
        String s = sb.toString();
        // System.out.println("字符串："+target+" 的处理后字符串为：" +sb);
        return s;
    }

    static Pattern p1 = Pattern.compile("[一二两三四五六七八九123456789１２３４５６７８９０]万[一二两三四五六七八九123456789１２３４５６７８９０](?!(千|百|十))");

    static Pattern p2 = Pattern.compile("[一二两三四五六七八九123456789１２３４５６７８９０]千[一二两三四五六七八九123456789１２３４５６７８９０](?!(百|十))");

    static Pattern p3 = Pattern.compile("[一二两三四五六七八九123456789１２３４５６７８９０]百[一二两三四五六七八九123456789１２３４５６７８９０](?!十)");

    static Pattern p4 = Pattern.compile("[零一二两三四五六七八九]");

    static Pattern p5 = Pattern.compile("(?<=(周|星期))[天日]");

    static Pattern p6 = Pattern.compile("(?<!(周|星期))0?[0-9]?十[0-9]?");

    static Pattern p7 = Pattern.compile("0?[1-9]百[0-9]?[0-9]?");

    static Pattern p8 = Pattern.compile("0?[1-9]千[0-9]?[0-9]?[0-9]?");

    static Pattern p9 = Pattern.compile("[0-9]+万[0-9]?[0-9]?[0-9]?[0-9]?");

    /**
     * 该方法可以将字符串中所有的用汉字表示的数字转化为用阿拉伯数字表示的数字 如"这里有一千两百个人，六百零五个来自中国"可以转化为
     * "这里有1200个人，605个来自中国"
     *
     * @param target 待转化的字符串
     * @return 转化完毕后的字符串
     */
    public static String numberTranslator(String target) {
        Matcher m = p1.matcher(target);
        StringBuffer sb = new StringBuffer();
        boolean result = m.find();
        while (result) {
            String group = m.group();
            String[] s = group.split("万");
            int num = 0;
            if (s.length == 2) {
                num += wordToNumber(s[0]) * 10000 + wordToNumber(s[1]) * 1000;
            }
            m.appendReplacement(sb, Integer.toString(num));
            result = m.find();
        }
        m.appendTail(sb);
        target = sb.toString();

        m = p2.matcher(target);
        sb = new StringBuffer();
        result = m.find();
        while (result) {
            String group = m.group();
            String[] s = group.split("千");
            int num = 0;
            if (s.length == 2) {
                num += wordToNumber(s[0]) * 1000 + wordToNumber(s[1]) * 100;
            }
            m.appendReplacement(sb, Integer.toString(num));
            result = m.find();
        }
        m.appendTail(sb);
        target = sb.toString();

        m = p3.matcher(target);
        sb = new StringBuffer();
        result = m.find();
        while (result) {
            String group = m.group();
            String[] s = group.split("百");
            int num = 0;
            if (s.length == 2) {
                num += wordToNumber(s[0]) * 100 + wordToNumber(s[1]) * 10;
            }
            m.appendReplacement(sb, Integer.toString(num));
            result = m.find();
        }
        m.appendTail(sb);
        target = sb.toString();

        m = p4.matcher(target);
        sb = new StringBuffer();
        result = m.find();
        while (result) {
            m.appendReplacement(sb, Integer.toString(wordToNumber(m.group())));
            result = m.find();
        }
        m.appendTail(sb);
        target = sb.toString();

        m = p5.matcher(target);
        sb = new StringBuffer();
        result = m.find();
        while (result) {
            m.appendReplacement(sb, Integer.toString(wordToNumber(m.group())));
            result = m.find();
        }
        m.appendTail(sb);
        target = sb.toString();

        m = p6.matcher(target);
        sb = new StringBuffer();
        result = m.find();
        while (result) {
            String group = m.group();
            String[] s = group.split("十");
            int num = 0;
            if (s.length == 0) {
                num += 10;
            } else if (s.length == 1) {
                int ten = Integer.parseInt(s[0]);
                if (ten == 0) {
                    num += 10;
                } else {
                    num += ten * 10;
                }
            } else if (s.length == 2) {
                if (s[0].equals("")) {
                    num += 10;
                } else {
                    int ten = Integer.parseInt(s[0]);
                    if (ten == 0) {
                        num += 10;
                    } else {
                        num += ten * 10;
                    }
                }
                num += Integer.parseInt(s[1]);
            }
            m.appendReplacement(sb, Integer.toString(num));
            result = m.find();
        }
        m.appendTail(sb);
        target = sb.toString();

        m = p7.matcher(target);
        sb = new StringBuffer();
        result = m.find();
        while (result) {
            String group = m.group();
            String[] s = group.split("百");
            int num = 0;
            if (s.length == 1) {
                int hundred = Integer.parseInt(s[0]);
                num += hundred * 100;
            } else if (s.length == 2) {
                int hundred = Integer.parseInt(s[0]);
                num += hundred * 100;
                num += Integer.parseInt(s[1]);
            }
            m.appendReplacement(sb, Integer.toString(num));
            result = m.find();
        }
        m.appendTail(sb);
        target = sb.toString();

        m = p8.matcher(target);
        sb = new StringBuffer();
        result = m.find();
        while (result) {
            String group = m.group();
            String[] s = group.split("千");
            int num = 0;
            if (s.length == 1) {
                int thousand = Integer.parseInt(s[0]);
                num += thousand * 1000;
            } else if (s.length == 2) {
                int thousand = Integer.parseInt(s[0]);
                num += thousand * 1000;
                num += Integer.parseInt(s[1]);
            }
            m.appendReplacement(sb, Integer.toString(num));
            result = m.find();
        }
        m.appendTail(sb);
        target = sb.toString();

        m = p9.matcher(target);
        sb = new StringBuffer();
        result = m.find();
        while (result) {
            String group = m.group();
            String[] s = group.split("万");
            int num = 0;
            if (s.length == 1) {
                int tenthousand = Integer.parseInt(s[0]);
                num += tenthousand * 10000;
            } else if (s.length == 2) {
                int tenthousand = Integer.parseInt(s[0]);
                num += tenthousand * 10000;
                num += Integer.parseInt(s[1]);
            }
            m.appendReplacement(sb, Integer.toString(num));
            result = m.find();
        }
        m.appendTail(sb);
        target = sb.toString();

        return target;
    }

    /**
     * 方法numberTranslator的辅助方法，可将[零-九]正确翻译为[0-9]
     *
     * @param s 大写数字
     * @return 对应的整形数，如果不是大写数字返回-1
     */
    private static int wordToNumber(String s) {
        if (s.equals("零") || s.equals("0") || s.equals("０")) {
            return 0;
        } else if (s.equals("一") || s.equals("1") || s.equals("１")) {
            return 1;
        } else if (s.equals("二") || s.equals("两") || s.equals("2") || s.equals("２")) {
            return 2;
        } else if (s.equals("三") || s.equals("3") || s.equals("３")) {
            return 3;
        } else if (s.equals("四") || s.equals("4") || s.equals("４")) {
            return 4;
        } else if (s.equals("五") || s.equals("5") || s.equals("５")) {
            return 5;
        } else if (s.equals("六") || s.equals("6") || s.equals("６")) {
            return 6;
        } else if (s.equals("七") || s.equals("天") || s.equals("日") || s.equals("7") || s.equals("７")) {
            return 7;
        } else if (s.equals("八") || s.equals("8") || s.equals("８")) {
            return 8;
        } else if (s.equals("九") || s.equals("9") || s.equals("９")) {
            return 9;
        } else {
            return -1;
        }
    }
}
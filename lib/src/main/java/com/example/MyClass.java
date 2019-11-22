package com.example;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Pattern;

public class MyClass {

    private static int num = 0;

    public static void main(String[] args) {

//        for (int i = 0; i < 10; i++) {
//            System.out.print("i = " + i);
//        }
//        System.out.print("Yangchen");

//        AndroidAdapter.imgAdapter(461, 510);
//        double sqrt = Math.sqrt(320 * 320 + 480 * 480) / 3.2f;
//        Log.i("sqrt === " + sqrt);

//        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(10);

//        Object str = null;
//        String lala = (String) null;
//        Log.i("lala === " + lala);



//        MyThread myThread = new MyThread();
//        myThread.start();
//        try {
//            myThread.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        Log.i("num === " + num);


//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String format = formatter.format(getDayStartTime());
//        Log.i(format);

//        Log.i("== " + ("YYY" == "YYY"));
//        Log.i("== " + "YYY".hashCode());
//        Log.i("== " + ("YYY" == "YYY"));


//        try {
//            String str = null;
//            System.out.print(MyClass.class.getName());
//            if (MyClass.class.getName().equals("com.example.MyClass3328")) {
//                str = null;
//            }
//            System.out.print(str.hashCode());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        try {
//            String str = null;
//            System.out.print(MyClass.class.getName());
//            if (MyClass.class.getName().equals("com.example.MyClass3328")) {
//                str = null;
//            }
//            System.out.print(str.hashCode());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


//        Date date = new Date();
//        Locale.setDefault(Locale.US);
//        DateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm:ss");
//        DateFormat sdf_locale = new SimpleDateFormat("yyyy MMM dd HH:mm:ss",Locale.JAPANESE);
//        System.out.println(sdf.format(date));
//        System.out.println(sdf_locale.format(date));


//        Date d1 = new Date();
//        System.out.println("today is : " + d1.toString());
//        Locale locItalian = new Locale("it", "ch");
//        DateFormat df = DateFormat.getDateInstance(DateFormat.DAY_OF_YEAR_FIELD, locItalian);
//        System.out.println("today is in Italian Language  in Switzerland Format : "
//                        + df.format(d1));


//        System.out.println((int)(8.9f / 3));


//        String a = "hello2";
//        final String b = "hello";
//        String d = "hello";
//        String c = b + 2;
//        String e = d + 2;
//        System.out.println((a == c));
//        System.out.println((a == e));
//        System.out.println((a == b));

//        String s1 = "a";
//        String s2 = "b";
//        String s = s1 + s2;
//        System.out.println(s == a);
//        System.out.println(s.intern() == a);
//        System.out.println(s.intern() == a.intern());

        // true
        // false

//        String a = "a1";
//        String b = "a" + 1;
//        System.out.println(a == b);



//        String time = "2018-04-24 11:46:27";
//        int index = time.lastIndexOf(':');
//        Log.i("time = " + time.substring(0, index));
////        Log.i("time String = " + time.lastIndexOf(":"));
//
//        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        try {
//            Date date = sdf.parse(time);
//            Log.i("" + date.getTime());
//            date = new Date(date.getTime());
//            Log.i(sdf.format(date));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

//        Calendar cal = Calendar.getInstance();
////        cal.set(Calendar.DAY_OF_MONTH, 1);
//        cal.setFirstDayOfWeek(Calendar.MONDAY);
//        cal.set(Calendar.DAY_OF_WEEK, 2);
//        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String day_first = sf.format(cal.getTime());
//        Log.i("day_first = " + day_first);



//        String str = Encrypt("yngcen6881090", "SHA-1");
//        Log.i("str = " + str);

//        for (int i = 0; i <= 100; i++) {
//            int r = (int) Math.sin(3 * Math.PI * i);
//            Log.i("r === " + r);
//        }

//        double r = Math.sin(2 * Math.PI * 1);
//        Log.i("r === " + r);

//        for (float i = 0f; i >= -0.5f; i-=0.1f) {
//            float r = (float) (Math.cos((i + 1) * Math.PI) / 2.0f) + 0.5f;
//            Log.i("i === " + i + " --- r === " + r);
//        }

        funtest(1);



//        Date date = new Date();
//        Log.i(String.valueOf(date.getTime()));
//        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        try {
//            Log.i("LLLLL " + sdf.parse("2019-11-17").getTime());
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        DateFormat sdf_locale = new SimpleDateFormat("yyyy MMM dd HH:mm:ss", Locale.JAPANESE);
//        System.out.println(sdf.format(date));
//        System.out.println(sdf_locale.format(date));

//        Log.i(String.valueOf(DateTimeNormalizer.convertDateLong("2019-11-17")));


//        boolean matchesYMDHM = Pattern.matches("^\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{1,2}$", "2019-01-19 13:30");
//        boolean matchesYMD = Pattern.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$", "2019-01-19");
//        boolean matchesMDHM = Pattern.matches("^\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{1,2}$", "01-19 13:30");
//        boolean matchesMD = Pattern.matches("^\\d{1,2}-\\d{1,2}$", "01-19");
//        boolean matchesHM = Pattern.matches("^\\d{1,2}:\\d{1,2}$", "13:30");
//        Log.i("matchesYMDHM = " + matchesYMDHM);
//        Log.i("matchesYMD = " + matchesYMD);
//        Log.i("matchesMDHM = " + matchesMDHM);
//        Log.i("matchesMD = " + matchesMD);
//        Log.i("matchesHM = " + matchesHM);





//        Calendar calendar = Calendar.getInstance();
//        int year = calendar.get(Calendar.YEAR);
//        int month = calendar.get(Calendar.MONTH);
//        int day = calendar.get(Calendar.DAY_OF_MONTH);
//        int hour = calendar.get(Calendar.HOUR_OF_DAY);
//        int minute = calendar.get(Calendar.MINUTE);
//
//        Log.i(String.format("%d-%d-%d %d:%d", year, month + 1, day, hour, minute));


//        Set<String> sets = Collections.synchronizedSet(new HashSet<>());
//        sets.add("C++");
//        sets.add("Python");
//        sets.add("javascript");
//        sets.add("java");
//
//
//        for (String str : sets) {
//            Log.i(str);
//        }
//
//        sets.remove("java");
//
//        Log.i("-----------------------------------");
//
//        for (String str : sets) {
//            Log.i(str);
//        }


//        String plugins = "Python";
//        String[] split = plugins.split("&");
//        Log.i("length = " + split.length);

//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                if (j == 1) {
//                    break;
//                }
//                Log.i("i = " + i + " - j = " + j);
//            }
//        }

//        Log.i("" + (128 & 128));



//        String E = "android.app.RemoteServiceException: Bad notification posted from package com.qihoo360.mobilesafe: Couldn't expand RemoteViews for: StatusBarNotification(pkg=com.qihoo360.mobilesafe user=UserHandle{0} id=1 tag=null score=20 key=0|com.qihoo360.mobilesafe|1|null|10001: Notification(pri=2 contentView=com.qihoo360.mobilesafe/0x7f03008b vibrate=null sound=null defaults=0x0 flags=0x62 color=0x00000000 vis=PRIVATE)) \n" +
//                "   at android.app.ActivityThread$H.handleMessage(ActivityThread.java:1763) \n" +
//                "   at android.os.Handler.dispatchMessage(Handler.java:111) \n" +
//                "   at android.os.Looper.loop(Looper.java:224) \n" +
//                "   at android.app.ActivityThread.main(ActivityThread.java:5911) \n" +
//                "   at java.lang.reflect.Method.invoke(Native Method) \n" +
//                "   at java.lang.reflect.Method.invoke(Method.java:372) \n" +
//                "   at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:1113) \n" +
//                "   at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:879) ";
//
//        if (E.contains("Couldn't expand RemoteViews for: StatusBarNotification")) {
//            String[] split = E.split("/");
//            String substring = split[1].substring(0, "0x7f03007f".length());
//            Log.i(substring);
//        }
    }

    private static void funtest(int i) {
        try {
            float r = 1 / i;
            exceptionTest(i - 1);
            Log.i("hahaha");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Log.i("finally --- ");
        }

        Log.i("bottom --- ");

    }

    private static void exceptionTest(int i) {
        float r = 1 / i;

        Log.i("hahaha");
    }

    private static String a = new String("ab");
    static class MyThread extends Thread {

        @Override
        public void run() {
            super.run();
            for (int i = 0; i < 5; i++) {
                num++;
//                Log.i("MyThread num === " + num);
            }
        }
    }

    private static String getStr() {
        return null;
    }

    public static Long getDayStartTime() {
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        return todayStart.getTime().getTime();
    }












    public static String Encrypt(String strSrc, String encName) {
        MessageDigest md=null;
        String strDes=null;
        byte[] bt=strSrc.getBytes();
        try {
            if (encName==null||encName.equals("")) {
                encName="MD5";
            }
            md= MessageDigest.getInstance(encName);
            md.update(bt);
            strDes=bytes2Hex(md.digest());  //to HexString
        }
        catch (NoSuchAlgorithmException e) {
            return null;
        }
        return strDes;
    }
    public static String bytes2Hex(byte[]bts) {
        String des="";
        String tmp=null;
        for (int i=0;i<bts.length;i++) {
            tmp=(Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length()==1) {
                des+="0";
            }
            des+=tmp;
        }
        return des;
    }
}

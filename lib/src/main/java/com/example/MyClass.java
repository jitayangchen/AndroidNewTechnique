package com.example;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;

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



        String str = Encrypt("yngcen6881090", "SHA-1");
        Log.i("str = " + str);

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

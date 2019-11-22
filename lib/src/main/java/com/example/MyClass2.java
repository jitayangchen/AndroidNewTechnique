package com.example;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MyClass2 {

    private static int num = 0;

    public static void main(String[] args) {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        SimpleDateFormat sdf = new SimpleDateFormat("HH");
        Date date = new Date(System.currentTimeMillis());
        String dateStr = sdf.format(date);
        Log.i(dateStr);
        try {
            long diff = (System.currentTimeMillis() - (sdf.parse(dateStr).getTime())) / (60 * 60 * 1000);
            Log.i("current hour = " + diff);
        } catch (ParseException e) {
            e.printStackTrace();
        }


//        Calendar cal = Calendar.getInstance();
////        cal.set(Calendar.DAY_OF_MONTH, 1);
////        cal.setFirstDayOfWeek(Calendar.MONDAY);
////        cal.set(Calendar.DAY_OF_WEEK, 2);
//        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String day_first = sf.format(cal.getTime());
//        Log.i("day_first = " + day_first);





//        Calendar calendar = Calendar.getInstance();
//        int year = calendar.get(Calendar.YEAR);
//        int month = calendar.get(Calendar.MONTH);
//        int day = calendar.get(Calendar.DAY_OF_MONTH);
//        int hour = calendar.get(Calendar.HOUR_OF_DAY);
//        int minute = calendar.get(Calendar.MINUTE);
//
//        Log.i(String.format("%d-%d-%d %d:%d", year, month + 1, day, hour, minute));
    }
}

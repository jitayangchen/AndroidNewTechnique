package com.example;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;

public class MyClass3 {

    public static void main(String[] args) {
//        int time = 12 * 60 + 36;
//        Log.i(String.valueOf(12 * 60 + 36));   // 家
//        Log.i(String.valueOf(8 * 60 + 6));   // 公司
//        Log.i(String.valueOf(3 * 60 + 3));   // 其它
//        Log.i(String.valueOf(time % (60)).length() + "");


//        Calendar cal = Calendar.getInstance();
////        cal.set(Calendar.DAY_OF_MONTH, 1);
//        cal.setFirstDayOfWeek(Calendar.MONDAY);
//        cal.set(Calendar.DAY_OF_WEEK, 2);
//        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String day_first = sf.format(cal.getTime());
//        Log.i("day_first = " + day_first);

//        Log.i("70L % 60f = " + (70L % 60f));
//        Log.i("70L / 60f = " + (70L / 65f));
//        Log.i("70L / 60f = " + String.format("%.1f", (70L / 60f)));
//
//        int tmp = (int) (70L / 60f * 10);
//        Log.i("tmp = " + (tmp / 10f));
//        DecimalFormat decimalFormat=new DecimalFormat(".#");
//        Log.i("70L / 60f = " + decimalFormat.format(70L / 60f));





//        int[] weekDays = {1, 2};
//        Calendar cal = Calendar.getInstance();
//        cal.setTimeInMillis(1559404800000L);
//        int week = cal.get(Calendar.DAY_OF_WEEK) - 1;
//        Log.i("week = " + week);


//        Random random = new Random();
//        Log.i("random = " + random.nextInt(2));



        String E = " { when=-1s744ms what=134 target=android.app.ActivityThread$H obj=Bad notification posted from package com.qihoo360.mobilesafe: Couldn't expand RemoteViews for: StatusBarNotification(pkg=com.qihoo360.mobilesafe user=UserHandle{0} id=1 tag=null score=20 key=0|com.qihoo360.mobilesafe|1|null|10000: Notification(pri=2 contentView=com.qihoo360.mobilesafe/0x7f03007e vibrate=null sound=null tick defaults=0x0 flags=0x62 color=0x00000000 vis=PRIVATE)) } \n" +
                "   07-01 13:31:16.121 E/ANR_LOG ( 1971): Current msg <3> = { when=-1s417ms what=113 target=android.app.ActivityThread$H obj=ReceiverData{intent=Intent { act=android.intent.action.SIG_STR flg=0x20000010 cmp=com.qihoo360.mobilesafe/com.qihoo.pushsdk.keepalive.PushWakeUpReceiver (has extras) } packageName=com.qihoo360.mobilesafe resultCode=-1 resultData=null resultExtras=null} } ";

        if (E.contains("Couldn't expand RemoteViews for: StatusBarNotification")) {
            String[] split = E.split("/");
            String substring = split[1].substring(0, "0x7f03007f".length());
            Log.i(substring);
        }

    }
}

package com.pepoc.androidnewtechnique.screenlocker;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import java.util.ArrayList;

public class ScreenStateListener {


    public interface OnStateChangeListener{
        void onScreenOn();
        void onScreenOff();
    }




    public static void register(Context context, OnStateChangeListener listener){
        if(!hasRegisterReceiver){
            IntentFilter filter = new IntentFilter();
            filter.addAction(Intent.ACTION_SCREEN_ON);
            filter.addAction(Intent.ACTION_SCREEN_OFF);
            filter.setPriority(Integer.MAX_VALUE);
            context.getApplicationContext().registerReceiver(receiver, filter);
            hasRegisterReceiver = true;
        }
        if(!listeners.contains(listener))
            listeners.add(listener);
    }
    public static void unRegister(Context context, OnStateChangeListener listener){
        listeners.remove(listener);
        if(listeners.isEmpty() && hasRegisterReceiver){
            context.getApplicationContext().unregisterReceiver(receiver);
            hasRegisterReceiver = false;
        }
    }





    private static boolean hasRegisterReceiver = false;
    private static final ArrayList<OnStateChangeListener > listeners = new ArrayList<>();
    private static final BroadcastReceiver receiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if(listeners.isEmpty())
                return ;
            String action = intent.getAction();
            if(Intent.ACTION_SCREEN_OFF.equals(action)){
                for (OnStateChangeListener listener : listeners)
                    listener.onScreenOff();
            }else if(Intent.ACTION_SCREEN_ON.equals(action)){
                for (OnStateChangeListener listener : listeners)
                    listener.onScreenOn();
            }
        }
    };

}

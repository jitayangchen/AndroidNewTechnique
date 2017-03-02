package com.pepoc.androidnewtechnique.screenlocker;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import java.util.ArrayList;

public class AlarmStateListener {

    public interface OnStateChangeListener{
        void onAlarmChanged();
    }



    public static void register(Context context, OnStateChangeListener listener){
        if(!hasRegisterReceiver){
            IntentFilter filter = new IntentFilter();
            for (String action : actions)
                filter.addAction(action);
            filter.setPriority(Integer.MAX_VALUE);
            context.getApplicationContext().registerReceiver(RECEIVER, filter);
            hasRegisterReceiver = true;
        }
        if(!listeners.contains(listener))
            listeners.add(listener);
    }
    public static void unRegister(Context context, OnStateChangeListener listener){
        listeners.remove(listener);
        if(listeners.isEmpty() && hasRegisterReceiver){
            context.getApplicationContext().unregisterReceiver(RECEIVER);
            hasRegisterReceiver = false;
        }
    }




    private static final ArrayList<String> actions = new ArrayList<>();
    static {
        actions.add("android.intent.action.ALARM_CHANGED");
    }
    private static boolean hasRegisterReceiver = false;
    private static final ArrayList<OnStateChangeListener> listeners = new ArrayList<>();
    private static final BroadcastReceiver RECEIVER = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if(listeners.isEmpty())
                return ;
            String action = intent.getAction();
            if(actions.contains(action))
                for (OnStateChangeListener listener : listeners)
                    listener.onAlarmChanged();
        }
    };


}

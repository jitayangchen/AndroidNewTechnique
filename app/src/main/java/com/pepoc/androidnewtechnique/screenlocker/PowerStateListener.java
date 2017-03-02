package com.pepoc.androidnewtechnique.screenlocker;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;

import java.util.ArrayList;


public class PowerStateListener {


    public interface OnStateChangeListener{
        int STATE_CONNECTED = 1;
        int STATE_DISCONNECTED = 2;
        void onConnectionStateChanged(int nowState);
        void onBatteryStateChanged(boolean isCharging, int percent);
    }


    /**
     * 注册回调
     */
    public static void register(Context context, OnStateChangeListener listener){
        if(!hasRegisterReceiver){
            IntentFilter filter = new IntentFilter();
            filter.addAction(Intent.ACTION_POWER_DISCONNECTED);
            filter.addAction(Intent.ACTION_POWER_CONNECTED);
            filter.addAction(Intent.ACTION_BATTERY_CHANGED);
            filter.setPriority(Integer.MAX_VALUE);
            context.getApplicationContext().registerReceiver(receiver, filter);
            hasRegisterReceiver = true;
        }
        if(!listeners.contains(listener))
            listeners.add(listener);
    }
    /**
     * 取消回调监听
     */
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
            if(Intent.ACTION_POWER_CONNECTED.equals(action)){
                for (OnStateChangeListener listener : listeners)
                    listener.onConnectionStateChanged(OnStateChangeListener.STATE_CONNECTED);
            }else if(Intent.ACTION_POWER_DISCONNECTED.equals(action)){
                for (OnStateChangeListener listener : listeners)
                    listener.onConnectionStateChanged(OnStateChangeListener.STATE_DISCONNECTED);
            }else if(Intent.ACTION_BATTERY_CHANGED.equals(action)){
                int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
                boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING || status == BatteryManager.BATTERY_STATUS_FULL;
                int nowPowerLevel = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
                int maxPowerLevel = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
                float percent = (float)nowPowerLevel / (float)maxPowerLevel;
                for (OnStateChangeListener listener : listeners)
                    listener.onBatteryStateChanged(isCharging, (int)(percent*100));
            }
        }
    };


}

package com.pepoc.androidnewtechnique.screenlocker;


import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class ScreenLockService extends Service {

    public static void startService(Context context){
        context = context.getApplicationContext();
        Intent intent = new Intent(context, ScreenLockService.class);
        intent.setPackage(context.getPackageName());
        context.startService(intent);
    }
    public static void stopService(Context context){
        context = context.getApplicationContext();
        Intent intent = new Intent(context, ScreenLockService.class);
        intent.setPackage(context.getPackageName());
        context.stopService(intent);
    }



    /******************************************************************/


    /* 充电状态改变监听 */
    private final PowerStateListener.OnStateChangeListener powerListener = new PowerStateListener.OnStateChangeListener() {
        public void onConnectionStateChanged(int nowState) {
            if(nowState == STATE_CONNECTED){
                ChargingScreenLock.show();
            }
        }
        public void onBatteryStateChanged(boolean isCharging, int percent) {
        }
    };
    /* 屏幕关闭/开启变化监听 */
    private final ScreenStateListener.OnStateChangeListener screenListener = new ScreenStateListener.OnStateChangeListener() {
        public void onScreenOn() {
            ScreenLocker.show(ScreenLockService.this);
        }
        public void onScreenOff() {

        }
    };
    /* 电话状态改变监听 */
    private final CallStateListener.OnStateChangeListener callListener = new CallStateListener.OnStateChangeListener() {
        public void onStateIDLE() {

        }
        public void onStateOffHook() {

        }
        public void onStateRing() {

        }
    };
    /* 闹铃状态变化监听 */
    private final AlarmStateListener.OnStateChangeListener alarmListener = new AlarmStateListener.OnStateChangeListener() {
        public void onAlarmChanged() {

        }
    };




    @Override
    public void onCreate() {
        super.onCreate();
        ScreenStateListener.register(this, screenListener);
        PowerStateListener.register(this, powerListener);
        CallStateListener.register(this, callListener);
        AlarmStateListener.register(this, alarmListener);
    }
    @Override
    public void onDestroy() {
        ScreenStateListener.unRegister(this, screenListener);
        PowerStateListener.unRegister(this, powerListener);
        CallStateListener.unRegister(this, callListener);
        AlarmStateListener.unRegister(this, alarmListener);
        super.onDestroy();
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}

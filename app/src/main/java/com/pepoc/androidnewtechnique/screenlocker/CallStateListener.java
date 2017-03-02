package com.pepoc.androidnewtechnique.screenlocker;

import android.content.Context;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

import java.util.ArrayList;


public class CallStateListener {

    public interface OnStateChangeListener{
        void onStateIDLE();
        void onStateOffHook();
        void onStateRing();
    }




    public static void register(Context context, CallStateListener.OnStateChangeListener listener){
        if(!hasRegisterReceiver){
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            tm.listen(LISTENER, PhoneStateListener.LISTEN_CALL_STATE);
            hasRegisterReceiver = true;
        }
        if(!listeners.contains(listener))
            listeners.add(listener);
    }
    public static void unRegister(Context context, CallStateListener.OnStateChangeListener listener){
        listeners.remove(listener);
        if(listeners.isEmpty() && hasRegisterReceiver){
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            tm.listen(LISTENER, PhoneStateListener.LISTEN_NONE);
            hasRegisterReceiver = false;
        }
    }




    private static boolean hasRegisterReceiver = false;
    private static final ArrayList<OnStateChangeListener> listeners = new ArrayList<>();
    private static final PhoneStateListener LISTENER = new PhoneStateListener(){
        public void onCallStateChanged(int state, String incomingNumber) {
            switch (state){
                case TelephonyManager.CALL_STATE_IDLE:
                    for (OnStateChangeListener listener : listeners)
                        listener.onStateIDLE();
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    for (OnStateChangeListener listener : listeners)
                        listener.onStateOffHook();
                    break;
                case TelephonyManager.CALL_STATE_RINGING:
                    for (OnStateChangeListener listener : listeners)
                        listener.onStateRing();
                    break;
            }
        }
    };

}

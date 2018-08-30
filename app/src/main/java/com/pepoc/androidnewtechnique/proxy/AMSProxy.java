package com.pepoc.androidnewtechnique.proxy;

import android.content.Intent;
import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class AMSProxy implements InvocationHandler {

    private static final String TAG = "HookAMS";

    private Object iActivityManager;

    public AMSProxy(Object iActivityManager) {
        this.iActivityManager = iActivityManager;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Log.d(TAG, "method name is :" + method.getName() + " args length is :" + args.length + "   args is :" + args);
        if ("startActivity".equals(method.getName())) {
            // 第三个参数是intent
            Intent intent = (Intent)args[2];
            Log.d(TAG, "method name is :"+ method.getName()+"   intent is :"+intent+"   extradata is :"+intent.getStringExtra("DATA"));
        }
        return method.invoke(iActivityManager, args);
    }
}

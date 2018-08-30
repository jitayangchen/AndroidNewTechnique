package com.example.proxy;

import com.example.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicProxy implements InvocationHandler {

    private BuySomething buySomething;
    private String goodsName;

    public DynamicProxy(BuySomething buySomething, String goodsName) {
        this.buySomething = buySomething;
        this.goodsName = goodsName;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        Log.i("invoke Buy before o.getName" + o.getClass().getName());
        if (o instanceof BuySomething) {
            Log.i("o instanceof BuySomething TRUE");
        }
        for (Object obj : objects) {
            Log.i(obj.toString());
        }
        buySomething.buyGoods(goodsName);
        method.invoke(buySomething, goodsName + " HAHAHA");
        Log.i("invoke Buy after " + method.getName());
        return null;
    }
}

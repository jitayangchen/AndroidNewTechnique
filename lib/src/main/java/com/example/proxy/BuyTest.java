package com.example.proxy;

import com.example.Log;

import java.lang.reflect.Proxy;

public class BuyTest {

    public static void main(String[] args) {
//        BuySomethingImpl buySomething = new BuySomethingImpl();
//        BuyProxy buyProxy = new BuyProxy(buySomething);
//        buyProxy.buyGoods("Banana");


        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        BuySomething apple = (BuySomething) Proxy.newProxyInstance(BuySomething.class.getClassLoader(), new Class[]{BuySomething.class}, new DynamicProxy(new BuySomethingImpl(), "Apple"));
        if ((apple instanceof BuySomething)) {
            Log.i(apple.getClass().getCanonicalName());
            Log.i(apple.getClass().getTypeName());
        }
        apple.buyGoods("YOYOYO");

//        com.sun.proxy.$Proxy0
    }
}

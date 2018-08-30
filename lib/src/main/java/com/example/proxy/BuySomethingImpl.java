package com.example.proxy;

import com.example.Log;

public class BuySomethingImpl implements BuySomething {
    @Override
    public void buyGoods(String goodsName) {
        Log.i("Buy " + goodsName);
    }
}

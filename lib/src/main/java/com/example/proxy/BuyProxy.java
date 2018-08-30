package com.example.proxy;

import com.example.Log;

public class BuyProxy implements BuySomething {

    private BuySomething buySomething;

    public BuyProxy(BuySomething buySomething) {
        this.buySomething = buySomething;
    }

    @Override
    public void buyGoods(String goodsName) {
        Log.i("Buy before");
        buySomething.buyGoods(goodsName);
        Log.i("Buy after");
    }
}

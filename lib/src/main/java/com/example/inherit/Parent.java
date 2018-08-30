package com.example.inherit;

public class Parent extends SuperParent {
    public Parent() {
//        Log.i("--- Parent ---");
    }

    public Parent(String arg) {
//        Log.i("--- Parent --- arg = " + arg);
    }

    public String getTest() {
        return "Parent";
    }

    public String getSuperTest() {
        return super.getTest();
    }
}

package com.example.classloader;

import com.example.Jobs;
import com.example.Log;

public class ClassLoaderTest {
    public static void main(String[] args) {
        DiskClassLoader diskClassLoader = new DiskClassLoader("/Users/yangchen/LearenGit/GitTest");//1
//        try {
//            Class c = diskClassLoader.loadClass("com.example.Jobs");//2
//            if (c != null) {
//                try {
//                    Object obj = c.newInstance();
//                    Log.i(obj.toString());
//                    System.out.println(obj.getClass().getClassLoader());
//                    Method method = c.getDeclaredMethod("say", null);
//                    method.invoke(obj, null);//3
//                } catch (InstantiationException | IllegalAccessException
//                        | NoSuchMethodException
//                        | SecurityException |
//                        IllegalArgumentException |
//                        InvocationTargetException e) {
//                    e.printStackTrace();
//                }
//            }
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }




        try {
            Class c = diskClassLoader.loadClass("com.example.Jobs");//2
            if (c != null) {
                try {
                    Jobs obj = (Jobs) c.newInstance();
                    Log.i(obj.toString());
                    System.out.println(obj.getClass().getClassLoader());
                    obj.say();
//                    Method method = c.getDeclaredMethod("say", null);
//                    method.invoke(obj, null);//3
                } catch (InstantiationException | IllegalAccessException
                        | SecurityException |
                        IllegalArgumentException e) {
                    e.printStackTrace();
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

//        Jobs jobs = new Jobs();
//        jobs.say();
    }
}

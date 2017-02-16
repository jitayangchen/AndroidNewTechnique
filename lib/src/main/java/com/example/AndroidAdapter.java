package com.example;

/**
 * @author yaodingding
 * @Description:
 * @date 2016/11/7 19:15
 * @copyright TCL-MIG
 */

public class AndroidAdapter {
    private static float ldpi_w = 240f;
    private static float ldpi_h = 320f;
    private static float ldpi_rate = 0.75f;

    private static float mdpi_w = 320f;
    private static float mdpi_h = 480f;
    private static float mdpi_rate = 1f;

    private static float hdpi_w = 480f;
    private static float hdpi_h = 800f;
    private static float hdpi_rate = 1.5f;

    private static float xhdpi_w = 720f;
    private static float xhdpi_h = 1280f;
    private static float xhdpi_rate = 2f;

    private static float xxhdpi_w = 1080f;
    private static float xxhdpi_h = 1920f;
    private static float xxhdpi_rate = 3f;


    public static void imgAdapter(float img_xhdpi_w, float img_xhdpi_h) {
        float zoom_ldpi = xhdpi_w / ldpi_w > xhdpi_h / ldpi_h ? xhdpi_w / ldpi_w : xhdpi_h / ldpi_h;
        float zoom_mdpi = xhdpi_w / mdpi_w > xhdpi_h / mdpi_h ? xhdpi_w / mdpi_w : xhdpi_h / mdpi_h;
        float zoom_hdpi = xhdpi_w / hdpi_w > xhdpi_h / hdpi_h ? xhdpi_w / hdpi_w : xhdpi_h / hdpi_h;
        float zoom_xxhdpi = xhdpi_w / xxhdpi_w < xhdpi_h / xxhdpi_h ? xhdpi_w / xxhdpi_w : xhdpi_h / xxhdpi_h;

//        float img_xhdpi_w=604f;
//        float img_xhdpi_h=916f;

        System.out.print("ldpi-->" + (int) (img_xhdpi_w / (ldpi_rate * zoom_ldpi)) + "dp" + "--->" + (int) (img_xhdpi_h / (ldpi_rate * zoom_ldpi)) + "dp" + "\n");

        System.out.print("mdpi-->" + (int) (img_xhdpi_w / (mdpi_rate * zoom_mdpi)) + "dp" + "--->" + (int) (img_xhdpi_h / (mdpi_rate * zoom_mdpi)) + "dp" + "\n");

        System.out.print("hdpi-->" + (int) (img_xhdpi_w / (hdpi_rate * zoom_hdpi)) + "dp" + "--->" + (int) (img_xhdpi_h / (hdpi_rate * zoom_hdpi)) + "dp" + "\n");
        System.out.print("xxhdpi-->" + (int) (img_xhdpi_w / (xxhdpi_rate * zoom_xxhdpi)) + "dp" + "--->" + (int) (img_xhdpi_h / (xxhdpi_rate * zoom_xxhdpi)) + "dp");
    }


    public static void hAdapter(float XHDP_px) {
        float zoom_ldpi = xhdpi_w / ldpi_w > xhdpi_h / ldpi_h ? xhdpi_w / ldpi_w : xhdpi_h / ldpi_h;
        float zoom_mdpi = xhdpi_w / mdpi_w > xhdpi_h / mdpi_h ? xhdpi_w / mdpi_w : xhdpi_h / mdpi_h;
        float zoom_hdpi = xhdpi_w / hdpi_w > xhdpi_h / hdpi_h ? xhdpi_w / hdpi_w : xhdpi_h / hdpi_h;
        float zoom_xxhdpi = xhdpi_w / xxhdpi_w < xhdpi_h / xxhdpi_h ? xhdpi_w / xxhdpi_w : xhdpi_h / xxhdpi_h;


//        System.out.print("ldpi-->" + (int) (XHDP_px / (ldpi_rate * zoom_ldpi)) + "dp" + "\n");

        System.out.print("mdpi-->" + "--->" + (int) (XHDP_px / (mdpi_rate * zoom_mdpi)) + "dp" + "\n");

//        System.out.print("hdpi-->" + "--->" + (int) (XHDP_px / (hdpi_rate * zoom_hdpi)) + "dp" + "\n");
//        System.out.print("xxhdpi-->"+ "--->" + (int) (XHDP_px / (xxhdpi_rate * zoom_xxhdpi)) + "dp");

    }

}

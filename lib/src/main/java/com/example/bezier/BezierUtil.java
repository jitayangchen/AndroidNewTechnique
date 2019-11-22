package com.example.bezier;

import com.example.Log;

public class BezierUtil {

    public static void main(String[] args) {

        PointF[] points = new PointF[1000];
        for (float t = 0.001f; t <= 1f; t += 0.001) {

            PointF pointF0 = new PointF(0, 0);
            PointF pointF1 = new PointF(0.17f, 0.67f);
            PointF pointF2 = new PointF(0.83f, 0.67f);
            PointF pointF3 = new PointF(1, 1);
            points[(int) (t * 1000) - 1] = calculateBezierPointForCubic(t, pointF0, pointF1, pointF2, pointF3);
        }

        for (int i = 0; i < points.length; i++) {
            Log.i(points[i].toString());
        }
    }

    public static PointF calculateBezierPointForCubic(float t, PointF p0, PointF p1, PointF p2, PointF p3) {
        PointF point = new PointF();
        float temp = 1 - t;
        point.x = p0.x * temp * temp * temp + 3 * p1.x * t * temp * temp + 3 * p2.x * t * t * temp + p3.x * t * t * t;
        point.y = p0.y * temp * temp * temp + 3 * p1.y * t * temp * temp + 3 * p2.y * t * t * temp + p3.y * t * t * t;
        return point;
    }
}

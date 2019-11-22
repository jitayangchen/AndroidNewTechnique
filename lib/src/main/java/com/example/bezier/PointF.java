package com.example.bezier;

class PointF {

    public float x;
    public float y;

    public PointF() {

    }

    public PointF(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "PointF{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}

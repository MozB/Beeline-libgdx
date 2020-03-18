package org.beelinelibgdx.util;

import java.io.Serializable;

import static java.lang.Math.round;

public class Point implements Serializable {

    private static final long serialVersionUID = 1l;

    public float x;
    public float y;

    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "[" + round(x*10)/10 + ", " + round(y*10)/10 + "]";
    }
}

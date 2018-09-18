package org.beelinelibgdx.util;

import java.io.Serializable;

public class Point implements Serializable {

    private static final long serialVersionUID = 1l;

    public float x;
    public float y;

    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }
}

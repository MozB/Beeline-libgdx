package org.beelinelibgdx.util;

import com.badlogic.gdx.graphics.Color;

import java.io.Serializable;

public class BeelineColor implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    float r, g, b, a;

    public BeelineColor(Color color) {
        r = color.r;
        g = color.g;
        b = color.b;
        a = color.a;
    }

    public Color toColor() {
        return new Color(r, g, b, a);
    }

}

package org.jacpfx.dto;

import java.io.Serializable;

/**
 * Created by Andy Moncsek on 16.12.13.
 * This Class represents a coordinate on canvas
 *
 * @author Andy Moncsek
 */
public class CanvasPoint implements Serializable {
    private static final long serialVersionUID = -3632448279678515949L;
    private final double x;
    private final double y;
    private final Type type;
    private final ColorDTO color;


    public CanvasPoint(final double x, final double y, final Type type) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.color = null;
    }

    public CanvasPoint(final ColorDTO color, final Type type) {
        this.color = color;
        this.type = type;
        this.x = 0;
        this.y = 0;
    }

    public double getX() {
        return x;
    }


    public double getY() {
        return y;
    }


    public Type getType() {
        return this.type;
    }

    public ColorDTO getColor() {
        return this.color;
    }

    public enum Type {
        BEGIN, DRAW, CLEAR, RELEASE, COLOR
    }

}

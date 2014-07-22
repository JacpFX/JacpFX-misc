package org.jacpfx.dto;

import java.io.Serializable;

/**
 * Created by Andy Moncsek on 16.12.13.
 * This Class represents a coordinate on canvas
 * @author Andy Moncsek
 */
public class CanvasPoint implements Serializable {
    private static final long serialVersionUID = -3632448279678515949L;
    private double x;
    private double y;
    private Type type;

    public CanvasPoint() {

    }

    public CanvasPoint(final double x, final double y, final Type type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Type getType() {
        return this.type;
    }

    public enum Type {
        BEGIN, DRAW, CLEAR, RELEASE
    }

}

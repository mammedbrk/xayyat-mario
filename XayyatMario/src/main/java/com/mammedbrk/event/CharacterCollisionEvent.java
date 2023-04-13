package com.mammedbrk.event;

import java.util.EventObject;

public class CharacterCollisionEvent extends EventObject {
    private double dx, dy, xFront, xBack, yFront, yBack;

    public CharacterCollisionEvent(Object source) {
        super(source);
    }

    public CharacterCollisionEvent(Object source, double dx, double dy, double xFront, double xBack, double yFront, double yBack) {
        super(source);
        this.dx = dx;
        this.dy = dy;
        this.xFront = xFront;
        this.xBack = xBack;
        this.yFront = yFront;
        this.yBack = yBack;
    }

    public double getDx() {
        return dx;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public double getDy() {
        return dy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public double getxFront() {
        return xFront;
    }

    public void setxFront(double xFront) {
        this.xFront = xFront;
    }

    public double getxBack() {
        return xBack;
    }

    public void setxBack(double xBack) {
        this.xBack = xBack;
    }

    public double getyFront() {
        return yFront;
    }

    public void setyFront(double yFront) {
        this.yFront = yFront;
    }

    public double getyBack() {
        return yBack;
    }

    public void setyBack(double yBack) {
        this.yBack = yBack;
    }
}

package com.mammedbrk.model.component.enemy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mammedbrk.current.Current;
import com.mammedbrk.model.interfaces.Gravitational;
import com.mammedbrk.model.interfaces.Movable;

public class Spiny extends Enemy implements Movable, Gravitational {
    @JsonIgnore
    private static double SPEED;
    @JsonIgnore
    private double speed;
    @JsonIgnore
    private double gravity;
    private boolean marioCheck;
    private double velocity;

    public Spiny() {
        speed = SPEED;
    }

    public Spiny(double x, double y) {
        super(x, y);
        this.speed = SPEED;
    }

    public Spiny(double x, double y, boolean marioCheck, double velocity) {
        super(x, y);
        this.marioCheck = marioCheck;
        this.velocity = velocity;
    }

    @Override
    public void move() {
        if (marioCheck) {
            velocity = velocity + speed;
            x = x + velocity;
        }
        else {
            velocity = 0;
            x = x + speed;
        }
    }

    @Override
    public void changeDirection() {
        speed = -speed;
    }

    @Override
    public void applyGravity() {
        gravity = gravity + Current.gravity; // todo
        y += gravity;
    }

    @Override
    public void resetGravity() {
        gravity = 0;
    }

    public static void setSPEED(double SPEED) {
        Spiny.SPEED = SPEED;
    }

    public void setMarioCheck(boolean marioCheck) {
        this.marioCheck = marioCheck;
    }

    public boolean isMarioCheck() {
        return marioCheck;
    }

    public double getVelocity() {
        return velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }
}

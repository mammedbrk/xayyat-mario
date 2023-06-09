package com.mammedbrk.model.component.enemy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mammedbrk.current.Current;
import com.mammedbrk.model.interfaces.Gravitational;
import com.mammedbrk.model.interfaces.Hittable;
import com.mammedbrk.model.interfaces.Movable;
import com.mammedbrk.model.interfaces.Timer;

import java.util.Random;

public class Koopa extends Enemy implements Movable, Gravitational, Hittable, Timer {
    @JsonIgnore
    private static double SPEED;
    @JsonIgnore
    private double speed;
    @JsonIgnore
    private double gravity;
    private boolean hit;
    private long time;

    public Koopa() {
        speed = SPEED;
    }

    public Koopa(double x, double y) {
        super(x, y);
        this.speed = SPEED;
    }

    public Koopa(double x, double y, boolean hit, long time) {
        super(x, y);
        this.hit = hit;
        this.time = time;
    }

    @Override
    public void move() {
        if (!hit)
            x = x + speed;
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

    @Override
    public void hit() {
        if (hit) {
            die();
            return;
        }
        hit = true;
        time = 0;
        moveAfterHit();
    }

    private void moveAfterHit() {
        boolean direction = new Random().nextBoolean();
        x = x + (direction? 1: -1);
    }

    public static void setSPEED(double SPEED) {
        Koopa.SPEED = SPEED;
    }

    @Override
    public void changeTime() {
        if (time < 3)
            time++;
        else {
            hit = false;
        }
    }

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}

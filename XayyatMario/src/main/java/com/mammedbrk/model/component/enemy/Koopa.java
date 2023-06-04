package com.mammedbrk.model.component.enemy;

import com.mammedbrk.current.Current;
import com.mammedbrk.model.interfaces.Gravitational;
import com.mammedbrk.model.interfaces.Hittable;
import com.mammedbrk.model.interfaces.Movable;
import com.mammedbrk.model.interfaces.Timer;

import java.util.Random;

public class Koopa extends Enemy implements Movable, Gravitational, Hittable, Timer {
    private static double speed;
    private double gravity;
    private boolean hit;
    private long time;

    public Koopa() {
    }

    public Koopa(int x, int y) {
        super(x, y);
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
        gravity = gravity + Current.gravity; // todo maybe from config
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

    public static void setSpeed(double speed) {
        Koopa.speed = speed;
    }

    @Override
    public void changeTime() {
        if (time < 3)
            time++;
        else hit = false;
    }
}

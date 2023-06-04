package com.mammedbrk.model.component.enemy;

import com.mammedbrk.current.Current;
import com.mammedbrk.model.interfaces.Gravitational;
import com.mammedbrk.model.interfaces.Hittable;
import com.mammedbrk.model.interfaces.Movable;

import java.util.Random;

public class Koopa extends Enemy implements Movable, Gravitational, Hittable {
    private static double speed;
    private double gravity;
    private boolean hit;
    private long hitTime;

    public Koopa() {
    }

    public Koopa(int x, int y) {
        super(x, y);
    }

    @Override
    public void move() {
        if (hit && System.currentTimeMillis() - hitTime >= 3000)
            hit = false;
        if (!hit) {
            x = x + speed;
        }
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
        if (hit && System.currentTimeMillis() - hitTime < 3000) { // todo maybe read from config
            die();
            return;
        }
        hit = true;
        hitTime = System.currentTimeMillis();
        moveAfterHit();
    }

    private void moveAfterHit() {
        boolean direction = new Random().nextBoolean();
        x = x + (direction? 1: -1);
    }

    public static void setSpeed(double speed) {
        Koopa.speed = speed;
    }
}

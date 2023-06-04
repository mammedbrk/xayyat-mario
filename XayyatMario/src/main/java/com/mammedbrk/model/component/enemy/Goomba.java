package com.mammedbrk.model.component.enemy;

import com.mammedbrk.current.Current;
import com.mammedbrk.model.interfaces.Gravitational;
import com.mammedbrk.model.interfaces.Hittable;
import com.mammedbrk.model.interfaces.Movable;

public class Goomba extends Enemy implements Movable, Gravitational, Hittable {
    private static double speed;
    private double gravity;

    public Goomba() {
    }

    public Goomba(int x, int y) {
        super(x, y);
    }

    @Override
    public void move() {
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
        die();
    }

    public static void setSpeed(double speed) {
        Goomba.speed = speed;
    }
}

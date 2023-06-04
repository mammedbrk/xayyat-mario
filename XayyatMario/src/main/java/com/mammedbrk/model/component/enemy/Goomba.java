package com.mammedbrk.model.component.enemy;

import com.mammedbrk.current.Current;
import com.mammedbrk.model.interfaces.Gravitational;
import com.mammedbrk.model.interfaces.Movable;

public class Goomba extends Enemy implements Movable, Gravitational {
    private static double speed;
    private double gravity;

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
}

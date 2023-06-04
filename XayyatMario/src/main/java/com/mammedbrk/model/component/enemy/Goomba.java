package com.mammedbrk.model.component.enemy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mammedbrk.current.Current;
import com.mammedbrk.model.interfaces.Gravitational;
import com.mammedbrk.model.interfaces.Hittable;
import com.mammedbrk.model.interfaces.Movable;

public class Goomba extends Enemy implements Movable, Gravitational, Hittable {
    @JsonIgnore
    private static double speed;
    @JsonIgnore
    private double gravity;

    public Goomba() {
    }

    public Goomba(double x, double y) {
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
        gravity = gravity + Current.gravity; // todo
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

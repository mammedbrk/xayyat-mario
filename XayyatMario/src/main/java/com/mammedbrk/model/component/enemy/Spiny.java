package com.mammedbrk.model.component.enemy;

import com.mammedbrk.current.Current;
import com.mammedbrk.model.interfaces.Gravitational;
import com.mammedbrk.model.interfaces.Hittable;
import com.mammedbrk.model.interfaces.Movable;

import java.util.Random;

public class Spiny extends Enemy implements Movable, Gravitational {
    private static double speed;
    private double gravity;
    private boolean marioCheck;
    private double velocity;

    public Spiny() {
    }

    public Spiny(int x, int y) {
        super(x, y);
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
        gravity = gravity + Current.gravity; // todo maybe from config
        y += gravity;
    }

    @Override
    public void resetGravity() {
        gravity = 0;
    }

    public static void setSpeed(double speed) {
        Spiny.speed = speed;
    }

    public void setMarioCheck(boolean marioCheck) {
        this.marioCheck = marioCheck;
    }
}

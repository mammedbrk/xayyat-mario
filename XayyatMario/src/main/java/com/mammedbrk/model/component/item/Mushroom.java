package com.mammedbrk.model.component.item;

import com.mammedbrk.current.Current;
import com.mammedbrk.model.component.enemy.Goomba;
import com.mammedbrk.model.interfaces.Gravitational;
import com.mammedbrk.model.interfaces.Movable;
import com.mammedbrk.model.interfaces.Timer;

public class Mushroom extends Item implements Gravitational, Movable, Timer {
    private static double speed;
    private double gravity;
    private int time;
    private boolean move;

    public Mushroom() {
    }

    public Mushroom(int x, int y) {
        super(x, y);
    }

    @Override
    public void changeTime() {
        if (time < 3) // todo
            time++;
        else move = true;
    }

    @Override
    public void move() {
        if (move)
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

    public static void setSpeed(double speed) {
        Mushroom.speed = speed;
    }
}

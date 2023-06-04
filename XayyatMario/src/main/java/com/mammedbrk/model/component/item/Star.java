package com.mammedbrk.model.component.item;

import com.mammedbrk.current.Current;
import com.mammedbrk.model.interfaces.Gravitational;
import com.mammedbrk.model.interfaces.Movable;
import com.mammedbrk.model.interfaces.Timer;

public class Star extends Item implements Movable, Gravitational, Timer {
    private static double speed;
    private static double jumpSpeed;
    private double gravity;
    private int waitTime;
    private boolean move;
    private boolean jump;

    public Star() {
    }

    public Star(int x, int y) {
        super(x, y);
    }

    @Override
    public void changeTime() {
        if (waitTime < 3) // todo
            waitTime++;
        else move = jump = true;
    }

    @Override
    public void move() {
        if (move)
            x = x + speed;
        if (jump) {
            y = y + jumpSpeed; // todo may cause some bug
            jump = false;
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

    public static void setSpeed(double speed) {
        Star.speed = speed;
    }

    public static void setJumpSpeed(double jumpSpeed) {
        Star.jumpSpeed = jumpSpeed;
    }
}

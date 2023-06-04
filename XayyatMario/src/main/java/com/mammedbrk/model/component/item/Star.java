package com.mammedbrk.model.component.item;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mammedbrk.current.Current;
import com.mammedbrk.model.interfaces.Gravitational;
import com.mammedbrk.model.interfaces.Movable;
import com.mammedbrk.model.interfaces.Timer;

public class Star extends Item implements Movable, Gravitational, Timer {
    @JsonIgnore
    private static double speed;
    @JsonIgnore
    private static double jumpSpeed;
    @JsonIgnore
    private double gravity;
    private int waitTime;
    private boolean move;
    private boolean jump;

    public Star() {
    }

    public Star(int x, int y) {
        super(x, y);
    }

    public Star(int x, int y, int waitTime, boolean move, boolean jump) {
        super(x, y);
        this.waitTime = waitTime;
        this.move = move;
        this.jump = jump;
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

    public int getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(int waitTime) {
        this.waitTime = waitTime;
    }

    public boolean isMove() {
        return move;
    }

    public void setMove(boolean move) {
        this.move = move;
    }

    public boolean isJump() {
        return jump;
    }

    public void setJump(boolean jump) {
        this.jump = jump;
    }
}

package com.mammedbrk.model.component.item;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mammedbrk.config.Config;
import com.mammedbrk.current.Current;
import com.mammedbrk.model.interfaces.Gravitational;
import com.mammedbrk.model.interfaces.Movable;
import com.mammedbrk.model.interfaces.Timer;

public class Star extends Item implements Movable, Gravitational, Timer {
    @JsonIgnore
    private static double SPEED = Double.parseDouble(Config.getInstance().getProperty("star_speed")) / Integer.parseInt(Config.getInstance().getProperty("fps"));
    @JsonIgnore
    private static double JUMP_SPEED = Double.parseDouble(Config.getInstance().getProperty("star_jump")) / Integer.parseInt(Config.getInstance().getProperty("fps"));
    @JsonIgnore
    private double gravity;
    private double speed;
    private int waitTime;
    private boolean move;
    private boolean jump;

    public Star() {
        speed = SPEED;
    }

    public Star(double x, double y) {
        super(x, y);
        speed = SPEED;
    }

    public Star(double x, double y, int waitTime, boolean move, boolean jump) {
        super(x, y);
        this.waitTime = waitTime;
        this.move = move;
        this.jump = jump;
        speed = SPEED;
    }

    @Override
    public void changeTime() {
        if (waitTime < Integer.parseInt(Config.getInstance().getProperty("star_time")))
            waitTime++;
        else move = jump = true;
    }

    @Override
    public void move() {
        if (move)
            x = x + speed;
        if (jump)
            y = y + JUMP_SPEED; // todo may cause some bug
    }

    @Override
    public void changeDirection() {
        speed = -speed;
    }

    @Override
    public void applyGravity() {
        gravity = gravity + Double.parseDouble(Config.getInstance().getProperty("gravity")) / Integer.parseInt(Config.getInstance().getProperty("fps")) / Integer.parseInt(Config.getInstance().getProperty("fps"));
        y += gravity;
    }

    @Override
    public void resetGravity() {
        gravity = 0;
        jump = false;
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

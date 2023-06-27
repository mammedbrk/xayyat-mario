package com.mammedbrk.model.component.item;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mammedbrk.config.Config;
import com.mammedbrk.current.Current;
import com.mammedbrk.model.interfaces.Gravitational;
import com.mammedbrk.model.interfaces.Movable;
import com.mammedbrk.model.interfaces.Timer;

public class Mushroom extends Item implements Gravitational, Movable, Timer {
    @JsonIgnore
    private static double SPEED = Double.parseDouble(Config.getInstance().getProperty("goomba_speed")) / Integer.parseInt(Config.getInstance().getProperty("fps"));
    @JsonIgnore
    private double gravity;
    private double speed;
    private int time;
    private boolean move;

    public Mushroom() {
        speed = SPEED;
    }

    public Mushroom(double x, double y) {
        super(x, y);
        speed = SPEED;
    }

    public Mushroom(double x, double y, int time, boolean move) {
        super(x, y);
        this.time = time;
        this.move = move;
        speed = SPEED;
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
        gravity = gravity + Double.parseDouble(Config.getInstance().getProperty("gravity")) / Integer.parseInt(Config.getInstance().getProperty("fps")) / Integer.parseInt(Config.getInstance().getProperty("fps"));
        y += gravity;
    }

    @Override
    public void resetGravity() {
        gravity = 0;
    }
}

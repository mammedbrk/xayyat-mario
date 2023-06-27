package com.mammedbrk.model.component.enemy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mammedbrk.config.Config;
import com.mammedbrk.current.Current;
import com.mammedbrk.model.interfaces.Gravitational;
import com.mammedbrk.model.interfaces.Hittable;
import com.mammedbrk.model.interfaces.Movable;

public class Goomba extends Enemy implements Movable, Gravitational, Hittable {
    @JsonIgnore
    private static double SPEED = Double.parseDouble(Config.getInstance().getProperty("goomba_speed")) / Integer.parseInt(Config.getInstance().getProperty("fps"));
    @JsonIgnore
    private double speed;
    @JsonIgnore
    private double gravity;

    public Goomba() {
        speed = SPEED;
    }

    public Goomba(double x, double y) {
        super(x, y);
        this.speed = SPEED;
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

    @JsonIgnore
    @Override
    public boolean isHit() {
        return false;
    }

    public static void setSPEED(double SPEED) {
        Goomba.SPEED = SPEED;
    }
}

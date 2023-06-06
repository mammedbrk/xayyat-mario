package com.mammedbrk.model.component;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mammedbrk.current.Current;
import com.mammedbrk.model.interfaces.Gravitational;
import com.mammedbrk.model.interfaces.Movable;

public class Mario extends Component implements Movable, Gravitational {
    private String name;
    private double speed;
    private double jumpSpeed;
    @JsonIgnore
    private boolean move;
    @JsonIgnore
    private boolean left;
    @JsonIgnore
    private boolean jump;
    @JsonIgnore
    private double gravity;

    public Mario() {
    }

    public Mario(double x, double y) {
        super(x, y);
    }

    public Mario(double x, double y, String name, double speed, double jumpSpeed) {
        super(x, y);
        this.name = name;
        this.speed = speed;
        this.jumpSpeed = jumpSpeed;
    }

    @Override
    public void move() {
        if (move)
            x = x + (left? -speed: speed);
        if (jump)
            y = y + jumpSpeed;
    }

    @Override
    public void changeDirection() {
    }

    @Override
    public void applyGravity() {
        gravity = gravity + Current.gravity; // todo
        y += gravity;
    }

    @Override
    public void resetGravity() {
        gravity = 0;
        jump = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getJumpSpeed() {
        return jumpSpeed;
    }

    public void setJumpSpeed(double jumpSpeed) {
        this.jumpSpeed = jumpSpeed;
    }

    public void setMove(boolean move) {
        this.move = move;
    }

    public void setJump(boolean jump) {
        this.jump = jump;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }
}

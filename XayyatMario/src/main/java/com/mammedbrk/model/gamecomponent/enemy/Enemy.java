package com.mammedbrk.model.gamecomponent.enemy;

public abstract class Enemy {
    protected int x, y;

    public Enemy() {
    }

    public Enemy(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Getters and setters

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}

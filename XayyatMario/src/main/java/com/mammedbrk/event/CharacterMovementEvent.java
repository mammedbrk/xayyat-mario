package com.mammedbrk.event;

import com.mammedbrk.model.gamecomponent.Tile;

public class CharacterMovementEvent {
    private double dx, dy;
    private boolean canJump, killed, powerUp;
    private Tile removedTile;

    public CharacterMovementEvent() {
    }

    public CharacterMovementEvent(double dx, double dy, boolean canJump, boolean killed, boolean powerUp) {
        this.dx = dx;
        this.dy = dy;
        this.canJump = canJump;
        this.killed = killed;
        this.powerUp = powerUp;
    }

    public double getDx() {
        return dx;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public double getDy() {
        return dy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public boolean isCanJump() {
        return canJump;
    }

    public void setCanJump(boolean canJump) {
        this.canJump = canJump;
    }

    public boolean isKilled() {
        return killed;
    }

    public void setKilled(boolean killed) {
        this.killed = killed;
    }

    public boolean isPowerUp() {
        return powerUp;
    }

    public void setPowerUp(boolean powerUp) {
        this.powerUp = powerUp;
    }

    public Tile getRemovedTile() {
        return removedTile;
    }

    public void setRemovedTile(Tile removedTile) {
        this.removedTile = removedTile;
    }
}

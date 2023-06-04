package com.mammedbrk.event;

import com.mammedbrk.model.component.Component;

public class CharacterMovementEvent {
    private double dx, dy;
    private boolean canJump, loadNeeded, powerUp, finished;
    private Component removedTile;

    public CharacterMovementEvent() {
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

    public boolean isLoadNeeded() {
        return loadNeeded;
    }

    public void setLoadNeeded(boolean loadNeeded) {
        this.loadNeeded = loadNeeded;
    }

    public boolean isPowerUp() {
        return powerUp;
    }

    public void setPowerUp(boolean powerUp) {
        this.powerUp = powerUp;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public Component getRemovedTile() {
        return removedTile;
    }

    public void setRemovedTile(Component removedTile) {
        this.removedTile = removedTile;
    }
}

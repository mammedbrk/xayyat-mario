package com.mammedbrk.event;

public class CharacterMovementEvent {
    private double dx, dy;
    private boolean visible, canJump, killed, powerUp;

    public CharacterMovementEvent() {
    }

    public CharacterMovementEvent(double dx, double dy, boolean visible, boolean canJump, boolean killed, boolean powerUp) {
        this.dx = dx;
        this.dy = dy;
        this.visible = visible;
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

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
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
}

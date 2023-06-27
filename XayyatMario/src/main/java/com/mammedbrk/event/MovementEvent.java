package com.mammedbrk.event;

public class MovementEvent {
    private boolean move;
    private boolean left;
    private boolean jump;
    private boolean sit;

    public MovementEvent() {
    }

    public MovementEvent(boolean move, boolean left, boolean jump, boolean sit) {
        this.move = move;
        this.left = left;
        this.jump = jump;
        this.sit = sit;
    }

    public boolean isMove() {
        return move;
    }

    public void setMove(boolean move) {
        this.move = move;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isJump() {
        return jump;
    }

    public void setJump(boolean jump) {
        this.jump = jump;
    }

    public boolean isSit() {
        return sit;
    }

    public void setSit(boolean sit) {
        this.sit = sit;
    }
}

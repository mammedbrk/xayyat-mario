package com.mammedbrk.model.component.block;

public class CoinsBlock extends Block {
    private int hitCount;

    public CoinsBlock() {

    }

    public CoinsBlock(int x, int y) {
        super(x, y);
    }

    public void increaseHitCount() {
        hitCount++;
    }

    public boolean hasCoins() {
        return hitCount < 5; // todo read from config file
    }

    // Getters and setters

    public int getHitCount() {
        return hitCount;
    }

    public void setHitCount(int hitCount) {
        this.hitCount = hitCount;
    }
}

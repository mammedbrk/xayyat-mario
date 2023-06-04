package com.mammedbrk.model.component.block;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CoinsBlock extends Block {
    @JsonIgnore
    private static int coinsCount;
    private int hitCount;

    public CoinsBlock() {
    }

    public CoinsBlock(int x, int y) {
        super(x, y);
    }

    public CoinsBlock(int x, int y, int hitCount) {
        super(x, y);
        this.hitCount = hitCount;
    }

    public void increaseHitCount() {
        hitCount++;
    }

    public boolean hasCoins() {
        return hitCount < coinsCount;
    }

    public int getHitCount() {
        return hitCount;
    }

    public void setHitCount(int hitCount) {
        this.hitCount = hitCount;
    }

    public static void setCoinsCount(int coinsCount) {
        CoinsBlock.coinsCount = coinsCount;
    }
}

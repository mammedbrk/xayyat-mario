package com.mammedbrk.model.component.block;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mammedbrk.model.interfaces.Changeable;

public class CoinsBlock extends Block implements Changeable<EmptyBlock> {
    @JsonIgnore
    private static int coinsCount;
    private int hitCount;

    public CoinsBlock() {
    }

    public CoinsBlock(double x, double y) {
        super(x, y);
    }

    public CoinsBlock(double x, double y, int hitCount) {
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

    @Override
    public EmptyBlock changedObject() {
        return new EmptyBlock(x, y);
    }
}

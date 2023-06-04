package com.mammedbrk.model.component.block;

public class CoinBlock extends Block {
    private boolean hit;

    public CoinBlock() {

    }

    public CoinBlock(int x, int y) {
        super(x, y);
    }

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }
}

package com.mammedbrk.model.component.block;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CoinBlock extends Block {
    @JsonIgnore
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

package com.mammedbrk.model.component.block;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mammedbrk.model.interfaces.Changeable;

public class CoinBlock extends Block implements Changeable<SimpleBlock> {
    @JsonIgnore
    private boolean hit;

    public CoinBlock() {
    }

    public CoinBlock(double x, double y) {
        super(x, y);
    }

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    @Override
    public SimpleBlock changedObject() {
        return new SimpleBlock(x, y);
    }
}

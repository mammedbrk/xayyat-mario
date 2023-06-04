package com.mammedbrk.model.component.block;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class SimpleBlock extends Block {
    @JsonIgnore
    private boolean hit;

    public SimpleBlock() {
    }

    public SimpleBlock(int x, int y) {
        super(x, y);
    }

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }
}

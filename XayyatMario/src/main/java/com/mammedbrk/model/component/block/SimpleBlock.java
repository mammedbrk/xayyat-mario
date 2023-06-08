package com.mammedbrk.model.component.block;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mammedbrk.model.interfaces.Hittable;

public class SimpleBlock extends Block implements Hittable {
    @JsonIgnore
    private boolean hit;

    public SimpleBlock() {
    }

    public SimpleBlock(double x, double y) {
        super(x, y);
    }

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    @Override
    public void hit() {
        hit = true;
    }
}

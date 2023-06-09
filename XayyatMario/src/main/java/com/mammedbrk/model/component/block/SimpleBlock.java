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

    @Override
    public void hit() {
        hit = true;
    }

    @Override
    public boolean isHit() {
        return hit;
    }
}

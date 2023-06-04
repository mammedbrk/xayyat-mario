package com.mammedbrk.model.component.item;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mammedbrk.current.Current;
import com.mammedbrk.model.interfaces.Gravitational;

public class Coin extends Item implements Gravitational {
    @JsonIgnore
    private double gravity;

    public Coin() {
    }

    public Coin(int x, int y) {
        super(x, y);
    }

    @Override
    public void applyGravity() {
        gravity = gravity + Current.gravity; // todo
        y += gravity;
    }

    @Override
    public void resetGravity() {
        gravity = 0;
    }
}

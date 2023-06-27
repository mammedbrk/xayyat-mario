package com.mammedbrk.model.component.item;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mammedbrk.config.Config;
import com.mammedbrk.current.Current;
import com.mammedbrk.model.interfaces.Gravitational;

public class Coin extends Item implements Gravitational {
    @JsonIgnore
    private double gravity;

    public Coin() {
    }

    public Coin(double x, double y) {
        super(x, y);
    }

    @Override
    public void applyGravity() {
        gravity = gravity + Double.parseDouble(Config.getInstance().getProperty("gravity")) / Integer.parseInt(Config.getInstance().getProperty("fps")) / Integer.parseInt(Config.getInstance().getProperty("fps"));
        y += gravity;
    }

    @Override
    public void resetGravity() {
        gravity = 0;
    }
}

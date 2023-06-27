package com.mammedbrk.model.component;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mammedbrk.config.Config;
import com.mammedbrk.current.Current;
import com.mammedbrk.model.interfaces.Gravitational;

public class Checkpoint extends Component implements Gravitational {
    @JsonIgnore
    private double gravity;

    public Checkpoint() {
    }

    public Checkpoint(double x, double y) {
        super(x, y);
    }

    @Override
    public String getType() {
        return this.getClass().getSimpleName();
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

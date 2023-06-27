package com.mammedbrk.model.component;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
        return "Checkpoint";
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

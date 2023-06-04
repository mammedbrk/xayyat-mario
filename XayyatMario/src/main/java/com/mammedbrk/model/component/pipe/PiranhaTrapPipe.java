package com.mammedbrk.model.component.pipe;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mammedbrk.model.Scene;
import com.mammedbrk.model.component.enemy.Piranha;

public class PiranhaTrapPipe extends Pipe implements Piranha {
    @JsonIgnore
    private static int showTime;
    @JsonIgnore
    private static int hideTime;
    private boolean alive;
    @JsonIgnore
    private boolean shown;
    @JsonIgnore
    private int time;

    public PiranhaTrapPipe() {
    }

    public PiranhaTrapPipe(double x, double y) {
        super(x, y);
        alive = true;
    }

    public PiranhaTrapPipe(double x, double y, boolean alive) {
        super(x, y);
        this.alive = alive;
    }

    @Override
    public boolean isAlive() {
        return alive;
    }

    @Override
    public void die() {
        alive = false;
    }

    @Override
    public boolean isShown() {
        return shown;
    }

    @Override
    public void changeTime() {
        if (time > 0)
            time--;
        else {
            if (shown) {
                time = hideTime;
                shown = false;
            }
            else {
                time = showTime;
                shown = true;
            }
        }
    }

    public static void setShowTime(int showTime) {
        PiranhaTrapPipe.showTime = showTime;
    }

    public static void setHideTime(int hideTime) {
        PiranhaTrapPipe.hideTime = hideTime;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}

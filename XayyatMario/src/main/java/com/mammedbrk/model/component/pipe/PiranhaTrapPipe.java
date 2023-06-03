package com.mammedbrk.model.component.pipe;

import com.mammedbrk.model.component.enemy.Piranha;

public class PiranhaTrapPipe extends Pipe implements Piranha {
    private boolean alive;
    private boolean shown;
    private int time;
    private int showTime;
    private int hideTime;

    public PiranhaTrapPipe() {
    }

    public PiranhaTrapPipe(int x, int y) {
        super(x, y);
        alive = true;
        showTime = 3; // todo read from config file
        hideTime = 2; // todo read from config file
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
    public void changeVisibility() {
        if (shown && time == showTime) {
            shown = false;
        }
        else if (!shown && time == hideTime) {
            shown = true;
        }
    }

    @Override
    public void resetTime() {
        time = 0;
    }

    @Override
    public void increaseTime() {
        time++;
    }
}

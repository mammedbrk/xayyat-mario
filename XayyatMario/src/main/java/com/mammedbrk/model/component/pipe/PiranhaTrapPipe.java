package com.mammedbrk.model.component.pipe;

import com.mammedbrk.model.component.enemy.Piranha;

public class PiranhaTrapPipe extends Pipe implements Piranha {
    private boolean alive;
    private boolean shown;
    private int time;


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
        if (shown && time == 3) { // todo read from config file
            shown = false;
        }
        else if (!shown && time == 2) { // todo read from config file
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

package com.mammedbrk.model.component.pipe;

import com.mammedbrk.model.Section;
import com.mammedbrk.model.component.enemy.Piranha;

public class TelePiranhaPipe extends TelePipe implements Piranha {
    private boolean alive;
    private boolean shown;
    private int time;
    private int showTime;
    private int hideTime;

    public TelePiranhaPipe() {
    }

    public TelePiranhaPipe(int x, int y, Section section) {
        super(x, y, section);
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

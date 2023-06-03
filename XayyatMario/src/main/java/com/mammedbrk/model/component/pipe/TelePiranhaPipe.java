package com.mammedbrk.model.component.pipe;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mammedbrk.model.Scene;
import com.mammedbrk.model.Section;
import com.mammedbrk.model.component.enemy.Piranha;

public class TelePiranhaPipe extends TelePipe implements Piranha {
    @JsonIgnore
    private int showTime;
    @JsonIgnore
    private int hideTime;
    private boolean alive;
    private boolean shown;
    private int time;

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

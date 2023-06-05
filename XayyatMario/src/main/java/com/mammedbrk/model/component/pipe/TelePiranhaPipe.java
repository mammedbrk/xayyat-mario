package com.mammedbrk.model.component.pipe;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mammedbrk.model.game.Section;
import com.mammedbrk.model.component.enemy.Piranha;

public class TelePiranhaPipe extends TelePipe implements Piranha {
    @JsonIgnore
    private static int showTime;
    @JsonIgnore
    private static int hideTime;
    private boolean alive;
    @JsonIgnore
    private boolean shown;
    @JsonIgnore
    private int time;

    public TelePiranhaPipe() {
    }

    public TelePiranhaPipe(double x, double y, Section section) {
        super(x, y, section);
        alive = true;
    }

    public TelePiranhaPipe(int x, int y, Section section, boolean alive) {
        super(x, y, section);
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
        TelePiranhaPipe.showTime = showTime;
    }

    public static void setHideTime(int hideTime) {
        TelePiranhaPipe.hideTime = hideTime;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}

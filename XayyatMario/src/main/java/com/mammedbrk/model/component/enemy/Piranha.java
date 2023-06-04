package com.mammedbrk.model.component.enemy;

import com.mammedbrk.model.interfaces.Movable;
import com.mammedbrk.model.interfaces.Timer;

public interface Piranha extends Timer {
    boolean isAlive();
    void die();
    boolean isShown();
}

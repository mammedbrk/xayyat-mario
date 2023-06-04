package com.mammedbrk.model.component.enemy;

public interface Piranha {
    boolean isAlive();
    void die();
    boolean isShown();
    void changeVisibility();
    void resetTime();
    void increaseTime();
}

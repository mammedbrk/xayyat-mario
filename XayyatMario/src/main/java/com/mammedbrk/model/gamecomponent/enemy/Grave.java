package com.mammedbrk.model.gamecomponent.enemy;

// Grave is a fixed enemy like plant in SuperMario
public class Grave extends Enemy {
    public Grave() {
    }

    public Grave(int x, int y) {
        super(x, y, "src/main/resources/com.mammedbrk/image/game/grave.png");
    }

    @Override
    public double scaleX() {
        return 0.8;
    }
}

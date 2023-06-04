package com.mammedbrk.model.component.enemy;

// Grave is a fixed enemy like plant in SuperMario
public class Grave extends Enemy {
    public Grave() {
    }

    public Grave(int x, int y) {
        super(x, y, "src/main/resources/com.mammedbrk/image/game/grave.png", 0, 0.7);
    }

    @Override
    public double scaleX() {
        return 0.8;
    }

    @Override
    public void modifySpeed() {
//        if (Math.abs(yCurrent - y) >= 1) {
        if (yCurrent - y >= 2) {
            yVelocity = -0.5;
        }
        if (yCurrent - y <= -1) {
            yVelocity = 0.7;
        }
    }
}

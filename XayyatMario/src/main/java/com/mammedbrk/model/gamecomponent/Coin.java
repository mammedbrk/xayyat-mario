package com.mammedbrk.model.gamecomponent;

public class Coin extends Tile {
    private int value;

    public Coin(int x, int y) {
        super(x, y, "src/main/resources/com.mammedbrk/image/game/coin.png");
        this.value = 1;
    }

    public Coin(int x, int y, int value) {
        this(x, y);
        this.value = value;
    }

    @Override
    public double getScaleX() {
        return 0.6;
    }

    @Override
    public double getScaleY() {
        return 0.6;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

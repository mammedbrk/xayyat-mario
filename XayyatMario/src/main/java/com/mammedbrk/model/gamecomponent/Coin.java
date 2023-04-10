package com.mammedbrk.model.gamecomponent;

import com.mammedbrk.model.tile.Tile;

public class Coin extends Tile {
    private int value;

    public Coin(int x, int y, int value) {
        super(x, y);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

package com.mammedbrk.model.gamecomponent;

import com.mammedbrk.model.tile.Tile;

public class Coin extends GameComponent {
    private int value;

    public Coin(Tile tile) {
        super(tile);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

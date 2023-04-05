package com.mammedbrk.model.gamecomponent.block;

import com.mammedbrk.model.tile.Tile;

public class CoinBlock extends Block {
    private int value;
    public CoinBlock(Tile tile) {
        super(tile);
        value = 1;
    }

    public CoinBlock(Tile tile, int value) {
        super(tile);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

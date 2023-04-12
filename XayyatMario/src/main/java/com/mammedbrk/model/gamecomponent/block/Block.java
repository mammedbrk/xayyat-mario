package com.mammedbrk.model.gamecomponent.block;

import com.mammedbrk.model.gamecomponent.Tile;

public abstract class Block extends Tile {
    public Block(int x, int y, String imageAddress) {
        super(x, y, imageAddress);
    }
}

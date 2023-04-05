package com.mammedbrk.model.gamecomponent.block;

import com.mammedbrk.model.gamecomponent.GameComponent;
import com.mammedbrk.model.tile.Tile;

public abstract class Block extends GameComponent {
    public Block(Tile tile) {
        super(tile);
    }
}

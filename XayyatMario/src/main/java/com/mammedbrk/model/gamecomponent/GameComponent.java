package com.mammedbrk.model.gamecomponent;

import com.mammedbrk.model.tile.Tile;

public abstract class GameComponent {
    protected final Tile tile;

    protected GameComponent(Tile tile) {
        this.tile = tile;
    }

    public Tile getTile() {
        return tile;
    }
}

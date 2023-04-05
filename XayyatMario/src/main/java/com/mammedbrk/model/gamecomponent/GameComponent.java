package com.mammedbrk.model.gamecomponent;

import com.mammedbrk.model.tile.Tile;

public class GameComponent {
    protected Tile tile;

    public Tile getTile() {
        return tile;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }
}

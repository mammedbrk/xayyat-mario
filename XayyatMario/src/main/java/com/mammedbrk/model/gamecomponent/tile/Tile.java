package com.mammedbrk.model.gamecomponent.tile;

import com.mammedbrk.model.gamecomponent.Coin;
import com.mammedbrk.model.gamecomponent.block.Block;

public class Tile {
    private final int x, y;
    private final TileType type;
    private Block block;
    private Coin coin;

    public Tile(int x, int y, TileType type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    // Getters and setters

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public TileType getType() {
        return type;
    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public Coin getCoin() {
        return coin;
    }

    public void setCoin(Coin coin) {
        this.coin = coin;
    }
}

package com.mammedbrk.model.gamecomponent;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.mammedbrk.model.gamecomponent.block.Block;
import com.mammedbrk.model.gamecomponent.block.Pipe;
import com.mammedbrk.model.gamecomponent.enemy.Enemy;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Block.class, name = "block"),
        @JsonSubTypes.Type(value = Enemy.class, name = "enemy"),
        @JsonSubTypes.Type(value = Coin.class, name = "coin"),
        @JsonSubTypes.Type(value = Pipe.class, name = "pipe"),
})
public abstract class Tile {
    public static int TILE_SIZE = 60;
    protected int x, y;
    protected String imageAddress;

    public Tile() {
    }

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Tile(int x, int y, String imageAddress) {
        this.x = x;
        this.y = y;
        this.imageAddress = imageAddress;
    }

    public double scaleX() {
        return 1;
    }

    public double scaleY() {
        return 1;
    }

    // Getters and setters

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getImageAddress() {
        return imageAddress;
    }

    public void setImageAddress(String imageAddress) {
        this.imageAddress = imageAddress;
    }
}

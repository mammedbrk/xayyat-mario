package com.mammedbrk.model.gamecomponent.block;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.mammedbrk.model.gamecomponent.Tile;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = OrdinaryBlock.class, name = "ordinaryBlock"),
        @JsonSubTypes.Type(value = CoinBlock.class, name = "coinBlock"),
        @JsonSubTypes.Type(value = PowerUpBlock.class, name = "powerUpBlock"),
})
public abstract class Block extends Tile {
    public Block() {
    }

    public Block(int x, int y, String imageAddress) {
        super(x, y, imageAddress);
    }
}

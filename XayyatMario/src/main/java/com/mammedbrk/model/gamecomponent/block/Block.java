package com.mammedbrk.model.gamecomponent.block;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.mammedbrk.model.gamecomponent.Tile;

import java.nio.channels.Pipe;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = OrdinaryBlock.class, name = "ordinaryBlock"),
        @JsonSubTypes.Type(value = EarthBlock.class, name = "earthBlock"),
        @JsonSubTypes.Type(value = CoinBlock.class, name = "coinBlock"),
        @JsonSubTypes.Type(value = PowerUpBlock.class, name = "powerUpBlock"),
        @JsonSubTypes.Type(value = Pipe.class, name = "pipe"),
})
public abstract class Block extends Tile {
    public Block() {
    }

    public Block(int x, int y, String imageAddress) {
        super(x, y, imageAddress);
    }
}

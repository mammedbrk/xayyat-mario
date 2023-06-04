package com.mammedbrk.model.component.item;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.mammedbrk.model.component.Component;
import com.mammedbrk.model.component.block.*;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Coin.class, name = "COIN"),
        @JsonSubTypes.Type(value = Star.class, name = "STAR"),
        @JsonSubTypes.Type(value = Mushroom.class, name = "MUSHROOM"),
        @JsonSubTypes.Type(value = Flower.class, name = "FLOWER")
})
public abstract class Item extends Component {
    @JsonIgnore
    private boolean hit;

    public Item() {
    }

    public Item(double x, double y) {
        super(x, y);
    }

    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }
}

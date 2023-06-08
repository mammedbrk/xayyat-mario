package com.mammedbrk.model.component.block;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.mammedbrk.model.component.Component;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = SimpleBlock.class, name = "SIMPLE"),
        @JsonSubTypes.Type(value = CoinBlock.class, name = "COIN"),
        @JsonSubTypes.Type(value = CoinsBlock.class, name = "COINS"),
        @JsonSubTypes.Type(value = EmptyBlock.class, name = "EMPTY"),
        @JsonSubTypes.Type(value = QuestionBlock.class, name = "QUESTION")
})
public abstract class Block extends Component {
    public Block() {
    }

    public Block(double x, double y) {
        super(x, y);
    }
}

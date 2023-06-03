package com.mammedbrk.model.component.block;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.mammedbrk.model.component.Component;

import java.nio.channels.Pipe;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
})
public abstract class Block extends Component {
    public Block() {
    }

    public Block(int x, int y) {
        super(x, y);
    }
}

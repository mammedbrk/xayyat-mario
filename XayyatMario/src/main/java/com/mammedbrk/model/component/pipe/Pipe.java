package com.mammedbrk.model.component.pipe;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.mammedbrk.model.component.Component;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = SimplePipe.class, name = "SIMPLE"),
        @JsonSubTypes.Type(value = TeleSimplePipe.class, name = "TELE_SIMPLE"),
        @JsonSubTypes.Type(value = PiranhaTrapPipe.class, name = "PIRANHA_TRAP"),
        @JsonSubTypes.Type(value = TelePiranhaPipe.class, name = "TELE_PIRANHA")
})
public abstract class Pipe extends Component {
    public Pipe() {
    }

    public Pipe(double x, double y) {
        super(x, y);
    }

    @Override
    public String getType() {
        return "Pipe";
    }
}

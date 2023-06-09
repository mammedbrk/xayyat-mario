package com.mammedbrk.model.component.enemy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.mammedbrk.model.component.Component;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Goomba.class, name = "GOOMBA"),
        @JsonSubTypes.Type(value = Koopa.class, name = "KOOPA"),
        @JsonSubTypes.Type(value = Spiny.class, name = "SPINY")
})
public abstract class Enemy extends Component {
    @JsonIgnore
    protected boolean alive;

    public Enemy() {
        alive = true;
    }

    public Enemy(double x, double y) {
        super(x, y);
        alive = true;
    }

    @Override
    public String getType() {
        return this.getClass().getSimpleName();
    }

    public void die() {
        alive = false;
        System.out.println(this);
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}

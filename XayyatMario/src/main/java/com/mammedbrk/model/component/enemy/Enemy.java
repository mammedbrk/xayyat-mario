package com.mammedbrk.model.component.enemy;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.mammedbrk.model.component.Component;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
})
public abstract class Enemy extends Component {
    protected boolean alive;

    public Enemy() {
    }

    public Enemy(int x, int y) {
        super(x, y);
        alive = true;
    }

    public void die() {
        alive = false;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}

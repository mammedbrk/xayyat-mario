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
        @JsonSubTypes.Type(value = Grave.class, name = "grave")
})
public abstract class Enemy extends Component {
    protected int xCurrent, yCurrent;
    protected double xVelocity, yVelocity;

    public Enemy() {
    }

    public Enemy(int x, int y, String imageAddress, double xVelocity, double yVelocity) {
        super(x, y, imageAddress);
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
        this.xCurrent = x;
        this.yCurrent = y;
    }

    public abstract void modifySpeed();


    public double getxVelocity() {
        return xVelocity;
    }

    public void setxVelocity(double xVelocity) {
        this.xVelocity = xVelocity;
    }

    public double getyVelocity() {
        return yVelocity;
    }

    public void setyVelocity(double yVelocity) {
        this.yVelocity = yVelocity;
    }

    public int getxCurrent() {
        return xCurrent;
    }

    public void setxCurrent(int xCurrent) {
        this.xCurrent = xCurrent;
    }

    public int getyCurrent() {
        return yCurrent;
    }

    public void setyCurrent(int yCurrent) {
        this.yCurrent = yCurrent;
    }
}

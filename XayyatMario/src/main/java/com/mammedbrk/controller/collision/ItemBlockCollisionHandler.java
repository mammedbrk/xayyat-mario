package com.mammedbrk.controller.collision;

import com.mammedbrk.controller.CollisionSystem;
import com.mammedbrk.model.component.Component;
import com.mammedbrk.model.interfaces.Gravitational;
import com.mammedbrk.model.interfaces.Movable;

public class ItemBlockCollisionHandler implements CollisionHandler {
    private CollisionSystem collisionSystem = new CollisionSystem();

    @Override
    public void handle(Component item, Component block) {
        switch (collisionSystem.checkCollision(item, block)) {
            case bottom_to_top -> {
                if (item instanceof Gravitational) {
                    item.setY(block.getY() + 1.);
                    ((Gravitational) item).resetGravity();
                }
            }
            case left_to_right -> {
                if (item instanceof Movable) {
                    ((Movable) item).changeDirection();
                    item.setX(block.getX() + 1.);
                }
            }
            case right_to_left -> {
                if (item instanceof Movable) {
                    ((Movable) item).changeDirection();
                    item.setX(block.getX() - 1.);
                }
            }
            case top_to_bottom -> {
                if (item instanceof Gravitational) {
                    item.setY(block.getY() - 1.);
                    ((Gravitational) item).resetGravity();
                }
            }
        }
    }
}

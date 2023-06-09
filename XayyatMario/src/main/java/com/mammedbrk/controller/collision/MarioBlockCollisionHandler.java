package com.mammedbrk.controller.collision;

import com.mammedbrk.model.component.Component;
import com.mammedbrk.model.interfaces.Gravitational;
import com.mammedbrk.model.interfaces.Hittable;

public class MarioBlockCollisionHandler implements CollisionHandler {
    private CollisionSystem collisionSystem = new CollisionSystem();

    @Override
    public void handle(Component mario, Component block) {
        switch (collisionSystem.checkCollision(mario, block)) {
            case top_to_bottom -> {
                mario.setY(block.getY() - 1);
                ((Gravitational) mario).resetGravity();
                if (block instanceof Hittable)
                    ((Hittable) block).hit();
            }
            case bottom_to_top -> {
                mario.setY(block.getY() + 1.);
                ((Gravitational) mario).resetGravity();
            }
            case right_to_left -> mario.setX(block.getX() - 1);
            case left_to_right -> mario.setX(block.getX() + 1);
        }
    }
}

package com.mammedbrk.controller.collision;

import com.mammedbrk.controller.CollisionSystem;
import com.mammedbrk.model.component.Component;
import com.mammedbrk.model.interfaces.Gravitational;
import com.mammedbrk.model.interfaces.Hittable;
import com.mammedbrk.model.interfaces.Movable;

public class EnemyBlockCollisionHandler implements CollisionHandler {
    private CollisionSystem collisionSystem = new CollisionSystem();

    @Override
    public void handle(Component enemy, Component block) {
        switch (collisionSystem.checkCollision(enemy, block)) {
            case bottom_to_top -> {
                if (enemy instanceof Gravitational) {
                    enemy.setY(block.getY() + 1.);
                    ((Gravitational) enemy).resetGravity();
                }
            }
            case left_to_right -> {
                if (enemy instanceof Movable) {
                    ((Movable) enemy).changeDirection();
                    enemy.setX(block.getX() + 1.);
                }
            }
            case right_to_left -> {
                if (enemy instanceof Movable) {
                    ((Movable) enemy).changeDirection();
                    enemy.setX(block.getX() - 1.);
                }
            }
            case top_to_bottom -> {
                if (enemy instanceof Gravitational) {
                    enemy.setY(block.getY() - 1.);
                    ((Gravitational) enemy).resetGravity();
                }
            }
        }
    }
}

package com.mammedbrk.controller.collision;

import com.mammedbrk.model.component.Component;
import com.mammedbrk.model.interfaces.Hittable;
import com.mammedbrk.model.interfaces.Movable;

public class EnemyEnemyCollisionHandler implements CollisionHandler {
    private CollisionSystem collisionSystem = new CollisionSystem();

    @Override
    public void handle(Component enemy1, Component enemy2) {
        switch (collisionSystem.checkCollision(enemy1, enemy2)) {
            case bottom_to_top -> {
                if (enemy2 instanceof Hittable)
                    ((Hittable) enemy2).hit();
            }
            case right_to_left -> {
                if (enemy1 instanceof Movable) {
                    ((Movable) enemy2).changeDirection();
                    ((Movable) enemy1).changeDirection();
                    enemy2.setX(enemy1.getX() + 1.);
                }
            }
        }
    }
}

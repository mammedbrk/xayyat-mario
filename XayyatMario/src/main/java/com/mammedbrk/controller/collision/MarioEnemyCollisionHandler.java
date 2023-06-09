package com.mammedbrk.controller.collision;

import com.mammedbrk.controller.CollisionSystem;
import com.mammedbrk.model.component.Component;
import com.mammedbrk.model.interfaces.Hittable;

public class MarioEnemyCollisionHandler implements CollisionHandler {
    private CollisionSystem collisionSystem = new CollisionSystem();

    @Override
    public void handle(Component mario, Component enemy) {
        switch (collisionSystem.checkCollision(mario, enemy)) {
            case bottom_to_top -> {
                if (enemy instanceof Hittable)
                    ((Hittable) enemy).hit();
                else {
                    // todo kill mario
                }
            }
            case top_to_bottom, right_to_left, left_to_right -> {
                // todo kill mario
            }
        }
    }
}

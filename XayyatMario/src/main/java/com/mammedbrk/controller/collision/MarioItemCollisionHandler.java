package com.mammedbrk.controller.collision;

import com.mammedbrk.model.component.Component;
import com.mammedbrk.model.component.item.*;

public class MarioItemCollisionHandler implements CollisionHandler {
    private CollisionSystem collisionSystem = new CollisionSystem();

    @Override
    public void handle(Component mario, Component item) {
        if (collisionSystem.checkCollision(mario, item) != CollisionDirection.none) {
            ((Item) item).setHit(true);
            // todo power up
        }
    }
}

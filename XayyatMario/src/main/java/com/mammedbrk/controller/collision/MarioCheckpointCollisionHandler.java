package com.mammedbrk.controller.collision;

import com.mammedbrk.model.component.Component;

public class MarioCheckpointCollisionHandler implements CollisionHandler {
    private CollisionSystem collisionSystem = new CollisionSystem();

    @Override
    public void handle(Component mario, Component checkpoint) {
        if (collisionSystem.checkCollision(mario, checkpoint) != CollisionDirection.none) {
            // todo save game or ...
        }
    }
}

package com.mammedbrk.controller.collision;

import com.mammedbrk.controller.CollisionDirection;
import com.mammedbrk.controller.CollisionSystem;
import com.mammedbrk.model.component.Component;
import com.mammedbrk.model.interfaces.Gravitational;
import com.mammedbrk.model.interfaces.Hittable;

public class MarioCheckpointCollisionHandler implements CollisionHandler {
    private CollisionSystem collisionSystem = new CollisionSystem();

    @Override
    public void handle(Component mario, Component checkpoint) {
        if (collisionSystem.checkCollision(mario, checkpoint) != CollisionDirection.none) {
            // todo save game or ...
        }
    }
}

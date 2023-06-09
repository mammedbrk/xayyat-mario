package com.mammedbrk.controller.collision;

import com.mammedbrk.model.component.Component;

import java.util.HashMap;

public class CollisionManager {
    private HashMap<String, CollisionHandler> collisionHandlers;

    public CollisionManager() {
        collisionHandlers = new HashMap<>();
        collisionHandlers.put("MarioBlock", new MarioBlockCollisionHandler());
        collisionHandlers.put("MarioPipe", new MarioPipeCollisionHandler());
        collisionHandlers.put("MarioCheckpoint", new MarioCheckpointCollisionHandler());
        collisionHandlers.put("MarioEnemy", new MarioEnemyCollisionHandler());
        collisionHandlers.put("MarioItem", new MarioItemCollisionHandler());
        collisionHandlers.put("EnemyBlock", new EnemyBlockCollisionHandler());
        collisionHandlers.put("EnemyPipe", new EnemyPipeCollisionHandler());
        collisionHandlers.put("EnemyEnemy", new EnemyEnemyCollisionHandler());
        collisionHandlers.put("ItemBlock", new ItemBlockCollisionHandler());
        collisionHandlers.put("ItemPipe", new ItemPipeCollisionHandler());
    }

    public void handleCollision(Component component1, Component component2) {
        String collisionKey = component1.getType() + component2.getType();
        CollisionHandler collisionHandler = collisionHandlers.get(collisionKey);
        if (collisionHandler != null) {
            collisionHandler.handle(component1, component2);
        }
    }
}


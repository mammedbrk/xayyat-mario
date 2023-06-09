package com.mammedbrk.controller.collision;

import com.mammedbrk.controller.CollisionDirection;
import com.mammedbrk.controller.CollisionSystem;
import com.mammedbrk.model.component.Checkpoint;
import com.mammedbrk.model.component.Mario;
import com.mammedbrk.model.component.block.Block;
import com.mammedbrk.model.component.enemy.Enemy;
import com.mammedbrk.model.component.item.Item;
import com.mammedbrk.model.interfaces.Gravitational;
import com.mammedbrk.model.interfaces.Hittable;
import com.mammedbrk.model.interfaces.Movable;

import java.util.List;

public class CheckpointCollisionVisitor implements CollisionVisitor {
    private final CollisionSystem collisionSystem = new CollisionSystem();
    private List<Checkpoint> checkpoints;

    @Override
    public void visit(Mario mario) {
        for (Checkpoint checkpoint: checkpoints) {
            if (collisionSystem.checkCollision(mario, checkpoint) != CollisionDirection.none) {

            }
        }
    }

    @Override
    public void visit(Enemy enemy) {
    }

    @Override
    public void visit(Item item) {
    }

    public void setBlocks(List<Checkpoint> checkpoints) {
        this.checkpoints = checkpoints;
    }
}

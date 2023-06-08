package com.mammedbrk.controller.collision;

import com.mammedbrk.model.component.*;
import com.mammedbrk.model.component.enemy.Enemy;
import com.mammedbrk.model.component.item.Item;

public interface CollisionVisitor {
    void visit(Mario mario);
    void visit(Enemy enemy);
    void visit(Item item);
}

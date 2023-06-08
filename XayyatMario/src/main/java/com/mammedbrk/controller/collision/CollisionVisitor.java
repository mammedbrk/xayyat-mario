package com.mammedbrk.controller.collision;

import com.mammedbrk.model.component.*;
import com.mammedbrk.model.component.enemy.Enemy;
import com.mammedbrk.model.component.item.Item;

public interface CollisionVisitor<E> {
    void visit(E e, Mario mario);
    void visit(E e, Enemy enemy);
    void visit(E e, Item item);
}

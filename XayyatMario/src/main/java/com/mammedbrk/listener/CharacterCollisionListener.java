package com.mammedbrk.listener;

import com.mammedbrk.controller.GameController;
import com.mammedbrk.current.Current;
import com.mammedbrk.event.CharacterCollisionEvent;
import com.mammedbrk.event.CharacterMovementEvent;

public class CharacterCollisionListener {
    private final GameController controller = Current.controller;

    public CharacterMovementEvent listen(CharacterCollisionEvent characterMovementEvent) { // todo return movement things
        return controller.moveAndCollision(characterMovementEvent);
    }
}

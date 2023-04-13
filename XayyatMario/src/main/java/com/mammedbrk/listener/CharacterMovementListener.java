package com.mammedbrk.listener;

import com.mammedbrk.controller.GameController;
import com.mammedbrk.current.Current;
import com.mammedbrk.event.CharacterMovementEvent;

public class CharacterMovementListener {
    private final GameController controller = Current.controller;

    public boolean listen(CharacterMovementEvent characterMovementEvent) { // todo return movement things
        return controller.moveAndCollision(characterMovementEvent);
    }
}

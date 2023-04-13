package com.mammedbrk.listener;

import com.mammedbrk.controller.UserController;
import com.mammedbrk.current.Current;
import com.mammedbrk.event.CharacterEvent;

public class CharacterBuyListener {
    private final UserController controller = new UserController();

    public boolean listen(CharacterEvent characterEvent) {
        return controller.buyCharacter(Current.user, characterEvent.getCharacter());
    }
}

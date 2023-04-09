package com.mammedbrk.listener;

import com.mammedbrk.controller.UserController;
import com.mammedbrk.current.Current;
import com.mammedbrk.event.CharacterEvent;

public class CharacterBuyListener implements Listener<CharacterEvent> {
    private final UserController controller = new UserController();
    @Override
    public boolean listen(CharacterEvent characterEvent) {
        return controller.buyCharacter(Current.user, characterEvent.getCharacter());
    }
}

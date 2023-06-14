package com.mammedbrk.listener;

import com.mammedbrk.controller.UserController;
import com.mammedbrk.current.Current;
import com.mammedbrk.event.CharacterEvent;

public class CharacterChooseListener {
    private final UserController controller = new UserController();

    public void listen(CharacterEvent characterEvent) {
//        controller.chooseCharacter(Current.user, characterEvent.getCharacter());
    }
}

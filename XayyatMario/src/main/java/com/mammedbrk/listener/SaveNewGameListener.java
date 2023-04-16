package com.mammedbrk.listener;

import com.mammedbrk.controller.UserController;
import com.mammedbrk.current.Current;

public class SaveNewGameListener {
    private final UserController controller = new UserController();

    public void listen(int index) {
        controller.saveNewGame(Current.user, index);
    }
}

package com.mammedbrk.listener;

import com.mammedbrk.controller.UserController;
import com.mammedbrk.current.Current;

public class SaveExistingGameListener {
    private final UserController controller = new UserController();

    public void listen() {
        controller.updateUser(Current.user);
    }
}

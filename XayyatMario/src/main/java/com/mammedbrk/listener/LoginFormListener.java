package com.mammedbrk.listener;

import com.mammedbrk.controller.UserController;
import com.mammedbrk.event.FormEvent;

public class LoginFormListener {
    private final UserController controller = new UserController();

    public boolean listen(FormEvent e) {
        return controller.login(e) != null;
    }
}

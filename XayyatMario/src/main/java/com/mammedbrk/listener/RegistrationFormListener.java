package com.mammedbrk.listener;

import com.mammedbrk.controller.UserController;
import com.mammedbrk.event.FormEvent;

public class RegistrationFormListener {
    private final UserController controller = new UserController();

    public boolean listen(FormEvent e) {
        return controller.register(e) != null;
    }
}

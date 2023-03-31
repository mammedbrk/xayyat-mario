package com.mammedbrk.listener;

import com.mammedbrk.controller.UserController;
import com.mammedbrk.current.Current;
import com.mammedbrk.event.FormEvent;

public class RegistrationFormListener implements Listener<FormEvent> {
    private final UserController controller = new UserController();

    @Override
    public boolean listen(FormEvent e) {
        return false;
    }
}

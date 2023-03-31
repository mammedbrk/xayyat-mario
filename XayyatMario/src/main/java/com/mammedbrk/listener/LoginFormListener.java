package com.mammedbrk.listener;

import com.mammedbrk.controller.UserController;
import com.mammedbrk.current.Current;
import com.mammedbrk.event.FormEvent;

public class LoginFormListener implements Listener<FormEvent> {
    private final UserController controller = new UserController();

    @Override
    public boolean listen(FormEvent e) {
        Current.user = controller.login(e);
        return Current.user != null;
    }
}

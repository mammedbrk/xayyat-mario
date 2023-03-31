package com.mammedbrk.controller;

import com.mammedbrk.access.UserAccess;
import com.mammedbrk.event.FormEvent;
import com.mammedbrk.model.User;

public class UserController {
    UserAccess access = new UserAccess();

    public User register(FormEvent e) {
        if (access.get(e.getUsername()) != null) {
            return null;
        }
        User user = new User(access.lastId() + 1, e.getUsername(), e.getPassword());
        access.add(user);
        return user;
    }

    public User login(FormEvent e) {
        User user = access.get(e.getUsername());
        if (user != null && user.getPassword().equals(e.getPassword())) {
            return user;
        }
        return null;
    }
}

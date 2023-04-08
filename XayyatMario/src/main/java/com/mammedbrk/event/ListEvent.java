package com.mammedbrk.event;

import com.mammedbrk.model.User;

import java.util.EventObject;
import java.util.List;

public class ListEvent extends EventObject {
    private List<User> users;

    public ListEvent(Object source, List<User> users) {
        super(source);
        this.users = users;
    }

    // Getters and setters

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}

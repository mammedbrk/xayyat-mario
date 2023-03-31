package com.mammedbrk.event;

import java.util.EventObject;

public class FormEvent extends EventObject {
    private String username;
    private String password;

    public FormEvent(Object source, String username, String password) {
        super(source);
        this.username = username;
        this.password = password;
    }

    // Getters and setters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

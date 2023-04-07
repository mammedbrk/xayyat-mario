package com.mammedbrk.view.menu;

import com.mammedbrk.listener.Listener;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

import java.util.List;

public class MainMenuHeaderView {
    private List<Listener<String>> listeners;

    public void addListener(Listener<String> listener) {
        listeners.add(listener);
    }

    public void profileBtnClicked(MouseEvent mouseEvent) {
        System.out.println("profile");
    }

    public void shopBtnClicked(ActionEvent event) {
        System.out.println("shop");
    }

    // Getters and setters

    public List<Listener<String>> getListeners() {
        return listeners;
    }

    public void setListeners(List<Listener<String>> listeners) {
        this.listeners = listeners;
    }
}

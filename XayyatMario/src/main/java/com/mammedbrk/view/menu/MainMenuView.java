package com.mammedbrk.view.menu;

import com.mammedbrk.listener.Listener;
import javafx.event.ActionEvent;

import java.util.LinkedList;
import java.util.List;

public class MainMenuView {
    private List<Listener<String>> listeners = new LinkedList<>();

    public void addListener(Listener<String> listener) {
        listeners.add(listener);
    }

    public void startBtnClicked(ActionEvent event) {
        for (Listener<String> listener: listeners) {
            listener.listen("NewGameSetupView");
        }
    }

    public void continueBtnClicked(ActionEvent event) {
        for (Listener<String> listener: listeners) {
            listener.listen("PreGameSetupView");
        }
    }

    public void rankingBtnClicked(ActionEvent event) {
        for (Listener<String> listener: listeners) {
            listener.listen("RankingView");
        }
    }

    public void logoutBtnClicked(ActionEvent event) {
        for (Listener<String> listener: listeners) {
            listener.listen("LoginView");
        }
    }


    // Getters and setters

    public List<Listener<String>> getListeners() {
        return listeners;
    }

    public void setListeners(List<Listener<String>> listeners) {
        this.listeners = listeners;
    }
}

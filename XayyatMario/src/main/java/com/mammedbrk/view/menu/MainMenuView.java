package com.mammedbrk.view.menu;

import com.mammedbrk.listener.StringListener;
import javafx.event.ActionEvent;

import java.util.LinkedList;
import java.util.List;

public class MainMenuView {
    private List<StringListener> listeners = new LinkedList<>();

    public void addListener(StringListener listener) {
        listeners.add(listener);
    }

    public void startBtnClicked(ActionEvent event) {
        for (StringListener listener: listeners) {
            listener.listen("NewGameSetupView");
        }
    }

    public void continueBtnClicked(ActionEvent event) {
        for (StringListener listener: listeners) {
            listener.listen("PreGameSetupView");
        }
    }

    public void rankingBtnClicked(ActionEvent event) {
        for (StringListener listener: listeners) {
            listener.listen("RankingView");
        }
    }

    public void logoutBtnClicked(ActionEvent event) {
        for (StringListener listener: listeners) {
            listener.listen("LoginView");
        }
    }


    // Getters and setters

    public List<StringListener> getListeners() {
        return listeners;
    }

    public void setListeners(List<StringListener> listeners) {
        this.listeners = listeners;
    }
}

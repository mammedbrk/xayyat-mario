package com.mammedbrk.view.auth.listener;

import com.mammedbrk.event.FormEvent;
import com.mammedbrk.listener.Listener;
import com.mammedbrk.listener.LoginFormListener;
import com.mammedbrk.view.MainView;
import com.mammedbrk.view.auth.LoginView;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class RegistrationToLoginFormListener implements Listener<FormEvent> {
    private final MainView mainView;

    public RegistrationToLoginFormListener(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public boolean listen(FormEvent formEvent) {
        final FXMLLoader loginViewFxml = new FXMLLoader(getClass().getResource("/com.mammedbrk/fxml/auth/login-view.fxml"));
        try {
            mainView.setCenter(loginViewFxml.load());
            LoginView loginView = loginViewFxml.getController();
            loginView.setUsername(formEvent.getUsername());
            loginView.setPassword(formEvent.getPassword());
            loginView.setLoginFormListener(new LoginFormListener());
            loginView.setLoginToRegistrationFormListener(new LoginToRegistrationFormListener(mainView));
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

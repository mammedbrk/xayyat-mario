package com.mammedbrk.view.auth.listener;

import com.mammedbrk.event.FormEvent;
import com.mammedbrk.listener.Listener;
import com.mammedbrk.listener.RegistrationFormListener;
import com.mammedbrk.view.MainView;
import com.mammedbrk.view.auth.RegistrationView;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class LoginToRegistrationFormListener implements Listener<FormEvent> {
    private final MainView mainView;

    public LoginToRegistrationFormListener(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public boolean listen(FormEvent formEvent) {
        final FXMLLoader registrationViewFxml = new FXMLLoader(getClass().getResource("/com.mammedbrk/fxml/auth/registration-view.fxml"));
        try {
            mainView.setCenter(registrationViewFxml.load());
            RegistrationView registrationView = registrationViewFxml.getController();
            registrationView.setUsername(formEvent.getUsername());
            registrationView.setPassword(formEvent.getPassword());
            registrationView.setRegistrationToLoginFormListener(new RegistrationToLoginFormListener(mainView));
            registrationView.setRegistrationFormListener(new RegistrationFormListener());
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

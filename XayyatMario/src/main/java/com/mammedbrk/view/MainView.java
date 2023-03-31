package com.mammedbrk.view;

import com.mammedbrk.event.FormEvent;
import com.mammedbrk.listener.Listener;
import com.mammedbrk.listener.LoginFormListener;
import com.mammedbrk.listener.view.LoginToRegistrationFormListener;
import com.mammedbrk.view.auth.LoginView;
import com.mammedbrk.view.auth.RegistrationView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class MainView extends BorderPane {
    public MainView() throws IOException {
        final FXMLLoader loginViewFxml = new FXMLLoader(getClass().getResource("/com.mammedbrk/fxml/auth/login-view.fxml"));
        this.setCenter(loginViewFxml.load());
        LoginView loginView = loginViewFxml.getController();
        loginView.setLoginFormListener(new LoginFormListener());
        loginView.setLoginToRegistrationFormListener(new LoginToRegistrationFormListener(this));
    }
}

package com.mammedbrk.view;

import com.mammedbrk.listener.LoginFormListener;
import com.mammedbrk.view.auth.LoginView;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class MainView extends BorderPane {
    public MainView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com.mammedbrk/fxml/auth/login-view.fxml"));
        setCenter(loader.load());
        ((LoginView) loader.getController()).setLoginFormListener(new LoginFormListener());
    }
}

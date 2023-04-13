package com.mammedbrk.view.auth;

import com.mammedbrk.event.FormEvent;
import com.mammedbrk.listener.LoginFormListener;
import com.mammedbrk.listener.StringListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.LinkedList;
import java.util.List;

public class LoginView {
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    private LoginFormListener loginFormListener;
    private List<StringListener> listeners = new LinkedList<>();

    public void addListener(StringListener listener) {
        listeners.add(listener);
    }

    public void signInClicked(ActionEvent e) {
        if (getUsername().isEmpty()) {
            username.setStyle("-fx-border-color: red;");
        }
        else {
            username.setStyle("-fx-border-color: transparent;");
        }
        if (getPassword().isEmpty()) {
            password.setStyle("-fx-border-color: red;");
        }
        else {
            password.setStyle("-fx-border-color: transparent;");
        }
        if (!getUsername().isEmpty() && !getPassword().isEmpty()) {
            FormEvent formEvent = new FormEvent(this, getUsername(), getPassword());
            if (loginFormListener.listen(formEvent)) {
                for (StringListener listener: listeners) {
                    listener.listen("MainMenu");
                }
                // System.out.println("Logged in!");
            }
            else {
                username.setStyle("-fx-border-color: red;");
                password.setStyle("-fx-border-color: red;");
            }
        }
    }

    public void signUpClicked(ActionEvent e) {
        for (StringListener listener: listeners) {
            listener.listen("RegistrationView");
        }
    }

    // Getters and setters

    public LoginFormListener getLoginFormListener() {
        return loginFormListener;
    }

    public void setLoginFormListener(LoginFormListener loginFormListener) {
        this.loginFormListener = loginFormListener;
    }

    public List<StringListener> getListeners() {
        return listeners;
    }

    public void setListeners(List<StringListener> listeners) {
        this.listeners = listeners;
    }

    public String getUsername() {
        return username.getText();
    }

    public void setUsername(String username) {
        this.username.setText(username);
    }

    public String getPassword() {
        return password.getText();
    }

    public void setPassword(String password) {
        this.password.setText(password);
    }
}

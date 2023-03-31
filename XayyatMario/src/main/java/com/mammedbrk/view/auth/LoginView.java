package com.mammedbrk.view.auth;

import com.mammedbrk.event.FormEvent;
import com.mammedbrk.listener.LoginFormListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.awt.*;

public class LoginView {
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    private LoginFormListener loginFormListener;


    public void signInClicked(ActionEvent e) {
        FormEvent formEvent = new FormEvent(this, getUsername(), getPassword());
        System.out.println(loginFormListener.listen(formEvent));
    }

    public void signUpClicked(ActionEvent e) {

    }

    // Getters and setters

    public LoginFormListener getLoginFormListener() {
        return loginFormListener;
    }

    public void setLoginFormListener(LoginFormListener loginFormListener) {
        this.loginFormListener = loginFormListener;
    }

    public String getUsername() {
        return username.getText();
    }

    public String getPassword() {
        return password.getText();
    }
}

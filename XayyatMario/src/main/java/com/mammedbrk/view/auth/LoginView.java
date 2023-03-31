package com.mammedbrk.view.auth;

import com.mammedbrk.event.FormEvent;
import com.mammedbrk.listener.Listener;
import com.mammedbrk.listener.LoginFormListener;
import com.mammedbrk.listener.view.LoginToRegistrationFormListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginView {
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    private LoginFormListener loginFormListener;
    private LoginToRegistrationFormListener loginToRegistrationFormListener;

    public void signInClicked(ActionEvent e) {
        FormEvent formEvent = new FormEvent(this, getUsername(), getPassword());
        System.out.println(loginFormListener.listen(formEvent)); // todo if false
    }

    public void signUpClicked(ActionEvent e) {
        FormEvent formEvent = new FormEvent(this, getUsername(), getPassword());
        loginToRegistrationFormListener.listen(formEvent);
    }

    // Getters and setters

    public LoginFormListener getLoginFormListener() {
        return loginFormListener;
    }

    public void setLoginFormListener(LoginFormListener loginFormListener) {
        this.loginFormListener = loginFormListener;
    }

    public LoginToRegistrationFormListener getLoginToRegistrationFormListener() {
        return loginToRegistrationFormListener;
    }

    public void setLoginToRegistrationFormListener(LoginToRegistrationFormListener loginToRegistrationFormListener) {
        this.loginToRegistrationFormListener = loginToRegistrationFormListener;
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

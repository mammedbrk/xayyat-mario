package com.mammedbrk.view.auth;

import com.mammedbrk.event.FormEvent;
import com.mammedbrk.listener.LoginFormListener;
import com.mammedbrk.view.auth.listener.LoginToRegistrationFormListener;
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
                // todo login to main menu of game
                System.out.println("Logged in!");
            }
            else {
                username.setStyle("-fx-border-color: red;");
                password.setStyle("-fx-border-color: red;");
            }
        }
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

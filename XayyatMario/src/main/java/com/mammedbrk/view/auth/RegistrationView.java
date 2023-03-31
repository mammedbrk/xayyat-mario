package com.mammedbrk.view.auth;

import com.mammedbrk.listener.Listener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.LinkedList;
import java.util.List;

public class RegistrationView {
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField confirmPassword;


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

    public String getConfirmPassword() {
        return confirmPassword.getText();
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword.setText(confirmPassword);
    }

    public void backClicked(ActionEvent e) {

    }

    public void signUpClicked(ActionEvent e) {

    }
}

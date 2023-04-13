package com.mammedbrk.view.auth;

import com.mammedbrk.event.FormEvent;
import com.mammedbrk.listener.RegistrationFormListener;
import com.mammedbrk.listener.StringListener;
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
    private RegistrationFormListener registrationFormListener;
    private List<StringListener> listeners = new LinkedList<>();


    public void addListener(StringListener listener) {
        listeners.add(listener);
    }

    public void backClicked(ActionEvent e) {
        for (StringListener listener: listeners) {
            listener.listen("LoginView");
        }
    }

    public void signUpClicked(ActionEvent e) {
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
        if (!getConfirmPassword().equals(getPassword())) {
            confirmPassword.setStyle("-fx-border-color: red;");
        }
        else {
            confirmPassword.setStyle("-fx-border-color: transparent;");
        }
        if (!getUsername().isEmpty() && !getPassword().isEmpty() && getConfirmPassword().equals(getPassword())) {
            FormEvent formEvent = new FormEvent(this, getUsername(), getPassword());
            if (registrationFormListener.listen(formEvent)) {
                for (StringListener listener: listeners) {
                    listener.listen("MainMenu");
                }
            }
            else {
                username.setStyle("-fx-border-color: red;");
            }
        }
    }

    // Getters and setters

    public RegistrationFormListener getRegistrationFormListener() {
        return registrationFormListener;
    }

    public void setRegistrationFormListener(RegistrationFormListener registrationFormListener) {
        this.registrationFormListener = registrationFormListener;
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

    public String getConfirmPassword() {
        return confirmPassword.getText();
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword.setText(confirmPassword);
    }

    public List<StringListener> getListeners() {
        return listeners;
    }

    public void setListeners(List<StringListener> listeners) {
        this.listeners = listeners;
    }
}

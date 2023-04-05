package com.mammedbrk.view.auth;

import com.mammedbrk.event.FormEvent;
import com.mammedbrk.listener.RegistrationFormListener;
import com.mammedbrk.view.auth.listener.RegistrationToLoginFormListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegistrationView {
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField confirmPassword;

    private RegistrationToLoginFormListener registrationToLoginFormListener;
    private RegistrationFormListener registrationFormListener;



    public void backClicked(ActionEvent e) {
        FormEvent formEvent = new FormEvent(this, getUsername(), getPassword());
        registrationToLoginFormListener.listen(formEvent);
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
                // todo login to main menu of game
                System.out.println("Registered!");
            }
            else {
                username.setStyle("-fx-border-color: red;");
            }
        }
    }


    public RegistrationToLoginFormListener getRegistrationToLoginFormListener() {
        return registrationToLoginFormListener;
    }

    public void setRegistrationToLoginFormListener(RegistrationToLoginFormListener registrationToLoginFormListener) {
        this.registrationToLoginFormListener = registrationToLoginFormListener;
    }

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
}

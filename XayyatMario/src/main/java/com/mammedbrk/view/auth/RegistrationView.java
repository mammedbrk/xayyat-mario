package com.mammedbrk.view.auth;

import com.mammedbrk.event.FormEvent;
import com.mammedbrk.listener.RegistrationFormListener;
import com.mammedbrk.listener.view.auth.RegistrationToLoginFormListener;
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
        FormEvent formEvent = new FormEvent(this, getUsername(), getPassword());
        registrationFormListener.listen(formEvent);
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

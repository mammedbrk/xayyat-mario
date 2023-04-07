package com.mammedbrk.view;

import com.mammedbrk.listener.Listener;
import com.mammedbrk.listener.LoginFormListener;
import com.mammedbrk.listener.RegistrationFormListener;
import com.mammedbrk.view.auth.LoginView;
import com.mammedbrk.view.auth.RegistrationView;
import com.mammedbrk.view.menu.MainMenuHeaderView;
import com.mammedbrk.view.menu.MainMenuView;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class MainView extends BorderPane {
    private final String dir = "/com.mammedbrk/fxml/";
    private FXMLLoader loginViewLoader;
    private FXMLLoader registrationViewLoader;
    private FXMLLoader mainMenuViewLoader;
    private FXMLLoader mainMenuHeaderViewLoader;

    public MainView() throws IOException {
        loginView();
    }


    public void loginView() throws IOException {
        loginViewLoader = new FXMLLoader(getClass().getResource(dir + "auth/login-view.fxml"));
        this.setCenter(loginViewLoader.load());
        LoginView loginView = loginViewLoader.getController();
        loginView.setLoginFormListener(new LoginFormListener());

        if (registrationViewLoader != null) {
            RegistrationView registrationView = registrationViewLoader.getController();
            loginView.setUsername(registrationView.getUsername());
            loginView.setPassword(registrationView.getPassword());
        }

        loginView.addListener(new Listener<String>() {
            @Override
            public boolean listen(String s) {
                if (s.equals("MainMenu")) {
                    try {
                        mainMenu();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                if (s.equals("RegistrationView")) {
                    try {
                        registrationView();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                return true;
            }
        });
    }

    public void registrationView() throws IOException {
        registrationViewLoader = new FXMLLoader(getClass().getResource(dir + "auth/registration-view.fxml"));
        this.setCenter(registrationViewLoader.load());
        RegistrationView registrationView = registrationViewLoader.getController();
        registrationView.setRegistrationFormListener(new RegistrationFormListener());

        if (loginViewLoader != null) {
            LoginView loginView = loginViewLoader.getController();
            registrationView.setUsername(loginView.getUsername());
            registrationView.setPassword(loginView.getPassword());
        }

        registrationView.addListener(new Listener<String>() {
            @Override
            public boolean listen(String s) {
                if (s.equals("LoginView")) {
                    try {
                        loginView();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                if (s.equals("MainMenu")) {
                    try {
                        mainMenu();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                return true;
            }
        });
    }

    public void mainMenu() throws IOException {
        mainMenuHeaderViewLoader = new FXMLLoader(getClass().getResource(dir + "menu/main-menu-header-view.fxml"));
        mainMenuViewLoader = new FXMLLoader(getClass().getResource(dir + "menu/main-menu-view.fxml"));
        this.setTop(mainMenuHeaderViewLoader.load());
        this.setCenter(mainMenuViewLoader.load());
        MainMenuHeaderView mainMenuHeaderView = mainMenuHeaderViewLoader.getController();
        MainMenuView mainMenuView = mainMenuViewLoader.getController();

    }
}

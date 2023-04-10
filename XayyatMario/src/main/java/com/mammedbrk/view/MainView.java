package com.mammedbrk.view;

import com.mammedbrk.listener.*;
import com.mammedbrk.view.auth.LoginView;
import com.mammedbrk.view.auth.RegistrationView;
import com.mammedbrk.view.menu.MainMenuHeaderView;
import com.mammedbrk.view.menu.MainMenuView;
import com.mammedbrk.view.ranking.RankingView;
import com.mammedbrk.view.shop.ShopView;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class MainView extends BorderPane {
    private final String dir = "/com.mammedbrk/fxml/";
    private FXMLLoader loginViewLoader;
    private FXMLLoader registrationViewLoader;
    private FXMLLoader mainMenuViewLoader;
    private FXMLLoader mainMenuHeaderViewLoader;
    private FXMLLoader rankingViewLoader;
    private FXMLLoader shopViewLoader;

    public MainView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(dir + "tmp.fxml"));
        this.setCenter(loader.load());
//        loginView();
    }


    private void loginView() throws IOException {
        resetPane();
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

    private void registrationView() throws IOException {
        resetPane();
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

    private void mainMenu() throws IOException {
        resetPane();
        mainMenuHeaderViewLoader = new FXMLLoader(getClass().getResource(dir + "menu/main-menu-header-view.fxml"));
        mainMenuViewLoader = new FXMLLoader(getClass().getResource(dir + "menu/main-menu-view.fxml"));
        this.setTop(mainMenuHeaderViewLoader.load());
        this.setCenter(mainMenuViewLoader.load());
        MainMenuHeaderView mainMenuHeaderView = mainMenuHeaderViewLoader.getController();
        MainMenuView mainMenuView = mainMenuViewLoader.getController();


        mainMenuView.addListener(new Listener<String>() {
            @Override
            public boolean listen(String s) {
                if (s.equals("LoginView")) {
                    try {
                        loginView();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                if (s.equals("RankingView")) {
                    try {
                        rankingView();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                if (s.equals("NewGameSetupView")) {
                    // todo
                }
                if (s.equals("PreGameSetupView")) {
                    // todo
                }
                return true;
            }
        });
        mainMenuHeaderView.addListener(new Listener<String>() {
            @Override
            public boolean listen(String s) {
                if (s.equals("ProfileView")) {
                    // todo
                }
                if (s.equals("ShopView")) {
                    try {
                        shopView();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                return true;
            }
        });
    }

    private void rankingView() throws IOException {
        resetPane();
        rankingViewLoader = new FXMLLoader(getClass().getResource(dir + "ranking/ranking-view.fxml"));
        this.setCenter(rankingViewLoader.load());
        RankingView rankingView = rankingViewLoader.getController();

        rankingView.addListener(new Listener<String>() {
            @Override
            public boolean listen(String s) {
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

    private void shopView() throws IOException {
        resetPane();
        shopViewLoader = new FXMLLoader(getClass().getResource(dir + "shop/shop-view.fxml"));
        this.setCenter(shopViewLoader.load());
        ShopView shopView = shopViewLoader.getController();
        shopView.setCharacterBuyListener(new CharacterBuyListener());
        shopView.addListener(new Listener<String>() {
            @Override
            public boolean listen(String s) {
                try {
                    mainMenu();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                return true;
            }
        });
    }

    private void resetPane() {
        this.setTop(null);
        this.setLeft(null);
        this.setCenter(null);
        this.setRight(null);
        this.setBottom(null);
    }
}

package com.mammedbrk.view;

import com.mammedbrk.controller.GameController;
import com.mammedbrk.current.Current;
import com.mammedbrk.listener.*;
import com.mammedbrk.view.auth.LoginView;
import com.mammedbrk.view.auth.RegistrationView;
import com.mammedbrk.view.game.GameHeaderView;
import com.mammedbrk.view.game.GameView;
import com.mammedbrk.view.game.NewGameSetupView;
import com.mammedbrk.view.game.PreGameSetupView;
import com.mammedbrk.view.menu.MainMenuHeaderView;
import com.mammedbrk.view.menu.MainMenuView;
import com.mammedbrk.view.profile.ProfileView;
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
    private FXMLLoader profileViewLoader;
    private FXMLLoader newGameSetupLoader;
    private FXMLLoader preGameSetupLoader;
    private FXMLLoader gameHeaderViewLoader;

    public MainView() throws IOException {
//        loginView();
        startGame(0);
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

        loginView.addListener(new StringListener() {
            @Override
            public void listen(String s) {
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

        registrationView.addListener(new StringListener() {
            @Override
            public void listen(String s) {
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


        mainMenuView.addListener(new StringListener() {
            @Override
            public void listen(String s) {
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
                    try {
                        newGameSetupView();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                if (s.equals("PreGameSetupView")) {
                    try {
                        preGameSetupView();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        mainMenuHeaderView.addListener(new StringListener() {
            @Override
            public void listen(String s) {
                if (s.equals("ProfileView")) {
                    try {
                        profileView();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                if (s.equals("ShopView")) {
                    try {
                        shopView();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    private void rankingView() throws IOException {
        resetPane();
        rankingViewLoader = new FXMLLoader(getClass().getResource(dir + "ranking/ranking-view.fxml"));
        this.setCenter(rankingViewLoader.load());
        RankingView rankingView = rankingViewLoader.getController();

        rankingView.addListener(new StringListener() {
            @Override
            public void listen(String s) {
                if (s.equals("MainMenu")) {
                    try {
                        mainMenu();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    private void shopView() throws IOException {
        resetPane();
        shopViewLoader = new FXMLLoader(getClass().getResource(dir + "shop/shop-view.fxml"));
        this.setCenter(shopViewLoader.load());
        ShopView shopView = shopViewLoader.getController();
        shopView.setCharacterBuyListener(new CharacterBuyListener());
        shopView.addListener(new StringListener() {
            @Override
            public void listen(String s) {
                if (s.equals("MainMenu")) {
                    try {
                        mainMenu();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    private void profileView() throws IOException {
        resetPane();
        profileViewLoader = new FXMLLoader(getClass().getResource(dir + "profile/profile-view.fxml"));
        this.setCenter(profileViewLoader.load());
        ProfileView profileView = profileViewLoader.getController();
        profileView.setCharacterChooseListener(new CharacterChooseListener());
        profileView.addListener(new StringListener() {
            @Override
            public void listen(String s) {
                if (s.equals("MainMenu")) {
                    try {
                        mainMenu();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    private void newGameSetupView() throws IOException {
        resetPane();
        newGameSetupLoader = new FXMLLoader(getClass().getResource(dir + "game/new-game-setup-view.fxml"));
        this.setCenter(newGameSetupLoader.load());
        NewGameSetupView newGameSetupView = newGameSetupLoader.getController();
        newGameSetupView.addListener(new StringListener() {
            @Override
            public void listen(String s) {
                if (s.equals("MainMenu")) {
                    try {
                        mainMenu();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                if (s.equals("0")) {
                    try {
                        startGame(0);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                if (s.equals("1")) {
                    try {
                        startGame(1);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                if (s.equals("2")) {
                    try {
                        startGame(2);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    private void startGame(int index) throws IOException {
        resetPane();
        gameHeaderViewLoader = new FXMLLoader(getClass().getResource(dir + "game/game-header-view.fxml"));
        this.setTop(gameHeaderViewLoader.load());
        GameHeaderView gameHeaderView = gameHeaderViewLoader.getController();

        GameView gameView = new GameView();
        GameController controller = new GameController(Current.user.getGames().get(index), gameView);
        this.setCenter(gameView);

        gameView.addListener(s -> {
            if (s.equals("MainMenu")) {
                try {
                    mainMenu();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void preGameSetupView() throws IOException {
        resetPane();
        preGameSetupLoader = new FXMLLoader(getClass().getResource(dir + "game/pre-game-setup-view.fxml"));
        this.setCenter(preGameSetupLoader.load());
        PreGameSetupView preGameSetupView = preGameSetupLoader.getController();

        preGameSetupView.addListener(new StringListener() {
            @Override
            public void listen(String s) {
                if (s.equals("MainMenu")) {
                    try {
                        System.out.println("golabi");
                        mainMenu();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                if (s.equals("0")) {
                    try {
                        startGame(0);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                if (s.equals("1")) {
                    try {
                        startGame(1);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                if (s.equals("2")) {
                    try {
                        startGame(2);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
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

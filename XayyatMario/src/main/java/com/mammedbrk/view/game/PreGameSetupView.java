package com.mammedbrk.view.game;

import com.mammedbrk.current.Current;
import com.mammedbrk.listener.StringListener;
import com.mammedbrk.model.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class PreGameSetupView implements Initializable {
    @FXML
    private Button gameBtn1;
    @FXML
    private Button gameBtn2;
    @FXML
    private Button gameBtn3;
    private final List<StringListener> listeners = new LinkedList<>();

    public void gameBtn1Clicked(ActionEvent event) { // TOF
        Current.user.setCurrentGame(Current.user.getGames().get(0));
        for (StringListener listener: listeners) {
            listener.listen("StartGame");
        }
    }

    public void gameBtn2Clicked(ActionEvent event) {
        Current.user.setCurrentGame(Current.user.getGames().get(1));
        for (StringListener listener: listeners) {
            listener.listen("StartGame");
        }
    }

    public void gameBtn3Clicked(ActionEvent event) {
        Current.user.setCurrentGame(Current.user.getGames().get(2));
        for (StringListener listener: listeners) {
            listener.listen("StartGame");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (Current.user.getGames().get(0).getCurrentLevel() != null) {
            gameBtn1.setText(buttonText(Current.user.getGames().get(0)));
        }
        else {
            gameBtn1.setText("No Game");
            gameBtn1.setDisable(true);
        }
        if (Current.user.getGames().get(1).getCurrentLevel() != null) {
            gameBtn2.setText(buttonText(Current.user.getGames().get(1)));
        }
        else {
            gameBtn2.setText("No Game");
            gameBtn2.setDisable(true);

        }
        if (Current.user.getGames().get(2).getCurrentLevel() != null) {
            gameBtn3.setText(buttonText(Current.user.getGames().get(2)));
        }
        else {
            gameBtn3.setText("No Game");
            gameBtn3.setDisable(true);

        }
    }

    private String buttonText(Game game) {
        return "L" + game.getCurrentLevel().getNo() +
                ", S" + game.getCurrentLevel().getCurrentSection().getNo() +
                " | score: " + (game.getScore() + game.getCurrentLevel().getScore() + game.getCurrentLevel().getCurrentSection().getScore()) +
                " | coins: " + (game.getCoins() + game.getCurrentLevel().getCoins() + game.getCurrentLevel().getCurrentSection().getCoins());
    }

    public void backBtnClicked(ActionEvent event) {
        for (StringListener listener: listeners) {
            listener.listen("MainMenu");
        }
    }

    public void addListener(StringListener listener) {
        listeners.add(listener);
    }

}

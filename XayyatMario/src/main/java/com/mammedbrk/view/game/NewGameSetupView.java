package com.mammedbrk.view.game;

import com.mammedbrk.current.Current;
import com.mammedbrk.listener.SaveNewGameListener;
import com.mammedbrk.listener.StringListener;
import com.mammedbrk.model.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class NewGameSetupView implements Initializable {
    @FXML
    private Button gameBtn1;
    @FXML
    private Button gameBtn2;
    @FXML
    private Button gameBtn3;

    private SaveNewGameListener saveGameListener = new SaveNewGameListener();
    private List<StringListener> listeners = new LinkedList<>();

    public void addListener(StringListener listener) {
        listeners.add(listener);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (Current.user.getGames().get(0).getCurrentLevel() != null) {
            gameBtn1.setText(buttonText(Current.user.getGames().get(0)));
        }
        if (Current.user.getGames().get(1).getCurrentLevel() != null) {
            gameBtn2.setText(buttonText(Current.user.getGames().get(1)));
        }
        if (Current.user.getGames().get(2).getCurrentLevel() != null) {
            gameBtn3.setText(buttonText(Current.user.getGames().get(2)));
        }
    }

    private String buttonText(Game game) {
        return "L" + game.getCurrentLevel().getNo() +
                ", S" + game.getCurrentLevel().getCurrentSection().getNo() +
                " | score: " + (game.getScore() + game.getCurrentLevel().getScore() + game.getCurrentLevel().getCurrentSection().getScore()) +
                " | coins: " + (game.getCoins() + game.getCurrentLevel().getCoins() + game.getCurrentLevel().getCurrentSection().getCoins());
    }

    public void gameBtn1Clicked(ActionEvent event) {
        if (Current.user.getGames().get(0).getCurrentLevel() != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Sure to delete previous game and save this on it?");
            if (alert.showAndWait().get() == ButtonType.OK) {
                saveGameListener.listen(0);
                for (StringListener listener: listeners) {
                    listener.listen("StartGame");
                }
            }
        }
        else {
            saveGameListener.listen(0);
            for (StringListener listener: listeners) {
                listener.listen("StartGame");
            }
        }
    }

    public void gameBtn2Clicked(ActionEvent event) {
        if (Current.user.getGames().get(1).getCurrentLevel() != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Sure to delete previous game and save this on it?");
            if (alert.showAndWait().get() == ButtonType.OK) {
                saveGameListener.listen(1);
                for (StringListener listener: listeners) {
                    listener.listen("StartGame");
                }
            }
        } else {
            saveGameListener.listen(1);
            for (StringListener listener: listeners) {
                listener.listen("StartGame");
            }
        }
    }

    public void gameBtn3Clicked(ActionEvent event) {
        if (Current.user.getGames().get(2).getCurrentLevel() != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Sure to delete previous game and save this on it?");
            if (alert.showAndWait().get() == ButtonType.OK) {
                saveGameListener.listen(2);
                for (StringListener listener: listeners) {
                    listener.listen("StartGame");
                }
            }
        } else {
            saveGameListener.listen(2);
            for (StringListener listener: listeners) {
                listener.listen("StartGame");
            }
        }
    }

    public void backBtnClicked(ActionEvent event) {
        for (StringListener listener: listeners) {
            listener.listen("MainMenu");
        }
    }

    // Getters and setters

    public List<StringListener> getListeners() {
        return listeners;
    }

    public void setListeners(List<StringListener> listeners) {
        this.listeners = listeners;
    }

    public SaveNewGameListener getSaveGameListener() {
        return saveGameListener;
    }

    public void setSaveGameListener(SaveNewGameListener saveGameListener) {
        this.saveGameListener = saveGameListener;
    }
}

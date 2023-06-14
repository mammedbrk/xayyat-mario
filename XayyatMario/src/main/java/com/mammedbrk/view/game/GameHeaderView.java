package com.mammedbrk.view.game;

import com.mammedbrk.listener.SaveExistingGameListener;
import com.mammedbrk.listener.StringListener;
import com.mammedbrk.model.game.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

import java.util.LinkedList;
import java.util.List;

public class GameHeaderView {
    @FXML
    private Label heartLabel;
    @FXML
    private Label scoreLabel;
    @FXML
    private Label timeLabel;
    @FXML
    private Label coinLabel;
    @FXML
    private Label levelLabel;
    private List<StringListener> listeners = new LinkedList<>();
    private SaveExistingGameListener saveExistingGameListener = new SaveExistingGameListener();

    public void render(Game game) {
        scoreLabel.setText(String.valueOf(game.getScore()));
        coinLabel.setText(String.valueOf(game.getCoins()));
        timeLabel.setText(String.valueOf(game.currentSection().getTime()));
        levelLabel.setText(game.getLevelNo() + "-" + game.getSectionNo());
        switch (game.getHearts()) {
            case 3 -> heartLabel.setText("<3 <3 <3");
            case 2 -> heartLabel.setText("<3 <3");
            case 1 -> heartLabel.setText("<3");
        }
    }

    public void addListener(StringListener listener) {
        listeners.add(listener);
    }

    @FXML
    void pauseBtnClicked(ActionEvent event) {
        for (StringListener listener: listeners) {
            listener.listen("pause");
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Want to save and quit this game?");
        if (alert.showAndWait().get() == ButtonType.OK) {
            saveExistingGameListener.listen();
            for (StringListener listener : listeners) {
                listener.listen("MainMenu");
            }
        }
        else {
            for (StringListener listener: listeners) {
                listener.listen("resume");
            }
        }
    }
}

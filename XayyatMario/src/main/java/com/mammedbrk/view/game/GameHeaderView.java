package com.mammedbrk.view.game;

import com.mammedbrk.current.Current;
import com.mammedbrk.listener.SaveExistingGameListener;
import com.mammedbrk.listener.StringListener;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class GameHeaderView implements Initializable {
    @FXML
    private Label heartLabel;
    @FXML
    private Label scoreLabel;
    @FXML
    private Label timeLabel;
    @FXML
    private Label coinLabel;
    private List<StringListener> listeners = new LinkedList<>();
    private SaveExistingGameListener saveExistingGameListener = new SaveExistingGameListener();

    private Timeline timeline;
    private AnimationTimer animationTimer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        timeline = new Timeline();
        timeline.setCycleCount(Animation.INDEFINITE);

        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (Current.user.getCurrentGame().getCurrentLevel().getCurrentSection().getTime() == 0) {
                    for (StringListener listener: listeners) {
                        listener.listen("time");
                    }
                }
                timeLabel.setText(timeLeft());
                if (Current.user.getCurrentGame().getCurrentLevel().getCurrentSection().getTime() > 0)
                    Current.user.getCurrentGame().getCurrentLevel().getCurrentSection().reduceTime();
            }
        }));
        timeline.play();

        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                coinLabel.setText(String.valueOf(Current.user.getCurrentGame().getCoins() + Current.user.getCurrentGame().getCurrentLevel().getCoins() + Current.user.getCurrentGame().getCurrentLevel().getCurrentSection().getCoins()));
                scoreLabel.setText(String.valueOf(Current.user.getCurrentGame().getScore() + Current.user.getCurrentGame().getCurrentLevel().getScore() + Current.user.getCurrentGame().getCurrentLevel().getCurrentSection().getScore()));
                switch (Current.user.getCurrentGame().getHearts()) {
                    case 3: heartLabel.setText("<3 <3 <3"); break;
                    case 2: heartLabel.setText("<3 <3"); break;
                    case 1: heartLabel.setText("<3"); break;
                }
            }
        };
        animationTimer.start();
    }

    public void addListener(StringListener listener) {
        listeners.add(listener);
    }

    public void stopTimer() {
        timeline.stop();
        animationTimer.stop();
    }

    public void startTimer() {
        timeline.play();
        animationTimer.start();
    }

    private String timeLeft() {
        int time = Current.user.getCurrentGame().getCurrentLevel().getCurrentSection().getTime();
        if ((time % 60) / 10 == 0)
            return (time / 60 + ":0" + time % 60);
        return (time / 60 + ":" + time % 60);
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

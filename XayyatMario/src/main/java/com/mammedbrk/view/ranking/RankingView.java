package com.mammedbrk.view.ranking;

import com.mammedbrk.event.ListEvent;
import com.mammedbrk.listener.Listener;
import com.mammedbrk.listener.RankingListListener;
import com.mammedbrk.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class RankingView implements Initializable {
    @FXML
    private VBox vBox;
    private List<Listener<String>> listeners = new LinkedList<>();
    private RankingListListener rankingListListener = new RankingListListener();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<User> users = new ArrayList<>();
        rankingListListener.listen(new ListEvent(this, users));
        int rank = 0;
        for (User user: users) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com.mammedbrk/fxml/ranking/user-ranking-card-view.fxml"));
            try {
                vBox.getChildren().add(loader.load());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            UserRankingCardView userRankingCardView = loader.getController();
            userRankingCardView.setRankLabel(String.valueOf(++rank));
            try {
                userRankingCardView.getCharacterImg().setImage(new Image(new FileInputStream(user.chosenCharacter().getImageAddress())));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            userRankingCardView.setUsernameLabel(user.getUsername());
            userRankingCardView.setScoreLabel(String.valueOf(user.getMaxScore()));
        }
    }

    public void addListener(Listener<String> listener) {
        listeners.add(listener);
    }

    public void backBtnClicked(ActionEvent event) {
        for (Listener<String> listener: listeners) {
            listener.listen("MainMenu");
        }
    }

    // Getters and setters

    public RankingListListener getRankingListListener() {
        return rankingListListener;
    }

    public void setRankingListListener(RankingListListener rankingListListener) {
        this.rankingListListener = rankingListListener;
    }

    public List<Listener<String>> getListeners() {
        return listeners;
    }

    public void setListeners(List<Listener<String>> listeners) {
        this.listeners = listeners;
    }
}

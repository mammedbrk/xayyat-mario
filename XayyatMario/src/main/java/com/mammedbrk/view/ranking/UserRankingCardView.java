package com.mammedbrk.view.ranking;

import com.mammedbrk.model.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class UserRankingCardView {
    @FXML
    private Label rankLabel;
    @FXML
    private ImageView characterImg;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label scoreLabel;

    // Getters and setters

    public String getRankLabel() {
        return rankLabel.getText();
    }

    public void setRankLabel(String rankLabel) {
        this.rankLabel.setText(rankLabel);
    }

    public ImageView getCharacterImg() {
        return characterImg;
    }

    public void setCharacterImg(ImageView characterImg) {
        this.characterImg = characterImg;
    }

    public String getUsernameLabel() {
        return usernameLabel.getText();
    }

    public void setUsernameLabel(String usernameLabel) {
        this.usernameLabel.setText(usernameLabel);
    }

    public String getScoreLabel() {
        return scoreLabel.getText();
    }

    public void setScoreLabel(String scoreLabel) {
        this.scoreLabel.setText(scoreLabel);
    }
}

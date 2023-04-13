package com.mammedbrk.view.ranking;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

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

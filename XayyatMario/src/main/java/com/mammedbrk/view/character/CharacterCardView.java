package com.mammedbrk.view.character;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class CharacterCardView {
    @FXML
    private ImageView characterImg;
    @FXML
    private Label characterNameLabel;
    @FXML
    private Label characterPriceLabel;
    @FXML
    private Label collectLabel;
    @FXML
    private Label jumpLabel;
    @FXML
    private Label shootLabel;
    @FXML
    private Label speedLabel;

    // Getters and setters

    public ImageView getCharacterImg() {
        return characterImg;
    }

    public void setCharacterImg(ImageView characterImg) {
        this.characterImg = characterImg;
    }

    public String getCharacterNameLabel() {
        return characterNameLabel.getText();
    }

    public void setCharacterNameLabel(String characterNameLabel) {
        this.characterNameLabel.setText(characterNameLabel);
    }

    public String getCharacterPriceLabel() {
        return characterPriceLabel.getText();
    }

    public void setCharacterPriceLabel(String characterPriceLabel) {
        this.characterPriceLabel.setText(characterPriceLabel);
    }

    public String getCollectLabel() {
        return collectLabel.getText();
    }

    public void setCollectLabel(String collectLabel) {
        this.collectLabel.setText(collectLabel);
    }

    public String getJumpLabel() {
        return jumpLabel.getText();
    }

    public void setJumpLabel(String jumpLabel) {
        this.jumpLabel.setText(jumpLabel);
    }

    public String getShootLabel() {
        return shootLabel.getText();
    }

    public void setShootLabel(String shootLabel) {
        this.shootLabel.setText(shootLabel);
    }

    public String getSpeedLabel() {
        return speedLabel.getText();
    }

    public void setSpeedLabel(String speedLabel) {
        this.speedLabel.setText(speedLabel);
    }
}

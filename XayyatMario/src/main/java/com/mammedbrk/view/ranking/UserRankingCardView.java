package com.mammedbrk.view.ranking;

import com.mammedbrk.model.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class UserRankingCardView implements Initializable {
    @FXML
    private Label rankLabel;
    @FXML
    private ImageView characterImg;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label scoreLabel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

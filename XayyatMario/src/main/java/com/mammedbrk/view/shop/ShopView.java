package com.mammedbrk.view.shop;

import com.mammedbrk.current.Current;
import com.mammedbrk.event.CharacterEvent;
import com.mammedbrk.listener.CharacterBuyListener;
import com.mammedbrk.listener.CharacterListListener;
import com.mammedbrk.listener.StringListener;
import com.mammedbrk.model.Character;
import com.mammedbrk.view.character.CharacterCardView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class ShopView implements Initializable {
    @FXML
    private Button buyBtn;
    @FXML
    private BorderPane cardPane;
    @FXML
    private ImageView characterImg;
    @FXML
    private Label coin;
    @FXML
    private TilePane tilePane;
    @FXML
    private Label username;

    private CharacterListListener characterListListener = new CharacterListListener();
    private List<StringListener> listeners = new LinkedList<>();
    private CharacterBuyListener characterBuyListener;

    // Methods

    public void addListener(StringListener listener) {
        listeners.add(listener);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // User info
        setUserInfo();

        // Character cards
        List<Character> characters = characterListListener.listen();
        for (Character character: characters) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com.mammedbrk/fxml/character/character-card-view.fxml"));
            try {
                tilePane.getChildren().add(loader.load());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            setItemsInCard(loader.getController(), character);

            Pane card = loader.getRoot();
            if (Current.user.getCharacters().contains(character)) {
                card.setStyle("-fx-background-color: #236346; -fx-background-radius: 10;");
            }

            card.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    // Set selectedCard to cardPane
                    FXMLLoader mainCardLoader = new FXMLLoader(getClass().getResource("/com.mammedbrk/fxml/character/character-card-view.fxml"));
                    Pane selectedPane;
                    try {
                        selectedPane = mainCardLoader.load();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    setItemsInCard(mainCardLoader.getController(), character);

                    if (Current.user.getCharacters().contains(character)) {
                        selectedPane.setStyle("-fx-background-color: #236346; -fx-background-radius: 10;");
                    }

                    selectedPane.setScaleX(1.5);
                    selectedPane.setScaleY(1.5);
                    cardPane.setCenter(selectedPane);

                    // Setting buy button
                    if (Current.user.getCharacters().contains(character)) {
                        buyBtn.setText("Bought!");
                        buyBtn.setDisable(true);
                    }
                    else {
                        buyBtn.setText("Buy");
                        buyBtn.setDisable(false);
                    }
                    buyBtn.setVisible(true);

                    buyBtn.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            if (characterBuyListener.listen(new CharacterEvent(this, character))) {
                                selectedPane.setStyle("-fx-background-color: #236346; -fx-background-radius: 10;");
                                card.setStyle("-fx-background-color: #236346; -fx-background-radius: 10;");

                                buyBtn.setText("Bought!");
                                buyBtn.setDisable(true);

                                setUserInfo();
                            }
                            else {
                                Alert alert = new Alert(Alert.AlertType.ERROR, "You don't have enough money to buy " + character.getName() + "!");
                                alert.show();
                            }
                        }
                    });
                }
            });
        }

    }

    private void setUserInfo() {
        username.setText(Current.user.getUsername());
        coin.setText(String.valueOf(Current.user.getCoins()));
        try {
            characterImg.setImage(new Image(new FileInputStream(Current.user.getChosenCharacter().getImageAddress())));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setItemsInCard(CharacterCardView characterCardView, Character character) {
        try {
            characterCardView.getCharacterImg().setImage(new Image(new FileInputStream(character.getImageAddress())));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        characterCardView.setCharacterNameLabel(character.getName());
        characterCardView.setSpeedLabel(String.valueOf(character.getSpeed()));
        characterCardView.setJumpLabel(String.valueOf(character.getJumpAbility()));
        characterCardView.setCollectLabel(String.valueOf(character.getCollectAbility()));
        characterCardView.setShootLabel(String.valueOf(character.getShootAbility()));
        characterCardView.setCharacterPriceLabel(String.valueOf(character.getPrice()));
    }


    public void backBtnClicked(ActionEvent event) {
        for (StringListener listener: listeners) {
            listener.listen("MainMenu");
        }
    }

    // Getters and setters

    public CharacterListListener getCharacterListListener() {
        return characterListListener;
    }

    public void setCharacterListListener(CharacterListListener characterListListener) {
        this.characterListListener = characterListListener;
    }

    public List<StringListener> getListeners() {
        return listeners;
    }

    public void setListeners(List<StringListener> listeners) {
        this.listeners = listeners;
    }

    public CharacterBuyListener getCharacterBuyListener() {
        return characterBuyListener;
    }

    public void setCharacterBuyListener(CharacterBuyListener characterBuyListener) {
        this.characterBuyListener = characterBuyListener;
    }
}

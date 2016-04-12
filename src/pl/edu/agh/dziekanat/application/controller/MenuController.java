package pl.edu.agh.dziekanat.application.controller;

import java.io.IOException;
import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import pl.edu.agh.dziekanat.application.MainApp;

/**
 * Menu Panel
 */
public class MenuController {

    @FXML // fx:id="displayOne"
    private MenuItem displayOne; // Value injected by FXMLLoader

    @FXML // fx:id="displayTwo"
    private MenuItem displayTwo; // Value injected by FXMLLoader

    @FXML // fx:id="displayThree"
    private MenuItem displayThree;

    @FXML
    void switchToOne(ActionEvent event) {
        try {
            URL paneOneUrl = getClass().getResource("/pl/edu/agh/dziekanat/application/form/EmptyPane.fxml");
            AnchorPane paneOne = FXMLLoader.load(paneOneUrl);
            BorderPane border = MainApp.getRoot();
            border.setCenter(paneOne);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void switchToTwo(ActionEvent event) {
        try {
            URL paneTwoUrl = getClass().getResource("/pl/edu/agh/dziekanat/application/form/EmptyPane.fxml");
            AnchorPane paneTwo = FXMLLoader.load(paneTwoUrl);
            BorderPane border = MainApp.getRoot();
            border.setCenter(paneTwo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void switchToMaintainPerson(ActionEvent event) {
        try {
            URL panePersonUrl = getClass().getResource("/pl/edu/agh/dziekanat/application/form/PersonPane.fxml");
            AnchorPane panePerson = FXMLLoader.load(panePersonUrl);
            BorderPane border = MainApp.getRoot();
            border.setCenter(panePerson);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void switchToEmailAction(ActionEvent event) {
        try {
            URL panePersonUrl = getClass().getResource("/pl/edu/agh/dziekanat/application/form/MailToPane.fxml");
            AnchorPane pane = FXMLLoader.load(panePersonUrl);
            BorderPane border = MainApp.getRoot();
            border.setCenter(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void doHelloWorld(ActionEvent e) {
        System.out.println("Hello World");
    }

}

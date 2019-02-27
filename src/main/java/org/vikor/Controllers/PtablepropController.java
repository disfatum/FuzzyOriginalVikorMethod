package org.vikor.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class PtablepropController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private VBox Vbox;

    public static JFXButton jb = new JFXButton();
    
    @FXML
    void initialize() {
        assert Vbox != null : "fx:id=\"Vbox\" was not injected: check your FXML file 'PtableProperty.fxml'.";

    }
}


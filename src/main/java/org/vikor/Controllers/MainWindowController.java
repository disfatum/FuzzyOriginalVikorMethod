package org.vikor.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

public class MainWindowController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button CalcButton;

    @FXML
    private Button VPButton;

    @FXML
    private Button QVButton;

    @FXML
    private Button DomButton;

    @FXML
    private TableView<?> PTable;

    @FXML
    private Button Settings;

    @FXML
    private Button SRWButton;

    @FXML
    void initialize() {
        assert CalcButton != null : "fx:id=\"CalcButton\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert VPButton != null : "fx:id=\"VPButton\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert QVButton != null : "fx:id=\"QVButton\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert DomButton != null : "fx:id=\"DomButton\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert PTable != null : "fx:id=\"PTable\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert Settings != null : "fx:id=\"Settings\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert SRWButton != null : "fx:id=\"SRWButton\" was not injected: check your FXML file 'MainWindow.fxml'.";

    }
}


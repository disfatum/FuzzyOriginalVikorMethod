package org.vikor.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

public class CalculateController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private BarChart<?, ?> BarChart;

    @FXML
    private TableView<?> ResTable;

    @FXML
    private Label C1Label;

    @FXML
    private Label C2Label;

    @FXML
    void initialize() {
        assert BarChart != null : "fx:id=\"BarChart\" was not injected: check your FXML file 'Calculate.fxml'.";
        assert ResTable != null : "fx:id=\"ResTable\" was not injected: check your FXML file 'Calculate.fxml'.";
        assert C1Label != null : "fx:id=\"C1Label\" was not injected: check your FXML file 'Calculate.fxml'.";
        assert C2Label != null : "fx:id=\"C2Label\" was not injected: check your FXML file 'Calculate.fxml'.";

    }
}


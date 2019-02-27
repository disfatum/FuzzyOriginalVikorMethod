package org.vikor.Controllers;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HomeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnDashboard;

    @FXML
    private Button btnStudents;

    @FXML
    private Button btn_Timetable;

    @FXML
    private Button btnSettings;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnClasses;

    @FXML
    void handleButtonClicks(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert btnDashboard != null : "fx:id=\"btnDashboard\" was not injected: check your FXML file 'HomeWindow.fxml'.";
        assert btnStudents != null : "fx:id=\"btnStudents\" was not injected: check your FXML file 'HomeWindow.fxml'.";
        assert btn_Timetable != null : "fx:id=\"btn_Timetable\" was not injected: check your FXML file 'HomeWindow.fxml'.";
        assert btnSettings != null : "fx:id=\"btnSettings\" was not injected: check your FXML file 'HomeWindow.fxml'.";
        assert btnUpdate != null : "fx:id=\"btnUpdate\" was not injected: check your FXML file 'HomeWindow.fxml'.";
        assert btnClasses != null : "fx:id=\"btnClasses\" was not injected: check your FXML file 'HomeWindow.fxml'.";

    }
}

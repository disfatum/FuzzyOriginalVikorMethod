package org.vikor.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import org.vikor.Views.AddFA;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class addFAController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button AddAltButton;

    @FXML
    private Button AddfButton;

    @FXML
    void initialize() {
    	AddAltButton.addEventHandler(MouseEvent.ANY, event -> {
       	 
      	    if (event.getClickCount() == 1 && event.getButton().equals(MouseButton.SECONDARY)) {
      	        if (event.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
      	        	try {
						MainWindowController.f.stop();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
      	        }
      	    }
      	});
    }
}

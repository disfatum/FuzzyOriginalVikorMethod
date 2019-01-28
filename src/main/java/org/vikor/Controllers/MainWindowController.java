package org.vikor.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.vikor.Views.AddFA;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.ChoiceBox;

public class MainWindowController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private ChoiceBox<String> MethodBox;
    
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

    public void addMnames(ChoiceBox<String> MethodBox) {
	     ObservableList<String> methodnames = FXCollections.observableArrayList();
	     methodnames.add("Original VIKOR");
	     methodnames.add("Fuzzy VIKOR");	
	     MethodBox.setItems(methodnames);
	     MethodBox.setValue("Original VIKOR");
    }
    static public AddFA f = new AddFA();
    @FXML
    void initialize() {
      addMnames(MethodBox);
      PTable.addEventHandler(MouseEvent.ANY, event -> {
    	    if (event.getClickCount() == 2 && event.getButton().equals(MouseButton.PRIMARY)) {
    	        if (event.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
    	            System.out.println("hello"); // perform some action
    	        }

    	        event.consume();
    	    }
    	});
      PTable.addEventHandler(MouseEvent.ANY, event -> {
    	 
  	    if (event.getClickCount() == 1 && event.getButton().equals(MouseButton.SECONDARY)) {
  	        if (event.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
  	            System.out.println("lol"); // perform some action
  	          
  		 		Stage primaryStage = new Stage();
  				try {
					f.start(primaryStage);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
  	          
  	        }

  	        event.consume();
  	    }
  	});

    }
}


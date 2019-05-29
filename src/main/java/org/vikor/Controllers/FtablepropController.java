package org.vikor.Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;

import org.vikor.Alerts.Alerts;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class FtablepropController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTextField NameField;

    @FXML
    private JFXTextField DiscField;

    @FXML
    private JFXTextField WeighField;

    @FXML
    private JFXComboBox<String> MxMnBox;

    @FXML
    private JFXButton OkButton;

    @FXML
    private JFXButton CencelButton;
    
    static int Tableindex = 0;
    static String ChangedName ;
    static ActionEvent AE;
    public static JFXButton jb = new JFXButton();
    @FXML
    void initialize() {
    	AE = new ActionEvent();
    	//AE = new EventType<ActionEvent>;
    	ObservableList<String> l = FXCollections.observableArrayList();
    	l.add("Максимизация");
    	l.add("Минимизация");
    	MxMnBox.setItems(l);
    	MxMnBox.setValue(l.get(0));
    	
    	int index = VikorController.indexPropCol;
        NameField.setText(VikorController.FTableData.get(index).getName());
        DiscField.setText(VikorController.FTableData.get(index).getDiscription());
        WeighField.setText(VikorController.FTableData.get(index).getWeigh());
        MxMnBox.setValue(VikorController.FTableData.get(index).getMaxmin());
        
        OkButton.setOnAction(e->{
          try {	
        	VikorController.FTableData.get(index).setName(NameField.getText());
        	VikorController.FTableData.get(index).setDiscription(DiscField.getText());
        	VikorController.FTableData.get(index).setWeigh(WeighField.getText());
        	VikorController.FTableData.get(index).setMaxmin(MxMnBox.getValue());
        	Tableindex = index+1;
        	ChangedName = NameField.getText();
        	AE = e;
        	 try {
             	Stage ps;
     	    	ps  = (Stage) OkButton.getScene().getWindow();
     	    	ps.close();
     		} catch (Exception e1) {
     			//do nothing
     		}
        	 jb.fireEvent(e);
          }
          catch(Exception ex) {
        	  Alerts alert = new Alerts();
			alert.ErrorData();
          }
        });
        CencelButton.setOnAction(e->{
        try {
        	Stage ps;
	    	ps  = (Stage) OkButton.getScene().getWindow();
	    	ps.close();
		} catch (Exception e1) {
			//do nothing
		}
        });
        
        
        
    }
}
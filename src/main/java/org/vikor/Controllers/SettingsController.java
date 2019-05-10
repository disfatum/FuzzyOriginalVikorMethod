package org.vikor.Controllers;

import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SettingsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField coef_v;

    @FXML
    private TextField QV_counter;

    @FXML
    private TextField Qvstep;

    @FXML
    private TextField SR_counter;

    @FXML
    private TextField SRstep;

    @FXML
    private JFXComboBox<String> Synh_combo;

    @FXML
    private Button OkButton;

    @FXML
    private Button CencelButton;

    @FXML
    void initialize() {
    	
    	Synh_combo.setValue(VikorController.Settings.getSynchronization());
    	coef_v.setText(VikorController.Settings.getV()+"");
    	QV_counter.setText(VikorController.Settings.getQvstep()+"");
    	Qvstep.setText(VikorController.Settings.getQvs()+"");
    	SR_counter.setText(VikorController.Settings.getQvstep()+"");
    	SRstep.setText(VikorController.Settings.getSrs()+"");
    	
    	ObservableList<String> l = FXCollections.observableArrayList();
		l.add("Да");l.add("Нет");
		Synh_combo.setItems(l);
		Synh_combo.setValue(l.get(0));
		
    	CencelButton.setOnAction(e->{
    		 try {
    	        	Stage ps;
    		    	ps  = (Stage) OkButton.getScene().getWindow();
    		    	ps.close();
    			} catch (Exception e1) {
    				//do nothing
    			}
    	});
    	OkButton.setOnAction(e->{
    	try {
    		VikorController.Settings.setSynchronization(Synh_combo.getValue());
    		VikorController.Settings.setV(coef_v.getText());
    		VikorController.Settings.setQvstep(Integer.valueOf(QV_counter.getText()));
    		VikorController.Settings.setQvs(Double.valueOf(Qvstep.getText()));
    		VikorController.Settings.setSRstep(Integer.valueOf(SR_counter.getText()));
    		VikorController.Settings.setSrs(Double.valueOf(SRstep.getText()));
    		
	        Stage ps;
		    ps  = (Stage) OkButton.getScene().getWindow();
		    ps.close();
		    
			} catch (Exception e1) {
				//do nothing
			}
    	});
    }
}

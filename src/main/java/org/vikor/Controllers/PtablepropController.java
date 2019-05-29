package org.vikor.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import org.vikor.Alerts.Alerts;
import org.vikor.DataStructure.TriangularFuzzyNumber;

import com.jfoenix.controls.JFXButton;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PtablepropController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private VBox Vbox;
    
    @FXML
    private SplitPane splt;
    
    @FXML
    private NumberAxis xAxis;
    
    @FXML
    private LineChart<Number, Number> linechart;

    public static JFXButton jb = new JFXButton();
    ObservableList<TextField> TfList =  FXCollections.observableArrayList();
    ObservableList<Button> but =  FXCollections.observableArrayList();
    @FXML
    void initialize() {
    	Label l = new Label("Альтернатива "+VikorController.PTableData.get(VikorController.pindexPropCol).getFromList(0));
    	Vbox.getChildren().add(l);
     
    		 for(int j = 0; j < VikorController.PTableData.get(VikorController.pindexPropCol).size();j++) {
    			 addHbox(Vbox, j);
    		 }
    	 
      footerHbox(Vbox);
      
      for(int i = 0; i < but.size();i++) {
    	  int final_i = i;
      but.get(i).setOnAction(e->{
  		linechart.getData().clear();
  		Series<Number, Number> series1 = new Series<>();
  		TriangularFuzzyNumber tfn = new TriangularFuzzyNumber(1.0,1.0,1.0);
  		tfn.RefreshData(TfList.get(final_i+1).getText());
	        		series1.getData().add(new Data<Number, Number>(tfn.getLeft(),0.0));
	        		series1.getData().add(new Data<Number, Number>(tfn.getCenter(),1.0));
	        		series1.getData().add(new Data<Number, Number>(tfn.getRight(),0.0));
	        	series1.setName(VikorController.Colnames.get(final_i+1));
	        	
	        	
	        	linechart.getData().add(series1);
	        	xAxis.setLowerBound(tfn.getLeft());
	        	xAxis.setUpperBound(tfn.getRight());
  		});
      }
    }
    int c = 0;
    public void addHbox(VBox vb, int j) {
    	
    	Label l = new Label();
    	l.setText(VikorController.Colnames.get(c));
    	TextField tx = new TextField();
    	TfList.add(tx);
    	tx.setAlignment(Pos.CENTER);
    	System.out.println(VikorController.pindexPropCol);
    	tx.setText(VikorController.PTableData.get(VikorController.pindexPropCol).getFromList(TfList.size()-1));
    	l.setPrefSize(350, 50);
    	tx.setPrefSize(350, 50);
    	HBox hb;
    	
    	if(VikorController.f == false) {
    		splt.setDividerPositions(0.99);
    		hb = new HBox(l,tx);
    	}
    	else {
    		
    		splt.setDividerPositions(0.5);
    		hb = new HBox(l,tx);
    		if(c > 0) {
    			Button fb = new Button("Показать на графике");
        		but.add(fb);
        		hb = new HBox(l,tx,fb);
        	}
    	}
    	hb.setSpacing(10);
    	hb.setPadding(new Insets(15,20, 10,10));
    	hb.setAlignment(Pos.CENTER);
    	vb.getChildren().add(hb);
    	c++;
    }
    public void footerHbox(VBox vb) {
    	JFXButton b1 = new JFXButton("Подтвердить изменения");
    	JFXButton b2 = new JFXButton("Отмена");
    	b1.setPrefSize(350, 50);
    	b2.setPrefSize(350, 50);
    	HBox hb = new HBox(b1,b2);
    	
    	b1.setOnAction(e->{
    		
    		try {
	    		for(int i = 0 ; i < TfList.size();i++) {
	    			VikorController.PTableData.get(VikorController.pindexPropCol).setinlist(i, TfList.get(i).getText());
	    			jb.fire();
	    		}
            	Stage ps;
    	    	ps  = (Stage) b2.getScene().getWindow();
    	    	ps.close();
    		} catch (Exception e1) {
    			  Alerts alert = new Alerts();
    			  alert.ErrorData();
    		}
    	});
    	b2.setOnAction(e->{
    		try {
            	Stage ps;
    	    	ps  = (Stage) b2.getScene().getWindow();
    	    	ps.close();
    		} catch (Exception e1) {
    			//do nothing
    		}
    	});
    	
    	vb.getChildren().add(hb);
    }
}


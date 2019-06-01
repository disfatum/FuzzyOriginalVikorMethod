package org.vikor.Controllers;

import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;
import org.vikor.DataStructure.VikorTableData;
import org.vikor.Methods.ClassicVikor;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * @author disfatum
 * @mail disfatum@yandex.ru
 * @github https://github.com/disfatum
 *
 */
public class CalculateController {

	    @FXML
	    private ResourceBundle resources;

	    @FXML
	    private URL location;

	    @FXML
	    private BarChart<String, Number> BarChart;

	    @FXML
	    private TableView<VikorTableData> ResTable;

	    @FXML
	    private Label c1;

	    @FXML
	    private Label c2;

	    ObservableList<Double> S = FXCollections.observableArrayList();
	    ObservableList<Double> R = FXCollections.observableArrayList(); 
	    ObservableList<Double> Q = FXCollections.observableArrayList();
	    ObservableList<String> alt = FXCollections.observableArrayList();
	    ObservableList<VikorTableData> Data = FXCollections.observableArrayList();
	    
	    public void AddData() throws Exception{
	    	try {
	    	int c = alt.size();
	    		for(int i = 0; i < c; i++) {
	    			VikorTableData t = new VikorTableData(alt.get(i),S.get(i),R.get(i),Q.get(i));
	    			Data.add(t);
	    		}
	    	}catch( Exception e ){
	        }
	    }
	    @FXML
	    void initialize() {
	    	ClassicVikor t = VikorController.t;
	    	alt = t.AltNames;
	    	Q = t.Q;
	    	S = t.S;
	    	R = t.R;
	    	try {
				AddData();
			} catch (Exception e) {
			}
	    	
	    	TableColumn<VikorTableData,String> alte = new TableColumn<VikorTableData,String>("Название Альтернативы");
	    	alte.setCellValueFactory(new PropertyValueFactory<VikorTableData, String>("AltName"));
	    	TableColumn<VikorTableData,Double> s = new TableColumn<VikorTableData,Double>("S");
	    	s.setCellValueFactory(new PropertyValueFactory<VikorTableData, Double>("S"));
	    	TableColumn<VikorTableData,Double> r = new TableColumn<VikorTableData,Double>("R");
	    	r.setCellValueFactory(new PropertyValueFactory<VikorTableData, Double>("R"));
	    	TableColumn<VikorTableData,Double> q = new TableColumn<VikorTableData,Double>("Q");
	    	q.setCellValueFactory(new PropertyValueFactory<VikorTableData, Double>("Q"));
	    	
	    	ResTable.getColumns().add(alte);
	    	ResTable.getColumns().add(s);
	    	ResTable.getColumns().add(r);
	    	ResTable.getColumns().add(q);
	    	ResTable.setItems(Data);
	    	
	    	 CategoryAxis xAxis = new CategoryAxis();
	         xAxis.setLabel("Название альтернативы");
	   
	         NumberAxis yAxis = new NumberAxis();
	         yAxis.setLabel("значение коэф. Q");
	         
	    	int c = alt.size();
	    	for(int i = 0; i < c; i++) {
	    		XYChart.Series<String, Number> alt1 = new XYChart.Series<String, Number>();
	    		alt1.getData().add(new XYChart.Data<String, Number>("", Q.get(i)+0.01));
	    		alt1.setName(alt.get(i));
	    		BarChart.getData().add(alt1);
	    	}
	    	 
	    	ObservableList<Double> Qsort = FXCollections.observableArrayList();
	    	Qsort = Q;
	    	Collections.sort(Qsort);
	    	Double Dq = 1.0 / (Qsort.size() - 1.0);
	    	
	    	if((Qsort.get(1) - Qsort.get(0)) >= Dq) {
	    		c1.setText(c1.getText() + "Выполняется");
	    	}
	    	else {
	    		c1.setText(c1.getText() + "Не выполняется");
	    	}
	    	
	    	if((Qsort.get(Qsort.size()-1) - Qsort.get(0)) < Dq) {
	    		c2.setText(c2.getText() + "Выполняется");
	    	}
	    	else {
	    		c2.setText(c2.getText() + "Не выполняется");
	    	}
	    }
	}

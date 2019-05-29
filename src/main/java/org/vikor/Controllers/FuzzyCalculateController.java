package org.vikor.Controllers;

import java.net.URL;
import java.util.ResourceBundle;

import org.vikor.DataStructure.VikorTableData;
import org.vikor.Methods.FuzzyVikorCentroid;
import org.vikor.Methods.FuzzyVikorMax;
import org.vikor.Methods.FuzzyVikorMediana;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;

public class FuzzyCalculateController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private Tab tabS;
    
    @FXML
    private Tab tabR;
    
    @FXML
    private URL location;

    @FXML
    private LineChart<Number, Number> LineChartS;

    @FXML
    private LineChart<Number, Number> LineChartR;

    @FXML
    private LineChart<Number, Number> LineChartQ;

    @FXML
    private TableView<VikorTableData> Table;
    
    @FXML
    private TabPane TabPane;

    @FXML
    private ComboBox<String> ComboBox;

    @FXML
    private BarChart<String, Number> BarChart;
    
    @FXML
    private Button Button;

    ObservableList<String> alt = FXCollections.observableArrayList();
    
    @FXML
    void initialize() {
    	//FuzzyVikorCentroid t = VikorController.Cindex;
    	
    	FuzzyVikorCentroid Cindex = VikorController.Cindex;
        FuzzyVikorMediana Mediana = VikorController.Mediana;
        FuzzyVikorMax LargeMax = VikorController.LargeMax;
        
        addCols();
    	 alt = Cindex.AltNames;
    	 TabPane.addEventHandler(MouseEvent.MOUSE_CLICKED, e->{
    		 
    		 LineChartS.getData().clear();
    		 LineChartR.getData().clear();
    		 //LineChartQ.getData().clear();
    		 
    		 if(TabPane.getSelectionModel().isSelected(0)) {
    			
 	    	for(int j = 0; j < Cindex.S.size();j++) {
 	    		Series<Number, Number> series1= new Series<>();
 	    		series1.getData().add(new Data<Number,Number>(Cindex.S.get(j).getLeft(), 0));
 	    		series1.getData().add(new Data<Number,Number>(Cindex.S.get(j).getCenter(), 1));
 	    		series1.getData().add(new Data<Number,Number>(Cindex.S.get(j).getRight(), 0));
 	    		series1.setName("S "+alt.get(j));

 	 	    	LineChartS.getData().add(series1);
 	    	}
    	 }
    	 if(TabPane.getSelectionModel().isSelected(1)) {
    	
 	    	for(int j = 0; j < Cindex.R.size();j++) {
 	    		
 	    		Series<Number, Number> series1= new Series<>();
 	    		series1.getData().add(new Data<Number,Number>(Cindex.R.get(j).getLeft(), 0));
 	    		series1.getData().add(new Data<Number,Number>(Cindex.R.get(j).getCenter(), 1));
 	    		series1.getData().add(new Data<Number,Number>(Cindex.R.get(j).getRight(), 0));
 	    		series1.setName("R "+alt.get(j));

 	 	    	LineChartR.getData().add(series1);
 	    	}
    	 }
    	 
    	 });
    	 
    	 
    	 ObservableList<String> l = FXCollections.observableArrayList();
    	 l.add("Центр масс");l.add("Медиана");l.add("Наибольший максимум");
    	 ComboBox.setItems(l);
    	 ComboBox.setValue(l.get(0));

    	 Button.setOnAction(e->{
    		 BarChart.getData().clear();
    		 if(ComboBox.getValue().equals(l.get(0))) {
    			 alt = Cindex.AltNames;
    			 int c = alt.size();
    			 
    		    	for(int i = 0; i < c; i++) {
    		    		XYChart.Series<String, Number> alt1 = new XYChart.Series<String, Number>();
    		    		alt1.getData().add(new XYChart.Data<String, Number>("", Cindex.Q.get(i)));
    		    		alt1.setName(alt.get(i));
    		    		BarChart.getData().add(alt1);
    		    	}
    		 }
    		 if(ComboBox.getValue().equals(l.get(1))) {
    			 alt = Mediana.AltNames;
    			 int c = alt.size();
    			 
    		    	for(int i = 0; i < c; i++) {
    		    		XYChart.Series<String, Number> alt1 = new XYChart.Series<String, Number>();
    		    		alt1.getData().add(new XYChart.Data<String, Number>("", Mediana.Q1.get(i)));
    		    		alt1.setName(alt.get(i));
    		    		BarChart.getData().add(alt1);
    		    	}
    		 }
    		 if(ComboBox.getValue().equals(l.get(2))) {
    			 alt = LargeMax.AltNames;
    			 int c = alt.size();
    			 
    		    	for(int i = 0; i < c; i++) {
    		    		XYChart.Series<String, Number> alt1 = new XYChart.Series<String, Number>();
    		    		alt1.getData().add(new XYChart.Data<String, Number>("", LargeMax.Q1.get(i)));
    		    		alt1.setName(alt.get(i));
    		    		BarChart.getData().add(alt1);
    		    	}
    		 }
    	 });
    	 
    	 
    }
    public void addCols() {
    	TableColumn<VikorTableData,String> alte = new TableColumn<VikorTableData,String>("Название Альтернативы");
    	alte.setCellValueFactory(new PropertyValueFactory<VikorTableData, String>("AltName"));
    	TableColumn<VikorTableData,Double> s = new TableColumn<VikorTableData,Double>("S");
    	s.setCellValueFactory(new PropertyValueFactory<VikorTableData, Double>("S"));
    	TableColumn<VikorTableData,Double> r = new TableColumn<VikorTableData,Double>("R");
    	r.setCellValueFactory(new PropertyValueFactory<VikorTableData, Double>("R"));
    	TableColumn<VikorTableData,Double> q = new TableColumn<VikorTableData,Double>("Q");
    	q.setCellValueFactory(new PropertyValueFactory<VikorTableData, Double>("Q"));
    	
    	ObservableList<VikorTableData> Data =  FXCollections.observableArrayList();
    	
    	for(int i = 0; i < VikorController.PTableData.size();i++) {
    	VikorTableData l = new VikorTableData(VikorController.PTableData.get(i).get(0),
		VikorController.Cindex.S.get(i).DefazzyCentriod(),
		VikorController.Cindex.R.get(i).DefazzyCentriod(),
		VikorController.Cindex.Q1.get(i));
    		Data.add(l);
    	}
    	
    	Table.getColumns().add(alte);
    	Table.getColumns().add(s);
    	Table.getColumns().add(r);
    	Table.getColumns().add(q);
    	Table.setItems(Data);
    }
}

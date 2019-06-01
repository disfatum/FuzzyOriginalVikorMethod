package org.vikor.Controllers;

import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;

import org.vikor.DataStructure.TriangularFuzzyNumber;
import org.vikor.Methods.FuzzyVikorCentroid;
import org.vikor.Methods.FuzzyVikorMax;
import org.vikor.Methods.FuzzyVikorMediana;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;

public class QvFuzzyController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private ComboBox<String> MethodBox;

    @FXML
    private TextArea TextArea;

    @FXML
    private LineChart<Number, Number> Chart;

    @FXML
    private Button ReButton;
    
    @FXML
    private Slider Slider;
    
    @FXML
    private NumberAxis xAxis;
    
    ObservableList<XYChart.Series<Number,Number>> chartData = FXCollections.observableArrayList();
    ObservableList<Double> Qv = FXCollections.observableArrayList();
    ObservableList<TriangularFuzzyNumber> s = FXCollections.observableArrayList();
    ObservableList<TriangularFuzzyNumber> r = FXCollections.observableArrayList();
    ObservableList<Double> Q = FXCollections.observableArrayList();
    ObservableList<String> alt = FXCollections.observableArrayList();
    
    Double Rminus = 0.0;
	Double Sminus = 0.0;
	Double Rstar = 0.0;
    Double Sstar = 0.0;
    
    FuzzyVikorCentroid centr;
    FuzzyVikorMediana med;
    FuzzyVikorMax max;
    
    @FXML
    void initialize() {
    	for(int i = 0; i < VikorController.PTableData.size();i++) {
    		alt.add(VikorController.PTableData.get(i).get(0));
    	}
    	ObservableList<String> Mnames = FXCollections.observableArrayList();
    	Mnames.addAll("Центроид","Медиана","Больший Максимум");
    	MethodBox.setItems(Mnames);
    	
    	  Rminus = 0.0;
		  Sminus = 0.0;
		  Rstar = 0.0;
		  Sstar = 0.0;
    	
    	MethodBox.setOnAction(e->{
    		TextArea.clear();
    		chartData.clear();
    		Chart.getData().clear();
    		if(MethodBox.getValue().equals(Mnames.get(0))) {
    			ReButton.setDisable(true);
    	        TextArea.setEditable(false);
    	        centr = VikorController.Cindex;
    	 
    	        //alt = VikorController.;
    	        s = centr.S;
    	        r = centr.R;
    	        
    	         Rminus = centr.Rminus1;
    			 Sminus = centr.Sminus1;
    			 Rstar = centr.Rstar1;
    			 Sstar = centr.Sstar1;
    	        Qv = centr.Qv;
    	        Q = centr.Q1;
    	        
    	        Slider.setMin(Collections.min(Qv));
    	        Slider.setMax(Collections.max(Qv));
    	        Slider.setShowTickLabels(true);
    	        Slider.setShowTickMarks(true);

    	        for(int i  = 0; i < s.size(); i++) {
    	        		
    	        	Series<Number, Number> series1 = new Series<>();
    	        	for(int j = 0; j < Qv.size();j++) {
    	        		Double q = (Qv.get(j) * (s.get(i).DefazzyCentriod() - Sstar) / (Sminus - Sstar))+((1 - Qv.get(j)) * (r.get(i).DefazzyCentriod() - Rstar)/(Rminus - Rstar));
    	        		series1.getData().add(new Data<Number, Number>(Qv.get(j),q));
    	        		
    	        	}
    	        	series1.setName(alt.get(i));
    	        	//Chart.getData().add(series1);
    	        	chartData.add(series1);
    	        }
    	        
    	        Series<Number, Number> series2 = new Series<>();
    	        series2.getData().add(new Data<Number,Number>(Double.valueOf(VikorController.Settings.getV()),0));//settings
    	        series2.getData().add(new Data<Number,Number>(Double.valueOf(VikorController.Settings.getV()),1));
    	        series2.setName("V");
    	        chartData.add(series2);
    	        
    	        xAxis.setLowerBound(Qv.get(0));
    	        xAxis.setUpperBound(Qv.get(Qv.size()-1));
    	        //xAxis.setTickLabelsVisible(true);
    	        Chart.setData(chartData);
    	        
    	        Slider.setValue(Double.valueOf(VikorController.Settings.getV()));
    	        Slider.setShowTickLabels(true);
    	        Slider.setShowTickMarks(true);
    	        int c = 0;
    			TextArea.appendText("при V = "+VikorController.Settings.getV()+"\n");
    			do {
    				TextArea.appendText(String.valueOf(alt.get(c))+" = "+String.valueOf(Q.get(c))+"\n");
    				
    				c++;
    			}while(c != Q.size());
    		}
    		if(MethodBox.getValue().equals(Mnames.get(1))) {
    			ReButton.setDisable(true);
    	        TextArea.setEditable(false);
    	        med = VikorController.Mediana;
    	 
    	        //alt = VikorController.;
    	        s = med.S;
    	        r = med.R;
    	        
    	         Rminus = med.Rminus1;
    			 Sminus = med.Sminus1;
    			 Rstar = med.Rstar1;
    			 Sstar = med.Sstar1;
    	        Qv = med.Qv;
    	        Q = med.Q1;
    	        
    	        Slider.setMin(Collections.min(Qv));
    	        Slider.setMax(Collections.max(Qv));
    	        Slider.setShowTickLabels(true);
    	        Slider.setShowTickMarks(true);

    	        for(int i  = 0; i < s.size(); i++) {
    	        		
    	        	Series<Number, Number> series1 = new Series<>();
    	        	for(int j = 0; j < Qv.size();j++) {
    	        		Double q = (Qv.get(j) * (s.get(i).DefuzzyMediana() - Sstar) / (Sminus - Sstar))+((1 - Qv.get(j)) * (r.get(i).DefuzzyMediana() - Rstar)/(Rminus - Rstar));
    	        		series1.getData().add(new Data<Number, Number>(Qv.get(j),q));
    	        		
    	        	}
    	        	series1.setName(alt.get(i));
    	        	//Chart.getData().add(series1);
    	        	chartData.add(series1);
    	        }
    	        
    	        Series<Number, Number> series2 = new Series<>();
    	        series2.getData().add(new Data<Number,Number>(Double.valueOf(VikorController.Settings.getV()),0));//settings
    	        series2.getData().add(new Data<Number,Number>(Double.valueOf(VikorController.Settings.getV()),1));
    	        series2.setName("V");
    	        chartData.add(series2);
    	        
    	        xAxis.setLowerBound(Qv.get(0));
    	        xAxis.setUpperBound(Qv.get(Qv.size()-1));
    	        //xAxis.setTickLabelsVisible(true);
    	        Chart.setData(chartData);
    	        
    	        Slider.setValue(Double.valueOf(VikorController.Settings.getV()));
    	        Slider.setShowTickLabels(true);
    	        Slider.setShowTickMarks(true);
    	        int c = 0;
    			TextArea.appendText("при V = "+VikorController.Settings.getV()+"\n");
    			do {
    				TextArea.appendText(String.valueOf(alt.get(c))+" = "+String.valueOf(Q.get(c))+"\n");
    				
    				c++;
    			}while(c != Q.size());
    		}
    		if(MethodBox.getValue().equals(Mnames.get(2))) {
    			ReButton.setDisable(true);
    	        TextArea.setEditable(false);
    	        max = VikorController.LargeMax;
    	 
    	        //alt = VikorController.;
    	        s = max.S;
    	        r = max.R;
    	        
    	         Rminus = max.Rminus1;
    			 Sminus = max.Sminus1;
    			 Rstar = max.Rstar1;
    			 Sstar = max.Sstar1;
    	        Qv = max.Qv;
    	        Q = max.Q1;
    	        
    	        Slider.setMin(Collections.min(Qv));
    	        Slider.setMax(Collections.max(Qv));
    	        Slider.setShowTickLabels(true);
    	        Slider.setShowTickMarks(true);

    	        for(int i  = 0; i < s.size(); i++) {
    	        		
    	        	Series<Number, Number> series1 = new Series<>();
    	        	for(int j = 0; j < Qv.size();j++) {
    	        		Double q = (Qv.get(j) * (s.get(i).DefuzzyLargeMax() - Sstar) / (Sminus - Sstar))+((1 - Qv.get(j)) * (r.get(i).DefuzzyLargeMax() - Rstar)/(Rminus - Rstar));
    	        		series1.getData().add(new Data<Number, Number>(Qv.get(j),q));
    	        		
    	        	}
    	        	series1.setName(alt.get(i));
    	        	//Chart.getData().add(series1);
    	        	chartData.add(series1);
    	        }
    	        
    	        Series<Number, Number> series2 = new Series<>();
    	        series2.getData().add(new Data<Number,Number>(Double.valueOf(VikorController.Settings.getV()),0));//settings
    	        series2.getData().add(new Data<Number,Number>(Double.valueOf(VikorController.Settings.getV()),1));
    	        series2.setName("V");
    	        chartData.add(series2);
    	        
    	        xAxis.setLowerBound(Qv.get(0));
    	        xAxis.setUpperBound(Qv.get(Qv.size()-1));
    	        //xAxis.setTickLabelsVisible(true);
    	        Chart.setData(chartData);
    	        Event event = new Event(Event.ANY);
    	        MethodBox.fireEvent(event);
    	        Slider.setValue(Double.valueOf(VikorController.Settings.getV()));
    	        Slider.setShowTickLabels(true);
    	        Slider.setShowTickMarks(true);
    	        int c = 0;
    			TextArea.appendText("при V = "+VikorController.Settings.getV()+"\n");
    			do {
    				TextArea.appendText(String.valueOf(alt.get(c))+" = "+String.valueOf(Q.get(c))+"\n");
    				
    				c++;
    			}while(c != Q.size());
    		}
    	});
    	
		//xAxis.setTickLabelsVisible(true);
        Slider.valueProperty().addListener(new ChangeListener<Number>() {
        	
            @Override
            public void changed(ObservableValue<? extends Number> observable, //
                  Number oldValue, Number newValue) {
            	ReButton.setDisable(false);
            		Chart.getData().clear();
            		TextArea.clear();
            		chartData.clear();
            		ObservableList<Double> qq1 = FXCollections.observableArrayList();
            		ObservableList<String> a = FXCollections.observableArrayList();
            		
            			if(MethodBox.getValue().equals(Mnames.get(0))) {
	            			a = centr.altname();
	                		qq1 = centr.VikorV((double)newValue);
		        		}
		        		if(MethodBox.getValue().equals(Mnames.get(1))) {
		        			a = med.altname();
	                		qq1 = med.VikorV((double)newValue);
			        		
		        		}
		        		if(MethodBox.getValue().equals(Mnames.get(2))) {
		        			a = max.altname();
	                		qq1 = max.VikorV((double)newValue);
		        		}
            		
            		Series<Number, Number> series2 = new Series<>();
					series2.getData().add(0, new Data<Number,Number>(newValue,0));
					series2.getData().add(1, new Data<Number,Number>(newValue,1));
					series2.setName("V");
					for(int i  = 0; i < s.size(); i++) {
		        		
			        	Series<Number, Number> series1 = new Series<>();
			        	for(int j = 0; j < Qv.size();j++) {
			        		double q = 0;
			        		if(MethodBox.getValue().equals(Mnames.get(0))) {
			        		 q = (Qv.get(j) * (s.get(i).DefazzyCentriod() - Sstar) / (Sminus - Sstar))+
			        				((1 - Qv.get(j)) * (r.get(i).DefazzyCentriod() - Rstar)/(Rminus - Rstar));
			        		}
			        		if(MethodBox.getValue().equals(Mnames.get(1))) {
			        			  q = (Qv.get(j) * (s.get(i).DefuzzyMediana()- Sstar) / (Sminus - Sstar))+
				        				((1 - Qv.get(j)) * (r.get(i).DefuzzyMediana() - Rstar)/(Rminus - Rstar));
				        	}
			        		if(MethodBox.getValue().equals(Mnames.get(2))) {
			        			  q = (Qv.get(j) * (s.get(i).DefuzzyLargeMax() - Sstar) / (Sminus - Sstar))+
				        				((1 - Qv.get(j)) * (r.get(i).DefuzzyLargeMax() - Rstar)/(Rminus - Rstar));
				        			
			        		}
			        		series1.getData().add(new Data<Number, Number>(Qv.get(j),q));
			        		
			        	}
			        	series1.setName(alt.get(i));
			        	chartData.add(series1);
			        }
					xAxis.setLowerBound(Qv.get(0));
			        chartData.add(series2);
			        xAxis.setUpperBound(Qv.get(Qv.size()-1));
			        //xAxis.setTickLabelsVisible(true);
			        Chart.setData(chartData);
            		int c = 0;
            		TextArea.appendText("при V = "+newValue+"\n");
            		do {
            			TextArea.appendText(String.valueOf(a.get(c))+" = "+String.valueOf(qq1.get(c))+"\n");
            			
            			c++;
            		}while(c != qq1.size());
            }
         });
    ReButton.setOnAction(e->{
    	
    	chartData.clear();	
    	Chart.getData().clear();
    	TextArea.setText("");
    	Slider.setValue(Double.valueOf(VikorController.Settings.getV()));
    	ReButton.setDisable(true);
    	
    });
  }
}

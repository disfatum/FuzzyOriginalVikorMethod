package org.vikor.Controllers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;

import org.vikor.Methods.ClassicVikor;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
/**
 * @author disfatum
 * @mail disfatum@yandex.ru
 * @github https://github.com/disfatum
 *
 */
public class QvController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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
    ObservableList<Double> s = FXCollections.observableArrayList();
    ObservableList<Double> r = FXCollections.observableArrayList();
    ObservableList<Double> Q = FXCollections.observableArrayList();
    ObservableList<String> alt = FXCollections.observableArrayList();
    
    @FXML
    void initialize() {
    	for(int i = 0; i < VikorController.PTableData.size();i++) {
    		alt.add(VikorController.PTableData.get(i).get(0));
    	}
    	
    	ReButton.setDisable(true);
        TextArea.setEditable(false);
        ClassicVikor t = VikorController.t;
 
        //alt = VikorController.;
        s = t.S;
        r = t.R;
        
        Double Rminus = Collections.max(r);
		Double Sminus = Collections.max(s);
		Double Rstar = Collections.min(r);
		Double Sstar = Collections.min(s);
        Qv = t.Qv;
        Q = t.Q;
        
        Slider.setMin(Collections.min(Qv));
        Slider.setMax(Collections.max(Qv));
        Slider.setShowTickLabels(true);
        Slider.setShowTickMarks(true);

        for(int i  = 0; i < s.size(); i++) {
        		
        	Series<Number, Number> series1 = new Series<>();
        	for(int j = 0; j < Qv.size();j++) {
        		Double q = (Qv.get(j) * (s.get(i) - Sstar) / (Sminus - Sstar))+((1 - Qv.get(j)) * (r.get(i) - Rstar)/(Rminus - Rstar));
        		series1.getData().add(new Data<Number, Number>(Qv.get(j),q));
        		
        	}
        	series1.setName(alt.get(i));
        	Chart.getData().add(series1);
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
		TextArea.appendText("��� V = "+VikorController.Settings.getV()+"\n");
		do {
			TextArea.appendText(String.valueOf(alt.get(c))+" = "+String.valueOf(Q.get(c))+"\n");
			
			c++;
		}while(c != Q.size());
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
            		a = t.altname();
            		qq1 = t.VikorV((double)newValue);
            		Series<Number, Number> series2 = new Series<>();
					series2.getData().add(0, new Data<Number,Number>(newValue,0));
					series2.getData().add(1, new Data<Number,Number>(newValue,1));
					series2.setName("V");
					for(int i  = 0; i < s.size(); i++) {
		        		
			        	Series<Number, Number> series1 = new Series<>();
			        	for(int j = 0; j < Qv.size();j++) {
			        		Double q = (Qv.get(j) * (s.get(i) - Sstar) / (Sminus - Sstar))+((1 - Qv.get(j)) * (r.get(i) - Rstar)/(Rminus - Rstar));
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
            		double value = (Double) newValue; 
            		value = new BigDecimal(value).setScale(3, RoundingMode.UP).doubleValue();
            		TextArea.appendText("��� V = "+value+"\n");
            		do {
            			double buf = new BigDecimal(qq1.get(c)).setScale(3, RoundingMode.UP).doubleValue();
            			TextArea.appendText(String.valueOf(a.get(c))+" = "+String.valueOf(buf)+"\n");
            			
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

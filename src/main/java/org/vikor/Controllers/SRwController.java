package org.vikor.Controllers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import org.vikor.DataStructure.Settings;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
/**
 * @author disfatum
 * @mail disfatum@yandex.ru
 * @github https://github.com/disfatum
 *
 */
public class SRwController {

	@FXML
    private NumberAxis xAxisR;

    @FXML
    private NumberAxis yAxisR;
    
    @FXML
    private NumberAxis xAxisS;

    @FXML
    private NumberAxis yAxisS;
    
    @FXML
    private Button OkButton;
    
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea TextAreaS;

    @FXML
    private LineChart<Number, Number> ChartS;

    @FXML
    private Slider SliderS;

    @FXML
    private TextArea TextAreaR;

    @FXML
    private LineChart<Number, Number> ChartR;

    @FXML
    private Slider SliderR;

    @FXML
    private ComboBox<String> ComboBox;

    ObservableList<String> alt = FXCollections.observableArrayList();
    ObservableList<Double> s = FXCollections.observableArrayList();
    ObservableList<Double> r = FXCollections.observableArrayList();
    ObservableList<String> critn = FXCollections.observableArrayList();
    ObservableList<Double> critw = FXCollections.observableArrayList();
    ObservableList<XYChart.Series<Number,Number>> chartDatas = FXCollections.observableArrayList();
    ObservableList<XYChart.Series<Number,Number>> chartDatar= FXCollections.observableArrayList();
    ObservableList<Double> ww = FXCollections.observableArrayList();
    	ObservableList<Double> w = FXCollections.observableArrayList();
        ObservableList<List<Double>> w0 = FXCollections.observableArrayList();
        ObservableList<List<Double>> w1 = FXCollections.observableArrayList();
        ObservableList<List<Double>> w2 = FXCollections.observableArrayList();
        double max = 0;
        double min = 0;
    @FXML
    void initialize() {
    	for(int i = 0; i < VikorController.PTableData.size();i++) {
    		alt.add(VikorController.PTableData.get(i).get(0));
    	}
    	
        for(int i = 0; i < VikorController.FTableData.size(); i++) {
        	critn.add(VikorController.FTableData.get(i).getName());
        	critw.add(Double.valueOf(VikorController.FTableData.get(i).getWeigh()));
        }
        ClassicVikor t = VikorController.t;
        ComboBox.setItems(critn);
        ComboBox.setValue(critn.get(0));
       
       
        SliderS.valueProperty().addListener(new ChangeListener<Number>() {
        	
            @Override
            public void changed(ObservableValue<? extends Number> observable, //
                  Number oldValue, Number newValue) {
            	ChartS.getData().clear();
            	chartDatas.clear();
            	TextAreaS.clear();
            	xAxisS.setUpperBound(Collections.max(w));
           	    xAxisS.setLowerBound(Collections.min(w));
        	    ObservableList<Double> g = FXCollections.observableArrayList();
        	    for(int i =0; i < alt.size();i++) {
        	    	Series<Number, Number> series1= new Series<>();
        	    	for(int j = 0; j < w.size();j++) {
        	    		double tt = t.VikorS(w.get(j), i,critn.indexOf(ComboBox.getValue()));
        	    		g.add(tt);
        	    		double y =w.get(j);
        	    		series1.getData().add(new Data<Number,Number>(y,tt));
        	    	}
        	    	series1.setName("S "+alt.get(i));
        	    	chartDatas.add(series1);
        	    }
        	    max = Collections.max(g);
        	    min = Collections.min(g);
        	    Series<Number, Number> series2 = new Series<>();
        	    if(min > 0) {
                    series2.getData().add(new Data<Number,Number>(newValue,0));
        	    }
        	    else {
        	    	series2.getData().add(new Data<Number,Number>(newValue,min));
        	    }
                series2.getData().add(new Data<Number,Number>(newValue,max));
                series2.setName("w");
                chartDatas.add(series2);
                ChartS.setData(chartDatas);
                
                TextAreaS.appendText("��� w="+newValue+"\n");
                for(int i =0; i < alt.size();i++) {
                	double s = t.VikorS(Double.valueOf(newValue.toString()), i,critn.indexOf(ComboBox.getValue()));
                	s = new BigDecimal(s).setScale(3, RoundingMode.UP).doubleValue();
                	TextAreaS.appendText("S "+alt.get(i)+"="+s+"\n");
                }
            }
       });
        
       SliderR.valueProperty().addListener(new ChangeListener<Number>() {
        	
            @Override
            public void changed(ObservableValue<? extends Number> observable, //
                  Number oldValue, Number newValue) {
            	ChartR.getData().clear();
            	chartDatar.clear();
            	TextAreaR.clear();
            	xAxisR.setUpperBound(Collections.max(w));
           	    xAxisR.setLowerBound(Collections.min(w));
        	    ObservableList<Double> g = FXCollections.observableArrayList();
        	    for(int i =0; i < alt.size();i++) {
        	    	Series<Number, Number> series1= new Series<>();
        	    	for(int j = 0; j < w.size();j++) {
        	    		double tt = t.VikorR(w.get(j), i, critn.indexOf(ComboBox.getValue()));
        	    		g.add(tt);
        	    		double y =w.get(j);
        	    		series1.getData().add(new Data<Number,Number>(y,tt));
        	    	}
        	    	series1.setName("R "+alt.get(i));
        	    	chartDatar.add(series1);
        	    }
        	    max = Collections.max(g);
        	    min = Collections.min(g);
        	    Series<Number, Number> series2 = new Series<>();
        	    if(min > 0) {
                    series2.getData().add(new Data<Number,Number>(newValue,0));
        	    }
        	    else {
        	    	series2.getData().add(new Data<Number,Number>(newValue,min));
        	    }
                series2.getData().add(new Data<Number,Number>(newValue,max));
                series2.setName("w");
                chartDatar.add(series2);
                ChartR.setData(chartDatar);
                
                TextAreaR.appendText("��� w="+newValue+"\n");
                for(int i =0; i < alt.size();i++) {
                	double r = t.VikorR(Double.valueOf(newValue.toString()), i,critn.indexOf(ComboBox.getValue()));
                	r = new BigDecimal(r).setScale(3, RoundingMode.UP).doubleValue();
                	TextAreaR.appendText("R "+alt.get(i)+"="+r+"\n");
                }
            }
            
       });
       OkButton.setOnAction(e->{
    	   
    	   Settings f = VikorController.Settings;
          // for(int j = 0; j < Controller.list.size(); j++) {
           	w.clear();
           	w2.clear();
           	ww.clear();
           	int j = critn.indexOf(ComboBox.getValue());
           	int k = 0;
           	for(int i = 0; i < f.getSRstep() / 2; i++) {
           		w.add(Double.valueOf(VikorController.FTableData.get(j).getWeigh()) - f.getSrs()*i);
           		k++;
           		ww.add(Double.valueOf(VikorController.FTableData.get(j).getWeigh()));
           	}
           	Collections.sort(w);
           	int k1 = 1;
           	for(int l = k; l < f.getSRstep();l++) {
           		w.add(Double.valueOf(VikorController.FTableData.get(j).getWeigh()) + f.getSrs()*k1);
           		ww.add(Double.valueOf(VikorController.FTableData.get(j).getWeigh()));
           		k1++;
           	}
           	w1.add(w);
           	w2 = w1;
           	System.out.println(w1.toString()+ "w1");
         //  }   
        SliderS.setMax(Collections.max(w));
        SliderS.setMin(Collections.min(w));
        SliderR.setMax(Collections.max(w));
        SliderR.setMin(Collections.min(w));
        
        SliderS.setValue(Double.valueOf(VikorController.FTableData.get(critn.indexOf(ComboBox.getValue())).getWeigh()));
        SliderR.setValue(Double.valueOf(VikorController.FTableData.get(critn.indexOf(ComboBox.getValue())).getWeigh()));
       }); 
    }
}


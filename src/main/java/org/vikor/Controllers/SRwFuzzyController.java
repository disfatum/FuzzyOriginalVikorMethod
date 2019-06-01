package org.vikor.Controllers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.vikor.DataStructure.TriangularFuzzyNumber;
import org.vikor.FuzzyOperations.FuzzyOp;
import org.vikor.Methods.FuzzyVikorCentroid;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;

public class SRwFuzzyController {

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
    private AreaChart<Number, Number> ChartS;

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
    	ObservableList<TriangularFuzzyNumber> w = FXCollections.observableArrayList();
        ObservableList<List<Double>> w0 = FXCollections.observableArrayList();
        ObservableList<List<Double>> w1 = FXCollections.observableArrayList();
        ObservableList<List<Double>> w2 = FXCollections.observableArrayList();
        FuzzyOp Fop = new FuzzyOp();
        double max = 0;
        double min = 0;
    @FXML
    void initialize() {
    	for(int i = 0; i < VikorController.PTableData.size();i++) {
    		alt.add(VikorController.PTableData.get(i).get(0));
    	}
    	
        for(int i = 0; i < VikorController.FTableData.size(); i++) {
        	critn.add(VikorController.FTableData.get(i).getName());
        	//critw.add(Double.valueOf(VikorController.FTableData.get(i).getWeigh()));
        }
        FuzzyVikorCentroid t = VikorController.Cindex;
        ComboBox.setItems(critn);
        //ComboBox.setValue(critn.get(0));
    	
        SliderS.valueProperty().addListener(new ChangeListener<Number>() {
        	
            @Override
            public void changed(ObservableValue<? extends Number> observable, //
                  Number oldValue, Number newValue) {
            	ChartS.getData().clear();
            	TextAreaS.clear();
            	chartDatas.clear();
            	xAxisS.setUpperBound(1);
           	    xAxisS.setLowerBound(0);
           	    
        	    Series<Number, Number> series2= new Series<>();
        	    if(w.get(lol).getCenter() < (double) newValue) {
	        	    w.get(critn.indexOf(ComboBox.getValue())).setCenter((double)newValue);
	        	    w.get(lol).setLeft((double)newValue - tc);
	        	    w.get(lol).setRight(w.get(lol).getLeft()+tf);
	        	    System.out.println(w.get(lol).getLeft()+","+w.get(lol).getCenter()+";"+w.get(lol).getRight()+" 1");
	        	    	series2.getData().add(new Data<Number,Number>(w.get(lol).getLeft(),(Number)0));
	        	    	series2.getData().add(new Data<Number,Number>((double)newValue,(Number)1));
	        	    	series2.getData().add(new Data<Number,Number>(w.get(lol).getRight(),(Number)0));
	        	    	
	        	    	
	        	    	series2.setName("w");
	        	    	chartDatas.add(series2);
	        	    	
	            }
        	    else {
        	        w.get(critn.indexOf(ComboBox.getValue())).setCenter((double)newValue);
            	    w.get(lol).setLeft((double)newValue - tc);
            	    w.get(lol).setRight(w.get(lol).getLeft()+tf);
            	    System.out.println(w.get(lol).getLeft()+","+w.get(lol).getCenter()+";"+w.get(lol).getRight());
             	    	series2.getData().add(new Data<Number,Number>(w.get(critn.indexOf(ComboBox.getValue())).getLeft(),(Number)0));
             	    	series2.getData().add(new Data<Number,Number>((double)newValue,(Number)1));
             	    	series2.getData().add(new Data<Number,Number>(w.get(critn.indexOf(ComboBox.getValue())).getRight(),(Number)0));
             	    	
             	    	series2.setName("w");
             	    	chartDatas.add(series2);
        	    }
                ChartS.setData(chartDatas);
                
                double left = new BigDecimal(w.get(lol).getLeft()).setScale(3, RoundingMode.UP).doubleValue();
                double center = new BigDecimal((double)newValue).setScale(3, RoundingMode.UP).doubleValue();
                double right = new BigDecimal(w.get(lol).getRight()).setScale(3, RoundingMode.UP).doubleValue();
                
                TextAreaS.appendText("при w= "+left+","+center+","+right+"\n");
                for(int i =0; i < alt.size();i++) {
                	TriangularFuzzyNumber tfn = new TriangularFuzzyNumber(w.get(lol).getLeft(),(double)newValue,w.get(lol).getRight());
                	TextAreaS.appendText("S "+alt.get(i)+"="+
                	t.VikorS(tfn, i,critn.indexOf(ComboBox.getValue())).DataforTable()+"\n");
                }
            }
       });
        
       SliderR.valueProperty().addListener(new ChangeListener<Number>() {
    	   @Override
           public void changed(ObservableValue<? extends Number> observable, //
                 Number oldValue, Number newValue) {
           	ChartR.getData().clear();
           	TextAreaR.clear();
           	chartDatar.clear();
           	xAxisR.setUpperBound(1);
          	    xAxisR.setLowerBound(0);
          	    
       	    Series<Number, Number> series2= new Series<>();
       	    if(w.get(lol).getCenter() < (double) newValue) {
	        	    w.get(critn.indexOf(ComboBox.getValue())).setCenter((double)newValue);
	        	    w.get(lol).setLeft((double)newValue - tc);
	        	    w.get(lol).setRight(w.get(lol).getLeft()+tf);
	        	    System.out.println(w.get(lol).getLeft()+","+w.get(lol).getCenter()+";"+w.get(lol).getRight()+" 1");
	        	    	series2.getData().add(new Data<Number,Number>(w.get(lol).getLeft(),(Number)0));
	        	    	series2.getData().add(new Data<Number,Number>((double)newValue,(Number)1));
	        	    	series2.getData().add(new Data<Number,Number>(w.get(lol).getRight(),(Number)0));
	        	    	
	        	    	
	        	    	series2.setName("w");
	        	    	chartDatar.add(series2);
	        	    	
	            }
       	    else {
       	        w.get(critn.indexOf(ComboBox.getValue())).setCenter((double)newValue);
           	    w.get(lol).setLeft((double)newValue - tc);
           	    w.get(lol).setRight(w.get(lol).getLeft()+tf);
           	    System.out.println(w.get(lol).getLeft()+","+w.get(lol).getCenter()+";"+w.get(lol).getRight());
            	    	series2.getData().add(new Data<Number,Number>(w.get(critn.indexOf(ComboBox.getValue())).getLeft(),(Number)0));
            	    	series2.getData().add(new Data<Number,Number>((double)newValue,(Number)1));
            	    	series2.getData().add(new Data<Number,Number>(w.get(critn.indexOf(ComboBox.getValue())).getRight(),(Number)0));
            	    	
            	    	series2.setName("w");
            	    	chartDatar.add(series2);
       	    }
               ChartR.setData(chartDatar);
               
               double left = new BigDecimal(w.get(lol).getLeft()).setScale(3, RoundingMode.UP).doubleValue();
               double center = new BigDecimal((double)newValue).setScale(3, RoundingMode.UP).doubleValue();
               double right = new BigDecimal(w.get(lol).getRight()).setScale(3, RoundingMode.UP).doubleValue();
               
               TextAreaR.appendText("при w= "+left+","+center+","+right+"\n");
               for(int i =0; i < alt.size();i++) {
               	TriangularFuzzyNumber tfn = new TriangularFuzzyNumber(w.get(lol).getLeft(),(double)newValue,w.get(lol).getRight());
               	TextAreaR.appendText("R "+alt.get(i)+"="+
               	t.VikorR(tfn, i,critn.indexOf(ComboBox.getValue())).DataforTable()+"\n");
               }
           }
            
       });
        
       OkButton.setOnAction(e->{
    	   
           	w.clear();
           	w2.clear();
           	ww.clear();
           	for(int i = 0; i < VikorController.FTableData.size(); i++) {
           		TriangularFuzzyNumber tfn = new TriangularFuzzyNumber(1.0,1.0,1.0);
           		tfn.RefreshData( VikorController.FTableData.get(i).getWeigh());
           		w.add(tfn);
           	}
           	  lol = critn.indexOf(ComboBox.getValue());
     	   
    	     tc = w.get(lol).getCenter() - w.get(lol).getLeft();
    	     tf = w.get(lol).getRight() - w.get(lol).getLeft();
           //	xAxisS.setUpperBound(1.3);
       	   // xAxisS.setLowerBound(-0.3);
           SliderS.setValue(w.get(critn.indexOf(ComboBox.getValue())).getCenter());
           SliderR.setValue(w.get(critn.indexOf(ComboBox.getValue())).getCenter());
           	w2 = w1;
           	System.out.println(tc+ "tc");
        	System.out.println(tf+ "tf");
        
       }); 
    }
    int  lol = 0;
    double tc = 0;
    double tf = 0;
    double tcr = 0;
    double tfr = 0;
}



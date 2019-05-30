package org.vikor.FuzzyOperations;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.List;
import org.vikor.DataStructure.TriangularFuzzyNumber;
import javafx.collections.FXCollections;

public class FuzzyOp {
	
	public TriangularFuzzyNumber Multyply(TriangularFuzzyNumber tfn1, TriangularFuzzyNumber tfn2) {
		
		TriangularFuzzyNumber result = new TriangularFuzzyNumber(1.0, 1.0, 1.0);
		List<Double> l = FXCollections.observableArrayList();
		l.add(tfn2.getLeft());l.add(tfn2.getCenter());l.add(tfn2.getRight());
		Collections.sort(l);
		System.out.println(l);
		
		double def = tfn1.getLeft()*l.get(0);
		def = new BigDecimal(def).setScale(3, RoundingMode.UP).doubleValue();
		result.setLeft(def);
		
		def = tfn1.getCenter()*tfn2.getCenter();
		def = new BigDecimal(def).setScale(3, RoundingMode.UP).doubleValue();
		result.setCenter(def);
		
		def = tfn1.getRight()*l.get(2);
		def = new BigDecimal(def).setScale(3, RoundingMode.UP).doubleValue();
		result.setRight(def);
		
		return result;
		
	}
	
	public TriangularFuzzyNumber Summ(TriangularFuzzyNumber tfn1, TriangularFuzzyNumber tfn2) {
		
		TriangularFuzzyNumber result = new TriangularFuzzyNumber(1.0, 1.0, 1.0);
		List<Double> l = FXCollections.observableArrayList();
		l.add(tfn2.getLeft());l.add(tfn2.getCenter());l.add(tfn2.getRight());
		Collections.sort(l);
		
		double def = tfn1.getLeft()+l.get(0);
		def = new BigDecimal(def).setScale(3, RoundingMode.UP).doubleValue();
		result.setLeft(def);
		
		def = tfn1.getCenter()+tfn2.getCenter();
		def = new BigDecimal(def).setScale(3, RoundingMode.UP).doubleValue();
		result.setCenter(def);
		
		def = tfn1.getRight()+l.get(2);
		def = new BigDecimal(def).setScale(3, RoundingMode.UP).doubleValue();
		result.setRight(def);
		
		return result;
		
	}
	
	public TriangularFuzzyNumber Minus(TriangularFuzzyNumber tfn1, TriangularFuzzyNumber tfn2) {

		TriangularFuzzyNumber result = new TriangularFuzzyNumber(1.0, 1.0, 1.0);
		List<Double> l = FXCollections.observableArrayList();
		l.add(tfn2.getLeft());l.add(tfn2.getCenter());l.add(tfn2.getRight());
		Collections.sort(l);
		//System.out.println(l);
		
		double def = tfn1.getLeft()-l.get(2);
		def = new BigDecimal(def).setScale(3, RoundingMode.UP).doubleValue();
		result.setLeft(def);
		def = tfn1.getCenter()-tfn2.getCenter();
		def = new BigDecimal(def).setScale(3, RoundingMode.UP).doubleValue();
		result.setCenter(def);
		def =tfn1.getRight()-l.get(0);
		def = new BigDecimal(def).setScale(3, RoundingMode.UP).doubleValue();
		result.setRight(def);
		
		return result;
		
	}
	
	public TriangularFuzzyNumber Div(TriangularFuzzyNumber tfn1, TriangularFuzzyNumber tfn2) {

		TriangularFuzzyNumber result = new TriangularFuzzyNumber(1.0, 1.0, 1.0);
		List<Double> l = FXCollections.observableArrayList();
		l.add(tfn2.getLeft());l.add(tfn2.getCenter());l.add(tfn2.getRight());
		Collections.sort(l);
		double def = tfn1.getLeft()/l.get(2);
		def = new BigDecimal(def).setScale(3, RoundingMode.UP).doubleValue();
		result.setLeft(def);
		
		def = tfn1.getCenter()/tfn2.getCenter();
		def = new BigDecimal(def).setScale(3, RoundingMode.UP).doubleValue();
		result.setCenter(def);
		
		def = tfn1.getRight()/l.get(0);
		def = new BigDecimal(def).setScale(3, RoundingMode.UP).doubleValue();
		result.setRight(def);
		
		return result;
	}
	
	public int MAX_Centroid(List<TriangularFuzzyNumber> sdata) {
		List<Double> defuzzydata = FXCollections.observableArrayList();
		for(int i = 0; i < sdata.size();i++) {
			defuzzydata.add(sdata.get(i).DefazzyCentriod());
		}
		int res = defuzzydata.indexOf(Collections.max(defuzzydata));
		return res;
		
	}
	public int MAX_Mediana(List<TriangularFuzzyNumber> sdata) {
		List<Double> defuzzydata = FXCollections.observableArrayList();
		for(int i = 0; i < sdata.size();i++) {
			defuzzydata.add(sdata.get(i).DefuzzyMediana());
		}
		int res = defuzzydata.indexOf(Collections.max(defuzzydata));
		return res;
		
	}
	public int MAX_SmallestMax(List<TriangularFuzzyNumber> sdata) {
		List<Double> defuzzydata = FXCollections.observableArrayList();
		for(int i = 0; i < sdata.size();i++) {
			defuzzydata.add(sdata.get(i).DefuzzySmallestMax());
		}
		int res = defuzzydata.indexOf(Collections.max(defuzzydata));
		return res;
		
	}
	public int MAX_LargestMax(List<TriangularFuzzyNumber> sdata) {
		List<Double> defuzzydata = FXCollections.observableArrayList();
		for(int i = 0; i < sdata.size();i++) {
			defuzzydata.add(sdata.get(i).DefuzzyLargeMax());
		}
		int res = defuzzydata.indexOf(Collections.max(defuzzydata));
		return res;
		
	}
	public int Min_Centroid(List<TriangularFuzzyNumber> sdata) {
		List<Double> defuzzydata = FXCollections.observableArrayList();
		for(int i = 0; i < sdata.size();i++) {
			defuzzydata.add(sdata.get(i).DefazzyCentriod());
		}
		int res = defuzzydata.indexOf(Collections.min(defuzzydata));
		return res;
		
	}
	public int Min_Mediana(List<TriangularFuzzyNumber> sdata) {
		List<Double> defuzzydata = FXCollections.observableArrayList();
		for(int i = 0; i < sdata.size();i++) {
			defuzzydata.add(sdata.get(i).DefuzzyMediana());
		}
		int res = defuzzydata.indexOf(Collections.min(defuzzydata));
		return res;
		
	}
	public int Min_SmallestMax(List<TriangularFuzzyNumber> sdata) {
		List<Double> defuzzydata = FXCollections.observableArrayList();
		for(int i = 0; i < sdata.size();i++) {
			defuzzydata.add(sdata.get(i).DefuzzySmallestMax());
		}
		int res = defuzzydata.indexOf(Collections.min(defuzzydata));
		return res;
		
	}
	public int Min_LargestMax(List<TriangularFuzzyNumber> sdata) {
		List<Double> defuzzydata = FXCollections.observableArrayList();
		for(int i = 0; i < sdata.size();i++) {
			defuzzydata.add(sdata.get(i).DefuzzyLargeMax());
		}
		int res = defuzzydata.indexOf(Collections.min(defuzzydata));
		return res;
		
	}
}

package org.vikor.FuzzyOperations;

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
		result.setLeft(tfn1.getLeft()*l.get(0));
		result.setCenter(tfn1.getCenter()*tfn2.getCenter());
		result.setRight(tfn1.getRight()*l.get(2));
		
		return result;
		
	}
	
	public TriangularFuzzyNumber Summ(TriangularFuzzyNumber tfn1, TriangularFuzzyNumber tfn2) {
		
		TriangularFuzzyNumber result = new TriangularFuzzyNumber(1.0, 1.0, 1.0);
		List<Double> l = FXCollections.observableArrayList();
		l.add(tfn2.getLeft());l.add(tfn2.getCenter());l.add(tfn2.getRight());
		Collections.sort(l);
		
		result.setLeft(tfn1.getLeft()+l.get(0));
		result.setCenter(tfn1.getCenter()+tfn2.getCenter());
		result.setRight(tfn1.getRight()+l.get(2));
		
		return result;
		
	}
	
	public TriangularFuzzyNumber Minus(TriangularFuzzyNumber tfn1, TriangularFuzzyNumber tfn2) {

		TriangularFuzzyNumber result = new TriangularFuzzyNumber(1.0, 1.0, 1.0);
		List<Double> l = FXCollections.observableArrayList();
		l.add(tfn2.getLeft());l.add(tfn2.getCenter());l.add(tfn2.getRight());
		Collections.sort(l);
		//System.out.println(l);
		result.setLeft(tfn1.getLeft()-l.get(2));
		result.setCenter(tfn1.getCenter()-tfn2.getCenter());
		result.setRight(tfn1.getRight()-l.get(0));
		
		return result;
		
	}
	
	public TriangularFuzzyNumber Div(TriangularFuzzyNumber tfn1, TriangularFuzzyNumber tfn2) {

		TriangularFuzzyNumber result = new TriangularFuzzyNumber(1.0, 1.0, 1.0);
		List<Double> l = FXCollections.observableArrayList();
		l.add(tfn2.getLeft());l.add(tfn2.getCenter());l.add(tfn2.getRight());
		Collections.sort(l);
		
		result.setLeft(tfn1.getLeft()/l.get(2));
		result.setCenter(tfn1.getCenter()/tfn2.getCenter());
		result.setRight(tfn1.getRight()/l.get(0));
		
		return result;
	}
}

package org.vikor.Events;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

public class InitMethodBox {
	
	public void init(ComboBox<String> ClassicFuzzyBox){
		
		ObservableList<String> l = FXCollections.observableArrayList();
		
		l.add("Classic VIKOR");l.add("Fuzzy VIKOR");
		ClassicFuzzyBox.setItems(l);
		ClassicFuzzyBox.setValue(l.get(0));
		
	}
}

package org.vikor.Methods;

import org.vikor.DataStructure.OriginalCriterionDataStructure;
import org.vikor.DataStructure.OriginalPtableData;
import org.vikor.DataStructure.Settings;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FuzzyVikor {
	
	public ObservableList<Double> Qv = FXCollections.observableArrayList();
	public ObservableList<Double> Q = FXCollections.observableArrayList();
	public ObservableList<Double> S = FXCollections.observableArrayList();
	public ObservableList<Double> R = FXCollections.observableArrayList();
	public ObservableList<String> AltNames = FXCollections.observableArrayList();
	public ObservableList<String> CritNames = FXCollections.observableArrayList();
	public ObservableList<ObservableList<String>> d = FXCollections.observableArrayList();
	public int Fsize;
	public int Critsize;

	public Double Rminus; 
	public Double Sminus;
	public Double Rstar; 
	public Double Sstar; 
	
	public ObservableList<Double> star = FXCollections.observableArrayList();
	public ObservableList<Double> minus = FXCollections.observableArrayList();
	public ObservableList<Double> W = FXCollections.observableArrayList();
	
	public void Calculate(

	    ObservableList<OriginalPtableData> PTableData,
		ObservableList<OriginalCriterionDataStructure> FTableData,
		Double V,
		Settings Settings) {
			
		d.clear();
		AltNames.clear();
		CritNames.clear();
		Q.clear();
		S.clear();
		R.clear();
		
		for(int i = 0; i < PTableData.size();i++) {
			AltNames.add(PTableData.get(i).getFromList(0));
		}
		for(int i = 0; i < FTableData.size();i++) {
			CritNames.add(FTableData.get(i).getName());
		}
		
		for(int i = 0; i < PTableData.size();i++) {
			ObservableList<String> l = FXCollections.observableArrayList();
			for(int j = 1; j < PTableData.get(i).getList().size();j++) {
				l.add(PTableData.get(i).getFromList(j));
			}
			d.add(l);
		}
		Fsize = FTableData.size();
		Critsize = PTableData.size();
		
		//шаг 1
		
		}
}
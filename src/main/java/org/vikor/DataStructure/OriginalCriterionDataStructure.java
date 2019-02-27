package org.vikor.DataStructure;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class OriginalCriterionDataStructure extends RecursiveTreeObject<OriginalCriterionDataStructure>{
	
	private StringProperty name;
	private StringProperty discription;
	private StringProperty weigh;
	private StringProperty maxmin;
	
	public OriginalCriterionDataStructure(String name, String discription, String weigh, String maxmin) {
		this.name = new SimpleStringProperty(name);
		this.discription = new SimpleStringProperty(discription);
		this.weigh = new SimpleStringProperty(weigh);
		this.maxmin = new SimpleStringProperty(maxmin);
	}
	public String getName() {
		return name.get();
	}

	public void setName(String name) {
		this.name.setValue(name);
	}

	public String getDiscription() {
		return discription.get();
	}

	public void setDiscription(String discription) {
		this.discription.setValue(discription);
	}

	public String getWeigh() {
		return weigh.get();
	}

	public void setWeigh(String weigh) {
		this.weigh.setValue(weigh);;
	}

	public String getMaxmin() {
		return maxmin.get();
	}

	public void setMaxmin(String maxmin) {
		this.maxmin.setValue(maxmin);;
	}
	
	
}

package org.vikor.DataStructure;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

public class OriginalPtableData {
	
	private ObservableList<String> list;
	
	public OriginalPtableData(ObservableList<String > list) {
		this.list = list;
		//System.out.println(this.list.toString()+"List from class");
	}
	public ObservableList<String> getList() {
		return this.list;
	}
	public String getFromList(int index) {
		
		return this.list.get(index);
	}
	public void add(String s) {
		this.list.add(s);
	}
	public void addtoLast() {
		StringProperty e = new SimpleStringProperty();
		e.set("1");
		this.list.add(e.get());
	}
	public void setinlist(int index,String s) {
		StringProperty e = new SimpleStringProperty();
		e.set(s);
		this.list.set(index, s);
	}

	public void setList(ObservableList<String> list) {
		this.list = list;
	}
	
	public void remove(int index) {
		this.list.remove(index);
	}
	
	public int size() {
		return this.list.size();
	}
	public String get(int j) {
		// TODO Auto-generated method stub
		return this.list.get(j);
	}
}

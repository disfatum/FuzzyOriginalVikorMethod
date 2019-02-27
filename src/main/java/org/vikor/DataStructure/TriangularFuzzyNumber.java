package org.vikor.DataStructure;

public class TriangularFuzzyNumber {

	private Double left;
	private Double center;
	private Double right;
	
	public TriangularFuzzyNumber(Double left,Double center,Double right) {
		this.left = left;
		this.center = center;
		this.right = right;
	}

	public Double getLeft() {
		return left;
	}

	public void setLeft(Double left) {
		this.left = left;
	}

	public Double getCenter() {
		return center;
	}

	public void setCenter(Double center) {
		this.center = center;
	}

	public Double getRight() {
		return right;
	}

	public void setRight(Double right) {
		this.right = right;
	}
	public Double DefazzyCentriod() {
	
		Double def = (this.left + this.center+this.right)/3;
		return def;
	}
	public String DataforTable() {
		
		String data = this.left+","+this.center+","+this.right;
		return data ;
	}
	
	public void RefreshData(String data) {
		 String[] l = new String[data.length()];
	    	l = data.split(",");
	    	this.left = Double.valueOf(l[0]);
	    	this.center = Double.valueOf(l[1]);
	    	this.right = Double.valueOf(l[2]);
	}
	
}

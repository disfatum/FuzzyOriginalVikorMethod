package org.vikor.DataStructure;

public class TriangularFuzzyNumber {

	private String left;
	private String center;
	private String right;
	public String Name;
	
	public TriangularFuzzyNumber(Double left,Double center,Double right) {
		this.left = String.valueOf(left);
		this.center = String.valueOf(center);;
		this.right = String.valueOf(right);;
	}
	public TriangularFuzzyNumber(String s) {
		this.Name = s;
	}

	public Double getLeft() {
		return Double.valueOf(left);
	}

	public void setLeft(Double left) {
		this.left = String.valueOf(left);
	}

	public Double getCenter() {
		return Double.valueOf(center);
	}

	public void setCenter(Double center) {
		this.center = String.valueOf(center);
	}

	public Double getRight() {
		return Double.valueOf(right);
	}

	public void setRight(Double right) {
		this.right = String.valueOf(right);
	}
	public Double DefazzyCentriod() {
	
		Double def = (Double.valueOf(this.left) + Double.valueOf(this.center)+Double.valueOf(this.right))/3;
		return def;
	}
	public String DataforTable() {
		
		String data = this.left+","+this.center+","+this.right;
		return data ;
	}
	
	public void RefreshData(String data) {
		 String[] l = new String[3];
	    	l = data.split(",");
	    	this.left = l[0];
	    	this.center = l[1];
	    	this.right = l[2];
	}
	
}

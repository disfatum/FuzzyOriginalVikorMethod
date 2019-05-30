package org.vikor.DataStructure;

import java.math.BigDecimal;
import java.math.RoundingMode;

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
		Double tfn = new BigDecimal(Double.valueOf(left)).setScale(3, RoundingMode.UP).doubleValue();
		return tfn;
	}

	public void setLeft(Double left) {
		this.left = String.valueOf(left);
	}

	public Double getCenter() {
		Double tfn = new BigDecimal(Double.valueOf(center)).setScale(3, RoundingMode.UP).doubleValue();
		return tfn;
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
		def= new BigDecimal(def).setScale(3, RoundingMode.UP).doubleValue();
		return def;
	}
	public Double DefuzzyMediana() {
		
		Double v1 = 2*Double.valueOf(this.left)*Double.valueOf(this.left);
		Double v2 = 2*Double.valueOf(this.right)*Double.valueOf(this.right);
		Double v3 = Double.valueOf(this.center)*Double.valueOf(this.center);
		Double v4 = (v1+v2-v3)/4;
		Double def = Math.pow(v4, 0.5);
		def= new BigDecimal(def).setScale(3, RoundingMode.UP).doubleValue();
		return def;
	}
	public Double DefuzzyLargeMax() {
		Double def = this.getCenter();
		return def;
	}
	public Double DefuzzySmallestMax() {
		Double def = this.getCenter();
		def= new BigDecimal(def).setScale(3, RoundingMode.UP).doubleValue();
		return def;
	}
	public String DataforTable() {
		
		String data = this.left+","+this.center+","+this.right;
		return data ;
	}
	
	public void RefreshData(String data) {
		 String[] l = new String[3];
		// System.out.println(data+"sasa");
	    	l = data.split(",");
	    	this.left = l[0];
	    	this.center = l[1];
	    	this.right = l[2];
	}
	
}

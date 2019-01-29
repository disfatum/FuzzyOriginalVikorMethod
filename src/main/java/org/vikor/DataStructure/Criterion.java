package org.vikor.DataStructure;
 
public class Criterion {
	
	private String Name;
	private String Discription;
	private String MaxMin;
	private String Weigh;
	
	public Criterion(String Name, String Discription, String MaxMin, String Weigh) {
		this.Name = Name;
		this.Discription = Discription;
		this.MaxMin = MaxMin;
		this.Weigh = Weigh;
	}
	
	public String getDiscription() {
		return Discription;
	}
	public void setDiscription(String discription) {
		Discription = discription;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getMaxMin() {
		return MaxMin;
	}

	public void setMaxMin(String maxmin) {
		MaxMin = maxmin;
	}

	public String getWeigh() {
		return Weigh;
	}

	public void setWeigh(String weigh) {
		Weigh = weigh;
	}
}

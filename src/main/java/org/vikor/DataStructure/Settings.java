package org.vikor.DataStructure;

public class Settings {
	
	private String synchronization;
	private double v;
	private int Qvstep;
	private double Qvs;
	private double Srs;
	private int SRstep;
	
	public String getSynchronization() {
		return synchronization;
	}

	public void setSynchronization(String synchronization) {
		this.synchronization = synchronization;
	}
	public Settings(String synchronization,double v,int qv, int sr,double Qvs,double Srs) {
		this.v = v;
		this.Qvstep = qv;
		this.SRstep = sr;
		this.Qvs = Qvs;
		this.Srs = Srs;
		this.synchronization = synchronization;
	}
	public double getV() {
		return v;
	}
	public void setV(double v) {
		this.v = v;
	}
	
	public int getQvstep() {
		return Qvstep;
	}
	public void setQvstep(int qvstep) {
		Qvstep = qvstep;
	}
	public int getSRstep() {
		return SRstep;
	}
	public void setSRstep(int sRstep) {
		SRstep = sRstep;
	}
	public double getQvs() {
		return Qvs;
	}
	public void setQvs(double qvs) {
		Qvs = qvs;
	}
	public double getSrs() {
		return Srs;
	}
	public void setSrs(double srs) {
		Srs = srs;
	}
}

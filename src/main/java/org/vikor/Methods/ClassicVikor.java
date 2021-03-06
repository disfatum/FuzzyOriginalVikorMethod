package org.vikor.Methods;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.List;

import org.vikor.Controllers.VikorController;
import org.vikor.DataStructure.OriginalCriterionDataStructure;
import org.vikor.DataStructure.OriginalPtableData;
import org.vikor.DataStructure.Settings;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ClassicVikor {
	
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
		
		/*
		 * шаг 1; 
		 */
				
				ObservableList<String> MxMn = FXCollections.observableArrayList();
				ObservableList<Double> S = FXCollections.observableArrayList();
				ObservableList<Double> R = FXCollections.observableArrayList();
				
				
				ObservableList<Double> Q = FXCollections.observableArrayList();
				
				for(int i  = 0; i < Fsize; i++) {
					MxMn.add(FTableData.get(i).getMaxmin());
					W.add(Double.valueOf(FTableData.get(i).getWeigh()));
				}System.out.println(MxMn.toString()+" msmn");
				
				//for(int i  = 0; i < Asize; i++) {Collections.sort(Sdata.get(i));}
				
				
				ObservableList<List<Double>> Sdata = FXCollections.observableArrayList();
				System.out.println(PTableData.toString()+"asd");
				
				
				for(int j = 0; j < d.get(0).size();j++) {
					List<Double> bf = FXCollections.observableArrayList();
					for(int i = 0; i < PTableData.size();i++) {
						bf.add(Double.valueOf(d.get(i).get(j)));
					}
					Sdata.add(bf);
				}
				
				System.out.println(Sdata.toString()+" Sdata");
				for(int i  = 0; i < Sdata.size(); i++) {
					double stars = 0, starm = 0;
					for(int j  = 0; j < Fsize; j++) {
						
						if(MxMn.get(j).equals("Максимизация")) {
							List<Double> sdata = FXCollections.observableArrayList();
							sdata = Sdata.get(i);
							stars = Collections.max(sdata);
							starm = Collections.min(sdata);
						}
						if(MxMn.get(j).equals("Минимизация")) {
							List<Double> sdata = FXCollections.observableArrayList();
							sdata = Sdata.get(i);
							starm = Collections.max(sdata);
							stars = Collections.min(sdata);
							
						}
						
					}star.add(stars);
					minus.add(starm);
					
				}
				System.out.println(star.toString()+" star");
				System.out.println(minus.toString()+" minus");
				
		/*
		 * шаг 2;вычисление Si и Ri
		 */
				
				for(int i  = 0; i < PTableData.size(); i++) {
					double e = 0;
					ObservableList<Double> R_buf = FXCollections.observableArrayList();
					for(int j  = 0; j < W.size(); j++) {
						e = e +VikorS(W.get(j),i,j);
						R_buf.add(VikorR(W.get(j), i,j));
					}
					R.add(Collections.max(R_buf));
					e = new BigDecimal(e).setScale(3, RoundingMode.UP).doubleValue();
					S.add(e);
				}
				System.out.println(R.toString()+"-R");
				System.out.println(S.toString()+"-S");
				Double Rminus = Collections.max(R);
				Double Sminus = Collections.max(S);
				Double Rstar = Collections.min(R);
				Double Sstar = Collections.min(S);
			
		/*
		 * шаг 3; вычисление Qi
		 */
				for(int i  = 0; i < PTableData.size(); i++) {
					Double q = (V * (S.get(i) - Sstar) / (Sminus - Sstar))+((1 - V) * (R.get(i) - Rstar)/(Rminus - Rstar));
					 q = new BigDecimal(q).setScale(3, RoundingMode.UP).doubleValue();
					Q.add(q);
				}
				System.out.println(Q.toString()+" Q");
				this.Q = Q;
				this.S = S;
				this.R = R;
				this.Sminus = Collections.max(S);
				this.Sstar = Collections.min(S);
				this.Rminus = Collections.max(R);
				this.Rstar = Collections.min(R);
				
				int o = VikorController.Settings.getQvstep();
				double qvs = VikorController.Settings.getQvs();
				double v = V;
				ObservableList<Double> v1 = FXCollections.observableArrayList();
				for(int i = 0; i < o /2;i++) {
					v1.add(v - (o/2 - i)*(qvs) );
				}
				int j = 0;
				for (int i = o /2; i < o;i++) {
					
					v1.add(v+j*qvs);
					j++;
				}
				this.Qv = v1;
				System.out.println(v1.toString()+"v1");
				
				
			}
			public ObservableList<Double> VikorV(double v) {
				ObservableList<Double> qq = FXCollections.observableArrayList(); 
				for(int i  = 0; i < S.size(); i++) {
					Double q = (v * (S.get(i) - Sstar) / (Sminus - Sstar))+((1 - v) * (R.get(i) - Rstar)/(Rminus - Rstar));
					qq.add(q);
				}
				return qq;
			}
			public ObservableList<String> altname(){
				ObservableList<String> qq = FXCollections.observableArrayList();
				qq = AltNames;
				return qq;
			}
			public Double VikorS(double w,int c, int critnumber){
				ObservableList<Double> wg = FXCollections.observableArrayList();
				wg = W;
				wg.set(critnumber, w);
				System.out.println(wg.size()+" size");	
				System.out.println(wg.toString()+" size");
					double S = 0;
					for(int j  = 0; j < wg.size(); j++) {
						S = S + wg.get(j)*((star.get(j) - Double.valueOf(d.get(c).get(j)))/(star.get(j) - minus.get(j)));	
					}
		        S = new BigDecimal(S).setScale(3, RoundingMode.UP).doubleValue();
				return S;
			}
			public Double VikorR(double newValue,int c, int critnumber){
					
					double R;
					ObservableList<Double> R1 = FXCollections.observableArrayList();
					ObservableList<Double> wg = FXCollections.observableArrayList();
					wg = W;
					wg.set(critnumber, newValue);
					System.out.println(wg.size()+" size");	
					System.out.println(wg.toString()+" size");
					for(int j  = 0; j < wg.size(); j++) {
						R1.add(wg.get(j)*((star.get(j) - Double.valueOf(d.get(c).get(j)))/(star.get(j) - minus.get(j))));
						System.out.println(d.get(c).get(j)+" d.get(c).get(j) from vikor");
					}
					
				R = Collections.max(R1);
				R = new BigDecimal(R).setScale(3, RoundingMode.UP).doubleValue();
				return R;
			}
}

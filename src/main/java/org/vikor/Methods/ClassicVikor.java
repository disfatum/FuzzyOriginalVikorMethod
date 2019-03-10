package org.vikor.Methods;

import java.util.Collections;
import java.util.List;

import org.vikor.DataStructure.OriginalCriterionDataStructure;
import org.vikor.DataStructure.OriginalPtableData;
import org.vikor.DataStructure.Settings;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ClassicVikor {
	
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
			Settings Settings,
			ObservableList<ObservableList<String>> OriginalPTableData) {
		
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
		 * ��� 1; ������ � ������ �������� �������� ��� ��� ��� ����
		 */
				
				ObservableList<String> MxMn = FXCollections.observableArrayList();
				ObservableList<Double> S = FXCollections.observableArrayList();
				ObservableList<Double> R = FXCollections.observableArrayList();
				ObservableList<Double> R_buf;
				
				ObservableList<Double> Q = FXCollections.observableArrayList();
				
				for(int i  = 0; i < Fsize; i++) {
					MxMn.add(FTableData.get(i).getMaxmin());
					W.add(Double.valueOf(FTableData.get(i).getWeigh()));
				}System.out.println(MxMn.toString()+" msmn");
				
				//for(int i  = 0; i < Asize; i++) {Collections.sort(Sdata.get(i));}
				
				double stars = 0, starm = 0;
				ObservableList<List<Double>> Sdata = FXCollections.observableArrayList();
				
				
				
				for(int j = 1; j < OriginalPTableData.get(0).size();j++) {
					List<Double> bf = FXCollections.observableArrayList();
					for(int i = 0; i < PTableData.size();i++) {
						bf.add(Double.valueOf(OriginalPTableData.get(i).get(j)));
				}
					Sdata.add(bf);
			}
				for(int i  = 0; i < PTableData.size(); i++) {
					
					for(int j  = 0; j < Fsize; j++) {
						if(MxMn.get(j) == "MAX") {
							ObservableList<List<Double>> Sdata1 = FXCollections.observableArrayList();
							Sdata1 = Sdata;
							stars = Collections.max(Sdata1.get(i));
							starm = Collections.min(Sdata1.get(i));
						}
						if(MxMn.get(j) == "MIN") {
							ObservableList<List<Double>> Sdata1 = FXCollections.observableArrayList();
							Sdata1 = Sdata;
							starm = Collections.max(Sdata1.get(i));
							stars = Collections.min(Sdata1.get(i));
							
						}
						
					}star.add(stars);
					minus.add(starm);
					
				}
				System.out.println(star.toString()+" star");
				System.out.println(minus.toString()+" minus");
				
		/*
		 * ��� 2;��������� �������� Si � Ri
		 */
				
				for(int i  = 0; i < PTableData.size(); i++) {
					double e = 0;
					R_buf = FXCollections.observableArrayList();
					for(int j  = 0; j < W.size(); j++) {
						e = e +VikorS(W.get(j),i,j);
						R_buf.add(VikorR(W.get(j), i,j));
						System.out.println(e+" S\n");
						System.out.println(R_buf.toString()+" R_buf\n");
					}
					//System.out.println(R_buf.toString()+" R_buf\n");
					//Collections.sort(R_buf);
					R.add(Collections.max(R_buf));
					S.add(e);
				}
				System.out.println(R.toString()+"-R");
				System.out.println(S.toString()+"-S");
				Double Rminus = Collections.max(R);
				Double Sminus = Collections.max(S);
				Double Rstar = Collections.min(R);
				Double Sstar = Collections.min(S);
			
		/*
		 * ��� 3;��������� �������� Qi
		 */
				for(int i  = 0; i < PTableData.size(); i++) {
					Double q = (V * (S.get(i) - Sstar) / (Sminus - Sstar))+((1 - V) * (R.get(i) - Rstar)/(Rminus - Rstar));
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
				
				//int o = Controller.f.getQvstep();
				//double qvs = Controller.f.getQvs();
				//double v = Controller.f.getV();
				/*ObservableList<Double> v1 = FXCollections.observableArrayList();
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
				
				/*/
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
						S = S + wg.get(j)*((0 - Double.valueOf(d.get(c).get(j)))/(0 - 1));	
					}
				
				return S;
			}
			public Double VikorR(double newValue,int c, int critnumber){
					
					double R = 0;
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
				System.out.println(R+" r from vikor");	
				System.out.println(newValue+" newValue from vikor");
				return R;
			}
}

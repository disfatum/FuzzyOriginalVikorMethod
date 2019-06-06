package org.vikor.Methods;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.vikor.Controllers.VikorController;
import org.vikor.DataStructure.OriginalCriterionDataStructure;
import org.vikor.DataStructure.OriginalPtableData;
import org.vikor.DataStructure.TriangularFuzzyNumber;
import org.vikor.FuzzyOperations.FuzzyOp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FuzzyVikorCentroid {
	
	public ObservableList<Double> Qv = FXCollections.observableArrayList();
	public ObservableList<Double> Q = FXCollections.observableArrayList();
	public ObservableList<Double> Q1 = FXCollections.observableArrayList();
	public ObservableList<TriangularFuzzyNumber> S = FXCollections.observableArrayList();
	public ObservableList<TriangularFuzzyNumber> R = FXCollections.observableArrayList();
	public ObservableList<String> AltNames = FXCollections.observableArrayList();
	public ObservableList<String> CritNames = FXCollections.observableArrayList();
	public ObservableList<ObservableList<TriangularFuzzyNumber>> d = FXCollections.observableArrayList();
	public int Fsize;
	public int Critsize;

	public Double Rminus1; 
	public Double Sminus1;
	public Double Rstar1; 
	public Double Sstar1; 
	
	public ObservableList<TriangularFuzzyNumber> star = FXCollections.observableArrayList();
	public ObservableList<List<TriangularFuzzyNumber>> D = FXCollections.observableArrayList();
	public ObservableList<TriangularFuzzyNumber> minus = FXCollections.observableArrayList();
	public ObservableList<TriangularFuzzyNumber> W = FXCollections.observableArrayList();
	
	public void Calculate(

	    ObservableList<OriginalPtableData> PTableData,
		ObservableList<OriginalCriterionDataStructure> FTableData,
		TriangularFuzzyNumber V) {
		FuzzyOp Fop = new FuzzyOp();
		d.clear();
		AltNames.clear();
		CritNames.clear();
		Q.clear();
		S.clear();
		R.clear();
		D.clear();
		star.clear();
		minus.clear();
		W.clear();
		for(int i = 0; i < PTableData.size();i++) {
			AltNames.add(PTableData.get(i).getFromList(0));
		}
		for(int i = 0; i < FTableData.size();i++) {
			CritNames.add(FTableData.get(i).getName());
		}
		
		for(int i = 0; i < PTableData.size();i++) {
			ObservableList<TriangularFuzzyNumber> l = FXCollections.observableArrayList();
			for(int j = 1; j < PTableData.get(i).getList().size();j++) {
				TriangularFuzzyNumber tfn = new TriangularFuzzyNumber(1.0,1.0,1.0);
				tfn.RefreshData(PTableData.get(i).getFromList(j));
				l.add(tfn);
			}
			d.add(l);
		}
		Fsize = FTableData.size();
		Critsize = PTableData.size();
		
		//шаг 1

		ObservableList<String> MxMn = FXCollections.observableArrayList();
		ObservableList<TriangularFuzzyNumber> S = FXCollections.observableArrayList();
		ObservableList<TriangularFuzzyNumber> R = FXCollections.observableArrayList();
		
		
		ObservableList<TriangularFuzzyNumber> Q = FXCollections.observableArrayList();
		
		for(int i  = 0; i < Fsize; i++) {
			MxMn.add(FTableData.get(i).getMaxmin());
			TriangularFuzzyNumber tfn1 = new TriangularFuzzyNumber(1.0,1.0,1.0);
			tfn1.RefreshData(FTableData.get(i).getWeigh());
			W.add(tfn1);
		}System.out.println(MxMn.toString()+" msmn");
		
		//for(int i  = 0; i < Asize; i++) {Collections.sort(Sdata.get(i));}
		
		
		ObservableList<List<TriangularFuzzyNumber>> Sdata = FXCollections.observableArrayList();
		System.out.println(PTableData.toString()+"asd");
		
		
		for(int j = 0; j < d.get(0).size();j++) {
			List<TriangularFuzzyNumber> bf = FXCollections.observableArrayList();
			for(int i = 0; i < PTableData.size();i++) {
				bf.add((d.get(i).get(j)));
			}
			Sdata.add(bf);
		}
		
		System.out.println(Sdata.toString()+" Sdata");
		for(int i  = 0; i < Sdata.size(); i++) {
			TriangularFuzzyNumber stars = new TriangularFuzzyNumber(1.0,1.0,1.0);
			TriangularFuzzyNumber starm = new TriangularFuzzyNumber(1.0,1.0,1.0);
			for(int j  = 0; j < Fsize; j++) {
				
				if(MxMn.get(j).equals("ћаксимизаци€")) {
					List<TriangularFuzzyNumber> sdata = FXCollections.observableArrayList();
					sdata = Sdata.get(i);
					stars = sdata.get(Fop.MAX_Centroid(sdata));
					starm = sdata.get(Fop.Min_Centroid(sdata));
				}
				if(MxMn.get(j).equals("ћинимизаци€")) {
					List<TriangularFuzzyNumber> sdata = FXCollections.observableArrayList();
					sdata = Sdata.get(i);
					starm = sdata.get(Fop.MAX_Centroid(sdata));
					stars = sdata.get(Fop.Min_Centroid(sdata));
					
				}
				
			}
			System.out.println(stars.DataforTable()+" star");
			System.out.println(starm.DataforTable()+" minus");
			star.add(stars);
			minus.add(starm);
			
		}
		
/*
 * шаг вычисление D
 */
	for(int i = 0; i < PTableData.size();i++) {
		List<TriangularFuzzyNumber> sdata = FXCollections.observableArrayList();
		for(int j = 0; j < star.size();j++) {
			sdata.add(Fop.Div(Fop.Minus(star.get(j), d.get(i).get(j)),
					Fop.Minus(star.get(j),minus.get(j))));
		}
		D.add(sdata);
	}	
	for(int i = 0; i < PTableData.size();i++) {
		for(int j = 0; j < star.size();j++) {
		System.out.println(D.get(i).get(j).DataforTable()+" D");
		}
	}
/*
 * шаг 2;вычисление Si и Ri
 */		
		for(int i  = 0; i < PTableData.size(); i++) {
			TriangularFuzzyNumber e = new TriangularFuzzyNumber(1.0,1.0,1.0);
			ObservableList<TriangularFuzzyNumber> R_buf = FXCollections.observableArrayList();
			for(int j  = 0; j < W.size(); j++) {
				e = Fop.Summ(Fop.Multyply(W.get(j),D.get(i).get(j)),e);
				R_buf.add((Fop.Multyply(W.get(j),D.get(i).get(j))));
			}
			R.add(R_buf.get(Fop.MAX_Centroid(R_buf)));
			S.add(e);
		}
		for(int i  = 0; i < PTableData.size(); i++) {
		System.out.print(R.get(i).DataforTable()+"-R");
		System.out.print(S.get(i).DataforTable()+"-S");
		}
		TriangularFuzzyNumber Rminus = R.get(Fop.MAX_Centroid(R));
		Rminus1 = Rminus.DefazzyCentriod();
		TriangularFuzzyNumber Sminus = S.get(Fop.MAX_Centroid(S));
		Sminus1 = Sminus.DefazzyCentriod();
		TriangularFuzzyNumber Rstar = R.get(Fop.Min_Centroid(R));
		Rstar1 = Rstar.DefazzyCentriod();
		TriangularFuzzyNumber Sstar = S.get(Fop.Min_Centroid(S));
	    Sstar1 = Sstar.DefazzyCentriod();
/*
 * шаг 3; вычисление Qi
 */		
		TriangularFuzzyNumber tfn3 = new TriangularFuzzyNumber(1.0,1.0,1.0);
		for(int i  = 0; i < PTableData.size(); i++) {
			TriangularFuzzyNumber tf1 = Fop.Multyply(V,Fop.Div(Fop.Minus(S.get(i), Sstar),Fop.Minus(Sminus, Sstar)));
			TriangularFuzzyNumber tf2 = Fop.Multyply(Fop.Minus(tfn3, V),Fop.Div(Fop.Minus(R.get(i), Rstar),Fop.Minus(Rminus, Rstar)));
			TriangularFuzzyNumber q = Fop.Summ(tf1, tf2);
			Q.add(q);
		}
		ObservableList<Double> qq = FXCollections.observableArrayList(); 
		for(int i  = 0; i < PTableData.size(); i++) {
			Double q = (V.getCenter() * (S.get(i).DefazzyCentriod() - Sstar.DefazzyCentriod()) / (Sminus.DefazzyCentriod() - Sstar.DefazzyCentriod()))+((1 - V.DefazzyCentriod()) * 
					(R.get(i).DefazzyCentriod() - Rstar.DefazzyCentriod())/(Rminus.DefazzyCentriod() - Rstar.DefazzyCentriod()));
			q = new BigDecimal(q).setScale(3, RoundingMode.UP).doubleValue();
			qq.add(q);
		}
		
		this.Q = qq;
		this.S = S;
		this.R = R;
		
		
		for(int i  = 0; i < Q.size(); i++) {
			System.out.println(Q.get(i).DataforTable()+"q");	
			
			System.out.print(qq.get(i) +"q normal ||");
		}
		this.Q1 = qq;
		System.out.print("\n");
		int o = VikorController.Settings.getQvstep();
		double qvs = VikorController.Settings.getQvs();
		double v = V.DefazzyCentriod();
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
	}		
	public TriangularFuzzyNumber VikorS(TriangularFuzzyNumber w,int c, int critnumber){
		ObservableList<TriangularFuzzyNumber> wg = FXCollections.observableArrayList();
		wg = W;
		wg.set(critnumber, w);
		System.out.println(wg.size()+" size");	
		System.out.println(wg.toString()+" size");
			TriangularFuzzyNumber S = new TriangularFuzzyNumber(0.0,0.0,0.0);
			for(int j  = 0; j < wg.size(); j++) {
					FuzzyOp Fop = new FuzzyOp();
					S = Fop.Summ(Fop.Multyply(W.get(j),D.get(c).get(j)),S);
			}
		
		return S;
	}
	public TriangularFuzzyNumber VikorR(TriangularFuzzyNumber w,int c, int critnumber){
			
		TriangularFuzzyNumber R = new TriangularFuzzyNumber(0.0,0.0,0.0);
			ObservableList<TriangularFuzzyNumber> R1 = FXCollections.observableArrayList();
			ObservableList<TriangularFuzzyNumber> wg = FXCollections.observableArrayList();
			wg = W;
			wg.set(critnumber, w);
			System.out.println(wg.size()+" size");	
			System.out.println(wg.toString()+" size");
			FuzzyOp Fop = new FuzzyOp();
			for(int j  = 0; j < wg.size(); j++) {
				R1.add((Fop .Multyply(W.get(j),D.get(c).get(j))));
			}
			R = (R1.get(Fop.MAX_Centroid(R1)));
		return R;
	}
	public ObservableList<Double> VikorV(double v) {
		ObservableList<Double> qq = FXCollections.observableArrayList(); 
		for(int i  = 0; i < S.size(); i++) {
			Double q = (v * (S.get(i).DefazzyCentriod() - Sstar1) / (Sminus1 - Sstar1))+((1 - v) * (R.get(i).DefazzyCentriod() - Rstar1)/(Rminus1 - Rstar1));
			q = new BigDecimal(q).setScale(3, RoundingMode.UP).doubleValue();
			qq.add(q);
		}
		
		return qq;
	}
	public ObservableList<String> altname(){
		ObservableList<String> qq = FXCollections.observableArrayList();
		qq = AltNames;
		return qq;
	}
}


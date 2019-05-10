package org.vikor.Methods;

import java.util.List;

import org.vikor.DataStructure.OriginalCriterionDataStructure;
import org.vikor.DataStructure.OriginalPtableData;
import org.vikor.DataStructure.Settings;
import org.vikor.DataStructure.TriangularFuzzyNumber;
import org.vikor.FuzzyOperations.FuzzyOp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FuzzyVikorMax {
	
	public ObservableList<Double> Qv = FXCollections.observableArrayList();
	public ObservableList<TriangularFuzzyNumber> Q = FXCollections.observableArrayList();
	public ObservableList<TriangularFuzzyNumber> S = FXCollections.observableArrayList();
	public ObservableList<TriangularFuzzyNumber> R = FXCollections.observableArrayList();
	public ObservableList<String> AltNames = FXCollections.observableArrayList();
	public ObservableList<String> CritNames = FXCollections.observableArrayList();
	public ObservableList<ObservableList<TriangularFuzzyNumber>> d = FXCollections.observableArrayList();
	public int Fsize;
	public int Critsize;

	public Double Rminus; 
	public Double Sminus;
	public Double Rstar; 
	public Double Sstar; 
	
	public ObservableList<TriangularFuzzyNumber> star = FXCollections.observableArrayList();
	public ObservableList<List<TriangularFuzzyNumber>> D = FXCollections.observableArrayList();
	public ObservableList<TriangularFuzzyNumber> minus = FXCollections.observableArrayList();
	public ObservableList<TriangularFuzzyNumber> W = FXCollections.observableArrayList();
	
	public void Calculate(

	    ObservableList<OriginalPtableData> PTableData,
		ObservableList<OriginalCriterionDataStructure> FTableData,
		TriangularFuzzyNumber V,
		Settings Settings) {
		FuzzyOp Fop = new FuzzyOp();
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
					stars = sdata.get(Fop.MAX_LargestMax(sdata));
					starm = sdata.get(Fop.Min_LargestMax(sdata));
				}
				if(MxMn.get(j).equals("ћинимизаци€")) {
					List<TriangularFuzzyNumber> sdata = FXCollections.observableArrayList();
					sdata = Sdata.get(i);
					starm = sdata.get(Fop.MAX_LargestMax(sdata));
					stars = sdata.get(Fop.Min_LargestMax(sdata));
					
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
			R.add(R_buf.get(Fop.MAX_LargestMax(R_buf)));
			S.add(e);
		}
		for(int i  = 0; i < PTableData.size(); i++) {
		System.out.print(R.get(i).DataforTable()+"-R");
		System.out.print(S.get(i).DataforTable()+"-S");
		}
		TriangularFuzzyNumber Rminus = R.get(Fop.MAX_LargestMax(R));
		TriangularFuzzyNumber Sminus = S.get(Fop.MAX_LargestMax(S));
		TriangularFuzzyNumber Rstar = R.get(Fop.Min_LargestMax(R));
		TriangularFuzzyNumber Sstar = S.get(Fop.Min_LargestMax(S));
	
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
		
		this.Q = Q;
		this.S = S;
		this.R = R;
		for(int i  = 0; i < Q.size(); i++) {
			System.out.println(Q.get(i).DataforTable()+"q");	
			
			System.out.println(Q.get(i).DefuzzyLargeMax()+"dq");
		}
	}		
}


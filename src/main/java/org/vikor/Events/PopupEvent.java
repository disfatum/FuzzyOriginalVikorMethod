package org.vikor.Events;

import java.io.IOException;

import org.vikor.Controllers.VikorController;
import org.vikor.DataStructure.OriginalCriterionDataStructure;
import org.vikor.DataStructure.OriginalPtableData;
import org.vikor.DataStructure.TriangularFuzzyNumber;
import org.vikor.Views.Ftableprop;
import org.vikor.Views.Ptableprop;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PopupEvent {

	public void Faddnames(JFXPopup Fnamepop,
			TableView<OriginalPtableData> Ptable,
			PtableEvent p,
			ObservableList<OriginalPtableData> PTableData) {
		
    	Label l = new Label("Введите название критерия:");
    	l.alignmentProperty().set(Pos.CENTER);
    	JFXTextField b1 = new JFXTextField();
    	JFXButton b2 = new JFXButton("Добавить критерий");
    	b1.alignmentProperty().set(Pos.CENTER);
    	l.setPrefSize(350, 50);
    	b1.setPrefSize(350, 50);
    	b2.setPrefSize(350, 50);
    	
    	b2.setOnAction(e->{
			if(VikorController.Settings.getSynchronization().equals("Да")) {
    			OriginalCriterionDataStructure l1 = new OriginalCriterionDataStructure(b1.getText(),"","1","");
        		OriginalCriterionDataStructure l2 = new OriginalCriterionDataStructure(b1.getText(),"","0.9,1,1.1","");
        		VikorController.FTableData.add(l1);
        		VikorController.FuzzyFTableData.add(l2);
        		VikorController.OriginalFTableData.add(l1);
			}
			if(VikorController.Settings.getSynchronization().equals("Нет")) {
				if(VikorController.f == false) {
					OriginalCriterionDataStructure l1 = new OriginalCriterionDataStructure(b1.getText(),"","1","");
					VikorController.OriginalFTableData.add(l1);
					VikorController.FTableData.add(l1);
				}
				if(VikorController.f == true) {
					OriginalCriterionDataStructure l2 = new OriginalCriterionDataStructure(b1.getText(),"","0.9,1,1.1","");
					VikorController.FuzzyFTableData.add(l2);
					VikorController.FTableData.add(l2);
				}
			}
    		p.AddCol(b1.getText(), PTableData, Ptable);
    		
    	});
    	VBox vb = new VBox(l,b1,b2);
    	vb.alignmentProperty().set(Pos.CENTER);
    	Fnamepop.setPopupContent(vb);
	
	}
	
	public void FNotSelected(
			JFXPopup Ftablepopfirst,
			JFXPopup Fnamepop) {
    	JFXButton b1 = new JFXButton("Добавить критерий");
    	b1.setOnMouseClicked( e->{
    		if(e.getClickCount() == 1 && e.getButton().equals(MouseButton.PRIMARY)) {
    			showPop(e, Fnamepop);
    		}
    	});
    	b1.setPrefSize(350, 50);
    	VBox vb = new VBox(b1);
    	Ftablepopfirst.setPopupContent(vb);
    }
	
	public void FSelectedRow(
			JFXPopup Ftablepop,
			JFXPopup Fnamepop,
			TableView<OriginalCriterionDataStructure> Ftable,
			ObservableList<OriginalCriterionDataStructure> FTableData,
			TableView<OriginalPtableData> Ptable,
			ObservableList<OriginalPtableData> PTableData) {
		
		JFXButton b1 = new JFXButton("Добавить критерий");
    	Separator s1 = new Separator();
    	JFXButton b2 = new JFXButton("Удалить критерий");
    	JFXButton b3 = new JFXButton("Свойства");
    	b1.setOnMouseClicked( e->{
    		if(e.getClickCount() == 1 && e.getButton().equals(MouseButton.PRIMARY)) {
    			showPop(e, Fnamepop);
    		}
    	});
    	b2.setOnAction(e->{
    		
    			int ind = Ftable.getSelectionModel().getSelectedIndex();
    			Ptable.getColumns().remove(ind+1);
    			
    			if(VikorController.Settings.getSynchronization().equals("Да")) {
            		VikorController.FTableData.remove(ind);
            		VikorController.FuzzyFTableData.remove(ind);
            		VikorController.OriginalFTableData.remove(ind);
    			}
    			if(VikorController.Settings.getSynchronization().equals("Нет")) {
    				if(VikorController.f == false) {
    					VikorController.OriginalFTableData.remove(ind);
    					VikorController.FTableData.remove(ind);
    				}
    				if(VikorController.f == true) {
    					VikorController.FuzzyFTableData.remove(ind);
    					VikorController.FTableData.remove(ind);
    				}
    			}
    			//Ptable.refresh();
    			
    	});
    	b3.setOnMouseClicked( e->{
    		if(e.getClickCount() == 1 && e.getButton().equals(MouseButton.PRIMARY)) {
    			Ftableprop f = new Ftableprop();
    			Stage primaryStage = new Stage();
    			try {
    					f.start(primaryStage);
    				} catch (IOException e1) {
    					e1.printStackTrace();
    				}
    		}
    	});
    	s1.setPrefSize(350, 2);
    	b1.setPrefSize(350, 50);
    	b2.setPrefSize(350, 50);
    	b3.setPrefSize(350, 50);
    	VBox vb = new VBox(b1,s1,b2,b3);
    	Ftablepop.setPopupContent(vb);
    }
	
	public void PNotSelected(
			JFXPopup Paddnamespop,
			JFXPopup PfnamesPopup,
			JFXPopup PanamesPopup) {
		
	JFXButton b1 = new JFXButton("Добавить критерий");
	JFXButton b2 = new JFXButton("Добавить альтернативу");
	Separator sep = new Separator();
	
	b1.setOnMouseClicked( e->{
		if(e.getClickCount() == 1 && e.getButton().equals(MouseButton.PRIMARY)) {
			showPop(e, PfnamesPopup);
		}
	});
	
	b2.setOnMouseClicked(e->{
		if(e.getClickCount() == 1 && e.getButton().equals(MouseButton.PRIMARY)) {
			showPop(e, PanamesPopup);
		}
	});
	
	b1.setPrefSize(350, 50);
	b2.setPrefSize(350, 50);
	sep.setPrefSize(350, 2);
	VBox vb = new VBox(b1,sep,b2);
	Paddnamespop.setPopupContent(vb);
	
	}
	
	public void PFaddNames(
			JFXPopup Pnamepop,
			PtableEvent PtableEvents,
			PopupEvent pope,
			TableView<OriginalPtableData> Ptable,
			ObservableList<OriginalPtableData> PTableData) {
		Label l = new Label("Введите название критерия:");
    	l.alignmentProperty().set(Pos.CENTER);
    	JFXTextField b1 = new JFXTextField();
    	JFXButton b2 = new JFXButton("Добавить ");
    	b1.alignmentProperty().set(Pos.CENTER);
    	l.setPrefSize(350, 50);
    	b1.setPrefSize(350, 50);
    	b2.setPrefSize(350, 50);
    	
    	b2.setOnMouseClicked(e->{
    		if(e.getClickCount() == 1 && e.getButton().equals(MouseButton.PRIMARY)) {
    			PtableEvents.AddCol(b1.getText(), PTableData, Ptable);
    			VikorController.Colnames.add(b1.getText());
    			if(VikorController.Settings.getSynchronization().equals("Да")) {
	    			OriginalCriterionDataStructure l1 = new OriginalCriterionDataStructure(b1.getText(),"","1","");
	        		OriginalCriterionDataStructure l2 = new OriginalCriterionDataStructure(b1.getText(),"","0.9,1,1.1","");
	        		VikorController.FTableData.add(l1);
	        		VikorController.FuzzyFTableData.add(l2);
	        		VikorController.OriginalFTableData.add(l1);
    			}
    			if(VikorController.Settings.getSynchronization().equals("Нет")) {
    				if(VikorController.f == false) {
    					OriginalCriterionDataStructure l1 = new OriginalCriterionDataStructure(b1.getText(),"","1","");
    					VikorController.OriginalFTableData.add(l1);
    					VikorController.FTableData.add(l1);
    				}
    				if(VikorController.f == true) {
    					OriginalCriterionDataStructure l2 = new OriginalCriterionDataStructure(b1.getText(),"","0.9,1,1.1","");
    					VikorController.FuzzyFTableData.add(l2);
    					VikorController.FTableData.add(l2);
    				}
    			}
    		}
    	});
    	VBox vb = new VBox(l,b1,b2);
    	vb.alignmentProperty().set(Pos.CENTER);
    	Pnamepop.setPopupContent(vb);
	}
	
	public void PAaddNames(
			JFXPopup Pnamepop, 
			ObservableList<OriginalPtableData> pTableData, 
			TableView<OriginalPtableData> ptable
			) {
		
		Label l = new Label("Введите название альтернативы:");
    	l.alignmentProperty().set(Pos.CENTER);
    	JFXTextField b1 = new JFXTextField();
    	JFXButton b2 = new JFXButton("Добавить ");
    	b1.alignmentProperty().set(Pos.CENTER);
    	l.setPrefSize(350, 50);
    	b1.setPrefSize(350, 50);
    	b2.setPrefSize(350, 50);
    	
    	b2.setOnMouseClicked(e->{
    		if(e.getClickCount() == 1 && e.getButton().equals(MouseButton.PRIMARY)) {
    			
    			ObservableList<String > list =  FXCollections.observableArrayList();
    			ObservableList<String > originallist =  FXCollections.observableArrayList();
    			ObservableList<TriangularFuzzyNumber > fuzzylist =  FXCollections.observableArrayList();
    			
    			list.add(b1.getText());
    			TriangularFuzzyNumber tfn = new TriangularFuzzyNumber(0.9,1.0,1.1);
	    			for(int i = 1; i < ptable.getColumns().size();i++) {
	    				
	    					String val =  "1";
		    				list.add(val);
		    				originallist.add(val);
		    				fuzzylist.add(tfn);
	    			}
	    			System.out.println(VikorController.f );
    			if(VikorController.Settings.getSynchronization().equals("Да")) {
    				if(VikorController.f == false) {
    					OriginalPtableData pd = new OriginalPtableData(list);
    					pTableData.add(pd);
    					VikorController.OriginalPTableData.add(originallist);
    					VikorController.FuzzyPTableData.add(fuzzylist);	
    				}
    				if(VikorController.f == true) {
    					ObservableList<String > flist =  FXCollections.observableArrayList();
    					flist.add(list.get(0));
    					for(int i = 0; i < fuzzylist.size();i++) {
    						flist.add(fuzzylist.get(i).DataforTable());
    					}
    					OriginalPtableData pd = new OriginalPtableData(flist);
    					pTableData.add(pd);
    					VikorController.OriginalPTableData.add(originallist);
    					VikorController.FuzzyPTableData.add(fuzzylist);	
    				}
    		
    			}
    			if(VikorController.Settings.getSynchronization().equals("Нет")) {
    				if(VikorController.f == false) {
    					OriginalPtableData pd = new OriginalPtableData(list);
    					pTableData.add(pd);
    					VikorController.OriginalPTableData.add(list);
    				}
    				if(VikorController.f == true) {
    					ObservableList<TriangularFuzzyNumber> flist =  FXCollections.observableArrayList();
    					ObservableList<String > list1 =  FXCollections.observableArrayList();
    					TriangularFuzzyNumber tfn1 = new TriangularFuzzyNumber(list.get(0));
    					flist.add(tfn1);
    					list1.add(list.get(0));
    					for(int i = 0; i < fuzzylist.size();i++) {
    						flist.add(fuzzylist.get(i));
    						list1.add(fuzzylist.get(i).DataforTable());
    					}
    					OriginalPtableData pd = new OriginalPtableData(list1);
    					VikorController.FuzzyPTableData.add(flist);
    					pTableData.add(pd);
    				}
    				
    			}
    			ptable.setItems(pTableData);
    			ptable.refresh();
    		}
    	//System.out.println(pTableData.get(c).toString()+" ptd");c++;
    	//ptable.setItems(null);
    	
    	});
    	VBox vb = new VBox(l,b1,b2);
    	vb.alignmentProperty().set(Pos.CENTER);
    	Pnamepop.setPopupContent(vb);
    	
	}
	
	public void PSelected(
			JFXPopup PSelectedpop,
			JFXPopup PfnamesPopup,
			JFXPopup PanamesPopup,
			TableView<OriginalPtableData> Ptable,
			ObservableList<OriginalPtableData> PTableData) {
		
		JFXButton addcrit = new JFXButton("Добавить критерий");
		JFXButton addalt = new JFXButton("Добавить альтернативу");
		JFXButton deletealt = new JFXButton("Удалить альтернативу");
		JFXButton props = new JFXButton("Свойства");
		Separator sep = new Separator();
		
		addcrit.setOnMouseClicked(e->{
			if(e.getClickCount() == 1 && e.getButton().equals(MouseButton.PRIMARY)) {
				showPop(e,PfnamesPopup);
			}
		});
		
		addalt.setOnMouseClicked(e->{
			if(e.getClickCount() == 1 && e.getButton().equals(MouseButton.PRIMARY)) {
				showPop(e,PanamesPopup);
			}
		});
		
		deletealt.setOnMouseClicked(e->{
			if(e.getClickCount() == 1 && e.getButton().equals(MouseButton.PRIMARY)) {
				int ind = Ptable.getSelectionModel().getSelectedIndex();
				if(VikorController.Settings.getSynchronization().equals("Да")) {
            		VikorController.FuzzyFTableData.remove(ind);
            		VikorController.OriginalFTableData.remove(ind);
    			}
    			if(VikorController.Settings.getSynchronization().equals("Нет")) {
    				if(VikorController.f == false) {
    					VikorController.OriginalPTableData.remove(ind);
    					VikorController.PTableData.remove(ind);
    				}
    				if(VikorController.f == true) {
    					VikorController.FuzzyPTableData.remove(ind);
    					VikorController.PTableData.remove(ind);
    				}
    			}
			}
			Ptable.refresh();
		});
		
		props.setOnMouseClicked(e->{
			if(e.getClickCount() == 1 && e.getButton().equals(MouseButton.PRIMARY)) {
    			Ptableprop f = new Ptableprop();
    			Stage primaryStage = new Stage();
    			try {
    				VikorController.pindexPropCol = Ptable.getSelectionModel().getSelectedIndex();
    				VikorController.Colnames.clear();
    				for(int i = 0; i < Ptable.getColumns().size();i++) {
    	   				VikorController.Colnames.add(Ptable.getColumns().get(i).getText());
    	   			}
    					f.start(primaryStage);
    				} catch (IOException e1) {
    					e1.printStackTrace();
    				}
    		}
		});
		
		addcrit.setPrefSize(350, 50);
		addalt.setPrefSize(350, 50);
		deletealt.setPrefSize(350, 50);
		sep.setPrefSize(350, 5);
		props.setPrefSize(350, 50);
		
		VBox vb = new VBox(addcrit,addalt,sep,deletealt,props);
		
		PSelectedpop.setPopupContent(vb);
	}
	
	public void showPop (MouseEvent e , JFXPopup Fnamepop) {
		
    	Node node = (Node) e.getSource();
    	Fnamepop.show(node, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.LEFT, e.getX(), e.getY());
    	
    }
	
}
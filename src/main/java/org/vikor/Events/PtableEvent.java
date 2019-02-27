package org.vikor.Events;

import org.vikor.Controllers.VikorController;
import org.vikor.DataStructure.OriginalPtableData;

import com.jfoenix.controls.JFXPopup;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.input.MouseButton;
import javafx.util.Callback;

public class PtableEvent {
	
	public void a1ddFirstCol(
			ObservableList<OriginalPtableData> PTableData,
			TableView<OriginalPtableData> Ptable) {
		
		TableColumn<OriginalPtableData,String> col = new TableColumn<OriginalPtableData,String>("������������/��������");
		
		//col.setCellValueFactory(new PropertyValueFactory<>(NAME));
		//col.setText(NAME);
		col.sortableProperty().set(false);
		col.setOnEditCommit((CellEditEvent<OriginalPtableData, String> event2) -> {
             
       		 TablePosition<OriginalPtableData, String> pos = event2.getTablePosition();
       		String newitem = event2.getNewValue();
             int row0 = pos.getRow();
             int col0  = pos.getColumn();
             PTableData.get(row0).setinlist(col0, newitem);
           
            });
		for(int i = 0; i < Ptable.getColumns().size();i++) {
			final int q = i;
			col.setCellValueFactory(new Callback<CellDataFeatures<OriginalPtableData, String>, ObservableValue<String>>() {
		     public ObservableValue<String> call(CellDataFeatures<OriginalPtableData, String> p) {
				
		    	 return new ReadOnlyObjectWrapper<String>(p.getValue().getFromList(q));
		     }
		  });	
		}
		
		Ptable.getColumns().add(col);
	}

	public void AddCol(
			String NAME, // �������� ��������
			ObservableList<OriginalPtableData> PTableData,
			TableView<OriginalPtableData> Ptable
    		) {
		for(int i = 0; i < PTableData.size();i++) {
			PTableData.get(i).add("1");
		}
		
		TableColumn<OriginalPtableData,String> col = new TableColumn<OriginalPtableData,String>(NAME);
		
		//col.setCellValueFactory(new PropertyValueFactory<>(NAME));
		//col.setText(NAME);
		col.sortableProperty().set(false);
		col.setOnEditCommit((CellEditEvent<OriginalPtableData, String> event2) -> {
             
       		 TablePosition<OriginalPtableData, String> pos = event2.getTablePosition();
       		 String newitem = event2.getNewValue();
             int row0 = pos.getRow();
             int col0  = pos.getColumn();
             PTableData.get(row0).setinlist(col0, newitem);
           
            });
		for(int i = 0; i < Ptable.getColumns().size()+1;i++) {
			final int q = i;
			col.setCellValueFactory(new Callback<CellDataFeatures<OriginalPtableData, String>, ObservableValue<String>>() {
		     public ObservableValue<String> call(CellDataFeatures<OriginalPtableData, String> p) {
		    	 
		    	 return new ReadOnlyObjectWrapper<String>(p.getValue().getFromList(q));
		    	 
		     }
		  });	
		}
		
		Ptable.getColumns().add(col);
		Ptable.setItems(null);
		Ptable.setItems(PTableData);
		//Ptable.refresh();
	}
	
	public void addEvents(
			TableView<OriginalPtableData> Ptable,
    		ObservableList<OriginalPtableData> PTableData,
    		PopupEvent PopupEvents,
    		JFXPopup PfirstPopup,
    		JFXPopup PSelectedPopup) {
		
		 
	     Ptable.setOnMouseClicked(e->{
	    	 if(e.getClickCount() == 1 && e.getButton().equals(MouseButton.SECONDARY)
	        			&& Ptable.getSelectionModel().getSelectedCells().isEmpty() == false) {
	        		VikorController.pindexPropCol = Ptable.getSelectionModel().getSelectedIndex();
	        		PopupEvents.showPop(e, PSelectedPopup);
	        	}
	         if(e.getClickCount() == 1 && e.getButton().equals(MouseButton.SECONDARY)
	        			&& Ptable.getSelectionModel().getSelectedCells().isEmpty() == true) {
	        		PopupEvents.showPop(e, PfirstPopup);
	        	}
	     });
	}
}
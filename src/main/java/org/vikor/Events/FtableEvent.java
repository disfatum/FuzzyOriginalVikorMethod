package org.vikor.Events;

import org.vikor.Controllers.VikorController;
import org.vikor.DataStructure.OriginalCriterionDataStructure;
import com.jfoenix.controls.JFXPopup;

import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;

public class FtableEvent {
	
	public void AddCols(TableView<OriginalCriterionDataStructure> Ftable,
			ObservableList<OriginalCriterionDataStructure> FTableData,
    		PopupEvent PopupEvents,
    		JFXPopup Ftablepop,
    		JFXPopup  Ftablepopfirst
    		) {
		
		TableColumn<OriginalCriterionDataStructure,String> name = new TableColumn<>("Имя");
        TableColumn<OriginalCriterionDataStructure,String> discription = new TableColumn<>("Описание");
        TableColumn<OriginalCriterionDataStructure,String> weigh = new TableColumn<>("Вес");
        TableColumn<OriginalCriterionDataStructure,String> maxmin = new TableColumn<>("Макс/Мин");
        
        Add(name, "name", "Имя", Ftable, FTableData, PopupEvents, Ftablepop, Ftablepopfirst);
        Add(discription, "discription", "Описание", Ftable, FTableData, PopupEvents, Ftablepop, Ftablepopfirst);
        Add(weigh, "weigh", "Вес", Ftable, FTableData, PopupEvents, Ftablepop, Ftablepopfirst);
        Add(maxmin, "maxmin", "Макс/Мин", Ftable, FTableData, PopupEvents, Ftablepop, Ftablepopfirst);
        
	}
	protected void Add(TableColumn<OriginalCriterionDataStructure,String> Colname,
	    		String name, 
	    		String Col,
	    		TableView<OriginalCriterionDataStructure> Ftable,
	    		ObservableList<OriginalCriterionDataStructure> FTableData, 
	    		PopupEvent PopupEvents,
	    		JFXPopup Ftablepop,
	    		JFXPopup  Ftablepopfirst) {
		
	    	Colname.setCellValueFactory(new PropertyValueFactory<>(name));
	    	Colname.sortableProperty().set(false);
	    	Colname.setOnEditCommit((CellEditEvent<OriginalCriterionDataStructure, String> event2) -> {
	             
	       		 TablePosition<OriginalCriterionDataStructure, String> pos = event2.getTablePosition();
	                String newitem = event2.getNewValue();
	                int row0 = pos.getRow();
	                if(Col.equals("Название")) {
	                	FTableData.get(row0).setName(newitem); 
	                }
	                if(Col.equals("Описание")) {
	                	FTableData.get(row0).setDiscription(newitem);
	                }
	                if(Col.equals("Вес")) {
	                	FTableData.get(row0).setWeigh(newitem);
	                }
	                if(Col.equals("Макс/Мин")) {
	                	FTableData.get(row0).setMaxmin(newitem);
	                }
	                
	            });
	    	
	    	Ftable.getColumns().add(Colname);
	    	Ftable.setOnMouseClicked( e->{
	    		System.out.println(Ftable.getSelectionModel().getSelectedCells().isEmpty());
	        	if(e.getClickCount() == 1 && e.getButton().equals(MouseButton.SECONDARY)
	        			&& Ftable.getSelectionModel().getSelectedCells().isEmpty() == false) {
	        		VikorController.indexPropCol = Ftable.getSelectionModel().getSelectedIndex();
	        		PopupEvents.showPop(e, Ftablepop);
	        	}
	        	if(e.getClickCount() == 1 && e.getButton().equals(MouseButton.SECONDARY)
	        			&& Ftable.getSelectionModel().getSelectedCells().isEmpty() == true) {
	        		PopupEvents.showPop(e, Ftablepopfirst);
	        	}
	        
	        });	
	}
	
}

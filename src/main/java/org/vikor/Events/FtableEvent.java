package org.vikor.Events;

import org.vikor.Controllers.VikorController;
import org.vikor.DataStructure.OriginalCriterionDataStructure;
import org.vikor.DataStructure.OriginalPtableData;
import org.vikor.DataStructure.TriangularFuzzyNumber;

import com.jfoenix.controls.JFXPopup;

import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;

public class FtableEvent {
	
	public void AddCols(TableView<OriginalCriterionDataStructure> Ftable,
			ObservableList<OriginalCriterionDataStructure> FTableData,
    		PopupEvent PopupEvents,
    		JFXPopup Ftablepop,
    		JFXPopup  Ftablepopfirst,
    		ComboBox<String> classicFuzzyBox
    		) {
		
		TableColumn<OriginalCriterionDataStructure,String> name = new TableColumn<>("Имя");
        TableColumn<OriginalCriterionDataStructure,String> discription = new TableColumn<>("Описание");
        TableColumn<OriginalCriterionDataStructure,String> weigh = new TableColumn<>("Вес");
        TableColumn<OriginalCriterionDataStructure,String> maxmin = new TableColumn<>("Макс/Мин");
        
        Add(name, "name", "Имя", Ftable, FTableData, PopupEvents, Ftablepop, Ftablepopfirst,classicFuzzyBox);
        Add(discription, "discription", "Описание", Ftable, FTableData, PopupEvents, Ftablepop, Ftablepopfirst,classicFuzzyBox);
        Add(weigh, "weigh", "Вес", Ftable, FTableData, PopupEvents, Ftablepop, Ftablepopfirst,classicFuzzyBox);
        Add(maxmin, "maxmin", "Макс/Мин", Ftable, FTableData, PopupEvents, Ftablepop, Ftablepopfirst,classicFuzzyBox);
        
	}
	protected void Add(TableColumn<OriginalCriterionDataStructure,String> Colname,
	    		String name, 
	    		String Col,
	    		TableView<OriginalCriterionDataStructure> Ftable,
	    		ObservableList<OriginalCriterionDataStructure> FTableData, 
	    		PopupEvent PopupEvents,
	    		JFXPopup Ftablepop,
	    		JFXPopup  Ftablepopfirst, ComboBox<String> classicFuzzyBox) {
		
	    	Colname.setCellValueFactory(new PropertyValueFactory<>(name));
	    	Colname.sortableProperty().set(false);
	    	if(Col.equals("Вес")) {
	    		Colname.setCellFactory(column -> {
	    		    return new TableCell<OriginalCriterionDataStructure, String>() {
	    		        @Override
	    		        protected void updateItem(String item, boolean empty) {
	    		            super.updateItem(item, empty);

	    		            if (item == null || empty) {
	    		               // setText(null);
	    		               /// setStyle("");
	    		            } else {
	    		                // Style all dates in March with a different color.
	    		            	 try {
		    		                	if(classicFuzzyBox.getValue().equals("Classic VIKOR")) {
			    		                	Double.valueOf(item);
			    		                	this.setText(item);
		    		                	}
		    		                	else {
		    		                		TriangularFuzzyNumber tfn  = new TriangularFuzzyNumber(1.0,1.0,1.0);
	    		                			tfn.RefreshData(item);
	    		                			this.setText(item);
		    		                	}
		    		                	//setStyle("");
		    		                    //setStyle("-fx-background-color: yellow");
		    		                } catch(Exception ex) {
		    		                	this.setText(item);
		    		                	this.setStyle("-fx-background-color: yellow");
		    		                }
	    		            }
	    		        }
	    		    };
	    		});
	        }
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

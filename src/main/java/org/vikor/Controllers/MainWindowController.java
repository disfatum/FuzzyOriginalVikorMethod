package org.vikor.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.vikor.DataStructure.Criterion;
import org.vikor.Views.AddFA;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;

public class MainWindowController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private ChoiceBox<String> MethodBox;
    
    @FXML
    private URL location;

    @FXML
    private Button CalcButton;

    @FXML
    private Button VPButton;

    @FXML
    private Button QVButton;

    @FXML
    private Button DomButton;

    @FXML
    private TableView<?> PTable;
    
    @FXML
    private TableView<Criterion>Ftable;
    
    @FXML
    private Button Settings;

    @FXML
    private Button SRWButton;
    public void adddata(TableColumn<Criterion, String> c) {
    	c.setCellValueFactory(new PropertyValueFactory<>("weigh"));
    	c.setCellFactory(TextFieldTableCell.<Criterion> forTableColumn());
    	
        // On Cell edit commit (for FullName column)
    	
    }
    public void addMnames(ChoiceBox<String> MethodBox) {
	     ObservableList<String> methodnames = FXCollections.observableArrayList();
	     methodnames.add("Original VIKOR");
	     methodnames.add("Fuzzy VIKOR");	
	     MethodBox.setItems(methodnames);
	     MethodBox.setValue("Original VIKOR");
    }
    static public AddFA f = new AddFA();
    int a = 0,b= 0,c= 0,d= 0;
    @FXML
    void initialize() {
      addMnames(MethodBox);
      PTable.addEventHandler(MouseEvent.ANY, event -> {
    	    if (event.getClickCount() == 2 && event.getButton().equals(MouseButton.PRIMARY)) {
    	        if (event.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
    	            System.out.println("hello"); // perform some action
    	        }

    	        event.consume();
    	    }
    	});
      PTable.addEventHandler(MouseEvent.ANY, event -> {
    	 
  	    if (event.getClickCount() == 1 && event.getButton().equals(MouseButton.SECONDARY)) {
  	        if (event.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
  	            System.out.println("lol"); // perform some action
  	           
  		 		Stage primaryStage = new Stage();
  				try {
					f.start(primaryStage);
					addFAController.stage = primaryStage;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
  	          
  	        }

  	        event.consume();
  	    }
  	});
      PTable.addEventHandler(MouseEvent.ANY, event -> {
     	 
    	    if (event.getClickCount() == 2 && event.getButton().equals(MouseButton.SECONDARY)) {
    	        if (event.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
    	            System.out.println("lol2"); // perform some action
    	       
    	        }

    	        event.consume();
    	    }
    	});
      Ftable.isEditable();
      ObservableList<Criterion> methodnames = FXCollections.observableArrayList();
      TableColumn<Criterion,String> Col0 = new TableColumn<Criterion,String>("a");
    	  TableColumn<Criterion,String> Col1 = new TableColumn<Criterion,String>("b");
    	  TableColumn<Criterion,String> Col2 = new TableColumn<Criterion,String>("c");
    	  TableColumn<Criterion,String> Col3 = new TableColumn<Criterion,String>("d");
    	  adddata(Col1);adddata(Col2);adddata(Col3);adddata(Col0);
    	  Ftable.getColumns().add(Col3);
    	  Ftable.getColumns().add(Col2);
    	  Ftable.getColumns().add(Col1);
    	  Ftable.getColumns().add(Col0);
      Ftable.addEventHandler(MouseEvent.ANY, event -> {
    	 
  	    if (event.getClickCount() == 1 && event.getButton().equals(MouseButton.SECONDARY)) {
  	        if (event.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
  	            System.out.println("lol2"); // perform some action
  	         // TableColumn<Criterion,String> Col0 = new TableColumn<Criterion,String>("Альтернативы/Критерии");
  	    	//pData.add(Col0);
  	    	
  	        Criterion cr = new Criterion(a+"",b+"",c+"",d+"");
  	        methodnames.add(cr);
  	      Ftable.setItems(methodnames);
  	        a++;b++;c++;d++;
  	        
  	        }

  	        event.consume();
  	    }
  	});
    }
}


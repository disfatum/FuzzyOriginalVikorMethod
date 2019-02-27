package org.vikor.Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPopup;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.vikor.DataStructure.OriginalCriterionDataStructure;
import org.vikor.DataStructure.OriginalPtableData;
import org.vikor.DataStructure.TriangularFuzzyNumber;
import org.vikor.Events.FtableEvent;
import org.vikor.Events.InitMethodBox;
import org.vikor.Events.PopupEvent;
import org.vikor.Events.PtableEvent;
import org.vikor.Views.Calculate;
import org.vikor.Views.Ptableprop;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

public class VikorController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton CalculateButton;

    @FXML
    private JFXButton DominationButton;

    @FXML
    private JFXButton VPButton;

    @FXML
    private JFXButton QvButton;

    @FXML
    private JFXButton SRwButton;

    @FXML
    private JFXButton SettingsButton;

    @FXML
    private TableView<OriginalCriterionDataStructure> Ftable;
    
    @FXML
    private TableView<OriginalPtableData> Ptable;
    
    @FXML
    private JFXPopup Ftablepop;
    
    @FXML
    private JFXPopup Ftablepopfirst;
    
    @FXML
    private JFXPopup Fnamepop;
    
    @FXML
    private JFXPopup PfnamesPopup;
    
    @FXML
    private JFXPopup PanamesPopup;
    
    @FXML
    private JFXPopup PfirstPopup;
    
    @FXML
    private JFXPopup PSelectedPopup;

    @FXML
    private ComboBox<String> ClassicFuzzyBox;
    
    
    public static int indexPropCol;
    PopupEvent PopupEvents = new PopupEvent();
    FtableEvent FtableEvents = new FtableEvent();
    PtableEvent PtableEvents = new PtableEvent();
    
   	public static ObservableList<OriginalCriterionDataStructure> FTableData = FXCollections.observableArrayList();
   	public static ObservableList<TriangularFuzzyNumber> FuzzyFTableData = FXCollections.observableArrayList();
   	public static ObservableList<OriginalPtableData> PTableData = FXCollections.observableArrayList();
    public static ObservableList<ObservableList<TriangularFuzzyNumber>> FuzzyPTableData = FXCollections.observableArrayList();
   	
	public static int pindexPropCol;
   	boolean bx = false;
   	
   	@FXML
    void initialize() {
   		
   		InitMethodBox init = new InitMethodBox();
   		init.init(ClassicFuzzyBox);
   		
   		firstenter();
   		
   		ClassicFuzzyBox.addEventHandler(Event.ANY, e->{
   			if(ClassicFuzzyBox.getValue().equals("Fuzzy VIKOR") && bx == false) {
   				System.out.println("FUZZY version initialize");
   				
   				FuzzyPTableData.clear();
   				for(int i = 0; i < PTableData.size();i++) {
   					ObservableList<TriangularFuzzyNumber> fuzzylist =  FXCollections.observableArrayList();
   					
   					for(int j = 1 ; j < PTableData.get(0).size();j++) {
   						Double center = Double.valueOf(PTableData.get(i).getFromList(j));
   						Double left = center * 0.9;
   						Double right = center * 1.1;
   						TriangularFuzzyNumber tfn = new TriangularFuzzyNumber(left,center,right);
   						fuzzylist.add(tfn);
   					}
   					FuzzyPTableData.add(fuzzylist);
   				}
   				
   				for(int i = 0; i < PTableData.size();i++) {
   					for(int j = 0 ; j < FuzzyPTableData.get(0).size();j++) {
   						PTableData.get(i).setinlist(j+1, FuzzyPTableData.get(i).get(j).DataforTable());
   					}
   				}
   				Ptable.refresh();
   				bx = true;
   				
   			}
   			if(ClassicFuzzyBox.getValue().equals("Classic VIKOR") && bx == true) {
   				System.out.println("Сlassic version initialize");
   				for(int i = 0; i < PTableData.size();i++) {
   					for(int j = 0 ; j < FuzzyPTableData.get(0).size();j++) {
   						PTableData.get(i).setinlist(j+1, FuzzyPTableData.get(i).get(j).getCenter().toString());
   					}
   				}
   				Ptable.refresh();
   				bx = false;
   			}
   		});
   		
   		
    }
   	/*
   	 * добавление первой колонки с именами альтернатив
   	 */
   	public void AltNameCol() {
   		TableColumn<OriginalPtableData,String> AltNameCol = new TableColumn<OriginalPtableData,String>("Альтернатива/Критерий");
   		AltNameCol.sortableProperty().set(false);
   		AltNameCol.setOnEditCommit((CellEditEvent<OriginalPtableData, String> event2) -> {
            
      		 TablePosition<OriginalPtableData, String> pos = event2.getTablePosition();
      		String newitem = event2.getNewValue();
            int row0 = pos.getRow();
            int col0  = pos.getColumn();
            PTableData.get(row0).setinlist(col0, newitem);
          
           });
   		for(int i = 0; i < 1;i++) {
   			final int q = i;
   		AltNameCol.setCellValueFactory(new Callback<CellDataFeatures<OriginalPtableData, String>, ObservableValue<String>>() {
		     public ObservableValue<String> call(CellDataFeatures<OriginalPtableData, String> p) {
				
		    	 return new ReadOnlyObjectWrapper<String>(p.getValue().getFromList(q));
		     }
		  });
   		}
   		Ptable.getColumns().add(AltNameCol);
   	}
   	
   	/*
   	 * инициализация при запуске
   	 */
   	public void firstenter() {
   		Ftable.isEditable();
        Ftable.setItems(FTableData);
        
        Ptable.isEditable();
        Ptable.setItems(PTableData);
        
        Fnamepop = new JFXPopup();
        Ftablepopfirst = new JFXPopup();
        Ftablepop  = new JFXPopup();
        
        PfnamesPopup = new JFXPopup();
        PfirstPopup = new JFXPopup();
        PSelectedPopup = new JFXPopup();
        PanamesPopup = new JFXPopup();
        
        
        PopupEvents.FSelectedRow(Ftablepop, Fnamepop, Ftable, FTableData, Ptable,PTableData);
        PopupEvents.FNotSelected(Ftablepopfirst, Fnamepop);
        PopupEvents.Faddnames(Fnamepop,Ptable, PtableEvents, PTableData); 
        
        PopupEvents.PAaddNames(PanamesPopup, PTableData, Ptable);
        PopupEvents.PFaddNames(PfnamesPopup, PtableEvents, PopupEvents, Ptable, PTableData);
        PopupEvents.PSelected(PSelectedPopup, PfnamesPopup, PanamesPopup, Ptable, PTableData);
        PopupEvents.PNotSelected(PfirstPopup, PfnamesPopup, PanamesPopup);
        
        FtableEvents.AddCols(Ftable, FTableData, PopupEvents, Ftablepop, Ftablepopfirst);
        PtableEvents.addEvents(Ptable, PTableData, PopupEvents, PfirstPopup, PSelectedPopup);
        
        AltNameCol();
        Ptable.refresh();
        Ftable.refresh();
        
        FtablepropController.jb.setOnAction(e->{
        	Ftable.refresh();
        	
        	Ptable.getColumns().get(FtablepropController.Tableindex).setText(FtablepropController.ChangedName);
        	Ptable.refresh();
        });
        
        PtablepropController.jb.setOnAction(e->{
        	Ptable.refresh();
        });
        
        CalculateButton.setOnAction(e->{
        	Calculate f = new Calculate();
			Stage primaryStage = new Stage();
			try {
					f.start(primaryStage);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
		
        });
   	}
}
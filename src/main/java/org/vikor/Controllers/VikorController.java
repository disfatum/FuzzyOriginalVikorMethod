package org.vikor.Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPopup;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.vikor.DataStructure.OriginalCriterionDataStructure;
import org.vikor.DataStructure.OriginalPtableData;
import org.vikor.DataStructure.Settings;
import org.vikor.DataStructure.TriangularFuzzyNumber;
import org.vikor.Events.FtableEvent;
import org.vikor.Events.InitMethodBox;
import org.vikor.Events.PopupEvent;
import org.vikor.Events.PtableEvent;
import org.vikor.Methods.ClassicVikor;
import org.vikor.Views.Calculate;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
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
    public static ClassicVikor t;
   	public static ObservableList<OriginalCriterionDataStructure> FTableData = FXCollections.observableArrayList();
   	public static ObservableList<OriginalCriterionDataStructure> FuzzyFTableData = FXCollections.observableArrayList();
   	public static ObservableList<OriginalCriterionDataStructure> OriginalFTableData = FXCollections.observableArrayList();
   	public static ObservableList<OriginalPtableData> PTableData = FXCollections.observableArrayList();
   	public static ObservableList<ObservableList<String>> OriginalPTableData = FXCollections.observableArrayList();
    public static ObservableList<ObservableList<TriangularFuzzyNumber>> FuzzyPTableData = FXCollections.observableArrayList();
    
    public static ObservableList<TableColumn<OriginalPtableData,String>> OriginalPTableDataColumns = FXCollections.observableArrayList();
    public static ObservableList<TableColumn<OriginalPtableData,String>> FuzzyPTableDataColumns = FXCollections.observableArrayList();
    public static ObservableList<String> Colnames = FXCollections.observableArrayList();
    
   	
	public static int pindexPropCol;
   	boolean bx = true;
   	public static boolean f = false;
   	
   	public static Settings Settings = new Settings("Нет");
   	
   	@FXML
    void initialize() {
   		
   		InitMethodBox init = new InitMethodBox();
   		init.init(ClassicFuzzyBox);
   		firstenter();
   		
   	ClassicFuzzyBox.addEventHandler(Event.ANY, e->{
   			
   		  if(Settings.getSynchronization().equals("Да")) {
   			  Colnames.clear();
   			if(ClassicFuzzyBox.getValue().equals("Fuzzy VIKOR") && bx == false) {
   					
	   				System.out.println("FUZZY version initialize");
	   				f = true;
	   				for(int i = 0; i < PTableData.size();i++) {
	   					for(int j = 0 ; j < FuzzyPTableData.get(0).size();j++) {
	   						PTableData.get(i).setinlist(j+1,FuzzyPTableData.get(i).get(j).DataforTable());
	   					
	   					}
	   				}
	   				for(int i = 0; i < FTableData.size();i++) {
	   						TriangularFuzzyNumber tfn = new TriangularFuzzyNumber(
	   								Double.valueOf(FTableData.get(i).getWeigh())*0.9,Double.valueOf(FTableData.get(i).getWeigh()),
	   								Double.valueOf(FTableData.get(i).getWeigh())*1.1);
	   						FTableData.get(i).setWeigh(tfn.DataforTable());
	   				}
	   				Ftable.refresh();
	   				Ptable.refresh();
	   				bx = true;
	   				
   				}
	   			if(ClassicFuzzyBox.getValue().equals("Classic VIKOR") && bx == true) {
	   				f = false;
	   				System.out.println("Сlassic version initialize");
	   				for(int i = 0; i < PTableData.size();i++) {
	   					for(int j = 0 ; j < FuzzyPTableData.get(i).size();j++) {
	   						PTableData.get(i).setinlist(j+1,OriginalPTableData.get(i).get(j));
	   					}
	   				}
	   				for(int i = 0; i < FTableData.size();i++) {
	   						TriangularFuzzyNumber tfn = new TriangularFuzzyNumber(0.9,1.0,1.1);
	   						tfn.RefreshData(FuzzyFTableData.get(i).getWeigh());
	   						FTableData.get(i).setWeigh(tfn.getCenter().toString());
	   				}
	   				Ftable.refresh();
	   				Ptable.refresh();
	   				bx = false;
	   			}
	   			for(int i = 0; i < Ptable.getColumns().size();i++) {
	   				Colnames.add(Ptable.getColumns().get(i).getText());
	   			}
   			}
   		  	if(Settings.getSynchronization().equals("Нет")) {
   				
   				if(ClassicFuzzyBox.getValue().equals("Fuzzy VIKOR") && bx == false) {
	   				f = true;
	   				Colnames.clear();
	   				System.out.println("FUZZY version initialize");
	   				PTableData.clear();
	   				FTableData.clear();
	   				Ptable.getColumns().clear();
	   				AltNameCol();
	   				
	   				for(int i = 0; i < FuzzyPTableData.size();i++) {
	   					ObservableList<String > list1 =  FXCollections.observableArrayList();
	   					list1.add(FuzzyPTableData.get(i).get(0).Name);
	   					for(int j = 1 ; j < FuzzyPTableData.get(i).size();j++) {
	   						
	   							list1.add(FuzzyPTableData.get(i).get(j).DataforTable());
	   					}
	   						OriginalPtableData pd = new OriginalPtableData(list1);
	   						PTableData.add(pd);
	   				}
	   				for(int i = 0; i < FuzzyFTableData.size();i++) {
	   					FTableData.add(FuzzyFTableData.get(i));
	   				}
	   				Ftable.refresh();
	   				Ptable.getColumns().addAll(FuzzyPTableDataColumns);
	   				Ptable.setItems(PTableData);
	   				Ptable.refresh();
	   				bx = true;
	   				for(int i = 0; i < Ptable.getColumns().size();i++) {
		   				Colnames.add(Ptable.getColumns().get(i).getText());
		   			}
   				}	
   			
   			if(ClassicFuzzyBox.getValue().equals("Classic VIKOR") && bx == true) {
   				Colnames.clear();
   				f = false;
   				FTableData.clear();
   				PTableData.clear();
   				Ptable.getColumns().clear();
   				AltNameCol();
   				System.out.println("Сlassic version initialize");
   				for(int i = 0; i < OriginalPTableData.size();i++) {
   						OriginalPtableData pd = new OriginalPtableData(OriginalPTableData.get(i));
   						PTableData.add(pd);
   					
   				}
   				
   				for(int i = 0; i < OriginalFTableData.size();i++) {
   					FTableData.add(OriginalFTableData.get(i));
   				}
   				Ftable.refresh();
   				Ptable.getColumns().addAll(OriginalPTableDataColumns);
   				Ptable.setItems(PTableData);
   				Ptable.refresh();
   				bx = false;
   				for(int i = 0; i < Ptable.getColumns().size();i++) {
	   				Colnames.add(Ptable.getColumns().get(i).getText());
	   			}
   			}
   		}
   	});
   		CalculateButton.setOnAction(e->{
        	Calculate f = new Calculate();
			Stage primaryStage = new Stage();
			try {	
					t = new ClassicVikor();
					t.Calculate(PTableData, FTableData, 0.5, Settings, OriginalPTableData);
					f.start(primaryStage);
				
				} catch (IOException e1) {
					e1.printStackTrace();
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
        
        
   	}
}
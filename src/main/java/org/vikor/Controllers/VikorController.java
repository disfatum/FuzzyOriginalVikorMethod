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
import org.vikor.Methods.FuzzyVikorCentroid;
import org.vikor.Methods.FuzzyVikorMax;
import org.vikor.Methods.FuzzyVikorMediana;
import org.vikor.Views.Calculate;
import org.vikor.Views.Domination;
import org.vikor.Views.FuzzyCalculate;
import org.vikor.Views.QvView;
import org.vikor.Views.SRwView;
import org.vikor.Views.Settingsview;
import org.vikor.Views.ValuePathView;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
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
    
    @FXML
    private BorderPane bp;
    
    public static int indexPropCol;
    PopupEvent PopupEvents = new PopupEvent();
    FtableEvent FtableEvents = new FtableEvent();
    PtableEvent PtableEvents = new PtableEvent();
    public static ClassicVikor t;
    public static FuzzyVikorCentroid Cindex;
    public static FuzzyVikorMediana Mediana;
    public static FuzzyVikorMax LargeMax;
    
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
   	
   	public static Settings Settings = new Settings("Да","0.5",10,10,0.01,0.01);
   	
   	@FXML
    void initialize() {
   		InitMethodBox init = new InitMethodBox();
   		init.init(ClassicFuzzyBox);
   		firstenter();
   		
   	ClassicFuzzyBox.addEventHandler(Event.ANY, e->{
   			
   		  if(Settings.getSynchronization().equals("Да")  ) {
   			  Colnames.clear();
   			if(ClassicFuzzyBox.getValue().equals("Fuzzy VIKOR") && bx == false) {
   				
   					DominationButton.setDisable(true);
   					VPButton.setDisable(true);
   					QvButton.setDisable(true);
   					SRwButton.setDisable(true);
   					
	   				System.out.println("FUZZY version initialize");
	   				f = true;
	   				
	   				for(int i = 0; i < PTableData.size();i++) {
	   					for(int j = 0 ; j < PTableData.get(0).size()-1;j++) {
	   						
	   						TriangularFuzzyNumber tfn = new TriangularFuzzyNumber(
	   								Double.valueOf(PTableData.get(i).get(j+1))*0.9,
	   								Double.valueOf(PTableData.get(i).get(j+1)),
	   								Double.valueOf(PTableData.get(i).get(j+1))*1.1);
	   						PTableData.get(i).setinlist(j+1,tfn.DataforTable());
	   					
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
	   				
   					DominationButton.setDisable(false);
   					VPButton.setDisable(false);
   					QvButton.setDisable(false);
   					SRwButton.setDisable(false);
	   				
	   				f = false;
	   				System.out.println("Classic version initialize");
	   				for(int i = 0; i < PTableData.size();i++) {
	   					for(int j = 0 ; j < PTableData.get(i).size()-1;j++) {
	   						TriangularFuzzyNumber tfn = new TriangularFuzzyNumber(0.0,1.0,1.1);
	   						//System.out.println(PTableData+"");
	   						tfn.RefreshData(PTableData.get(i).get(j+1));
	   						PTableData.get(i).setinlist(j+1,tfn.getCenter().toString());
	   					}
	   				}
	   				for(int i = 0; i < FTableData.size();i++) {
	   						TriangularFuzzyNumber tfn = new TriangularFuzzyNumber(0.9,1.0,1.1);
	   						tfn.RefreshData(FTableData.get(i).getWeigh());
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

   					DominationButton.setDisable(true);
   					VPButton.setDisable(true);
   					QvButton.setDisable(true);
   					SRwButton.setDisable(true);
   					
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
   				
   				DominationButton.setDisable(false);
				VPButton.setDisable(false);
				QvButton.setDisable(false);
				SRwButton.setDisable(false);
   				
   				Colnames.clear();
   				f = false;
   				FTableData.clear();
   				PTableData.clear();
   				Ptable.getColumns().clear();
   				AltNameCol();
   				System.out.println("Classic version initialize");
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
   	
   	Event event = new Event(Event.ANY);
    ClassicFuzzyBox.fireEvent(event);
    
   		CalculateButton.setOnAction(e->{
	   		if(ClassicFuzzyBox.getValue().equals("Classic VIKOR")) {
	        	Calculate f = new Calculate();
				Stage primaryStage = new Stage();
				try {	
						t = new ClassicVikor();
						t.Calculate(PTableData, FTableData, Double.valueOf(Settings.getV()), Settings);
						f.start(primaryStage);
					
					} catch (IOException e1) {
						e1.printStackTrace();
					}
	   		}
	   		if(ClassicFuzzyBox.getValue().equals("Fuzzy VIKOR")) {
	   			Cindex = new FuzzyVikorCentroid();
	   			TriangularFuzzyNumber tfn1 = new TriangularFuzzyNumber(0.0,0.2,0.5);
	   			tfn1.RefreshData(Settings.getV()+"");
	   			Cindex.Calculate(PTableData, FTableData, tfn1, Settings);
	   			
	   			Mediana = new FuzzyVikorMediana();
	   			TriangularFuzzyNumber tfn2 = new TriangularFuzzyNumber(0.0,0.2,0.5);
	   			tfn2.RefreshData(Settings.getV()+"");
	   			Mediana.Calculate(PTableData, FTableData, tfn2, Settings);
	   			
	   			LargeMax = new FuzzyVikorMax();
	   			TriangularFuzzyNumber tfn3 = new TriangularFuzzyNumber(0.0,0.2,0.5);
	   			tfn3.RefreshData(Settings.getV()+"");
	   			LargeMax.Calculate(PTableData, FTableData, tfn3, Settings);
	   			
	   			FuzzyCalculate p = new FuzzyCalculate();
	   			Stage primaryStage = new Stage();
				try {	
						p.start(primaryStage);
					
					} catch (IOException e1) {
						e1.printStackTrace();
					}
	   		}
        });
   		
   		SettingsButton.setOnAction(e->{
   			Settingsview f = new Settingsview();
			Stage primaryStage = new Stage();
			try {	
					f.start(primaryStage);
				
				} catch (IOException e1) {
					e1.printStackTrace();
				}
   		});
   		
   		QvButton.setOnAction(e->{
   			QvView f = new QvView();
			Stage primaryStage = new Stage();
			try {	
					t = new ClassicVikor();
					t.Calculate(PTableData, FTableData, Double.valueOf(Settings.getV()), Settings);
					f.start(primaryStage);
				
				} catch (IOException e1) {
					e1.printStackTrace();
				}
   		});
   		
   		SRwButton.setOnAction(e->{
   			SRwView f = new SRwView();
			Stage primaryStage = new Stage();
			try {	
					t = new ClassicVikor();
					t.Calculate(PTableData, FTableData, Double.valueOf(Settings.getV()), Settings);
					f.start(primaryStage);
				
				} catch (IOException e1) {
					e1.printStackTrace();
				}
   		});
   		VPButton.setOnAction(e->{
   			ValuePathView f = new ValuePathView();
			Stage primaryStage = new Stage();
			try {	
					t = new ClassicVikor();
					t.Calculate(PTableData, FTableData, Double.valueOf(Settings.getV()), Settings);
					f.start(primaryStage);
				
				} catch (IOException e1) {
					e1.printStackTrace();
				}
   		});
   		DominationButton.setOnAction(e->{
   			Domination f = new Domination();
			Stage primaryStage = new Stage();
			try {	
					t = new ClassicVikor();
					t.Calculate(PTableData, FTableData, Double.valueOf(Settings.getV()), Settings);
					f.start(primaryStage);
				
				} catch (IOException e1) {
					e1.printStackTrace();
				}
   		});
    }
   	/*
   	 * 
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
   	 * 
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
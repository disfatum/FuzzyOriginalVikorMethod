package org.vikor.DataStructure;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.vikor.Alerts.Alerts;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.util.Callback;


public class OpenProject {
	
	public void  getDat(String name,Settings settings, 
			ObservableList<OriginalCriterionDataStructure> fTableData,
			ObservableList<OriginalPtableData> pTableData,
			ComboBox<String> classicFuzzyBox, 
			TableView<OriginalPtableData> ptable,
			TableView<OriginalCriterionDataStructure> ftable2) throws NumberFormatException, IOException {
		try {
    	//List<ThemeFull> dat = new ArrayList<ThemeFull>();
		pTableData.clear();
		fTableData.clear();
		ptable.getColumns().remove(1, ptable.getColumns().size());
        String strLine;
        BufferedReader br;
		
			br = new BufferedReader(new FileReader(name));
			ObservableList<String> colnames = FXCollections.observableArrayList();
        String mode = "";
        while ((strLine = br.readLine()) != null) {
            String[] arr = strLine.split("=");
            if(arr[0].equals("MODE")) {
            	if(arr[1].equals("Classic VIKOR")) {
            		classicFuzzyBox.setValue("Classic VIKOR");
            	}
            	if(arr[1].equals("Fuzzy VIKOR")) {
            		classicFuzzyBox.setValue("Fuzzy VIKOR");
            	}
            	mode = arr[1];
            }
            if(arr[0].equals("Settings")) {
            	String[] set = arr[1].split(";");
            	settings.setSynchronization(set[0]);
            	settings.setV(set[1]);
            	settings.setQvs(Double.valueOf(set[2]));
            	settings.setQvstep(Integer.valueOf(set[3]));
            	settings.setSrs(Double.valueOf(set[4]));
            	settings.setSRstep(Integer.valueOf(set[5]));
            }
            if(settings.getSynchronization().equals("Äà")) {
	            if(arr[0].equals("FFUZZY")) {
	            	if(mode.equals("Fuzzy VIKOR")) {
	            	String[] ftable = arr[1].split("-");
	            	for(int c = 0; c < ftable.length;c++) {
            			System.out.println(ftable[c]+" ftable");
            		}
	            	for(int i = 0; i < ftable.length ;i++) {
	            		
	            		String[] critstruct = ftable[i].split(";");
	            		colnames.add(critstruct[0]);
	            		for(int c = 0; c < critstruct.length;c++) {
	            			System.out.println(critstruct[c]+" critstruct");
	            		}
	            		OriginalCriterionDataStructure od = new OriginalCriterionDataStructure(critstruct[0], critstruct[1], critstruct[2], critstruct[3]);
		            		fTableData.add(od);
		            	}
		            }
	            }
	            if(arr[0].equals("FCLASSIC")) {
	            	if(mode.equals("Classic VIKOR")) {
	            		String[] ftable = arr[1].split("-");
		            	
		            	for(int i = 0; i < ftable.length ;i++) {
		            		
		            		String[] critstruct = ftable[i].split(";");
		            		colnames.add(critstruct[0]);
		            		OriginalCriterionDataStructure od = new OriginalCriterionDataStructure(critstruct[0], critstruct[1], critstruct[2], critstruct[3]);
			            		fTableData.add(od);
			            	}
			            }
	            	
	            }
	            if(arr[0].equals("PFUZZY")) {
	            	if(mode.equals("Fuzzy VIKOR")) {
		            	String[] ptabledat = arr[1].split("-");
		            	
		            	for(int i = 0; i < ptabledat.length ;i++) {
		            		String[] struct = ptabledat[i].split(";");
		            		//OriginalPtableData pd = new OriginalPtableData(null);
		            		ObservableList<String> l = FXCollections.observableArrayList();
		            		for(int j = 0; j < struct.length;j++) {
		            			l.add(struct[j]);
		            		}
		            		OriginalPtableData pd = new OriginalPtableData(l);
		            		pTableData.add(pd);
		            		//AddCol(colnames.get(i), pTableData, ptable);
		            	}
	            	}
	            }
	            if(arr[0].equals("PCLASSIC")) {
	            	if(mode.equals("Classic VIKOR")) {
		            	String[] ptabledat = arr[1].split("-");
		            	
		            	for(int i = 0; i < ptabledat.length ;i++) {
		            		String[] struct = ptabledat[i].split(";");
		            		//OriginalPtableData pd = new OriginalPtableData(null);
		            		ObservableList<String> l = FXCollections.observableArrayList();
		            		for(int j = 0; j < struct.length;j++) {
		            			l.add(struct[j]);
		            		}
		            		OriginalPtableData pd = new OriginalPtableData(l);
		            		pTableData.add(pd);
		            		//AddCol(colnames.get(i), pTableData, ptable);
		            	}
	            	}
	            }
            }
            else if(settings.getSynchronization().equals("Íåò")) {///////ÄÎÄÅËÀÒÜ!!!!!!!!!1
            	 if(arr[0].equals("FFUZZY")) {
 	            	if(mode.equals("Fuzzy VIKOR")) {
 	            	String[] ftable = arr[1].split("-");
 	            	for(int c = 0; c < ftable.length;c++) {
             			System.out.println(ftable[c]+" ftable");
             		}
 	            	for(int i = 0; i < ftable.length ;i++) {
 	            		
 	            		String[] critstruct = ftable[i].split(";");
 	            		colnames.add(critstruct[0]);
 	            		for(int c = 0; c < critstruct.length;c++) {
 	            			System.out.println(critstruct[c]+" critstruct");
 	            		}
 	            		OriginalCriterionDataStructure od = new OriginalCriterionDataStructure(critstruct[0], critstruct[1], critstruct[2], critstruct[3]);
 		            		fTableData.add(od);
 		            	}
 		            }
 	            }
 	            if(arr[0].equals("FCLASSIC")) {
 	            	if(mode.equals("Classic VIKOR")) {
 	            		String[] ftable = arr[1].split("-");
 		            	
 		            	for(int i = 0; i < ftable.length ;i++) {
 		            		
 		            		String[] critstruct = ftable[i].split(";");
 		            		colnames.add(critstruct[0]);
 		            		OriginalCriterionDataStructure od = new OriginalCriterionDataStructure(critstruct[0], critstruct[1], critstruct[2], critstruct[3]);
 			            		fTableData.add(od);
 			            	}
 			            }
 	            	
 	            }
 	            if(arr[0].equals("PFUZZY")) {
 	            	if(mode.equals("Fuzzy VIKOR")) {
 		            	String[] ptabledat = arr[1].split("-");
 		            	
 		            	for(int i = 0; i < ptabledat.length ;i++) {
 		            		String[] struct = ptabledat[i].split(";");
 		            		//OriginalPtableData pd = new OriginalPtableData(null);
 		            		ObservableList<String> l = FXCollections.observableArrayList();
 		            		for(int j = 0; j < struct.length;j++) {
 		            			l.add(struct[j]);
 		            		}
 		            		OriginalPtableData pd = new OriginalPtableData(l);
 		            		pTableData.add(pd);
 		            		//AddCol(colnames.get(i), pTableData, ptable);
 		            	}
 	            	}
 	            }
 	            if(arr[0].equals("PCLASSIC")) {
 	            	if(mode.equals("Classic VIKOR")) {
 		            	String[] ptabledat = arr[1].split("-");
 		            	
 		            	for(int i = 0; i < ptabledat.length ;i++) {
 		            		String[] struct = ptabledat[i].split(";");
 		            		//OriginalPtableData pd = new OriginalPtableData(null);
 		            		ObservableList<String> l = FXCollections.observableArrayList();
 		            		for(int j = 0; j < struct.length;j++) {
 		            			l.add(struct[j]);
 		            		}
 		            		OriginalPtableData pd = new OriginalPtableData(l);
 		            		pTableData.add(pd);
 		            		//AddCol(colnames.get(i), pTableData, ptable);
 		            	}
 	            	}
 	            }
            }
	            
        }
        for(int i = 0; i < colnames.size();i++) {
        	AddCol(colnames.get(i), pTableData, ptable);
        }
        
        ptable.refresh();
        ftable2.refresh();
        br.close();
		}
    	catch(Exception ex) {
    		Alerts alert = new Alerts();;
			alert .Erroropen();
    	}
    }
	
	public void AddCol(String NAME,
			ObservableList<OriginalPtableData> pTableData,
			TableView<OriginalPtableData> ptable) {
			TableColumn<OriginalPtableData,String> col = new TableColumn<OriginalPtableData,String>(NAME);
		
		//col.setCellValueFactory(new PropertyValueFactory<>(NAME));
		//col.setText(NAME);
		col.sortableProperty().set(false);
		col.setOnEditCommit((CellEditEvent<OriginalPtableData, String> event2) -> {
             
       		 TablePosition<OriginalPtableData, String> pos = event2.getTablePosition();
       		 String newitem = event2.getNewValue();
             int row0 = pos.getRow();
             int col0  = pos.getColumn();
             pTableData.get(row0).setinlist(col0, newitem);
           
            });
		for(int i = 0; i < ptable.getColumns().size()+1;i++) {
			final int q = i;
			col.setCellValueFactory(new Callback<CellDataFeatures<OriginalPtableData, String>, ObservableValue<String>>() {
		     public ObservableValue<String> call(CellDataFeatures<OriginalPtableData, String> p) {
		    	 
		    	 return new ReadOnlyObjectWrapper<String>(p.getValue().getFromList(q));
		    	 
		     }
		  });	
		}
		ptable.getColumns().add(col);
	}
}

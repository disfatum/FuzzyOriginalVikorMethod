package org.vikor.DataStructure;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.vikor.Alerts.Alerts;
import org.vikor.Controllers.VikorController;

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
		ptable.getColumns().clear();
        String strLine;
        BufferedReader br;
		
			br = new BufferedReader(new FileReader(name));
			BufferedReader br1;
			
			br1 = new BufferedReader(new FileReader(name));
			ObservableList<String> colnames = FXCollections.observableArrayList();
        String mode = "";

         //int counter = 0; int mods = 0;
         while ((strLine = br1.readLine()) != null) {
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
         }
         br1.close();
        while ((strLine = br.readLine()) != null) {
            String[] arr = strLine.split("=");
            
            if(arr[0].equals("Settings")) {
            	String[] set = arr[1].split(";");
            	settings.setSynchronization(set[0]);
            	settings.setV(set[1]);
            	settings.setQvs(Double.valueOf(set[2]));
            	settings.setQvstep(Integer.valueOf(set[3]));
            	settings.setSrs(Double.valueOf(set[4]));
            	settings.setSRstep(Integer.valueOf(set[5]));
            }
            if(settings.getSynchronization().equals("Да") ) {
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
	            for(int i = 0; i < colnames.size();i++) {
	            	AddCol(colnames.get(i), pTableData, ptable);
	            }
            }
            
            else if(settings.getSynchronization().equals("Нет")) {///////ДОДЕЛАТЬ!!!!!!!!!1
            	 if(arr[0].equals("FFUZZY")) {
 	            	String[] ftable = arr[1].split("-");
 	            	List<String> cols = FXCollections.observableArrayList();
 	            	for(int i = 0; i < ftable.length ;i++) {
 	            		
 	            		String[] critstruct = ftable[i].split(";");
 	            		//cols.add(critstruct[0]);
 	            		
 	            		OriginalCriterionDataStructure od = new OriginalCriterionDataStructure(critstruct[0], critstruct[1], critstruct[2], critstruct[3]);
 	            		if(mode.equals("Fuzzy VIKOR")) {
		            			fTableData.add(od);
 	            		}
 		            		VikorController.FuzzyFTableData.add(od);
 		            		cols.add(critstruct[0]);
 	            	}
 	            	TableColumn<OriginalPtableData,String> col1 = new TableColumn<OriginalPtableData,String>("Альтернатива/Критерий");
 	           		
	            		VikorController.FuzzyPTableDataColumns.add(col1);
 	            	for(int i = 0; i < cols.size();i++) {
 	            		TableColumn<OriginalPtableData,String> col = new TableColumn<OriginalPtableData,String>(cols.get(i));
 	            		VikorController.FuzzyPTableDataColumns.add(col);
 	            	}
 		            
 	            }
 	            if(arr[0].equals("FCLASSIC")) {
 	            		String[] ftable = arr[1].split("-");
 	            		List<String> cols = FXCollections.observableArrayList();
 		            	for(int i = 0; i < ftable.length ;i++) {
 		            		
 		            		String[] critstruct = ftable[i].split(";");
 		            		colnames.add(critstruct[0]);
 		            		OriginalCriterionDataStructure od = new OriginalCriterionDataStructure(critstruct[0], critstruct[1], critstruct[2], critstruct[3]);
 			            		
 		            		if(mode.equals("Classic VIKOR")) {
 		            			fTableData.add(od);
 		            		}
 		            		cols.add(critstruct[0]);
 			            	VikorController.OriginalFTableData.add(od);
 			            	}
 		            	TableColumn<OriginalPtableData,String> col1 = new TableColumn<OriginalPtableData,String>("Альтернатива/Критерий");
 	 	           		
	            		VikorController.OriginalPTableDataColumns.add(col1);
 	            	for(int i = 0; i < cols.size();i++) {
 	            		TableColumn<OriginalPtableData,String> col = new TableColumn<OriginalPtableData,String>(cols.get(i));
 	            		VikorController.OriginalPTableDataColumns.add(col);
 	            	}     
 	            	
 	            }
 	            if(arr[0].equals("PCLASSIC")) {
 		            	String[] ptabledat = arr[1].split("-");
 		            	
 		            	for(int i = 0; i < ptabledat.length ;i++) {
 		            		String[] struct = ptabledat[i].split(";");
 		            		//OriginalPtableData pd = new OriginalPtableData(null);
 		            		ObservableList<String> l = FXCollections.observableArrayList();
 		            		
 		            		for(int j = 0; j < struct.length;j++) {
 		            			
 		            			l.add(struct[j]);
 		            		}
 		            		OriginalPtableData pd = new OriginalPtableData(l);
 		            		if(mode.equals("Classic VIKOR")) {
 		            			pTableData.add(pd);
 		            		}
 		            		VikorController.OriginalPTableData.add(l);
 		            		//AddCol(colnames.get(i), pTableData, ptable);
 		            	
 	            	}
 		            	
 	            }
 	            if(arr[0].equals("PFUZZY")) {
 		            	String[] ptabledat = arr[1].split("-");
 		            	
 		            	for(int i = 0; i < ptabledat.length ;i++) {
 		            		String[] struct = ptabledat[i].split(";");
 		            		//OriginalPtableData pd = new OriginalPtableData(null);
 		            		ObservableList<String> l = FXCollections.observableArrayList();
 		            		ObservableList<TriangularFuzzyNumber> e = FXCollections.observableArrayList();
 		            		
 		            		for(int j = 0; j < struct.length;j++) {
 		            			System.out.println(struct[j]+" "+ j);
 		            			l.add(struct[j]);
 		            			TriangularFuzzyNumber tfn = new TriangularFuzzyNumber(1.0,1.0,1.0);
 		            			if(j > 0) {
 		            				tfn.Name = struct[0];
 	 		            			tfn.RefreshData(struct[j]);
 	 		            			e.add(tfn);
 		            			}
 		            		}
 		            		OriginalPtableData pd = new OriginalPtableData(l);
 		            		if(mode.equals("Fuzzy VIKOR")) {
 		            			pTableData.add(pd);
 		            		}
 		            		VikorController.FuzzyPTableData.add(e);
 		            		
 		            		//AddCol(colnames.get(i), pTableData, ptable);
 		            	}
 		            	for(int i = 0; i < VikorController.FuzzyPTableData.size();i++) {
 		            		for(int j = 0; j < VikorController.FuzzyPTableData.get(i).size();j++) {
 		            			System.out.println(VikorController.FuzzyPTableData.get(i).get(j).Name+" name");
 		            			System.out.println(VikorController.FuzzyPTableData.get(i).get(j).DataforTable()+" dat");
 		            		}
 		            	}
 	            	
 	            }
 	            
            }
	            
        }
        if(mode.equals("Fuzzy VIKOR")) {
        	ptable.getColumns().addAll(VikorController.FuzzyPTableDataColumns);
        }
        if(mode.equals("Classic VIKOR")) {
        	 ptable.getColumns().addAll(VikorController.OriginalPTableDataColumns);
        }
        for(int i = 0; i < VikorController.FuzzyPTableDataColumns.size();i++) {
       	   AddColfuzy(VikorController.FuzzyPTableDataColumns.get(i), pTableData, ptable,i);
       	}
         for(int i = 0; i < VikorController.OriginalPTableDataColumns.size();i++) {
        	 origAddCol(VikorController.OriginalPTableDataColumns.get(i), pTableData, ptable, i);
          }
        
        
        
        ptable.refresh();
        ftable2.refresh();
        br.close();
        Alerts alert = new Alerts();
        alert.successfile();
		}
    	catch(Exception ex) {
    		Alerts alert = new Alerts();
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
            // VikorController.OriginalPTableData.get(row0).set(col0, newitem);
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
	
	public TableColumn<OriginalPtableData, String> fuzzyAddCol(String NAME,
			ObservableList<OriginalPtableData> pTableData,
			TableView<OriginalPtableData> ptable) {
			TableColumn<OriginalPtableData,String> col = new TableColumn<OriginalPtableData,String>(NAME);
		
		col.sortableProperty().set(false);
		return col;
	}
	public void origAddCol(TableColumn<OriginalPtableData,String> col,
	ObservableList<OriginalPtableData> pTableData,
	TableView<OriginalPtableData> ptable, int i) {
		//col.setCellValueFactory(new PropertyValueFactory<>(NAME));
		//col.setText(NAME);
		col.sortableProperty().set(false);
		col.setOnEditCommit((CellEditEvent<OriginalPtableData, String> event2) -> {
             
       		 TablePosition<OriginalPtableData, String> pos = event2.getTablePosition();
       		 String newitem = event2.getNewValue();
             int row0 = pos.getRow();
             int col0  = pos.getColumn();
             pTableData.get(row0).setinlist(col0, newitem);
             VikorController.OriginalPTableData.get(row0).set(col0, newitem);
            });
		for(int j = 0; j < VikorController.OriginalPTableData.size();j++) {
			final int z = i;
			col.setCellValueFactory(new Callback<CellDataFeatures<OriginalPtableData, String>, ObservableValue<String>>() {
		     public ObservableValue<String> call(CellDataFeatures<OriginalPtableData, String> p) {
		    	 System.out.println(z+" orig i");
		    	 return new ReadOnlyObjectWrapper<String>(p.getValue().getFromList(z));
		    	 
		     }
		  });	
		}
	}
	public static void AddColfuzy(TableColumn<OriginalPtableData,String> col,
			ObservableList<OriginalPtableData> pTableData,
			TableView<OriginalPtableData> ptable,
			int i) {
			//TableColumn<OriginalPtableData,String> col = new TableColumn<OriginalPtableData,String>(NAME);

		//
		//col.setCellValueFactory(new PropertyValueFactory<>(NAME));
		//col.setText(NAME);
		col.sortableProperty().set(false);
		col.setOnEditCommit((CellEditEvent<OriginalPtableData, String> event2) -> {
             
       		 TablePosition<OriginalPtableData, String> pos = event2.getTablePosition();
       		 String newitem = event2.getNewValue();
             int row0 = pos.getRow();
             int col0  = pos.getColumn();
             pTableData.get(row0).setinlist(col0, newitem);
             TriangularFuzzyNumber tfn1 = new TriangularFuzzyNumber(1.0,1.0,1.0);
             tfn1.RefreshData(newitem);
             VikorController.FuzzyPTableData.get(row0).set(col0, tfn1);
           
            });
		
		for(int j = 0; j < ptable.getColumns().size();j++) {
			final int q = i;
			col.setCellValueFactory(new Callback<CellDataFeatures<OriginalPtableData, String>, ObservableValue<String>>() {
		     public ObservableValue<String> call(CellDataFeatures<OriginalPtableData, String> p) {
		    	 

		    	 return new ReadOnlyObjectWrapper<String>(p.getValue().get(q));
		    	 
		     }
		  });	
		}
	}
}

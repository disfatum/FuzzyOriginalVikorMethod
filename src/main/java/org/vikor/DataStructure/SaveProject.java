package org.vikor.DataStructure;

import java.io.FileWriter;
import java.io.IOException;

import org.vikor.Alerts.Alerts;
import org.vikor.Controllers.VikorController;

import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;

public class SaveProject {
			
	public void Save(String name,Settings settings, 
			ObservableList<OriginalCriterionDataStructure> fTableData,
			ObservableList<OriginalPtableData> pTableData,
			ComboBox<String> classicFuzzyBox, 
			TableView<OriginalPtableData> ptable,
			TableView<OriginalCriterionDataStructure> ftable2){
		
		String mode = "MODE="+classicFuzzyBox.getValue()+"\n";
		String Sets = "Settings="+settings.getSynchronization()+";"+settings.getV()+";"+
		settings.getQvs()+";"+settings.getQvstep()+";"+settings.getSrs()+";"+settings.getSRstep()+";\n";
		String FFuzzy = "FFUZZY=";
		String FCLASSIC = "FCLASSIC=";
		String PFUZZY = "PFUZZY=";
		String PCLASSIC = "PCLASSIC=";
		
		//if(classicFuzzyBox.getValue().equals("Fuzzy VIKOR")) {
			for(int i = 0; i < VikorController.FuzzyFTableData.size();i++) {
				FFuzzy = FFuzzy+fTableData.get(i).getName()+";"+fTableData.get(i).getDiscription()+";"+fTableData.get(i).getWeigh()+";"
						+VikorController.FuzzyFTableData.get(i).getMaxmin()+"-";
			}
			for(int i = 0; i < VikorController.FuzzyPTableData.size();i++) {
				for(int j = 0; j < VikorController.FuzzyPTableData.get(i).size();j++) {
					PFUZZY = PFUZZY + VikorController.FuzzyPTableData.get(i).get(j).DataforTable()+";";
				}
				PFUZZY = PFUZZY+"-";
			}
		
		FFuzzy = FFuzzy+"\n";
		PFUZZY = PFUZZY+"\n";
		//if(classicFuzzyBox.getValue().equals("Classic VIKOR")) {
			for(int i = 0; i < VikorController.OriginalFTableData.size();i++) {
				FCLASSIC = FCLASSIC+fTableData.get(i).getName()+";"+fTableData.get(i).getDiscription()+";"+fTableData.get(i).getWeigh()+";"
						+VikorController.OriginalFTableData.get(i).getMaxmin()+"-";
			}
			for(int i = 0; i < VikorController.OriginalPTableData.size();i++) {
				for(int j = 0; j < VikorController.OriginalPTableData.get(i).size();j++) {
					PCLASSIC = PCLASSIC + VikorController.OriginalPTableData.get(i).get(j)+";";
				}
				PCLASSIC = PCLASSIC+"-";
			//}
			
		}
		PCLASSIC = PCLASSIC+"\n";
		FCLASSIC = FCLASSIC+"\n";
		
		
		try {
			FileWriter writer = new FileWriter(name, false);
				writer.write(mode+System.getProperty("line.separator")+
						Sets+System.getProperty("line.separator")+
						FFuzzy+System.getProperty("line.separator")+
						FCLASSIC+System.getProperty("line.separator")+
						PFUZZY+System.getProperty("line.separator")+
						PCLASSIC+System.getProperty("line.separator"));
				writer.flush();
				writer.close();
				Alerts a = new Alerts();
				a.success();
			} catch (IOException e) {
				 Alerts a = new Alerts();
				 a.Errosave();
			}
            // запись по символам
           // writer.append('\n');
           // writer.append('E');
             
           
	}   
}
	
//Alerts a = new Alerts();
// a.Errosave();
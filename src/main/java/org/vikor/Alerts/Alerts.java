package org.vikor.Alerts;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class Alerts {

	public void ErrorData() {
		Alert alert = new Alert(AlertType.WARNING);
        alert.setHeaderText("Ошибка в данных:");
        alert.setContentText("Проверьте корректность введённых данных");
 
        alert.showAndWait();
	}
	  public void showConfirmation() {
		  
	      Alert alert = new Alert(AlertType.CONFIRMATION);
	      alert.setHeaderText("Выход");
	      alert.setContentText("Вы уверены?");
	 
	      // option != null.
	      Optional<ButtonType> option = alert.showAndWait();
	 
	      if (option.get() == null) {
	        
	      } else if (option.get() == ButtonType.OK) {
	         System.exit(0);
	      } else if (option.get() == ButtonType.CANCEL) {
	        
	      } else {
	        
	      }
	   }
	  public void Erroropen() {
			Alert alert = new Alert(AlertType.ERROR);
	        alert.setHeaderText("Ошибка при открытии:");
	        alert.setContentText("Проверьте корректность структуры файла");
	 
	        alert.showAndWait();
		}
	  public void Errosave() {
			Alert alert = new Alert(AlertType.ERROR);
	        alert.setHeaderText("Ошибка при сохранении:");
	        alert.setContentText("Невозможно сохранить");
	 
	        alert.showAndWait();
		}
	  
}

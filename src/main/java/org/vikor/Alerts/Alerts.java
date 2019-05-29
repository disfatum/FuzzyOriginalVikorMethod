package org.vikor.Alerts;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class Alerts {

	public void ErrorData() {
		Alert alert = new Alert(AlertType.WARNING);
        alert.setHeaderText("������ � ������:");
        alert.setContentText("��������� ������������ �������� ������");
 
        alert.showAndWait();
	}
	  public void showConfirmation() {
		  
	      Alert alert = new Alert(AlertType.CONFIRMATION);
	      alert.setHeaderText("�����");
	      alert.setContentText("�� �������?");
	 
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
	        alert.setHeaderText("������ ��� ��������:");
	        alert.setContentText("��������� ������������ ��������� �����");
	 
	        alert.showAndWait();
		}
	  public void Errosave() {
			Alert alert = new Alert(AlertType.ERROR);
	        alert.setHeaderText("������ ��� ����������:");
	        alert.setContentText("���������� ���������");
	 
	        alert.showAndWait();
		}
	  
}

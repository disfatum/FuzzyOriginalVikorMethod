package org.vikor.Views;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Ptableprop extends Application{
	static Stage primaryStage1;
	@Override
	public void start(Stage primaryStage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/PtableProperty.fxml"));
		primaryStage.setTitle("");
		primaryStage.initModality(Modality.WINDOW_MODAL);
		 
         // Specifies the owner Window (parent) for new window
		primaryStage.initOwner(Vikor.getPrimaryStage());
		primaryStage.initStyle(StageStyle.UTILITY);
		primaryStage.setScene(new Scene(root, 424, 300));
		primaryStage.show();
		primaryStage1 = primaryStage;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	public static Stage getPrimaryStage() {
		return primaryStage1;
		
	}
}

package view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GameView{


	
	public GameView(Stage primaryStage){
		// TODO Auto-generated method stub
		BorderPane border = new BorderPane();
		GridPane grid = new GridPane();
		grid.setMinSize(400, 200);
		grid.setPadding(new Insets(10,10,10,10));
		grid.setVgap(5);
		grid.setHgap(5);
		
		grid.setAlignment(Pos.CENTER);
		grid.setGridLinesVisible(true);
		
		border.setCenter(grid);
		
		Scene scene = new Scene(border, 400, 400);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

}

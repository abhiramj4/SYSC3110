package controller;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import board.Board;
import controller.LevelCreator;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Game2 extends Application{
	
	private static final int HEIGHT = 700;
	private static final int WIDTH = 1200;	
	private Stage primaryStage;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		BorderPane root = new BorderPane();
		
		EventHandler<ActionEvent> sharedHandler = new buttonEvent(this.primaryStage);
		
		VBox menubuttons = new VBox();	
		
		Button aboutbutton = new Button("ABOUT");
		aboutbutton.setMinSize(120, 50);
		aboutbutton.setOnAction(sharedHandler);
		
		Button playbutton = new Button("PLAY");
		playbutton.setMinSize(120, 50);
		playbutton.setOnAction(sharedHandler);
		
		Button controlbutton = new Button("CONTROLS");
		controlbutton.setMinSize(120, 50);
		controlbutton.setOnAction(sharedHandler);
		
		Button buildbutton = new Button("LEVEL BUILDER");
		buildbutton.setMinSize(120, 50);
		buildbutton.setOnAction(sharedHandler);
		
		menubuttons.getChildren().addAll(aboutbutton,playbutton, controlbutton, buildbutton );
		
		VBox.setMargin(aboutbutton, new Insets(0, 0, 10, 10));
		VBox.setMargin(playbutton, new Insets(0, 0, 10, 10));
		VBox.setMargin(controlbutton, new Insets(0, 0, 10, 10));
		VBox.setMargin(buildbutton, new Insets(0, 0, 10, 10));
		
		BorderPane.setMargin(menubuttons, new Insets(250, 10, 10, 540));
		
		
		root.setCenter(menubuttons);
		Scene scene = new Scene(root, WIDTH, HEIGHT);
		
		File fr;
		fr = new File("resources/images/other/menuback.jpg");

		try {
			URL url = fr.toURI().toURL();
			Image image = new Image(url.toString());
			BackgroundImage backimage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, 
					BackgroundPosition.DEFAULT, new BackgroundSize(WIDTH, HEIGHT, false, false, false, false));	
			root.setBackground(new Background(backimage));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		Board mainboard = new Board();
//		root.setCenter(mainboard);
//		BorderPane.setMargin(mainboard, new Insets(10, 10, 10, 10));

		primaryStage.setTitle("PLANTS VS ZOMBIES: THE BOOTLEG EDITION");
		primaryStage.setScene(scene);
		primaryStage.show();

	}
	
	
	
	public static void main (String args[]) {
		launch(args);
	}
}

class buttonEvent implements EventHandler<ActionEvent> {

	private Stage primaryStage;
	public buttonEvent(Stage stage) {
		this.primaryStage = stage;
	}
    @Override
    public void handle(ActionEvent event) {
        
        Object source = event.getSource();
        Button clickedButton = (Button)source;
        String button = clickedButton.getText();
        
        if(button.equals("PLAY")) {
        	Platform.setImplicitExit(false);
        	Game g = new Game();
        	this.primaryStage.setScene(null);
        } else {
        	LevelCreator lvlCreate = new LevelCreator();
        	this.primaryStage.setScene(lvlCreate.getLevelCreatorScene());
        	
        }
    }
    
}

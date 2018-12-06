package controller;

import javax.swing.JTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.control.Label;

public class LevelCreator {
	private static final int HEIGHT = 700;
	private static final int WIDTH = 1200;
	
	public LevelCreator() {
		
		
	}
	
	public Scene getLevelCreatorScene() {
		//Region spacer = new Region();
		//HBox.setHgrow(spacer, Priority.ALWAYS);
		//VBox.setVgrow(spacer, Priority.ALWAYS);
		Pane root =  new VBox();  
        Pane plantPane = new HBox();
        Pane zombiePane = new HBox();
        Pane numZomb = new HBox();
        Pane numRound = new HBox();
        
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        root.getChildren().addAll(plantPane, zombiePane, numZomb, numRound);
        
        ObservableList<String> options = 
    	    FXCollections.observableArrayList(
    	        "Option 1",
    	        "Option 2",
    	        "Option 3"
    	    );
    	final ComboBox<String> comboBox = new ComboBox<String>(options);
    	Text plantText = new Text("Hello World!");
    	//plantText.setEditable(false);
    	plantPane.getChildren().addAll(this.makeLabel("Plants"), comboBox, plantText);
        	
    	ObservableList<String> options2 = 
    	    FXCollections.observableArrayList(
    	        "BaseZombie",
    	        "ConeHeadZombie",
    	        "FlagZombie"
    	    );
    	final ComboBox<String> comboBox2 = new ComboBox<String>(options2);
    	Text zombText = new Text("These are Zombies!");
    	zombiePane.getChildren().addAll(this.makeLabel("Zombies"), comboBox2, zombText);
        
    	ObservableList<Integer> numsHund = FXCollections.observableArrayList(); 
    	for(int i = 1; i <= 100; i++) {
    		numsHund.add(i);
    	}
    	final ComboBox<Integer> zombs = new ComboBox<Integer>(numsHund);
    	numZomb.getChildren().addAll(this.makeLabel("Number of Zombies"), zombs);
    	
    	ObservableList<Integer> numsFifty = FXCollections.observableArrayList(); 
    	for(int i = 1; i <= 50; i++) {
    		numsFifty.add(i);
    	}
    	final ComboBox<Integer> rounds = new ComboBox<Integer>(numsFifty);
    	numRound.getChildren().addAll(this.makeLabel("Number of Rounds"), rounds);
    	
		return scene;
	}
	
	private Label makeLabel(String name) {
		Label label = new Label(name);
		label.setMinSize(5.0, 5.0);
		//label.setMaxSize(5.0, 5.0);
		return new Label(name);
	}
}

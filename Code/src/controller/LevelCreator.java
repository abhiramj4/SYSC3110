package controller;

import javax.swing.JTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.control.ChoiceBox;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class LevelCreator{
	private static final int HEIGHT = 700;
	private static final int WIDTH = 1200;
	private Menu menu;
	
	private int nRounds = -1;
	private int nZomb = -1;
	private ArrayList<String> plantChoice = new ArrayList<String>();
	private ArrayList<String> zombChoice = new ArrayList<String>();
	
	public LevelCreator(Menu menu) {
		this.menu = menu;
		
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
        Pane buttonPane = new HBox();
        
        Button menuButton = new Button("Menu");
        menuButton.setMinSize(120, 50);
		menuButton.setOnAction(click2 -> {
			try {
				menu.menuSet();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
		Button saveButton = new Button("Save");
		saveButton.setMinSize(120,50);
		saveButton.setOnAction(click2 ->{
			try {
				this.writeToJSON();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		});
		
		buttonPane.getChildren().addAll(menuButton, saveButton);
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        root.getChildren().addAll(plantPane, zombiePane, numZomb, numRound, buttonPane);
        
        
        ObservableList<String> options = 
    	    FXCollections.observableArrayList(
    	        "Sunflower",
    	        "Peashooter",
    	        "CherryBomb", 
    	        "Wallnut"
    	    );
    	final ComboBox<String> plantBox = new ComboBox<String>(options);
    	Text plantText = new Text("");
    	plantBox.setOnAction(click-> {
    		String choice = plantBox.getSelectionModel().getSelectedItem();
    		if(plantChoice.contains(choice)) {
    			plantChoice.remove(choice);
    		}else {
    			plantChoice.add(choice);
    		}
    		plantText.setText(""); //so that it only prints the current list and not prev + curr list
    		for(String s : plantChoice) {
    			plantText.setText(plantText.getText() + s);  //prints list of plants
    		}
    		
    	});
    	plantPane.getChildren().addAll(this.makeLabel("Plants"), plantBox, plantText);
        	
    	ObservableList<String> options2 = 
    	    FXCollections.observableArrayList(
    	        "BaseZombie",
    	        "ConeHeadZombie",
    	        "FlagZombie"
    	    );
    	final ComboBox<String> zombBox = new ComboBox<String>(options2);
    	Text zombText = new Text("");
    	zombBox.setOnAction(click-> {
    		String choice = zombBox.getSelectionModel().getSelectedItem();
    		if(zombChoice.contains(choice)) {
    			zombChoice.remove(choice);
    		}else {
    			zombChoice.add(choice);
    		}
    		zombText.setText(""); //so that it only prints the current list and not prev + curr list
    		for(String s : zombChoice) {
    			zombText.setText(zombText.getText() + s);  //prints list of plants
    		}
    		
    	});
    	zombiePane.getChildren().addAll(this.makeLabel("Zombies"), zombBox, zombText);
        
    	ObservableList<Integer> numsHund = FXCollections.observableArrayList(); 
    	for(int i = 1; i <= 100; i++) {
    		numsHund.add(i);
    	}
    	final ChoiceBox<Integer> zombs = new ChoiceBox<Integer>(numsHund);
    	zombs.setTooltip(new Tooltip("Choose the number of zombies in the game"));
    	zombs.setOnAction(click -> {
    		nZomb = zombs.getSelectionModel().getSelectedItem();
    	});
    	numZomb.getChildren().addAll(this.makeLabel("Number of Zombies"), zombs);
    	
    	ObservableList<Integer> numsFifty = FXCollections.observableArrayList(); 
    	for(int i = 1; i <= 50; i++) {
    		numsFifty.add(i);
    	}
    	final ChoiceBox<Integer> rounds = new ChoiceBox<Integer>(numsFifty);
    	rounds.setTooltip(new Tooltip("Choose the number of rounds in the game"));
    	rounds.setOnAction(click -> {
    		nRounds = rounds.getSelectionModel().getSelectedItem();
    	});
    	numRound.getChildren().addAll(this.makeLabel("Number of Rounds"), rounds);
    	
		return scene;
	}
	
	private Label makeLabel(String name) {
		Label label = new Label(name);
		label.setMinSize(5.0, 5.0);
		//label.setMaxSize(5.0, 5.0);
		return new Label(name);
	}
	
	public void writeToJSON() throws Exception{
		JSONObject obj = new JSONObject();
		
		//random unused level number
		obj.put("levelNum", 24);
		
		if(this.nRounds < 0) {
			throw new Exception("Number of Rounds has not been selected yet!");
		}
		obj.put("rounds", nRounds);
		
		if(this.nZomb < 0) {
			throw new Exception("Number of Rounds has not been selected yet!");
		}
		obj.put("numZombies", nZomb);
		
		JSONArray plantArr = new JSONArray();
		for(String s : this.plantChoice) {
			plantArr.add(s);
		}
		
		obj.put("plants", plantArr);
		
		JSONArray zombArr = new JSONArray();
		for(String s : this.zombChoice) {
			zombArr.add(s);
		}
		
		obj.put("zombies", zombArr);
		
		try (FileWriter file = new FileWriter("resources/files/test.json")) {
			file.write(obj.toJSONString());
			System.out.println("Successfully Copied JSON Object to File...");
			System.out.println("\nJSON Object: " + obj);
		}
	}
	
	public static void main(String[] args) {
		LevelCreator lc = new LevelCreator(null);
		try{
			lc.writeToJSON();
		} catch(Exception e) {
			System.out.println(e);
		}
	}
}

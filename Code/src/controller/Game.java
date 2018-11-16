package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import board.*;
import entities.Entity.EntityType;
import entities.plants.*;
import entities.zombies.*;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 * @author Liam Murphy, Sai Vikranth Desu, Abhi Santhosh
 *
 */
public class Game extends Application {

	private Board gameboard;
	private String availablePlants[];
	private int currlevel;
	private int sun;
	private List<GameListener> gameListeners;
	private HashMap<String, Integer>plantCost;

	private Level level;
	private int[] zombieSpawn;

	private int numZombies;

	private int tick;

	private static final int HEIGHT = 700;
	private static final int WIDTH = 1200;
	private int numCards;
	private PlantCard plants[]; //list of cards
	private HBox cards;
	private Button advance;
	private Label sunlabel;
	
	private PlantCard selectedCard;
	private Boolean cardSelected;

	/**
	 * Different states of the game
	 *
	 */
	public enum State {
		ABOUT, CONTROLS, PLAY, SETTINGS, PROFILES, MENU
	};

	/**
	 * Initialize the game
	 */
	public void init() {
		this.gameboard = new Board();
		this.gameListeners = new ArrayList<GameListener>();
		this.sun = 50;
		this.tick = 0;
		this.plantCost = new HashMap<String, Integer>();
		
		this.plantCost.put("Sunflower", 50);
		this.plantCost.put("Peashooter", 100);
		
		this.level = new Level(1);
		this.availablePlants = level.getPlants();
		this.currlevel = level.getLevelNum();
		this.zombieSpawn = level.getZombieSpawn();
		this.numZombies = this.zombieSpawn.length;
		
	}

	//view
	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root, WIDTH, HEIGHT);
		cards = new HBox();
		
		cardSelected = false;
		levelinit();
		
		//add action listeners to cards
		advance = new Button("NEXT TURN");
		advance.setMinSize(50, 300);

		Board mainboard = new Board();
		mainboard.setMinSize(1000, 500);
		root.setTop(cards);
		root.setLeft(advance);
		root.setCenter(mainboard);
		BorderPane.setMargin(advance, new Insets(110, 10, 10, 20));
		BorderPane.setMargin(mainboard, new Insets(10, 10, 10, 10));

		primaryStage.setTitle("PLANTS VS ZOMBIES: THE BOOTLEG EDITION");
		primaryStage.setScene(scene);
		primaryStage.show();
		mainboard.addEntity(new Sunflower(), new Coordinate(2,2));
		mainboard.addEntity(new BaseZombie(), new Coordinate(3,4));
		boardListenerInit(mainboard);
	}

	//view
	public void levelinit() {
		
		sunlabel = new Label("" + getSun());
		sunlabel.setGraphic(null);

		for (int i = 0; i < availablePlants.length; i++) {
			PlantCard temp = new PlantCard(availablePlants[i], this.plantCost.get(availablePlants[i]));
			initCardListeners(temp);
			//temp.setGraphic(new Image(Sunflower.getImagePath()));
			cards.getChildren().add(temp);
		}
		
	}

	
	public void boardListenerInit(Board board) {
		
		//add an action listener to every tile on this board
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 9; j++) {
				initTileListeners(board.getSquare(new Coordinate(j,i)), board);
				
			}
		}
		
	}
	
	
	//function to init actionlisteners to all cards
	//add an action listener for a given plant card
	public void initCardListeners(PlantCard card) {
		
		card.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				cardClick(card);
			}
			
		});
		
	}
	
	public void initTileListeners(Square square, Board board) {
		square.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				tileClick(square,board);
			}
			
		});
	}
	
	//on click for the card, hold on temporarily to type of card
	public void cardClick(PlantCard card) {
		this.selectedCard = card;
		cardSelected = true;
	}
	
	public void tileClick(Square square, Board board) {
		
		
		//create temp entity
		Plant tempPlant;
		if(!cardSelected) {
			return;
		}
		
		if(!square.isEmpty()) {
			
			Alert alert = new Alert(AlertType.INFORMATION, "There's a plant here already! ", ButtonType.OK);
			alert.showAndWait();
			
			if(alert.getResult() == ButtonType.OK) {
				return;
			}
		}
		
		if(getSun() == 0) {
			Alert alert = new Alert(AlertType.INFORMATION, "You've Run out of sun! ", ButtonType.OK);
			alert.showAndWait();
			
			if(alert.getResult() == ButtonType.OK) {
				return;
			}
		}
		
		if(this.selectedCard.getPlantname().equals("Sunflower")) {
			tempPlant = new Sunflower();
		} else {
			tempPlant = new PeaShooter();
		} 
		
		board.addEntity(tempPlant, square.getCoordinate());
		setSun(getSun() - tempPlant.getCost());
	}
	
	/**
	 * "Tick" the game forward and update everything
	 */
	public void tick() {
		tick++;
		for (int i = 0; i < gameListeners.size(); i++) {
			gameListeners.get(i).update(this, "TICK");
		}
		if (tick % 2 == 0) {
			this.sun += 25;
			zombieSpawn(this.zombieSpawn[this.numZombies - 1], new BaseZombie()); // Zombie spawn based on level info
			numZombies -= 1;

		}
	}

	/**
	 * Check if the game is over
	 */
	private void gameoverCheck() {
		for (int i = 0; i < 5; i++) {
			Coordinate curr = new Coordinate(0, i);
			
			if (!(this.gameboard.getSquare(curr).isEmpty())) {
				if (this.gameboard.getSquare(curr).getEntity().getEntityType() == EntityType.ZOMBIE) {
					GameOver();
				}				
			}
		}
	}


	/**
	 * Get how much sun the player has
	 * 
	 * @return the sun the player has
	 */
	public int getSun() {
		return sun;
	}

	/**
	 * Set how much sun the player has
	 * 
	 * @param sun the player will have
	 */
	public void setSun(int sun) {
		this.sun = sun;
	}

	private void zombieSpawn(int row, Zombie zombie) {
		Zombie spawn = zombie;
		gameListeners.add(spawn);
		getGameboard().addEntity(zombie, new Coordinate(9, row));
	}

	/**
	 * Get the game listeners of this game
	 * 
	 * @return the gamelisteners
	 */
	public List<GameListener> getGameListeners() {
		return gameListeners;
	}

	/**
	 * Set the game listeners of this game by passing a list
	 * 
	 * @param gameListeners that this game will have
	 */
	public void setGameListeners(List<GameListener> gameListeners) {
		this.gameListeners = gameListeners;
	}

	/**
	 * Get the gameboard as a Board object
	 * 
	 * @return the gameBoard
	 */
	public Board getGameboard() {
		return gameboard;
	}

	/**
	 * Set the game board by passing a gameboard
	 * 
	 * @param gameboard to be set
	 */
	public void setGameboard(Board gameboard) {
		this.gameboard = gameboard;
	}
	
	//view
	public static void main(String args[]) throws InterruptedException {
		launch(args);
	}

	/**
	 * Game over method
	 */
	public void GameOver() {
		System.out.println();
		System.out.println("Game over!! A Zombie got to your house");
	}
}
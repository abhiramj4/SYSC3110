package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import board.*;
import entities.Entity.EntityType;
import entities.plants.*;
import entities.zombies.*;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
	private PlantCard plants[];
	private HBox cards;
	private Button advance;
	private Label sunlabel;

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
		this.sun = 150;
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

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root, WIDTH, HEIGHT);
		cards = new HBox();
		

		levelinit();

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
	}

	public void levelinit() {
		
		sunlabel = new Label("" + getSun());
		sunlabel.setGraphic(null);

		for (int i = 0; i < availablePlants.length; i++) {
			PlantCard temp = new PlantCard(availablePlants[i], this.plantCost.get(availablePlants[i]));
			//temp.setGraphic(new Image(Sunflower.getImagePath()));
			cards.getChildren().add(temp);
		}
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
	 * Handle a command
	 * 
	 * @param option passed
	 */
	private void handleCommand(String option) {
		String[] words = option.split("\\W+");
		if (option.equals("nothing")) {
			tick();
		} else if (option.equals("exit")) {
			System.out.println("Exiting...");
		} else if (option.equals("advance")) {
			tick();
		} else if (words[0].equals("plant")) {
			Plant currPlant = null;

			if (words[1].equals("sunflower")) {
				currPlant = new Sunflower();
				// gameListeners.add(currPlant);
			} else if (words[1].equals("peashooter")) {
				currPlant = new PeaShooter();
			}

			if (currPlant.getCost() > this.sun) {
				System.out.println("Sorry, you don't have enough sun to purchase this plant.");
			} else {
				gameListeners.add(currPlant);
				this.sun -= currPlant.getCost();
				int x = Integer.parseInt(words[3]);
				int y = Integer.parseInt(words[4]);
				Coordinate thiscoord = new Coordinate(x, y);
				this.gameboard.addEntity(currPlant, thiscoord);
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
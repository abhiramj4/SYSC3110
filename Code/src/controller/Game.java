package controller;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import board.Board;
import board.Coordinate;
import board.PlantCard;
import board.Square;
import entities.Entity.EntityType;
import entities.plants.PeaShooter;
import entities.plants.Plant;
import entities.plants.Sunflower;
import entities.zombies.BaseZombie;
import entities.zombies.Zombie;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Game {
	private Board gameboard;
	private String availablePlants[];
	private int currlevel;
	private int sun;
	private List<GameListener> gameListeners;
	private HashMap<String, Integer> plantCost;
	private Board lastBoard; // to save last board
	private Level level;
	private int[] zombieSpawn;
	private static final String header = "PLANTS VS ZOMBIES: GAME";
	private int numZombies;
	private ArrayList<Board> gameStates;
	private int tick;

	private static final int HEIGHT = 700;
	private static final int WIDTH = 1200;
	private int numCards;
	private PlantCard plants[]; // list of cards
	private HBox cards;
	private Button advance;
	private Label sunlabel;

	private PlantCard selectedCard;
	private Boolean cardSelected;
	private boolean running = false;

	private Scene scene;

	private int mowerNum;
	private int score;
	private Menu menu;

	public enum State {
		ABOUT, CONTROLS, PLAY, SETTINGS, MENU, BUILDER
	};

	public Game(Menu menu) {
		this.menu = menu;
		init();
		gameStates = new ArrayList<Board>();
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root, WIDTH, HEIGHT);
		this.scene = scene;
		cards = new HBox();
		VBox options = new VBox();

		Button advancebutton = new Button("NEXT TURN");
		advancebutton.setMinSize(120, 50);
		this.advance = advancebutton;
		
		Button savebutton = new Button("SAVE GAME");
		savebutton.setMinSize(120, 50);

		Button menubutton = new Button("MENU");
		menubutton.setMinSize(120, 50);
		menubutton.setOnAction(click -> {
			try {
				menu.primaryStage.close();
				menu.start(new Stage());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		options.getChildren().addAll(advancebutton, savebutton, menubutton);

		Button undobutton = new Button("");
		undobutton.setMinSize(50, 50);
		undobutton.setMaxSize(60, 60);

		File fr = new File("resources/images/other/redo.jpg");

		try {
			URL url = fr.toURI().toURL();
			Image image = new Image(url.toString());
			BackgroundImage backimage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
					BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
					new BackgroundSize(50, 50, false, false, false, false));
			undobutton.setBackground(new Background(backimage));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Button redobutton = new Button();

		options.getChildren().addAll(undobutton, redobutton);

		cardSelected = false;
		levelinit();

		gameboard.setMinSize(1000, 500);
		root.setTop(cards);
		root.setLeft(options);
		root.setCenter(gameboard);
		BorderPane.setMargin(gameboard, new Insets(10, 10, 10, 10));

		this.scene = scene;

		boardListenerInit(gameboard);
		initNextRoundListener(); // init the next round button

		initLawnMower(); // set all lawn mower
		
		gameStates.add(gameboard);
		score = 0;
		mowerNum = 4;
	}

	public void init() {

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
		this.gameboard = new Board();

	}

	public void levelinit() {

		sunlabel = new Label("SUN: \n" + getSun());
		sunlabel.setGraphic(null);
		cards.getChildren().add(sunlabel);
		HBox.setMargin(sunlabel, new Insets(20, 20, 20, 20));

		for (int i = 0; i < availablePlants.length; i++) {
			PlantCard temp = new PlantCard(availablePlants[i], this.plantCost.get(availablePlants[i]));
			temp.setImage(availablePlants[i].toString());
			initCardListeners(temp);
			// temp.setGraphic(new Image(Sunflower.getImagePath()));
			cards.getChildren().add(temp);
		}
		this.levelAlert(this.currlevel);

	}

	public void runRound() {
		// call this every time a button is clicked
		this.lastBoard = this.gameboard;
		this.gameStates.add(lastBoard);
		gameoverCheck();
		tick();

	}

	/*
	 * function to init actionlisteners to all cards
	 */
	public void initCardListeners(PlantCard card) {

		card.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				cardClick(card);
			}

		});

	}

	public void boardListenerInit(Board board) {

		// add an action listener to every tile on this board
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 9; j++) {
				initTileListeners(board.getSquare(new Coordinate(j, i)), board);

			}
		}

	}

	private void levelAlert(int level) {
		Alert alert = new Alert(AlertType.INFORMATION, "Level " + currlevel, ButtonType.OK);
	}

	public void initTileListeners(Square square, Board board) {
		square.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				tileClick(square, board);
			}

		});
	}

	public void initUndoListener(Button undoButton) {
		undoButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				if(gameStates.size() > 1) {
					gameboard = gameStates.get(gameStates.size()-1); //last element
				} else {
					Alert alert = new Alert(AlertType.INFORMATION, "Can't go backwards!", ButtonType.OK,
							ButtonType.CANCEL);
					alert.showAndWait();

					if (alert.getResult() == ButtonType.OK || alert.getResult() == ButtonType.CANCEL) {
						return;
					}
				}
			}
			
		});
	}
	public void initNextRoundListener() {
		advance.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				runRound(); // update
			}

		});
	}

	/*
	 * This is what happens after the user clicks on a plant card then a tile on the
	 * board
	 */
	public void tileClick(Square square, Board board) {
		// create temp entity
		Plant tempPlant;
		if (!cardSelected) {
			return;
		}

		// Checking to see if there is already a plant/zombie occupying the space, if so
		// an alert pops up
		if (!square.isEmpty()) {

			Alert alert = new Alert(AlertType.INFORMATION, "There's an entity here already! ", ButtonType.OK);
			alert.showAndWait();

			if (alert.getResult() == ButtonType.OK) {
				return;
			}
		}

		// Checking to see if the user does not have enough sun to purchase the desired
		// plant, if so an alert pops up
		if (getSun() < selectedCard.getCost()) {
			Alert alert = new Alert(AlertType.INFORMATION, "You don't have enough sun! ", ButtonType.OK,
					ButtonType.CANCEL);
			alert.showAndWait();

			if (alert.getResult() == ButtonType.OK || alert.getResult() == ButtonType.CANCEL) {
				return;
			}
		}

		// If the user has enough sun and it is an empty tile, this following code will
		// go ahead and plant the plant.
		if (this.selectedCard.getPlantname().equals("Sunflower")) {
			tempPlant = new Sunflower();
			setSun(getSun() - tempPlant.getCost());
			sunlabel.setText("SUN: \n" + getSun());
		} else {
			tempPlant = new PeaShooter();
			setSun(getSun() - tempPlant.getCost());
			sunlabel.setText("SUN: \n" + getSun());
		}

		this.gameListeners.add(tempPlant);
		board.addEntity(tempPlant, square.getCoordinate());

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
			setSun(getSun() + 25);
		}
		if (tick % 2 == 0 && tick > 5) {
			if (numZombies > 0) {
				zombieSpawn(this.zombieSpawn[this.numZombies - 1], new BaseZombie()); //
				// Zombie spawn based on level info
				numZombies -= 1;
			}

		}
	}

	/*
	 * This method is used to spawn zombies based on level data each round
	 */
	private void zombieSpawn(int row, Zombie zombie) {
		Zombie spawn = zombie;
		gameListeners.add(spawn);
		getGameboard().addEntity(zombie, new Coordinate(9, row));

	}

	public void initLawnMower() {
		for (int j = 0; j < 5; j++) {
			gameboard.getBoard()[0][j].setLawnMower(true); // set the lawn mower
		}
	}

	public void mowOver(int y) {
		// mow over this x and y lane
		Coordinate curr;

		for (int i = 0; i < 9; i++) {

			curr = new Coordinate(i, y);
			if (!this.gameboard.getSquare(curr).isEmpty()
					&& this.gameboard.getSquare(curr).getEntity().getEntityType() == EntityType.ZOMBIE) {
				getGameboard().removeEntity(this, curr);
			}
		}
	}

	// on click for the card, hold on temporarily to type of card
	public void cardClick(PlantCard card) {
		this.selectedCard = card;
		cardSelected = true;
	}

	/**
	 * Check if the game is over
	 */
	private void gameoverCheck() {

		// Checking to see if any zombie made it to the house, this is a game over loss
		for (int i = 0; i < 5; i++) {
			Coordinate curr = new Coordinate(0, i);

			if (!(this.gameboard.getSquare(curr).isEmpty())) {
				// there's a zombie AND a lawn mower
				if (this.gameboard.getSquare(curr).getEntity().getEntityType() == EntityType.ZOMBIE) {

					if (this.gameboard.getSquare(curr).getLawnMower()) {
						this.gameboard.getSquare(curr).setLawnMower(false);
						Alert alert = new Alert(AlertType.INFORMATION, "A lawn mower passes through the lane",
								ButtonType.OK);
						alert.showAndWait();
						mowOver(curr.getY());
						mowerNum--;
						return;
					}
					GameOver(false);
					// mow over all zombies in this

				} else if (this.gameboard.getSquare(curr).getEntity().getEntityType() == EntityType.ZOMBIE) {
					GameOver(false);
				}

			}
		}

		// Checking to see if all the zombies are dead, this is a game over win
		boolean zombiepresent = false;
		for (Node child : gameboard.getChildren()) {
			Square square = (Square) child;
			if (!(square.isEmpty())) {
				if (square.getEntity() instanceof Zombie) {
					zombiepresent = true;
				}
			}
		}

		if (numZombies == 0 && (!(zombiepresent))) {
			GameOver(true);
		}
	}

	public void GameOver(boolean win) {

		// If the gameover method was called with a true value, that means the user won
		// and so the following alert is shown
		if (win) {
			score += 100;
			if (mowerNum == 4) {
				score += 50;
			} else if (mowerNum == 0) {
				score += 10;
			} else {
				score += 10;
			}
			Alert alert = new Alert(AlertType.INFORMATION, "Congratulations, you won. Your total score is " + score,
					ButtonType.OK);
			alert.showAndWait();
			advance.setDisable(true);
//			primaryStage.close();

			// If the gameover method was called with a value of false, that means the user
			// lost and so this alert is shown
		} else {
			Alert alert = new Alert(AlertType.INFORMATION, "A Zombie got to your house! \n You lose! ", ButtonType.OK);
			alert.showAndWait();
			this.advance.setDisable(true);
//			primaryStage.close();
		}

	}

	public Scene getScene() {
		return this.scene;
	}

	public static String getHeader() {
		return header;
	}

	/**
	 * Get how much sun the player has
	 * 
	 * @return the sun the player has
	 */
	public int getSun() {
		return sun;
	}

	public void setSun(int sun) {
		sunlabel.setText("Sun: " + Integer.valueOf(getSun()).toString());
	}

	public List<GameListener> getGameListeners() {
		return gameListeners;
	}

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
}

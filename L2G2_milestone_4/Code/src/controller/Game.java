package controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import board.Board;
import board.Coordinate;
import board.PlantCard;
import board.Square;
import entities.Entity.EntityType;
import entities.plants.*;
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

public class Game implements Serializable {

	private transient Board gameboard;
	private transient String availablePlants[];
	private transient int currlevel;
	private transient int sun;
	private transient List<GameListener> gameListeners;
	private transient HashMap<String, Integer> plantCost;
	private transient Board lastBoard; // to save last board
	private transient Level level;
	private transient int[] zombieSpawn;
	private transient static final String header = "PLANTS VS ZOMBIES: GAME";
	private transient int numZombies;
	private transient ArrayList<Game> gameStates;
	private transient int tick;
	private transient int numsaves;

	private transient static final int HEIGHT = 700;
	private transient static final int WIDTH = 1200;
	private transient static final String SAVEPATH = "src/savefiles";

	private transient Stack<Game> gamestates;
	private transient int numCards;
	private transient PlantCard plants[]; // list of cards
	private transient HBox cards;
	private transient Button advance;
	private transient Label sunlabel;

	private transient PlantCard selectedCard;
	private transient Boolean cardSelected;

	private transient Scene scene;

	private transient int mowerNum;
	private transient Boolean mowerUsed;
	private transient int score;
	private transient Menu menu;

	public enum State {
		ABOUT, CONTROLS, PLAY, SETTINGS, MENU, BUILDER
	};

	public Game(Menu menu, int lvl) {
		this.menu = menu;
		init(lvl);
		gameStates = new ArrayList<Game>();
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root, WIDTH, HEIGHT);
		this.scene = scene;
		cards = new HBox();
		VBox options = new VBox();

		Button advancebutton = new Button("NEXT TURN");
		advancebutton.setMinSize(120, 150);
		this.advance = advancebutton;

		advancebutton.setOnAction(click -> {
			runRound();
		});

		Button savebutton = new Button("SAVE GAME");
		savebutton.setMinSize(120, 50);
		savebutton.setOnAction(click -> {
			saveGame(this);
		});

		Button menubutton = new Button("MENU");
		menubutton.setMinSize(120, 50);
		menubutton.setOnAction(click -> {
			try {
				menu.menuSet();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		HBox undoredo = new HBox();
		Button undobutton = new Button();
		undobutton.setMinSize(75, 75);
		undobutton.setMaxSize(75, 75);

		undobutton.setOnAction(click -> {
			undo();
		});

		File fr = new File("resources/images/other/undo.jpg");

		try {
			URL url = fr.toURI().toURL();
			Image image = new Image(url.toString());
			BackgroundImage backimage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
					BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
					new BackgroundSize(75, 75, false, false, false, false));
			undobutton.setBackground(new Background(backimage));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Button redobutton = new Button();

		redobutton.setMinSize(75, 75);
		redobutton.setMaxSize(75, 75);

		redobutton.setOnAction(click -> {
			redo();
		});

		File fs = new File("resources/images/other/redo.jpg");

		try {
			URL url = fs.toURI().toURL();
			Image image = new Image(url.toString());
			BackgroundImage backimage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
					BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
					new BackgroundSize(75, 75, false, false, false, false));
			redobutton.setBackground(new Background(backimage));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		undoredo.getChildren().addAll(undobutton, redobutton);
		HBox.setMargin(undobutton, new Insets(0, 10, 0, 0));
		options.getChildren().addAll(menubutton, savebutton, advancebutton, undoredo);
		VBox.setMargin(undoredo, new Insets(10, 10, 10, 10));

		cardSelected = false;
		levelinit();

		gameboard.setMinSize(1000, 500);
		root.setTop(cards);
		root.setLeft(options);
		root.setCenter(gameboard);

		VBox.setMargin(menubutton, new Insets(20, 20, 10, 20));
		VBox.setMargin(savebutton, new Insets(10, 20, 10, 20));
		VBox.setMargin(advancebutton, new Insets(10, 20, 10, 20));

		BorderPane.setMargin(cards, new Insets(0, 0, 0, 20));
		BorderPane.setMargin(gameboard, new Insets(10, 0, 0, 0));

		this.scene = scene;

		boardListenerInit(gameboard);

		initLawnMower(); // set all lawn mower;

		gameStates.add(this);
		score = 0;
		mowerNum = 4;
		mowerUsed = false;
	}

	public void init(int lvl) {

		this.gameListeners = new ArrayList<GameListener>();
		this.sun = 50;
		this.tick = 0;
		this.plantCost = new HashMap<String, Integer>();
		this.gamestates = new Stack<Game>();

		this.plantCost.put("Sunflower", 50);
		this.plantCost.put("Peashooter", 100);
		this.plantCost.put("CherryBomb", 150);
		this.plantCost.put("Wallnut", 50);

		this.level = new Level(lvl);
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
			cards.getChildren().add(temp);
		}
		this.levelAlert(this.currlevel);
	}

	public void runRound() {
		// call this every time advance is clicked

		try {
			this.gameStates.add((Game) deepCopy(this));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Game copy = this;
		//gamestates.push(copy);
		gameoverCheck();
		tick();
	}

	private static Object deepCopy(Object object) {
		   try {
		     ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		     ObjectOutputStream outputStrm = new ObjectOutputStream(outputStream);
		     outputStrm.writeObject(object);
		     ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
		     ObjectInputStream objInputStream = new ObjectInputStream(inputStream);
		     return objInputStream.readObject();
		   }
		   catch (Exception e) {
		     e.printStackTrace();
		     return null;
		   }
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

	public void undo() {

		// TODO Auto-generated method stub

//		if (gameStates.size() > 0) {
//			Game temp = gameStates.get(gameStates.size() - 1); // last element
//			this.scene = temp.getScene();
//		} else {
//			Alert alert = new Alert(AlertType.INFORMATION, "Can't go backwards!", ButtonType.OK, ButtonType.CANCEL);
//			alert.showAndWait();
//
//			if (alert.getResult() == ButtonType.OK || alert.getResult() == ButtonType.CANCEL) {
//				return;
//			}
//		}

		if (gamestates.size() > 0) {
			//Game prev = gamestates.pop();
			//this.menu.setGame(prev);
			this.scene = this.gameStates.get(gameStates.size()-1).getScene();
		} else {
			Alert alert = new Alert(AlertType.INFORMATION, "Can't go back!", ButtonType.OK, ButtonType.CANCEL);
			alert.showAndWait();

			if (alert.getResult() == ButtonType.OK || alert.getResult() == ButtonType.CANCEL) {
				return;
			}
		}
	}

	private void saveGame(Game game) {
		if (tick == 0) {
			Alert alert = new Alert(AlertType.ERROR, "Nothing to save though!");
			alert.show();
		}
		try {

			FileOutputStream savefile = new FileOutputStream(SAVEPATH + "/save" + this.numsaves);
			ObjectOutputStream saveobject = new ObjectOutputStream(savefile);
			saveobject.writeObject(game);
			saveobject.close();
			Alert alert = new Alert(AlertType.INFORMATION, "Game Saved");
			numsaves++;
		} catch (Exception e) {
			e.printStackTrace();
			Alert alert = new Alert(AlertType.ERROR, "Error saving!");
		}
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
		String tname = this.selectedCard.getPlantname();
		if (tname.equals("Sunflower")) {
			tempPlant = new Sunflower();
			setSun(getSun() - tempPlant.getCost());
			sunlabel.setText("SUN: \n" + getSun());
		} else if(tname.equals("Peashooter")) {
			tempPlant = new PeaShooter();
			setSun(getSun() - tempPlant.getCost());
			sunlabel.setText("SUN: \n" + getSun());
		}else if(tname.equals("CherryBomb")) {
			tempPlant = new CherryBomb();
			setSun(getSun() - tempPlant.getCost());
			sunlabel.setText("SUN: \n" + getSun());
		}else {
			tempPlant = new Wallnut();
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
				mowerUsed = true;
			}
		}
	}

	// on click for the card, hold on temporarily to type of card
	public void cardClick(PlantCard card) {
		this.selectedCard = card;
		cardSelected = true;
	}

	public void redo() {

		// TODO Auto-generated method stub

		if (gameStates.indexOf(this) == gameStates.size() - 1) {
			Alert alert = new Alert(AlertType.INFORMATION, "Can't go forwards!", ButtonType.OK, ButtonType.CANCEL);
			alert.showAndWait();

			if (alert.getResult() == ButtonType.OK || alert.getResult() == ButtonType.CANCEL) {
				return;
			}
		} // at the current round

		// if our array is greater than 1 then go forward
		else {
			Game temp = gameStates.get(gameStates.indexOf(this) + 1);
			this.scene = temp.getScene();
		}
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

					if (this.gameboard.getSquare(curr).getLawnMower() && mowerUsed == false) {
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
			// primaryStage.close();

			// If the gameover method was called with a value of false, that means the user
			// lost and so this alert is shown
		} else {
			Alert alert = new Alert(AlertType.INFORMATION, "A Zombie got to your house! \n You lose! ", ButtonType.OK);
			alert.showAndWait();
			this.advance.setDisable(true);
			// primaryStage.close();
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
		return this.sun;
	}

	public void setSun(int sun) {
		this.sun = sun;
		sunlabel.setText("Sun: " + Integer.valueOf(sun).toString());
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

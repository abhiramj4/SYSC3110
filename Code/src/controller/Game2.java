package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import board.Board;
import board.PlantCard;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Game2 {
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

	public enum State {
		ABOUT, CONTROLS, PLAY, SETTINGS, MENU, BUILDER
	};

	public Game2() {
		init();
		
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root, WIDTH, HEIGHT);
		this.scene = scene;
		cards = new HBox();

		cardSelected = false;
//		levelinit();

		// add action listeners to cards
		advance = new Button("NEXT TURN");
		advance.setMinSize(50, 300);

		Board mainboard = new Board();
		mainboard.setMinSize(1000, 500);
		root.setTop(cards);
		root.setLeft(advance);
		root.setCenter(mainboard);
		BorderPane.setMargin(advance, new Insets(110, 10, 10, 20));
		BorderPane.setMargin(mainboard, new Insets(10, 10, 10, 10));
		
		this.scene = scene;

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

	}

	public Scene getScene() {
		return this.scene;
	}

	public static String getHeader() {
		return header;
	}
}

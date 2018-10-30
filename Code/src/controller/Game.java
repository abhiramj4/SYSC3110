package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import board.*;
import entities.Entity.EntityType;
import entities.plants.*;
import entities.zombies.*;

public class Game implements Runnable {

	private Board gameboard;
	private int plantcount;
	private String availablePlants[];
	private int currlevel;
	private int sun;
	private List<GameListener> gameListeners;

	private Thread thread;
	private int tick;

	private boolean running = false;

	/**
	 * Diffrent states of the game
	 * 
	 *
	 */
	public enum State {
		ABOUT, CONTROLS, PLAY, SETTINGS, PROFILES, MENU
	};

	/**
	 * Initialize the game
	 */
	public void init() {
		this.currlevel = 1;
		this.gameboard = new Board();
		this.sun = 50;
		this.tick = 0;
		this.plantcount = 2;
		availablePlants = new String[plantcount];
		this.gameListeners = new ArrayList<GameListener>();
		availablePlants[0] = "Sunflower";
		availablePlants[1] = "PeaShooter";
	}

	/**
	 * Start the game
	 */
	private void start() {
		if (running) {
			return;
		}

		running = true;
		thread = new Thread(this);
		thread.start();
	}

	/**
	 * Stop the game
	 */
	private synchronized void stop() {
		if (!running)
			return;

		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(1);
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
		}
	}

	/**
	 * Run the game
	 */
	@Override
	public void run() {
		init();
		/*
		 * System.out.println("Welcome to Plants Vs. Zombies: The Bootleg Edition"); try
		 * { TimeUnit.SECONDS.sleep(4); } catch (InterruptedException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 * 
		 * System.out.
		 * println("This is a turn based game, not real time. Each 'turn', you can call multiple commands as to what you want to do."
		 * ); try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { //
		 * TODO Auto-generated catch block e.printStackTrace(); }
		 * 
		 * System.out.
		 * println("To plant, follow this command: Plant <PLANTTYPE> at (<x>, <y>). It is a grid system with 0 to 8 for x, 0 to 4 for y."
		 * ); try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { //
		 * TODO Auto-generated catch block e.printStackTrace(); }
		 * 
		 * System.out.
		 * println("To dig up an existing plant, follow this command: Dig at (<x>, <y>)"
		 * ); try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { //
		 * TODO Auto-generated catch block e.printStackTrace(); }
		 * 
		 * System.out.println("Enjoy your game, and good luck."); try {
		 * TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 * 
		 * System.out.println(); System.out.println();
		 * 
		 * System.out.println("Welcome to LEVEL " + this.currlevel); try {
		 * TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } System.out.println();
		 */

		zombieSpawn(2, new BaseZombie()); // Zombie spawn based on level info

		while (running) {
			System.out.println("TURN " + (this.tick + 1));
			System.out.println();
			System.out.println("GAME BOARD:");
			System.out.println();
			System.out.println(this.gameboard.toString());
			System.out.println("You have: " + this.sun + " sun. These are the available plants for purchase: "
					+ availablePlants[0] + ", " + availablePlants[1] + ".");
			gameoverCheck();
			System.out.println("What would you like to do?");
			Scanner scanner = new Scanner(System.in);
			String option = scanner.nextLine().toLowerCase();
			handleCommand(option);
			System.out.println();
			System.out.println();
			System.out.println();
		}
	}

	/**
	 * Check if the game is over
	 */
	private void gameoverCheck() {
		for (int i = 0; i < 5; i++) {
			Coordinate curr = new Coordinate(0, i);
			if (!(this.gameboard.getSquare(curr).isEmpty())) {
				if (this.gameboard.getSquare(curr).getEntity().getClass().getSuperclass().getName().toLowerCase()
						.contains("zombie"))
					;
			}
		}
	}

	/**
	 * Handle a command
	 * @param option passed 
	 */
	private void handleCommand(String option) {
		if (option.equals("nothing")) {
			tick();
		} else if (option.equals("exit")) {
			System.out.println("Exiting...");
			stop();
		} else {
			Plant currPlant = null;
			// plant <TYPE> at (x, y)
			String[] words = option.split("\\W+");
			
			if(words.length < 4 || words.length > 5) {
				System.out.println("Invalid command, try again!");
				return;
			}
			
			if (words[0].equals("plant")) {
				if (words[1].equals("sunflower")) {
					currPlant = new Sunflower();
					//gameListeners.add(currPlant);
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
					tick();
				}
			}
		}
	}

	/**
	 * Get how much sun the player has
	 * @return the sun the player has
	 */
	public int getSun() {
		return sun;
	}

	/**
	 * Set how much sun the player has
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
	 * @return the gamelisteners
	 */
	public List<GameListener> getGameListeners() {
		return gameListeners;
	}

	/**
	 * Set the game listeners of this game by passing a list
	 * @param gameListeners that this game will have
	 */
	public void setGameListeners(List<GameListener> gameListeners) {
		this.gameListeners = gameListeners;
	}

	/**
	 * Get the gameboard as a Board object
	 * @return the gameBoard
	 */
	public Board getGameboard() {
		return gameboard;
	}

	/**
	 * Set the game board by passing a gameboard
	 * @param gameboard to be set 
	 */
	public void setGameboard(Board gameboard) {
		this.gameboard = gameboard;
	}

	public static void main(String args[]) throws InterruptedException {
		boolean ismenu = true;
		while (ismenu) {
			System.out.println("Welcome to Plants Vs. Zombies. Please select a menu option:");
			System.out.println("ABOUT    PLAY    CONTROLS");
			Scanner scanner = new Scanner(System.in);
			String result = scanner.nextLine().toLowerCase();
			switch (result) {
			case ("about"): {
				TimeUnit.SECONDS.sleep(1);
				System.out.println();
				break;
			}
			case ("play"): {
				System.out.println("Loading...");
				ismenu = false;
				TimeUnit.SECONDS.sleep(2);
				Game game = new Game();
				game.start();
				break;
			}
			case ("controls"): {
				TimeUnit.SECONDS.sleep(1);
				System.out.println();
				break;
			}
			}
		}
	}

	/**
	 * Game over method
	 */
	public void GameOver() {
		System.out.println("Game over!! A Zombie got to your house");
		stop();
	}
}
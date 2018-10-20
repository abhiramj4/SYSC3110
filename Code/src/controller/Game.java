package controller;

import java.util.List;
import java.util.Observable;
import java.util.Scanner;

import board.*;
import entities.plants.*;
import entities.zombies.*;

public class Game extends Observable implements Runnable {

	private Board gameboard;
	private int plantcount;
	private String availablePlants[];
	private int currlevel;
	private int sun;

	private Thread thread;
	private int tick;

	private boolean running = false;

	public enum State {
		ABOUT, CONTROLS, PLAY, SETTINGS, PROFILES, MENU
	};

	public void init() {
		this.currlevel = 0;
		this.gameboard = new Board();
		this.sun = 50;
		this.tick = 0;
		this.plantcount = 2;
		availablePlants = new String[plantcount];
		availablePlants[0] = "Sunflower";
		availablePlants[1] = "PeaShooter";
	}

	private void start() {
		if (running) {
			return;
		}

		running = true;
		thread = new Thread(this);
		thread.start();
	}

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

	public void tick() {
		tick++;
		setChanged();
		notifyObservers();
		if (tick % 2 == 0) {
//			this.sun += 25;
		}
	}

	@Override
	public void run() {
		init();
		long timer = System.currentTimeMillis();
		int tick = 0;

		while (running) {
//			zombieSpawn(); // Zombie spawn based on level info
			System.out.println("TURN " + (this.tick + 1));
			System.out.println();
			System.out.println("GAME BOARD:");
			System.out.println();
			System.out.println(this.gameboard.toString());
			System.out.println("You have: " + this.sun + " sun. These are the available plants for purchase: "
					+ availablePlants[0] + ", " + availablePlants[1] + ".");
			System.out.println("What would you like to do?");
			Scanner scanner = new Scanner(System.in);
			String option = scanner.nextLine();
			handle(option);
			System.out.println();
			System.out.println();
			System.out.println();
		}
	}

	private void handle(String option) {
		if (option.equals("Nothing") || option.equals("nothing")) {
			tick();
		} else if (option.equals("Exit") || option.equals("exit")) {
			System.out.println("Exiting...");
			stop();
		} else {
			Plant currPlant = null;
			String[] words = option.split("\\W+");
			if (words[0].equals("Plant") || words[0].equals("plant")) {
				if (words[1].equals("Sunflower") || words[1].equals("sunflower")) {
					currPlant = new Sunflower();
					addObserver(currPlant);
				} else if (words[1].equals("Peashooter") || words[1].equals("peashooter")) {
					currPlant = new PeaShooter();
					addObserver(currPlant);
				}
				if (currPlant.getCost() > this.sun) {
					System.out.println("Sorry, you don't have enough sun to purchase this plant.");
				} else {
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

	public int getSun() {
		return sun;
	}

	public void setSun(int sun) {
		this.sun = sun;
	}

	private void zombieSpawn(int rows[], Zombie zombies[]) {
		for (int i = 0; i < rows.length; i++) {
			Coordinate thiscoord = new Coordinate(9, rows[i]);
			this.gameboard.addEntity(zombies[i], thiscoord);
		}
	}

	public static void main(String args[]) {
//		System.out.println("Welcome to Plants Vs. Zombies. Please select a menu option:");
//		System.out.println("ABOUT    PLAY    CONTROLS");
//		Scanner scanner = new Scanner(System.in);

		Game game = new Game();
		game.start();
	}
}

//"This is the best game you will ever fucking play you twat.\nMade by: Sai Vikranth, Liam, Abhi, and Everett";

//public Menu() {
//	System.out.println("Welcome to Plants Vs. Zombies. Please select a menu option:");
//	System.out.println("ABOUT    PLAY    CONTROLS");
//}
//
//public void setState(String option) {
//	if (option == "MENU" || option == "Menu" || option == "menu") {
//		this.setChanged();
//		this.notifyObservers(State.MENU);
//	} else if (option == "PLAY" || option == "Play" || option == "play") {
//		this.setChanged();
//		this.notifyObservers(State.PLAY);
//	} else if (option == "CONTROLS" || option == "Controls" || option == "controls") {
//		this.setChanged();
//		this.notifyObservers(State.CONTROLS);
//	} else if (option.equals("ABOUT") || option == "About" || option == "about") {
//		this.setChanged();
//		this.notifyObservers(State.ABOUT);
//	} else {
//		this.setChanged();
//		this.notifyObservers(State.MENU);
//	}
//}
package controller;

import java.util.Observable;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import board.*;
import entities.plants.*;
import entities.zombies.*;

public class Game extends Observable implements Runnable {

	private Board gameboard;
	private int plantcount;
	private String availablePlants[];
	private int currlevel;
	private int sun;

	public Board getGameboard() {
		return gameboard;
	}

	public void setGameboard(Board gameboard) {
		this.gameboard = gameboard;
	}

	private Thread thread;
	private int tick;

	private boolean running = false;

	public enum State {
		ABOUT, CONTROLS, PLAY, SETTINGS, PROFILES, MENU
	};

	public void init() {
		this.currlevel = 1;
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
		
		System.out.println("Welcome to Plants Vs. Zombies: The Bootleg Edition");
		try {
			TimeUnit.SECONDS.sleep(4);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("This is a turn based game, not real time. Each 'turn', you can call multiple commands as to what you want to do.");
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("To plant, follow this command: Plant <PLANTTYPE> at (<x>, <y>). It is a grid system with 0 to 8 for x, 0 to 4 for y.");
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("To dig up an existing plant, follow this command: Dig at (<x>, <y>)");
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Enjoy your game, and good luck.");
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(); System.out.println();
		
		System.out.println("Welcome to LEVEL " + this.currlevel);
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(); 

		while (running) {
			zombieSpawn(2, new BaseZombie()); // Zombie spawn based on level info
						
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
			handleCommand(option);
			System.out.println();
			System.out.println();
			System.out.println();
		}
	}

	private void handleCommand(String option) {
		if (option.equals("Nothing") || option.equals("nothing")) {
			tick();
		} else if (option.equals("Exit") || option.equals("exit")) {
			System.out.println("Exiting...");
			stop();
		} else {
			Plant currPlant = null;
			String[] words = option.split("\\W+");
			
			
			//check for empty command, 
			
			if(words.length != 5) {
				System.out.println("Need to enter a correct command!");
				return;
			} else {
			
			
			if (words[0].toLowerCase().equals("plant")) {
				if (words[1].toLowerCase().equals("sunflower")) {
					currPlant = new Sunflower();
					addObserver(currPlant);
				} else if (words[1].toLowerCase().equals("peashooter")) {
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
	
	private void zombieSpawn(int row, Zombie zombie) {
		Zombie spawn = zombie;
		addObserver(spawn);
		getGameboard().addEntity(zombie, new Coordinate (9, row));
	}

	public static void main(String args[]) throws InterruptedException {		
		boolean ismenu = true;
		
		final String casePlay = "play";
		final String caseAbout = "about";
		final String caseControls = "controls";
		
		while (ismenu) {
			System.out.println("Welcome to Plants Vs. Zombies. Please select a menu option:");
			System.out.println("ABOUT    PLAY    CONTROLS");
			Scanner scanner = new Scanner(System.in);
			String result = scanner.nextLine().toLowerCase();
			switch (result) {
			case (caseAbout): {
				TimeUnit.SECONDS.sleep(1);
				System.out.println();
				break;
			} case (casePlay): {
				System.out.println("Loading...");
				ismenu = false;
				TimeUnit.SECONDS.sleep(2);
				Game game = new Game();
				game.start();
				break;
			} case (caseControls): {
				TimeUnit.SECONDS.sleep(1);
				System.out.println();
				break;
			}
				
			}
			
			
		}
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
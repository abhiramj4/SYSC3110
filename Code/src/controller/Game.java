package controller;
import java.util.List;
import java.util.Scanner;

import board.*;
import entities.plants.*;
import entities.zombies.*;


public class Game implements Runnable{
	
	private Board gameboard;
	private int plantcount;
	private String availablePlants[];
	private int currlevel;
	private int sun;
	
	private Thread thread; 
	private int tick;
	
	private boolean running = false;
	public enum State {ABOUT, CONTROLS, PLAY, SETTINGS, PROFILES, MENU};

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
		thread = new Thread (this);
		thread.start ();	
	}
	
	private synchronized void stop ()
	{
		if (!running)
			return;

		running = false;
		try
		{
			thread.join ();
		}
		catch (InterruptedException e)
		{
			e.printStackTrace ();
		}
		System.exit (1);
	}
	
	public void tick() {
		tick++;
	}

	@Override
	public void run() {
		init();
		long timer = System.currentTimeMillis ();
		int tick = 0;
		
		while(running) {
			System.out.println("You have: " + this.sun + " Sun. These are the available plants for purchase: " + availablePlants[0] + ", " + availablePlants[1] +".");
			System.out.println("What would you like to do?");
			Scanner scanner = new Scanner(System.in);
			String option = scanner.nextLine();
			handle(option);
		}
	}
	
	private void handle(String option) {
		if (option.equals("Nothing")) {
			tick();
		} else if (option.equals("Exit")) {
			System.out.println("Exiting...");
			stop();
		}
		
	}

	public static void main (String args[]) {
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
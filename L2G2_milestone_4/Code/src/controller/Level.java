package controller;

import java.io.FileReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.lang.IllegalArgumentException;
import java.util.Random;

import entities.plants.Plant;

/*
 * @author Liam Murphy
 *The class that reads the level json file and converts it into the data the game uses
 */
public class Level {

	private String[] availablePlants;
	private Plant available[];
	private String[] zombieTypes;
	private int numZombies;
	private int[] zombieSpawn; // an array of the y coordinate of the spawn

	private int rounds;
	private int levelNum;
	

	/**
	 * The level constructor
	 * 
	 * @param level the level of the game
	 */
	public Level(int level) {
		String file = "resources/files/Levels.json";
		
		if (level < 1) {
			throw new IllegalArgumentException("Level must be greater than 0");
		}
		if (level == 24) {
			file = "resources/files/test.json";
		}
		System.out.println(file);
		JSONParser parser = new JSONParser();

		try {
			JSONObject jsonObject = null;
			Object obj = parser.parse(new FileReader(file));
			if(file.equals("resources/files/Levels.json")) {
				JSONArray jsonArr = (JSONArray) obj;
				jsonObject = (JSONObject) jsonArr.get(level - 1);
			}else {
				jsonObject = (JSONObject)obj;
			}
			

			this.levelNum = (int) ((long) jsonObject.get("levelNum"));
			this.numZombies = (int) (long) jsonObject.get("numZombies");
			this.rounds = (int) (long) jsonObject.get("rounds");
			JSONArray plants = (JSONArray) jsonObject.get("plants");
			JSONArray zombies = (JSONArray) jsonObject.get("zombies");
			JSONArray zombSpawn = (JSONArray) jsonObject.get("spawnPoints");

			this.zombieTypes = toStringArray(zombies);
			System.out.println("plants");
			this.availablePlants = toStringArray(plants);
			this.zombieSpawn = toIntArray(zombSpawn);
			if(this.zombieSpawn == null) {
				this.zombieSpawn = new int[this.numZombies];
				Random r = new Random();
				for(int i = 0; i < this.numZombies; i++) {
					this.zombieSpawn[i] = r.nextInt(5);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Converts a JSONArray into the more useful int array
	 * 
	 * @param array the JSONArray to be converted
	 * @return the new int array
	 */
	private int[] toIntArray(JSONArray array) {
		if (array == null) {
			return null;
		}
		int[] intArr = new int[array.size() - 1];
		for (int i = 0; i < intArr.length; i++) {
			intArr[i] = (int) (long) array.get(i);
		}
		return intArr;
	}

	/**
	 * Converts a JSONArray into the more useful String array
	 * 
	 * @param array the JSONArray to be converted
	 * @return the new String array
	 */
	private String[] toStringArray(JSONArray array) {
		if (array == null) {
			return null;
		}
		
		String[] arr = new String[array.size()];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (String) array.get(i);
		}
		System.out.println("json arr: "+ array.size() + " string arr: " + arr.length);
		return arr;
	}

	@Override
	/**
	 * Creates a string representation of the level
	 * 
	 * @return the string that is formated to be printed
	 */
	public String toString() {
		String s = "Level Number: " + this.levelNum + "\nNumber of Rounds: " + this.rounds + "\nNumber of Zombies: "
				+ this.numZombies;

		s += "\nPlants available to use: ";
		for (String str : availablePlants) {
			s += str + " ";
		}

		s += "\nTypes of zombies: ";
		for (String str : zombieTypes) {
			s += str + " ";
		}
		return s;
	}

	/**
	 * Gets the array of plant types
	 * 
	 * @return the arrray of plant types
	 */
	public String[] getPlants() {
		return this.availablePlants;
	}

	/**
	 * Gets the arrray of zombie types
	 * 
	 * @return the array of zombie types
	 */
	public String[] getZombies() {
		return this.zombieTypes;
	}

	/**
	 * Gets the number of zombies for the level
	 * 
	 * @return the num of zombies for the level
	 */
	public int getNumZombies() {
		return this.numZombies;
	}

	/**
	 * Gets the number of rounds the zombies can spawn for
	 * 
	 * @return the number of rounds the zombie will spawn for
	 */
	public int getRounds() {
		return this.rounds;
	}

	/**
	 * Gets the current level number
	 * 
	 * @return the level number
	 */
	public int getLevelNum() {
		return this.levelNum;
	}

	/**
	 * Returns an array used to plot the y placement of new zombies
	 * 
	 * @return the array of ints for y placement
	 */
	public int[] getZombieSpawn() {
		return this.zombieSpawn;
	}
}

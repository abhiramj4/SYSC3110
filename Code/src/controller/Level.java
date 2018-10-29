package controller;

import java.io.FileReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Level {

	private String[] availablePlants;
	private String[] zombieTypes;
	private int numZombies;
	private int rounds;
	private int levelNum;

	public Level(int level) {
		JSONParser parser = new JSONParser();

		try {

			Object obj = parser.parse(new FileReader("resources/files/Levels.json"));

			JSONArray jsonArr = (JSONArray) obj;
			JSONObject jsonObject = (JSONObject) jsonArr.get(level - 1);

			this.levelNum = (int) ((long) jsonObject.get("levelNum"));
			this.numZombies = (int) (long) jsonObject.get("numZombies");
			this.rounds = (int) (long) jsonObject.get("rounds");
			JSONArray plants = (JSONArray) jsonObject.get("plants");
			JSONArray zombies = (JSONArray) jsonObject.get("zombies");

			this.zombieTypes = toStringArray(zombies);
			this.availablePlants = toStringArray(plants);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String[] toStringArray(JSONArray array) {
		if (array == null) {
			return null;
		}

		String[] arr = new String[array.size()];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (String) array.get(i);
		}
		return arr;
	}

	@Override
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

	public static void main(String[] args) {
		Level l = new Level(1);

		System.out.println(l);
	}
}

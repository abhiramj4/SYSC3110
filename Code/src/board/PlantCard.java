package board;

import javafx.scene.control.Button;

public class PlantCard extends Button {

	private String plantname;
	private int cost;

	public PlantCard(String plantname, int cost) {
		this.setMinSize(100, 150);
		this.plantname = plantname;
		this.cost = cost;
		this.setText(plantname + "\n" + cost);
	}
}

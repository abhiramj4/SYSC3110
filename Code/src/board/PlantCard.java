package board;

import javafx.scene.control.Button;
import javafx.scene.image.Image;

public class PlantCard extends Button {

	private final static String SUNIMAGEPATH = "resources/images/plants/sunflower.png";
	private final static String PEAIMAGEPATH = "resources/images/plants/peashooter.png";
	private String plantname;
	private int cost;

	public PlantCard(String plantname, int cost) {
		this.setMinSize(100, 150);
		this.plantname = plantname;
		this.cost = cost;
		this.setText(plantname + "\n" + cost);

	}

	/**
	 * @return the plantname
	 */
	public String getPlantname() {
		return plantname;
	}

	/**
	 * @param plantname the plantname to set
	 */
	public void setPlantname(String plantname) {
		this.plantname = plantname;
	}
	

	/**
	 * @return the cost
	 */
	public int getCost() {
		return cost;
	}

	/**
	 * @param cost the cost to set
	 */
	public void setCost(int cost) {
		this.cost = cost;
	}
	
	
}

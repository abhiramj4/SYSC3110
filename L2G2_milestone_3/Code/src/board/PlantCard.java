package board;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PlantCard extends Button {

	private final static String SUNIMAGEPATH = "resources/images/plants/sunflower.png";
	private final static String PEAIMAGEPATH = "resources/images/plants/peashooter.png";
	private final static String CHERRYBOMBIMAGEPATH = "resources/images/plants/cherrybomb.png";
	private final static String WALLNUTIMAGEPATH = "resources/images/plants/wallnut.png";
	private String plantname;
	private int cost;
	private Image image;

	public PlantCard(String plantname, int cost) {
		this.setMinSize(100, 150);
		this.plantname = plantname;
		this.cost = cost;
		this.setText("\n" + cost);

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
	
	public void setImage(String type) {
		File fr;
		URL url;
		//fr = new File(entity.getImagePath());
		
		try {
			
			if(type.toLowerCase().contains("sunflower")) {
				fr = new File(SUNIMAGEPATH);
				url = fr.toURI().toURL();
				this.image = new Image(url.toString(), 50, 50, false, false);
				this.setGraphic(new ImageView(this.image));
			} else if (type.toLowerCase().contains("peashooter")) {
				fr = new File(PEAIMAGEPATH);
				url = fr.toURI().toURL();
				this.image = new Image(url.toString(), 50, 50, false, false);
				this.setGraphic(new ImageView(this.image));
			}  else if (type.toLowerCase().contains("cherrybomb")) {
				fr = new File(CHERRYBOMBIMAGEPATH);
				url = fr.toURI().toURL();
				this.image = new Image(url.toString(), 50, 50, false, false);
				this.setGraphic(new ImageView(this.image));
			}  else if (type.toLowerCase().contains("wallnut")) {
				fr = new File(WALLNUTIMAGEPATH);
				url = fr.toURI().toURL();
				this.image = new Image(url.toString(), 50, 50, false, false);
				this.setGraphic(new ImageView(this.image));
			}
			//URL url = fr.toURI().toURL();
			
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

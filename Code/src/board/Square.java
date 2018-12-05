package board;

import java.io.File;

import entities.Entity;
import entities.plants.Plant;
import entities.zombies.Zombie;
import enumerations.SquareType;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Sai Vikranth Desu
 *
 */
public class Square extends Button {

	private Coordinate coordinate;
	private Entity entity;
	private SquareType type;
	private Image image;
	private boolean lawnMower;

	/**
	 * Constructor for class square
	 * 
	 * @param coordinate of this square
	 * @param entity     on this square
	 * @param type       of square
	 */
	public Square(Coordinate coordinate, Entity entity, SquareType type) {
		this.coordinate = coordinate;
		this.entity = entity;
		this.type = type;
		setImage();
	}

	/**
	 * Constructor for class square
	 * 
	 * @param coordinate of this square
	 * @param type       of square
	 */
	public Square(Coordinate coordinate, SquareType type) {
		this.coordinate = coordinate;
		this.type = type;
		setMinSize(100, 100);
		setMaxSize(100, 100);
		setImage();
	}

	public void setImage() {
		if (entity == null) {
			if (type == SquareType.LAWN) {
				this.setGraphic(null);
				this.setStyle("-fx-background-color: #006900;");
			} else {
				this.setGraphic(null);
				this.setStyle("-fx-background-color: grey;");
			}

		} else {
			File fr;
			fr = new File(entity.getImagePath());

			try {
				URL url = fr.toURI().toURL();
				this.image = new Image(url.toString(), 100, 100, false, false);
				this.setGraphic(new ImageView(this.image));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	/**
	 * Get the coordinate of this square
	 * 
	 * @return the coordinate
	 */
	public Coordinate getCoordinate() {
		return coordinate;
	}

	/**
	 * Set the coordinate of this square
	 * 
	 * @param coordinate of this square to be set
	 */
	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	/**
	 * Get the entity on this square
	 * 
	 * @return the entity on this square
	 */
	public Entity getEntity() {
		return entity;
	}

	/**
	 * Set the entity on this square
	 * 
	 * @param entity to be placed on this square
	 */
	public void setEntity(Entity entity) {
		this.entity = entity;
		this.setImage();
		if (entity instanceof Zombie) {
			((Zombie) entity).setPosition(this.coordinate);
		} else if (entity instanceof Plant) {
			((Plant) entity).setPosition(this.coordinate);
		}
	}

	/**
	 * Remove an entity from a square
	 * 
	 * @param coordinate to be removed from
	 * @return the removed entity
	 */
	public Entity removeEntity(Coordinate coordinate) {
		Entity temp = this.entity;
		this.entity = null;
		return temp;
	}

	/**
	 * Get the type of this square
	 * 
	 * @return the type of this square
	 */
	public SquareType getType() {
		return type;
	}

	public void setType(SquareType type) {
		this.type = type;
	}

	/**
	 * Check if this square is empty
	 * 
	 * @return true if the square is empty
	 */
	public boolean isEmpty() {
		return (this.entity == null);
	}

	/**
	 * Return a string version of this square
	 */
	@Override
	public String toString() {
		if (this.isEmpty()) {
			if (this.getType() == SquareType.LAWN) {
				return "LAWN";
			} else {
				return "SPWN";
			}
		} else {
			return this.getEntity().toString();
		}
	}

	public void setLawnMower(Boolean set) {
		this.lawnMower = set;
	}

	public boolean getLawnMower() {
		return this.lawnMower;
	}
}

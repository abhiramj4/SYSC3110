package board;

import entities.Entity;
import entities.plants.Plant;
import entities.zombies.Zombie;
import enumerations.SquareType;

/**
 * @author Sai Vikranth Desu
 *
 */
public class Square {

	private Coordinate coordinate;
	private Entity entity;
	private SquareType type;

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
}

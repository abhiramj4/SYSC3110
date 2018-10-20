package board;

import entities.Entity;
import enumerations.SquareType;

public class Square {

	private Coordinate coordinate;
	private Entity entity;
	private SquareType type;

	public Square(Coordinate coordinate, Entity entity, SquareType type) {
		this.coordinate = coordinate;
		this.entity = entity;
		this.type = type;
	}

	public Square(Coordinate coordinate, SquareType type) {
		this.coordinate = coordinate;
		this.type = type;
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}
	
	public Entity removeEntity(Coordinate coordinate) {
		Entity temp = this.entity;
		this.entity = null;
		return temp;
	}

	public SquareType getType() {
		return type;
	}

	public void setType(SquareType type) {
		this.type = type;
	}

	public boolean isEmpty() {
		return (this.entity == null);
	}

	@Override
	public String toString() {
		if (this.isEmpty()) {
			return this.getType().getName();
		} else {
			return this.getEntity().toString();
		}
	}
}

package entities;

import controller.GameListener;

public abstract class Entity implements GameListener {
	private int health;
	private String name;
	public enum EntityType { PLANT, ZOMBIE };
	private EntityType entityType;

	public Entity(int health, String name, EntityType entityType) {
		this.health = health;
		this.name = name;
	}

	public EntityType getEntityType() {
		return entityType;
	}

	public void setEntityType(EntityType entityType) {
		this.entityType = entityType;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getHealth() {
		return this.health;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
}

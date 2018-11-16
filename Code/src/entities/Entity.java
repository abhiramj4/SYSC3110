package entities;

import controller.GameListener;
import javafx.scene.image.Image;

/**
 * @author Everett Soldaat
 *
 */
public abstract class Entity implements GameListener {
	private int health;
	private String name;
	private EntityType entityType;
	private String imagePath;
	private Image image;

	/**
	 * Enumerator type for entity
	 * 
	 * @author Abhiram
	 *
	 */
	public enum EntityType {
		PLANT, ZOMBIE
	};

	/**
	 * Constructor for type entity
	 * 
	 * @param health     of the entity
	 * @param name       of the entityxx
	 * @param entityType type of the entity
	 */
	public Entity(int health, String name, EntityType entityType, String imagePath) {
		this.health = health;
		this.name = name;
		this.imagePath = imagePath;
		this.image = null;
	}

	/**
	 * Get the entity type
	 * 
	 * @return the type of this entity
	 */
	public EntityType getEntityType() {
		return entityType;
	}

	/**
	 * Set the type for this entity
	 * 
	 * @param entityType entity type to set
	 */
	public void setEntityType(EntityType entityType) {
		this.entityType = entityType;
	}

	/**
	 * Set the health of this entity
	 * 
	 * @param health of this entity
	 */
	public void setHealth(int health) {
		this.health = health;
	}

	/**
	 * Get the health of this entity
	 * 
	 * @return the health of this entity
	 */
	public int getHealth() {
		return this.health;
	}

	/**
	 * Set the name of this entity
	 * 
	 * @param name of this entity
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get the name of this entity
	 * 
	 * @return the name of this entity
	 */
	public String getName() {
		return this.name;
	}
	
	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
}

package entities;

import controller.GameListener;

import java.net.MalformedURLException;
import java.net.URL;
import java.io.File;

/**
 * @author Everett Soldaat
 *
 */
public abstract class Entity implements GameListener {
	private int health;
	private String name;
	private EntityType entityType;
	private String imagePath;
	private URL url;

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
	 * @param name       of the entity
	 * @param entityType type of the entity
	 */
	public Entity(int health, String name, EntityType entityType, String imagePath) {
		this.health = health;
		this.name = name;
		this.imagePath = imagePath;
		this.entityType = entityType;
		this.imagePath = imagePath;
		/*
		 * File fr; fr = new File(imagePath);
		 * 
		 * try { this.url = fr.toURI().toURL(); System.out.println(url); this.image =
		 * new Image(url.toString()); } catch (MalformedURLException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */

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

	public String getImagePath() {
		return this.imagePath;
	}

}

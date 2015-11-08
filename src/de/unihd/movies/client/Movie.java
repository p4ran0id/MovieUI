package de.unihd.movies.client;

import java.io.Serializable;

/**
 * The Class Movie.
 */
public class Movie implements Serializable
{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	private int id;
	
	private static int nextId = 1;
	
	public static int getNextId() {
		return nextId;
	}

	public static void setNextId(int nextId) {
		Movie.nextId = nextId;
	}

	/** The name. */
	private String name;
	
	/** The time. */
	private int time;
	
	/** The language. */
	private Language language;
	
	/** The description. */
	private String description;
	
	/** The place. */
	private String place;

	/**
	 * Instantiates an empty movie.
	 * */
	public Movie() {
		this.name = "";
		this.time = 0;
		this.language = Language.English;
		this.description = "";
		this.place = "";
		this.id = nextId;
		nextId = nextId + 1;
	}
	
	/**
	 * Instantiates a new movie.
	 *
	 * @param id the id
	 * @param name the name
	 * @param time the time
	 * @param language the language
	 * @param description the description
	 * @param place the place
	 */
	public Movie(String name, int time, Language language, String description, String place)
	{
		this.id = nextId;
		nextId = nextId + 1;
		this.name = name;
		this.time = time;
		this.language = language;
		this.description = description;
		this.place = place;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the time.
	 *
	 * @return the time
	 */
	public int getTime() {
		return time;
	}

	/**
	 * Sets the time.
	 *
	 * @param time the new time
	 */
	public void setTime(int time) {
		this.time = time;
	}

	/**
	 * Gets the language.
	 *
	 * @return the language
	 */
	public Language getLanguage() {
		return language;
	}

	/**
	 * Sets the language.
	 *
	 * @param language the new language
	 */
	public void setLanguage(Language language) {
		this.language = language;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the place.
	 *
	 * @return the place
	 */
	public String getPlace() {
		return place;
	}

	/**
	 * Sets the place.
	 *
	 * @param place the new place
	 */
	public void setPlace(String place) {
		this.place = place;
	}
	/**
	* Information about a contact.
	   */

}

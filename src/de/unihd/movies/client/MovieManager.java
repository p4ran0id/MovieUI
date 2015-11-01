package de.unihd.movies.client;

import com.google.gwt.core.client.EntryPoint;
import java.util.ArrayList;

/**
 * The Class MovieManager.
 */
public class MovieManager implements EntryPoint {

	private ArrayList<Movie> movies = new ArrayList<Movie>();
	
	public void onModuleLoad() {
		//create some testdata
		Movie mov1 = new Movie(5,"Film1",321,"Deutsch","super","iwo");
		Movie mov2 = new Movie(3,"EFilm2",421,"Englisch","absoltoll","da");
		Movie mov3 = new Movie(2,"CFilm3",122,"Franoesisch","top","dvd");
		Movie mov4 = new Movie(1,"aFilm4",223,"Russisch","klasse","Regal");
		movies.add(mov1);
		movies.add(mov2);
		movies.add(mov3);
		movies.add(mov4);
		
		//start the MovieUI
		MovieUI gui = new MovieUI(movies);
		//set UI visible
		gui.show();
	}
	
	
}

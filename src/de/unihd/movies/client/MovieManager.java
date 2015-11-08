package de.unihd.movies.client;

import com.google.gwt.core.client.EntryPoint;
import java.util.ArrayList;

/**
 * The Class MovieManager.
 */
public class MovieManager implements EntryPoint {

	private ArrayList<Movie> movies = new ArrayList<Movie>();
	
	public void onModuleLoad() {
		//create some test data
		Movie mov1 = new Movie("Film1",321,Language.English,"super","iwo");
		Movie mov2 = new Movie("EFilm2",421,Language.French,"absoltoll","da");
		Movie mov3 = new Movie("CFilm3",122,Language.Spanish,"top","dvd");
		Movie mov4 = new Movie("aFilm4",223,Language.German,"klasse","Regal");
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

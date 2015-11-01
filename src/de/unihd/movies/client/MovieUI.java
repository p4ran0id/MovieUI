package de.unihd.movies.client;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.google.gwt.cell.client.NumberCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;


/**
 * MovieUI that contains a table of movies.
 * */
public class MovieUI extends Composite {

	/**
	 * The main panel which contain all other widgets
	 * */
	private VerticalPanel panel;

	/**
	 * Creates a MovieUI with the given list of movies.
	 * 
	 * @param movies
	 *            The list of movies to show.
	 * */
	public MovieUI(final ArrayList<Movie> movies) {

		panel = new VerticalPanel();
		
		// Create a table containing all movies
		CellTable<Movie> movieTable = new CellTable<Movie>();
		
		//add table to panel
		panel.add(movieTable);
		 movieTable.setVisible(true);	
		
		 /**
		  * create and add columns to table
		  * 1. creating a column with value
		  * 2. add column to table
		  * 3. set column to sortable
		  */
		//create id column
		Column<Movie, Number> columnId = new Column<Movie, Number>(new NumberCell()) {
            @Override
            public Integer getValue(Movie movies) {
                return movies.getId();
            }
        };
        //add id column
        movieTable.addColumn(columnId,"ID");
        //activate sort
        columnId.setSortable(true);
        
        //create name column
        Column<Movie, String> columnName = new Column<Movie, String>(new TextCell ()) {
            @Override
            public String getValue(Movie movies) {
                return movies.getName();
            }
        };
        //add name column to table
        movieTable.addColumn(columnName,"Name");
      //activate sort
        columnName.setSortable(true);
        
      //create time column
        Column<Movie,Number> columnTime = new Column<Movie, Number>(new NumberCell()) {
            @Override
            public Integer getValue(Movie movies) {
                return movies.getTime();
            }
        };
        //add Time column to table
        movieTable.addColumn(columnTime,"Time");
        //activate sort
        columnTime.setSortable(true);
        
        
        //create language column
        Column<Movie, String> columnLanguage = new Column<Movie, String>(new TextCell()) {
            @Override
            public String getValue(Movie movies) {
                return movies.getLanguage();
            }
        };
        //add Language column to table
        movieTable.addColumn(columnLanguage,"Language");
        //activate sort
        columnLanguage.setSortable(true);
        
        //create description column
        Column<Movie, String> columnDescription = new Column<Movie, String>(new TextCell()) {
            @Override
            public String getValue(Movie movies) {
                return movies.getDescription();
            }
        };
        //add Language column to table
        movieTable.addColumn(columnDescription,"Description");
        //activate sort
        columnDescription.setSortable(true);
        
      //create description column
        Column<Movie, String> columnPlace = new Column<Movie, String>(new TextCell()) {
            @Override
            public String getValue(Movie movies) {
                return movies.getPlace();
            }
        };
        //add Language column to table
        movieTable.addColumn(columnPlace,"Place");
        //activate sort
        columnPlace.setSortable(true);
        
        /**
         * DataProvider
         */
        
        //create a DataProvider
        ListDataProvider<Movie> movieProvider = new ListDataProvider<Movie>(){};    
        
        //connect table to DataProvider
        movieProvider.addDataDisplay(movieTable);
        

        // Add the data to the data provider, which automatically pushes it to the
        // widget.
        List<Movie> list = movieProvider.getList();
        for (Movie contact : movies) {
          list.add(contact);
        }
        
       
        /**
         * ListHandler with a separate comperator 
         * for each column.
         */
        //ListHandler for ID column
        ListHandler<Movie> columnIdSortHandler = new ListHandler<Movie>(list);
        columnIdSortHandler.setComparator(columnId,
            new Comparator<Movie>() {
              public int compare(Movie o1, Movie o2) {
                if (o1 == o2) {
                  return 0;
                }
              // Compare the ID columns.
                if (o1 != null) {
                  return (o2 != null) ? Integer.compare(o1.getId(),o2.getId()) : 1;
                }
                return -1;
              }
        });
        movieTable.addColumnSortHandler(columnIdSortHandler);
        
        //ListHandler for name column
        ListHandler<Movie> columnNameSortHandler = new ListHandler<Movie>(list);
            columnNameSortHandler.setComparator(columnName,
                new Comparator<Movie>() {
                  public int compare(Movie o1, Movie o2) {
                    if (o1 == o2) {
                      return 0;
                    }
                  // Compare the Name columns.
                    if (o1 != null) {
                      return (o2 != null) ? o1.getName().compareTo(o2.getName()) : 1;
                    }
                    return -1;
                  }
            });
            movieTable.addColumnSortHandler(columnNameSortHandler);
            
            //ListHandler for Time column
            ListHandler<Movie> columnTimeSortHandler = new ListHandler<Movie>(list);
            columnTimeSortHandler.setComparator(columnTime,
                new Comparator<Movie>() {
                  public int compare(Movie o1, Movie o2) {
                    if (o1 == o2) {
                      return 0;
                    }
                  // Compare the Time columns.
                    if (o1 != null) {
                      return (o2 != null) ? Integer.compare(o1.getTime(),o2.getTime()) : 1;
                    }
                    return -1;
                  }
            });
            movieTable.addColumnSortHandler(columnTimeSortHandler);
            
            //ListHandler for Language column
            ListHandler<Movie> columnLanguageSortHandler = new ListHandler<Movie>(list);
            columnLanguageSortHandler.setComparator(columnLanguage,
                new Comparator<Movie>() {
                  public int compare(Movie o1, Movie o2) {
                    if (o1 == o2) {
                      return 0;
                    }
                  // Compare the Language columns.
                    if (o1 != null) {
                      return (o2 != null) ? o1.getLanguage().compareTo(o2.getLanguage()) : 1;
                    }
                    return -1;
                  }
            });
            movieTable.addColumnSortHandler(columnLanguageSortHandler);
            
            //ListHandler for Description column
            ListHandler<Movie> columnDescriptionSortHandler = new ListHandler<Movie>(list);
            columnDescriptionSortHandler.setComparator(columnDescription,
                new Comparator<Movie>() {
                  public int compare(Movie o1, Movie o2) {
                    if (o1 == o2) {
                      return 0;
                    }
                  // Compare the Description columns.
                    if (o1 != null) {
                      return (o2 != null) ? o1.getName().compareTo(o2.getName()) : 1;
                    }
                    return -1;
                  }
            });
            movieTable.addColumnSortHandler(columnDescriptionSortHandler);
            
            //ListHandler for Place column
            ListHandler<Movie> columnPlaceSortHandler = new ListHandler<Movie>(list);
            columnNameSortHandler.setComparator(columnPlace,
                new Comparator<Movie>() {
                  public int compare(Movie o1, Movie o2) {
                    if (o1 == o2) {
                      return 0;
                    }
                  // Compare the place columns.
                    if (o1 != null) {
                      return (o2 != null) ? o1.getName().compareTo(o2.getName()) : 1;
                    }
                    return -1;
                  }
            });
            movieTable.addColumnSortHandler(columnPlaceSortHandler);
   
            
            
            
	}

	/**
	 * Shows the content of the MovieUI.
	 * */
	public void show() {
		initWidget(panel);
		RootPanel.get("content").add(this);
		this.setVisible(true);
	}

}

package de.unihd.movies.client;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.google.gwt.cell.client.EditTextCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.NumberCell;
import com.google.gwt.cell.client.SelectionCell;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;


/**
 * MovieUI that contains a table of movies.
 * */
public class MovieUI extends Composite {

	 
	/**
	 * The main panel which contain all other widgets
	 * 
	 * hpanel - horizontal Panel for Buttons and Filter
	 * vpanel - vertical Panel with movieTable
	 * 
	 * hpanel is attached on vpanel.
	 * */
	private VerticalPanel vpanel;
	private HorizontalPanel hpanel;

	/**
	 * Creates a MovieUI with the given list of movies.
	 * 
	 * @param movies
	 *            The list of movies to show.
	 * */
	public MovieUI(final ArrayList<Movie> movies) {
		
		
		final ProvidesKey<Movie> keyProvider = new ProvidesKey<Movie>() {
	        public Object getKey(Movie item) {
	            // Always do a null check.
	            return (item == null) ? null : item.getId();
	        }
	    };
	    
	    
		
		//----------------------------------------------___Panel___---------------------
		vpanel = new VerticalPanel();
		hpanel = new HorizontalPanel();
		hpanel.setSpacing(15);
		
		// Create a table containing all movies
				final CellTable<Movie> movieTable = new CellTable<Movie>();
		
		//----------------------------------------------___Table___---------------------
		 
		 //----------------------------------------------SelectionModel--------
		 /**
	         * Selection Model for the MovieTable
	         * @param:
	         * 	selectedMovie - The Movie in the Selected row      
	         */
	        final SingleSelectionModel<Movie> selectionModel = new SingleSelectionModel<Movie>(keyProvider);
	        selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler(){

//				@Override
//				public void onSelectionChange(SelectionChangeEvent event) {
//					// TODO Auto-generated method stub
//					
//				}
	       	@Override public void onSelectionChange(SelectionChangeEvent event){
	        	      Movie selectedMovie = selectionModel.getSelectedObject();
	        	      selectionModel.setSelected(selectedMovie, true);
	        	    	 }
	          	}
	        
	       
	        );
	        movieTable.setSelectionModel(selectionModel);
        
	        //----------------------------------------------___DataProvider___---------------------
	        /**
	         * DataProvider
	         */
	        
	        //create a DataProvider
	        final ListDataProvider<Movie> movieProvider = new ListDataProvider<Movie>(){};    
	        
	        //connect table to DataProvider
	        movieProvider.addDataDisplay(movieTable);
	        

	        // Add the data to the data provider, which automatically pushes it to the
	        // widget.
	        List<Movie> list = movieProvider.getList();
	        for (Movie contact : movies) {
	          list.add(contact);
	        }
	        
	      //----------------------------------------------___ToolBox___---------------------
    		//-----------------------------------------__-Buttons-__--------------------------
    		Button addMovieButton = new Button ("Add Movie", new ClickHandler() {
    			  @Override
    			public void onClick(ClickEvent event) {
//    				movies.add(new Movie("test", 0, Language.English, "test", "test"));    				
    				movieProvider.getList().add(new Movie("", 0, Language.English, "", ""));
    				movieTable.redraw();
    				//movieProvider.refresh();
    				Window.alert("Added");
    		      }
    		    });
    	    hpanel.add(addMovieButton);
    	    
    	    Button removeMovieButton = new Button ("Remove Movie", new ClickHandler() {
    	    	@Override
    	    	public void onClick(ClickEvent event) {
    	    		//movies.remove(selectionModel.getSelectedObject());
    				movieProvider.getList().remove(selectionModel.getSelectedObject());
    				//Movie.setNextId(Movie.getNextId() -1);
    				movieTable.setRowCount(movieProvider.getList().size());
    				movieTable.redraw();
    				//movieProvider.refresh();

    		      }
    		    });
    	    hpanel.add(removeMovieButton);
    		vpanel.add(hpanel);
    		
    		//-----------------------------------------__-Label-__--------------------------
    		InlineLabel filter = new InlineLabel("Filter");
    		
    		//-----------------------------------------__-TextBox-__--------------------------
    		TextBox filterBox = new TextBox();
    		filterBox.setName("Filter");
    		filterBox.setTitle("Filter");
    		hpanel.add(filter);
    		hpanel.add(filterBox);
    		
    		
    	/**
		  * create and add columns to table
		  * 1. creating a column with value
		  * 2. add column to table
		  * 3. set column to sortable
		  */
		//------------------------__-ID-__-----------------------
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
        
        //------------------------__-NAME-__-----------------------
        //create name column
        Column<Movie, String> nameColumn = new Column<Movie, String>(new EditTextCell ()) {
            @Override
            public String getValue(Movie movies) {
                return movies.getName();
            }
        };
        //add name column to table
        movieTable.addColumn(nameColumn,"Name");
      //activate sort
        nameColumn.setSortable(true);
        
      //--------------_-_FieldUpdater_-_------------------
        nameColumn.setFieldUpdater(new FieldUpdater<Movie, String>() {
            @Override
            public void update(int index, Movie object, String value) {
              // Called when the user changes the value.
            	//Window.alert("You changed the name of " + object.getName() + " to " + value);
            	//Push changes to movies 
            	object.setName(value);
            	movieTable.redraw();
            }
          });
        // Push the data into the widget
//        	movieTable.setRowData(movies);
//        	
      //------------------------__-TIME-__-----------------------
      //create time column
        
        final EditTextCell timeCell = new EditTextCell();  
        
        final Column<Movie, String> timeColumn = new Column<Movie, String>(timeCell){
            
        	@Override
            public String getValue(Movie movies) {
        		// convert int to String
                return String.valueOf(movies.getTime()) ;
            }
        };
        
        //add Time column to table
        movieTable.addColumn(timeColumn,"Time");
        //activate sort
        timeColumn.setSortable(true);
        
        //--------------_-_FieldUpdater_-_------------------
        
        timeColumn.setFieldUpdater(new FieldUpdater<Movie, String>() {
            
        	@Override
            public void update(int index, Movie object, String value) {
              // Called when the user changes the value.
            	//Window.alert("You changed the name of " + object.getName() + " to " + value);
            	//Push changes to movies 
            	if (!value.matches("^[0-9]*$")){
            		timeCell.clearViewData(selectionModel.getSelectedObject()); 
            		movieProvider.refresh();
            		movieTable.redraw(); 
            	    Window.alert(value + " ist keine gueltige eingabe. Bitte eine positive Zahl eingeben!");
            		}
            	else  {
            		object.setTime(Integer.valueOf(value));
        			movieTable.redraw();
        			}
        	}
        	
          });
        // Push the data into the widget
//        	movieTable.setRowData(movies);
        
        //------------------------__-LANGUAGE-__-------------------
        //Language List
        List <String> language = new ArrayList<String>();
        language.add("English"); 
        language.add("German"); 
        language.add("Spanish");
        language.add("French");          
        
        //create language column
        Column<Movie, String> languageColumn = new Column<Movie, String>(
        		new SelectionCell(language)) {
					
                @Override
            public String getValue(Movie movies) {
                return movies.getLanguage().toString();
            }
        };
        
        //add Language column to table
        movieTable.addColumn(languageColumn,"Language");
        //activate sort
        languageColumn.setSortable(true);
        
        //------------------------__-DESCRIPTION-__-----------------------
        //create description column
        Column<Movie, String> descriptionColumn = new Column<Movie, String>(new EditTextCell()) {
            @Override
            public String getValue(Movie movies) {
                return movies.getDescription();
            }
        };
        //add Language column to table
        movieTable.addColumn(descriptionColumn,"Description");
        //activate sort
        descriptionColumn.setSortable(true);
        
      //--------------_-_FieldUpdater_-_------------------
        descriptionColumn.setFieldUpdater(new FieldUpdater<Movie, String>() {
            @Override
            public void update(int index, Movie object, String value) {
              // Called when the user changes the value.
            	//Window.alert("You changed the name of " + object.getName() + " to " + value);
            	//Push changes to movies 
            	object.setDescription(value);
            	movieTable.redraw();
            }
          });
        // Push the data into the widget
//        	movieTable.setRowData(movies);
        	
	 
      //------------------------__-PLACE-__-----------------------
      //create description column
        Column<Movie, String> placeColumn = new Column<Movie, String>(new EditTextCell()) {
            @Override
            public String getValue(Movie movies) {
                return movies.getPlace();
            }
        };
        //add Place column to table
        movieTable.addColumn(placeColumn,"Place");
        //activate sort
        placeColumn.setSortable(true);
        
      //--------------_-_FieldUpdater_-_------------------
        placeColumn.setFieldUpdater(new FieldUpdater<Movie, String>() {
            @Override
            public void update(int index, Movie object, String value) {
              // Called when the user changes the value.
            	//Window.alert("You changed the name of " + object.getName() + " to " + value);
            	//Push changes to movies 
            	object.setPlace(value);
            	movieTable.redraw();
            }
          });
        // Push the data into the widget
      //  	movieTable.setRowData(movies);
        
      //----------------------------------------------___ListHandler___---------------------
        /**
         * ListHandler with a separate comperator 
         * for each column.
         */
 	   //-----------------------------__-id_handler-__-------------------
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
 	   //-----------------------------__-name_handler-__-------------------
        //ListHandler for name column
        ListHandler<Movie> columnNameSortHandler = new ListHandler<Movie>(list);
            columnNameSortHandler.setComparator(nameColumn,
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

      	   //-----------------------------__-time_handler-__-------------------
            //ListHandler for Time column
            ListHandler<Movie> columnTimeSortHandler = new ListHandler<Movie>(list);
            columnTimeSortHandler.setComparator(timeColumn,
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
      	   
            //-----------------------------__-language_handler-__-------------------
            //ListHandler for Language column
            ListHandler<Movie> columnLanguageSortHandler = new ListHandler<Movie>(list);
            columnLanguageSortHandler.setComparator(languageColumn,
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
            
      	   //-----------------------------__-description_handler-__-------------------
            //ListHandler for Description column
            ListHandler<Movie> columnDescriptionSortHandler = new ListHandler<Movie>(list);
            columnDescriptionSortHandler.setComparator(descriptionColumn,
                new Comparator<Movie>() {
                  public int compare(Movie o1, Movie o2) {
                    if (o1 == o2) {
                      return 0;
                    }
                  // Compare the Description columns.
                    if (o1 != null) {
                      return (o2 != null) ? o1.getDescription().compareTo(o2.getName()) : 1;
                    }
                    return -1;
                  }
            });
            movieTable.addColumnSortHandler(columnDescriptionSortHandler);
      	   
            //-----------------------------__-place_handler-__-------------------
            //ListHandler for Place column
            ListHandler<Movie> columnPlaceSortHandler = new ListHandler<Movie>(list);
            columnNameSortHandler.setComparator(placeColumn,
                new Comparator<Movie>() {
                  public int compare(Movie o1, Movie o2) {
                    if (o1 == o2) {
                      return 0;
                    }
                  // Compare the place columns.
                    if (o1 != null) {
                      return (o2 != null) ? o1.getPlace().compareTo(o2.getName()) : 1;
                    }
                    return -1;
                  }
            });
            movieTable.addColumnSortHandler(columnPlaceSortHandler);
            
        	//add table to panel
			vpanel.add(movieTable);
	}

	/**
	 * Shows the content of the MovieUI.
	 * */
	public void show() {
		initWidget(vpanel);
		RootPanel.get("content").add(this);
		
		this.setVisible(true);
	}

}

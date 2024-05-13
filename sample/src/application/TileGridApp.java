package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.util.Random;

public class TileGridApp extends Application {
	private static final int SCREEN_WIDTH = 540;
	private static final int SCREEN_HEIGHT = 960;
    private static final int GRID_ROWS = 15;
    private static final int GRID_COLUMNS = 15;
    
    // pre-made maps
    private int[][][] grids = {
		{
    	    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    	    {1,0,1,0,1,0,0,0,0,0,1,0,1,0,1},
    	    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    	    {0,1,1,1,0,0,0,0,0,0,0,1,1,1,0},
    	    {0,0,0,1,0,0,0,0,0,0,0,1,0,0,0},
    	    {0,1,0,1,0,1,1,0,1,1,0,1,0,1,0},
    	    {0,0,0,1,0,1,0,0,0,1,0,1,0,0,0},
    	    {0,1,0,1,1,1,0,0,0,1,1,1,0,1,0},
    	    {0,0,0,1,0,1,0,0,0,1,0,1,0,0,0},
    	    {0,1,0,1,0,1,1,0,1,1,0,1,0,1,0},
    	    {0,0,0,1,0,0,0,0,0,0,0,1,0,0,0},
    	    {0,1,1,1,0,0,0,0,0,0,0,1,1,1,0},
    	    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    	    {1,0,1,0,1,0,0,0,0,0,1,0,1,0,1},
    	    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
    	},
		{
		    {0,0,0,0,0,0,0,1,0,0,0,0,0,0,0},
		    {0,0,0,0,0,0,1,0,1,0,0,0,0,0,0},
		    {0,0,0,0,0,1,0,0,0,1,0,0,0,0,0},
		    {0,0,0,1,0,0,0,0,0,0,0,1,0,0,0},
		    {0,0,0,0,1,0,0,0,0,0,1,0,0,0,0},
		    {0,0,1,0,0,1,0,0,0,1,0,0,1,0,0},
		    {0,1,0,0,0,0,1,1,1,0,0,0,0,1,0},
		    {1,0,0,0,0,0,1,1,1,0,0,0,0,0,1},
		    {0,1,0,0,0,0,1,1,1,0,0,0,0,1,0},
		    {0,0,1,0,0,1,0,0,0,1,0,0,1,0,0},
		    {0,0,0,0,1,0,0,0,0,0,1,0,0,0,0},
		    {0,0,0,1,0,0,0,0,0,0,0,1,0,0,0},
		    {0,0,0,0,0,1,0,0,0,1,0,0,0,0,0},
		    {0,0,0,0,0,0,1,0,1,0,0,0,0,0,0},
		    {0,0,0,0,0,0,0,1,0,0,0,0,0,0,0}
		},
		{
		    {0,1,0,0,0,0,0,0,0,0,0,0,0,1,0},
		    {0,0,1,0,0,0,0,0,0,0,0,0,1,0,0},
		    {0,0,0,1,0,0,0,0,0,0,0,1,0,0,0},
		    {0,0,0,0,1,0,0,1,0,0,1,0,0,0,0},
		    {0,1,0,0,0,0,1,0,1,0,0,0,0,1,0},
		    {0,0,1,0,0,1,0,0,0,1,0,0,1,0,0},
		    {0,1,0,0,1,0,0,0,0,0,1,0,0,1,0},
		    {0,0,1,0,0,0,0,0,0,0,0,0,1,0,0},
		    {0,1,0,0,1,0,0,0,0,0,1,0,0,1,0},
		    {0,0,1,0,0,1,0,0,0,1,0,0,1,0,0},
		    {0,1,0,0,0,0,1,0,1,0,0,0,0,1,0},
		    {0,0,0,0,1,0,0,1,0,0,1,0,0,0,0},
		    {0,0,0,1,0,0,0,0,0,0,0,1,0,0,0},
		    {0,0,1,0,0,0,0,0,0,0,0,0,1,0,0},
		    {0,1,0,0,0,0,0,0,0,0,0,0,0,1,0}
		},
		{
		    {0,0,0,1,0,0,0,0,0,0,0,1,0,0,0},
		    {0,0,1,0,0,0,1,0,1,0,0,0,1,0,0},
		    {0,1,0,0,0,1,0,0,0,1,0,0,0,1,0},
		    {0,0,0,0,1,0,0,1,0,0,1,0,0,0,0},
		    {0,0,0,0,0,0,0,1,0,0,0,0,0,0,0},
		    {0,0,0,1,0,1,0,1,0,1,0,1,0,0,0},
		    {0,0,0,1,0,1,0,1,0,1,0,1,0,0,0},
		    {0,0,0,1,1,1,1,1,1,1,1,1,0,0,0},
		    {0,0,0,1,0,1,0,1,0,1,0,1,0,0,0},
		    {0,0,0,1,0,1,0,1,0,1,0,1,0,0,0},
		    {0,0,0,0,0,0,0,1,0,0,0,0,0,0,0},
		    {0,0,0,0,1,0,0,1,0,0,1,0,0,0,0},
		    {0,1,0,0,0,1,0,0,0,1,0,0,0,1,0},
		    {0,0,1,0,0,0,1,0,1,0,0,0,1,0,0},
		    {0,0,0,1,0,0,0,0,0,0,0,1,0,0,0}
		},	
		{
		    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		    {0,1,1,1,1,0,0,0,0,0,1,1,1,1,0},
		    {0,1,0,0,1,0,0,0,0,0,1,0,0,1,0},
		    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		    {0,1,0,0,1,0,1,0,1,0,1,0,0,1,0},
		    {0,1,1,1,1,1,1,0,1,1,1,1,1,1,0},
		    {0,0,0,0,1,0,0,0,0,0,1,0,0,0,0},
		    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		    {0,0,0,0,1,0,0,0,0,0,1,0,0,0,0},
		    {0,1,1,1,1,1,1,0,1,1,1,1,1,1,0},
		    {0,1,0,0,1,0,1,0,1,0,1,0,0,1,0},
		    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		    {0,1,0,0,1,0,0,0,0,0,1,0,0,1,0},
		    {0,1,1,1,1,0,0,0,0,0,1,1,1,1,0},
		    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		}
    };
    
    @Override
    public void start(Stage primaryStage) {
        GridPane gridPane = new GridPane();
        Random rand = new Random();
        
        int initGrid = rand.nextInt(5); // generate a random index
        int[][] grid = grids[initGrid]; // set the map to be used 
        
        for (int y = 0; y < GRID_COLUMNS; y++) {
            for (int x = 0; x < GRID_ROWS; x++) {
                Rectangle tile = createTile(grid[y][x]);
                gridPane.add(tile, x, y);
            }
        }

        Scene scene = new Scene(gridPane, SCREEN_WIDTH, SCREEN_HEIGHT);

        primaryStage.setScene(scene);
        primaryStage.setTitle("CMSC 137");
        primaryStage.show();
    }

    private Rectangle createTile(int value) {
    	double tile_width = (SCREEN_WIDTH / GRID_ROWS) - 1;
    	double tile_height = SCREEN_HEIGHT / GRID_COLUMNS;

    	Rectangle tile = new Rectangle(tile_width, tile_width); // unstretched
    	// Rectangle tile = new Rectangle(tile_width, tile_height); // stretched

    	if(value == 1) {
    		tile.setFill(Color.BLACK);
    	} else {
    		tile.setFill(Color.WHITE);
    	}

        tile.setStroke(Color.GRAY);
        tile.setStrokeWidth(1);
        return tile;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

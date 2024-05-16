package application;

import java.util.Random;

//import game.GameStage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
//import javafx.scene.control.Label;
//import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
//import javafx.scene.layout.Pane;
//import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class GameStage {
	private Scene scene;
	private Scene splashScene;
	private Stage stage;
	private Group root;
	private Canvas canvas;

	static final int WINDOW_HEIGHT = 600;
	static final int WINDOW_WIDTH = 500;

	public static final Color FONT_COLOR = Color.WHITE;
	private static final Font TITLE_FONT = Font.font("Verdana",FontWeight.BOLD,25);

    private static final int GRID_ROWS = 15;
    private static final int GRID_COLUMNS = 15;
    
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
    
	//the class constructor
	public GameStage() {
		this.root = new Group();
		this.scene = new Scene(root, GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT);
		this.canvas = new Canvas(GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT);
		}

	//method to add the stage elements
	public void setStage(Stage stage) {
		this.stage = stage;

		//set stage elements here
		this.root.getChildren().addAll(this.createCanvas(GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT),canvas);

		this.stage.setTitle("Chroma Clash");
		this.stage.setResizable(false);
		this.initSplash(stage);
		this.stage.setScene(this.splashScene);
		this.stage.show();
	}


	private Canvas createCanvas(int width, int height) {
    	Canvas canvas = new Canvas(width, height);
        return canvas;
    }

	private VBox createVBox() {
    	VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(8);
        Label title = new Label("Chroma Clash");
        Button b1 = new Button("New Game");
		title.setFont(TITLE_FONT);
        vbox.getChildren().addAll(title,b1);
        
        b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
            	start(stage);			// changes the scene into the instruction scene
            }
        });

        return vbox;
    }



    private void start(Stage stage) {
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

        Scene scene = new Scene(gridPane, WINDOW_WIDTH, WINDOW_HEIGHT);

        stage.setScene(scene);
        stage.show();
    }
    
    // TODO: implement some sort of algorithm to make grid generation better (e.g. flood fill algorithm)
//    private int[][] createGrid() {
//    	double WALL_DENSITY = 0.15; // some arbitrary value to dictate wall density
//    	int[][] grid = new int[GRID_ROWS][GRID_COLUMNS];
//    	
//    	for(int x = 0; x < GRID_ROWS; x++) {
//    		for(int y = 0; y < GRID_COLUMNS; y++) {
//    			Random rand = new Random();
//    			
//    			if(rand.nextDouble() < WALL_DENSITY) {
//    	    		grid[x][y] = 1;
//    	    	} else {
//    	    		grid[x][y] = 0;
//    	    	}
//    		}
//    	}
//    	
//    	return grid;
//    }
    
    private Rectangle createTile(int value) {
    	double tile_width = (WINDOW_WIDTH / GRID_ROWS) - 1;
    	double tile_height = WINDOW_HEIGHT / GRID_COLUMNS;

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
    
	private void setMenu(Stage stage) {
        stage.setScene( splashScene );
	}

	private void initSplash(Stage stage) {
		StackPane root = new StackPane();
        root.getChildren().addAll(this.createCanvas(GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT),this.createVBox());
        this.splashScene = new Scene(root,GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT);
	}
}


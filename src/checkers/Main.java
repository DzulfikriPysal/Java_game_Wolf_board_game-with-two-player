package checkers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
    	// Project Layout 
        BorderPane bp = new BorderPane();
        bp.setMinSize(500, 500);
        GridPane gridPane = new GridPane();
        GridPane gridPane2 = new GridPane();
        int counter_start=0;
        
        List<Button> collection_of_button = new ArrayList<>();
        List<wolf> collection_of_wolf = new ArrayList<>();
        //player object
        player p1 = new player();
        player p2 = new player();
        
        //initialize player tiles
        p1.player_tiles = 1;
        p2.player_tiles = 1;
        TextField tf = new TextField();
        
        for(int i = 0; i < 3; i++) {
        	wolf wolf_new = new wolf();
        	Random rand = new Random(); 
			int rand_num = rand.nextInt(25); 
			rand_num += 1;
			wolf_new.wolf_location(rand_num);
			collection_of_wolf.add(wolf_new);
			//System.out.println(rand_num);
        }
        
        
       
        // loop to create tiles
        int counter = 1;
        int counter2 = 10; 
        for (int row = 5; row > 0; row--) {
        	if(row % 2 == 0) {
        		for (int col = 4; col >= 0; col--) {
                    Button button = new Button("" + counter++);
                    button.setMaxWidth(100);
                    button.setMaxHeight(100);
                    button.setMinHeight(100);
                    button.setDisable(true);
                    gridPane.add(button, col, row);
                    GridPane.setHgrow(button, Priority.ALWAYS);
                    
                    collection_of_button.add(button);
                }
        	}else {
        		for (int col = 0; col < 5; col++) {
                    Button button = new Button("" + counter++);
                    button.setMaxWidth(100);
                    button.setMaxHeight(100);
                    button.setMinHeight(100);
                    button.setDisable(true);
                    gridPane.add(button, col, row);
                    GridPane.setHgrow(button, Priority.ALWAYS);
                    
                    collection_of_button.add(button);
                }
        	}
        	
            
        }
        
        System.out.println(collection_of_button);
        if(counter_start == 0) {
        	Button a = collection_of_button.get(0);
        	a.setStyle("-fx-background-color: #00ffff; ");
        }
        
        //event
        EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
            	Random rand = new Random(); 
				int rand_num = rand.nextInt(6); 
				rand_num += 1;
				Button a = collection_of_button.get(p1.player_tiles);
	        	//a.setStyle("-fx-background-color: #110000; ");
				
				p1.player_tiles += rand_num;
				a = collection_of_button.get(p1.player_tiles-1);
				
				for(int i = 0; i < 3; i++) {
					wolf b = collection_of_wolf.get(i);
					int c = b.wolf_location;
					int f = p1.player_tiles;
					System.out.println(c);
					System.out.println(f);
					if(f == c) {
						a.setStyle("-fx-background-color: #110000; ");
						tf.setText("Player 1 meet mr wolf at " + p1.player_tiles + " tiles");
						break ;
					}
					else {
						if(p1.player_tiles == p2.player_tiles) {
							a.setStyle("-fx-background-color: #00ffff; ");
						}
						else {
							a.setStyle("-fx-background-color: #00ff00; ");
						}
						tf.setText("Player 1 role number " + rand_num);
					}
					
					
				}
				
				
            } 
        }; 
        
        EventHandler<ActionEvent> event2 = new EventHandler<ActionEvent>() { 
            public void handle(ActionEvent e) 
            { 
            	Random rand = new Random(); 
				int rand_num = rand.nextInt(6); 
				rand_num += 1;
				Button a = collection_of_button.get(p2.player_tiles);
	        	//a.setStyle("-fx-background-color: #110000; ");
				
				p2.player_tiles += rand_num;
				a = collection_of_button.get(p2.player_tiles-1);
				if(p2.player_tiles == p1.player_tiles) {
					a.setStyle("-fx-background-color: #00ffff; ");
				}
				else {
					a.setStyle("-fx-background-color: #ff0000; ");
				}
				tf.setText("Player 2 role number " + rand_num);
            } 
        }; 
        
        // Application 
        
        bp.setBottom(tf);
        bp.setTop(gridPane2);
        bp.setCenter(gridPane);
        Button player1 = new Button("Role dice for Player 1");
        player1.setStyle("-fx-background-color: #00ff00; ");
        player1.setOnAction(event1); 
        Button player2 = new Button("Role dice for Player 2");
        player2.setStyle("-fx-background-color: #ff0000; ");
        player2.setOnAction(event2); 
        gridPane2.add(player1, 0, 0);
        gridPane2.add(player2, 4, 0);
        Scene scene = new Scene(bp);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
package de.montessori;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import de.montessori.pushygame.PushyWorld;

public class Pushy extends Application {
	PushyWorld pushyWorld;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		pushyWorld = new PushyWorld("Simple Pushy");
		
//		primaryStage.initStyle(StageStyle.DECORATED);
		primaryStage.initStyle(StageStyle.UTILITY);
		pushyWorld.initialize(primaryStage);
		
        primaryStage.show();
	}

	public static void main(String[] args) {
        launch(args);
	}
	
    @Override
    public void stop() throws Exception {
        Platform.exit();     
    }	

}

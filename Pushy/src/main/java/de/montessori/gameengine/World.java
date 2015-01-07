package de.montessori.gameengine;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class World {

	/** Title in the application window. */
	private final String windowTitle;
	
    /**
     * All nodes to be displayed in the game window.
     */
    private Group mainGroup;
    
    /**
     * the size of a single actor. All actors in the world have the same dimensions.
     */
    private int ACTOR_SIZE;
    
    /**
     * The JavaFX Scene as the game surface
     */
    private Scene gameScene;  
	
	
	public World(String title){
		windowTitle = title;
	}
	
	
    /**
     * Initialize the game world by update the JavaFX Stage.
     *
     * @param primaryStage The main window containing the JavaFX Scene.
     */
    public abstract void initialize(final Stage primaryStage);
    
	
	/**
	 * Returns the game's window title.
	 * 
	 * @return String The game's window title.
	 */
	public String getWindowTitle() {
		return windowTitle;
	}
	
	/**
	 * All JavaFX nodes which are rendered onto the game surface(Scene) is a
	 * JavaFX Group object.
	 * 
	 * @return Group The root containing many child nodes to be displayed into
	 *         the Scene area.
	 */
	public Group getMainGroup() {
		return mainGroup;
	}

	/**
	 * Sets the JavaFX Group that will hold all JavaFX nodes which are rendered
	 * onto the game surface(Scene) is a JavaFX Group object.
	 * 
	 * @param sceneNodes
	 *            The root container having many children nodes to be displayed
	 *            into the Scene area.
	 */
	protected void setMainGroup(Group mainGroup) {
		this.mainGroup = mainGroup;
	}
	


	public int getActorSize() {
		return ACTOR_SIZE;
	}

	public void setActorSize(int size) {
		ACTOR_SIZE = size;
	}
	
	/**
	 * Returns the JavaFX Scene. This is called the game surface to allow the
	 * developer to add JavaFX Node objects onto the Scene.
	 * 
	 * @return
	 */
	public Scene getGameScene() {
		return gameScene;
	}

	/**
	 * Sets the JavaFX Scene. This is called the game surface to allow the
	 * developer to add JavaFX Node objects onto the Scene.
	 * 
	 * @param gameSurface
	 *            The main game surface (JavaFX Scene).
	 */
	protected void setGameScene(Scene gameScene) {
		this.gameScene = gameScene;
	}
	
    
}

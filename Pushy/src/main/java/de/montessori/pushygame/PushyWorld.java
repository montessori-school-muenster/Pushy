package de.montessori.pushygame;

import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import de.montessori.gameengine.World;
import de.montessori.pushygame.actors.PushBox;
import de.montessori.pushygame.actors.PusherMan;
import de.montessori.pushygame.actors.Target;
import de.montessori.pushygame.actors.Wall;
import de.montessori.pushygame.controller.PushyActorManager;

public class PushyWorld extends World {

	public final static int ACTOR_SIZE = 36;

	private static final Duration TRANSLATE_DURATION = Duration.seconds(0.75);

	private TranslateTransition transition;

	private Image BACKGROUND_IMAGE;

	private Image BOX_IMAGE;

	private Image BOX_HIT_IMAGE;

	private Image PUSHY_IMAGE;

	private Image TARGET_IMAGE;

	private Image WALL_IMAGE;

	private Group wallGroup;

	private Group boxGroup;

	private Group targetGroup;

	private StackPane glassPane;

	private PusherMan pushy;

	private PushyActorManager manager;

	public PushyWorld(String title) {
		super(title);
	}

	@Override
	public void initialize(Stage primaryStage) {
		// Sets the window title
		primaryStage.setTitle(getWindowTitle());
		primaryStage.setResizable(false);
		setActorSize(ACTOR_SIZE);

		manager = new PushyActorManager(this);

		loadImages();
		setMainGroup(createMainGroup());

		Scene scene = new Scene(getMainGroup(), 360, 432);
		setGameScene(scene);
		scene.setOnKeyPressed(manager);

		primaryStage.setScene(scene);

		transition = new TranslateTransition(TRANSLATE_DURATION, glassPane);

		manager.startGame();
	}

	private Group createMainGroup() {
		ImageView backgroundView = new ImageView(BACKGROUND_IMAGE);
		pushy = new PusherMan(PUSHY_IMAGE, ACTOR_SIZE * 4, ACTOR_SIZE * 6);
		manager.setPusherMan(pushy);

		wallGroup = createWallGroup();
		targetGroup = createTargetGroup();
		boxGroup = createBoxGroup();
		glassPane = createGlassPane();
		return new Group(backgroundView, wallGroup, targetGroup, boxGroup,
				pushy.getNode(), glassPane);
	}

	private StackPane createGlassPane() {
		final Label label = new Label("You made it");
		label.setStyle("-fx-text-fill: goldenrod; -fx-font-style: italic; -fx-font-weight: bold; -fx-font-size: 30; -fx-padding: 0 0 20 0;");

		StackPane glass = new StackPane();
		StackPane.setAlignment(label, Pos.BOTTOM_CENTER);
		glass.getChildren().addAll(label);
		glass.setStyle("-fx-background-color: rgba(0, 100, 100, 0.5); -fx-background-radius: 10;");
		glass.setMinWidth(340);
		glass.setMinHeight(412);
		// glass.setLayoutX(800);
		// glass.setLayoutY(800);
		glass.setTranslateX(800);
		glass.setTranslateY(10);
		return glass;
	}

	private Group createBoxGroup() {
		Group boxGroup = new Group();
		PushBox box1 = new PushBox(BOX_IMAGE, ACTOR_SIZE * 3, ACTOR_SIZE * 6);
		box1.setHitImage(BOX_HIT_IMAGE);
		boxGroup.getChildren().add(box1.getNode());

		PushBox box2 = new PushBox(BOX_IMAGE, ACTOR_SIZE * 5, ACTOR_SIZE * 7);
		box2.setHitImage(BOX_HIT_IMAGE);
		boxGroup.getChildren().add(box2.getNode());

		manager.addPushBoxes(box1, box2);
		return boxGroup;
	}

	private Group createTargetGroup() {
		Group targetGroup = new Group();
		Target target1 = new Target(TARGET_IMAGE, ACTOR_SIZE * 4,
				ACTOR_SIZE * 4);
		targetGroup.getChildren().add(target1.getNode());

		Target target2 = new Target(TARGET_IMAGE, ACTOR_SIZE * 3,
				ACTOR_SIZE * 6);
		targetGroup.getChildren().add(target2.getNode());

		manager.addTargetBoxes(target1, target2);
		return targetGroup;
	}

	private Group createWallGroup() {
		Group wallGroup = new Group();
		Wall wall = new Wall(WALL_IMAGE, ACTOR_SIZE * 2, ACTOR_SIZE * 3);
		wallGroup.getChildren().add(wall.getNode());
		manager.addWallBoxes(wall);

		wall = new Wall(WALL_IMAGE, ACTOR_SIZE * 3, ACTOR_SIZE * 3);
		wallGroup.getChildren().add(wall.getNode());
		manager.addWallBoxes(wall);

		wall = new Wall(WALL_IMAGE, ACTOR_SIZE * 4, ACTOR_SIZE * 3);
		wallGroup.getChildren().add(wall.getNode());
		manager.addWallBoxes(wall);

		wall = new Wall(WALL_IMAGE, ACTOR_SIZE * 5, ACTOR_SIZE * 3);
		wallGroup.getChildren().add(wall.getNode());
		manager.addWallBoxes(wall);

		wall = new Wall(WALL_IMAGE, ACTOR_SIZE * 2, ACTOR_SIZE * 4);
		wallGroup.getChildren().add(wall.getNode());
		manager.addWallBoxes(wall);

		wall = new Wall(WALL_IMAGE, ACTOR_SIZE * 5, ACTOR_SIZE * 4);
		wallGroup.getChildren().add(wall.getNode());
		manager.addWallBoxes(wall);

		wall = new Wall(WALL_IMAGE, ACTOR_SIZE * 2, ACTOR_SIZE * 5);
		wallGroup.getChildren().add(wall.getNode());
		manager.addWallBoxes(wall);

		wall = new Wall(WALL_IMAGE, ACTOR_SIZE * 5, ACTOR_SIZE * 5);
		wallGroup.getChildren().add(wall.getNode());
		manager.addWallBoxes(wall);

		wall = new Wall(WALL_IMAGE, ACTOR_SIZE * 6, ACTOR_SIZE * 5);
		wallGroup.getChildren().add(wall.getNode());
		manager.addWallBoxes(wall);

		wall = new Wall(WALL_IMAGE, ACTOR_SIZE * 7, ACTOR_SIZE * 5);
		wallGroup.getChildren().add(wall.getNode());
		manager.addWallBoxes(wall);

		wall = new Wall(WALL_IMAGE, ACTOR_SIZE * 2, ACTOR_SIZE * 6);
		wallGroup.getChildren().add(wall.getNode());
		manager.addWallBoxes(wall);

		wall = new Wall(WALL_IMAGE, ACTOR_SIZE * 7, ACTOR_SIZE * 6);
		wallGroup.getChildren().add(wall.getNode());
		manager.addWallBoxes(wall);

		wall = new Wall(WALL_IMAGE, ACTOR_SIZE * 2, ACTOR_SIZE * 7);
		wallGroup.getChildren().add(wall.getNode());
		manager.addWallBoxes(wall);

		wall = new Wall(WALL_IMAGE, ACTOR_SIZE * 7, ACTOR_SIZE * 7);
		wallGroup.getChildren().add(wall.getNode());
		manager.addWallBoxes(wall);

		wall = new Wall(WALL_IMAGE, ACTOR_SIZE * 2, ACTOR_SIZE * 8);
		wallGroup.getChildren().add(wall.getNode());
		manager.addWallBoxes(wall);

		wall = new Wall(WALL_IMAGE, ACTOR_SIZE * 5, ACTOR_SIZE * 8);
		wallGroup.getChildren().add(wall.getNode());
		manager.addWallBoxes(wall);

		wall = new Wall(WALL_IMAGE, ACTOR_SIZE * 6, ACTOR_SIZE * 8);
		wallGroup.getChildren().add(wall.getNode());
		manager.addWallBoxes(wall);

		wall = new Wall(WALL_IMAGE, ACTOR_SIZE * 7, ACTOR_SIZE * 8);
		wallGroup.getChildren().add(wall.getNode());
		manager.addWallBoxes(wall);

		wall = new Wall(WALL_IMAGE, ACTOR_SIZE * 2, ACTOR_SIZE * 9);
		wallGroup.getChildren().add(wall.getNode());
		manager.addWallBoxes(wall);

		wall = new Wall(WALL_IMAGE, ACTOR_SIZE * 3, ACTOR_SIZE * 9);
		wallGroup.getChildren().add(wall.getNode());
		manager.addWallBoxes(wall);

		wall = new Wall(WALL_IMAGE, ACTOR_SIZE * 4, ACTOR_SIZE * 9);
		wallGroup.getChildren().add(wall.getNode());
		manager.addWallBoxes(wall);

		wall = new Wall(WALL_IMAGE, ACTOR_SIZE * 5, ACTOR_SIZE * 9);
		wallGroup.getChildren().add(wall.getNode());
		manager.addWallBoxes(wall);

		return wallGroup;
	}

	private void loadImages() {
		BACKGROUND_IMAGE = new Image(PushyWorld.class.getResource(
				"/images/sand.jpg").toString());
		BOX_IMAGE = new Image(PushyWorld.class.getResource(
				"/images/kisterot.png").toString());
		BOX_HIT_IMAGE = new Image(PushyWorld.class.getResource(
				"/images/kisteblau.png").toString());
		PUSHY_IMAGE = new Image(PushyWorld.class.getResource(
				"/images/ghost1.png").toString());
		TARGET_IMAGE = new Image(PushyWorld.class.getResource(
				"/images/sandstone.jpg").toString());
		WALL_IMAGE = new Image(PushyWorld.class.getResource("/images/wall.jpg")
				.toString());
	}

	public void showLevelFinished() {
		transition.setToY(10);
		transition.setToX(10);
		transition.play();
	}

}

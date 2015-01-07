package de.montessori.pushygame.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import de.montessori.gameengine.Actor;
import de.montessori.gameengine.ActorManager;
import de.montessori.pushygame.PushyWorld;
import de.montessori.pushygame.actors.PushBox;
import de.montessori.pushygame.actors.PusherMan;
import de.montessori.pushygame.actors.Target;
import de.montessori.pushygame.actors.Wall;

public class PushyActorManager extends ActorManager implements
		EventHandler<KeyEvent> {

	/** All the PushBox objects currently in play */
	private List<PushBox> GAME_BOXES = new ArrayList<PushBox>();

	/** All the wall objects currently in play */
	private List<Wall> WALL_BOXES = new ArrayList<Wall>();

	/** All the Target objects currently in play */
	private List<Target> TARGET_BOXES = new ArrayList<Target>();

	private PusherMan pusherMan;

	private KeyEvent lastKeyEvent;

	private List<KeyCode> validKeyCodes = new ArrayList<KeyCode>(Arrays.asList(
			KeyCode.UP, KeyCode.DOWN, KeyCode.LEFT, KeyCode.RIGHT));

	public PushyActorManager(PushyWorld world) {
		super(world);
	}

	/**
	 * 
	 * @return a list of all PushBox objects.
	 */
	public List<PushBox> getAllPushBoxes() {
		return GAME_BOXES;
	}

	/**
	 * VarArgs of box objects to be added to the game.
	 * 
	 * @param boxes
	 */
	public void addPushBoxes(PushBox... box) {
		addActors(box);
		GAME_BOXES.addAll(Arrays.asList(box));
	}

	public void addPushBoxes(List<PushBox> boxes) {
		GAME_BOXES.addAll(boxes);
		for (PushBox box : boxes) {
			addActors((Actor) box);
		}
	}

	/**
	 * 
	 * @return a list of all wall boxes.
	 */
	public List<Wall> getAllWallBoxes() {
		return WALL_BOXES;
	}

	/**
	 * VarArgs of box objects to be added to the game.
	 * 
	 * @param boxes
	 */
	public void addWallBoxes(Wall... wall) {
		addActors(wall);
		WALL_BOXES.addAll(Arrays.asList(wall));
	}

	public void addWallBoxes(List<Wall> walls) {
		WALL_BOXES.addAll(walls);
		for (Wall wall : walls) {
			addActors((Actor) wall);
		}
	}

	/**
	 * 
	 * @return a list of all wall boxes.
	 */
	public List<Target> getAllTargetBoxes() {
		return TARGET_BOXES;
	}

	/**
	 * VarArgs of box objects to be added to the game.
	 * 
	 * @param boxes
	 */
	public void addTargetBoxes(Target... target) {
		addActors(target);
		TARGET_BOXES.addAll(Arrays.asList(target));
	}

	public void addTargetBoxes(List<Target> targets) {
		TARGET_BOXES.addAll(targets);
		for (Target target : targets) {
			addActors((Actor) target);
		}
	}

	public void setPusherMan(PusherMan pusherMan) {
		this.pusherMan = pusherMan;
		pusherMan.registerManager(this);
		addActors((Actor) pusherMan);
	}

	@Override
	public void handle(KeyEvent event) {
		System.out.println("KeyPressed: " + event.getCode().getName());
		if (validKeyCodes.contains(event.getCode())) {
			lastKeyEvent = event;

			// calculate new position for the pusherman and all movable boxes
			pusherMan.calculatePositions();

			// do the actual rendering
			for (Actor actor : getAllActors()) {
				actor.act();
			}

			if (levelIsFinished()) {
				((PushyWorld) getWorld()).showLevelFinished();
			}
		}
	}

	private boolean levelIsFinished() {
		for (int i = 0; i < getAllPushBoxes().size(); i++) {
			PushBox box = getAllPushBoxes().get(i);
			if (!box.isOnTarget()) {
				return false;
			}
		}
		return true;
	}

	public void startGame() {
		for (Actor actor : getAllActors()) {
			actor.act();
		}
	}

	public KeyEvent getLastKeyEvent() {
		return lastKeyEvent;
	}

	public boolean collideWithWall(Point2D targetPoint) {
		for (int i = 0; i < getAllWallBoxes().size(); i++) {
			Wall wall = getAllWallBoxes().get(i);
			if (wall.collide(targetPoint)) {
				return true;
			}
		}
		return false;
	}

	public boolean collideWithBox(Point2D targetPoint) {
		for (int i = 0; i < getAllPushBoxes().size(); i++) {
			PushBox box = getAllPushBoxes().get(i);
			if (box.collide(targetPoint)) {
				return true;
			}
		}
		return false;
	}

	public boolean hitTarget(Point2D targetPoint) {
		for (int i = 0; i < getAllTargetBoxes().size(); i++) {
			Target target = getAllTargetBoxes().get(i);
			if (target.collide(targetPoint)) {
				return true;
			}
		}
		return false;
	}

	public Point2D createTargetPoint(Point2D actualPosition) {
		KeyEvent event = getLastKeyEvent();
		Point2D calculatedTargetPoint = null;
		switch (event.getCode()) {
		case UP:
			calculatedTargetPoint = new Point2D(actualPosition.getX(),
					actualPosition.getY() - PushyWorld.ACTOR_SIZE);
			break;
		case RIGHT:
			calculatedTargetPoint = new Point2D(actualPosition.getX()
					+ PushyWorld.ACTOR_SIZE, actualPosition.getY());
			break;
		case DOWN:
			calculatedTargetPoint = new Point2D(actualPosition.getX(),
					actualPosition.getY() + PushyWorld.ACTOR_SIZE);
			break;
		case LEFT:
			calculatedTargetPoint = new Point2D(actualPosition.getX()
					- PushyWorld.ACTOR_SIZE, actualPosition.getY());
			break;
		default:

		}
		return calculatedTargetPoint;
	}

}

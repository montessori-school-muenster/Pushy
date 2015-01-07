package de.montessori.pushygame.actors;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import de.montessori.gameengine.Actor;
import de.montessori.pushygame.controller.PushyActorManager;

public class PusherMan extends Actor {

	private Point2D targetPoint = null;

	public PusherMan(Image image, double x, double y) {
		super(image, x, y);
	}

	@Override
	public void act() {
		if (targetPoint != null) {
			move(targetPoint);
			targetPoint = null;
		}
	}

	public void calculatePositions() {
		Point2D calculatedTargetPoint = ((PushyActorManager) getManager())
				.createTargetPoint(new Point2D(getX(), getY()));
		boolean isColliding = collide(calculatedTargetPoint);
		if (!isColliding) {
			targetPoint = calculatedTargetPoint;
			System.out.println("Pusherman-x: " + targetPoint.getX()
					+ "Pusherman-y: " + targetPoint.getY());
		}
	}

	@Override
	public boolean collide(Point2D calculatedTargetPoint) {
		if (((PushyActorManager) getManager())
				.collideWithWall(calculatedTargetPoint)
				|| ((PushyActorManager) getManager())
						.collideWithBox(calculatedTargetPoint)) {
			return true;
		}
		return false;

	}

	public void setTargetPoint(Point2D targetPoint) {
		this.targetPoint = targetPoint;
	}

}
